package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.BoardDTO;
import poly.dto.PagingDTO;
import poly.dto.UserDTO;
import poly.service.HashKey;
import poly.service.IBoardService;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.Email;
import poly.util.EmailSender;
import poly.util.MailUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	private static String connectIP = "http://13.209.27.0:8080/"; //자기 ip 쓰기 (이메일 보내기 url)
	
	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
	 * */
	@Resource(name = "UserService")
	private IUserService userService;
	
	
	
	@Autowired
	private EmailSender emailSender;
	
	/*
	 * 함수명 위의 value="notice/NoticeList" => /notice/NoticeList.do로 호출되는 url은 무조건 이 함수가 실행된다.
	 * method=RequestMethod.GET => 폼 전송방법을 지정하는 것으로 get방식은 GET, post방식은 POST이다.
	 * method => 기입안하면 GET, POST 모두 가능하나, 가급적 적어주는 것이 좋다.
	 * */
	
	@RequestMapping(value="index")
	public String Index(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome index");
		return "/index";
	}
	
	@RequestMapping(value="login")
	public String Login(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome login");
		return "/login";
	}
	
	@RequestMapping(value="register")
	public String Register(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome register");
		return "/register";
	}
	
	@RequestMapping(value="idfind")
	public String Idfind(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome idfind");
		return "/idfind";
	}
	
	@RequestMapping(value="pwfind")
	public String Pwfind(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome pwfind");
		return "/pwfind";
	}
	
	
	
	@RequestMapping(value="rc", method=RequestMethod.POST)
	public String Rc(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info("welcome rc");
		
		String id = CmmUtil.nvl(request.getParameter("id"));
		log.info("id : " + id);
		String password = CmmUtil.nvl(request.getParameter("password"));
		log.info("password : " + password);
		String name = CmmUtil.nvl(request.getParameter("name"));
		log.info("name : " + name);
		String gender= CmmUtil.nvl(request.getParameter("gender"));
		log.info("gender : " + gender);
		String email= CmmUtil.nvl(request.getParameter("email"));
		log.info("email : " + email);
		String phone= CmmUtil.nvl(request.getParameter("phone"));
		log.info("phone : " + phone);	
		
		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserId(id);
		uDTO.setPassword(password);
		uDTO.setUserName(name);
		uDTO.setGender(gender);
		uDTO.setEmail(email);
		uDTO.setPhone(phone);
		
		int result = userService.insertMember(uDTO);
		log.info(result);
		
		UserDTO uDTO2 = userService.getUserInfo(uDTO);
		String userNo = uDTO2.getUserNo();
		log.info(userNo);
		
		String url =connectIP+"emailConfirm.do";
		String parameter ="?userNo="+userNo;
		String body ="<a href='"+url+parameter+"'>인증 하기</a>";
		MailUtil.sendMail(email, "AirCheck 인증메일입니다.", body);
			
		model.addAttribute("msg", "회원가입이 완료되었습니다.");
		model.addAttribute("url", "/index.do");
		
		String msg = "";
		String url2 = "";
		
		if(result == 1) {
			msg = "성공.";
			url2 = "/login.do";
			
		}else{
			msg = "실패.";
			url2 = "/index.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url2", url2);	
		
		uDTO = null;
		
		return "/alert";
	}
	
	@RequestMapping(value="/emailConfirm", method=RequestMethod.GET)
	public String emailConfirm(HttpServletRequest request, HttpSession session,
			ModelMap model) throws Exception {
		
		log.info("welcome emailConfirm");
		
		String userNo = CmmUtil.nvl(request.getParameter("userNo"));
		log.info(userNo);
		
		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserNo(userNo);
		
		
		int result = userService.emailConfirm(uDTO);
		log.info("인증이 완료되었습니다."+result);
		
		/*model.addAttribute("uDTO", uDTO);*/
		
		String msg;
		String url;
		
		if(result == 1) {
			msg = "이메일 인증되었습니다.";
			url = "/login.do";
			
		}else{
			msg = "이메일 인증되지않았습니다.";
			url = "/index.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		uDTO = null;
		
		return "/alert";
		
	}
	
	@RequestMapping(value="/ajaxTest", method=RequestMethod.POST)
	public @ResponseBody int ajaxTest(
			@RequestParam(value="id")String id
			) throws Exception {
		
		log.info("welcome ajaxTest");
		log.info("id :" + id);
		
		
		int count = userService.checkDuplication(id);
		
		log.info(count);
		
		return count;
		
	}
	
	
	/**
	 * 회원로그인
	 * */
	@RequestMapping(value="loginProc", method=RequestMethod.POST)
	public String loginProc(HttpServletRequest request, HttpSession session,
			ModelMap model) throws Exception {
		
		log.info("welcome loginProc");
		
		
		String id = CmmUtil.nvl(request.getParameter("id"));
		String password = CmmUtil.sha256(HashKey.hashEncKey+ CmmUtil.nvl(request.getParameter("password")));
		
		
		log.info("id : " + id);
		log.info("password : " + password);
		
		
		UserDTO uDTO = new UserDTO();
		
		
		uDTO.setUserId(id);
		uDTO.setPassword(password);
		
		
		//서비스 결과가 uDTO 객체를 덮어쓰기 합니다.
		uDTO = userService.getLoginInfo(uDTO);
		
		if(uDTO == null) {
			model.addAttribute("msg", "로그인이 실패하였습니다.");
			model.addAttribute("url", "/login.do");
			return "/alert";
		}
		
		String emailConfirm = uDTO.getEmailConfirm();
		
		if(uDTO == null || ("N".equals(emailConfirm))) {
			model.addAttribute("msg", "이메일 인증을 해주세요");
			model.addAttribute("url", "/login.do");
			
		} else {
			session.setAttribute("userId", uDTO.getUserId());
			session.setAttribute("userName", uDTO.getUserName());
			session.setAttribute("userNo", uDTO.getUserNo());
			session.setAttribute("gender", uDTO.getGender());
			session.setAttribute("email", uDTO.getEmail());
			session.setAttribute("phone", uDTO.getPhone());
			session.setAttribute("password", uDTO.getPassword());
			model.addAttribute("msg", "로그인 되었습니다.");
			model.addAttribute("url", "/index.do");
		}
		
		
		log.info("end loginProc!!");
		
		uDTO = null;
		
		return "/alert" ;
		
		}
		
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		
		log.info("welcome logout");
		
		session.invalidate();
		
		model.addAttribute("msg", "로그아웃 되었습니다.");
		model.addAttribute("url", "/index.do");
		
		
		
		
		return "/alert";
		
	}
	
	@RequestMapping(value="idfind2", method=RequestMethod.POST)
	public String Idfind2(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info("welcome idfind2");
		
		String name = CmmUtil.nvl(request.getParameter("name"));
		String email = CmmUtil.nvl(request.getParameter("email"));
		
		
		log.info("name : " + name);
		log.info("email : " + email);
		
		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserName(name);
		uDTO.setEmail(email);
		
		uDTO=userService.getIdfind(uDTO);
		
		
		String msg;
		String url;
		if(uDTO == null) {
			model.addAttribute("msg", "올바른 정보를 입력해 주세요.");
			model.addAttribute("url", "/idfind.do");
			
			return "/alert";
		} 
			
		
		model.addAttribute("uDTO",uDTO);
		
		uDTO = null;
		
		return "/idfindview";
	}
	
	@RequestMapping(value="pwfind2", method=RequestMethod.POST)
	public String PWfind2(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model) throws Exception {
		
		log.info("welcome pwfind2");
		
		String id = CmmUtil.nvl(request.getParameter("id"));
		log.info("id : " + id);
		String email = CmmUtil.nvl(request.getParameter("email"));
		log.info("email : " + email);
		
		Email sendEmail = new Email();
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserId(id);
		uDTO.setEmail(email);
		log.info("id : " + uDTO.getUserId());
		log.info("email : " + uDTO.getEmail());
		
		HashMap<String, Object> hMap = new HashMap<>();
		hMap.put("uDTO", uDTO);
		hMap = userService.updateTmpPass(hMap);
		
		int result = (int) hMap.get("result");
		
		String msg = "";
		String url = "";
		
		if(result == 0) {
			msg = "회원정보가 일치하지 않습니다.";
			url = "/index.do";
		} else {
			sendEmail.setReciver(email);
			sendEmail.setSubject(id + "님 임시비밀번호");
			sendEmail.setContent(sendEmail.setContents(hMap));
			
			emailSender.SendEmail(sendEmail);
			
			msg = "임시비밀번호가 전송되었습니다.";
			url = "/login.do";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		hMap = null;
		uDTO = null;
		
		log.info("passwordfind end");
			
		return "/alert";
	} 
		
	
	@RequestMapping(value="mypage")
	public String Mypage(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		log.info("welcome mypage");
		
		String userNo = (String)session.getAttribute("userNo");
		log.info(userNo);
		
		String id = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(id);
		
		if(id == "" || id.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {

		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserNo(userNo);
		
		
		uDTO=userService.getMyPage(uDTO);
		
		
		model.addAttribute("uDTO",uDTO);
		
		uDTO = null;
		
		return "/mypage";
		
		}
	}
	
	@RequestMapping(value="mypagerevise")
	public String MyPageRevise(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		log.info("welcome userrevise");
		
		
		String userNo = CmmUtil.nvl(request.getParameter("userNo"));
		log.info(userNo);
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(userId == "" || userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserNo(userNo);
		
		uDTO=userService.getMyPage(uDTO);
				
		model.addAttribute("uDTO",uDTO);
		
		uDTO = null;
		
		return "/mypagerevise";
		
		}
	}
	
	@RequestMapping(value="mypagerevise2")
	public String MyPageRevise2(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		log.info("welcome mypage revise2");
		
		String userNo = CmmUtil.nvl(request.getParameter("userNo"));
		log.info(userNo);
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(userId == "" || userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
			
			UserDTO uDTO = new UserDTO();
			uDTO.setUserNo(userNo);
			
			uDTO=userService.getMyPage(uDTO);
			String pw = uDTO.getPassword();
			log.info(pw);
			
			String id = CmmUtil.nvl((String)request.getParameter("id"));
			log.info(id);
			
			
			if(CmmUtil.nvl((String)request.getParameter("password")).equals(pw)) {
				String password = pw;
				log.info(password);
				uDTO.setPassword(password);
			} else {
				String password = CmmUtil.sha256(HashKey.hashEncKey+ CmmUtil.nvl(request.getParameter("password")));
				log.info(password);
				uDTO.setPassword(password);
			}
			
			String name = CmmUtil.nvl((String)request.getParameter("name"));
			log.info(name);
			String gender = CmmUtil.nvl((String)request.getParameter("gender"));
			log.info(gender);
			String email = CmmUtil.nvl((String)request.getParameter("email"));
			log.info(email);
			String phone = CmmUtil.nvl((String)request.getParameter("phone"));
			log.info(phone);
			
			
			
			uDTO.setUserId(id);
			uDTO.setUserName(name);
			uDTO.setGender(gender);
			uDTO.setEmail(email);
			uDTO.setPhone(phone);
			
			int result = userService.mypagerevise2(uDTO);
			log.info(result);
			
			String url;
			String msg;
			
			if(result == 1) {
				msg = "수정되었습니다.";
				url = "/index.do";
			} else {
				msg = "실패하였습니다.";
				url = "/index.do";
			}
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			uDTO = null;
			
			return "/alert";
			
		}
		
		
	}
	
	@RequestMapping(value="mypagedelete")
	public String mypagedelete(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		
		log.info("welcome mypagedelete");
			
		String userNo = (String)session.getAttribute("userNo");
		log.info(userNo);
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(userId == "" || userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		
		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserNo(userNo);
		
		int result = userService.mypagedelete(uDTO);
		log.info(result);
		


		String url;
		String msg;
		if(result == 1) {
			model.addAttribute("msg", "탈퇴되었습니다.");
			model.addAttribute("url", "/index.do");
			session.invalidate();
		} else {
			model.addAttribute("msg", "실패하였습니다.");
			model.addAttribute("url", "/mypage.do");
		}
		
		uDTO = null;
		
		return "/alert";
		
		}
		
	}
	
	@RequestMapping(value="UserList")
	public String UserList(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome UserList");
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(!userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		String keyword = CmmUtil.nvl((String)request.getParameter("keyword"),"");
		
		log.info(keyword);
		log.info("TEST"+keyword +"TEST");
		
		int totalcount = userService.getUserListTotalCount(keyword);
		int page = 1;
		
		page = Integer.parseInt(CmmUtil.nvl(request.getParameter("page"),"1"));
		System.out.println(totalcount +"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		// 페이징 Dto 생성
		PagingDTO pDTO = new PagingDTO();
		pDTO.setTotalItemCount(totalcount);
		pDTO.setKeyword(keyword);
		pDTO.setPage(page);
		
		List<UserDTO> ulist = new ArrayList<>();
		
		ulist = userService.getUserList(pDTO);
			
		model.addAttribute("ulist", ulist);
		
		model.addAttribute("pDTO", pDTO);
		
		ulist = null;
		
		pDTO = null;
		
		return "/UserList";
		
		}
	}
	
	@RequestMapping(value="UserDetail", method=RequestMethod.GET)
	public String UserDetail(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		log.info("welcome UserDetail");
		
		String userNo = CmmUtil.nvl((String)request.getParameter("userNo"));
		log.info(userNo);
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(!userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		
		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserNo(userNo);
		
		
		uDTO=userService.UserDetail(uDTO);
		
		
		model.addAttribute("uDTO",uDTO);
		
		uDTO = null;
		
		return "/UserDetail";
		
		}
	}
	
	@RequestMapping(value="UserRevise")
	public String UserRevise(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		log.info("welcome UserRevise");
		
		
		String userNo = CmmUtil.nvl(request.getParameter("userNo"));
		log.info(userNo);
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(!userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		UserDTO uDTO = new UserDTO();
		uDTO.setUserNo(userNo);
		
		uDTO=userService.UserDetail(uDTO);
				
		model.addAttribute("uDTO",uDTO);
		
		uDTO = null;
		
		return "/UserRevise";
		
		}
	}
	
	@RequestMapping(value="UserRevise2")
	public String UserRevise2(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		log.info("welcome UserRevise2");
		
		String userNo = CmmUtil.nvl(request.getParameter("userNo"));
		log.info(userNo);
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(!userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
			
			UserDTO uDTO = new UserDTO();
			uDTO.setUserNo(userNo);
			
			uDTO=userService.UserDetail(uDTO);
			String pw = uDTO.getPassword();
			log.info(pw);
			
			String id = CmmUtil.nvl((String)request.getParameter("id"));
			log.info(id);
			
			if(CmmUtil.nvl((String)request.getParameter("password")).equals(pw)) {
				String password = pw;
				log.info(password);
				uDTO.setPassword(password);
			} else {
				String password = CmmUtil.sha256(HashKey.hashEncKey+ CmmUtil.nvl(request.getParameter("password")));
				log.info(password);
				uDTO.setPassword(password);
			}
			
			String name = CmmUtil.nvl((String)request.getParameter("name"));
			log.info(name);
			String gender = CmmUtil.nvl((String)request.getParameter("gender"));
			log.info(gender);
			String email = CmmUtil.nvl((String)request.getParameter("email"));
			log.info(email);
			String phone = CmmUtil.nvl((String)request.getParameter("phone"));
			log.info(phone);
			
			uDTO.setUserId(id);
			uDTO.setUserName(name);
			uDTO.setGender(gender);
			uDTO.setEmail(email);
			uDTO.setPhone(phone);
			
			int result = userService.UserRevise2(uDTO);
			log.info(result);
			
			String url;
			String msg;
			
			if(result == 1) {
				msg = "수정되었습니다.";
				url = "/UserList.do";
			} else {
				msg = "실패하였습니다.";
				url = "/index.do";
			}
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			uDTO = null;
			
			return "/alert";
		}
		
		
	}
	
	@RequestMapping(value="UserDelete")
	public String UserDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		
		log.info("welcome UserDelete");
			
		String userNo = CmmUtil.nvl(request.getParameter("userNo"));
		log.info(userNo);
		
		String userId = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(userId);
		
		if(!userId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		
		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserNo(userNo);
		
		int result = userService.UserDelete(uDTO);
		
		
		log.info(result);
		


		String url;
		String msg;
		if(result == 1) {
			model.addAttribute("msg", "탈퇴되었습니다.");
			model.addAttribute("url", "/index.do");

		} else {
			model.addAttribute("msg", "실패하였습니다.");
			model.addAttribute("url", "/UserList.do");
		}
		
		uDTO = null;
		
		return "/alert";
		
		}
		
	}
	
	
}

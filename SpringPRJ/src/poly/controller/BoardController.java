package poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

import poly.dto.BoardDTO;
import poly.dto.NoticeDTO;
import poly.dto.PagingDTO;
import poly.dto.UserDTO;
import poly.filter.UrlFilter;
import poly.service.IBoardService;
import poly.service.INoticeService;
import poly.service.IUserService;
import poly.util.CmmUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class BoardController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "BoardService")
	private IBoardService boardService;
	
	
	
	@RequestMapping(value="BoardList")
	public String BoardList(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome blog");
		
		log.info(this.getClass().getName() + ".BoardList start!");
		String keyword = CmmUtil.nvl((String)request.getParameter("keyword"),"");
		
		log.info(keyword);
		log.info("TEST"+keyword +"TEST");
		
		int totalcount = boardService.getBoardListTotalCount(keyword);
		int page = 1;
		
		page = Integer.parseInt(CmmUtil.nvl(request.getParameter("page"),"1"));
		System.out.println(totalcount +"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		// 페이징 Dto 생성
		PagingDTO pDTO = new PagingDTO();
		pDTO.setTotalItemCount(totalcount);
		pDTO.setKeyword(keyword);
		pDTO.setPage(page);
		
		List<BoardDTO> blist = new ArrayList<>();
		
		blist = boardService.getBoardList(pDTO);

		model.addAttribute("blist", blist);
		
		model.addAttribute("pDTO", pDTO);
		
		log.info(this.getClass().getName() + ".BoardList end!");
		
		blist = null;
		
		pDTO = null;
		
		return "/BoardList";
	}
	
	
	
	
	@RequestMapping(value="blogwrite")
	public String Blogwrite(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome blogwrite");
		
		String id = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(id);
		
		if(id == "") {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		return "/blogwrite";
		
		}
	}
	
	@RequestMapping(value="BoardDetail", method=RequestMethod.GET)
	public String BoardDetail(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome blogdetail");
		
		String boardNo = CmmUtil.nvl(request.getParameter("boardNo"));
		log.info(boardNo);
		
				
		BoardDTO bDTO = new BoardDTO();
		
		bDTO.setBoardNo(boardNo);
		
		bDTO = boardService.getBoardDetail(bDTO);
		
		log.info(bDTO.getTitle());
		log.info(bDTO.getContent());
		log.info(bDTO.getUserName());
		log.info(bDTO.getChgDt());
		log.info(bDTO.getViewCnt());
		
		ModelAndView view = new ModelAndView();
		
	    Cookie cookies[] = request.getCookies();
	    log.info(cookies);
	    
	    
	   
        // 비교하기 위해 새로운 쿠키
        Cookie viewCookie = null;
        log.info(viewCookie);
 
        // 쿠키가 있을 경우 
        if (cookies != null && cookies.length > 0) 
        {
            for (int i = 0; i < cookies.length; i++)
            {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌 
                if (cookies[i].getName().equals("cookie"))
                { 
                    System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
                    viewCookie = cookies[i];
                    log.info(viewCookie);
                }
                log.info("cookie 이름" + cookies[i].getName());
                log.info("cookie 값" + cookies[i].getValue());
            }
        } 
        
        if (bDTO != null) {
            System.out.println("System - 해당 상세 리뷰페이지로 넘어감");
            
            view.addObject("bDTO", bDTO);
            log.info(viewCookie);
 
            // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
            if (viewCookie == null) {    
                System.out.println("cookie 없음");
                
                // 쿠키 생성(이름, 값)
                Cookie newCookie = new Cookie("cookie", "|" + boardNo + "|");
                newCookie.setPath("/");  
                
                                
                // 쿠키 추가
                response.addCookie(newCookie);
 
                // 쿠키를 추가 시키고 조회수 증가시킴
                int viewCnt = boardService.updateViewCnt(bDTO);
                
                if(viewCnt>0) {
                    System.out.println("조회수 증가");
                    
                    String viewCnt2 = String.valueOf(viewCnt);
            		
            		bDTO.setViewCnt(viewCnt2);
            		
            		log.info(viewCnt2);
            		
            		bDTO = boardService.getBoardDetail(bDTO);
            		
            		log.info(bDTO.getTitle());
            		log.info(bDTO.getContent());
            		log.info(bDTO.getUserName());
            		log.info(bDTO.getChgDt());
            		log.info(bDTO.getViewCnt());
            		
            		model.addAttribute("bDTO",bDTO);
            		
            		   
                }else {
                    System.out.println("조회수 증가 에러");
                }
                
            } 		// viewCookie가 null이 아닐경우 쿠키가 있으므로 조회수 증가 로직을 처리하지 않음.
            	else {
                System.out.println("cookie 있음");
                
                log.info(viewCookie);
                
                // 쿠키 값 받아옴.
                String value = viewCookie.getValue();
                
                System.out.println("cookie 값 : " + value);
                
                bDTO = boardService.getBoardDetail(bDTO);
                
                log.info(bDTO.getTitle());
        		log.info(bDTO.getContent());
        		log.info(bDTO.getUserName());
        		log.info(bDTO.getChgDt());
        		log.info(bDTO.getViewCnt());
        		
        		model.addAttribute("bDTO",bDTO);
        
            }
            
            
            
            
        }
        

            view.setViewName("BoardDetail");
            
            bDTO = null;
            
            return "/BoardDetail";
            
  } 
        
        
        
    
	
	@RequestMapping(value="BoardError", method=RequestMethod.POST)
	public String BoardError(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome BoardError");
		
		
		
		
		
		return "/BoardError";
	}

        
		
	
	
	@RequestMapping(value="blogregister", method=RequestMethod.POST)
	public String Blogregister(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome blogregister");
		
		UrlFilter filter = new UrlFilter(request);
		
		String title = CmmUtil.nvl(request.getParameter("title"));
		log.info("title : " + title);
		
		String content= CmmUtil.nvl(filter.getParameter("content"));
		log.info("content : " + content);
		
		String id = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(id);
		
		String userNo = CmmUtil.nvl((String)session.getAttribute("userNo"));
		log.info(userNo);
		
		String name = CmmUtil.nvl((String)session.getAttribute("userName"));
		log.info(name);
		
		
		BoardDTO bDTO = new BoardDTO();
		
		bDTO.setTitle(title);
		bDTO.setContent(content);
		bDTO.setUserId(id);
		bDTO.setUserNo(userNo);
		bDTO.setUserName(name);
		
		int result = boardService.insertBoard(bDTO);
		
		log.info(result);
		
		String msg;
		String url;
		if(result==1) {
			model.addAttribute("msg", "등록이 완료되었습니다.");
			model.addAttribute("url", "/BoardList.do");
		} else {
			model.addAttribute("msg", "등록이 되지않았습니다.");
			model.addAttribute("url", "/index.do");
		}
		
		bDTO = null;
		
		return "/alert";
	}
	
	@RequestMapping(value="BoardRevise")
	public String BoardRevise(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome BoardRevise");
		
		String boardNo = CmmUtil.nvl(request.getParameter("boardNo"));
		log.info(boardNo);
		
		String id = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(id);
		
		if(id == "") {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
				
		BoardDTO bDTO = new BoardDTO();
		
		bDTO.setBoardNo(boardNo);
				
		bDTO = boardService.getBoardDetail(bDTO);
		
		model.addAttribute("bDTO",bDTO);
		
		bDTO = null;
		
		return "/BoardRevise";
		
		}
	}
	
	
	@RequestMapping(value="BoardRevise2")
	public String BoardRevise2(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception{
		
		System.out.println("welcome BoardRevise2");
		
		UrlFilter filter = new UrlFilter(request);
		
		String name = CmmUtil.nvl((String)request.getParameter("name"));
		log.info(name);
		String title = CmmUtil.nvl((String)request.getParameter("title"));
		log.info(title);
		String content = CmmUtil.nvl((String)filter.getParameter("content"));
		log.info(content);
		String chgDt = CmmUtil.nvl((String)request.getParameter("chgDt"));
		log.info(chgDt);
		String viewCnt = CmmUtil.nvl((String)request.getParameter("viewCnt"));
		log.info(viewCnt);
		String boardNo = CmmUtil.nvl(request.getParameter("boardNo"));
		log.info(boardNo);
		
		String id = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(id);
		
		if(id == "") {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
			
			BoardDTO bDTO = new BoardDTO();
			
			bDTO.setUserName(name);
			log.info(name);
			bDTO.setTitle(title);
			log.info(title);
			bDTO.setContent(content);
			log.info(content);
			bDTO.setChgDt(chgDt);
			log.info(chgDt);
			bDTO.setViewCnt(viewCnt);
			log.info(viewCnt);
			bDTO.setBoardNo(boardNo);
			log.info(boardNo);
					
			int result = boardService.BoardRevise2(bDTO);
			log.info(result);
			
			String url;
			String msg;
			
			if(result == 1) {
				msg = "수정되었습니다.";
				url = "/BoardList.do";
			} else {
				msg = "실패하였습니다.";
				url = "/index.do";
			}
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			bDTO = null;
			
			return "/alert";
			
		}
				
		
	}
	
	@RequestMapping(value="BoardDelete")
	public String BoardDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) throws Exception {
		
		log.info("welcome BoardDelete");
			
		String boardNo = CmmUtil.nvl(request.getParameter("boardNo"));
		log.info(boardNo);
		
		String id = CmmUtil.nvl((String)session.getAttribute("userId"));
		log.info(id);
		
		if(id == "") {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("url", "/login.do");
			
			
			return "/alert";
			
		} else {
		
		
		BoardDTO bDTO = new BoardDTO();
		
		bDTO.setBoardNo(boardNo);
		
		int result = boardService.BoardDelete(bDTO);
		
		
		log.info(result);
		


		String url;
		String msg;
		if(result == 1) {
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "/BoardList.do");
		} else {
			model.addAttribute("msg", "실패하였습니다.");
			model.addAttribute("url", "/index.do");
		}
		
		bDTO = null;
		
		return "/alert";
		
		}
		
	}
	
}

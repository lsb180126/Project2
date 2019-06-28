package poly.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.PagingDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.UserMapper;
import poly.service.HashKey;
import poly.service.IUserService;
import poly.util.CmmUtil;

@Service("UserService")
public class UserService implements IUserService {

	@Resource(name = "UserMapper")
	private UserMapper userMapper;

	/**
	 * 회원가입
	 * */
	@Override
	public int insertMember(UserDTO uDTO) throws Exception {

		//회원번호 가져오기
		UserDTO rDTO = userMapper.getUserNo();
		
		//회원번호 못가져왔을때 에러 대비용으로 메모리 올리기
		if (rDTO==null) {
			rDTO = new UserDTO();
		}
		
		//회원번호를 userNo 변수에 저장하기
		String userNo = CmmUtil.nvl(rDTO.getUserNo());
		
		//기존 전달받은 회원정보(아이디, 패스워드 등등등)에 앞서 저장한 회원번호 넣기
		uDTO.setUserNo(userNo);
		
		//회원정보의 패스워드를 해시함수 돌리기
		String password = CmmUtil.nvl(uDTO.getPassword());
		
		//해시실행(hashPassword 변수에 해시로 암호회된 패스워드 값이 저장됨)
		String hashPassword = CmmUtil.sha256(HashKey.hashEncKey + password);
		
		//해시로 암호화된 값을 회원정보에 덮어쓰기
		uDTO.setPassword(hashPassword);
		
		
		return userMapper.insertMember(uDTO);
	}

	@Override
	public int checkDuplication(String id) throws Exception {

		return userMapper.checkDuplication(id);
	}

	@Override
	public UserDTO getUserInfo(UserDTO uDTO) throws Exception {

		return userMapper.getUserInfo(uDTO);
	}

	@Override
	public int emailConfirm(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.emailConfirm(uDTO);
	}

	@Override
	public UserDTO getLoginInfo(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getLoginInfo(uDTO);
	}

	@Override
	public UserDTO getIdfind(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getIdfind(uDTO);
	}

	@Override
	public HashMap<String, Object> updateTmpPass(HashMap<String, Object> hMap) throws Exception {
		// TODO Auto-generated method stub
		UserDTO uDTO = (UserDTO) hMap.get("uDTO");
		Long temp_Pw = (long) (Math.random() * 9000000000l) + 1000000000l;
		String temp_password = Long.toHexString(temp_Pw);
		
		
		//회원정보의 패스워드를 해시함수 돌리기
		String password = (String) temp_password;
		
		//해시실행(hashPassword 변수에 해시로 암호회된 패스워드 값이 저장됨)
		String hashPassword = CmmUtil.sha256(HashKey.hashEncKey + password);
		
		//해시로 암호화된 값을 회원정보에 덮어쓰기
		uDTO.setPassword(hashPassword);
		
		int result = userMapper.updateTmpPass(uDTO);

		hMap.put("tmpPass", temp_password);
		hMap.put("result", result);
		return hMap;
	}

	@Override
	public UserDTO getMyPage(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getMyPage(uDTO);
	}

	@Override
	public int mypagerevise2(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub

		System.out.print(uDTO.getUserName());
		System.out.print(uDTO.getPhone());
		System.out.print(uDTO.getPassword());
		System.out.print(uDTO.getUserId());
		return userMapper.mypagerevise2(uDTO);
	}

	@Override
	public int mypagedelete(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.mypagedelete(uDTO);
	}

	@Override
	public List<UserDTO> getUserList(PagingDTO pDTO) throws Exception {
		
		return userMapper.getUserList(pDTO);
	}

	@Override
	public UserDTO UserDetail(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.UserDetail(uDTO);
	}

	@Override
	public int UserRevise2(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.UserRevise2(uDTO);
	}

	@Override
	public int UserDelete(UserDTO uDTO) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.UserDelete(uDTO);
	}

	@Override
	public int getUserListTotalCount(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getUserListTotalCount(keyword);
	}

}
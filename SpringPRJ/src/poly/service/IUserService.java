package poly.service;

import java.util.HashMap;
import java.util.List;

import poly.dto.PagingDTO;
import poly.dto.UserDTO;

public interface IUserService {

	public int insertMember(UserDTO uDTO) throws Exception;

	public int checkDuplication(String id) throws Exception;

	public UserDTO getUserInfo(UserDTO uDTO) throws Exception;

	public int emailConfirm(UserDTO uDTO) throws Exception;

	public UserDTO getLoginInfo(UserDTO uDTO) throws Exception;

	public UserDTO getIdfind(UserDTO uDTO) throws Exception;

	public HashMap<String, Object> updateTmpPass(HashMap<String, Object> hMap) throws Exception;

	public UserDTO getMyPage(UserDTO uDTO) throws Exception;

	public int mypagerevise2(UserDTO uDTO) throws Exception;

	public int mypagedelete(UserDTO uDTO) throws Exception;

	public List<UserDTO> getUserList(PagingDTO pDTO) throws Exception;

	public UserDTO UserDetail(UserDTO uDTO) throws Exception;

	public int UserRevise2(UserDTO uDTO) throws Exception;

	public int UserDelete(UserDTO uDTO) throws Exception;

	public int getUserListTotalCount(String keyword) throws Exception;

}
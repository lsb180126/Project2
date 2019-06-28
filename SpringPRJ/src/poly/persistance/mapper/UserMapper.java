package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.PagingDTO;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface UserMapper {

	// 회원번호 가져오기
	public UserDTO getUserNo() throws Exception;

	public int insertMember(UserDTO uDTO) throws Exception;

	public int checkDuplication(String id) throws Exception;

	public UserDTO getUserInfo(UserDTO uDTO) throws Exception;

	public int emailConfirm(UserDTO uDTO) throws Exception;

	public UserDTO getLoginInfo(UserDTO uDTO) throws Exception;

	public UserDTO getIdfind(UserDTO uDTO) throws Exception;

	public int updateTmpPass(UserDTO uDTO) throws Exception;

	public UserDTO getMyPage(UserDTO uDTO) throws Exception;

	public int mypagerevise2(UserDTO uDTO) throws Exception;

	public int mypagedelete(UserDTO uDTO) throws Exception;

	public List<UserDTO> getUserList(PagingDTO pDTO) throws Exception;

	public UserDTO UserDetail(UserDTO uDTO) throws Exception;

	public int UserRevise2(UserDTO uDTO) throws Exception;

	public int UserDelete(UserDTO uDTO) throws Exception;

	public int getUserListTotalCount(String keyword) throws Exception;

}
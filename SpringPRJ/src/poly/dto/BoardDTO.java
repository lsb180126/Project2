/*package poly.dto;

*//**
 * @author 이협건
 * @version 1.1 공지사항 DTO
 *//*
public class NoticeDTO {

	private String notice_seq; // 기본키, 순번
	private String title; // 제목
	private String notice_yn; // 공지글 여부
	private String contents; // 글 내용
	private String user_id; // 작성자
	private String read_cnt; // 조회수
	private String reg_id; // 등록자 아이디
	private String reg_dt; // 등록일
	private String chg_id; // 수정자 아이디
	private String chg_dt; // 수정일

	private String user_name; // 등록자명

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getNotice_seq() {
		return notice_seq;
	}

	public void setNotice_seq(String notice_seq) {
		this.notice_seq = notice_seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotice_yn() {
		return notice_yn;
	}

	public void setNotice_yn(String notice_yn) {
		this.notice_yn = notice_yn;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(String read_cnt) {
		this.read_cnt = read_cnt;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getChg_id() {
		return chg_id;
	}

	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

	public String getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

}
*/

package poly.dto;

public class BoardDTO {
	private String boardNo;
	private String title;
	private String content;
	private String regUserNo;
	private String regDt;
	private String chgUserNo;
	private String chgDt;
	private String userNo;
	private String userId;
	private String userName;
	private String viewCnt;
	
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegUserNo() {
		return regUserNo;
	}
	public void setRegUserNo(String regUserNo) {
		this.regUserNo = regUserNo;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getChgUserNo() {
		return chgUserNo;
	}
	public void setChgUserNo(String chgUserNo) {
		this.chgUserNo = chgUserNo;
	}
	public String getChgDt() {
		return chgDt;
	}
	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	
	
	
	
}
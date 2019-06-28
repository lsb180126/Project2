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

public class InCheonAirplaneDTO {
	private String airline;
	private String flightId;
	private String scheduleDateTime;
	private String estimatedDateTime;
	private String airport;
	private String gatenumber;
	private String remark;
	private String terminalid;
	
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getScheduleDateTime() {
		return scheduleDateTime;
	}
	public void setScheduleDateTime(String scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}
	public String getEstimatedDateTime() {
		return estimatedDateTime;
	}
	public void setEstimatedDateTime(String estimatedDateTime) {
		this.estimatedDateTime = estimatedDateTime;
	}
	public String getAirport() {
		return airport;
	}
	public void setAirport(String airport) {
		this.airport = airport;
	}
	public String getGatenumber() {
		return gatenumber;
	}
	public void setGatenumber(String gatenumber) {
		this.gatenumber = gatenumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTerminalid() {
		return terminalid;
	}
	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}
	
	
	
	
	
	
}
package poly.dto;


public class PagingDTO {
	
    private String keyword;
    private int itemPerPage = 10;     //한 페이지에 들어있는 아이템의 수

    private int nextPage = 10;         //뛰어 넘는 페이지의 수

    private int page = 1;                 //페이지 변수

    private int totalItemCount;         //총 아이템 수 

    

    public int getCurrentPage() {     //현재 위치한 페이지를 구합니다.

     int page = this.page;

     if (page < 1) {

      page = 1;

     }

     int pageCount = getPageCount();

     if (page > pageCount) {

      page = pageCount;

     }

     return page;

    }

    

    public int getPageCount() {  // 총 몇페이지 까지 있는지 구합니다.

     return (totalItemCount - 1) / itemPerPage + 1;

    }

    

    public int getPageBegin() {     //페이지 시작 위치를 구합니다.

     return ((getCurrentPage() - 1) / nextPage) * nextPage + 1;

    }

    

    public int getPageEnd() {      //페이지 끝 위치를 구합니다.

     int pageCount = getPageCount();

     int num = getPageBegin() + nextPage - 1;

     return Math.min(pageCount, num);

    }

    

    public int getTotalItemCount() {

     return totalItemCount;

    }

    

    public void setTotalItemCount(int totalItemCount) {

     this.totalItemCount = totalItemCount;

    }

    

    public int getItemPerPage() {

     return itemPerPage;

    }

    

    public void setItemPerPage(int itemPerPage) {

     this.itemPerPage = itemPerPage;

    }

    

    public int getNextPage() {

     return nextPage;

    }

    

    public void setNextPage(int nextPage) {

     this.nextPage = nextPage;

    }

    

    public int getPage() {

     return page;

    }

    

    public void setPage(int page) {

     this.page = page;

    }

    

    public int getCurrentItem() {         //현재 몇번째 아이템인지 구합니다 (mysql의 limit에서 사용)

     return (page - 1) * itemPerPage ;

    }

    

    public int getJumpNextPage() {    //다음으로 점프하는 페이지를 구합니다.

     return Math.min(getPageCount(), page + nextPage);

    }

    

    public int getJumpPrevPage() {    //이전으로 점프하는 페이지를 구합니다.

     return Math.max(1, page - nextPage);

    }

    public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
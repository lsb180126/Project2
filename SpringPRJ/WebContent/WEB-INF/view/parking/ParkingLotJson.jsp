<%@ page language="java" contentType="text/json; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="poly.dto.ParkingDTO"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
[
<%
List<ParkingDTO> rList = (List<ParkingDTO>)request.getAttribute("rList");

if (rList ==null){
	rList = new ArrayList<ParkingDTO>();
}

Iterator<ParkingDTO> it = rList.iterator();

//첫번째 반복일때 콤마표기 안하기
int idx = 0;
while(it.hasNext()){
	ParkingDTO rDTO = it.next();
   
   if (idx>0){
      out.print(",");
   }
%>
	{ 
	  "parkingAirportCodeName" : "<%=CmmUtil.nvl(rDTO.getParkingAirportCodeName()) %>", 
	  "parkingIincnt" : "<%=CmmUtil.nvl(rDTO.getParkingIincnt()) %>", 
	  "parkingIoutcnt" : "<%=CmmUtil.nvl(rDTO.getParkingIoutcnt()) %>", 
	  "parkingIstay" : "<%=CmmUtil.nvl(rDTO.getParkingIstay()) %>"
	}

<%
   idx++;
	}
%>
]

<%@ page language="java" contentType="text/json; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="poly.dto.InCheonAirplaneDTO"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
[
<%
List<InCheonAirplaneDTO> rList = (List<InCheonAirplaneDTO>)request.getAttribute("rList");

if (rList ==null){
	rList = new ArrayList<InCheonAirplaneDTO>();
}

Iterator<InCheonAirplaneDTO> it = rList.iterator();

//첫번째 반복일때 콤마표기 안하기
int idx = 0;
while(it.hasNext()){
	InCheonAirplaneDTO rDTO = it.next();
   
   if (idx>0){
      out.print(",");
   }
%>
	{ 
	  "airline" : "<%=CmmUtil.nvl(rDTO.getAirline()) %>", 
	  "flightId" : "<%=CmmUtil.nvl(rDTO.getFlightId()) %>", 
	  "scheduleDateTime" : "<%=CmmUtil.nvl(rDTO.getScheduleDateTime()) %>", 
	  "estimatedDateTime" : "<%=CmmUtil.nvl(rDTO.getEstimatedDateTime()) %>", 
	  "airport" : "<%=CmmUtil.nvl(rDTO.getAirport()) %>",
	  "terminalid" : "<%=CmmUtil.nvl(rDTO.getTerminalid()) %>"
	}

<%
   idx++;
	}
%>
]

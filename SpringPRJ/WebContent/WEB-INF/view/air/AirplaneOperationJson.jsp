<%@ page language="java" contentType="text/json; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="poly.dto.AirplaneDTO"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
[
<%
List<AirplaneDTO> rList = (List<AirplaneDTO>)request.getAttribute("rList");

if (rList ==null){
	rList = new ArrayList<AirplaneDTO>();
}

Iterator<AirplaneDTO> it = rList.iterator();

//첫번째 반복일때 콤마표기 안하기
int idx = 0;
while(it.hasNext()){
	AirplaneDTO rDTO = it.next();
   
   if (idx>0){
      out.print(",");
   }
%>
	{ 
	  "airlineKorean" : "<%=CmmUtil.nvl(rDTO.getAirlineKorean()) %>", 
	  "airFln" : "<%=CmmUtil.nvl(rDTO.getAirFln()) %>", 
	  "boardingKor" : "<%=CmmUtil.nvl(rDTO.getBoardingKor()) %>", 
	  "arrivedKor" : "<%=CmmUtil.nvl(rDTO.getArrivedKor()) %>", 
	  "std" : "<%=CmmUtil.nvl(rDTO.getStd()) %>"
	}

<%
   idx++;
	}
%>
]

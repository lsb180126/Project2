<%@ page language="java" contentType="text/json; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="poly.dto.InCheonParkingDTO"%>
<%@ page import="poly.util.CmmUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
[
<%
List<InCheonParkingDTO> rList = (List<InCheonParkingDTO>)request.getAttribute("rList");

if (rList ==null){
	rList = new ArrayList<InCheonParkingDTO>();
}

Iterator<InCheonParkingDTO> it = rList.iterator();

//첫번째 반복일때 콤마표기 안하기
int idx = 0;
while(it.hasNext()){
	InCheonParkingDTO rDTO = it.next();
   
   if (idx>0){
      out.print(",");
   }
%>
	{ 
	  "floor" : "<%=CmmUtil.nvl(rDTO.getFloor()) %>", 
	  "parking" : "<%=CmmUtil.nvl(rDTO.getParking()) %>", 
	  "parkingarea" : "<%=CmmUtil.nvl(rDTO.getParkingarea()) %>", 
	  "datetm" : "<%=CmmUtil.nvl(rDTO.getDatetm()) %>"
	}

<%
   idx++;
	}
%>
]

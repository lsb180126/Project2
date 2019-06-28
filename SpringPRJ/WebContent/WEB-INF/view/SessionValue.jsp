<%@ page import="poly.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%  
	String id = CmmUtil.nvl((String)session.getAttribute("userId"));
	String password = CmmUtil.nvl((String)session.getAttribute("password"));
	String name = CmmUtil.nvl((String)session.getAttribute("userName"));
	String gender = CmmUtil.nvl((String)session.getAttribute("gender"));
	String email = CmmUtil.nvl((String)session.getAttribute("email"));
	String phone = CmmUtil.nvl((String)session.getAttribute("phone"));
	String userNo = CmmUtil.nvl((String)session.getAttribute("userNo"));
	String boardNo = CmmUtil.nvl((String)session.getAttribute("boardNo"));
	String title = CmmUtil.nvl((String)session.getAttribute("title"));
	String content = CmmUtil.nvl((String)session.getAttribute("content"));
	String chgDt = CmmUtil.nvl((String)session.getAttribute("chgDt"));
	String viewCnt = CmmUtil.nvl((String)session.getAttribute("viewCnt"));
%>

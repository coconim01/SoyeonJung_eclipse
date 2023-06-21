<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	JSONArray jsArr = (JSONArray)request.getAttribute("jsArr");

%>
<%=jsArr.toString()%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%
List<String> list=(List<String>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/heartrails/css/main.css">
<script src="/heartrails/js/main.js"></script>
</head>
<body>
<%if(list !=null && list.size()>0){ %>
<table border="1" id="station">
<%for(String s:list){ %>
<tr><td><%=s %></td></tr>
<%} %>
</table>
<%} %>
<a href="/heartrails/Main">戻る</a>
</body>
</html>
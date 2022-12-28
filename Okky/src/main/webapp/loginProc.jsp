<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	//회원 아이디와 패스워드가 일치하는지 비교
	UserDAO udao = new UserDAO();
	int result = udao.getUser(id,password);
	

	
	if(result==0){
	%>
	<script>
		alert("아이디 또는 비밀번호가 틀립니다.");
		history.go(-1);
	</script>
	<%
	}else{
		//회원정보가 일치하다면
		session.setAttribute("id",id);
		response.sendRedirect("index.jsp");
		//세션의 유지시간 설정
		session.setMaxInactiveInterval(60);
	}
	%>
</body>
</html>
<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/reset.css" />
<link rel="stylesheet" href="./css/header.css"/>
</head>
<body>
<%
	//세션을 이용한 로그인 처리
	String id = (String)session.getAttribute("id");//로그인이 되있다면 null이 아닐 것이다.
	
	UserDAO udao = new UserDAO();
	String nickName = udao.getNickName(id);
	
	if(id == null){//로그아웃 상태
		id = "LOGOUT";
	}

%>
	<div id="headerWrap">
      <header id="header">
        <div class="left-nav">
          <h1><a href="index.jsp">로고</a></h1>
          <nav>
            <h2 class="hidden">주요이용메뉴</h2>
            <ul class="gnb">
              <li><a href="QandABoard.jsp">Q&A</a></li>
              <li class="left-line"><a href="#">커뮤니티</a></li>
              <li class="left-line"><a href="#">공지사항</a></li>
            </ul>
          </nav>
        </div>
        <nav class="right-nav">
          <h2 class="hidden">계정관련이용메뉴</h2>
          <ul class="gnb">
            <li class="search">
              <form>
                <label>
                  <button type="submit">
                    <i class="fa fa-light fa-magnifying-glass"></i>
                  </button>
                  <input type="search" placeholder="검색"/>
                </label>
              </form>
            </li>
            <%
            if(id.equals("LOGOUT")){
            %>
            <li class="login admin"><a href="login.jsp">로그인</a></li>
            <li class="join admin"><a href="join.jsp">회원가입</a></li>
            <%
            }else{
            %>
            <li class="login admin"><a href="#"><%=nickName%>님 </a></li>
            <li class="join admin"><a href="logoutProc.jsp">로그아웃</a></li>
            <%
            }
            %>
          </ul>
        </nav>
      </header>
    </div>
</body>
</html>
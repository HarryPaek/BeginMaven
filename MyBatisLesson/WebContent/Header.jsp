<%@page import="spms.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="session" class="spms.vo.Member" type="spms.vo.Member" />
<div style="background-color: #00008b; color: #ffffff; height: 20px; padding: 5px;">
    SPMS(Simple Project Management System)
    
    <span style="float:right;">
        <a style="color:white;" href="<%=request.getContextPath()%>/project/list.do">프로젝트</a>
        <a style="color:white;" href="<%=request.getContextPath()%>/member/list.do">회원</a>
        <% if(member.getName() == null) { %> 
            <a style="color: white;" href="<%=request.getContextPath()%>/auth/login.do">로그인</a>
        <% } else { %>
            <%=member.getName()%>
            (<a style="color: white;" href="<%=request.getContextPath()%>/auth/logout.do">로그아웃</a>)
        <%} %>
    </span>
</div>
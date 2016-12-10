<%@page import="spms.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
    <jsp:include page="/Header.jsp" />
    <h1>회원 목록</h1>
    <p><a href='add.do'>신규 회원</a></p>
    <jsp:useBean id="orderCond" scope="request" class="java.lang.String" type="java.lang.String"/>
    <jsp:useBean id="members" scope="request" class="java.util.ArrayList" type="java.util.ArrayList<Member>" />
    <table border="1">
        <tr>
            <th>
            <% if(orderCond.equalsIgnoreCase("NO_ASC")) {%>
                <a href="list.do?orderCond=NO_DESC">번호↑</a>
            <%} else if(orderCond.equalsIgnoreCase("NO_DESC")) {%>
                <a href="list.do?orderCond=NO_ASC">번호↓</a>
            <%} else {%>
                <a href="list.do?orderCond=NO_ASC">번호</a>
            <%} %>
            </th>
            <th>
            <% if(orderCond.equalsIgnoreCase("NAME_ASC")) {%>
                <a href="list.do?orderCond=NAME_DESC">이름↑</a>
            <%} else if(orderCond.equalsIgnoreCase("NAME_DESC")) {%>
                <a href="list.do?orderCond=NAME_ASC">이름↓</a>
            <%} else {%>
                <a href="list.do?orderCond=NAME_ASC">이름</a>
            <%} %>
            </th>
            <th>이메일</th><th>등록일</th><th>최근수정일</th><th></th>
        </tr>
    <%
    for(Member member : members) {
    %>
        <tr><td><%=member.getNo()%></td>
            <td><a href='update.do?no=<%=member.getNo()%>'><%=member.getName()%></a></td>
            <td><%=member.getEmail()%></td>
            <td><%=member.getCreatedDate()%></td>
            <td><%=member.getModifiedDate()%></td>
            <td><a href='delete.do?no=<%=member.getNo()%>'>[삭제]</a></td>
        </tr>
    <%} %>
    </table>
    <jsp:include page="/Tail.jsp" />
</body>
</html>

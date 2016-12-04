<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>
</head>
<body>
    <jsp:include page="/Header.jsp" />
    <h1>회원 정보</h1>
    <jsp:useBean id="member" scope="request" class="spms.vo.Member" type="spms.vo.Member" />
    <form action="update.do" method="post">
        번호: <input type="text" name="no" value="<%=member.getNo()%>" readonly><br>
        이름: *<input type="text" name="name" value="<%=member.getName()%>"><br>
        이메일: <input type="text" name="email" value="<%=member.getEmail()%>"><br>
        가입일: <%=member.getCreatedDate()%><br>
        <input type="submit" value="저장">
        <a href="delete.do?no=<%=member.getNo()%>"><input type="button" value="삭제"></a>
        <input type="reset" value="취소" onclick="location.href='list.do'">
    </form>
    <br>
    <jsp:include page="/Tail.jsp" />
</body>
</html>

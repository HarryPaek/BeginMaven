<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="1; url=login">
<title>로그인 실패</title>
</head>
<body>
    <jsp:include page="/Header.jsp" />
    <h2>로그인 실패!</h2>
    <p>이메일 또는 암호가 맞지 않습니다!<br>
       잠시 후, 다시 로그인 화면으로 갑니다.</p>
    <br>
    <jsp:include page="/Tail.jsp" />
</body>
</html>
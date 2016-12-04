<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 등록</title>
<style>
  ul { padding: 0; }
  li {
      list-style: none;
      margin-bottom: 10px;
  }
  
  label {
      float: left;
      text-align: right;
      width: 60px;
      padding-right: 10px;
  }
</style>
</head>
<body>
    <jsp:include page="/Header.jsp" />
    <h1>프로젝트 등록</h1>
    <form action="add.do" method="post">
        <ul>
            <li>
                <label for="title">제목</label>
                <input id="title" name="title" type="text" size="50">
            </li>
            <li>
                <label for="content">내용</label>
                <textarea id="content" name="content" rows="5" cols="50"></textarea>
            </li>
            <li>
                <label for="startdate">시작일</label>
                <input id="startdate" name="startDate" placeholder="예)2016-07-25" type="text">
            </li>
            <li>
                <label for="enddate">종료일</label>
                <input id="enddate" name="endDate" placeholder="예)2016-12-09" type="text">
            </li>
            <li>
                <label for="tags">태그</label>
                <input id="tags" name="tags" placeholder="예)태그1 태그2 태그3" type="text" size="50">
            </li>
        </ul>
        <input type="submit" value="추가">
        <input type="reset" value="취소" onclick="location.href='list.do'">
    </form>
    <br>
    <jsp:include page="/Tail.jsp" />
</body>
</html>

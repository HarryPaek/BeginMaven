<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 정보</title>
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
    <h1>프로젝트 정보</h1>
    <jsp:useBean id="project" scope="request" class="spms.vo.Project" type="spms.vo.Project" />
    <form action="update.do" method="post">
        <ul>
            <li>
                <label for="no">번호</label>
                <input id="no" name="no" type="text" value="<%=project.getNo()%>" readonly>
            </li>
            <li>
                <label for="title">제목</label>
                <input id="title" name="title" type="text" size="50" value="<%=project.getTitle()%>">
            </li>
            <li>
                <label for="content">내용</label>
                <textarea id="content" name="content" rows="5" cols="50"><%=project.getContent()%></textarea>
            </li>
            <li>
                <label for="startdate">시작일</label>
                <input id="startdate" name="startDate" value="<%=project.getStartDate()%>" placeholder="예)2016-07-25" type="text">
            </li>
            <li>
                <label for="enddate">종료일</label>
                <input id="enddate" name="endDate" value="<%=project.getEndDate()%>" placeholder="예)2016-12-09" type="text">
            </li>
            <li>
                <label for="state">상태</label>
                <select id="state" name="state">
                    <option value="0" <%=project.getState() == 0 ? "selected" : ""%>>준비</option>
                    <option value="1" <%=project.getState() == 1 ? "selected" : ""%>>진행</option>
                    <option value="2" <%=project.getState() == 2 ? "selected" : ""%>>완료</option>
                    <option value="3" <%=project.getState() == 3 ? "selected" : ""%>>취소</option>
                </select>
            </li>
            <li>
                <label for="tags">태그</label>
                <input id="tags" name="tags" value="<%=project.getTags()%>" placeholder="예)태그1 태그2 태그3" type="text" size="50">
            </li>
        </ul>
        <input type="submit" value="저장">
        <input type="button" value="삭제" onclick="location.href='delete.do?no=<%=project.getNo()%>'">
        <input type="reset" value="취소" onclick="location.href='list.do'">
    </form>
    <br>
    <jsp:include page="/Tail.jsp" />
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL Tests</title>
</head>
<body>
  <h1>JSTL Tests</h1>
  <p>
    <h3>값 출력 방식</h3>
    <c:out value="안녕하세요!"/><br>
    <c:out value="${null}">반갑습니다.</c:out><br>
    <c:out value="안녕하세요!">반갑습니다.</c:out><br>
    <c:out value="${null}"/><br>
  </p>
  <p>
    <h3>값 설정 방식</h3>
    <c:set var="username1" value="홍길동"/>
    <c:set var="username2">임꺽정</c:set>
    ${username1}<br>
    ${username2}<br>
    
    <h3>기본 보관소 - Page</h3>
    ${pageScope.username1}<br>
    ${requestScope.username2}<br>
    
    <h3>보관소 지정 - Scope 속성</h3>
    <c:set var="username3" scope="request">일지매</c:set>
    ${pageScope.username3}<br>
    ${requestScope.username3}<br>
    
    <h3>기본 값 덮어쓰기</h3>
    <% pageContext.setAttribute("username4", "똘이장군"); %>
    기존 값=${username4}<br>
    <c:set var="username4" value="주먹대장" />
    덮어쓴 값=${username4}<br>
  </p>
  <p>
    <h3>객체 프로퍼티 값 변경</h3>
    <%!
    public class MyMember {
    	int no;
    	String name;
   	
    	public int getNo() {
    		return no;
    	}
    	public void setNo(int no) {
    		this.no = no;
    	}
    	
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}
    }
    %>
    <%
    MyMember member = new MyMember();
    member.setNo(100);
    member.setName("홍길동");
    pageContext.setAttribute("member", member);
    %>
    원래 값=${member.name}<br>
    <c:set target="${member}" property="name" value="임꺽정" />
    바꾼 값=${member.name}<br>
  </p>
  
  <p>
    <h3>IF 태그</h3>
    <c:if test="${10 > 20}" var="result1">10은 20보다 크다.<br></c:if>
    ${result1}<br>
    <c:if test="${10 < 20}" var="result2">20은 10보다 크다.<br></c:if>
    ${result2}<br>
  </p>
  
  <p>
    <h3>CHOOSE 태그</h3>
    <c:set var="userid" value="admin"/>
    <c:choose>
      <c:when test="${userid == 'hong'}">
        홍길동님 반갑습니다.
      </c:when>
      <c:when test="${userid == 'leem'}">
        임꺽정님 반갑습니다.
      </c:when>
      <c:when test="${userid == 'admin'}">
        관리지 전용 페이지입니다.
      </c:when>
      <c:otherwise>
        등록되지 않은 사용자입니다.
      </c:otherwise>
    </c:choose>
  </p>
  
  <p>
    <h3>forEach 태그</h3>
    <% pageContext.setAttribute("nameList", new String[] {"홍길동", "임꺽정", "일지매", "주먹대장", "똘이장군", "백종근"}); %>
    <ul>
      <c:forEach var="name" items="${nameList}">
        <li>${name}</li>
      </c:forEach>
    </ul>
    <ul>
      <c:forEach var="name" items="${nameList}" begin="2" end="3">
        <li>${name}</li>
      </c:forEach>
    </ul>
    <% pageContext.setAttribute("nameList2", "홍길동, 임꺽정, 일지매, 주먹대장, 똘이장군, 백종근"); %>
    <ul>
      <c:forEach var="name" items="${nameList2}">
        <li>${name}</li>
      </c:forEach>
    </ul>
    <ul>
      <c:forEach var="no" begin="1" end="6">
        <li><a href="jstl0${no}.jsp">JSTL 예제 ${no}</a></li>
      </c:forEach>
    </ul>
  </p>
  
  <p>
    <h3>forTokens 태그</h3>
    <% pageContext.setAttribute("tokens", "v1=20&v2=30&op=+"); %>
    <ul>
      <c:forTokens var="item" items="${tokens}" delims="&">
        <li>${item}</li>
      </c:forTokens>
    </ul>
  </p>
  
  <p>
    <h3>URL 태그</h3>
    <c:url var="calcUrl" value="http://localhost:18080/JspLessons/calc/Calculator.jsp">
      <c:param name="v1" value="20" />
      <c:param name="v2" value="30" />
      <c:param name="op" value="*" />
    </c:url>
    <a href="${calcUrl}">계산하기</a>
  </p>
  
  <p>
    <h3>import 태그</h3>
    <c:import var="daumMap" url="http://map.daum.net" />
    <textarea rows="100" cols="100%" style="width:100%;">${daumMap}</textarea>
  </p>
  
  <p>
    <h3>fmt 태그</h3>
    <fmt:parseDate var="date1" value="2016-11-25" pattern="yyyy-MM-dd" />
    <fmt:formatDate value="${date1}" pattern="yyyy/MM/dd" />
  </p>
</body>
</html>
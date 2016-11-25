<%@page import="java.util.ResourceBundle"%>
<%@page import="spms.vo.Member"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL Tests</title>
</head>
<body>
<h1>EL Tests</h1>
<p>
    문자열-1: ${"test-1"}<br>
    문자열-2: ${'test-2'}<br>
    숫자: ${3.14 }<br>
    참거짓: ${true}<br>
    null: ${null}<br>
</p>
<p>
    <%
        pageContext.setAttribute("scores", new int[] {90, 80, 70, 100});
    %>
    ${scores[2]}
</p>

<p>
    <% 
        List<String> nameList = new LinkedList<String>();
        nameList.add("홍길동");
        nameList.add("임꺽정");
        nameList.add("일지매");
        pageContext.setAttribute("nameList", nameList);
    %>
    ${nameList[1]}
</p>

<p>
    <% 
        Map<String, String> map = new HashMap<String, String>();
        map.put("s01", "홍길동");
        map.put("s02", "임꺽정");
        map.put("s03", "일지매");
        pageContext.setAttribute("map", map);
    %>
    ${map.s02}
</p>

<p>
    <% 
        Member member = new Member()
                       .setNo(100)
                       .setName("홍길동")
                       .setEmail("hong@example.com");
        pageContext.setAttribute("member", member);
    %>
    ${member.email}
</p>

<p>
    <% 
        pageContext.setAttribute("myRB", ResourceBundle.getBundle("MyResourceBundle"));
    %>
    ${myRB.OK}
</p>

<p>
    <% 
        pageContext.setAttribute("title", "EL 연산자!");
    %>
    \${empty title} = ${empty title}<br>
    \${empty title2} = ${empty title2}<br>
    \${10 > 20 ? "크다" : "작다"} = ${10 > 20 ? "크다" : "작다"}
</p>

</body>
</html>
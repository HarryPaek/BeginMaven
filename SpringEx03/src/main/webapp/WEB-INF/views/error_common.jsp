<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
    <!-- Main Content  -->
    <section class="content" style="min-height: 946px;">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">${exception.getMessage()}</h3>
                    </div>
                    <div class="box-body">
                        <ul>
                          <c:forEach items="${exception.getStackTrace()}" var="stack">
                            <li>${stack.toString()}</li>
                          </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
<%@include file="include/footer.jsp" %>
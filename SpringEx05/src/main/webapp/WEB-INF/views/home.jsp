<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
    <!-- Main Content  -->
    <section class="content">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Example05 HOME PAGE</h3>
                    </div>
                    <div class="box-body">
                        <h4>Service time is ${serverTime}</h4>
                    </div>
                </div>
            </div>
        </div>
    </section>
<%@include file="include/footer.jsp" %>

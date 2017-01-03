<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
    <!-- Main Content  -->
    <section class="content" style="min-height: 946px;">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">SEARCH RESISTER BOARD</h3>
                    </div>
                    
                    <form role="form" method="post">
                        <input type="hidden" name="perPageCount" value="${criteria.perPageCount}">

                        <div class="box-body">
                            <div class="form-group">
                                <label for="exampleInputTitle">Title</label>
                                <input type="text" name="title" class="form-control" placeholder="Enter Title">
                            </div>
                            <div class="form-group">
                                <label for="exampleTextAreaContent">Content</label>
                                <textarea class="form-control" name="content" rows="3" placeholder="Enter ....."></textarea>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputWriter">Writer</label>
                                <input type="text" name="writer" class="form-control" placeholder="Enter Writer">
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
<%@include file="../include/footer.jsp" %>

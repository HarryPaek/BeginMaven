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
                        <h3 class="box-title">READ PAGE BOARD</h3>
                    </div>
                    
                    <form role="form" method="post" action="modifyPage">
                        <input type="hidden" name="bno" value="${boardVO.bno}">
                        <input type="hidden" name="page" value="${criteria.page}">
                        <input type="hidden" name="perPageCount" value="${criteria.perPageCount}">
                    </form>
                    
                    <div class="box-body">
                        <div class="form-group">
                            <label for="exampleInputTitle">Title</label>
                            <input type="text" name="title" class="form-control" placeholder="Enter Title" value="${boardVO.title}" readonly="readonly">
                        </div>
                        <div class="form-group">
                            <label for="exampleTextAreaContent">Content</label>
                            <textarea class="form-control" name="content" rows="3" placeholder="Enter ....." readonly="readonly">${boardVO.content}</textarea>
                        </div>
                        <div class="form-group">
                             <label for="exampleInputWriter">Writer</label>
                             <input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${boardVO.writer}" readonly="readonly">
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="submit" class="btn btn-warning">Modify</button>
                        <button type="submit" class="btn btn-danger">DELETE</button>                                            
                        <button type="submit" class="btn btn-primary">List</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        $(document).ready(function() {
        	var formObj = $('form[role="form"]');
        	console.log(formObj);
        	
        	$('.btn-warning').on('click', function() {
        		formObj.attr('action', '/board/modifyPage');
        		formObj.attr('method', 'get');
        		formObj.submit();
        	});
        	
        	$('.btn-danger').on('click', function() {
        		formObj.attr('action', '/board/removePage');
        		formObj.submit();
        	});

        	$('.btn-primary').on('click', function() {
        		self.location = '/board/listPage?page=${criteria.page}&perPageCount=${criteria.perPageCount}';
        	});
        });
    </script>
<%@include file="../include/footer.jsp" %>

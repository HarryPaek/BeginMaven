<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
    <!-- Main Content  -->
    <section class="content">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Board</h3>
                    </div>
                    <div class="box-body">
                        <form role="form" method="get" action="list">
                            <input type="hidden" name="page" value="1">
                            <input type="hidden" name="perPageCount" value="${pageMaker.criteria.perPageCount}">
                            
                            <select name="searchType">
                                <option value="n" <c:out value="${pageMaker.criteria.searchType == null ? 'selected' : ''}" />>---</option>
                                <option value="t" <c:out value="${pageMaker.criteria.searchType eq 't' ? 'selected' : ''}" />>Title</option>
                                <option value="c" <c:out value="${pageMaker.criteria.searchType eq 'c' ? 'selected' : ''}" />>Content</option>
                                <option value="w" <c:out value="${pageMaker.criteria.searchType eq 'w' ? 'selected' : ''}" />>Writer</option>
                                <option value="tc" <c:out value="${pageMaker.criteria.searchType eq 'tc' ? 'selected' : ''}" />>Title OR Content</option>
                                <option value="cw" <c:out value="${pageMaker.criteria.searchType eq 'cw' ? 'selected' : ''}" />>Content OR Writer</option>
                                <option value="tcw" <c:out value="${pageMaker.criteria.searchType eq 'tcw' ? 'selected' : ''}" />>Title OR Content OR Writer</option>
                            </select>
                            <input type="text" name="keyword" id="keywordInput" value="${pageMaker.criteria.keyword}">
                            <button id="searchBtn">Search</button>
                            <button id="newBtn">New Board</button>
                        </form>
                    </div>
                </div>
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">LIST PAGE</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tr><th style="width: 10px">BNO</th><th>TITLE</th><th>WRITER</th><th>REGDATE</th><th style="width: 40px">VIEWCNT</th></tr>
                            <c:forEach items="${list}" var="boardVO">
                                <tr>
                                    <td>${boardVO.bno}</td>
                                    <td><a href="read${pageMaker.makeQuery(pageMaker.criteria.page)}&bno=${boardVO.bno}">${boardVO.title} <strong>[ ${boardVO.replycnt} ]</strong></a></td>
                                    <td>${boardVO.writer}</td>
                                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
                                    <td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="box-footer">
                        <div class="text-center">
                            <ul class="pagination">
                                <c:if test="${pageMaker.prev}">
                                    <li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
                                </c:if>
                                
                                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                    <li <c:out value="${pageMaker.criteria.page == idx ? 'class = active' : '' }"/>>
                                        <a href="list${pageMaker.makeQuery(idx)}" >${idx}</a>
                                    </li>
                                </c:forEach>
                                
                                <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                    <li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <script>
        var result = '${message}';
        
        // alert('result = [' + result + ']');
        
        if(result == 'success' || result == 'SUCCESS') {
        	alert('처리가 완료되었습니다.');
        }
    </script>
    <script>
        $(document).ready(function() {
        	var formObj = $('form[role="form"]');
        	console.log(formObj);
        	
        	$('#searchBtn').on('click', function(evt) {
        		evt.preventDefault();
        		formObj.submit();
        	});
        	
        	$('#newBtn').on('click', function(evt) {
        		evt.preventDefault();
        		self.location = 'register?perPageCount=${pageMaker.criteria.perPageCount}';
        	});
        });
    </script>
<%@include file="../include/footer.jsp" %>

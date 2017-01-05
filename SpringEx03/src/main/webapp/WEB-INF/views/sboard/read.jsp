<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
    <!-- Main Content  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.6/handlebars.js"></script>
    <section class="content">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">SEARCH READ PAGE BOARD</h3>
                    </div>
                    
                    <form role="form" method="post" action="modify">
                        <input type="hidden" name="bno" value="${boardVO.bno}">
                        <input type="hidden" name="page" value="${criteria.page}">
                        <input type="hidden" name="perPageCount" value="${criteria.perPageCount}">
                        <input type="hidden" name="searchType" value="${criteria.searchType}">
                        <input type="hidden" name="keyword" value="${criteria.keyword}">
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
                        <button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
                        <button type="submit" class="btn btn-danger" id="removeBtn">DELETE</button>                                            
                        <button type="submit" class="btn btn-primary" id="goListBtn">List</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-success">
                    <div class="box-header with-border">
                        <h3 class="box-title">ADD NEW REPLY</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                             <label for="newReplyWriter">Writer</label>
                             <input class="form-control" type="text" name="replyWriter" id="newReplyWriter" placeholder="User Id">
                        </div>
                        <div class="form-group">
                            <label for="newReplyText">Reply Text</label>
                            <textarea class="form-control" name="replyText" id="newReplyText" rows="3" placeholder="Enter Reply Text ....."></textarea>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
                    </div>
                </div>
            
                <!-- The time line -->
                <ul class="timeline">
                    <!-- time line time label -->
                    <li class="time-label" id="repliesDiv"><span class="bg-green">Replies List</span></li>
                </ul>
            
                <div class="text-center">
                    <ul id="pagination" class="pagination pagination-sm no-margin">
                    </ul>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div id="modifyModal" class="modal modal-primary fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal Content -->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"></h4>
                    </div>
                    <div class="modal-body" data-rno>
                        <textarea class="form-control" id="replyText" rows="3" placeholder="Enter Reply Text ....."></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" id="replyModifyBtn" data-dismiss="modal">Modify</button>
                        <button type="button" class="btn btn-danger" id="replyDeleteBtn" data-dismiss="modal">DELETE</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        $(document).ready(function() {
        	var formObj = $('form[role="form"]');
        	console.log(formObj);
        	
        	$('#modifyBtn').on('click', function() {
        		formObj.attr('action', 'modify');
        		formObj.attr('method', 'get');
        		formObj.submit();
        	});
        	
        	$('#removeBtn').on('click', function() {
        		formObj.attr('action', 'remove');
        		formObj.submit();
        	});

        	$('#goListBtn').on('click', function() {
        		self.location = 'list?page=${criteria.page}&perPageCount=${criteria.perPageCount}&searchType=${criteria.searchType}&keyword=${criteria.keyword}';
        	});
        });
    </script>
    <script>
        $(document).ready(function() {
        	var bno = ${boardVO.bno};
        	var replyPage = 1;
        	
        	function getPage(pageInfo) {
                $.getJSON(pageInfo, function(data) {
                	printData(data.list, $('#repliesDiv'), $('#replyTemplate'));
                	printPaging(data.pageMaker, $('.pagination'));
                });
            }
        	
            var printPaging = function(pageMaker, target) {
            	var str = '';
            	
            	if(pageMaker.prev) {
            		str += '<li><a href="' + (pageMaker.startPage - 1) + '"> << </a></li>';
            	}
            	
            	for(var i=pageMaker.startPage, len=pageMaker.endPage; i <= len; i++) {
            		var strClass = pageMaker.criteria.page == i ? 'class="active"' : '';
            		str += '<li ' + strClass + '><a href="' + i + '">' + i + '</a></li>';
            	}
            	
            	if(pageMaker.next) {
            		str += '<li><a href="' + (pageMaker.endPage + 1) + '"> >> </a></li>';
            	}
            	
            	target.html(str);
            };
            
            $('#repliesDiv').on('click', function() {
        		if($('.timeline li').size() > 1) {
        			return;
        		}
        		
        		getPage('/replies/' + bno + '/1');
        	});
            
            $('.pagination').on('click', 'li a', function(event) {
            	event.preventDefault();
            	replyPage = $(this).attr('href');
            	
            	getPage('/replies/' + bno + '/' + replyPage);
            });
            
            $('#replyAddBtn').on('click', function(event) {
            	event.preventDefault();

            	var replyerObj = $('#newReplyWriter');
            	var replyTextObj = $('#newReplyText');
            	var replyer = replyerObj.val();
            	var replyText = replyTextObj.val();
            	
            	$.ajax({
            		type: 'post',
            		url: '/replies',
            		headers : {
            			'Content-Type': 'application/json',
            			'X-HTTP-Method-Override': 'POST'
            		},
            		dataType: 'text',
            		data: JSON.stringify({bno: bno, replyer: replyer, replyText: replyText}),
            		success: function(result) {
            			if(result == 'SUCCESS') {
            				alert('등록 되었습니다.');
            				replyPage = 1;
            				getPage('/replies/' + bno + '/' + replyPage);
            				replyerObj.val('');
            				replyTextObj.val('');
            			}
            		}
            	});
            });
            
            $('#replyModifyBtn').on('click', function() {
            	var rno = $('.modal-title').html();
            	var replyText = $('#replyText').val();
            	
            	$.ajax({
            		type: 'put',
            		url: '/replies/' + rno,
            		headers : {
            			'Content-Type': 'application/json',
            			'X-HTTP-Method-Override': 'PUT'
            		},
            		dataType: 'text',
            		data: JSON.stringify({replyText: replyText}),
            		success: function(result) {
            			console.log('result: ' + result);
            			if(result == 'SUCCESS') {
            				alert('수정 되었습니다.');
            				$('#modDiv').hide('slow');
            				getPage('/replies/' + bno + '/' + replyPage);
            			}
            		}
            	});
            });

            $('#replyDeleteBtn').on('click', function() {
            	var rno = $('.modal-title').html();
            	
            	$.ajax({
            		type: 'delete',
            		url: '/replies/' + rno,
            		headers : {
            			'Content-Type': 'application/json',
            			'X-HTTP-Method-Override': 'DELETE'
            		},
            		dataType: 'text',
            		success: function(result) {
            			console.log('result: ' + result);
            			if(result == 'SUCCESS') {
            				alert('삭제 되었습니다.');
            				getPage('/replies/' + bno + '/' + replyPage);
            			}
            		}
            	});
            });

            $('.timeline').on('click', '.replyLi', function(event) {
            	var reply = $(this);
            	
            	$('.modal-title').html(reply.attr('data-rno'));
            	$('#replyText').val(reply.find('.timeline-body').text());
            });

        });
    </script>
    
    <script id='replyTemplate' type="text/x-handlebars-template">
        {{#each .}}
            <li class="replyLi" data-rno={{rno}}>
                <i class="fa fa-comments bg-blue"></i>
                <div class="timeline-item">
                    <span class="time">
                        <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
                    </span>
                    <h3 class="timeline-header"><strong>{{rno}}</strong> - {{replyer}}</h3>
                    <div class="timeline-body">{{replyText}}</div>
                    <div class="timeline-footer">
                        <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
                    </div>
                </div>
            </li>
        {{/each}}
    </script>
    <script>
        Handlebars.registerHelper('prettifyDate', function(timeValue) {
        	var dateObj = new Date(timeValue);
        	var year = dateObj.getFullYear();
        	var month = dateObj.getMonth() + 1;
        	var date = dateObj.getDate();
        	
        	return year + '/' + month + '/' + date;
        });
        
        var printData = function(replyList, target, templateObject) {
        	var template = Handlebars.compile(templateObject.html());
        	var html = template(replyList);
        	
        	$('.replyLi').remove();
        	target.after(html);
        }
    </script>
<%@include file="../include/footer.jsp" %>

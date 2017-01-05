<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
    <!-- Main Content  -->
    <section class="content">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">AJAX TEST PAGE</h3>
                    </div>

                    <div class="box-body">
                        <div class="form-group">
                            <label for="newReplyWriter">Replyer</label>
                            <input type="text" name="replyer" id="newReplyWriter" class="form-control" placeholder="Enter Replyer">
                        </div>
                        <div class="form-group">
                            <label for="newReplyText">Reply Text</label>
                            <textarea class="form-control" name="replyText" id="newReplyText" rows="3" placeholder="Enter Reply Text ....."></textarea>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button id="replyAddBtn" class="btn btn-primary">Add Reply</button>
                    </div>
                </div>
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Reply List PAGE</h3>
                    </div>
                    <div class="box-body">
                        <ul id="replies">
                        </ul>
                    </div>
                    <div class="box-footer">
                        <ul class="pagination">
                        </ul>
                    </div>
                </div>

                <!-- Modal Pop-up Box -->
                <div id="modDiv" class="box box-primary" style="display: none;">
                    <div class="modal-title box-header with-border">
                        <h3 class="box-title">Reply Working Page</h3>
                    </div>

                    <div class="box-body">
                        <div class="form-group">
                            <label for="replyText">Reply Text</label>
                            <textarea class="form-control" id="replyText" rows="3" placeholder="Enter Reply Text ....."></textarea>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="button" id="replyModifyBtn" class="btn btn-warning">Modify</button>
                        <button type="button" id="replyDeleteBtn" class="btn btn-danger">Delete</button>
                        <button type="button" id="replyCloseBtn" class="btn btn-primary">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </section>

    <script>
        var bno =  5;
        var replyPage = 1;
        
        $(document).ready(function() {
        	// getAllList(bno);
        	getPageList(bno, replyPage);
        });
        
        $('#replyAddBtn').on('click', function() {
        	var replyer = $('#newReplyWriter').val();
        	var replyText = $('#newReplyText').val();
        	
        	$.ajax({
        		type: 'post',
        		url: '/replies',
        		headers : {
        			'Content-Type': 'application/json',
        			'X-HTTP-Method-Override': 'POST'
        		},
        		dataType: 'text',
        		data: JSON.stringify({
        			bno: bno,
        			replyer: replyer,
        			replyText: replyText
        		}),
        		success: function(result) {
        			if(result == 'SUCCESS') {
        				alert('등록 되었습니다.');
        				replyPage = 1;
        				getPageList(bno, replyPage);
        			}
        		}
        	});
        });
        
        $('#replies').on('click', '.replyLi button', function() {
        	var reply = $(this).parent();
        	var rno = reply.attr('data-rno');
        	var replyText = reply.text();
        	
        	// alert('Selected Reply = [' + rno + ': ' + replyText + ']');
        	$('.modal-title h3').html(rno);
        	$('#replyText').val(replyText);
        	$('#modDiv').show('slow');
        });
        
        function getAllList(no) {
            $.getJSON('/replies/' + no, function(data) {
            	var str='';

            	console.log(data.length);
            	
            	$(data).each(function() {
            		str += '<li data-rno="' + this.rno + '" class="replyLi">' + this.rno + ': ' + this.replyText + ' <button class="btn btn-warning">Modify</button></li>';
            	});
            	
            	$('#replies').html(str);
            });
        }
        
        function getPageList(no, page) {
            $.getJSON('/replies/' + no + '/' + page, function(data) {
            	var str='';

            	console.log(data.list.length);
            	
            	$(data.list).each(function() {
            		str += '<li data-rno="' + this.rno + '" class="replyLi">' + this.rno + ': ' + this.replyText + ' <button class="btn btn-warning">Modify</button></li>';
            	});
            	
            	$('#replies').html(str);
            	
            	printPaging(data.pageMaker);
            });
        }
        
        function printPaging(pageMaker) {
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
        	
        	$('.pagination').html(str);
        }
        
        $('.pagination').on('click', 'li a', function(event) {
        	event.preventDefault();
        	replyPage = $(this).attr('href');
        	
        	getPageList(bno, replyPage);
        });

        
        $('#replyDeleteBtn').on('click', function() {
        	var rno = $('.modal-title h3').html();
        	var replyText = $('#replyText').val();
        	
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
        				$('#modDiv').hide('slow');
        				getPageList(bno, replyPage);
        			}
        		}
        	});
        });

        $('#replyModifyBtn').on('click', function() {
        	var rno = $('.modal-title h3').html();
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
        				getPageList(bno, replyPage);
        			}
        		}
        	});
        });
        
        $('#replyCloseBtn').on('click', function() {
        	$('#modDiv').hide('slow');
        });

    </script>
    <style>
        #modDiv {
            width: 400px;
            height: 250px;
            background-color: LAVENDER;
            position: absolute;
            top: 540px;
            left: 50%;
            margin-top: -120px;
            margin-left: -200px;
            padding: 10px;
            z-index: 1000;
        }
    </style>
    

<%@include file="include/footer.jsp" %>

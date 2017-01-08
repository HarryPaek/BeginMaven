<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
    <!-- Main Content  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.6/handlebars.js"></script>
    <script type="text/javascript" src="/resources/js/upload.js"></script>
    <style>
        .fileDrop {
              width: 90%;
              height: 100px;
              border: 1px dotted blue;
              background-color: lightblue;
              margin: auto;
        }
    </style>
    <section class="content">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">SEARCH RESISTER BOARD</h3>
                    </div>
                    
                    <form id="registerForm" role="form" method="post">
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
                            <div class="form-group">
                                <label for="fileDrop">File DROP Here</label>
                                <div class="fileDrop"></div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <div>
                                <hr>
                            </div>
                            
                            <ul class="mailbox-attachments clearfix uploadedList">
                            </ul>
                            
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <script id='attachTemplate' type="text/x-handlebars-template">
        <li>
            <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment" /></span>
            <div class="mailbox-attachment-info">
                <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
                <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
            </div>
        </li>
    </script>
    <script>
        $(document).ready(function() {
            $('.fileDrop').on('dragenter dragover', function(event) {
            	event.preventDefault();
            });
            
            $('.fileDrop').on('drop', function(event) {
            	event.preventDefault();

            	var attachTemplate = Handlebars.compile($('#attachTemplate').html());
            	var files = event.originalEvent.dataTransfer.files;
            	var file = files[0];
            	var formData = new FormData();
            	formData.append("file", file);
            	
            	$.ajax({
            		url: '/files/uploadAjax',
            		data: formData,
            		dataType: 'text',
            		processData: false,
            		contentType: false,
            		type: 'POST',
            		success: function(data) {
            			alert(data);
            			
            			var fileInfo = getFileInfo(data);
            			var html = attachTemplate(fileInfo);
            			
            			$('.uploadedList').append(html);
            		}
            	});
            });
            
            $('#registerForm').submit(function(event) {
            	event.preventDefault();
            	
            	var that = $(this);
            	var str = '';
            	
            	$('.uploadedList .delbtn').each(function(index){
            		str += '<input type="hidden" name="files[' + index + ']" value="' + $(this).attr('href') + '">';
            	});
            	
            	that.append(str);
            	
            	that.get(0).submit();
            });
            
            $(".uploadedList").on("click", ".delbtn", function(event) {
            	event.preventDefault();
            	
            	var that = $(this);
            	 
            	$.ajax({
            	   url:"/files/deleteFile",
            	   type:"post",
            	   data: {fileName:$(this).attr("href")},
            	   dataType:"text",
            	   success:function(result){
            		   if(result == 'deleted'){
            			   that.closest("li").remove();
            		   }
            	   }
               });
            });
        });
    </script>
<%@include file="../include/footer.jsp" %>

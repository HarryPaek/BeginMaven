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
        .popup {
            position: absolute;
        }
        .back {
            background-color: gray;
            opacity:0.5;
            width: 100%;
            height: 300%;
            overflow:hidden;
            z-index:1101;
        }
        .front {
            z-index:1110;
            opacity:1;
            boarder:1px;
            margin: auto;
        }
        .show {
            position:relative;
            max-width: 1200px;
            max-height: 800px;
            overflow: auto;
        } 
    </style>
    
    <div class="popup back" style="display:none;">
    </div>
    <div id="popup_front" class="popup front" style="display:none;">
        <img id="popup_img">
    </div>
    
    <section class="content">
        <div class="row">
            <!-- left column -->
            <div class="col-md-12">
                <!-- general form elements -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">SEARCH BOARD MODIFY PAGE</h3>
                    </div>
                    
                    <form role="form" method="post" action="modify">
                        <input type="hidden" name="bno" value="${boardVO.bno}">
                        <input type="hidden" name="page" value="${criteria.page}">
                        <input type="hidden" name="perPageCount" value="${criteria.perPageCount}">
                        <input type="hidden" name="searchType" value="${criteria.searchType}">
                        <input type="hidden" name="keyword" value="${criteria.keyword}">
                    
                        <div class="box-body">
                            <div class="form-group">
                                <label for="exampleInputTitle">Title</label>
                                <input type="text" name="title" class="form-control" placeholder="Enter Title" value="${boardVO.title}">
                            </div>
                            <div class="form-group">
                                <label for="exampleTextAreaContent">Content</label>
                                <textarea class="form-control" name="content" rows="3" placeholder="Enter .....">${boardVO.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputWriter">Writer</label>
                                <input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${boardVO.writer}" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label for="fileDrop">File DROP Here</label>
                                <div class="fileDrop"></div>
                            </div>
                        </div>
                    </form>
                    <div class="box-footer">
                        <div>
                            <hr>
                        </div>
                            
                        <ul class="mailbox-attachments clearfix uploadedList">
                        </ul>

                        <button type="submit" class="btn btn-primary">SAVE</button>
                        <button type="submit" class="btn btn-warning">Cancel</button>
                    </div>
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
        	var bno = ${boardVO.bno};
        	var attachTemplate = Handlebars.compile($('#attachTemplate').html());

            $.getJSON('/sboard/getAttach/' + bno, function(list) {
            	$(list).each(function(){
            		var fileInfo = getFileInfo(this);
            		var html = attachTemplate(fileInfo);
            		
            		$('.uploadedList').append(html);
            	});
            });

            $('.fileDrop').on('dragenter dragover', function(event) {
            	event.preventDefault();
            });
            
            $('.fileDrop').on('drop', function(event) {
            	event.preventDefault();

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
            
            $('.uploadedList').on('click', '.mailbox-attachment-info .mailbox-attachment-name', function(event) {
            	var fileLink = $(this).attr('href');
            	
            	if(ckeckImageType(fileLink)) {
            		event.preventDefault();
            		
            		var imgTag = $('#popup_img');
            		imgTag.attr('src', fileLink);
            		
            		console.log(imgTag.attr('src'));
            		
            		$('.popup').show('slow');
            		imgTag.addClass('show');
            	}
            });

            $('#popup_img').on('click', function(event) {
            	$(this).removeClass('show');
        		$('.popup').hide('slow');
            });

        });
    </script>

    <script>
        $(document).ready(function() {
        	var formObj = $('form[role="form"]');
        	console.log(formObj);

        	$('.btn-warning').on('click', function() {
        		self.location = 'list?page=${criteria.page}&perPageCount=${criteria.perPageCount}&searchType=${criteria.searchType}&keyword=${criteria.keyword}';
        	});
        	
        	$('.btn-primary').on('click', function() {
        		formObj.submit();
        	});
        	
        	formObj.submit(function(event) {
            	event.preventDefault();
            	
            	var that = $(this);
            	var str = '';
            	
            	$('.uploadedList .delbtn').each(function(index){
            		str += '<input type="hidden" name="files[' + index + ']" value="' + $(this).attr('href') + '">';
            	});
            	
            	that.append(str);
            	
            	that.get(0).submit();
            });
        });
    </script>
<%@include file="../include/footer.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 업로드 With AJAX</title>
    <style>
        .fileDrop {
            witdh: 100%;
            height: 200px;
            border: 1px dotted blue;
        }
        
        small {
            margin-left: 3px;
            font-weight: bold;
            color: gray;
        }
    </style>
</head>
<body>
    <h3>AJAX File Upload</h3>
    <div class="fileDrop"></div>
    
    <div class="uploadedList"></div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        $('.fileDrop').on('dragenter dragover', function(event) {
        	event.preventDefault();
        });
        
        $('.fileDrop').on('drop', function(event) {
        	event.preventDefault();
        	
        	var files = event.originalEvent.dataTransfer.files;
        	// console.log(files);
        	
        	var file = files[0];
        	// console.log(file);
        	
        	var formData = new FormData();
        	formData.append("file", file);
        	
        	$.ajax({
        		url: 'uploadAjax',
        		data: formData,
        		dataType: 'text',
        		processData: false,
        		contentType: false,
        		type: 'POST',
        		success: function(data) {
        			alert(data);
        			var str = '';
        			
        			if(ckeckImageType(data)) {
        				str = '<div><a href="displayFile?fileName=' + getImageLink(data) + '" target="_blank"><img src="displayFile?fileName=' + data + '"/></a><small data-src=' + data + '>X</small></div>';
        			}
        			else {
        				str = '<div><a href="displayFile?fileName=' + data + '">' + getOriginalFileName(data) + '</a><small data-src=' + data + '>X</small></div>';
        			}
        			
        			$('.uploadedList').append(str);
        		}
        	});
        });
        
        $('.uploadedList').on('click', 'small', function(event) {
        	var self = $(this);
        	
        	$.ajax({
        		url: 'deleteFile',
        		type: 'POST',
        		data: {fileName:self.attr('data-src')},
        		dataType: 'text',
        		success: function(result) {
        			if(result == 'deleted') {
        				alert('deleted');
        				self.parent('div').remove();
        			}
        		}
        	});
        });
        
        function ckeckImageType(fileName) {
        	var pattern = /jpg|gif|png|jpeg/i;
        	
        	return fileName.match(pattern);
        }
        
        function getOriginalFileName(fileName) {
        	if(ckeckImageType(fileName))
        		return;
        	
        	var idx = fileName.indexOf('_') + 1;
        	
        	return fileName.substr(idx);
        }
        
        function getImageLink(fileName) {
        	if(!ckeckImageType(fileName))
        		return;
        	
        	var front = fileName.substr(0, 12);
        	var end = fileName.substr(14);
        	
        	return front + end;
        }
    </script>
</body>
</html>

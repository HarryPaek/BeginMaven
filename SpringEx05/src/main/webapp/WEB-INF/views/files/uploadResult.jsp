<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 업로드 결과</title>
</head>
<body>
    <div></div>

    <script>
        var result = '${savedName}';
        parent.addFilePath(result);
    </script>
</body>
</html>

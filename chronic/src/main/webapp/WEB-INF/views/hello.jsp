<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
hello , ${name};<br/>
<form action="hello.do" method="post" >
phone:<input type="text" name="phone"/><br/>
password:<input type="password" name="password"/>
<br/>
<button>submit</button>
</form>
</body>
</html>
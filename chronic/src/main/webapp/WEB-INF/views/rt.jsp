<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
<h1>获取服务端更新数据</h1>
<div id="result"></div>

<script>
if(typeof(EventSource)!=="undefined")
  {
  var source=new EventSource("${ctx}/ecg/rt/data");
  source.onmessage=function(event)
    {
	    console.log(event.data);
    document.getElementById("result").innerHTML+=event.data + "<br>";
    };
  }
else
  {
  document.getElementById("result").innerHTML="抱歉，你的浏览器不支持 server-sent 事件...";
  }
</script>

</body>
</html>

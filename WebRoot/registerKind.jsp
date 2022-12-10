<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'input.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
		.a{
			padding-left: 380px;
		}
		.g{
			padding-left:750px;
			padding-top:200px;
			background-image: url("img/img2.jpg");
			background-size:100% 100%;
		}
		.b{
			background-color: #FFC1C1;
		}
		.b:HOVER{
			background-color: #CD9B9B;
		}
	</style>
      <%
          int who=request.getParameter("who")==null?0:Integer.parseInt(request.getParameter("who"));
      %>
	<script type="text/javascript">
		function a(){
			var n=document.form1.kindName.value;
			if(n==""){
				window.alert("组名不能为空!!!");
			}else{
				form1.submit();
			}
		}
	
	
	</script>

  </head>
  
  <body class="g">
  <fieldset style="width: 400px;">
  	<legend><h2><spring:message code="AddKind"></spring:message></h2></legend>
    <form name="form1" id="form1" action="registerKind.do" method="post">
        <input type="text" style="display: none" name="who" id="who" value="<%= who%>">
    <table>
    <tr><td><spring:message code="EnterKindName"></spring:message>：</td><td><input type="text" name="kindName" /></td></tr>
    <tr><td><spring:message code="Info"></spring:message>:</td><td><textarea id="textarea" name="kindInfo" size="20"></textarea></td></tr>
   	<tr><td></td><td align="left"><input type="button" value="<spring:message code="submitt"></spring:message>" onclick="a()" class="b"/></td></tr>
    	</table>
    </form>
    <a href="findkind.do?who=<%=who%>" class="a"><img src="img/back.jpg" width="20" height="20"></img></a>
    </fieldset>
  </body>
</html>

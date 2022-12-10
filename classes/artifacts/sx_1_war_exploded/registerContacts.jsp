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
		.b:VISITED {
			color: red;
		}
	</style>
<%
    String who="0";
    if(request.getAttribute("who")!=null) who= request.getAttribute("who").toString();
    if(request.getParameter("who")!=null) who= request.getParameter("who");
%>
	<script type="text/javascript">
		function a(){
				form1.submit();
		}
	</script>

  </head>
  
  <body class="g">
  <jsp:useBean id="command" class="Bean.Contacts" scope="request"></jsp:useBean>
  <fieldset style="width: 400px;">
  	<legend><h2><spring:message code="AddContacts" ></spring:message></h2></legend>
  	<% 
  	int id;
  	if(request.getParameter("id")!=null){
    		 id=Integer.parseInt(request.getParameter("id").toString());
    }else{
    		 id=Integer.parseInt(request.getAttribute("id").toString());
    }
    %>
    <form:form name="form1" id="form1" action="registerContacts.do" method="post">
    	<form:input type="hidden" path="kindId" value="<%=id %>"/>
        <form:input type="hidden" path="who" value="<%=who %>"/>
    <table>
    <tr><td><spring:message code="personName"></spring:message>:</td><td><form:input  path="personName" /><form:errors path="personName"></form:errors></td></tr>
    <tr><td><spring:message code="NickName"></spring:message>:</td><td><input type="text" name="personNickName" /></td></tr>
    <tr><td><spring:message code="Sex"></spring:message>:</td><td>
    	<input type="radio" name="personSex" value="<spring:message code="man"></spring:message>" checked><spring:message code="man"></spring:message>
      <input type="radio" name="personSex" value="<spring:message code="woman"></spring:message>" ><spring:message code="woman"></spring:message>
    </td></tr>
    <tr><td><spring:message code="personBirthday"></spring:message>:</td><td><form:input  path="personBirthday" placeholder="XXXX-XX-XX" /><form:errors path="personBirthday"></form:errors></td></tr>
    <tr><td><spring:message code="personTelephone"></spring:message>:</td><td><form:input path="personTelephone" /><form:errors path="personTelephone"></form:errors></td></tr>
    <tr><td><spring:message code="personQQ"></spring:message>:</td><td><form:input  path="personQQ" /><form:errors path="personQQ"></form:errors></td></tr>
    <tr><td><spring:message code="Email"></spring:message>:</td><td><input type="text" name="personEmail" /></td></tr>
    <tr><td><spring:message code="Address"></spring:message>:</td><td><input type="text" name="personAddress" /></td></tr>
    <tr><td>MSN:</td><td><input type="text" name="personMSN" /></td></tr>
    <tr><td><spring:message code="Info"></spring:message>:</td><td><textarea id="textarea" name="personInfo" size="20"></textarea></td></tr>
   	<tr><td></td><td align="left"><input type="button" value="<spring:message code="submitt"></spring:message>" onclick="a()" class="b"/></td></tr>
    	</table>
    </form:form>
    </fieldset>
  </body>
</html>

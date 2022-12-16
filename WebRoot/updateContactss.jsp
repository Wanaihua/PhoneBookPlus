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
    
    <title>My JSP 'update.jsp' starting page</title>
    
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
  </head>
  
  <body class="g">
   <fieldset style="width: 300px;">
  	<legend><h2>联系人信息修改</h2></legend>
    <form name="form1" id="form1" action="updateContactss.do?personId=${sessionScope.list.get(0).personId}" method="post">
		<input style="display: none" name="who" id="who" value=${sessionScope.list.get(0).who}>
    <table>
        <tr><td><spring:message code="personName"></spring:message>: </td><td><input name="personName" id="personName" value=${sessionScope.list.get(0).personName }><form:errors path="personName"></form:errors></td></tr>
        <tr><td><spring:message code="NickName"></spring:message>: </td><td><input  name="personNickName" id="personNickName" value=${sessionScope.list.get(0).personNickName }></td></tr>
        <tr><td><spring:message code="Sex"></spring:message>:</td><td>
    		<input type="radio" name="personSex" value="<spring:message code="man"></spring:message>" id="man"><spring:message code="man"></spring:message>
      		<input type="radio" name="personSex" value="<spring:message code="woman"></spring:message>" id="woman"><spring:message code="woman"></spring:message>
    	</td></tr>
        <tr><td><spring:message code="personBirthday"></spring:message>: </td><td><input  name="personBirthday" id="personBirthday" value=${sessionScope.list.get(0).personBirthday }><form:errors path="personBirthday"></form:errors></td></tr>
        <tr>
    	<td><spring:message code="Kind"></spring:message>:</td>
    		<td>
    		<select name="kindId" id="kindId" >
    			<c:forEach var="kind" items="${listt}" varStatus="s">
    				<option value="${kind.kindId }">${kind.kindName}</option>
    			</c:forEach>
    		</select>
   		</td></tr>
        <tr><td><spring:message code="personTelephone"></spring:message>: </td><td><input  name="personTelephone" id="personTelephone" value=${sessionScope.list.get(0).personTelephone }><form:errors path="personTelephone"></form:errors></td></tr>
        <tr><td>QQ: </td><td><input  name="personQQ" id="personQQ" value=${sessionScope.list.get(0).personQQ }><form:errors path="personQQ"></form:errors></td></tr>
        <tr><td><spring:message code="Email"></spring:message>: </td><td><input  name="personEmail" id="personEmail" value=${sessionScope.list.get(0).personEmail }></td></tr>
        <tr><td><spring:message code="Address"></spring:message>: </td><td><input  name="personAddress" id="personAddress" value=${sessionScope.list.get(0).personAddress }></td></tr>
        <tr><td>MSN: </td><td><input  name="personMSN" id="personMSN" value=${sessionScope.list.get(0).personMSN }></td></tr>
        <tr><td><spring:message code="Info"></spring:message>: </td><td><textarea  name="personInfo" id="personInfo"></textarea></tr>     	
        <tr><td></td><td align="left"><input type="submit" value="<spring:message code="submitt"></spring:message>" class="b"/></td></tr>
    </table>
	</form>
	 </fieldset>
	  <script type="text/javascript">
    			var kindId=document.getElementById("kindId");
    			var opt=document.getElementsByTagName("option");
    			var m=document.getElementById("man");
    			var w=document.getElementById("woman");
    			var a=${sessionScope.list.get(0).kindId };
    			var z="${sessionScope.list.get(0).personSex }";
				for(var i=0;i<opt.length;i++){
					if(opt[i].value==a){
						opt[i].selected=true;
					}
				}
				document.getElementById("personInfo").value="${sessionScope.list.get(0).personInfo }";
				if(z=="男"){
    				m.checked=true;
    			}else{
    				w.checked=true;
    			}
       		</script>
  </body>
</html>

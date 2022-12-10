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
    
    <title>My JSP 'index.jsp' starting page</title>
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
		.b{
			padding-left: 350px;
		}
		.c{
			padding-top: 20px;
		}
		.d{
			width: 600px;
			height: 300px;
		}
		.e{
			float: left;
			width: 250px;
		}
		.g{
			padding-left:550px;
			padding-top:200px;
			background-image: url("img/img2.jpg");
			background-size:100% 100%;
		}
		.but {
  			display: inline-block;
 			border-radius: 4px;
  			background-color: #FFC1C1;
  			border: none;
  			color: #FFFFFF;
  			text-align: center;
  			font-size: 14px;
  			padding: 10px;
 			 width: 100px;
  			transition: all 0.5s;
  			cursor: pointer;
 			margin: 5px;
		}

		.but span {
  			cursor: pointer;
  			display: inline-block;
  			position: relative;
  			transition: 0.5s;
		}

		.but span:after {
 	 		content: '»';
  			position: absolute;
  			opacity: 0;
  			top: 0;
  			right: -10px;
  			transition: 0.5s;
		}
		.but:hover{
			background-color: #CD9B9B;
		}
		.but:hover span {
 	 		padding-right: 12px;
		}

		.but:hover span:after {
  			opacity: 1;
  			right: 0;
		}
		.butlast{
			margin-left:260px;
			background-color: #FFC1C1;
		}
		.butlast:hover{			
			background-color: #CD9B9B;
		}
		.sumbut {
  			display: inline-block;
  			padding: 3px 5px;
  			font-size: 4px;
  			cursor: pointer;
  			text-align: center;   
  			text-decoration: none;
  			outline: none;
 			color: #fff;
 			background-color: #FFC1C1;
  			border: none;
  			border-radius: 5px;
 			box-shadow: 0 4px #999;
		}

		.sumbut:hover {
			background-color: #CD9B9B;
		}

		.sumbut:active {
  			background-color: #CD9B9B;
  			box-shadow: 0 2px #666;
  			transform: translateY(4px);
		}
	</style>
	  <%
		  String who="0";
		  if(request.getAttribute("who")!=null) who= request.getAttribute("who").toString();
		  if(request.getParameter("who")!=null) who= request.getParameter("who");
	  %>
	<script type="text/javascript">
		function c(){
			var d=document.form1.personName.value;
			if(d==""){
				window.alert("查询名不能为空!");
			}else{
				form1.submit();
			}
		}
		function a(){
			window.open("registerContacts1.do","_self");
		}
		function d(){
			window.open("findAll.do?who=<%=who %>","_self");
		}
		function b(){
			window.open("findkind.do?who=<%=who %>","_self");
		}
	</script>
  </head>
  
  <body class="g">
 <!--   <%
      if(request.getAttribute("page")!=null){
     	%><script type="text/javascript">window.alert("用户名不存在!");</script><%
     }

%>   -->
  		 <fieldset style="width: 650px;">
  		 <legend><button onclick="b()" class="but"><span><spring:message code="group"></spring:message></span></button><button onclick="d()" class="but"><span><spring:message code="all"></spring:message></span></button></legend>
  		 <form name="form1" id="form1" class="b" action="findcontactss.do?page=${page }" method="post">
  		 	<input type="text" name="personName" placeholder="<spring:message code="Enterusername"/>"> <button onclick="c()" class="sumbut"><span><spring:message code="select"/></span></button>
  		 </form>
  		 <div class="d">
  		 <table border="1" bordercolor="#E0E0E0" cellspacing="0" cellpadding="0">
  		 <tr align="center" bgcolor="FFC1E0"><td width="250px"><spring:message code="personName"></spring:message></td><td width="250px"><spring:message code="personTelephone"></spring:message></td><td colspan="2" align="center" width="200px"><spring:message code="do"></spring:message></td></tr>
  		<c:forEach var="contacts" items="${page.contactslist}" varStatus="s">
　		<tr align="center"  class="column_${s.count }" height="50px">
		     <td width="250px">${contacts.personName}</td>
		     <td width="250px">${contacts.personTelephone}</td>
		     <td width="100px"><a href="deleteContactss.do?id=${contacts.personId}&amp;p=${page.currentPage}&amp;who=<%=who%>"><spring:message code="delete"></spring:message></a></td>
		     <td width="100px"><a href="queryContactsByIdd.do?id=${contacts.personId}"><spring:message code="look"></spring:message></a></td>
		   </tr>
		 </c:forEach>

    	</table>
    	</div>
    	<div class="c">
    	
    	<div class="e">
    	 <spring:message code="page"></spring:message>：${page.currentPage}/${page.totalPage}&nbsp;每页${page.pageSize}&nbsp;总数${page.totalRecord}&nbsp;&nbsp;&nbsp;&nbsp;
  <br>
 	<a href="
          	<c:url value='listtt.do'>
           	<c:param name='p' value='1' ></c:param>
           	<c:param name='who' value='<%=who%>'></c:param>
           	</c:url>
       	"><spring:message code="FirstPage"></spring:message></a>
    <c:choose>  
           <c:when test="${page.currentPage>1}">
               <a href="
               			<c:url value='listtt.do'>
               				<c:param name='p' value='${page.currentPage-1 }' ></c:param>
               				<c:param name='who' value='<%=who%>'></c:param>
               			</c:url>
               		"><spring:message code="PreviousPage"></spring:message></a>
           </c:when>  

    </c:choose>
    <c:choose>  
           <c:when test="${page.currentPage<page.totalPage}">
               <a href="
               			<c:url value='listtt.do'>
               				<c:param name='p' value='${page.currentPage+1 }' ></c:param>
               				<c:param name='who' value='<%=who%>'></c:param>
               			</c:url>
               		"><spring:message code="NextPage"></spring:message></a>
           </c:when>  
    </c:choose>
    <a href="
        	<c:url value='listtt.do'>
  			<c:param name='p' value='${page.totalPage}' ></c:param>
  			<c:param name="who" value="<%=who%>"></c:param>
 			</c:url>
         "><spring:message code="LastPage"></spring:message></a>
     </div> 
    </div>
    <input type="button" value='<spring:message code="NewContacts"></spring:message>' onclick="a()" class="butlast" />
    <a href="findkind.do?who=<%=who %>" class="a"><img src="img/back.jpg" width="20" height="20"></img></a>
    </fieldset>

  </body>
</html>

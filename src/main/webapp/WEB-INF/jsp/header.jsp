<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String serverIP = java.net.InetAddress.getLocalHost().getHostAddress();
%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title>X界</title>
<base href="<%=basePath%>">
<link rel="shortcut icon" href="/file/favicon.ico" />
<%-- <jsp:include page="base.jsp"></jsp:include> --%><%-- 因为header.jsp文件和base.jsp文件在同一个文件目录中所以不用../../base.jsp --%>
<style type="text/css">
 .table th, .table td { 
text-align: center;
vertical-align: middle!important;
} 
</style>
<%@page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>权限不足!</title>
		<link rel="stylesheet" type="text/css" href="resources/widget/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="resources/css/default.css">
		<style type="text/css">
			body{padding-top: 100px;}
			h3{font-weight:normal!important;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<div class="panel panel-danger">
					  <div class="panel-heading">
					    <h3 class="panel-title"><i class="glyphicon glyphicon-exclamation-sign"></i> 权限不足!</h3>
					  </div>
					  <div class="panel-body">
							<h4>对不起，您没有权限访问该页面！</h4>
					    <span class="text-muted">
					    	请求地址：<a href="<%=request.getParameter("uri") %>"><%=request.getParameter("uri") %></a>
					    </span>
					  </div><!-- ./panel-body -->
					  <div class="panel-footer">
					  	<div class="row text-center">
					    	<a class="btn btn-sm btn-danger" href="javascript:history.go(-1);">
					    		<i class="glyphicon glyphicon-chevron-left"></i> 返 回
					    	</a>
					    </div>
					  </div><!-- ./panel-footer -->
					</div><!-- ./panel -->
				</div><!-- ./col-sm-6 -->
			</div><!-- ./row -->
		</div>
	</body>
</html>

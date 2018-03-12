<%@page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
	    <jsp:include page="/WEB-INF/jsp/header.jsp" />
		<base href="<%=basePath%>">
		<title>登录超时</title>
		<link rel="stylesheet" type="text/css" href="resources/widget/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="resources/css/webapp.css">
		<style type="text/css">
			body{padding-top: 100px;}
			a>img{margin-top: -5px;}
		</style>
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <!-- <a class="navbar-brand"><img class="logo" src="resources/img/logo.gif"></a> -->
		      <a class="navbar-brand"><img class="logo" src="/file/favicon.ico"></a>
		      <a class="navbar-brand"><span>little bitch</span></a>
		    </div>
		    <div class="collapse navbar-collapse">

		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<div class="container">
			<div class="row text-center">
				<div class="col-sm-offset-2 col-sm-8">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<h3 class="text-danger"><i class="glyphicon glyphicon-exclamation-sign"></i> 登录超时，请重新登录！</h3>
						</div>
						<div class="panel-body">
							<a href="login?redirectUrl=<%=request.getParameter("redirectUrl")%>">
								<i class="glyphicon glyphicon-hand-right"></i> 单击重新登录
							</a>
						</div>
					</div><!-- ./panel -->
				</div>
			</div><!-- ./row -->
		</div><!-- ./container -->
	</body>
</html>

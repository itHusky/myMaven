<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"  src="resources/widget/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- JS配置文件没有和title在同一个文件的话不能实现下拉菜单的展开 -->
<jsp:include page="base.jsp"></jsp:include>
<style type="text/css">
html,body {
  padding-top: 35px;
}
</style>
	<div class="container">
	   <div class="row">
	       <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
	           <div class="container-fluid">
	               <dir class="navbar-header">
                      <a class="navbar-brand" href="user/list">X界致力让生活更美好！</a>
	               </dir><!-- navbar-header -->
	               <div class="collapse navbar-collapse navbar-right">
	                    <ul class="nav navbar-nav">
	                         <li class="dropdown">
								              <a href="#" class="dropdown-toggle glyphicon glyphicon-play-circle" data-toggle="dropdown"> 列表   <b class="caret"></b></a>
								              <ul class="dropdown-menu">
								                <hf:func uri="user/list">
								                  <li><a href="user/list" class="glyphicon glyphicon-tree-conifer"> 主站</a></li>
								                </hf:func>
								                <hf:func uri="user/list">
								                  <li><a href="user/list" class="glyphicon glyphicon-envelope"> 相簿</a></li>
								                </hf:func>
								                <li role="presentation" class="divider"></li>
								                <hf:func uri="file/list">
								                  <li><a href="file/list" class="glyphicon glyphicon-tree-deciduous"> 文档</a></li>
								                </hf:func>
								                <hf:func uri="video/list">
								                  <li><a href="video/list" class="glyphicon glyphicon-eye-open"> 视频</a></li>
								                </hf:func>
								                <li role="presentation" class="divider"></li>
								                <hf:func uri="user/list">
								                  <li><a href="user/list" class="glyphicon glyphicon-tasks"> 个人空间</a></li>
								                </hf:func>
								                <hf:func uri="user/list">
								                  <li><a href="user/list" class="glyphicon glyphicon-compressed"> 用户中心</a></li>
								                </hf:func>
								              </ul>
								            </li>
	                    </ul><!-- nav navbar-nav -->
	               </div><!-- collapse navbar-collapse -->
	           </div><!-- container-fluid -->
	       </nav><!-- navbar navbar-fixed-top -->
	   </div><!-- row -->
	</div><!-- container -->

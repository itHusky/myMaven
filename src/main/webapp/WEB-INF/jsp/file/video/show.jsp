<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="zyhTag" prefix="zyh" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../header.jsp" />
<style type="text/css">
.bad-video {
	position: relative;
	overflow: hidden;
	background-color: #CCCCCC;
}

.bad-video .vplay {
	position: absolute;
	width: 15%;
	z-index: 99;
	top: 50%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
</style>
</head>
<body>
  <div><jsp:include page="../../title.jsp"></jsp:include></div>
  <jsp:include page="../../video.jsp" flush="false"/>

  <%-- <jsp:include page="../../video.jsp" flush="true" /> --%> <!--动态包含-->
  <%-- <jsp:include page="../../video.jsp" flush="false" /> --%> <!--静态包含-->
  
	<div>
		<video id="video" class="bad-video" style="object-fit:fill;" controls width="640" height="264">
		  <!-- <source url="http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4" type="video/mp4"> --> <!-- 您的浏览器不支持 HTML5 video 标签。 -->
		  <source src="/file/${test }" type="video/mp4">
		  <p>设备不支持</p>
		</video>
		<!-- <img src="/file/1.jpg" /> -->
	</div>
	<div class="controls">
		<div>
			<div class="progressBar">
				<div class="timeBar"></div>
			</div>
		</div>
		<div>
			<span class="current">00:00</span>/<span class="duration">00:00</span>
		</div>
		<div>
			<span class="fill">全屏</span>
		</div>
	</div>
</body>
<script type="text/javascript" src="resources/widget/video-js-6.6.0/video.js"></script>
</html>
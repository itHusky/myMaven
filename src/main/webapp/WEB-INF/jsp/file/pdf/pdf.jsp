<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="zyhTag" prefix="zyh"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../header.jsp" />
<!-- <meta http-equiv="refresh" content="0;url=http://192.168.12.244:8080/myMaven/user/list"> -->
<!-- <meta http-equiv="refresh" content="0;url=http://192.168.10.211:8011/"> -->
<!-- <meta http-equiv="refresh" content="0;url=https://www.baidu.com/"> -->   
<!-- http://192.168.12.244:8080/myMaven/user/list -->
<!-- 使用网页重定向 -->
</head>
<body class="table-responsive">
  <div><jsp:include page="../../title.jsp"></jsp:include></div>
	<div class="container-fluid">
		<div>
			<!-- <table class="table" border="1" cellpadding="2" cellspacing="0" rules="all"> -->
			<table class="table table-hover table-bordered table-striped">
				<tr>
					<th class="">序号</th>
					<th class="col-lg-4">文件名称</th>
					<th>文件大小</th>
					<th class="col-lg-5">文件地址</th>
					<th>操作</th>
				</tr>
				<tbody>
					<c:forEach items="${listFile }" var="listFile" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${listFile.name }</td>
							<td><fmt:formatNumber
									value="${listFile.length() / 1024 / 1024 }" type="number"
									maxFractionDigits="2" />MB</td>
							<td>${listFile.path }</td>
							<td><a class="btn btn-sm btn-default"
								href="javascript:openLayer('${listFile.name }')">打开</a></td>
								<!-- onclick:最开始使用的是的点击事件但是编辑报错  ： 链接的onclick事件是先被执行其次才时href属性下的动作：伪链接或者js函数 -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!--  PDF文件列表显示区域  -->
		<div>${test }</div>
		<!--  PDF内容显示区域  -->
		<div class="hide"><!-- 隐藏显示 -->
			<button class="btn btn-default" onclick="playPause()">播放/暂停</button>
			<button onclick="makeBig()">放大</button>
			<button onclick="makeSmall()">缩小</button>
			<button onclick="makeNormal()">普通</button>
			<br>
			<video id="video1" width="320" height="240" controls> <source
				src="/WEB-INF/jsp/wareHouse/manager.mp4" type="video/mp4">
			<!-- 您的浏览器不支持 HTML5 video 标签。 --></video>
		</div>
		<div>
		  <label>${book }</label>
		</div>
	</div>
</body>
<script>
function openLayer(name){
	layer.open({
	    type : 2,
	    area : [ '800px', '600px' ],
	    fix : false,
	    maxmin : true,
	    content : 'file/show?name=' + name,
	    end : function() {
	    	layer.open({
	    	      type : 2,
	    	      area : [ '800px', '600px' ],
	    	      fix : false,
	    	      maxmin : false,
	    	      content : 'file/show?name=' + name,
	    	      end : function() {
	    	        ;
	    	      }
	    	    });
	    }
	  });
	}

var myVideo=document.getElementById("video1");

function playPause()
{
  if (myVideo.paused)
    myVideo.play();
  else
    myVideo.pause();
}

  function makeBig()
{
  myVideo.width=560;
  myVideo.height=420;
}

  function makeSmall()
{
  myVideo.width=320;
  myVideo.height=240;
}

  function makeNormal()
{
  myVideo.width=420;
}
</script>
</html>
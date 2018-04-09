<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="5;url=https://www.baidu.com/">
<title>跳转界面</title>
<jsp:include page="../header.jsp" />
</head>
<body onload="timedCount()">
    <div style="margin-top: 10%;">
	    <div class="form-group text-center">
	        <H1>技术规范查询平台已升级，请更新您的收藏夹网站地址!</H1>
	    </div>
	    <div class="form-group text-center">
	        <h4 class="text-center"><a style="color:red" id="number">5</a>秒后跳转至新系统...</h4>
	    </div>
	    <div class="form-group">
	        <label class="col-sm-6 text-right">技术查询规范地址：</label>
	        <label class="col-sm-6" id="urlOne">http://....</label>
	    </div>
	    <div class="form-group">
	        <label class="col-sm-6 text-right">后台管理地址：</label>
	        <label class="col-sm-6" id="urlTwo">http://....</label>
	    </div>
	    <div class="form-group col-sm-6">
	        <!-- <a href="#" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.baidu.com');return(false);" >设为首页</a> | -->
	        <!-- <a href="#" onClick="this.style.behavior='url(#default#homepage)';" >设为首页</a> | -->
	        <!-- IE 浏览器操作方式 -->
	        <!-- <a href="javascript:window.external.AddFavorite('http://www.baidu.com','百度一下')" >加入收藏</a> -->
	        <div class="col-sm-8"></div>
	        <a class="btn btn-sm btn-default col-sm-4 text-right" href="test/test" rel="sidebar" onclick="addFavorite()">点击收藏网址</a><!-- '我的网站',location.href -->
	    </div>
	    <div class="form-group col-sm-6">
	        <!-- <a href="#" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.baidu.com');return(false);" >设为首页</a> | -->
	        <!-- <a href="#" onClick="this.style.behavior='url(#default#homepage)';" >设为首页</a> | -->
	        <!-- IE 浏览器操作方式 -->
	        <!-- <a href="javascript:window.external.AddFavorite('http://www.baidu.com','百度一下')" >加入收藏</a> -->
	        <a class="btn btn-sm btn-default col-sm-4 text-left" rel="sidebar" onclock="timedCount()">立刻跳转新系统</a>
	        <div class="col-sm-8"></div>
	    </div>
    </div>
</body>
<script type="text/javascript">

  var c = 6;
  //跳转秒数倒计时
  function timedCount() {
	    document.getElementById("number").value = c;
	    c = c - 1;
	    t = setTimeout(function(){ timedCount();}, 1000);
	    document.getElementById("number").innerHTML = c;  
	}
function addFavorite(){
	console.log(document.all);
	console.log(window.sidebar);
	console.log(window);
	console.log(window.external);
	console.log(document.getElementById("urlOne").innerHTML);
	console.log(document.getElementById("urlTwo").innerText);
    if (document.all){
        try{
        	window.external.AddFavorite(document.getElementById("urlOne"),"技术查询规范地址");
        	window.external.AddFavorite(document.getElementById("urlTwo"),"后台管理地址");
        }catch(e){
            alert( "加入收藏失败，请使用Ctrl+D进行添加1" );
        }
    }else if (window.sidebar){/* 谷歌浏览器window.sidebar为空 没有定义  */
    	//如果 <a>上面没有加 “rel=sidebar” ，那么将会报错： window.sidebar.addPanel is not a function （by default7#zbphp.com）
    	/* 火狐浏览器进入此步但是却没有定义addPanel */
        /* window.sidebar.addPanel(document.title, window.location.href, ''); */
    	window.sidebar.addPanel("技术查询规范地址", document.getElementById("urlOne").innerHTML, '');
    	window.sidebar.addPanel("后台管理地址", document.getElementById("urlTwo").innerHTML, '');
     }else{//chrome 不支持 window.sidebar 对象
        alert( "加入收藏失败，请使用Ctrl+D进行添加" );
    }
}
</script>
</html>
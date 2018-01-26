<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
%>
<!DOCTYPE HTML>
<html>
  <head>
  <meta http-equiv="X-UA-Compatible" content="chrome=1">
  <jsp:include page="header.jsp"></jsp:include>
  <style type="text/css">
           html,body{
               width:100%;
               height:100%;
               margin:0;
               padding: 0;
           }
           h1,h2,h3,h4{font-weight:normal!important;margin-top:0px;padding-top:20px;}
            .wrapper{
               margin-top:-112px;/* 要素2：设置表单上部距离为负数，以便跨过中线，实现背景色对称的效果 */
           }
           .login-form{
               border: 8px solid #666; /* 设置边框宽度 */
               border-color: rgba(0,0,0,.25); /* 设置边框半透明，用法：rgba(R, G, B, 透明度) */
           }
           .login-form-content{
               background: #fff;
           }
  </style>
  <script type="text/javascript" src="resources/js/vue2.js"></script>
        <script language="javascript">
            if(top.location !== self.location){ //  用于焦点集中与当前页面(当前看见的界面)
                top.location.href=self.location.href;// 保持登陆页始终显示在最顶层框架(当前看见的界面)
            }
        </script>
  </head>
    <div><jsp:include page="base.jsp"></jsp:include></div>
	<body>
	<div style="height:32%; width:100%; background-color: #3db8c1;"></div><!-- 要素1： 设置高度为页面高度的40%    注：我觉得这个的效果等价于设置中线 -->
	
	<!-- ---------- -显示错误信息begin- --------- -->
      <c:if test="${not empty message}">
        <div class="alert alert-danger" data-dismiss="alert" aria-hidden="true" style="margin-top:-72px;">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">X</button>
            ${message }
        </div>
        <script type="text/javascript">
        $(function() {
        	setTimeout(function(){
        		$(".alert").alert("close");
        	}, 5000);
        })
        </script>
      </c:if>	
	<!-- ---------- -显示错误信息end- --------- -->
	<div class="row">
	   <div class="col-sm-offset-2 col-sm-8">
	       <div class="login-from wrapper">
	           <div class="page-header">
	               <div class="col-sm-offset-5">
	                   <h2>X界 <small>- 登录</small></h2>
	               </div><!-- col-sm-offset -->
	           </div><!-- page-header -->
	           <!-- ---------- -登录框begin- --------- -->
	           <form id="login" action="login" method="post" class="form-horizontal">
			        <div class="form-group">
			           <label class="col-sm-4 control-label">登录用户</label>
			           <div class="col-sm-4"><input class="form-control" name="userNo"></div>
			        </div>
			        <div class="form-group">
			           <label class="col-sm-4 control-label">用户密码</label>
			           <div class="col-sm-4"><input class="form-control" name="userPassword" type="password"></div>
			        </div>
			        <div class="form-group">
                       <div class="col-sm-offset-3 col-sm-5">
                       <div class="checkbox">
                           <label>
                           <input type="checkbox" id="rememberUser" />记住密码
                           </label>
                        </div>
                        </div>
                    </div>
			        <div class="form-group">
			           <div class="col-sm-offset-3 col-sm-5"><button class="btn btn-success btn-block" type="button" autofocus="autofocus" onclick="login()">登录</button></div>
			        </div>
			    </form>
			    <!-- ---------- -登录框end- --------- -->
			    <!-- ---------- -版权声明begin- --------- -->
			    <nav class="navbar navbar-default navbar-fixed-bottom">
			         <div class="container-fluid">
			         <div class="row">
			             <div style="text-align:center!important">
			                 <p class="navbar-text" style="float:none!important;">SUPER BB@版权所有</p>
			             </div><!-- 优先级强制提升 -->
			         </div><!-- row -->
			         </div><!-- container -->
			    </nav>
			    <!-- ---------- -版权声明end- --------- -->
    
	       </div><!-- login-from wrapper -->
	   </div><!-- col-sm-8 -->	
	</div><!-- row -->
	
	
	</body>
	<script type="text/javascript">
	   $(function(){
		  //表单验证  相关内容
		  //init();//初始化
		  $("input[name='userNo']").focus().keydown(function(event) {
			  if (event.keyCode == 13) {// 回车键的 Unicode值：13 效果等价于按回车键换行
				  $("input[name='userPassword']").focus();
				  return false;
			  }
		  }).change(function() {
			  // 如果cookie中存在保存的密码信息则读取名写入对应的密码框
			  // $("input[name='userPassword']").val("");
		  });
		  $("input[name='userPassword']").keydown(function (event){
			  if (event.keyCode == 13){// 回车键的 Unicode值：13 效果等价于按回车键提交表单
				  $("#login").submit();
			  }
		  });
	   });
	</script>
	<script type="text/javascript">
	   function login(){// $("#login").serialize() 必须配和 <form></form> 标签
		   $("#login").submit();
	   // 不能通过ajax 只能通过提交表单的方式处理这个！！！！ 切记！！！
	   // (这个和ajax的工作目的有关，AJAX最根本原理就是在不刷新页面的情况下访问服务器处理数据,并根据数据的处理结果按你想要的方式对页面作出即时更改)
		   /*console.log("test",$("#login").serialize());
		   ajaxFormUtil("login",{
		        'userNo' : "1052516",
		        'userPassword' : '123'
		    },function() {
			   layer.msg("success");
		   },null,
			 function(){
			   layer.msg("error");
		   });*/
	   }
	</script>
</html>
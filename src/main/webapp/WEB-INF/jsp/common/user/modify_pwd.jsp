<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>修改密码</title>
		<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="resources/css/default.css">
		<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="wrapper">
				<div class="page-header">
				  <h4><i class="glyphicon glyphicon-lock"></i> 修改密码 </h4>
				</div>
				<div class="row">
					<form id="fm" class="form-horizontal">
						<input type="hidden" name="userNo" value="${user.no }">
						<div class="form-group">
					    <label class="col-sm-2 control-label">旧密码：</label>
					    <div class="col-sm-4">
					      <input type="password" class="form-control input-sm" id="oldPasswd" name="oldPasswd" />
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label">新密码：</label>
					    <div class="col-sm-4">
					      <input type="password" class="form-control input-sm" id="newPasswd" name="newPasswd" />
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label">确认新密码：</label>
					    <div class="col-sm-4">
					      <input type="password" class="form-control input-sm" id="check_newPasswd" name="check_newPasswd" />
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <a class="btn btn-sm btn-danger" href="javascript:modifyPwd()"><i class="glyphicon glyphicon-ok"></i> 确认修改</a>
					    </div>
					  </div>
					</form>
				</div><!-- ./row -->
			</div><!-- ./wrapper -->	
		</div>
		<script type="text/javascript">
			var validator;
	
			$(function() {
				jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
					return value != $(param).val();
				}, "新密码不能跟旧密码相同！");
				initValid();
				$("input[name='oldPasswd']").focus();
			});// jQuery
	
			function modifyPwd() {
				if (!validator.form()) {
					return;
				}
	
				ajaxFormUtil("user/modify_pwd", $("#fm").serialize(), function(result) {
					if (result.success) {
						layer.msg("修改成功！");
						setTimeout(function() {
							window.location.href = "logout";
						}, 2000);
					} else {
						layer.alert("修改失败！" + result.message);
					}
				});
			}
	
			function initValid() {
				validator = $("#fm").validate({
					rules : {
						// 'oldPasswd' : "required",旧密码默认为空，不能限制
						'newPasswd' : {
							required : true,
							minlength : 6,
							notEqualTo : '#oldPasswd'
						},
						'check_newPasswd' : {
							required : true,
							minlength : 6,
							equalTo : '#newPasswd'
						}
					},
					messages : {
						'newPasswd' : {
							required : '请输入新密码！',
							minlength : '密码长度不能小于6位！',
							notEqualTo : '新密码不能跟旧密码相同！'
						},
						'check_newPasswd' : {
							required : '请输入确认新密码！',
							minlength : '密码长度不能小于6位！',
							equalTo : '两次输入不一致，请重新输入！'
						}
					}
				});
			}
		</script>
		<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="resources/widget/layer/layer.js"></script>
		<link rel="stylesheet" type="text/css" href="resources/widget/jQuery Validation Plugin/jquery.validate.css">
		<script type="text/javascript" src="resources/widget/jQuery Validation Plugin/jquery.validate.min.js"></script>
		<script type="text/javascript" src="resources/js/util.js"></script>
	</body>
</html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="resources/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="resources/easyui/themes/icon.css">
		<link media="screen" rel="stylesheet" type="text/css" href="resources/widget/jQuery Validation Plugin/jquery.validate.css" />
		<script type="text/javascript" src="resources/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="resources/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="resources/easyui/locale/easyui-lang-zh_CN.js"></script>				
		<script type="text/javascript" src="resources/js/util.js"></script>
		<script type="text/javascript" src="resources/js/jquery.form.js"></script>		
		<script type="text/javascript" src="resources/widget/jQuery Validation Plugin/jquery.validate.min.js"></script>
		<style type="text/css">			

		</style>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'west',title:'部门列表',split:true" style="width:200px;padding:5px;" >
			<ul id="deptTree" class="easyui-tree" url="dept/findByParentId?action=admin" 
						data-options="onClick:showDeptUsers"/>
		</div>
		<div data-options="region:'center',split:true">
			<table id="dgUser" class="easyui-datagrid" url="user/findByPage?action=admin" 
					rownumbers="true" fit="true" striped="true"	remoteSort="true" sortOrder="DESC" 
					title="用户列表" pagination="true" fitColumns="false" pageSize="20" border="false"
					singleSelect="true" toolbar="#tb-dgUser"
					data-options="onClickRow: showUserRoles">
				<thead>
					<th data-options="field:'id'">ID</th>
					<th data-options="field:'no', sortable:true">工号</th>
					<th data-options="field:'name', sortable:true">姓名</th>
					<th data-options="field:'status'">状态</th>
					<th data-options="field:'phone'">电话</th>
					<th data-options="field:'allowLogin', align:'center', formatter:formatAllowLogin">允许登陆</th>
					<th data-options="field:'dept.name', sortable:true, formatter:formatDeptName">部门</th>
					<th data-options="field:'lastLoginTime'">最后一次登陆时间</th>
					<th data-options="field:'lastLoginIP'">最后一次登陆IP</th>
				</thead>
			</table>
		</div>	
		<div data-options="region:'east',title:'拥有角色',split:true" style="width:400px;" >
			<table id="dgRole" class="easyui-datagrid" url="role/findByUserId?action=admin" 
					rownumbers="true" fit="true" striped="true"	remoteSort="true" sortOrder="DESC" 
					pagination="true" fitColumns="true" pageSize="20" border="false"
					singleSelect="false" toolbar="#tb-dgRole">
				<thead>
					<th data-options="checkbox:true"></th>
					<th data-options="field:'name', sortable:true">角色</th>
					<th data-options="field:'desc'">描述</th>
				</thead>
			</table>
		</div>
		<!------------------ 隐藏区域 begin ------------------>
		<div style="display:none">
			<!-- 用户列表工具栏 begin -->
			<div id="tb-dgUser" style="">
				<a href="javascript:addUser()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> 
				<a href="javascript:editUser()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
				<a href="javascript:delUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
				<a href="javascript:resetPasswd()" class="easyui-linkbutton" iconCls="icon-undo" plain="true">重置密码</a>
				<form id="fmSearch" style="display:inline;">
					工号：<input name="no" size="10" />
					姓名：<input name="name" size="10" />
				</form>	
				<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
	    </div>
	    <!-- 用户列表工具栏  end  -->
	    <div id="dlgEditUser" />
			<!-- 角色列表工具栏 begin -->
			<div id="tb-dgRole" style="">
				<a href="javascript:addRoles()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">授予角色</a> 
				<a href="javascript:delRoles()" class="easyui-linkbutton" iconCls="icon-undo" plain="true">撤销角色</a> 
	    </div>
	    <!-- 角色列表工具栏  end  -->
	    <!-- 用来选择角色的表格 begin -->
	    <div id="dlgRoleSelection" class="easyui-dialog" closed="true"/>
				<table id="dgRoleSelection" class="easyui-datagrid" url="role/findByPage" rownumbers="true" fit="true" 
						striped="true" remoteSort="true" sortOrder="DESC" pagination="true" fitColumns="true" 
						singleSelect="false" toolbar="#tb-dlgRoleSelection">
					<thead>
						<th data-options="checkbox:true"></th>
						<th data-options="field:'id'">角色ID</th>
						<th data-options="field:'name', sortable:true">姓名</th>
						<th data-options="field:'desc'">描述</th>
					</thead>
				</table>
			</div>
			<div id="tb-dlgRoleSelection">
				<a href="javascript:authorize()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">确认授权</a> 
	    </div>
	    <!-- 用来选择角色的表格  end  -->
		</div>
		<!------------------ 隐藏区域  end ------------------>	
	</body>
	<script type="text/javascript">		
		$(function(){
				
			});
		
		function addUser(){
			openEditUserDialog("添加用户", "user/add");
		}
		
		function editUser(){
			var user = $("#dgUser").datagrid("getSelected");
			if(!user){
				$.messager.alert("提示", "请选择要编辑的用户！", "warning");
				return;
			}
			openEditUserDialog("编辑用户", "user/edit?id=" + user.id);
		}
		
		function searchUser(){
			var no = $("#fmSearch input[name='no']").val();
			var name =  $("#fmSearch input[name='name']").val();
			var params = {no: no, name: name};
			$("#dgUser").datagrid({queryParams: params});
		}
		
		function delUser(){
			var user = $("#dgUser").datagrid("getSelected");
			if(!user){
				$.messager.alert("提示", "请选择用户！", "warning");
				return;
			}
			$.messager.confirm("提示", "确认删除用户 <span style='color:red'>" + user.name + "</span> ？", function(ok){
					if(ok){
						ajaxFormUtil("user/del?id=" + user.id, {}, function(result){
								if(result.success){
									$.messager.alert("提示", "删除成功！", "info");
									$("#dgUser").datagrid("reload");
								}else{
									$.messager.alert("提示", "删除失败！<br>" + result.message, "warning");
								}
							});
					}
				});
		}
		
		function closeEditUserDialog(){
			$("#dlgEditUser").dialog("close");
		}
		
		function openEditUserDialog(title, href){
			$("#dlgEditUser").dialog({
				    title: title,
				    width: 500,
				    height: 300,
				    closed: false,
				    cache: false,
				    href: href,
				    modal: true,
				    onClose: function(){$("#dgUser").datagrid("reload");}
				});
		}
		
		function showDeptUsers(node){
			var deptId = node.id;
			$("#dgUser").datagrid({queryParams:{'dept.id': deptId}, pageNumber: 1});
		}

		function formatDeptName(value, rowData, rowIndex){
			var dept = rowData.dept;
			if(dept!=null){
				return dept.name;
			}
		}
			
		function formatAllowLogin(allowLogin, row){
			return allowLogin?"<span style='color:red;'>√</span>" : "";
		}
		
		function resetPasswd(){
			var user = $("#dgUser").datagrid("getSelected");
			if(!user){
				$.messager.alert("提示", "请选择用户！", "warning");
				return;
			}
			$.messager.confirm("警告", "确定将用户 <span style='color:red'>" + user.name + "</span> 的密码重置？", function(ok){
					if(ok){
						$.messager.prompt('提示', '请输入新密码：', function(newPasswd){
								if (newPasswd){
									ajaxFormUtil("user/resetPasswd?id=" + user.id + "&newPasswd=" + newPasswd, {}, function(result){
											if(result.success){
												$.messager.alert("提示", "密码重置成功！", "info");
											}else{
												$.messager.alert("提示", "密码重置失败！<br>" + result.message, "warning");
											}
										});
								}
							});
					}
				});
		}
		
		function showUserRoles(rowIndex, rowData){
			var userId = rowData.id;
			$("#dgRole").datagrid({queryParams:{'userId': userId}});
		}
		
		function addRoles(){
			var user = $("#dgUser").datagrid("getSelected");
			if(!user){
				$.messager.alert("提示", "请选择用户！", "warning");
				return;
			}
			$("#dgRoleSelection").datagrid("reload");
			$("#dlgRoleSelection").dialog({
				    title: "请选择要授权的角色！",
				    width: 600,
				    height: 500,
				    closed: false,
				    cache: false,
				    modal: true,
				    onClose: function(){$("#dgRole").datagrid("reload");}
				});
		}
		
		function authorize(){
			var roles = $("#dgRoleSelection").datagrid("getSelections");
			if(!roles){
				$.messager.alert("提示", "请选择要授权的角色！", "warning");
				return;
			}
			var roleIds = [];
			for(var i=0; i<roles.length; i++){
				roleIds.push(roles[i].id);
			}
			roleIds = roleIds.join(",");
			var user = $("#dgUser").datagrid("getSelected");
			$.messager.confirm("警告", "确定授予选中的角色？", function(ok){
					if(ok){
						ajaxFormUtil("role/authorize?userId=" + user.id + "&roleIds=" + roleIds, {}, function(result){
								if(result.success){
									$.messager.alert("提示", "授予角色成功！", "info");
									$("#dgRole").datagrid("reload");
									$("#dlgRoleSelection").dialog("close");
								}else{
									$.messager.alert("提示", "授予角色失败！<br>" + result.message, "warning");
								}
							});
					}
				});
		} 
		
		function delRoles(){
			var roles = $("#dgRole").datagrid("getSelections");
			if(!roles){
				$.messager.alert("提示", "请选择要撤销的角色！", "warning");
				return;
			}
			var roleIds = [];
			for(var i=0; i<roles.length; i++){
				roleIds.push(roles[i].id);
			}
			roleIds = roleIds.join(",");
			var user = $("#dgUser").datagrid("getSelected");
			$.messager.confirm("警告", "确定撤销选中的角色？", function(ok){
					if(ok){
						ajaxFormUtil("role/revoke?userId=" + user.id + "&roleIds=" + roleIds, {}, function(result){
								if(result.success){
									$.messager.alert("提示", "撤销角色成功！", "info");
									$("#dgRole").datagrid("reload");
								}else{
									$.messager.alert("提示", "撤销角色失败！<br>" + result.message, "warning");
								}
							});
					}		
				});
		}		
	</script>
</html>

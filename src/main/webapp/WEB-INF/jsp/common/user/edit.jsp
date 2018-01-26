<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
    
    嵌入式jsp界面 - 通过嵌入父界面来实现相关相关功能展示
        相关父界面函数：closeEditUserDialog();// 父页面（本页面嵌入到父页面的，所以可以直接调用）

 --%>
<style>
	.tbl-edit td{border-bottom:1px solid lightgray;}
	.td-txt{
		text-align:right!important;
		background-color:#F5F5F5;
		border-right:1px solid #F0F0F0;
	}
</style>
<form id="fmUser">
	<input type="hidden" name="id" value="${user.id }">
	<table class="tbl-edit" cellpadding="2" cellspacing="0" style="width:100%;">
		<tbody>
			<tr>
				<td class="td-txt" nowrap width="30%">用户工号：</td>
				<td class="td-val" nowrap width="40%"><input name="userNo" value="${user.user.userNo }" /></td>
				<td></td>
			</tr>
			<tr>
				<td class="td-txt">用户姓名：</td>
				<td class="td-val"><input name="userName" value="${user.user.userName }" /></td>
				<td></td>
			</tr>
			<tr>
                <td class="td-txt">用户权限：</td>
                <td class="td-val"><input name="roles.role" value="${user.roles.role }" /></td>
                <td></td>
            </tr>
            <tr>
                <td class="td-txt">权限描述：</td>
                <td class="td-val"><input name="roles.description" value="${user.roles.description }" /></td>
                <td></td>
            </tr>
<%-- 			<tr>
				<td class="td-txt">允许登录：</td>
				<td class="td-val">
					<input type="hidden" name="_allowLogin" value="true" />
					<label>
						<input type="checkBox" name="allowLogin" value="true" <c:if test="${user.allowLogin eq true }">checked</c:if> >允许
					</label>
				</td>
				<td></td>
			</tr> --%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3" align="center">
					<input type="button" value="保 存" onclick="saveForm()"/>
					<input type="reset" value="重 置" />
				</td>
			</tr>
		</tfoot>
	</table>
</form>
<script type="text/javascript">
	var validator;

	$(function(){
			initValid();
		});

	function saveForm(){
		if(!validator.form()){
			return;
		}

		// 解决dept.name被ComboTree隐藏而无法验证的问题。
		var deptId = $("input[name='dept.id']").val();
		if(deptId == ""){
			$.messager.alert("提示", "请选择所属部门！", "warning");
			return;
		}

		ajaxFormUtil("user/save", $("#fmUser").serialize(), function(result){
				if(result.success){
					$.messager.alert("提示", "保存成功！", "info");
					closeEditUserDialog();// 父页面（本页面嵌入到父页面的，所以可以直接调用）
				}else{
					$.messager.alert("提示", "保存失败！" + result.message, "warning");
				}
			});
	}

	function setDept(node){
		$("input[name='dept.id']").val(node.id);
		$("input[name='dept.name']").val(node.name);
	}

	function initValid() {
		validator = $("#fmUser").validate({
					rules : {
						"no" : {
							required : true
						},
						"name" : {
							required : true
						},
						"dept.name" : {
							required : true
						}
					},
					messages : {

					},
					errorElement : "em",
					errorPlacement : function(error, element) {
						error.appendTo(element.closest("td").next());
					},
					success : "success"
				});
	}
</script>

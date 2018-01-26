<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../header.jsp" />
</head>
<body>
	<div><jsp:include page="../../title.jsp"></jsp:include></div>
	<div class="container-fluid">
		<div class="row form-wrapper">
			<table class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>NO</th>
						<th>pass</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList }" var="userList" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${userList.userName }</td>
							<td>${userList.userNo }</td>
							<td>${userList.userPassword }</td>
							<td>
							    <!-- 删查改 -->
                                <a href="user/show?id=${userList.userId }" title="查看"><i class="glyphicon glyphicon-search"></i></a>
                                <hf:func uri="user/edit">
                                    <a href="user/edit?id=${userList.userId }" title="修改"><i class="glyphicon glyphicon-pencil"></i></a>
                                </hf:func>                                  
                                <hf:func uri="user/delete">
                                    <a href="javascript:delRow(${userList.userId })" title="删除"><i class="glyphicon glyphicon-remove"></i></a>
                                </hf:func>
                            </td>
						</tr>
					</c:forEach>
				</tbody>
				<caption>${caption }</caption>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript" src="resources/mainCode/user.js"></script>
</html>
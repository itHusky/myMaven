<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
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
</head>
<body class="table-responsive">
  <div><jsp:include page="../../title.jsp"></jsp:include></div>
  <div>
    <!-- <a href="#" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.baidu.com');return(false);" >设为首页</a> | -->
    <a href="#" onClick="this.style.behavior='url(#default#homepage)';" >设为首页</a> | 
    <a href="javascript:window.external.AddFavorite('http://www.baidu.com','百度一下')" >加入收藏</a>
    <a href="javascript:void(0);" onclick="AddFavorite('我的网站',location.href)">收藏本站</a>
  </div>
	<table class="table table-hover table-bordered table-striped">
		<thead>
			<tr>
			 <th>序号</th>
			 <th>文件名称</th>
			 <th>大小</th>
			 <th>地址</th>
			 <th>操作</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${fileList }" var="fileList" varStatus="status">
		    <tr>
		      <td>${status.count }</td>
		      <td>${fileList.name }</td>
		      <td><fmt:formatNumber
                  value="${fileList.length() / 1024 / 1024 }" type="number"
                  maxFractionDigits="2" />MB</td>
		      <td>${fileList.path }</td>
		      <td>
		        <a href="video/show?name=${fileList.name }">SHOW</a>
		      </td>
		    </tr>
		  </c:forEach>
		</tbody>
	</table>
</body><!--asdads  -->
<script type="text/javascript">
function AddFavorite(sURL, sTitle)
{
    try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
            alert("加入收藏失败，有劳您手动添加。");
        }
    }
}
</script>
</html>
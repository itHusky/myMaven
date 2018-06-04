<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="zyhTag" prefix="zyh"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../header.jsp" />
</head>
<body class="table-responsive">
	<div><jsp:include page="../title.jsp" /></div>
	<div class="container-fluid">
		<div class="wrapper">
			<div class="row">
				<div class="page-header">
					<h4>
						文件<small>- 列表</small>
					</h4>
				</div><!-- ./page-header -->
			</div><!-- ./row -->
			<div class="form-inline">
				<div class="pull-left">
					<div class="form-group">
						<input type="text" name="displayName"
							class="form-control input-sm col-sm-2"
							value="" placeholder="文件名称">
					</div>
					<div class="form-group">
	                     <button type="submit" class="btn btn-sm btn-info"><i class="glyphicon glyphicon-search"></i> 查 询</button>
	                     <button type="button" class="btn btn-sm btn-default" onclick="$('#fmSearch').k_clearForm().submit();">
	                         <i class="glyphicon glyphicon-remove"></i> 清 空
	                     </button>
	                </div>
				</div>
				<div id="uploader" class="wu-example pull-right">
					<!--用来存放文件信息-->
					<div id="thelist" class="uploader-list"></div>
					<div class="btns">
						<div id="picker" class="btn btn-sm">选择文件</div>
						<button id="ctlBtn" class="btn btn-default">开始上传</button>
					</div>
				</div>
				<div class="pull-right"> <!-- 第一种选择文件 -->
				    <input type="file" id="file_select" style="display:none"/>
				    <button type="button" onclick="openFile()">1.选择文件</button>
				    <!-- <a onclick="javascript:document.getElementById('file_select').click();">选择文件</a> -->
				</div>
				<div class="pull-right"> <!-- 第二种选择文件 -->
					<form name="form1" method="post" action="/abc.php"
						enctype="multipart/form-data">
						<!-- HTML5为表单文件项新增了一个multiple属性，可以设置实现选择多个文件 -->
						<!-- <input type="file" name="userImage" id="userImage" multiple> -->
						
						<input type="file" name="userImage" id="userImage" style="display: none;" multiple>
                        <input type="button" id="" value="选择文件" onclick="document.getElementById('userImage').click()">
					</form>
				</div>
				<div class="pull-right"> <!-- 第三种选择文件 -->
					<form name="form" id="form" method="post" action="fileTest.php"
						enctype="multipart/form-data">
						<!-- <input type="number" name="numberTest" value="100"> -->
						<input type="file" name="fileTest[]" id="fileTest" multiple>
						<!-- 当前选择的文件列表（最多显示5条） -->
						<span class="file-temp"> </span>
						<!-- 查看更多文件 -->
						<ul class="item-more">
						</ul>
						<input type="button" class="btn btn-success" id="uploadBtn"
							value="上传">
						<p class="upload-tip">文件上传成功</p>
					</form>
				</div>
		   </div>
			<!-- 
         table
         table-hover    鼠标移动显示阴影
         table-bordered 淡灰边框
         table-striped  隔行换色
     -->
			<div>
				<!-- class="row form-wrapper" -->
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>文件名称</th>
							<th>文件格式</th>
							<th>内容描述</th>
							<th>文件大小</th>
							<th>上传时间</th>
							<th>文件操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="file" varStatus="status">
							<tr>
								<td>${status.count }</td>
								<td>${file.displayName }</td>
								<td>${file.extension }</td>
								<td>${file.contentType }</td>
								<td>${file.fileSize }</td>
								<td><fmt:formatDate value="${file.createTime }" type="both" />
								<td>
								    <a><i>下载</i></a>
								    <a href="downorup/show?id=${file.fileId }"><i>show</i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<caption>标签</caption>
				</table>
			</div>
		</div>
	</div>
</body>
<link rel="stylesheet" type="text/css" href="resources/widget/webuploader-0.1.5/webuploader.css" />
<script type="text/javascript" src="resources/widget/webuploader-0.1.5/webuploader.js"></script>
<script type="text/javascript">
var applicationPath = window.applicationPath === "" ? "" : window.applicationPath || "../../";
// console.log(applicationPath);
// 文件上传
jQuery(function () {
    var $ = jQuery,
        $list = $('#thelist'),
        $btn = $('#ctlBtn'),
        state = 'pending',
        uploader;
    uploader = WebUploader.create({
        // 不压缩image
        resize: false, 

        // swf文件路径
        //swf: applicationPath + 'Script/webuploader/Uploader.swf',
        swf: applicationPath + 'resources/widget/webuploader-0.1.5/Uploader.swf',

        // 文件接收服务端。
//        server: 'UploaderFileByBaidu.ashx',
        server: 'downorup/upload',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker'

    }); 

    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        // 原来的代码中有bug 多出了一'>'符号
        //$list.append('<div background-color: #f5f5f5; color: #000000;">' + file.id + '" class="item">' +
        /*
        $list.append('<div background-color: #f5f5f5; color: #000000;"' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
        '</div>');
        */
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        $list.append( $li );

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>非图片文件不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, 100, 100 );
        //  }, thumbnailWidth, thumbnailHeight );

    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
    	console.log("开始上传！");
    	
    	
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar'); 
        console.log($percent);
        console.log($li);
        console.log(!$percent.length);
        // 现在问题是没有找到相应的内容  $li
        
        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo($li).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');
        $percent.css('width', percentage * 100 + '%');

    });

    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('p.state').text('已上传');
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });
    uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }
        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });

    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
});
</script>
<script type="text/javascript">
function openFile(){
    document.getElementById("file_select").click();
}
</script>
<!-- 模板化开发的相应使用办法  -->
<!-- 当前选择的文件列表 文件信息模版 -->
<script type="text/template" id="file-temp-item-tpl">
<span class="file-temp-item" style="{{style}}">
<span class="file-temp-name">{{name}}</span>
<span class="file-temp-btn">×</span>
</span>
</script>
<!-- 查看更多文件 文件信息模版 -->
<script type="text/template" id="file-more-item-tpl">
<li>
<span class="file-item-more-name">{{name}}</span>
<span class="file-item-more-btn">×</span>
</li>
</script>
</html>
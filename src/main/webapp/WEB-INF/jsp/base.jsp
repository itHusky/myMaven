<%@page language="java" pageEncoding="UTF-8"%>

<%-- 
下载bootstrap后，发现里面有bootstrap.js 和 bootstrap.min.js 两个js
对于引入后的页面效果是一样的。若你是开发的过程调试中建议引用bootstrap.js（因为这样式，便于阅读）；若发布的话，使用bootstrap.min.js（因为这个比较小）。
bootstrap.min.js这个是压缩版，如果只是自己网站用的话，引入这个就行了
bootstrap.js这个是预编译版，如果你比较专业想修改成自己的库那就用这个
注意css，js文件引入的先后顺序，应该先引入jquery的js文件再引入bootstrap的css文件
--%>

<script type="text/javascript" src="resources/js/jquery-3.2.1.min.js"></script>
<!-- <script type="text/javascript" src="resources/widget/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="resources/widget/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/widget/bootstrap-3.3.7-dist/css/bootstrap.css" />
<%-- bootstrap-theme.min.css 提供动画渐变效果 --%>
<link rel="stylesheet" type="text/css" href="resources/widget/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />
<!-- <link rel="stylesheet" type="text/css" href="resources/widget/bootstrap-3.3.7-dist/css/bootstrap.min.css" /> -->

<script type="text/javascript" src="resources/widget/layui-v2.2.45/layui/layui.all.js"></script>
<script type="text/javascript" src="resources/base/util.js"></script>
<script type="text/javascript" src="resources/base/canvas-nest.min.js"></script>
<script type="text/javascript" src="resources/base/ggk.js"></script>
<script type="text/javascript">
document.body.oncopy = function () {
        alert("复制成功！若要转载请务必保留原文链接，申明来源，谢谢合作！");
};
</script>
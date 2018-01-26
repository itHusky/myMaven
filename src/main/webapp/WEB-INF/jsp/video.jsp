<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<html>
<head>
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
  <link rel="stylesheet" type="text/css"  href="resources/widget/video-js-6.6.0/video-js.css" rel="stylesheet">

  <!-- If you'd like to support IE8 -->
  <script type="text/javascript" src="resources/widget/video-js-6.6.0/ie8/videojs-ie8.min.js"></script>
</head>

<body>
  <video id="my-video" class="video-js" controls preload="auto" width="640" height="264"
  poster="/file/1.jpg" data-setup="{}">
    <source src="/file/${test }" type='video/mp4'>
    <!-- <source src="MY_VIDEO.webm" type='video/webm'> -->
    <p class="vjs-no-js">
      To view this video please enable JavaScript, and consider upgrading to a web browser that
      <!-- <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a> -->
    </p>
  </video>
<div>${test }</div>
  <script type="text/javascript" src="resources/widget/video-js-6.6.0/video.js"></script>
</body>
</html>
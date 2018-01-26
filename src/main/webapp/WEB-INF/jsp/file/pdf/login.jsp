<%@ page language="java" import="java.util.*,java.io.*"
pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
    + request.getServerName() + ":" + request.getServerPort()
    + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <jsp:include page="../../header.jsp" />
</head>
<%
    out.clear();
   out = pageContext.pushBody();
   response.setContentType("application/pdf");

   request.setCharacterEncoding("UTF-8");// 设置接收的字符集
   
   try {
	  /* String name = request.getQueryString().split("=")[1]; */
	  // String name  = request.getParameter("name");
	  // name = "MySQL性能调优与架构设计 PDF中文版全册.pdf";
	  /* name = "12-第一行代码+Android+第2版-郭霖-人邮-2016.12-P580%40www.java1234.com.pdf"; */
	  
	  //这块由于中文参数的编码问题导致参数传递出现bug 如果解决这个问题则迎刃而解
	  //String name  = new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");/*但是这个对于有特殊字符的文件名称而言并不可靠*/
	  
	  String name  = new String(request.getParameter("name"));
	  //因为已经在Tomcat 7.0\conf\server.xml Connector 中新增URIEncoding="UTF-8"　　　　　——设置url传输时对url内容的编码格式 所以就不用上述方法
	  
	  /*不过最好还是不要使用中文进行命名*/
    //String strPdfPath = new String("C:/Users/1101399/Desktop/PDF/" + name);/*在这块我们最好明确PDF文件获取的地址是服务器的某个位置*/
    String strPdfPath = new String(request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/jsp/wareHouse/" + name);
    /*现在我们已经通过访问文件发布后的文件目录来实现读取相应的PDF文件*/
    
    //判断该路径下的文件是否存在
    File file = new File(strPdfPath);
    if (file.exists()) {
     DataOutputStream temps = new DataOutputStream(response.getOutputStream());
     DataInputStream in = new DataInputStream(new FileInputStream(strPdfPath));

     byte[] b = new byte[2048];
     while ((in.read(b)) != -1) {
      temps.write(b);
      temps.flush();
     }

     in.close();
     temps.close();
    } else {
     out.print(strPdfPath + " 文件不存在!");
    }

   } catch (Exception e) {
    out.println(e.getMessage());
   }
%>
<body>
   <br>
   <div class="hidden" id="div">${name }</div>
</body>
<script type="text/javascript">
</script>
</html>
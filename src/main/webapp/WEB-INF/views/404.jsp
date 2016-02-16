<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>404 对不起，您访问的页面不存在 - TOP</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${root}/assets/css/application.css" />
</head>

<body>
  <div class="wrapper">

    <jsp:include page="HEADER.jsp" />
    <div class="container">
      
      <div class="content login-row cf">
        <div>
          <div class="row">
            <div class="col-sm-12 fund-base">
              <p>&nbsp;</p>
              <p>
              <h1>404 啊噢，很抱歉，您访问的页面不存在耶！</h1>
              <p>&nbsp;</p>
              <p>
              <p>1、请检查您访问的网址是否正确；</p>
              <p>2、您所查看的内容不存在或已删除；</p>
              <p>3、你还以尝试访问其它页面。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <jsp:include page="FOOTER.jsp" />
</body>
</html>

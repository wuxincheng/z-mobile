<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>微信识别二维码登录 - 赚了没</title>

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="renderer" content="webkit">
</head>
<body id="home" class="notes-index">
  <div class="container">
    <div class="content row cf" style="text-align: center;">
      <aside>
        <iframe id="main" name="main" src="${wechatOAuthJSURI}" style="width:350px; height: 400px;"
          frameborder="0" scrolling="no" marginheight="0" allowTransparency="true" >
        </iframe>
        <div style="width: 100%; text-align: center; margin-top: 20px;">长按图中的二维码并识别</div>
      </aside>
    </div>
  </div>
</body>
</html>
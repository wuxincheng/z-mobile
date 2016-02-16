<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>错误信息 - 赚了没</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${root}/assets/css/application.css" />
</head>

<body>
  <div class="wrapper">

    <jsp:include page="HEADER.jsp" />
    <div class="container" style="text-align: center;">
      <div class="content cf">
        <div>
          <div class="row">
            <div class="col-sm-12">
              <p>&nbsp;</p>
              <p>
              <h1>错误信息</h1>
              <p>&nbsp;</p>
              <p>
              <p>您还没有登录或者登录超时，为保护您的账户安全，请重新登录。</p>
              <p>&nbsp;</p>
              <p><a href="${root}/login/"><input class="btn submit" type="button" value="确定" /></a></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <jsp:include page="FOOTER.jsp" />
</body>
</html>

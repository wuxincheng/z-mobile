<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>505 对不起，服务器异常 - TOP</title>

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
              <h1>505 对不起，服务器异常</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  <jsp:include page="FOOTER.jsp" />
</body>
</html>

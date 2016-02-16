<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>${product.prodName} | TOP</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<meta name="description" content="${product.prodName}，赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="${product.prodName}，赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<link data-turbolinks-track="true" href="${root}/assets/css/style.css" media="all" rel="stylesheet">
<link data-turbolinks-track="true" href="${root}/assets/css/application.css" media="all" rel="stylesheet">
<script data-turbolinks-track="true" src="${root}/assets/js/application.js"></script>

</head>
<body style="margin-bottom: 0px;">
  <div class="content row">
    <div style="text-align: center; color: #5BCD52; margin-top: 80px;">
      <span>TIP：加入榜单成功！</span>
    </div>
  </div>
</body>
</html>
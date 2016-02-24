<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>榜单 - 赚了没：最好玩的基金排行榜</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body>
  <jsp:include page="../HEADER.jsp" />
  
  <section id="content">
    <div class="container">
      <c:if test="${empty collects}">
      <div class="card">
        <div class="card-header"></div>
        <div class="card-body card-padding">
          <p>${warning}</p>
        </div>
      </div>
      </c:if>
      
      <c:forEach items="${collects}" var="collect">
      <div class="card">
        <div class="card-header">
          <div class="media">
            <div class="pull-left">
              <img class="lv-img" src="${root}/assets/img/profile-pics/profile-pic.jpg" alt=""/>
            </div>
            <div class="media-body m-t-5">
              <h2>
                <span>${collect.nickName}</span> 
                <small>发布时间：${collect.updateTime}</small>
              </h2>
            </div>
          </div>
        </div>
          
        <div class="card-body card-padding">
          <p>${collect.collectName}</p>
          <div class="wall-img-preview lightbox clearfix">
            <a href="${root}/collect/detail?collectid=${collect.collectid}">
              <img src="${collect.coverImgPath}" style="width: 92%; border: 1px solid #F6F6F6;"/>
            </a>
          </div>
          <ul class="wall-attrs clearfix list-inline list-unstyled">
            <li class="wa-stats">
              <span>
                <i class="zmdi zmdi-format-list-bulleted"></i> 
                <span>${collect.productSum}</span>
              </span>
              <span>
                <i class="zmdi zmdi-favorite"></i> 
                <span>${collect.collectSum}</span>
              </span>
            </li>
          </ul>
        </div>
      </div>
      </c:forEach>
    </div>
  </section>
  
  <jsp:include page="../FOOTER.jsp" />
</body>
</html>
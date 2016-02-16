<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人主页 - TOP</title>

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<!-- meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  
  <div class="content row ">
    <c:if test="${not empty userQuery}">
    <section class="profile">
      <a class="user-image-link" href="${root}/user/main?queryUserid=${userQuery.userid}" target="_blank">
        <img class="avatar" height="50px" width="50px" 
          <c:choose>
          <c:when test="${not empty userQuery.socialPicPath}">src="${userQuery.socialPicPath}"</c:when>
          <c:when test="${not empty userQuery.picPath}">src="${root}/imgbase/avatar/${userQuery.picPath}"</c:when>
          <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
          </c:choose> />
      </a>
      <h2 class="user-name">${userQuery.nickName}</h2>
      <p></p>
      <p class="user-subtitle"> ${userQuery.userGroup} - ${userQuery.position}<br></p>
      <p class="user-bio">${userQuery.memo}</p>
      <div class="profile-end-symbol">~</div>
    </section>
    </c:if>

    <c:if test="${empty userQuery}">
    <section class="profile">
      <a class="user-image-link" href="${root}/user/main?queryUserid=${user.userid}" target="_blank">
        <img class="avatar" height="50px" width="50px" 
          <c:choose>
          <c:when test="${not empty user.socialPicPath}">src="${user.socialPicPath}"</c:when>
          <c:when test="${not empty user.picPath}">src="${root}/imgbase/avatar/${user.picPath}"</c:when>
          <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
          </c:choose> />
      </a>
      <h2 class="user-name">${user.nickName}</h2>
      <p></p>
      <p class="user-subtitle"> ${user.userGroup} - ${user.position}<br></p>
      <p class="user-bio">${user.memo}</p>
      <div class="profile-end-symbol">~</div>
    </section>
    </c:if>

    <section class="post">
      <ul class="product-list">
        <c:if test="${not empty fundmarkets}">
        <c:forEach items="${products}" var="product">
        <li class="product-item">
          <div class="posts-group cf">
            <div class="upvote <c:if test="${not empty product.likeState}">voted</c:if>" id="prodlike${product.prodid}">
              <a class="upvote-link vote-up" data-method="put" data-remote="true" onclick="likeProduct('${product.prodid}')">
                <i class="upvote-arrow"></i>
                <span class="vote-count">${product.score}</span>
              </a>
            </div>
            <div class="product-url">
              <a class="post-url" data-client="null" href="${root}/fund/market/detail?fundCode=${product.fundCode}" ref="nofollow" target="_blank">${product.fundName}</a>
              <br>
              <span class="post-tagline">${product.memo}</span>
            </div>
            <ul class="product-meta right">
              <li class="product-avatar">
                <div class="user-image">
                  <a class="user-image-link" href="${root}/user/main?queryUserid=${product.userid}" target="_blank">
                    <img alt="0" class="avatar" height="60" width="60"
                      <c:choose>
                      <c:when test="${not empty product.socialPicPath}">src="${product.socialPicPath}"</c:when>
                      <c:when test="${not empty product.picPath}">src="${root}/user/avatar/${product.picPath}"</c:when>
                      <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                      </c:choose> />
                  </a>
                </div>
                <div class="user-tooltip">
                  <a class="user-image-link" href="#">
                    <img alt="0" class="avatar avatar-big" height="120" width="120"
                      <c:choose>
                      <c:when test="${not empty product.socialPicPath}">src="${product.socialPicPath}"</c:when>
                      <c:when test="${not empty product.picPath}">src="${root}/user/avatar/${product.picPath}"</c:when>
                      <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                      </c:choose> />
                  </a>
                  <h3 class="user-nickname">${product.nickName}</h3>
                  <h4 class="user-title">${product.userGroup} - ${product.position}<br></h4>
                  <p class="user-bio">${product.userMemo}</p>
                </div>
                <div class="product-comment">
                  <a class="product-comments" href="${root}/fund/market/detail?fundCode=${product.fundCode}" target="_blank"> ${product.commentSum} </a>
                </div>
              </li>
            </ul>
        </div>
        <a class="product-link" href="${root}/fund/market/detail?fundCode=${product.fundCode}" target="_blank"></a>
        </li>          
        </c:forEach>
        </c:if>
      </ul>
    </section>
    <div class="table-pagination"></div>
  </div>
  
  <jsp:include page="../FOOTER.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  <div class="container">
    <div class="content row ">
      <section class="main-header cf">
        <a class="btn submit-btn right" href="${root}/product/postUI">分享新产品</a>
      </section>
      
      <c:set var="currentProduct" value="0" />
      <c:choose>
      <c:when test="${not empty pager.productMapList}">
      <c:forEach items="${pager.productMapList}" var="productMap">
      <c:forEach items="${productMap}" var="map">
      <c:set var="currentProduct" value="${currentProduct+1}" />
      <section class="post">
        <div class="date row">
          <span class="cal"> <i class="month">${hfn:engMonth(map.key)}</i> <i class="day">${hfn:simpleDay(map.key)}</i>
          </span> <small>${hfn:monthDay(map.key)}，${hfn:weekday(map.key)}</small>
        </div>
        <ul>
          <c:forEach items="${map.value}" var="obj">
          <li class="product-item ">
            <div class="posts-group cf">
              <!-- 使用voted样式, 表示用户已经赞这个产品 -->
              <div class="upvote <c:if test="${not empty obj.likeState}">voted</c:if>" id="prodlike${obj.prodid}">
                <a class="upvote-link vote-up" onclick="likeProduct('${obj.prodid}')"> 
                  <i class="upvote-arrow"></i> 
                  <span class="vote-count" id="span${obj.prodid}">${obj.score}</span>
                </a>
              </div>

              <div class="product-url">
                <a class="post-url" data-client="null" href="${obj.prodUrl}"
                  ref="nofollow" target="_blank" title="html-js.com">${obj.prodName}</a> <br> <span
                  class="post-tagline">${obj.memo}</span>
              </div>
              <ul class="product-meta right">
                <li class="product-avatar">
                  <div class="user-image">
                    <a class="user-image-link" href="${root}/user/main?queryUserid=${obj.userid}" target="_blank">
                      <img class="avatar" height="60" width="60" 
                        <c:choose>
                        <c:when test="${not empty obj.socialPicPath}">src="${obj.socialPicPath}"</c:when>
                        <c:when test="${not empty obj.picPath}">src="${root}/user/avatar/${obj.picPath}"</c:when>
                        <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                        </c:choose>  />
                    </a>
                  </div>
                  <div class="user-tooltip">
                    <a class="user-image-link" href="#">
                      <img class="avatar avatar-big" height="120" width="120"
                        <c:choose>
                        <c:when test="${not empty obj.socialPicPath}">src="${obj.socialPicPath}"</c:when>
                        <c:when test="${not empty obj.picPath}">src="${root}/user/avatar/${obj.picPath}"</c:when>
                        <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                        </c:choose> />
                    </a>
                    <h3 class="user-nickname">${obj.nickName}</h3>
                    <h4 class="user-title">${obj.userGroup} - ${obj.position}<br></h4>
                    <p class="user-bio">${obj.userMemo}</p>
                  </div>

                  <div class="product-comment">
                    <a class="product-comments" href="${root}/product/detail?prodid=${obj.prodid}" target="_blank"> ${obj.commentSum} </a>
                  </div>
                </li>
              </ul>
            </div> <a class="product-link" href="${root}/product/detail?prodid=${obj.prodid}" target="_blank"></a>
          </li>
          </c:forEach>
        </ul>
        
        <!-- 
        <div class="showmore text-center">
          <a href="javascript:void(0)">展开其余 29 个产品</a>
        </div>
         -->
      </section>
      
      <c:if test="${currentProduct < 2}">
      <section class="featured cf">
        <a class="featured-item" href="http://www.howbuy.com/mobile/zsjj/"
          style="background-image: url(${root}/assets/images/ad/ad1.png)"
          target="_blank">
          <div class="featured-meta">
            <h3>掌上基金</h3>
            <p>人人都是基金经理</p>
          </div>
        </a>
      </section>
      </c:if>
      
      </c:forEach>
      </c:forEach>
      </c:when>
      <c:otherwise>
      </c:otherwise>
      </c:choose>

      <!-- 
      <div class="pagination cf more-notes">
        <a class="load-more-notes" data-remote="true"
          href="#">OK, TOP</a>
      </div>
       -->
      <div class="pagination cf more-notes">
        <a href="#">已经加载全部</a>
      </div>
    </div>
  </div>

  <jsp:include page="../FOOTER.jsp" />
  
  <script type="text/javascript">
  	function likeProduct (prodid) {
	  var url = "${root}/product/like";
	  
	  $.ajax({
	  	url : url, // 跳转到 action    
	  	data : {prodid : prodid},
	  	type : 'post',
	  	beforeSend:function(){
	  	},
	  	cache : false,
	  	dataType : 'json',
	  	success : function(data) {
			var result = data;
			var clazz = 'voted'; // 样式
			var divname = '#prodlike'+result.prodid; // 产品div
			var scorespan = '#span'+result.prodid; // 产品关注度div
			if ('1' == result.flag) { // 点赞标志
				$(divname).addClass(clazz);
			} else {
				$(divname).removeClass(clazz);
			}
			$(scorespan).text(result.score); // 产品关注度
	  	},
	  	error : function() {
	  		alert("友情提示：您还未登录!");
	  	}
	  });
	}  
  </script>

</body>
</html>
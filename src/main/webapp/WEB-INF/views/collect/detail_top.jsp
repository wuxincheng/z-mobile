<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>TOP - 榜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  <div>

    <section class="list-header" style="margin-top: -22px;">
      <div class="list-header-cover" style="
        <c:if test="${empty collect.bgColor}">background-image: url(${root}/collect/coverbg/${collect.coverImgPath});</c:if>
        <c:if test="${not empty collect.bgColor}">background-color: #${collect.bgColor};</c:if>">
      </div>
      <div class="list-header-info row">
        <h2>${collect.collectName}</h2>
        <div class="list-stats">
          <span></span><span>最后更新于 ${collect.updateTime}</span>
        </div>
        <p class="list-bio">${collect.memo}</p>
        <div class="list-header-meta cf">
          <div class="list-fav-btn <c:if test="${not empty collectUser}">faved</c:if>">
            <c:if test="${empty collectUser}">
            <a class="btn " href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">收藏</a>
            </c:if>
            
            <c:if test="${not empty collectUser}">
            <a class="btn fav" href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">已收藏</a>
            </c:if>
            <span class="fav-number"><span class='total-count'>${collect.collectSum}</span> 人已收藏</span>
          </div>
          <div class="share">
            <div class="share-weibo">
              <a href="http://service.weibo.com/share/share.php?searchPic=false&amp;title=${collect.collectName} - ${collect.memo}&amp;url=http://www.zhuanlemei.com/top/collect/detail?collectid=${collect.collectid}" target="_blank" title="点击分享到微博">
                <i class="svg-weibo"></i> <span>微博</span>
              </a>
            </div>
            <div class="share-wechat">
              <a title="微信扫码后分享" href="javascript:void(0)">
                <i class="svg-wechat"></i> <span>微信</span>
              </a>
              <div class="dropdown share-dropdown">
                <img height="156" src="http://s.jiathis.com/qrcode.php?url=http://www.zhuanlemei.com/top/collect/detail?collectid=${collect.collectid}" width="156" />
                <span>微信扫一扫：分享</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    <c:if test="${not empty collect.recommend}">
    <div class="container row">
      <div class="product-item">
        <div class="posts-group cf">
          <span class="post-tagline">${collect.recommend}</span>
        </div>
      </div>
    </div>
    </c:if>

    
    <div class="content row ">
      <section class="main-header cf row">
        <div class="list-tip left">
          <p><p>还有更好的产品没出现在这里？点击右边的按钮添加吧！</p>
        </div>
        <a class="btn submit-btn right" href="${root}/product/postUI?collectid=${collect.collectid}">补充好产品</a>
      </section>
      <section class="post">
        <ul class="product-list">
          <c:if test="${not empty products}">
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
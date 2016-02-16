<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<%@ taglib prefix="fund" uri="/WEB-INF/fund.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html style="font-size: 25.875px;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${fundMarket.fundName}(${fundMarket.fundCode})&nbsp;&nbsp;最好玩的基金排行榜就在赚了没+www.zhuanlemei.com</title>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone=no">

<script async="" src="${root}/assets/vendor/mobile/js/analytics.js"></script>
<script type="text/javascript">
	// 转化rem到vm，主要是用来计算padding，margin，width等等
	;
	(function(win) {
		function setUnitA() {
			document.documentElement.style.fontSize = document.documentElement.clientWidth
					/ 16 + "px";
		}
		var h = null;
		window.addEventListener("resize", function() {
			clearTimeout(h);
			h = setTimeout(setUnitA, 300);
		}, false);
		setUnitA();
	})(window);
</script>
<meta content="authenticity_token" name="csrf-param">
<meta content="0NHL00yRE9MiwmISDmJC7SCOvoWJEpLAMT9qghUsUHA=" name="csrf-token">
</head>
<body class="mobile articles show">
  <script language="JavaScript" type="text/javascript">
  	var o = 'd' + 'i' + 'v', a = ' class="com-s' + 'eo" style=\'disp', b = 'lay:', c = 'none\'';
  	document.write("<" + o + a + b + c + ">");
  </script>
  <div class="com-seo" style="display: none">
    <script language="JavaScript" type="text/javascript">
		var o = 'di' + 'v';
		document.write("</"+o+">");
	</script>
  </div>

  <jsp:include page="../../top.jsp"></jsp:include>
  
  <div class="menu-overlay"></div>
  
  <div class="page-content">
    <div class="com-article-detail short">
      <div class="article-detail-bd">
        <div class="public_fund_item fundPanel">
          <div><h3 class="name">${fundMarket.fundName}（${fundMarket.fundCode}）</h3></div>
          <div class="public-fund-type">
            <span class="gray9">[混合型]</span>
          </div>
          <div class="rate cf">
            <ul class="fl">
              <li>近6月收益</li>
              <li><span class="cGreen">${fundMarket.rateChange}</span></li>
            </ul>
            <ul class="fl" style="padding-left: 20px;">
              <li>最新净值</li>
              <li><span>${fundMarket.currentNav}</span></li>
            </ul>
            <ul class="fr" style="padding-top: 10px;">
              <li>
              <!-- 
              <c:if test="${not empty fundMarket.thisUserFocus}">
                <input id="focus-btn-${fundMarket.fundCode}" type="button" class="btn focus-btn" style="width: 80px;" onclick="focusFund('${fundMarket.fundCode}');" value="已关注" />
              </c:if>
              <c:if test="${empty fundMarket.thisUserFocus}">
                <input id="focus-btn-${fundMarket.fundCode}" type="button" class="btn" style="width: 80px;" onclick="focusFund('${fundMarket.fundCode}');" value="关注" />
              </c:if>
               -->
              </li>
            </ul>
          </div>
        </div>
        <div class="fund-nav-info">
          <c:if test="${not empty fundMarket.fundRiseThreeMonth}"><span><strong>近3月：</strong>${fundMarket.fundRiseThreeMonth}&nbsp;&nbsp;</span></c:if>
          <c:if test="${not empty fundMarket.fundRiseYear}"><span><strong>近1年：</strong>${fundMarket.fundRiseYear}&nbsp;&nbsp;</span></c:if><br/>
          <c:if test="${not empty fundMarket.newScale}"><span><strong>最新规模：</strong>${fundMarket.newScale}亿&nbsp;&nbsp;</span></c:if>
          <c:if test="${not empty fundMarket.foundedDate}"><span><strong>成立日期：</strong>${fundMarket.foundedDate}</span></c:if>
        </div>
      </div>
    </div>
  </div>

  <div class="com-related-comments" data-id="18650" data-initialized="true" data-guid="13">
    <div class="gap"></div>
    <div class="related-comments-bd">
      <p class="count" style="visibility: visible;">评论(${fundMarket.commentSum})</p>
      <div class="items comments">
        <c:if test="${not empty comments}">
        <c:forEach items="${comments}" var="comment">
        <div class="com-comment clearfix" data-commentid="154030">
          <div class="comment-left">
            <a href="javascript:void(0)" class="avatar circle x35">
              <img 
                <c:choose>
                <c:when test="${not empty comment.socialPicPath}">src="${comment.socialPicPath}"</c:when>
                <c:when test="${not empty comment.picPath}">src="${root}/imgbase/avatar/${comment.picPath}"</c:when>
                <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                </c:choose> />
            </a>
          </div>
          <div class="comment-right">
            <div class="name-date">
              <a href="javascript:void(0)" class="name">${comment.nickName}</a>
              <span class="date smart-date">${hfn:relativeDateFormat(comment.createTime)}</span>
            </div>
            <p class="comment-text">${comment.content}</p>
          </div>
        </div>
        </c:forEach>
        </c:if>
        <c:if test="${empty comments}"><div class="comment-tip">暂无评论</div></c:if>
      </div>
    </div>
  </div>

</body>
</html>
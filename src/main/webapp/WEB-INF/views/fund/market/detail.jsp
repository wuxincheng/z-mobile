<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<%@ taglib prefix="fund" uri="/WEB-INF/fund.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>${fundMarket.fundName}(${fundMarket.fundCode})&nbsp;&nbsp;最好玩的基金排行榜就在赚了没+www.zhuanlemei.com</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<script type="text/javascript" src="${root}/assets/js/popup.js"></script>

<meta name="description" content="${fundMarket.fundName}，赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="${fundMarket.fundName}，赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

</head>
<body id="home" class="notes-index">
  <jsp:include page="../../HEADER.jsp" />
  
  <div class="content row cf">
    <div class="forms">
      <div class="main-panel">
        <section class="product cf">
          <div class="fund-detail-panel">
            <input type="hidden" id="likePage" name="likePage" value="detail" />
            <div class="fund-name">
              <input type="hidden" id="userid" name="userid" value="${userid}" />
              <span>${fundMarket.fundName}（${fundMarket.fundCode}）</span>
              <c:if test="${not empty fundMarket.fundType}"><span class="fund-type">${fund:type(fundMarket.fundType)}</span></c:if>
              <c:if test="${'0' != fundMarket.fundRiskLevel}"><span class="fund-type">${fund:riskLevel(fundMarket.fundRiskLevel)}</span></c:if>
              
              <div style="margin-top: -30px; font-weight: normal; font-size: 14px;">
                <span style="float: right;">
                <a class="btn submit" style="padding: 5px 15px;" href="#" onclick="selected('${fundMarket.fundCode}');">加入榜单</a>
                </span>
                <c:if test="${not empty collectUser}">
                <span>
                <a class="btn primary" style="padding: 5px 10px; margin-bottom: 5px; float: right; margin-right: 10px;" 
                  href="${root}/fund/market/focus?fundCode=${fundMarket.fundCode}&userid=${userid}">已关注 (${fundMarket.focusSum})</a>
                </span>
                </c:if>
                <c:if test="${empty collectUser}">
                <span>
                <a class="btn submit" style="padding: 5px 10px; margin-bottom: 5px; float: right; margin-right: 10px;" 
                  href="${root}/fund/market/focus?fundCode=${fundMarket.fundCode}&userid=${userid}">+ 关注 (${fundMarket.focusSum})</a>
                </span>
                </c:if>
              </div>
            </div>
            
            <div class="fund-market">
              <div class="fund-nav-panel">
                <table style="width: 100%;"><tr>
                  <td style="width: 30%;"><div class="fund-nav">
                    <span class="fund-nv-up">${fundMarket.currentNav}</span>
                    <br><span>单位净值 [${fundMarket.navDate}]</span>
                  </div></td>
                  <td style="width: 30%;"><div class="fund-nav-updown">
                    <span class="fund-nv-down">（${fundMarket.rateChange}）</span>
                    <br><span>涨跌幅</span>
                  </div></td>
                  <td style="width: 30%;"><div class="fund-nav-sort">
                    <span class="fund-nv-down">
                      <c:if test="${not empty fundMarket.fundSortThreeMonth}">
                      <span class="fund-nv-up">${fundMarket.fundSortThreeMonth}</span>
                      <span>/&nbsp;${fundMarket.fundTotalThreeMonth}</span>
                      </c:if>
                      <c:if test="${empty fundMarket.fundSortThreeMonth}">
                      <span>无</span>
                      </c:if>
                    </span><br><span>近3月排名</span>
                  </div></td>
                </tr></table>
              </div>
              <div class="fund-nav-info">
                <div class="fund-base">
                  <c:if test="${not empty fundMarket.fundRiseThreeMonth}"><span> 近3月：${fundMarket.fundRiseThreeMonth}&nbsp;&nbsp;</span></c:if>
                  <c:if test="${not empty fundMarket.fundRiseYear}"><span>近1年：${fundMarket.fundRiseYear}&nbsp;&nbsp;</span></c:if>
                  <c:if test="${not empty fundMarket.newScale}"><span>最新规模：${fundMarket.newScale}亿&nbsp;&nbsp;</span></c:if>
                  <c:if test="${not empty fundMarket.foundedDate}"><span>成立日期：${fundMarket.foundedDate}</span></c:if>
                </div>
              </div>
            </div>
          </div>
          
          <div class="zmd-votebar">
            <table><tr>
              <td width="60px">&nbsp;<br><button class="up" aria-pressed="false" title="赞同" 
                onclick="likeMarket('${fundMarket.fundCode}', '0')">
              <i class="icon vote-arrow"></i>
              <span class="label">赞同</span>
              <div id="likeScore" class="count" style="margin-top: 10px;">赚</div>
              </button><br>
              <div>（${fundMarket.likeScore}）</div>
              </td>
              <td style="width:50px;">&nbsp;<br><button class="down" aria-pressed="false" 
                onclick="likeMarket('${fundMarket.fundCode}', '1')" title="反对，不会显示你的姓名">
              <div id="unLikeScore" class="countdown">赔</div>
              <i class="icon vote-arrowdown"></i>
              <span class="label">反对，不会显示你的姓名</span>
              </button><br>
              <div>（${fundMarket.unLikeScore}）</div>
              </td>
            </tr></table>
            <div class="share" style="padding-bottom: 0px; text-align: left; float: right; border: 0px; display: inline; margin-top: -40px;">
              <div class="share-weibo">
                <a href="http://service.weibo.com/share/share.php?searchPic=false&amp;title=${fundMarket.fundName}[${fundMarket.fundCode}]&amp;url=http://www.zhuanlemei.com/fund/market/detail?fundCode=${fundMarket.fundCode}" target="_blank" title="点击分享到微博">
                  <i class="svg-weibo"></i> <span>微博</span>
                </a>
              </div>
              <div class="share-wechat" style="padding-right: 0px;">
                <a title="微信扫码后分享" href="javascript:void(0)">
                  <i class="svg-wechat"></i> <span>微信</span>
                </a>
                <div class="dropdown share-dropdown">
                  <img height="156" src="http://s.jiathis.com/qrcode.php?url=http://www.zhuanlemei.com/fund/market/detail?fundCode=${fundMarket.fundCode}"
                     width="156" />
                  <span>微信扫一扫：分享</span>
                </div>
              </div>
            </div>
          </div>
        </section>
        
        <section class="comments" id="comments">
          <h4>评论 (${fundMarket.commentSum})</h4>
          <c:if test="${empty user}">
          <form accept-charset="UTF-8" action="" class="simple_form comment" method="post">
            <a href="${root}/login/">登录后发表评论</a></form>
          </c:if>
          <c:if test="${not empty user}">
          <form accept-charset="UTF-8" action="${root}/comment/post" class="simple_form comment" method="post">
            <input id="fundCode" name="fundCode" type="hidden" value="${fundMarket.fundCode}" />
            <input id="commentType" name="commentType" type="hidden" value="fundMarket" />
            <div class="form-group hidden comment_content">
            <input class="hidden form-control input-big" id="comment_content" name="content" type="hidden" />
            </div>
            <div contenteditable="true" class="input-big mention editable-comment form-control" 
              data-for="content" required="required" mentionable="true">
            </div>
            <input class="btn submit" name="commit" type="submit" value="发表评论" />
            <span class='help-inline'></span>
          </form>
          </c:if>
          
          <!-- 评论 -->
          <c:if test="${not empty comments}">
          <c:forEach items="${comments}" var="comment">
          <div class="media comment" id="comment_25191">
            <div class="comment-avatar">
              <div class="user-image">
                <a class="user-image-link" href="${root}/user/main?queryUserid=${comment.userid}" target="_blank">
                  <img class="avatar" height="60" width="60" 
                    <c:choose>
                    <c:when test="${not empty comment.socialPicPath}">src="${comment.socialPicPath}"</c:when>
                    <c:when test="${not empty comment.picPath}">src="${root}/user/avatar/${comment.picPath}"</c:when>
                    <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                    </c:choose> />
                </a>
              </div>
              <div class="user-tooltip">
                <a class="user-image-link" href="#">
                  <img class="avatar avatar-big" height="120" width="120" 
                    <c:choose>
                    <c:when test="${not empty comment.socialPicPath}">src="${comment.socialPicPath}"</c:when>
                    <c:when test="${not empty comment.picPath}">src="${root}/user/avatar/${comment.picPath}"</c:when>
                    <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                    </c:choose> />
                </a>
                <h3 class="user-nickname">${comment.nickName}</h3>
                <h4 class='user-title'>${comment.userGroup} - ${comment.position}<br></h4>
                <p class="user-bio">${comment.userMemo}</p>
              </div>
            </div>
            <div class="media-body">
              <div class="comment-details">
                <div class='comment-meta cf'>
                  <h3 class="user-nickname"><a href="#">${comment.nickName}</a></h3>
                  <span class="user-bio">${comment.userMemo}</span>
                </div>
                <div class="comment-content">
                  <p>${comment.content}</p>
                  <em>${hfn:relativeDateFormat(comment.createTime)}</em>
                </div>
                <!-- 
                <p>- <a class="reply" href="">回复</a><span class="reply">(1)</span></p>
                 -->
              </div>
            </div>
          </div>        
          </c:forEach>
          </c:if>
        
          <div id="mention_wrapper" data-note-id="14941">
            <input type="text" id="mention_user_selector">
          </div>
        </section>
      </div>
    </div>
    <aside class="aside-zlm" style="font-weight: normal;">
      <jsp:include page="sort.jsp" />
    </aside>
  </div>

  <jsp:include page="../../FOOTER.jsp" />
  
  <script type="text/javascript">
    function selected(fundCode) {
    	var userid = $("#userid").val();
    	if (userid == null || userid == '') {
    		alert('您还没有登录，请登录！');
    		window.location.href = "${root}/login/";
    		return;
    	}
      
      	var url = "${root}/collect/addin?fundCode="+fundCode;
      	var pop = new Popup({ contentType:1,scrollType:'auto',isReloadOnClose:false,width:700,height:390});
          pop.setContent("contentUrl", url);
          pop.setContent("title", "+ 加入榜单");
          pop.build();
          pop.show();
    }
  </script>
  
</body>
</html>
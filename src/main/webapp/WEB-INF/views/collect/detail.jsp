<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<%@ taglib prefix="fund" uri="/WEB-INF/fund.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>${collect.collectName}&nbsp;&nbsp;最好玩的基金排行榜就在赚了没+www.zhuanlemei.com</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />
<script type="text/javascript">
  function focusFund(fundCode) {
    $.post('${root}'+"/collect/focus", {
      "fundCode" : fundCode
    }, function(response) {
      if (response.redirectUrl) {
        layer.msg("请登录");
        window.location = '${root}'+response.redirectUrl;
        return;
      }
      if (response.successMsg) {
        var flag = response.successMsg;
        if (flag == '1') {
        layer.msg("关注成功");
        // 修改关注按钮样式
        $("#focus-btn-"+fundCode).addClass("focus-btn");
        $("#focus-btn-"+fundCode).val("已关注");
        }
        if (flag == '0') {
        layer.msg("取消关注成功");
        // 修改关注按钮样式
        $("#focus-btn-"+fundCode).removeClass("focus-btn");
        $("#focus-btn-"+fundCode).val("关注");
        }
      }
    });
  }
</script>
</head>
<body>
  <jsp:include page="../HEADER.jsp" />
  
  <section id="content">
    <div class="container">
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
            <img src="${collect.coverImgPath}" style="width: 92%; border: 1px solid #F6F6F6;"/>
          </div>
          <div>${collect.memo}</div>
          <div>${collect.recommend}</div>
          <ul class="wall-attrs clearfix list-inline list-unstyled" style="margin-top:20px;">
            <li class="wa-stats">
              <c:if test="${not empty collectUser}">
              <span>
                <a href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">
                <i class="zmdi zmdi-favorite"></i> 已关注
                </a>
              </span>
              </c:if>
              <c:if test="${empty collectUser}">
              <span>
                <a href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">
                <i class="zmdi zmdi-favorite-outline"></i> 未关注
                </a>
              </span>
              </c:if>
            </li>
          </ul>
        </div>
      </div>

      <div class="card">
        <div class="listview lv-bordered lv-lg">
          <div class="lv-body">
            <c:forEach items="${fundMarkets}" var="fundMarket">
            <div class="lv-item media">
              <div class="pull-right">
                <!-- 
                <button class="btn btn-primary">关注</button>
                <button class="btn btn-default">已关注</button>
                 -->
              </div>
              <div class="media-body">
                <div class="lv-title">${fundMarket.fundName}（${fundMarket.fundCode}）</div>
                <small class="lv-small"></small>

                <ul class="lv-attrs">
                  <li>净值日期：${fundMarket.navDate}</li>
                  <li>单位净值：${fundMarket.currentNav}</li>
                  <li>涨跌幅：${fundMarket.rateChange}</li>
                  <li>最新规模：${fundMarket.newScale}亿</li>
                  <li>成立日期：${fundMarket.foundedDate}</li>
                  <li>基金经理：${fund:huntHigh(fundMarket.fundManager, keyword)}</li>
                </ul>
              </div>
            </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </section>
  
  <jsp:include page="../FOOTER.jsp" />
</body>
</html>
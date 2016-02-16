<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<%@ taglib prefix="fund" uri="/WEB-INF/fund.tld"%>
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
  <jsp:include page="../../HEADER.jsp" />
  <div class="content row cf">
    <c:if test="${not empty keyword}"><div class="keyword">搜索关键字：<span class="fund-nv-up">${keyword}</span></div></c:if>
    <div class="forms">
      <div class="main-panel">
        <input type="hidden" id="likePage" name="likePage" value="list" />
        <c:choose>
        <c:when test="${not empty pager.fundMarkets}">
        <c:forEach items="${pager.fundMarkets}" var="fundMarket">
          <div class="fund-panel">
            <div class="zm-votebar goog-scrollfloater">
              <button class="up" aria-pressed="false" title="赞同"
                onclick="likeMarket('${fundMarket.fundCode}', '0')">
              <i class="icon vote-arrow"></i>
              <span class="label">赞同</span>
              <div id="${fundMarket.fundCode}count" class="count" style="margin-top: 10px;">赚</div>
              </button>
              <button class="down" aria-pressed="false" title="反对，不会显示你的姓名"
                onclick="likeMarket('${fundMarket.fundCode}', '1')">
              <div id="${fundMarket.fundCode}countdown" class="countdown">赔</div>
              <i class="icon vote-arrowdown"></i>
              <span class="label">反对，不会显示你的姓名</span>
              </button>
            </div>
            <div class="fund-info">
              <div class="fund-name">
                <a href="${root}/fund/market/detail?fundCode=${fundMarket.fundCode}" target="_blank">
                ${fund:huntHigh(fundMarket.fundName, keyword)}（${fund:huntHigh(fundMarket.fundCode, keyword)}）
                </a></div>
              <div class="fund-base">单位净值&nbsp;[${fundMarket.navDate}] 
                <c:if test="${not empty fundMarket.currentNav}"><span class="fund-nv-up">${fundMarket.currentNav}</span></c:if>
                <c:if test="${not empty fundMarket.rateChange}"><span class="fund-nv-down">（${fundMarket.rateChange}）</span></c:if>
              </div>
              <div class="fund-base">
                <c:if test="${not empty fundMarket.newScale}">最新规模：${fundMarket.newScale}亿&nbsp;&nbsp;</c:if>
                <c:if test="${not empty fundMarket.fundManager}">基金经理：${fund:huntHigh(fundMarket.fundManager, keyword)}&nbsp;&nbsp;</c:if>
                <c:if test="${not empty fundMarket.fundCompany}">基金公司：${fund:huntHigh(fundMarket.fundCompany, keyword)}&nbsp;&nbsp;</c:if>
                <c:if test="${not empty fundMarket.foundedDate}"><span>成立日期：${fundMarket.foundedDate}</span></c:if>
              </div>
              <div class="fund-base">赚（${fundMarket.likeScore}）&nbsp;&nbsp;赔（${fundMarket.unLikeScore}）&nbsp;&nbsp;评论（${fundMarket.commentSum}）</div>
            </div>
          </div>
        </c:forEach>
        
        <div class="pagination cf more-notes">
          <a <c:if test="${pager.currentPage > 1}">
          href="${root}/fund/market/list?currentPage=1&keyword=${keyword}"
          </c:if>>首页</a>
          <a <c:if test="${pager.currentPage > 1}">
          href="${root}/fund/market/list?currentPage=${pager.currentPage-1}&keyword=${keyword}"
          </c:if>>上一页</a>
          <a <c:if test="${pager.currentPage < pager.lastPage}">
          href="${root}/fund/market/list?currentPage=${pager.currentPage+1}&keyword=${keyword}"
          </c:if>>下一页</a>
          <a <c:if test="${pager.currentPage < pager.lastPage}">
          href="${root}/fund/market/list?currentPage=${pager.lastPage}&keyword=${keyword}"
          </c:if>>尾页</a>
          &nbsp;&nbsp;
          <strong>${pager.currentPage}&nbsp;/&nbsp;${pager.lastPage}</strong>
        </div>
        
        </c:when>
        <c:otherwise>
          <!-- 
          <div id="msg-alert" class="alert alert-info fade in row" style="margin-top: 30px;">
            <h4>没有查询到相关的基金信息</h4>
          </div>
           -->
        </c:otherwise>
        </c:choose>
      </div>
    </div>
    
    <aside class="aside-zlm">
      <jsp:include page="sort.jsp" />
    </aside>
    
  </div>
  
  <jsp:include page="../../FOOTER.jsp" />

</body>
</html>
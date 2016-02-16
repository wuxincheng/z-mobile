<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<div class="fund-sort" style="border-bottom: 2px solid #C00;">
  <span class="fund-nv-up">红榜</span>
</div>

<c:forEach items="${topRedMarkets}" var="topRedMarket" varStatus="s">
  <div class="fund-sort">
    <a href="${root}/fund/market/detail?fundCode=${topRedMarket.fundCode}" target="_blank">
    <span class="sort-num-up">${s.index+1}</span>${topRedMarket.fundName}</a>
  </div>
</c:forEach>

<p>&nbsp;</p>
<p>&nbsp;</p>

<div class="fund-sort" style="border-bottom: 2px solid #080;">
  <span class="fund-nv-down">绿榜</span>
</div>

<c:forEach items="${topGreenMarkets}" var="topGreenMarket" varStatus="s">
  <div class="fund-sort">
    <a href="${root}/fund/market/detail?fundCode=${topGreenMarket.fundCode}" target="_blank">
    <span class="sort-num-down">${s.index+1}</span>${topGreenMarket.fundName}</a>
  </div>
</c:forEach>

<p>&nbsp;</p>

<section class="featured cf" style="margin-top: 30px;">
  <a class="featured-item" href="http://www.howbuy.com/topic/hlwyy.html"
    style="background-image: url(${root}/assets/images/ad/ad-left-bottom.jpg)"
    target="_blank">
  </a>
</section>

</html>
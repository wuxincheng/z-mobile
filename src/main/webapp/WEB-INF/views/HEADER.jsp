<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赚了没|找到你喜欢的理财产品，榜单|赚了没</title>

<!-- Vendor CSS -->
<link type="text/css" rel="stylesheet" href="${root}/assets/vendor/components/fullcalendar/dist/fullcalendar.min.css" />
<link type="text/css" rel="stylesheet" href="${root}/assets/vendor/components/animate.css/animate.min.css" />
<link type="text/css" rel="stylesheet" href="${root}/assets/vendor/components/bootstrap-sweetalert/lib/sweet-alert.css" />
<link type="text/css" rel="stylesheet" href="${root}/assets/vendor/components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" />
<link type="text/css" rel="stylesheet" href="${root}/assets/vendor/components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" />

<!-- CSS -->
<link type="text/css" rel="stylesheet" href="${root}/assets/css/app.min.1.css" />
<link type="text/css" rel="stylesheet" href="${root}/assets/css/app.min.2.css" />
<link type="text/css" rel="stylesheet" href="${root}/assets/css/style.css" />

<script type="text/javascript" src="${root}/assets/vendor/components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="${root}/assets/vendor/components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${root}/assets/vendor/layer/layer.js"></script>

<!-- meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<!-- info -->
<meta name="description" content="找到你喜欢的理财产品">
<meta name="keywords" content="赚了没，基金点评，基金分享，基金排行榜">

<!--iOS -->
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<script type="text/javascript">
$(document).ready(function(){
  $('#unlogin-panel').click(function(e) {  
    window.location.href = '${root}/login/';
  });
});
</script>
</head>
<body>
  <header id="header" class="clearfix" data-current-skin="blue">
    <ul class="header-inner">
      <li id="menu-trigger" data-trigger="#sidebar">
        <div class="line-wrap">
          <div class="line top"></div>
          <div class="line center"></div>
          <div class="line bottom"></div>
        </div>
      </li>
      <!--  hidden-xs -->
      <li class="logo"><a href="">赚了没</a></li>

      <li class="pull-right">
        <ul class="top-menu">
          <li id="toggle-width">
            <div class="toggle-switch">
              <input id="tw-switch" type="checkbox" hidden="hidden" />
              <label for="tw-switch" class="ts-helper"></label>
            </div>
          </li>

          <c:if test="${empty user}">
          <li class="dropdown"><div class="unlogin"><a href="${root}/login/">未登录</a></div></li>
          </c:if>
          
          <c:if test="${not empty user}">
          <li class="dropdown"><a data-toggle="dropdown" href=""><i
              class="tm-icon zmdi zmdi-account"></i></a>
            <ul class="dropdown-menu dm-icon pull-right">
              <li><a data-action="clear-localstorage" href="${root}/my/collect/list"><i
                  class="zmdi zmdi-center-focus-strong"></i> 我的关注</a></li>
              <li><a href="${root}/logout/"><i class="zmdi zmdi-time-restore"></i>退出系统</a></li>
            </ul></li>
          </c:if>

        </ul>
      </li>
    </ul>
  </header>

  <section id="main" data-layout="layout-1">
    <aside id="sidebar" class="sidebar c-overflow">
      <c:if test="${not empty user}">
      <div class="profile-menu">
        <a href="">
          <c:if test="${not empty user.socialPicPath}">
          <div class="profile-pic">
            <img src="${user.socialPicPath}" />
          </div>
          </c:if>
          
          <c:if test="${empty user.socialPicPath}">
          <div class="profile-pic">
            <img src="${root}/assets/img/profile-pics/4.jpg" />
          </div>
          </c:if>

          <div class="profile-info">
            ${user.nickName} <i class="zmdi zmdi-caret-down"></i>
          </div>
        </a>

        <ul class="main-menu">
          <li><a href="${root}/logout/"><i class="zmdi zmdi-time-restore"></i>退出系统</a></li>
        </ul>
      </div>
      </c:if>
      
      <c:if test="${empty user}">
      <div class="profile-menu" id="unlogin-panel">
        <a href="">
          <div class="profile-pic">
            <img src="${root}/assets/img/unlogin.png" />
          </div>
          <div class="profile-info">未登录</div>
        </a>
      </div>
      </c:if>

      <ul class="main-menu">
        <li><a href="${root}/collect/list"><i
            class="zmdi zmdi-home"></i> 首 页</a></li>
        <li><a href="${root}/discovery/list"><i
            class="zmdi zmdi-eye"></i> 发 现</a></li>
        <li><a href="${root}/my/collect/list"><i
            class="zmdi zmdi-view-list"></i> 关 注</a></li>
        <li><a href="${root}/collect/list"><i
            class="zmdi zmdi-notifications"></i> 通 知</a></li>
        <li><a href=""><i
            class="zmdi zmdi-info"></i>关 于</a></li>
      </ul>
    </aside>
    
</body>
</html>
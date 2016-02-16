<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html style="font-size: 25.875px;">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>赚了没榜单</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link href="${root}/assets/vendor/bootstrap/css/bootstrap.css" media="all" rel="stylesheet">
  <link href="${root}/assets/vendor/mobile/css/index.css" media="all" rel="stylesheet" type="text/css">
  <link href="${root}/assets/vendor/mobile/css/fund.css" media="all" rel="stylesheet" type="text/css">
  <link href="${root}/assets/vendor/mobile/css/show.css" media="all" rel="stylesheet" type="text/css">
  <link href="${root}/assets/vendor/mobile/css/style.css" media="all" rel="stylesheet">
  
  <script src="${root}/assets/js/jquery.min.js"></script>
  <script src="${root}/assets/vendor/layer/layer.js"></script>
  <script src="${root}/assets/vendor/mobile/js/analytics.js"></script>
  <script src="${root}/assets/vendor/mobile/js/redirect.js"></script>
  <script src="${root}/assets/vendor/mobile/js/redirect.js" type="text/javascript"></script>
  
  <script type="text/javascript">
  	function showMenu(){
  		var flag = $("#menu-flag").val();
  		if (flag == 0) {
    		$("#user-menu").removeClass("hidden");
    		$("#menu-flag").val(1);
  		}
  		if (flag == 1) {
  			$("#user-menu").addClass("hidden");
    		$("#menu-flag").val(0);
  		}
  	}
  </script>
</head>
<body>
  <div class="com-header clearfix" data-islogined="false" data-guid="1" data-initialized="true">
    <div class="header-left" style="font-size: 18px;"><a href="${root}/mobile/collect/list">赚了没？</a></div>
    <div class="header-right">
      <c:if test="${not empty user}">
      <div class="user signin">
        <a href="#" class="user-hd" onclick="showMenu();">
          <span class="glyphicon glyphicon-user"></span> ${user.nickName}
        </a>
        <div id="user-menu" class="user-bd hidden">
          <input type="hidden" id="menu-flag" name="menu-flag" value="0" />
          <ul class="items">
            <li class="item">
              <a href="#" target="_self">
                <span class="glyphicon glyphicon-thumbs-up"></span> 我的关注
              </a>
            </li>
            <li class="item account-setting-link">
              <a href="/mobile/users/edit" target="_self">
                <span class="glyphicon glyphicon-home"></span> 个人中心
              </a>
            </li>
            <li class="item account-logout">
               <a href="${root}/mobile/logout/"><span class="glyphicon glyphicon-log-out"></span> 退出登录</a>
            </li>
          </ul>
        </div>
      </div>
      </c:if>
      
      <c:if test="${empty user}">
      <div class="user unsign">
        <a href="${root}/mobile/login/" class="user-hd open-popup">
          <span class="glyphicon glyphicon-user"></span> 登录
        </a>
      </div>
      </c:if>
    </div>
  </div>
</body>
</html>
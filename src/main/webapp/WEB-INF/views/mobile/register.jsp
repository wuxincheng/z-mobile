<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册 - 赚了没</title>

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<script src="${root}/assets/js/jquery.min.js"></script>
<script src="${root}/assets/vendor/layer/layer.js"></script>

<link href="${root}/assets/vendor/bootstrap/css/bootstrap.css" media="all" rel="stylesheet">
<link href="${root}/assets/vendor/mobile/css/index.css" media="all" rel="stylesheet" type="text/css">
<link href="${root}/assets/vendor/mobile/css/fund.css" media="all" rel="stylesheet" type="text/css">
<link href="${root}/assets/vendor/mobile/css/show.css" media="all" rel="stylesheet" type="text/css">
<link href="${root}/assets/vendor/mobile/css/style.css" media="all" rel="stylesheet">

<!-- meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<script type="text/javascript">
$(document).ready(function() {
});
function doSubmit() {
    var form_data = $('form').serialize();
	$.ajax( {
		type : "POST",
        url : '${root}'+"/mobile/register/submit",
        data : form_data,
        dataType : "json",
        success : function(response){
        	if (!response.success) {
        		layer.msg(response.errorMsg);
              	return;
        	}
        	if (response.redirectUrl) {
        		window.location = '${root}'+response.redirectUrl;
        	}
        	layer.msg("注册成功");
        },
        error : function(){layer.msg("新用户注册失败");}
    });
}
</script>
</head>
<body>
    <!-- 
  <div class="com-header clearfix" data-islogined="false" data-guid="1" data-initialized="true">
    <div class="header-left"><a href="javascript:window.opener=null;window.close();">返回</a></div>
    <div class="header-center">注册</div>
    <div class="header-right">返回</div>
  </div>
     -->
  <div class="container">
      <div class="form-panel">
        <div class="reg-title">新用户注册</div>
        <form accept-charset="UTF-8" action="${root}/mobile/register/submit" method="post">
          <div class="form-group">
            <input class="login-reg-input" id="loginEmail" name="loginEmail"
              placeholder="请输入邮箱" type="email" maxlength="50" />
          </div>
          <div class="form-group">
            <input class="login-reg-input" id="nickName" name="nickName"
              placeholder="请输入昵称" type="text" maxlength="10" />
          </div>
          <div class="form-group">
            <input class="login-reg-input" maxlength="50"
              id="password" name="password" placeholder="请输入密码" type="password" />
          </div>
          <div class="form-group">
            <input class="login-reg-input" maxlength="50"
              id="password2" name="password2" placeholder="重新输入密码" type="password" />
          </div>
          <input class="btn login-reg-btn" type="button" onclick="doSubmit();" value="注册" />
          
          <div class="fotter-tip">
            <strong><a href="${root}/mobile/login/">立即登录</a></strong>
          </div>
        </form>
      </div>
  </div>
</body>
</html>
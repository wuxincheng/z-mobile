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
  <meta name="format-detection" content="telephone=no">
  <link href="${root}/assets/vendor/bootstrap/css/bootstrap.css" media="all" rel="stylesheet">
  <script src="${root}/assets/vendor/mobile/js/analytics.js"></script>
  <script type="text/javascript">
    // 转化rem到vm，主要是用来计算padding，margin，width等等
    ;(function(win) {
        function setUnitA() {
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 16 + "px";
        }
        var h = null;
        window.addEventListener("resize", function() {
            clearTimeout(h);
            h = setTimeout(setUnitA, 300);
        }, false);
        setUnitA();
    })(window);
  </script>
</head>
<body class="mobile homes index">
  
  <jsp:include page="../top.jsp"></jsp:include>
  
  <div class="page-content">
    <div class="packery-container articles clearfix">
      <div class="packery-item combo" id="item-content">
      </div>
    </div>
  
    <p>&nbsp;</p>
    <!-- 
    <div class="com-loader loading" data-guid="3" data-initialized="true">
    	<div class="loader-bd">
    		<p class="nomore">没有更多啦</p>
    		<div class="spinner">
    			<div class="bounce1"></div>
    			<div class="bounce2"></div>
    			<div class="bounce3"></div>
    		</div>
    	</div>
    </div>
     -->
    
    <div id="load_more_div" class="container" style="text-align: center;">
      <div class="really-container">
        <button id="moretag" class="btn btn-block btn-lg btn-warning" onclick="loadMore()" type="button">已经加载全部</button>
      </div>
    </div>
    
    <p>&nbsp;</p>
</div>

<script src="${root}/assets/vendor/mobile/js/base.js" type="text/javascript"></script>

<script type="text/javascript">

$(document).ready(function(){
  var url = "${root}/mobile/collect/loadmore";
  var html = 
	'<a href="${root}/mobile/collect/detail?collectid=COLLECT_ID" class="com-grid-article">\
  	<div class="grid-article-hd">\
  		<div class="imgcover">\
  			<img class="pic" src="COVER_IMG_PATH" />\
  		</div>\
  		<div class="ribbon">\
  				<span class="glyphicon glyphicon-th">PRODUCT_SUM</span>\
  				<span class="glyphicon glyphicon-heart">COLLECT_SUM</span>\
  		</div>\
  	</div>\
  	<div class="grid-article-bd">\
  		<h1>COLLECT_NAME</h1>\
  		<span class="smart-date" data-origindate="2015-12-06 09:15:35 +0800">11 小时前</span>\
  	</div>\
  </a>';
  
  $.ajax({
    url : url, // 跳转到 action    
    type : 'post',
    beforeSend:function(){
      
    },
    cache : false,
    dataType : 'json',
    success : function(data) {
    
    var objs = data.collects;
    $.each(objs, function(i, obj){
      var item = html.replace("COLLECT_NAME", obj.collectName)
      				 .replace("COVER_IMG_PATH", obj.coverImgPath)
      				 .replace("COLLECT_ID", obj.collectid)
      				 .replace("PRODUCT_SUM", obj.productSum)
      				 .replace("COLLECT_SUM", obj.collectSum);
      $("#item-content").append(item);
    });
    },
    error : function() {
      alert("异常！");
    }
  });
});
</script>
</body>
</html>
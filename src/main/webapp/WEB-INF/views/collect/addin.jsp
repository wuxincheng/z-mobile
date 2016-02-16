<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<link data-turbolinks-track="true" href="${root}/assets/css/style.css" media="all" rel="stylesheet">
<link data-turbolinks-track="true" href="${root}/assets/css/application.css" media="all" rel="stylesheet">
<script data-turbolinks-track="true" src="${root}/assets/js/application.js"></script>

</head>
<body style="margin-bottom: 0px;">

  <div class="content row">
    <div style="float: left; text-align: left; width: 50%;">
      <div style="padding: 20px;">
        <div style="margin-top: -10px; margin-bottom: 10px;">请选择榜单：</div>
        <div class="collect-addin">
          <div class="items" style="left: 0px; display: block;">
            <ul>
              <c:forEach items="${collects}" var="collect" varStatus="s">
                <li><a href="javascript:addin('${collect.collectName}', '${collect.collectid}');" ><span class="name">${collect.collectName}</span></a></li>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div style="float: right; text-align: left; width: 50%; border-left: 0px solid #ccc;">
      <div style="padding-left: 20px;"><div class="fund-name"></div></div>
      <div style="padding: 0px 20px;">
        <form accept-charset="UTF-8" action="${root}/product/doPost" id="addin-form" name="addin-form" class="simple_form comment" style="border: 0;" method="post">
          <div class="form-group string required user_nickname">
            <label class="string required" for="user_nickname">已经选择榜单：</label>
            <input type="hidden" id="collectid" name="collectid" />
            <div class="selected-collect-name" style="font-weight: bold; color: #C00; font-size: 16px;"></div>
          </div>
          <input type="hidden" name="fundCode" id="fundCode" value="${fundCode}" />
          <input type="hidden" name="fundName" id="fundName" value="${fundName}" />
          <input type="hidden" name="likeState" id="likeState" value="0" />
          <label class="string required" for="user_nickname">那就说两句吧：</label>
          <textarea class="input-big form-control" name="memo" id="memo" onblur="cleartip();" oninput="cleartip();"></textarea>
          <a class="btn submit" id="commit" name="commit" href="#" onclick="submitaddin();" style="padding: 5px 15px;">加入</a>&nbsp;&nbsp;
          <label id="addintip" style="color: #C00;">&nbsp;</label>
        </form>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
function submitaddin(){
	var collectName = $("#collectid").val();
	if ('' == collectName) {
		$("#addintip").html('您还没有选择榜单');
		return;
	}
	
	var comment = $("#memo").val();
	if ('' == comment) {
		$("#addintip").html('您还没有评论');
		return;
	}
	
	$("#addin-form").submit();
}

function cleartip(){
	$("#addintip").empty();
}

function addin(collectName, collectid){
	$(".selected-collect-name").html(collectName);
	$("#collectid").attr("value", collectid);
	$("#addintip").empty();
}

</script>
</html>
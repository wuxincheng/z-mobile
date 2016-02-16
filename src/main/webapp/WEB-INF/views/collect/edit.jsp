<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>编辑榜单信息 - 赚了没？</title>

<script src="${root}/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="${root}/ckfinder/ckfinder.js" type="text/javascript"></script>

<link rel="stylesheet" href="${root}/assets/vendor/croppic/croppic.css" />
  
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />
<script type="text/javascript">
    window.onload = function() {
      var editor = CKEDITOR.replace('detailContent');
      CKFinder.setupCKEditor(editor,'/ckeditor/');
    };
</script>
</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  <div class="container">

    <div class="content row row cf">
      <div class="collect-form-panel">
        <form accept-charset="UTF-8" action="${root}/collect/create" method="post">
          <input type="hidden" id="collectid" name="collectid" value="${collect.collectid}" />
          <div>
            <label>榜单背景图片：（建议：670*350）</label>
            <input type="hidden" name="coverImgPath" id="coverImgPath" value="${collect.coverImgPath}" />
            <div style="width: 670px; height: 350px; overflow: hidden;" class="control-img cropContainer"
              id="coverImgPathCrop">
              <c:if test="${not empty collect.coverImgPath}">
              <img src="${collect.coverImgPath}" />
              </c:if>
            </div>
          </div>
          <div style="margin-top: 10px;">
            <label>榜单名称：</label>
            <input id="collectName" name="collectName" value="${collect.collectName}" 
                placeholder="榜单名称" class="form-control" type="text" />
          </div>
          
          <!-- 
          <div class="text required note_summary">
            <label class="text required" for="note_summary">一句话介绍：</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="memo" name="memo" required="required" style="min-height: 50px;"></textarea>
          </div>
           -->
          <div>
            <label>内容介绍：</label>
            <textarea class="form-control" id="recommend" name="recommend" 
                placeholder="内容介绍">${collect.recommend}</textarea>
          </div>
          
          <div style="margin-top: 10px;">
            <label class="detailContent">内容详细：</label>
            <textarea id="detailContent" name="detailContent">${collect.detailContent}</textarea>
          </div>
          <p>&nbsp;</p>
          <input class="btn submit" name="commit" type="button" value="提交" onclick="doSubmit();" />
        </form>
      </div>
    </div>
  </div>

  <jsp:include page="../FOOTER.jsp" />
  <script src="${root}/assets/vendor/croppic/croppic.min.js" type="text/javascript"></script>
  <script type="text/javascript">
    var cropOptions = {
      uploadUrl : '${root}' + '/image/upload',
      cropUrl : '${root}' + '/image/crop',
      imgEyecandy : false,
      rotateControls: false,
      modal:false,
      doubleZoomControls:false,
      loaderHtml : '<div class="loader bubblingG"><span id="bubblingG_1"></span><span id="bubblingG_2"></span><span id="bubblingG_3"></span></div>',
    };
    cropOptions.outputUrlId = "coverImgPath";
    var backgroundCrop = new Croppic('coverImgPathCrop', cropOptions);
  </script>
  
  <script type="text/javascript">
  function doSubmit() {
	var dc= CKEDITOR.instances.detailContent.getData();
	$("#detailContent").val(dc);
    var form_data = $('form').serialize();
    $.ajax( {
      type : "POST",
          url : '${root}'+"/collect/create",
          data : form_data,
          dataType : "json",
          success : function(response){
            if (!response.success) {
              layer.alert(response.errorMsg);
              return;
            }
            if (response.redirectUrl) {
            	layer.confirm("榜单信息编辑成功", {
    			    btn: ['确定'],
    			    closeBtn: 0
    			}, function(){
    				window.location = '${root}'+response.redirectUrl;
    			});
            }
          },
          error : function(response){
        	  window.top.location.reload();
          }
      });
  }
  </script>  
</body>
</html>
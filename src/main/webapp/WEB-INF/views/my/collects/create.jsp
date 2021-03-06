<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>赚了没 | 创建榜单</title>

<script src="${root}/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="${root}/ckfinder/ckfinder.js" type="text/javascript"></script>
  
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
  <jsp:include page="../../HEADER.jsp" />
  <div class="container">

    <div class="content row row cf">
      <div class="collect-form-panel">
        <form accept-charset="UTF-8" action="${root}/collect/create" class="simple_form new_note"
          method="post" enctype="multipart/form-data">
          <div style="display: none">
            <input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token"
              type="hidden" value="UPiDrTAjt2KU/pN2Pk67fOAFuScLNlZ77yx8CJ+TfSE=" />
          </div>

          <div class="hidden note_source">
            <input class="hidden form-control" id="note_source" name="note[source]" type="hidden"
              value="direct" />
          </div>
          <div class="string required note_title">
            <input type="hidden" id="userid" name="userid" value="${userid}" />
            <label class="string required" for="note_title">榜单名称：</label><input aria-required="true"
              autofocus="autofocus" class="string required form-control input-small" id="collectName"
              name="collectName" placeholder="榜单名称" required="required" type="text" />
          </div>
          <div class="string required note_title">
            <label class="string required" for="note_title">榜单背景图片：（建议：625*350）</label>
            <input autofocus="autofocus" class="form-control input-small" 
              id="coverImgFile" name="coverImgFile" type="file" />
          </div>
          <!-- 
          <div class="text required note_summary">
            <label class="text required" for="note_summary">一句话介绍：</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="memo" name="memo" required="required" style="min-height: 50px;"></textarea>
          </div>
           -->
          <div class="text required note_summary">
            <label class="text required" for="note_summary">内容介绍：</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="recommend" name="recommend" required="required"></textarea>
          </div>
          
          <div>
            <label class="detailContent">内容详细：</label>
            <textarea id="detailContent" name="detailContent"></textarea>
          </div>
          <p>&nbsp;</p>
          <input class="btn submit" name="commit" type="button" value="提交" onclick="doSubmit();" />
        </form>
      </div>

      <aside class="aside">
        <p>&nbsp;</p>
      </aside>

    </div>
  </div>

  <jsp:include page="../../FOOTER.jsp" />
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
              window.location = '${root}'+response.redirectUrl;
            }
            layer.msg("创建成功");
          },
          error : function(response){
        	  window.top.location.reload();
          }
      });
  }
  </script>  
</body>
</html>
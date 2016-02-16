<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>编辑榜单信息 | TOP</title>

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <div class="container">

    <jsp:include page="../../HEADER.jsp" />

    <div class="content row row cf">
      <div class="forms">
        <form accept-charset="UTF-8" action="${root}/my/collects/modify" class="simple_form new_note"
          method="post" enctype="multipart/form-data">
          <div class="form-group string required note_title">
            <input type="hidden" id="userid" name="userid" value="${userid}" />
            <label class="string required" for="note_title">榜单名称</label><input aria-required="true"
              autofocus="autofocus" class="string required form-control input-small" id="collectName"
              name="collectName" value="${collect.collectName}" placeholder="榜单名称" required="required" type="text" />
          </div>
          <div class="form-group string">
            <input type="hidden" id="collectid" name="collectid" value="${collect.collectid}" />
            <input type="hidden" id="coverImgPathHidden" name="coverImgPathHidden" value="${collect.coverImgPath}" />
            <label class="string" for="note_title">榜单背景图片（建议：625*350）</label>
            <input autofocus="autofocus" class="form-control input-small" 
              id="coverImgFile" name="coverImgFile" type="file" onclick="changeCoverImg(this)" />
          </div>
          <div class="form-group string">
            <input type="hidden" id="coverImgPath" name="coverImgPath" value="${collect.coverImgPath}" />
            <c:if test="${not empty collect.coverImgPath}">
            <img src="${root}/collect/coverbg/${collect.coverImgPath}" style="width: 300px; height: 168px;" />
            </c:if>
          </div>
          <div class="form-group text required note_summary">
            <label class="text required" for="note_summary">一句话介绍</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="memo" name="memo" required="required">${collect.memo}</textarea>
            <p class="help-block">请精简描述, 36字以内</p>
          </div>
          <div class="form-group text required note_summary">
            <label class="text required" for="note_summary">内容介绍</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="recommend" name="recommend" required="required">${collect.recommend}</textarea>
          </div>

          <input class="btn submit" name="commit" type="submit" value="提交" />
        </form>
      </div>

      <aside class="aside">
        <p>&nbsp;</p>
      </aside>

    </div>
  </div>

  <jsp:include page="../../FOOTER.jsp" />
  <script type="text/javascript">
  	function changeCoverImg(file){
  		$("#coverImgPath").val(null);
  	}
  </script>
</body>
</html>
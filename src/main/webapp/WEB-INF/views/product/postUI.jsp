<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  <div class="container">
    <div class="content row row cf">
      <div class="forms">
        <form accept-charset="UTF-8" action="${root}/product/doPost" class="simple_form new_note" id="new_note"
          method="post">
          <div style="display: none">
            <input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token"
              type="hidden" value="UPiDrTAjt2KU/pN2Pk67fOAFuScLNlZ77yx8CJ+TfSE=" />
          </div>

          <div class="form-group hidden note_source">
            <input class="hidden form-control" id="note_source" name="note[source]" type="hidden"
              value="direct" />
          </div>
          <div class="form-group string required note_title">
            <label class="string required" for="note_title">产品名称</label>
            <input type="hidden" id="fundCode" name="fundCode" />
            <input aria-required="true" autofocus="autofocus" class="string required form-control input-small" id="fundName"
              name="fundName" placeholder="输入基金代码或名称" required="required" type="text" autocomplete="off" />
            <div class="fundPanel">
              <div class="items"></div>
            </div>
          </div>
          <!-- 
          <div class="form-group url required note_url">
            <label class="url required" for="note_url">产品官网</label><input aria-required="true"
              class="string url required form-control input-small" id="prodUrl" name="prodUrl"
              placeholder="以 http:// 开头的有效网址" required="required" type="url" />
          </div>
           -->
          <div class="form-group text required note_summary">
            <label class="text required" for="note_summary">一句话描述这个产品</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="memo" name="memo" required="required"></textarea>
            <p class="help-block">请精简描述, 36字以内</p>
          </div>
          <input type="hidden" name="collectid" id="collectid" value="${collectid}" />
          <input class="btn submit" name="commit" type="submit" value="提交" />
        </form>
      </div>


      <aside class="aside">
        <p>&nbsp;</p>
      </aside>

    </div>
  </div>

  <script type="text/javascript">
    $('#fundName').bind('input propertychange', function() {
      $(".fundPanel .items").css("left", "0px");
      
      $("#fundName").bind("focus", function() {
        var time = setInterval(filter_staff_from_exist, 100);
        $(this).bind("blur", function() {
          clearInterval(time);
        });
      });

      $(".fundPanel .items").on("click", "a", function() {
        var fundCode = $(this).attr("fundCode");
        var fundName = $(this).find(".fn").text();
        $("[name=fundCode]").val(fundCode);
        $("#fundName").val(fundName);
        str = fundName;
        $(".fundPanel .items").hide();
        $("#fundClear").show();
      });

      $(".fundPanel .items").on("mouseover", "a", function() {
        var link = $(this);
        $(".fundPanel .items a").each(function(i, o) {
          if ($(o).is(link)) {
            fundIndex = i;
            return;
          }
        });

        $(".fundPanel .items a").removeClass("hover");
        $(this).addClass("hover").focus();
      });

      var funcKey = function(i) {
        var fundCount = $(".fundPanel .items a").length;
        if (fundIndex < 0) {
          fundIndex = 0;
        } else {
          fundIndex += i;
          if (fundIndex < 0) {
            fundIndex = 0;
          }
          if (fundIndex >= fundCount) {
            fundIndex = 0;
          }
        }

        $(".fundPanel .items a").removeClass("hover");
        $(".fundPanel .items a").eq(fundIndex).addClass("hover").focus();
      };

      $("#fundClear").click(function() {
        $("#fundName").val("");
        $("[name=fundCode]").val("");
        $(".fundPanel .items").hide().empty();
        str = "";
        $("#fundClear").hide();
      });
      
      $("#fundName").keydown(function(e) {
        switch (e.which) {
        case 40://下
          funcKey(2);
          return false;
        }
      });

      $(".fundPanel .items").keydown(function(e) {
        switch (e.which) {
        case 37://左
          funcKey(-1);
          return false;
        case 38://上
          funcKey(-1);
          return false;
        case 39://右
          funcKey(1);
          return false;
        case 40://下
          funcKey(1);
          return false;
        }
      });
      
      var keyword = $("#fundName").val();
      if (keyword != null && keyword.length >= 2) {
    	  var url = "${root}/fund/info/query";
          $.ajax({
            url : url, // 跳转到 action
            data : {keyword:keyword},
            type : 'post',
            beforeSend:function(){
            },
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.length > 0) {
                	$(".fundPanel .items").hide().empty();
                	
                	$(".fundPanel .items").append("<ul></ul>");
                	$.each(data, function(i, n) {
                        var fundName = n.fundName.replace(keyword, "<span class='selected'>" + keyword
                          + "</span>");
                        var fundCode = n.fundCode.replace(keyword, "<span class='selected'>" + keyword
                          + "</span>");
                        $(".fundPanel .items ul").append(
                          "<li><a href='javascript:void(0)' fundCode='" + n.fundCode
                            + "' hidefocus='true'><span class='fn'>" + fundName
                            + "</span><span class='fc'>" + fundCode + "</span></a></li>");
                      });
                    $(".fundPanel .items").show();
                }
            },
            error : function() {
              alert("友情提示：系统异常，请重试!");
            }
          });
      }
    });
  </script>

  <jsp:include page="../FOOTER.jsp" />

</body>
</html>
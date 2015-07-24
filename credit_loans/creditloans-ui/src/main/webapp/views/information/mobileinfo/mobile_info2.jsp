<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
	$("#password").blur(function(){
		if ($.trim($("#password").val()) != "") {
			checkMobile();
		}
	});
});

//手机号码验证
function checkMobile() {
	//$("#mobile_info2").submit();
	var mobileProp = $('#mobile_info2').serializeObject();
	jQuery.ajax({
		type: 'POST',
        url: "${pageContext.request.contextPath}/mobileInformation/showMobileLoginValidate",
        contentType: "application/json",
	    data: JSON.stringify(mobileProp),
        success: function (data) {
        	if (data.length != 0) {
	        	if (data.url != null && data.url != "") {
		        	$("#imgDiv").css("display", "block")
		        	$("#validateImage").attr("src", data.url);
	        	}
	        	$("#next").removeAttr("disabled");
        	}
        },
        error: function (data) {
        }
	});
}

</script>
<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">信息查看</a></li>
			<li class="active">手机信息查看</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				信息查看<small> <i class="icon-double-angle-right"></i>
					手机信息查看
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form class="form-horizontal" id="mobile_info2" action="${pageContext.request.contextPath}/mobileInformation/showMobileResult" method="post">
						<fieldset>
							<input type="hidden" name="mobile" value="${mobilePhoneNo }">
							<input type="hidden" name="isp" value="${isp }">
							<input type="hidden" name="province" value="${province }">
							<input type="hidden" name="cityName" value="${cityName }">
							<div class="form-group">
								手机号码：<span>${mobilePhoneNo }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								运营商：
								<c:choose>
								    <c:when test="${isp == ''}">
								      <span>未知</span>
								    </c:when>
									<c:otherwise>  
								      <span>${isp }</span>
								   	</c:otherwise>
							  	</c:choose>
							  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								归属地：
								<c:choose>
								    <c:when test="${province == ''}">
								      <span>未知</span>
								    </c:when>
									<c:otherwise>  
								      <span>${province }</span>&nbsp;(省/市)
								   	</c:otherwise>
							  	</c:choose>
							  	<c:if test="${cityName != ''}">
							  		<span>${cityName }</span>&nbsp;(市/县)
							  	</c:if>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">服务密码：</label>
								<div class="col-sm-3">
									<input type="password" id="password" name="password" class="form-control" placeholder="输入服务密码" />
								</div>
								<span style="color: red" class="col-sm-2">${loginInfo }</span>
							</div>
							<div class="form-group" id="imgDiv" style="display: none;">
								<div class="col-sm-3">
									<input type="text" id="imagCaptcha" name="imagCaptcha" class="form-control" placeholder="输入验证码" />
									&nbsp;&nbsp;
									<img alt="" src="" id="validateImage" onclick="checkMobile();">
								</div>
							</div>
							<div class="form-group">
								<a class="btn btn-primary" href="javascript:history.go(-1);">上一步</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn btn-primary" disabled="disabled" id="next">下一步</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>

		</div>
		<!-- class:row -->

	</div>
	<!-- /.page-content -->

</div>
<!-- /.main-content -->
<%@ include file="./../../common/footer.jsp"%>
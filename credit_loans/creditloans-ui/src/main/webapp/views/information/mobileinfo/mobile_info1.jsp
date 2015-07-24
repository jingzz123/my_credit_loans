<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

//手机号码验证
function checkMobile() {
	var mobile = $("#mobilePhoneNo").val();
	if (mobile == null || "" == mobile) {
		$("#error_mobile").html("手机号码为空");
		return;
	}
	if (!/^1[3|4|5|7|8][0-9]\d{8}$/.test(mobile)) { 
		$("#error_mobile").html("手机格式不正确");
		return;
	}
	$("#mobile_info1").submit();
	/**
	jQuery.ajax({
		type: 'POST',
        url: "http://virtual.paipai.com/extinfo/GetMobileProductInfo?mobile=" + mobile + "&amount=10000&callname=getPhoneNumInfoExtCallback",
		contentType: "application/json",
        data: null,
        success: function (result) {
        	// 获取省份
        	var province = result.province;
        	// 获取城市
        	var cityname = result.cityname;
        	// 获取运营商
        	var isp = result.isp;
        	window.location.href = "${pageContext.request.contextPath}/mobileInformation/getMobileType?province=" 
        			+ province + "&cityname=" + cityname + "&mobile=" + mobile + "&isp=" + isp;
        },
        error: function (data) {
        }
	});
    */
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
					<form class="form-horizontal" id="mobile_info1" action="${pageContext.request.contextPath}/mobileInformation/getMobileType" method="post">
						<fieldset>
							<div class="form-group">
								<input type="text" name="mobilePhoneNo" id="mobilePhoneNo" placeholder="输入手机号"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-primary" onclick="checkMobile();" value="下一步"/>
							</div>
							<div class="form-group">
								<span id="error_mobile" style="color: red" ></span>
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
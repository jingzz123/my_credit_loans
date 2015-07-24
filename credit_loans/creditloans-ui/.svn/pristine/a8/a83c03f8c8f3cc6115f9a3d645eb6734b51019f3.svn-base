<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">P2P贷款业务</a></li>
			<li class="active">贷款业务信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				贷款业务信息<small> <i class="icon-double-angle-right"></i>
					修改投资人信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="tzrxxInfoForm" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="tzrxm" class="col-sm-1 control-label">投资人姓名</label>
								<div class="col-sm-3">
									<input type="text" id="tzrxm" name="tzrxm" class="form-control"
										placeholder="输入投资人姓名" value="${p2pTzrxxDto.tzrxm}">
								</div>
								<span id="error_tzrxm" style="color: red" class="col-sm-2">*</span>								
								<label for="tzrzjlx" class="col-sm-1 control-label">投资人证件类型</label>
								<div class="col-sm-3">
									<select id="tzrzjlx_select" name="tzrzjlx" class="form-control" onchange="removeTzrzjhm();">
										<option value="">- 选择投资人证件类型 -</option>
										<option value="0">身份证</option>
									</select>
								</div>
								<span id="error_tzrzjlx" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="tzrzjhm" class="col-sm-1 control-label">投资人证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="tzrzjhm" name="tzrzjhm" class="form-control"
										placeholder="输入投资人证件号码" value="${p2pTzrxxDto.tzrzjhm}">
								</div>
								<span id="error_tzrzjhm" style="color: red" class="col-sm-2">*</span>
								<label for="tzrtzje" class="col-sm-1 control-label">投资人投资金额</label>
								<div class="col-sm-3">
									<input type="text" id="tzrtzje" name="tzrtzje" class="form-control"
										placeholder="输入投资人投资金额" value="${p2pTzrxxDto.tzrtzje}">
								</div>
								<span id="error_tzrtzje" style="color: red" class="col-sm-2">*</span>
							</div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">
							      	<c:if test="${p2pTzrxxDto.status == 1}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="2"/> 停止
								    </c:if>
								    <c:if test="${p2pTzrxxDto.status == 2}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="2"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						
						<input type="hidden" id="oldTzrzjhm" name="oldTzrzjhm" value="${p2pTzrxxDto.tzrzjhm}">
						<input type="hidden" id="dkjbxxId" name="dkjbxxId" value="${p2pTzrxxDto.dkjbxxId}">
						<input type="hidden" id="orgCode" name="orgCode" value="${p2pTzrxxDto.orgCode}">
						<input type="hidden" name="id" value="${p2pTzrxxDto.id}">
						<div class="form-group">
							<div class="col-sm-offset-5">
								<input type="submit" class="btn btn-primary" value="保存"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-success" value="返回"
									onclick="goback()"/>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
		<!-- class:row -->

	</div>
	<!-- /.page-content -->

</div>
<!-- /.main-content -->
<script type="text/javascript">
$().ready(function() {
	loadSelect();
	$("#tzrxxInfoForm").validate({
	    onkeyup: false,
	    rules: {
			tzrxm:{
				required:true,
				rangelength:[2,15],
				isChinese:true
			},
			tzrzjlx:{
				required:true
			},
			tzrzjhm:{
				required:true,
				isIdCardNo:true,
				remote:{
					url:"${pageContext.request.contextPath}/p2p/tzrxx/checkP2PTzrxxIsOnly",
					type: "post",
				    dataType: "json",
				    data: {
				    	tzrzjlx: function() {
				            return $("#tzrzjlx_select").val();
				        },
				        tzrzjhm: function() {
				            return $("#tzrzjhm").val();
				        },
				        dkjbxxId: function(){
				        	return $("#dkjbxxId").val();
				        },
				        orgCode: function() {
				            return $("#orgCode").val();
				        },
				        oldTzrzjhm: function(){
				        	return $("#oldTzrzjhm").val();
				        }
				    }
				}
			},
			tzrtzje:{
				required:true,
				digits:true,
				isIntGtZero:true,
				maxlength:10
			}
		},
		messages:{
			tzrxm:{
				required:"请输入投资人姓名",
				rangelength:"投资人姓名长度为2~15个中文",
				isChinese:"投资人姓名必须为中文"
			},
			tzrzjlx:{
				required:"请选择投资人证件类型"
			},
			tzrzjhm:{
				required:"请输入投资人证件号码",
				isIdCardNo:"请输入正确的身份证号码",
				remote:"当前贷款信息中该投资人已存在"
			},
			tzrtzje:{
				required:"请输入投资金额",
				digits:"投资金额应为大于0的整数",
				isIntGtZero:"投资金额应为大于0的整数",
				maxlength:"投资金额长度不能超过10位"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var tzrxxInfoForm = $('#tzrxxInfoForm').serializeObject();
			delete tzrxxInfoForm.oldTzrzjhm;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/p2p/tzrxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(tzrxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/p2p/tzrxx/showList?dkjbxxId=${p2pTzrxxDto.dkjbxxId}";
			    },
			    error: function () {
			        alert("edit tzrxx error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/p2p/tzrxx/showList?dkjbxxId=${p2pTzrxxDto.dkjbxxId}";
}

//临时处理，后面不用这种方式
function loadSelect(){
	var tzrzjlx = "${p2pTzrxxDto.tzrzjlx}";
	
	$("#tzrzjlx_select").find("option[value="+tzrzjlx+"]").attr("selected",true);
}

function removeTzrzjhm(){
	$("#tzrzjhm").removeData("previousValue");
}

    $('.form_date').datetimepicker({
    	format: "yyyymmdd",
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
</script>
<%@ include file="./../../common/footer.jsp"%>
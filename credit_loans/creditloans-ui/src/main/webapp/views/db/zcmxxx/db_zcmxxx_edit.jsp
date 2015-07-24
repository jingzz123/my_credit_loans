<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
	$("#zcmxxxInfoForm").validate({
		onkeyup: false,
		rules: {
			zcrq:{
				required:true
			},
			zcje:{
				required:true,
				isFloat:true,
				maxlength:[20]
			}
		},
		messages:{
			zcrq:{
				required:"请输入追偿日期"
			},
			zcje:{
				required:"请输入追偿金额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"追偿金额不能超过20位"
			}
		}, 
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var zcmxxxInfoForm = $('#zcmxxxInfoForm').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/db/zcmxxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(zcmxxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/db/zcmxxx/showList?dbhtxxId=${dbZcmxxxDTO.dbhtxxId}";
			    },
			    error: function () {
			        alert("edit zcmxxx error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/db/zcmxxx/showList?dbhtxxId=${dbZcmxxxDTO.dbhtxxId}";
}
</script>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">担保业务</a></li>
			<li class="active">担保业务信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				担保业务信息<small> <i class="icon-double-angle-right"></i>
					修改追偿明细信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="zcmxxxInfoForm" class="form-horizontal">
						<fieldset>
							<legend style="font-size: 16px; color: gray;">基础信息</legend>
							<div class="form-group">
							<label for="zcrq" class="col-sm-1 control-label">追偿日期</label>
							<div class="col-sm-3">
								<div class="input-group date form_date col-md-3 form-control"
									style="padding: 0px; margin: 0px;">
									<input class="form-control" id="zcrq" name="zcrq" type="text"
										placeholder="追偿日期" readonly="readonly" value="${dbZcmxxxDTO.zcrq}"> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove icon-remove"></span></span>
								</div>
							</div>
							<span id="error_dcrq" style="color: red" class="col-sm-2"></span>
						  	<label for="zcje" class="col-sm-1 control-label">追偿金额</label>
							<div class="col-sm-3">
								<input type="text" id="zcje" name="zcje" class="form-control"
									placeholder="输入追偿金额" value="${dbZcmxxxDTO.zcje}">
							</div>
							<span id="error_zcje" style="color: red" class="col-sm-2"></span>
						  </div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">
							      	<c:if test="${dbZcmxxxDTO.status == 0}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="0"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="1"/> 停止
								    </c:if>
								    <c:if test="${dbZcmxxxDTO.status == 1}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="0"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" name="orgCode" value="${dbZcmxxxDTO.orgCode}">
						<input type="hidden" name="dbhtxxId" value="${dbZcmxxxDTO.dbhtxxId}">
						<input type="hidden" name="id" value="${dbZcmxxxDTO.id}">
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
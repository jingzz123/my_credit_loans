<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
	loadSelect();
	$("#bfjnmxxxInfoForm").validate({
		onkeyup: false,
		rules: {
			yjrq:{
				required:true
			},
			yjje:{
				required:true,
				isFloat:true,
				maxlength:[20]
			},
			sjrq:{
				required:true
			},
			qjje:{
				required:true,
				isFloat:true,
				maxlength:[20]
			},
			bqbfjnzt:{
				required:true
			}
		},
		messages:{
			yjrq:{
				required:"请输入应缴日期"
			},
			yjje:{
				required:"请输入应缴金额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"应缴金额不能超过20位"
			},
			sjrq:{
				required:"请输入实缴日期"
			},
			qjje:{
				required:"请输入欠缴金额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"欠缴金额不能超过20位"
			},
			bqbfjnzt:{
				required:"请输入本期保费缴纳状态"
			}
		}, 
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var bfjnmxxxInfoForm = $('#bfjnmxxxInfoForm').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/db/bfjnmxxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(bfjnmxxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/db/bfjnmxxx/showList?dbhtxxId=${dbBfjnmxxxDTO.dbhtxxId}";
			    },
			    error: function () {
			        alert("edit bfjnmxxx error");
			    }	
			});
		}
	});

});

function loadSelect(){	
	var bqbfjnzt = "${dbBfjnmxxxDTO.bqbfjnzt}";
	
	$("#bqbfjnzt_select").find("option[value="+bqbfjnzt+"]").attr("selected", true);
}

function goback(){
	window.location.href = "${pageContext.request.contextPath}/db/bfjnmxxx/showList?dbhtxxId=${dbBfjnmxxxDTO.dbhtxxId}";
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
					修改保费缴纳明细信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="bfjnmxxxInfoForm" class="form-horizontal">
						<fieldset>
							<legend style="font-size: 16px; color: gray;">基础信息</legend>
							<div class="form-group">
							<label for="yjrq" class="col-sm-1 control-label">应缴日期</label>
							<div class="col-sm-3">
								<div class="input-group date form_date col-md-3 form-control"
									style="padding: 0px; margin: 0px;">
									<input class="form-control" id="yjrq" name="yjrq" type="text"
										placeholder="追偿日期" readonly="readonly" value="${dbBfjnmxxxDTO.yjrq}"> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove icon-remove"></span></span>
								</div>
							</div>
							<span id="error_yjrq" style="color: red" class="col-sm-2"></span>
						  	<label for="yjje" class="col-sm-1 control-label">应缴金额</label>
							<div class="col-sm-3">
								<input type="text" id="yjje" name="yjje" class="form-control"
									placeholder="输入应缴金额" value="${dbBfjnmxxxDTO.yjje}">
							</div>
							<span id="error_yjje" style="color: red" class="col-sm-2"></span>
						  </div>
							<div class="form-group">
							<label for="sjrq" class="col-sm-1 control-label">实缴日期</label>
							<div class="col-sm-3">
								<div class="input-group date form_date col-md-3 form-control"
									style="padding: 0px; margin: 0px;">
									<input class="form-control" id="sjrq" name="sjrq" type="text"
										placeholder="实缴日期" readonly="readonly" value="${dbBfjnmxxxDTO.sjrq}"> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove icon-remove"></span></span>
								</div>
							</div>
							<span id="error_sjrq" style="color: red" class="col-sm-2"></span>
						  	<label for="qjje" class="col-sm-1 control-label">欠缴金额</label>
							<div class="col-sm-3">
								<input type="text" id="qjje" name="qjje" class="form-control"
									placeholder="输入欠缴金额" value="${dbBfjnmxxxDTO.qjje}">
							</div>
							<span id="error_qjje" style="color: red" class="col-sm-2"></span>
						  </div>
						  <div class="form-group">
								<label for="bqbfjnzt" class="col-sm-1 control-label">本期保费缴纳状态</label>
								<div class="col-sm-3">
									<select id="bqbfjnzt_select" name="bqbfjnzt" class="form-control">
										<option value="">- 本期保费缴纳状态 -</option>
										<option value="00">正常</option>
										<option value="01">欠缴</option>
										<option value="02">欠缴2期后缴清</option>
										<option value="99">欠缴后缴清</option>
									</select>
								</div>
								<span id="error_bqbfjnzt" style="color: red" class="col-sm-2"></span>
							  </div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">
							      	<c:if test="${dbBfjnmxxxDTO.status == 0}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="0"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="1"/> 停止
								    </c:if>
								    <c:if test="${dbBfjnmxxxDTO.status == 1}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="0"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" name="orgCode" value="${dbBfjnmxxxDTO.orgCode}">
						<input type="hidden" name="dbhtxxId" value="${dbBfjnmxxxDTO.dbhtxxId}">
						<input type="hidden" name="id" value="${dbBfjnmxxxDTO.id}">
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
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
	loadSelect();
	$("#fdbrxxInfoForm").validate({
		onkeyup: false,
		rules: {
			fdbrlx:{
				required:true
			},
			fdbrmc:{
				required:true,
				maxlength:[80]
			},
			fdbrzjlx:{
				required:true
			},
			fdbrzjhm:{
				required:true,
				maxlength:[18]
			},
			fdbzrje:{
				required:true,
				isFloat:true,
				maxlength:[20]
			},
			ztw:{
				required:true
			}
		},
		messages:{
			fdbrlx:{
				required:"请输入反担保人类型"
			},
			fdbrmc:{
				required:"请输入反担保人名称",
				maxlength:"反担保人名称不能超过80位"
			},
			fdbrzjlx:{
				required:"请输入反担保人证件类型"
			},
			fdbrzjhm:{
				required:"请输入反担保人证件号码",
				maxlength:"反担保人证件号码不能超过18位"
			},
			fdbzrje:{
				required:"请输入反担保责任金额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"反担保责任金额不能超过20位"
			},
			ztw:{
				required:"请输入状态位"
			}
		}, 
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var fdbrxxInfoForm = $('#fdbrxxInfoForm').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/db/fdbrxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(fdbrxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/db/fdbrxx/showList?dbhtxxId=${dbFdbrxxDTO.dbhtxxId}";
			    },
			    error: function () {
			        alert("edit fdbrxx error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/db/fdbrxx/showList?dbhtxxId=${dbFdbrxxDTO.dbhtxxId}";
}
//临时处理，后面不用这种方式
function loadSelect(){	
	var fdbrlx = "${dbFdbrxxDTO.fdbrlx}";
	var fdbrzjlx = "${dbFdbrxxDTO.fdbrzjlx}";
	var ztw = "${dbFdbrxxDTO.ztw}";
	
	$("#fdbrlx_select").find("option[value="+fdbrlx+"]").attr("selected",true);
	$("#fdbrzjlx_select").find("option[value="+fdbrzjlx+"]").attr("selected",true);
	$("#ztw_select").find("option[value="+ztw+"]").attr("selected", true);
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
					修改反担保人信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="fdbrxxInfoForm" class="form-horizontal">
						<fieldset>
							<legend style="font-size: 16px; color: gray;">基础信息</legend>
							<div class="form-group">
								<label for="fdbrlx" class="col-sm-1 control-label">反担保人类型</label>
								<div class="col-sm-3">
									<select id="fdbrlx_select" name="fdbrlx" class="form-control">
										<option value="">- 反担保人类型 -</option>
										<option value="1">企业或其他组织</option>
										<option value="2">自然人</option>
									</select>
								</div>
								<span id="error_fdbrlx" style="color: red" class="col-sm-2"></span>
								<label for="fdbrmc" class="col-sm-1 control-label">反担保人名称</label>
								<div class="col-sm-3">
									<input type="text" id="fdbrmc" name="fdbrmc" class="form-control"
										placeholder="反担保人名称" value="${dbFdbrxxDTO.fdbrmc}">
								</div>
								<span id="error_fdbrmc" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								
								<label for="fdbrzjlx" class="col-sm-1 control-label">反担保人证件类型</label>
								<div class="col-sm-3">
									<select id="fdbrzjlx_select" name="fdbrzjlx" class="form-control">
										<option value="">- 反担保人证件类型 -</option>
										<option value="0">身份证</option>
										<option value="1">户口簿</option>
										<option value="2">护照</option>
										<option value="3">军官证</option>
										<option value="4">士兵证</option>
										<option value="5">港澳居民来往内地通行证</option>
										<option value="6">台湾同胞来往内地通行证</option>
										<option value="7">临时身份证</option>
										<option value="8">外国人居留证</option>
										<option value="9">警官证</option>
										<option value="a">组织机构代码证</option>
										<option value="c">贷款卡</option>
										<option value="d">机构信用代码</option>
										<option value="X">其他证件</option>
									</select>
								</div>
								<span id="error_fdbrzjlx" style="color: red" class="col-sm-2"></span>
								<label for="fdbrzjhm" class="col-sm-1 control-label">反担保人证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="fdbrzjhm" name="fdbrzjhm" class="form-control"
										placeholder="反担保人证件号码" value="${dbFdbrxxDTO.fdbrzjhm}">
								</div>
								<span id="error_fdbrzjhm" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="fdbzrje" class="col-sm-1 control-label">反担保责任金额</label>
								<div class="col-sm-3">
									<input type="text" id="fdbzrje" name="fdbzrje" class="form-control"
										placeholder="反担保责任金额" value="${dbFdbrxxDTO.fdbzrje}">
								</div>
								<span id="error_fdbzrje" style="color: red" class="col-sm-2"></span>
								<label for="ztw" class="col-sm-1 control-label">状态位</label>
								<div class="col-sm-3">
									<select id="ztw_select" name="ztw" class="form-control">
										<option value="">- 状态位 -</option>
										<option value="1">有效</option>
										<option value="2">无效</option>
									</select>
								</div>
								<span id="error_ztw" style="color: red" class="col-sm-2"></span>
								
							</div>
						</fieldset>
						
						
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">
							      	<c:if test="${dbFdbrxxDTO.status == 0}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="0"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="1"/> 停止
								    </c:if>
								    <c:if test="${dbFdbrxxDTO.status == 1}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="0"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" name="orgCode" value="${dbFdbrxxDTO.orgCode}">
						<input type="hidden" name="dbhtxxId" value="${dbFdbrxxDTO.dbhtxxId}">
						<input type="hidden" name="id" value="${dbFdbrxxDTO.id}">
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
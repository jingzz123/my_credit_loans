<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
	loadSelect();
	$("#bdbrxxInfoForm").validate({
		onkeyup: false,
		rules: {
			bdbrlx:{
				required:true
			},
			bdbrmc:{
				required:true,
				maxlength:[80]
			},
			bdbrzjlx:{
				required:true
			},
			bdbrzjhm:{
				required:true,
				maxlength:[18]
			},
			ztw:{
				required:true
			}
		},
		messages:{
			bdbrlx:{
				required:"请输入被担保人类型"
			},
			bdbrmc:{
				required:"请输入被担保人名称",
				maxlength:"被担保人名称不能超过80位"
			},
			bdbrzjlx:{
				required:"请输入被担保人证件类型"
			},
			bdbrzjhm:{
				required:"请输入被担保人证件号码",
				maxlength:"被担保人证件号码不能超过18位"
			},
			ztw:{
				required:"请输入状态位"
			}
		}, 
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var bdbrxxInfoForm = $('#bdbrxxInfoForm').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/db/bdbrxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(bdbrxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/db/bdbrxx/showList?dbhtxxId=${dbBdbrxxDTO.dbhtxxId}";
			    },
			    error: function () {
			        alert("edit bdbrxx error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/db/bdbrxx/showList?dbhtxxId=${dbBdbrxxDTO.dbhtxxId}";
}
//临时处理，后面不用这种方式
function loadSelect(){	
	var bdbrlx = "${dbBdbrxxDTO.bdbrlx}";
	var bdbrzjlx = "${dbBdbrxxDTO.bdbrzjlx}";
	var ztw = "${dbBdbrxxDTO.ztw}";
	
	$("#bdbrlx_select").find("option[value="+bdbrlx+"]").attr("selected",true);
	$("#bdbrzjlx_select").find("option[value="+bdbrzjlx+"]").attr("selected",true);
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
					修改被担保人信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="bdbrxxInfoForm" class="form-horizontal">
						<fieldset>
							<legend style="font-size: 16px; color: gray;">基础信息</legend>
							<div class="form-group">
								<label for="bdbrmc" class="col-sm-1 control-label">被担保人名称</label>
								<div class="col-sm-3">
									<input type="text" id="bdbrmc" name="bdbrmc" class="form-control"
										placeholder="被担保人名称" value="${dbBdbrxxDTO.bdbrmc }">
								</div>
								<span id="error_bdbrmc" style="color: red" class="col-sm-2"></span>
								<label for="bdbrlx" class="col-sm-1 control-label">被担保人类型</label>
								<div class="col-sm-3">
									<select id="bdbrlx_select" name="bdbrlx" class="form-control">
										<option value="">- 被担保人类型 -</option>
										<option value="1">企业或其他组织</option>
										<option value="2">自然人</option>
									</select>
								</div>
								<span id="error_bdbrlx" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								
								<label for="bdbrzjlx" class="col-sm-1 control-label">被担保人证件类型</label>
								<div class="col-sm-3">
									<select id="bdbrzjlx_select" name="bdbrzjlx" class="form-control">
										<option value="">- 被担保人证件类型 -</option>
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
										<option value="A">香港身份证</option>
										<option value="B">澳门身份证</option>
										<option value="C">台湾身份证</option>
										<option value="a">组织机构代码证</option>
										<option value="c">贷款卡</option>
										<option value="d">机构信用代码</option>
										<option value="X">其他证件</option>
									</select>
								</div>
								<span id="error_bdbrzjlx" style="color: red" class="col-sm-2"></span>
								<label for="bdbrzjhm" class="col-sm-1 control-label">被担保人证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="bdbrzjhm" name="bdbrzjhm" class="form-control"
										placeholder="被担保人证件号码" value="${dbBdbrxxDTO.bdbrzjhm }">
								</div>
								<span id="error_bdbrzjhm" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
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
							      	<c:if test="${dbBdbrxxDTO.status == 0}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="0"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="1"/> 停止
								    </c:if>
								    <c:if test="${dbBdbrxxDTO.status == 1}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="0"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" name="orgCode" value="${dbBdbrxxDTO.orgCode}">
						<input type="hidden" name="dbhtxxId" value="${dbBdbrxxDTO.dbhtxxId}">
						<input type="hidden" name="id" value="${dbBdbrxxDTO.id}">
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
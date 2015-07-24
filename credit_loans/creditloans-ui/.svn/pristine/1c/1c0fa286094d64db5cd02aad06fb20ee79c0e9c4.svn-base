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
					修改担保信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="dbxxInfoForm" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="dbrxm" class="col-sm-1 control-label">担保人姓名</label>
								<div class="col-sm-3">
									<input type="text" id="dbrxm" name="dbrxm" class="form-control"
										placeholder="输入担保人姓名" value="${p2pDbxxDto.dbrxm}">
								</div>
								<span id="error_dbrxm" style="color: red" class="col-sm-2">*</span>								
								<label for="dbrzjlx" class="col-sm-1 control-label">担保人证件类型</label>
								<div class="col-sm-3">
									<select id="dbrzjlx_select" name="dbrzjlx" class="form-control" onchange="removeDbrzjhm();">
										<option value="">- 选择担保人证件类型 -</option>
										<option value="0">身份证</option>
									</select>
								</div>
								<span id="error_dbrzjlx" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="dbrzjhm" class="col-sm-1 control-label">担保人证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="dbrzjhm" name="dbrzjhm" class="form-control"
										placeholder="输入担保人证件号码" value="${p2pDbxxDto.dbrzjhm}">
								</div>
								<span id="error_dbrzjhm" style="color: red" class="col-sm-2">*</span>
								<label for="dbje" class="col-sm-1 control-label">担保金额</label>
								<div class="col-sm-3">
									<input type="text" id="dbje" name="dbje" class="form-control"
										placeholder="输入担保金额" value="${p2pDbxxDto.dbje}">
								</div>
								<span id="error_dbje" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="dbzt" class="col-sm-1 control-label">担保状态</label>
								<div class="col-sm-3">
									<select id="dbzt_select" name="dbzt" class="form-control">
										<option value="">- 选择担保状态 -</option>
										<option value="1">担保</option>
									</select>
								</div>
								<span id="error_dbzt" style="color: red" class="col-sm-2">*</span>
							</div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">
							      	<c:if test="${p2pDbxxDto.status == 1}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="2"/> 停止
								    </c:if>
								    <c:if test="${p2pDbxxDto.status == 2}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="2"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" id="oldDbrzjhm" name="oldDbrzjhm" value="${p2pDbxxDto.dbrzjhm}">
						<input type="hidden" id="dkjbxxId" name="dkjbxxId" value="${p2pDbxxDto.dkjbxxId}">
						<input type="hidden" id="orgCode" name="orgCode" value="${p2pDbxxDto.orgCode}">
						<input type="hidden" name="id" value="${p2pDbxxDto.id}">
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
	$("#dbxxInfoForm").validate({
	    onkeyup: false,
	    rules: {
			dbrxm:{
				required:true,
				rangelength:[2,15],
				isChinese:true
			},
			dbrzjlx:{
				required:true
			},
			dbrzjhm:{
				required:true,
				isIdCardNo:true,
				remote:{
					url:"${pageContext.request.contextPath}/p2p/dbxx/checkP2PDbxxIsOnly",
					type: "post",
				    dataType: "json",
				    data: {
				    	dbrzjlx: function() {
				            return $("#dbrzjlx_select").val();
				        },
				        dbrzjhm: function() {
				            return $("#dbrzjhm").val();
				        },
				        dkjbxxId: function(){
				        	return $("#dkjbxxId").val();
				        },
				        orgCode: function() {
				            return $("#orgCode").val();
				        },
				        oldDbrzjhm: function(){
				        	return $("#oldDbrzjhm").val();
				        }
				    }
				}
			},
			dbje:{
				required:true,
				digits:true,
				isIntGtZero:true,
				maxlength:10
			},
			dbzt:{
				required:true
			}
		},
		messages:{
			dbrxm:{
				required:"请输入担保人姓名",
				rangelength:"担保人姓名长度为2~15个中文",
				isChinese:"担保人姓名必须为中文"
			},
			dbrzjlx:{
				required:"请选择担保人证件类型"
			},
			dbrzjhm:{
				required:"请输入担保人证件号码",
				isIdCardNo:"请输入正确的身份证号码",
				remote:"当前贷款信息中该担保人已存在"
			},
			dbje:{
				required:"请输入担保金额",
				digits:"担保金额应为大于0的整数",
				isIntGtZero:"担保金额应为大于0的整数",
				maxlength:"担保金额长度不能超过10位"
			},
			dbzt:{
				required:"请选择担保状态"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var dbxxInfoForm = $('#dbxxInfoForm').serializeObject();
			delete dbxxInfoForm.oldDbrzjhm;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/p2p/dbxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(dbxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/p2p/dbxx/showList?dkjbxxId=${p2pDbxxDto.dkjbxxId}";
			    },
			    error: function () {
			        alert("edit dbxx error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/p2p/dbxx/showList?dkjbxxId=${p2pDbxxDto.dkjbxxId}";
}

//临时处理，后面不用这种方式
function loadSelect(){
	var dbrzjlx = "${p2pDbxxDto.dbrzjlx}";
	var dbzt = "${p2pDbxxDto.dbzt}";
	
	$("#dbrzjlx_select").find("option[value="+dbrzjlx+"]").attr("selected",true);
	$("#dbzt_select").find("option[value="+dbzt+"]").attr("selected",true);
}

function removeDbrzjhm(){
	$("#dbrzjhm").removeData("previousValue");
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
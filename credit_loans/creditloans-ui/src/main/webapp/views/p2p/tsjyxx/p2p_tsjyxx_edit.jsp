<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">P2P贷款业务</a></li>
			<li class="active">特殊交易信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				特殊交易信息<small> <i class="icon-double-angle-right"></i>
					修改特殊交易信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="tsjyxxInfoForm" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="xm" class="col-sm-1 control-label">姓名</label>
								<div class="col-sm-3">
									<input type="text" id="xm" name="xm" class="form-control"
										placeholder="输入姓名" value="${p2pTsjyxxDto.xm}">
								</div>
								<span id="error_xm" style="color: red" class="col-sm-2"></span>								
								<label for="zjlx" class="col-sm-1 control-label">证件类型</label>
								<div class="col-sm-3">
									<select id="zjlx_select" name="zjlx" class="form-control">
										<option value="">- 选择证件类型 -</option>
										<option value="0">身份证</option>
									</select>
								</div>
								<span id="error_zjlx" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="zjhm" class="col-sm-1 control-label">证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="zjhm" name="zjhm" class="form-control"
										placeholder="输入证件号码" value="${p2pTsjyxxDto.zjhm}">
								</div>
								<span id="error_zjhm" style="color: red" class="col-sm-2"></span>
								<label for="ywh" class="col-sm-1 control-label">业务号</label>
								<div class="col-sm-3">
									<input type="text" id="ywh" name="ywh" class="form-control"
										placeholder="输入业务号" value="${p2pTsjyxxDto.ywh}">
								</div>
								<span id="error_ywh" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="tsjylx" class="col-sm-1 control-label">特殊交易类型</label>
								<div class="col-sm-3">
									<select id="tsjylx_select" name="tsjylx" class="form-control">
										<option value="">- 选择特殊交易类型 -</option>
										<option value="1">展期（延期）</option>
										<option value="2">担保人代还</option>
										<option value="3">以资抵债</option>
										<option value="4">提前还款（部分）</option>
										<option value="5">提前还款（全部）</option>
										<option value="6">长期拖欠（拖欠超过180天）</option>
										<option value="7">法律诉讼（已生效判决）</option>
										<option value="8">贷款欺诈</option>
										<option value="9">其他</option>
									</select>
								</div>
								<span id="error_tsjylx" style="color: red" class="col-sm-2"></span>
								<label for="fsrq" class="col-sm-1 control-label">发生日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="fsrq" name="fsrq" type="text"
											placeholder="发生日期" readonly="readonly" value="${p2pTsjyxxDto.fsrq}"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_fsrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="bgys" class="col-sm-1 control-label">变更月数</label>
								<div class="col-sm-3">
									<input type="text" id="bgys" name="bgys" class="form-control"
										placeholder="输入变更月数" value="${p2pTsjyxxDto.bgys}">
								</div>
								<span id="error_bgys" style="color: red" class="col-sm-2"></span>
								<label for="fsje" class="col-sm-1 control-label">发生金额</label>
								<div class="col-sm-3">
									<input type="text" id="fsje" name="fsje" class="form-control"
										placeholder="输入发生金额" value="${p2pTsjyxxDto.fsje}">
								</div>
								<span id="error_fsje" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="mxxx" class="col-sm-1 control-label">明细信息</label>
								<div class="col-sm-3">
									<textarea id="mxxx" name="mxxx" class="form-control" style="resize:none;" rows="5" cols="20">${p2pTsjyxxDto.mxxx}</textarea>
								</div>
								<span id="error_mxxx" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">
							      	<c:if test="${p2pTsjyxxDto.status == 1}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="2"/> 停止
								    </c:if>
								    <c:if test="${p2pTsjyxxDto.status == 2}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="2"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" id="oldZjhm" name="oldZjhm" value="${p2pTsjyxxDto.zjhm}">
						<input type="hidden" id="orgCode" name="orgCode" value="${p2pTsjyxxDto.orgCode}">
						<input type="hidden" name="id" value="${p2pTsjyxxDto.id}">
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
	$("#tsjyxxInfoForm").validate({
	    onkeyup: false,
	    rules: {
			xm:{
				required:true,
				rangelength:[2,15],
				isChinese:true
			},
			zjlx:{
				required:true
			},
			zjhm:{
				required:true,
				isIdCardNo:true,
				remote:{
					url:"${pageContext.request.contextPath}/p2p/tsjyxx/checkP2PTsjyxxIsOnly",
					type: "post",
				    dataType: "json",
				    data: {
				    	zjlx: function() {
				            return $("#zjlx_select").val();
				        },
				    	zjhm: function() {
				            return $("#zjhm").val();
				        },
				        fsrq: function() {
				            return $("#fsrq").val();
				        },
				        orgCode: function() {
				            return $("#orgCode").val();
				        },
				        oldZjhm:function(){
				        	return $("#oldZjhm").val();
				        }
				    }
				}
			},
			ywh:{
				realLength:40,
				isContainsSpecialChar:true
			},
			tsjylx:{
				required:true
			},
			fsrq:{
				required:true
			},
			bgys:{
				required:true,
				isInteger:true,
				maxlength:4
			},
			fsje:{
				digits:true,
				isIntGtZero:true,
				maxlength:10
			},
			mxxx:{
				realLength:200,
				isContainsSpecialChar:true
			}
		},
		messages:{
			xm:{
				required:"请输入姓名",
				rangelength:"姓名长度为2~15个中文",
				isChinese:"姓名必须为中文"
			},
			zjlx:{
				required:"请选择证件类型"
			},
			zjhm:{
				required:"请输入证件号码",
				isIdCardNo:"请输入正确的身份证号码"
			},
			ywh:{
				maxlength:"业务号长度不能超过40个字符",
				isContainsSpecialChar:"业务号不能包括特殊字符"
			},
			tsjylx:{
				required:"请选择特殊交易类型"
			},
			fsrq:{
				required:"请选择发生日期"
			},
			bgys:{
				required:"请输入变更月数",
				isInteger:"变更月数应为整数",
				maxlength:"变更月数长度不能超过4位"
			},
			fsje:{
				digits:"发生金额应为大于0的整数",
				isIntGtZero:"发生金额应为大于0的整数",
				maxlength:"发生金额长度不能超过10位"
			},
			mxxx:{
				maxlength:"明细信息长度不能超过200个字符",
				isContainsSpecialChar:"明细信息不能包括特殊字符"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var tsjyxxInfoForm = $('#tsjyxxInfoForm').serializeObject();
			delete tsjyxxInfoForm.oldZjhm;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/p2p/tsjyxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(tsjyxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/p2p/tsjyxx/showList";
			    },
			    error: function () {
			        alert("edit tsjyxx error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/p2p/tsjyxx/showList";
}

//临时处理，后面不用这种方式
function loadSelect(){
	var zjlx = "${p2pTsjyxxDto.zjlx}";
	var tsjylx = "${p2pTsjyxxDto.tsjylx}";
	
	$("#zjlx_select").find("option[value="+zjlx+"]").attr("selected",true);
	$("#tsjylx_select").find("option[value="+tsjylx+"]").attr("selected",true);
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
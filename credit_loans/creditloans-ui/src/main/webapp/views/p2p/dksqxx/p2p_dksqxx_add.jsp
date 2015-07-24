<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">P2P贷款业务</a></li>
			<li class="active">贷款申请信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				贷款申请信息<small> <i class="icon-double-angle-right"></i>
					添加贷款申请信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="dksqxxInfoForm" class="form-horizontal">
						<fieldset>
							<div class="form-group">								
								<label for="dksqh" class="col-sm-1 control-label">申请号</label>
								<div class="col-sm-3">
									<input type="text" id="dksqh" name="dksqh" class="form-control"
										placeholder="输入贷款申请号">
								</div>
								<span id="error_dksqh" style="color: red" class="col-sm-2">*</span>
								<label for="xm" class="col-sm-1 control-label">姓名</label>
								<div class="col-sm-3">
									<input type="text" id="xm" name="xm" class="form-control"
										placeholder="输入姓名">
								</div>
								<span id="error_xm" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">								
								<label for="zjlx" class="col-sm-1 control-label">证件类型</label>
								<div class="col-sm-3">
									<select id="zjlx_select" name="zjlx" class="form-control">
										<option value="">- 选择证件类型 -</option>
										<option value="0">身份证</option>
									</select>
								</div>
								<span id="error_zjlx" style="color: red" class="col-sm-2">*</span>
								<label for="zjhm" class="col-sm-1 control-label">证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="zjhm" name="zjhm" class="form-control"
										placeholder="输入证件号码">
								</div>
								<span id="error_zjhm" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="dksqlx" class="col-sm-1 control-label">申请类型</label>
								<div class="col-sm-3">
									<select id="dksqlx_select" name="dksqlx" class="form-control">
										<option value="">- 选择申请类型 -</option>
										<option value="11">个人住房贷款</option>
										<option value="12">个人商用房（含商住两用）贷款</option>
										<option value="13">个人住房公积金贷款</option>
										<option value="21">个人汽车消费贷款</option>
										<option value="31">个人助学贷款</option>
										<option value="41">个人经营性贷款</option>
										<option value="51">农户贷款</option>
										<option value="91">个人消费贷款</option>
										<option value="99">其他</option>
									</select>
								</div>
								<span id="error_dksqlx" style="color: red" class="col-sm-2">*</span>
								<label for="dksqje" class="col-sm-1 control-label">申请金额</label>
								<div class="col-sm-3">
									<input type="text" id="dksqje" name="dksqje" class="form-control"
										placeholder="输入贷款申请金额">
								</div>
								<span id="error_dksqje" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="dksqzqdw" class="col-sm-1 control-label">申请周期单位</label>
								<div class="col-sm-3">
									<select id="dksqzqdw_select" name="dksqzqdw" class="form-control">
										<option value="">- 选择申请周期单位 -</option>
										<option value="1">天</option>
										<option value="2">周</option>
										<option value="3">月</option>
										<option value="4">季</option>
										<option value="5">半年</option>
										<option value="6">年</option>
									</select>
								</div>
								<span id="error_dksqzqdw" style="color: red" class="col-sm-2">*</span>
								<label for="dksqzq" class="col-sm-1 control-label">申请周期</label>
								<div class="col-sm-3">
									<input type="text" id="dksqzq" name="dksqzq" class="form-control"
										placeholder="输入贷款申请周期">
								</div>
								<span id="error_dksqzq" style="color: red" class="col-sm-2">*</span>								
							</div>
							<div class="form-group">
								<label for="dksqsj" class="col-sm-1 control-label">申请时间</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="dksqsj" name="dksqsj" type="text"
											placeholder="贷款申请时间" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_dksqsj" style="color: red" class="col-sm-2">*</span>
								<label for="dksqzt" class="col-sm-1 control-label">申请状态</label>
								<div class="col-sm-3">
									<select id="dksqzt_select" name="dksqzt" class="form-control">
										<option value="">- 选择申请状态 -</option>
										<option value="1">申请中</option>
										<option value="2">已批准</option>
										<option value="3">未通过</option>
									</select>
								</div>
								<span id="error_dksqzt" style="color: red" class="col-sm-2">*</span>
							</div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">							    
							    	<input id="status_yes" name="status" type="radio" checked="checked" value="1"/> 正常 
							      	&nbsp;&nbsp;&nbsp;
							      	<input id="status_not" name="status" type="radio" value="2"/> 停止
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" id="oldDksqh" name="oldDksqh">
						<input type="hidden" id="dksqys" name="dksqys" value="">
						<input type="hidden" id="orgCode" name="orgCode" value="${orgCode}">
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
	$("#dksqxxInfoForm")[0].reset();
	$("#dksqxxInfoForm").validate({
	    onkeyup: false,
		rules: {
			dksqh:{
				required:true,
				realLength:40,
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/p2p/dksqxx/checkDksqh",
					type: "post",
				    dataType: "json",
				    data: {
				    	dksqh: function() {
				            return $("#dksqh").val();
				        },
				        orgCode: function() {
				            return $("#orgCode").val();
				        },
				        oldDksqh:function(){
				        	return $("#oldDksqh").val();
				        }
				    }
				}
			},
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
				isIdCardNo:true
			},
			dksqlx:{
				required:true
			},
			dksqje:{
				required:true,
				digits:true,
				isIntGtZero:true,
				maxlength:10
			},
			dksqzqdw:{
				required:true
			},
			dksqzq:{
				required:true,
				digits:true,
				isIntGtZero:true,
				maxlength:[3],
				range:[1,800]
			},
			dksqsj:{
				required:true
			},
			dksqzt:{
				required:true
			}
		},
		messages:{
			dksqh:{
				required:"请输入贷款申请号",
				maxlength:"贷款申请号长度不能超过40个字符",
				isContainsSpecialChar:"贷款申请号不能包含特殊字符",
				remote:"贷款申请号已存在"
			},
			xm:{
				required:"请输入姓名",
				rangelength:"姓名长度为2~15个字中文",
				isChinese:"姓名必须为中文"					
			},
			zjlx:{
				required:"请选择证件类型"
			},
			zjhm:{
				required:"请输入证件号码",
				isIdCardNo:"请输入正确的身份证号码"
			},
			dksqlx:{
				required:"请选择贷款申请类型"
			},
			dksqje:{
				required:"请输入贷款申请金额",
				digits:"贷款申请金额应为大于0的整数",
				isIntGtZero:"贷款申请金额应为大于0的整数",
				maxlength:"贷款申请金额最大长度为10"
			},
			dksqzqdw:{
				required:"请选择贷款申请周期单位"
			},
			dksqzq:{
				required:"请输入贷款申请周期",
				digits:"贷款申请周期应为大于0的整数",
				isIntGtZero:"贷款申请周期应为大于0的整数",
				maxlength:"贷款申请周期长度不能超过3位",
				range:"贷款申请周期应在1~800之间"
			},
			dksqsj:{
				required:"请选择贷款申请时间"
			},
			dksqzt:{
				required:"请选择贷款申请状态"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var dksqxxInfoForm = $('#dksqxxInfoForm').serializeObject();
			var sqys = calculateDksqys(dksqxxInfoForm.dksqzqdw, dksqxxInfoForm.dksqzq);
			dksqxxInfoForm.dksqys = sqys;
			delete dksqxxInfoForm.oldDksqh;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/p2p/dksqxx/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(dksqxxInfoForm),
			    success: function (p2pDksqxxId) {
			    	if(p2pDksqxxId>0){
				        alert("添加成功！");
			    	}else{
			    		alert("添加失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/p2p/dksqxx/showList";
			    },
			    error: function () {
			        alert("save dksqxx error");
			    }	
			});
		}
	});

});

function calculateDksqys(dksqzqdw, dksqzq){
	switch(dksqzqdw){
		case "1":			
			return Digit.round(parseInt(dksqzq)/30.42, 2);
			break;
		case "2":
			return Digit.round(parseInt(dksqzq)/4.33, 2);
			break;
		case "3":
			return parseInt(dksqzq);
			break;
		case "4":
			return parseInt(dksqzq)*3;
			break;
		case "5":
			return parseInt(dksqzq)*6;
			break;
		case "6":
			return parseInt(dksqzq)*12;
			break;
		default:
			return 0;
			break;
	} 
}

function goback(){
	window.location.href = "${pageContext.request.contextPath}/p2p/dksqxx/showList";
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
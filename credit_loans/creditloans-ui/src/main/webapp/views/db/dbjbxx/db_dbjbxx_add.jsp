<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
		$("#dbjbxxInfoForm").validate({
			onkeyup: false,
			rules: {
				dbjgdm:{
					required:true,
					maxlength:[14]
				},
				dbywbh:{
					required:true,
					isRightfulString:true,
					maxlength:[60]
				},
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
				sjbgrq:{
					required:true
				},
				dbywzl:{
					required:true
				},
				dbfs:{
					required:true
				},
				dbje:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				dbqsrq:{
					required:true
				},
				dbdqrq:{
					required:true
				},
				fl:{
					required:true,
					isFloat:true,
					maxlength:[6]
				},
				nhfl:{
					required:true,
					isFloat:true,
					maxlength:[6]
				},
				dbhtzt:{
					required:true
				},
				zbye:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				yebhrq:{
					required:true
				},
				jzrq:{
					required:true
				},
				jxzcbz:{
					required:true
				},
				zjycdcrq:{
					required:true
				},
				bjgcddcje:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				bjgcddcye:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				ljzcje:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				ljssje:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				jflb:{
					required:true
				},
				bfjnjzrq:{
					required:true
				},
				bfjnje:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				jnfs:{
					required:true
				},
				jfqsrq:{
					required:true
				},
				bfjnzt:{
					required:true
				},
				bfjnye:{
					required:true,
					isFloat:true,
					maxlength:[20]
				},
				ljqjje:{
					required:true,
					isFloat:true,
					maxlength:[20]
				}
			},
			messages:{
				dbjgdm:{
					required:"请输入担保机构代码",
					maxlength:"担保机构代码不能超过14位"
				},
				dbywbh:{
					required:"请输入担保业务编号",
					isRightfulString:"只能输入字母和数字",
					maxlength:"担保业务编号不能超过60位"
				},
				bdbrlx:{
					required:"请输入被担保人类型"
				},
				bdbrmc:{
					required:"请输入被担保人名称",
					maxlength:"被担保人名称不能超过60位"
				},
				bdbrzjlx:{
					required:"请输入被担保人证件类型"
				},
				bdbrzjhm:{
					required:"请输入被担保人证件号码",
					maxlength:"被担保人证件号码不能超过18位"
				},
				sjbgrq:{
					required:"请输入数据报告日期"
				},
				dbywzl:{
					required:"请输入担保业务种类"
				},
				dbfs:{
					required:"请输入担保方式"
				},
				dbje:{
					required:"请输入担保金额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"担保金额不能超过20位"
				},
				dbqsrq:{
					required:"请输入担保起始日期"
				},
				dbdqrq:{
					required:"请输入担保到期日期"
				},
				fl:{
					required:"请输入费率",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"费率不能超过6位"
				},
				nhfl:{
					required:"请输入年化费率",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"年化费率不能超过6位"
				},
				dbhtzt:{
					required:"请输入担保合同状态"
				},
				zbye:{
					required:"请输入在保余额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"在保余额不能超过20位"
				},
				yebhrq:{
					required:"请输入余额变化日期"
				},
				jzrq:{
					required:"请输入记账日期"
				},
				jxzcbz:{
					required:"请输入继续追偿标志"
				},
				zjycdcrq:{
					required:"请输入最近一次代偿日期"
				},
				bjgcddcje:{
					required:"请输入本机构承担代偿金额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"本机构承担代偿金额不能超过20位"
				},
				bjgcddcye:{
					required:"请输入本机构承担代偿余额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"本机构承担代偿余额不能超过20位"
				},
				ljzcje:{
					required:"请输入累计追偿金额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"累计追偿金额不能超过20位"
				},
				ljssje:{
					required:"请输入累计损失金额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"累计损失金额不能超过20位"
				},
				jflb:{
					required:"请输入缴费类别"
				},
				bfjnjzrq:{
					required:"请输入保费缴纳记账日期"
				},
				bfjnje:{
					required:"请输入保费缴纳金额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"保费缴纳金额不能超过20位"
				},
				jnfs:{
					required:"请输入缴纳方式"
				},
				jfqsrq:{
					required:"请输入计费起始日期"
				},
				bfjnzt:{
					required:"请输入保费缴纳状态"
				},
				bfjnye:{
					required:"请输入保费缴纳余额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"保费缴纳余额不能超过20位"
				},
				ljqjje:{
					required:"请输入累计欠缴金额",
					isFloat:"请输入合法的数字，只能包含数字、小数点",
					maxlength:"累计欠缴金额不能超过20位"
				}
			},
			errorPlacement: function(error, element) {
				$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
			},
			submitHandler:function(){
				var dbjbxxInfoForm = $('#dbjbxxInfoForm').serializeObject();
				jQuery.ajax({
					type: "post",
				    url: "${pageContext.request.contextPath}/db/dbjbxx/doAdd",
				    contentType: "application/json",
				    data: JSON.stringify(dbjbxxInfoForm),
				    success: function (dbjbxxId) {
				    	if(dbjbxxId>0){
					        alert("添加成功！");
				    	}else{
				    		alert("添加失败！");
				    		
				    	}
				    	window.location.href = "${pageContext.request.contextPath}/db/dbjbxx/showList";
				    },
				    error: function () {
				        alert("save dbjbxx error");
				    }	
				});
			}
		});
		$("#dbhtzt_select").change( function() {
		       var type = $("#dbhtzt_select").find("option:selected").text();
		       if(type=="无效"){
		           $("#dbzrjcrq").rules("remove");
		           $("#dbzrjcrq").rules("add", { required: true, messages: { required: "请输入担保责任解除日期"} });
		       }
		});
});
function goback(){
	window.location.href = "${pageContext.request.contextPath}/db/dbjbxx/showList";
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
					添加担保基本信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="dbjbxxInfoForm" class="form-horizontal">
						<fieldset>
							<legend style="font-size: 16px; color: gray;">基础信息</legend>
							<div class="form-group">
								<label for="dbjgdm" class="col-sm-1 control-label">担保机构代码</label>
								<div class="col-sm-3">
									<input type="text" id="dbjgdm" name="dbjgdm" class="form-control"
										placeholder="输入担保机构代码">
								</div>
								<span id="error_dbjgdm" style="color: red" class="col-sm-2"></span>
								<label for="dbywbh" class="col-sm-1 control-label">担保业务编号</label>
								<div class="col-sm-3">
									<input type="text" id="dbywbh" name="dbywbh" class="form-control"
										placeholder="输入担保业务编号">
								</div>
								<span id="error_dbywbh" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="dbhthm" class="col-sm-1 control-label">担保合同号码</label>
								<div class="col-sm-3">
									<input type="text" id="dbhthm" name="dbhthm" class="form-control"
										placeholder="输入担保合同号码">
								</div>
								<span id="error_dbhthm" style="color: red" class="col-sm-2"></span>
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
								<label for="bdbrmc" class="col-sm-1 control-label">被担保人名称</label>
								<div class="col-sm-3">
									<input type="text" id="bdbrmc" name="bdbrmc" class="form-control"
										placeholder="被担保人名称">
								</div>
								<span id="error_bdbrmc" style="color: red" class="col-sm-2"></span>
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
							</div>
							<div class="form-group">
								<label for="bdbrzjhm" class="col-sm-1 control-label">被担保人证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="bdbrzjhm" name="bdbrzjhm" class="form-control"
										placeholder="被担保人证件号码">
								</div>
								<span id="error_bdbrzjhm" style="color: red" class="col-sm-2"></span>
								<label for="sjbgrq" class="col-sm-1 control-label">数据报告日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="sjbgrq" name="sjbgrq" type="text"
											placeholder="数据报告日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_sjbgrq" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 16px; color: gray;">担保合同信息</legend>
							<div class="form-group">
							    <label for="dbywzl" class="col-sm-1 control-label">担保业务种类</label>
							    <div class="col-sm-3">
									<select id="dbywzl_select" name="dbywzl" class="form-control">
										<option value="">- 担保业务种类 -</option>
										<option value="01">贷款担保</option>
										<option value="02">票据承兑担保</option>
										<option value="03">贸易融资担保</option>
										<option value="04">项目融资担保</option>
										<option value="05">信用证担保</option>
										<option value="06">其他融资性担保</option>
										<option value="07">诉讼保全担保</option>
										<option value="08">履约担保</option>
										<option value="09">其他非融资性担保</option>
										<option value="10">债券发行担保</option>
										<option value="11">再担保</option>
									</select>
								</div>
							    <span id="error_dbywzl" style="color: red" class="col-sm-2"></span>
							    <label for="dbfs" class="col-sm-1 control-label">担保方式</label>
							   <div class="col-sm-3">
									<select id="dbfs_select" name="dbfs" class="form-control">
										<option value="">- 担保方式 -</option>
										<option value="1">保证</option>
										<option value="2">抵押</option>
										<option value="3">质押</option>
										<option value="4">多种形式组合担保</option>
									</select>
								</div>
							    <span id="error_dbfs" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="dbje" class="col-sm-1 control-label">担保金额</label>
								<div class="col-sm-3">
									<input type="text" id="dbje" name="dbje" class="form-control"
										placeholder="输入担保金额">
								</div>
								<span id="error_dbje" style="color: red" class="col-sm-2"></span>
								<label for="dbqsrq" class="col-sm-1 control-label">担保起始日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="dbqsrq" name="dbqsrq" type="text"
											placeholder="担保起始日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_dbqsrq" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	
								<label for="dbdqrq" class="col-sm-1 control-label">担保到期日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="dbdqrq" name="dbdqrq" type="text"
											placeholder="担保到期日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_dbdqrq" style="color: red" class="col-sm-2"></span>
								<label for="ccbzjbl" class="col-sm-1 control-label">存出保证金比例</label>
								<div class="col-sm-3">
									<input type="text" id="ccbzjbl" name="ccbzjbl" class="form-control"
										placeholder="存出保证金比例">
								</div>
								<span id="error_ccbzjbl" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
								<label for="fdbfs" class="col-sm-1 control-label">反担保方式</label>
								<div class="col-sm-3">
									<select id="fdbfs_select" name="fdbfs" class="form-control">
										<option value="">- 反担保方式 -</option>
										<option value="0">自然人信用担保</option>
										<option value="1">第三方企业信用担保</option>
										<option value="2">动产质押担保</option>
										<option value="3">存单质押担保</option>
										<option value="4">知识产权质押担保</option>
										<option value="5">应收账款质押担保</option>
										<option value="6">其他质押担保</option>
										<option value="7">房地产抵押担保</option>
										<option value="8">其他抵押担保</option>
										<option value="9">多种形式组合担保</option>
										<option value="x">无反担保方式</option>
									</select>
								</div>
								<span id="error_fdbfs" style="color: red" class="col-sm-2"></span>
								<label for="ydzdbbcbl" class="col-sm-1 control-label">约定再担保补偿比例</label>
								<div class="col-sm-3">
									<input type="text" id="ydzdbbcbl" name="ydzdbbcbl" class="form-control"
										placeholder="约定再担保补偿比例">
								</div>
								<span id="error_ydzdbbcbl" style="color: red" class="col-sm-2"></span>
							</div>
							  <div class="form-group">
								<label for="fl" class="col-sm-1 control-label">费率</label>
								<div class="col-sm-3">
									<input type="text" id="fl" name="fl" class="form-control"
										placeholder="费率">
								</div>
								<span id="error_fl" style="color: red" class="col-sm-2"></span>
								<label for="nhfl" class="col-sm-1 control-label">年化费率</label>
								<div class="col-sm-3">
									<input type="text" id="nhfl" name="nhfl" class="form-control"
										placeholder="年化费率">
								</div>
								<span id="error_nhfl" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						
						<fieldset>
							<legend style="font-size: 16px; color: gray;">实际在保责任信息</legend>
							<div class="form-group">
								<label for="dbhtzt" class="col-sm-1 control-label">担保合同状态</label>
								<div class="col-sm-3">
									<select id="dbhtzt_select" name="dbhtzt" class="form-control">
										<option value="">- 担保合同状态 -</option>
										<option value="1">有效</option>
										<option value="2">无效</option>
									</select>
								</div>
								<span id="error_dbhtzt" style="color: red" class="col-sm-2"></span>
								<label for="dbzrjcrq" class="col-sm-1 control-label">担保责任解除日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="dbzrjcrq" name="dbzrjcrq" type="text"
											placeholder="担保责任解除日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_dbzrjcrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="zbye" class="col-sm-1 control-label">在保余额</label>
								<div class="col-sm-3">
									<input type="text" id="zbye" name="zbye" class="form-control"
										placeholder="输入在保余额">
								</div>
								<span id="error_zbye" style="color: red" class="col-sm-2"></span>
								<label for="yebhrq" class="col-sm-1 control-label">余额变化日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="yebhrq" name="yebhrq" type="text"
											placeholder="余额变化日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_yebhrq" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						
						<fieldset>
							<legend style="font-size: 16px; color: gray;">代偿概况信息</legend>
							<div class="form-group">
								<label for="jzrq" class="col-sm-1 control-label">记账日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="jzrq" name="jzrq" type="text"
											placeholder="记账日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_jzrq" style="color: red" class="col-sm-2"></span>
								<label for="jxzcbz" class="col-sm-1 control-label">继续追偿标志</label>
								<div class="col-sm-3">
									<select id="jxzcbz_select" name="jxzcbz" class="form-control">
										<option value="">- 继续追偿标志 -</option>
										<option value="1">是</option>
										<option value="2">否</option>
									</select>
								</div>
								<span id="error_jxzcbz" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="zjycdcrq" class="col-sm-1 control-label">最近一次代偿日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="zjycdcrq" name="zjycdcrq" type="text"
											placeholder="最近一次代偿日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_zjycdcrq" style="color: red" class="col-sm-2"></span>
								<label for="ljdcje" class="col-sm-1 control-label">累计代偿金额</label>
								<div class="col-sm-3">
									<input type="text" id="ljdcje" name="ljdcje" class="form-control"
										placeholder="输入累计代偿金额">
								</div>
								<span id="error_ljdcje" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="bjgcddcje" class="col-sm-1 control-label">本机构承担代偿金额</label>
								<div class="col-sm-3">
									<input type="text" id="bjgcddcje" name="bjgcddcje" class="form-control"
										placeholder="输入本机构承担代偿金额">
								</div>
								<span id="error_bjgcddcje" style="color: red" class="col-sm-2"></span>
								<label for="zjyczcrq" class="col-sm-1 control-label">最近一次追偿日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="zjyczcrq" name="zjyczcrq" type="text"
											placeholder="最近一次代偿日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_zjyczcrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="ljzcje" class="col-sm-1 control-label">累计追偿金额</label>
								<div class="col-sm-3">
									<input type="text" id="ljzcje" name="ljzcje" class="form-control"
										placeholder="输入累计追偿金额">
								</div>
								<span id="error_ljzcje" style="color: red" class="col-sm-2"></span>
								<label for="ljssje" class="col-sm-1 control-label">累计损失金额</label>
								<div class="col-sm-3">
									<input type="text" id="ljssje" name="ljssje" class="form-control"
										placeholder="输入累计损失金额">
								</div>
								<span id="error_ljssje" style="color: red" class="col-sm-2"></span>
							</div>
							
						</fieldset>
						
						<fieldset>
							<legend style="font-size: 16px; color: gray;"> 保费缴纳概况信息</legend>
							<div class="form-group">
								<label for="jflb" class="col-sm-1 control-label">缴费类别</label>
								<div class="col-sm-3">
									<select id="jflb_select" name="jflb" class="form-control">
										<option value="">- 缴费类别 -</option>
										<option value="1">担保费</option>
										<option value="2">保险保费</option>
									</select>
								</div>
								<span id="error_jflb" style="color: red" class="col-sm-2"></span>
								<label for="bfjnjzrq" class="col-sm-1 control-label">保费缴纳记账日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="bfjnjzrq" name="bfjnjzrq" type="text"
											placeholder="保费缴纳记账日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_bfjnjzrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="bfjnje" class="col-sm-1 control-label">保费缴纳金额</label>
								<div class="col-sm-3">
									<input type="text" id="bfjnje" name="bfjnje" class="form-control"
										placeholder="输入保费缴纳金额">
								</div>
								<span id="error_bfjnje" style="color: red" class="col-sm-2"></span>
								<label for="jnfs" class="col-sm-1 control-label">缴纳方式</label>
								<div class="col-sm-3">
									<select id="jnfs_select" name="jnfs" class="form-control">
										<option value="">- 缴纳方式 -</option>
										<option value="1">一次性（趸交）</option>
										<option value="2">分期（期交）</option>
									</select>
								</div>
								<span id="error_jnfs" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="jnpl" class="col-sm-1 control-label">缴纳频率</label>
								<div class="col-sm-3">
									<select id="jnpl_select" name="jnpl" class="form-control">
										<option value="">- 缴纳频率 -</option>
										<option value="01">日</option>
										<option value="02">周</option>
										<option value="23">介于周和月之间</option>
										<option value="03">月</option>
										<option value="34">介于月和季之间</option>
										<option value="04">季</option>
										<option value="05">半年</option>
										<option value="06">年</option>
										<option value="99">其他</option>
									</select>
								</div>
								<span id="error_jnpl" style="color: red" class="col-sm-2"></span>
								<label for="jfqsrq" class="col-sm-1 control-label">计费起始日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="jfqsrq" name="jfqsrq" type="text"
											placeholder="计费起始日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_jfqsrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="bfjnzt" class="col-sm-1 control-label">保费缴纳状态</label>
								<div class="col-sm-3">
									<select id="bfjnzt_select" name="bfjnzt" class="form-control">
										<option value="">- 保费缴纳状态 -</option>
										<option value="1">正常</option>
										<option value="2">欠缴</option>
										<option value="3">缴清</option>
									</select>
								</div>
								<span id="error_bfjnzt" style="color: red" class="col-sm-2"></span>
								<label for="jnjsrq" class="col-sm-1 control-label">缴纳结束日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="jnjsrq" name="jnjsrq" type="text"
											placeholder="缴纳结束日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_jnjsrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="bfjnye" class="col-sm-1 control-label">保费缴纳余额</label>
								<div class="col-sm-3">
									<input type="text" id="bfjnye" name="bfjnye" class="form-control"
										placeholder="输入保费缴纳余额">
								</div>
								<span id="error_bfjnye" style="color: red" class="col-sm-2"></span>
								<label for="ljqjje" class="col-sm-1 control-label">累计欠缴金额</label>
								<div class="col-sm-3">
									<input type="text" id="ljqjje" name="ljqjje" class="form-control"
										placeholder="输入累计欠缴金额">
								</div>
								<span id="error_ljqjje" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">							    
							    	<input id="status_yes" name="status" type="radio" checked="checked" value="0"/> 正常 
							      	&nbsp;&nbsp;&nbsp;
							      	<input id="status_not" name="status" type="radio" value="1"/> 停用
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" name="orgCode" value="${orgCode}">
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
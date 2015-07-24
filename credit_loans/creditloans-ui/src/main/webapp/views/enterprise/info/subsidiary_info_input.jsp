<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ include  file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
	    <ul class="breadcrumb">
		    <li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li class="active">企业信息</li>
			<li class="active">企业信息添加</li>
		</ul>
	</div>
	<div class="page-content">
	   <div class="page-header">
		    <h1>
				企业信息
				<small> 
				    <i class="icon-double-angle-right"></i>添加企业信息
				</small>
			</h1>
		</div><!-- /.page-header -->
		<div class="row">
		    <div class="col-xs-12">
			    <div class="col-md-12">
				    <form id="enterpriseInfo" class="form-horizontal" >
				    	<fieldset>
							<legend style="font-size: 16px; color: gray;">基本信息</legend>
							<div class="form-group">
								<label for="name" class="col-sm-1 control-label">企业名称</label>
								<div class="col-sm-3">
							    	<input type="text" id="name" name="name" class="form-control" placeholder="输入企业名称" value="${enterprise.name}" />
								</div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
								<label for="shortName" class="col-sm-1 control-label">企业简称</label>
								<div class="col-sm-3">
							    	<input type="text" id="shortName" name="shortName" class="form-control" placeholder="输入企业简称" value="${enterprise.shortName}" />
								</div>
							    <span id="error_shortName" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="englishName" class="col-sm-1 control-label">企业英文名称</label>
								<div class="col-sm-3">
									<input type="text" id="englishName" name="englishName" class="form-control"
										placeholder="输入企业英文名称" value="${enterprise.shortName}">
								</div>
								<span id="error_englishName" style="color: red" class="col-sm-2"></span>
								<label for="typeId" class="col-sm-1 control-label">企业类型：</label>
								<div class="col-sm-3">
								<select class="form-control" id="typeId" name="typeId" >
									 <option>- 请选择企业类型 -</option>
									 <c:forEach items="${enterpriseTypes }" var="enterpriseType"  varStatus="status">
									 	 <c:if test="${enterpriseType.id != enterprise.typeId}">
									 	 	<option value="${enterpriseType.id}">${enterpriseType.name } </option>
									 	 </c:if>	
									 	 <c:if test="${enterpriseType.id == enterprise.typeId}">
									         <option value="${enterpriseType.id}" selected="selected">${enterpriseType.name } </option>
									     </c:if>
									 </c:forEach>
								</select>
								</div>
							    <span id="error_typeId" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="zjhm" class="col-sm-1 control-label">上级企业</label>
								<div class="col-sm-3">
									<input type="text" name="" value="${enterprises.name }" class="form-control"  readonly="readonly" />
								</div>
								<span id="error_fid" style="color: red" class="col-sm-2"></span>
								<label for="code" class="col-sm-1 control-label">企业状态</label>
								<div class="col-sm-3">
									<c:choose>
									<c:when test="${enterprise.isinvalid == 0}">
										<input id="isinvalid_alive" name="isinvalid" type="radio" checked="checked" value="0"/> 有效 
									    <input id="isinvalid_forbidden" name="isinvalid" type="radio" value="1"/> 无效
									</c:when>
									<c:when test="${enterprise.isinvalid == 1}">
									 	<input id="isinvalid_alive" name="isinvalid" type="radio" value="0"/> 有效 
									    <input id="isinvalid_forbidden" name="isinvalid" type="radio" checked="checked" value="1"/> 无效
									</c:when>
									<c:otherwise>
										<input id="isinvalid_alive" name="isinvalid" type="radio" checked="checked" value="0"/> 有效 
									    <input id="isinvalid_forbidden" name="isinvalid" type="radio" value="1"/> 无效
									</c:otherwise>
									</c:choose>
								</div>
								<span id="error_isinvalid" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="prepareApprovalCode" class="col-sm-1 control-label">筹建批文文号</label>
								<div class="col-sm-3">
									<input type="text" id="prepareApprovalCode" name="prepareApprovalCode" class="form-control"
										placeholder="输入筹建批文文号" value="${enterprise.prepareApprovalCode}">
								</div>
								<span id="error_prepareApprovalCode" style="color: red" class="col-sm-2"></span>
								<label for="businessApprovalCode" class="col-sm-1 control-label">开业批文文号</label>
								<div class="col-sm-3">
									<input type="text" id="businessApprovalCode" name="businessApprovalCode" class="form-control"
										placeholder="输入开业批文文号" value="${enterprise.businessApprovalCode}">
								</div>
								<span id="error_businessApprovalCode" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="businessDate" class="col-sm-1 control-label">开业日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="businessDate" name="businessDate" type="text"
											placeholder="开业日期" readonly="readonly" value="${enterprise.businessDate}"> 
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar icon-calendar"></span></span> 
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_businessDate" style="color: red" class="col-sm-2"></span>
								<label for="countryTaxationCode" class="col-sm-1 control-label">国税登记号码</label>
								<div class="col-sm-3">
									<input type="text" id="countryTaxationCode" name="countryTaxationCode" class="form-control"
										placeholder="输入国税登记号码" value="${enterprise.countryTaxationCode}">
								</div>
								<span id="error_countryTaxationCode" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="landTaxationCode" class="col-sm-1 control-label">地税登记号码</label>
								<div class="col-sm-3">
									<input type="text" id="landTaxationCode" name="landTaxationCode" class="form-control"
										placeholder="输入地税登记号码" value="${enterprise.landTaxationCode}">
								</div>
								<span id="error_landTaxationCode" style="color: red" class="col-sm-2"></span>
								<label for="postcode" class="col-sm-1 control-label">营业地邮编</label>
								<div class="col-sm-3">
									<input type="text" id="postcode" name="postcode" class="form-control"
										placeholder="输入营业地邮编" value="${enterprise.postcode}">
								</div>
								<span id="error_postcode" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="address" class="col-sm-1 control-label">营业地址</label>
								<div class="col-sm-3">
									<textarea id="address"  name="address" class="form-control" placeholder="输入营业地址">${enterprise.address}</textarea>
								</div>
								<span id="error_address" style="color: red" class="col-sm-2"></span>
								<label for="note" class="col-sm-1 control-label">备注信息</label>
								<div class="col-sm-3">
									<textarea id="note"  name="note" class="form-control" placeholder="输入备注信息" >${enterprise.note}</textarea>
								</div>
								<span id="error_note" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 16px; color: gray;">工商注册信息</legend>
							<div class="form-group">
								<label for="orgCode" class="col-sm-1 control-label">组织机构代码</label>
								<div class="col-sm-3">
									<input type="text" id="orgCode" name="orgCode" class="form-control"
										placeholder="输入组织机构代码" value="${enterprise.orgCode}">
								</div>
								<span id="error_orgCode" style="color: red" class="col-sm-2"></span>
								<label for="registeraAddr" class="col-sm-1 control-label">工商注册地</label>
								<div class="col-sm-3">
									<input type="text" id="registeraAddr" name="registeraAddr" class="form-control"
										placeholder="输入工商注册地" value="${enterprise.registeraAddr}">
								</div>
								<span id="error_registeraAddr" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="registerCode" class="col-sm-1 control-label">工商注册号</label>
								<div class="col-sm-3">
									<input type="text" id="registerCode" name="registerCode" class="form-control"
										placeholder="输入工商注册号" value="${enterprise.registerCode}">
								</div>
								<span id="error_registerCode" style="color: red" class="col-sm-2"></span>
								<label for="registeredCapital" class="col-sm-1 control-label">注册资本</label>
								<div class="col-sm-3">
									<input type="text" id="registeredCapital" name="registeredCapital" class="form-control"
										placeholder="输入注册资本(万元)" value="${enterprise.registeredCapital}">
								</div>
								<span id="error_registeredCapital" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="legalPerson" class="col-sm-1 control-label">法人姓名</label>
								<div class="col-sm-3">
									<input type="text" id="legalPerson" name="legalPerson" class="form-control"
										placeholder="输入法人姓名" value="${enterprise.legalPerson}">
								</div>
								<span id="error_legalPerson" style="color: red" class="col-sm-2"></span>
								<label for="acreage" class="col-sm-1 control-label">营业面积</label>
								<div class="col-sm-3">
									<input type="text" id="acreage" name="acreage" class="form-control"
										placeholder="输入营业面积" value="${enterprise.acreage}">
								</div>
								<span id="error_acreage" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 16px; color: gray;">联系信息</legend>
							<div class="form-group">
								<label for="tel" class="col-sm-1 control-label">联系电话</label>
								<div class="col-sm-3">
									<input type="text" id="tel" name="tel" class="form-control"
										placeholder="输入企业电话" value="${enterprise.tel}">
								</div>
								<span id="error_tel" style="color: red" class="col-sm-2"></span>
								<label for="fax" class="col-sm-1 control-label">传真</label>
								<div class="col-sm-3">
									<input type="text" id="fax" name="fax" class="form-control"
										placeholder="输入传真" value="${enterprise.fax}">
								</div>
								<span id="error_fax" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="email" class="col-sm-1 control-label">电子邮箱</label>
								<div class="col-sm-3">
									<input type="text" id="email" name="email" class="form-control"
										placeholder="输入电子邮箱" value="${enterprise.email}">
								</div>
								<span id="error_email" style="color: red" class="col-sm-2"></span>
								<label for="website" class="col-sm-1 control-label">网址</label>
								<div class="col-sm-3">
									<input type="text" id="website" name="website" class="form-control"
										placeholder="输入网址" value="${enterprise.website}">
								</div>
								<span id="error_website" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<input type="hidden" id="strId" name="strId" value="${enterprise.id}">						
						<input type="hidden" id="fid" name="fid" value="${enterprises.id}"/>
						<input type="hidden" id="oldName" name="oldName" value="${enterprise.name}"/>
						<input type="hidden" id="createType" name="createType" value="${enterprise.createType != null?enterprise.createType:0}"/>
						<div class="form-group">
							<div class="col-sm-offset-5">
								<input type="submit" class="btn btn-primary" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="#" class="btn btn-success " onclick="goback()">返回</a>
							</div>
						</div>
					</form>
				</div>
		    </div>
		</div> <!-- class:row -->
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#enterpriseInfo").validate({
		    onkeyup: false,
			rules: {
				name:{
					required:true,
					rangelength:[2,20],
					isContainsSpecialChar:true,
				    remote:{
						url:"${pageContext.request.contextPath}/subsidiary/info/checkEnterpriseName",
						type: "post",
					    dataType: "json",
					    data: {
					    	name: function() {
					            return $("#name").val();
					        },
					        oldName:function(){
					        	return $("#oldName").val();
					        }
					    },
					}
				}, 
				typeId:{
					min:1
				},
				tel:{
					isPhone: true
				},
				fax:{
					isPhone: true
				},
				postcode:{
					isZipCode:true
				},
				website:{
					url:true
				},
				email:{
					email:true
				},
				note:{
					rangelength:[0,120],
				},
				fid:{
					min:1,
					required:true
				}
			},
			messages:{
				name:{
					required:"请输入企业名称",
					rangelength:"企业名称长度为2~20个字符",
					isContainsSpecialChar:"企业名称只能由中文、英文",
				    remote:"该企业名称已存在！"
				},
				typeId:{
					min:"企业类型不能为空"
				},
				tel:{
					isPhone: "请输入正确的座机号码"
				},
				fax:{
					isPhone: "请输入正确的传真号码"
				},
	            postcode:{
	            	isZipCode:"请输入正确的邮政编码"
				},
				website:{
					url:"请输入正确的网址"
				},
				email:{
					email:"请输入正确的电子邮箱"
				},
				note:{
					rangelength:"最多可输入125个汉字"
				},
				fid:{
					min:"上级企业不能为空",
					required:"请选择上级企业"
				}
			},
			errorPlacement: function(error, element) {
				$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
			},
			submitHandler:function(){
				var enterpriseInfo = $("#enterpriseInfo").serialize();
				delete enterpriseInfo.oldName;
				$.ajax({
					 type:"POST",
					 url:"${pageContext.request.contextPath}/subsidiary/info/saveOrUpdateEnterprise",
					 data:enterpriseInfo,
					 success:function(msg){
						 if(msg){
							 alert("保存企业信息成功");
							 goback();
						 }else{
							 alert("保存企业信息失败");
						 }
					 },
					 error: function () {
			        	alert("保存企业信息失败");
			    	}	
				});
			}
		});
	});
	
	//返回
	function goback(){
		window.location.href = "${pageContext.request.contextPath}/subsidiary/info/enterpriseInfoList"
	}

    $('.form_date').datetimepicker({
    	format: "yyyy-mm-dd",
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
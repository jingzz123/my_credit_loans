<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 

<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">规定管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					规定管理 <small> <i class="icon-double-angle-right"></i>
						添加规定
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="ruleInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">规定名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入规定名称" 
							      value="${rulesetRuleDTO.name }">
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">规定描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入规定描述"
							      value="${rulesetRuleDTO.description }">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="isUsed" class="col-sm-1 control-label">是否可用</label>
							    <div class="col-sm-3">
							      <input id="isUsed_yes" name="isUsed" type="radio" checked="checked" value="Y"/>是 
							      <input id="isUsed_not" name="isUsed" type="radio" value="N"/>否
							    </div>
							    <span id="error_isUsed" style="color: red" class="col-sm-2"></span>
								<label for="sequence" class="col-sm-1 control-label">次序号</label>
							    <div class="col-sm-3">
							      <input type="text" id="sequence" name="sequence" class="form-control" placeholder="输入次序号"
							      value="${rulesetRuleDTO.sequence }">
							    </div>
							    <span id="error_sequence" style="color: red" class="col-sm-2"></span>
							</div>
							</fieldset>
							<legend style="font-size: 16px; color: gray;">action信息</legend>
<table class="table">
	<tbody>
		<tr>
			<td align="right" style="width: 50%;border-top:0;">
				<select id="sel_all_area2"  multiple="multiple" size="10" style="width:100%;height:200px">
				   <c:forEach items="${rulesetActionDTOList}" var="actionname" varStatus="status">
						<option value="${actionname.name }">${actionname.name }</option>
					</c:forEach>
				 </select>
			 </td>
			 <td style="width: 120px;border-top: 0; vertical-align: middle;">
				<button type="button"  id="add2" class="btn btn-primary icon-arrow-right">添加</button>
				<button type="button" id="delete2" class="btn btn-primary icon-arrow-left" style="margin-top: 20px;">移除</button>
			 </td>
			 <td style="width: 50%;border-top:0;">
				 <div style="margin-bottom: 2px;">
					  <select id="sel_selected_areas2" name="actionNameList" multiple="multiple" size="10" style="width:100%;height: 200px;">
					   <c:forEach items="${rulesetRuleDTO.actionNameList}" var="actionname" varStatus="status">
						<option value="${actionname }">${actionname }</option>
					</c:forEach>
				 </select>
				 </div>
				  <span id="error-selectAction" style="color: red">*</span>
			 </td>
		</tr>
	</tbody>
</table>
							
<legend style="font-size: 16px; color: gray;">condition信息</legend>
<table class="table">
	<tbody>
		<tr>
			<td align="right" style="width: 50%;border-top:0;">
				<select id="sel_all_area1"  multiple="multiple" size="10" style="width:100%;height:200px">
				   <c:forEach items="${rulesetConditionDTOList}" var="condition" varStatus="status">
						<option value="${condition.name }">${condition.name }</option>
					</c:forEach>
				 </select>
			 </td>
			 <td style="width: 120px;border-top: 0; vertical-align: middle;">
				<button type="button"  id="add1" class="btn btn-primary icon-arrow-right">添加</button>
				<button type="button" id="delete1" class="btn btn-primary icon-arrow-left" style="margin-top: 20px;">移除</button>
			 </td>
			 <td style="width: 50%;border-top:0;">
				 <div style="margin-bottom: 2px;">
					  <select id="sel_selected_areas1" name="conditionList" multiple="multiple" size="10" style="width:100%;height: 200px;">
					  <c:forEach items="${rulesetRuleDTO.conditionList}" var="condition" varStatus="status">
						<option value="${condition }">${condition }</option>
					</c:forEach>
				 </select>
				 </div>
				  <span id="error-selectCondition" style="color: red">*</span>
			 </td>
		</tr>
	</tbody>
</table>
							  <input type="hidden" id="rulesetId" name="rulesetId" value="${rulesetRuleDTO.rulesetId }">
							  <input type="hidden" id="id" name="id" value="${rulesetRuleDTO.id }">
							  <input type="hidden" id="oldSequence" name="oldSequence" value="${rulesetRuleDTO.oldSequence}">
							  <input type="hidden" id="oldName" name="oldName" value="${rulesetRuleDTO.oldName}">
							  <div class="form-group">
							    <div class="col-sm-offset-4">
							      <input type="submit" class="btn btn-primary" value="保存"></input>
							      <input type="button" class="btn btn-success" value="返回" onclick="goback()"></input>
							    </div>
							  </div>
						</form>
					</div>
				</div>
				 
			</div> <!-- class:row -->
			
		</div><!-- /.page-content -->
		
	</div><!-- /.main-content --> 
	<script type="text/javascript">
	$().ready(function() {
		$("#ruleInfoForm").validate({
		    onkeyup: false,
		    rules: {
				name:{
					required:true,
					maxlength:[50],
					isContainsSpecialChar:true,
					remote:{
						url:"${pageContext.request.contextPath}/regulation/basepackage/rule/checkName",
						type: "post",
					    dataType: "json",
					    data: {
					    	name: function() {
					            return $("#name").val();
					        },
					        oldName: function() {
					        	return $("#oldName").val();
					        }
					    },
					}
				},
				description:{
					required:true,
					maxlength:[200]
				},
				sequence:{
					required:true,
					number:true,
					remote:{
						url:"${pageContext.request.contextPath}/regulation/basepackage/rule/checkSequence",
						type: "post",
					    dataType: "json",
					    data: {
					    	sequence: function() {
					            return $("#sequence").val();
					        },
					        oldSequence: function() {
					        	return $("#oldSequence").val();
					        }
					    },
					}
				}
			},
			messages:{
				name:{
					required:"请输入包名称",
					maxlength:"包名称长度不超过50个字符",
					isContainsSpecialChar:"包名称只能由中文、英文、数字、“_”组成",
					remote:"该名称已存在！"
				},
				description:{
					required:"请输入包描述",
					maxlength:"包描述长度不超过200个字符"
				},
				sequence:{
					required:"请输入序列号",
					number:"请输入数字",
					remote:"该次序已存在！"				
				}
			},
			errorPlacement: function(error, element) {
				$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
			},
			submitHandler:function(){
				var ruleInfoForm = $('#ruleInfoForm').serializeObject();
				var conditionArray = conditionArr();
				$("#error-selectCondition").text("");
				var actionNameArrry = actionArr();
				$("#error-selectAction").text("")
				if(conditionArray == null || "" == conditionArray || actionNameArrry == null || "" == actionNameArrry){
					if(conditionArray == null || "" == conditionArray) {
						$("#error-selectCondition").text("*条件信息不能为空")
					}
					if(actionArrry == null || "" == actionArrry){
						$("#error-selectAction").text("*动作信息不能为空")
					}
					return false;	
				}
				ruleInfoForm.conditionList = conditionArray;
				ruleInfoForm.actionNameList = actionNameArrry;
				jQuery.ajax({
					type: "post",
				    url: "${pageContext.request.contextPath}/regulation/basepackage/rule/doEdit",
				    contentType: "application/json",
				    data: JSON.stringify(ruleInfoForm),
				    success: function () {
				    	alert("修改用户成功！");
				    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/rule/showList?rulesetId=${rulesetRuleDTO.rulesetId}";
				    },
				    error: function () {
				        alert("edit regulation rule error");
				    }	
				});
			}
		});
		
		
	});
	$("#add1").click(function(){
		if($("#sel_all_area1 option:selected").length > 0)  {  
	　　　　　　　　$("#sel_all_area1 option:selected").each(function(){  
	　　　　           $("#sel_selected_areas1").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");  
	                   $(this).remove();　  
	               });  
	　　　　}  else {  
	　　　　　　　alert("请选择要添加条件！");  
	　　　　}  
	});  

	$("#delete1").click(function(){  
	　　　　if($("#sel_selected_areas1 option:selected").length > 0)  {  
	　　　　    $("#sel_selected_areas1 option:selected").each(function(){  
	　　　　　　　　　$("#sel_all_area1").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");  
	　　　　　　　　　$(this).remove();　  
	　　　　　　})  
	　　　　} else {  
	　　　　　　alert("请选择要删除的条件！");  
	　　　　}  
	}); 
	function conditionArr(){
		var conditionInfo = [];
		var conditionSize = $("#sel_selected_areas1 option").length;
		if(conditionSize  > 0){
			$($("#sel_selected_areas1 option")).each(function(){
				conditionInfo.push($(this).val());
			});
		}
		return conditionInfo;
	}
	$("#add2").click(function(){
		if($("#sel_all_area2 option:selected").length > 0)  {  
	　　　　　　　　$("#sel_all_area2 option:selected").each(function(){  
	　　　　           $("#sel_selected_areas2").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");  
	                   $(this).remove();　  
	               });  
	　　　　}  else {  
	　　　　　　　alert("请选择要添加动作！");  
	　　　　}  
	});  

	$("#delete2").click(function(){  
	　　　　if($("#sel_selected_areas2 option:selected").length > 0)  {  
	　　　　    $("#sel_selected_areas2 option:selected").each(function(){  
	　　　　　　　　　$("#sel_all_area2").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");  
	　　　　　　　　　$(this).remove();　  
	　　　　　　})  
	　　　　} else {  
	　　　　　　alert("请选择要删除的动作！");  
	　　　　}  
	}); 
	function actionArr(){
		var actionInfo = [];
		var actionSize = $("#sel_selected_areas2 option").length;
		if(actionSize  > 0){
			$($("#sel_selected_areas2 option")).each(function(){
				actionInfo.push($(this).val());
			});
		}
		return actionInfo;
	}
	
	function loadSelect(){	
		var isUsed = "${rulesetRuleDTO.isUsed}";
		
		$("#isUsed_select").find("option[value="+isUsed+"]").attr("selected",true);
	}
	function goback(){
		window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/rule/showList?rulesetId=${rulesetRuleDTO.rulesetId}";
	}
</script>
<%@ include  file="./../../../common/footer.jsp"%>

<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">

$().ready(function() {
	$("#rulesetElementInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/checkName",
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
			baseId: {
				required: true
			},
			departmentId:{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入规则集名称",
				maxlength:"规则集名称长度不超过50个字符",
				isContainsSpecialChar:"规则集名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在！"
			},
			description:{
				required:"请输入规则集描述",
				maxlength:"规则集描述长度不超过200个字符"
			},
			baseId: {
				required: "请输入所属包",
			},
			departmentId:{
				required:"请选择所属机构"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var rulesetElementInfoForm = $('#rulesetElementInfoForm').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(rulesetElementInfoForm),
			    success: function (rulesetElementId) {
			    	if(rulesetElementId>0){
				        alert("添加rulesetElement成功！");
			    	}else{
			    		alert("添加rulesetElement失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/showList";
			    },
			    error: function () {
			        alert("save regulation rulesetelement error");
			    }	
			});
		}
	});
});


function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">规则集管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					规则集管理 <small> <i class="icon-double-angle-right"></i>
						添加规则集
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="rulesetElementInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">规则集名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入规则集名称" >
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">规则集描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入规则集描述">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							    <label for="baseId" class="col-sm-1 control-label">所属包</label>
							    <div class="col-sm-3">							    
							    	<select id="baseId_select" name="baseId" class="form-control">
							    		<option value="">- 所属包-</option>
							    		<c:forEach items="${baseElementDTOList}" var="base">
							    		<option value="${base.id}">${base.name}</option>
							    		</c:forEach>
							    	</select>
							  	</div>
							    <span id="error_baseId" style="color: red" class="col-sm-2"></span>
							    <label for="departmentId" class="col-sm-1 control-label">所属机构</label>
							    <div class="col-sm-3">							    
							    	<select id="departmentId_select" name="departmentId" class="form-control">
							    		<option value="">- 所属机构 -</option>
							    		<c:forEach items="${platformDepartmentDtoList}" var="dep">
							    		<option value="${dep.id}">${dep.name}</option>
							    		</c:forEach>
							    	</select>
							  	</div>
							    <span id="error_departmentId" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="isInner" class="col-sm-1 control-label">内部公用</label>
							    <div class="col-sm-3">
							      <input id="isInner_yes" name="isInner" type="radio" checked="checked" value="Y"/>是 
							      <input id="isInner_not" name="isInner" type="radio" value="N"/>否
							    </div>
							    <span id="error_isInner" style="color: red" class="col-sm-2"></span>
							  	<label for="isUsed" class="col-sm-1 control-label">是否可用</label>
							    <div class="col-sm-3">
							      <input id="isUsed_yes" name="isUsed" type="radio" checked="checked" value="Y"/>是 
							      <input id="isUsed_not" name="isUsed" type="radio" value="N"/>否
							    </div>
							    <span id="error_isUsed" style="color: red" class="col-sm-2"></span>
							  </div>
							</fieldset>
							 <input type="hidden" id="oldName" name="oldName" value="">
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
<%@ include  file="./../../../common/footer.jsp"%>
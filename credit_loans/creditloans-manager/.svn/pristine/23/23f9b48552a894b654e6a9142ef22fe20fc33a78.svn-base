<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">
$().ready(function() {
	$("#validatorElementInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/validatorelement/checkName",
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
			baseId :{
				required:true
			},
			departmentId:{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入校验集名称",
				maxlength:"校验集名称长度不超过50个字符",
				isContainsSpecialChar:"校验集名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在！"
			},
			description:{
				required:"请输入校验集描述",
				maxlength:"校验集描述长度不超过200个字符"
			},
			baseId: {
				required: "请输入所属包"
			},
			departmentId:{
				required:"请选择所属机构"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var validatorElementInfoForm = $('#validatorElementInfoForm').serializeObject();
			delete validatorElementInfoForm.templateName;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/validatorelement/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(validatorElementInfoForm),
			    success: function () {
			    	alert("修改用户成功！");
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/validatorelement/showList";
			    },
			    error: function () {
			        alert("edit regulation validatorelement error");
			    }	
			});
		}
	});

});

function submitUpload(){
	var file = $("#templateName").val();
	if(file == '' || file == null) {
		$("#error_templateName").text("文件不能为空");
	} 
	 var index = file.lastIndexOf(".");
	 var ext = file.substring(index + 1, file.length);
	 alert(ext)
	 if(true){
		 $.ajaxFileUpload({  
		        url : '${pageContext.request.contextPath}/upload/uploadFile',  
		        secureuri : false,//安全协议  
		        fileElementId:'templateName',//id  
		        type : 'POST',  
		        dataType : 'json',      
		        success : function(data) { 
		        	$("#fileName").val("");
		        	$("#fileName").val(data);
		        },
			 	error:function(data,  status, e) {  
		            alert('上传文件失败!'+e);  
		        }
		    });   
	 }else{
		 $("#error_templateName").text("文件类型不正确");
	 } 
}

function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/validatorelement/showList";
} 
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">校验集管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					校验集管理 <small> <i class="icon-double-angle-right"></i>
						修改校验集
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="validatorElementInfoForm" class="form-horizontal" enctype="multipart/form-data" method="post">
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">校验集名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入校验集名称" 
							      value="${validatorElementDTO.name }">
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">校验集描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入校验集描述"
							      value="${validatorElementDTO.name }">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							    <label for="baseId" class="col-sm-1 control-label">所属包</label>
							    <div class="col-sm-3">							    
							    	<select id="baseId_select" name="baseId" class="form-control">
							    		<option value="">- 所属包-</option>
							    		<c:forEach items="${baseElementDTOList}" var="base">
							    		<option value="${base.id}" <c:if test="${base.id == validatorElementDTO.baseId }">selected</c:if>>${base.name}</option>
							    		</c:forEach>
							    	</select>
							  	</div>
							    <span id="error_baseId" style="color: red" class="col-sm-2"></span>
							    <label for="departmentId" class="col-sm-1 control-label">所属机构</label>
							    <div class="col-sm-3">							    
							    	<select id="departmentId_select" name="departmentId" class="form-control">
							    		<option value="">- 所属机构 -</option>
							    		<c:forEach items="${platformDepartmentDTOList}" var="dep">
							    		<option value="${dep.id}" <c:if test="${dep.id == validatorElementDTO.departmentId }">selected</c:if>>${dep.name}</option>
							    		</c:forEach>
							    	</select>
							  	</div>
							    <span id="error_departmentId" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="isInner" class="col-sm-1 control-label">内部公用</label>
							    <div class="col-sm-3">
							      <input id="isInner_yes" name="isInner" type="radio" <c:if test="${validatorElementDTO.isInner == 'Y' }">checked</c:if> value="Y"/>是 
							      <input id="isInner_not" name="isInner" type="radio"  <c:if test="${validatorElementDTO.isInner == 'N' }">checked</c:if> value="N"/>否
							    </div>
							    <span id="error_isInner" style="color: red" class="col-sm-2"></span>
							  	<label for="isUsed" class="col-sm-1 control-label">是否可用</label>
							    <div class="col-sm-3">
							      <input id="isUsed_yes" name="isUsed" type="radio"  value="Y" <c:if test="${validatorElementDTO.isUsed == 'Y' }">checked</c:if> />是 
							      <input id="isUsed_not" name="isUsed" type="radio" value="N" <c:if test="${validatorElementDTO.isUsed == 'N' }">checked</c:if> />否
							    </div>
							    <span id="error_isUsed" style="color: red" class="col-sm-2"></span>
							  </div>
							   <div class="form-group form-group-ie">
				                  <label for="name" class="col-sm-1 control-label">上传文件</label>
				                  <div class="col-sm-3">
				                   <input type="file" name="templateName" id="templateName" onchange='submitUpload()'>
				                  </div>
				                  <span id="error_templateName" style="color: red" class="col-sm-2"></span>
                			</div>
							</fieldset>
							 <input type="hidden" name="fileName" id="fileName" value="" />
							 <input type="hidden" name="id" value="${validatorElementDTO.id }">
							 <input type="hidden" id="oldName" name="oldName" value="${validatorElementDTO.oldName}">
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
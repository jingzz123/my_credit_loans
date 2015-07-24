<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">
$().ready(function() {
	loadSelect();
	if($("[name='isNested']:checked").val() == 'N'){
		$("#mainSchema").show();
	}
	var schemaInfoForm;
	$("#schemaInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/metadata/schema/checkName",
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
			type:{
				required:true
			},
			delimiter: {
				required: true,
				maxlength:[1]
			},
			departmentId:{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入schema名称",
				maxlength:"schema名称长度不超过50个字符",
				isContainsSpecialChar:"schema名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在！"
			},
			description:{
				required:"请输入schema描述",
				maxlength:"schema描述长度不超过200个字符"
			},
			type:{
				required:"请输入schema类型"
			},
			delimiter: {
				required: "请输入分隔符",
				maxlength:"分割符最大不超过1个字节"
			},
			departmentId:{
				required:"请选择所属机构"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var schemaInfoForm = $('#schemaInfoForm').serializeObject();
			var mainSchemaIdArr = new Array();
			$("#main_showspace").find("input:checkbox:checked").each(function(i){
				mainSchemaIdArr.push($(this).val());			  
			});
			schemaInfoForm.mainSchemaIdList = mainSchemaIdArr;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/metadata/schema/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(schemaInfoForm),
			    success: function () {
			    	alert("修改用户成功！");
			    	window.location.href = "${pageContext.request.contextPath}/regulation/metadata/schema/showList";
			    },
			    error: function () {
			        alert("edit regulation schema error");
			    }	
			});
		}
	});

	$("#type_select").change( function() {
	       var type = $("#type_select").find("option:selected").text();
	       if(type=="定长"){
	           $("#idLength").rules("add", { required: true, messages: { required: "请输入schemaId长度"} });
	       }else{
	    	   $("#idLength").rules("remove");
	       }
	});
	
	$("[name='isNested']").on("change",function (e) {
		if($(this).val() == 'N'){
			$("#mainSchema").show(); 
			
			$("[name='mainSchemaIdList']").each(function(i,e) {
				$(this).attr("checked",false);
			});
		}else{
			$("#mainSchema").hide()
			delete schemaInfoForm.mainSchemaIdList
		}
	});
});

function loadSelect(){	
	var type = "${metadataSchemaDTO.type}";
	$("#type_select").find("option[value="+type+"]").attr("selected",true);
}
function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/metadata/schema/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">schema管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					schema管理 <small> <i class="icon-double-angle-right"></i>
						修改schema
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="schemaInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">schema名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control"
							       placeholder="输入schema名称" value="${metadataSchemaDTO.name }">
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							    <label for="type" class="col-sm-1 control-label">类型</label>
								<div class="col-sm-3">
									<select id="type_select" name="type" class="form-control">
										<option value="">- 类型 -</option>
										<option value="1">定长</option>
										<option value="2">不定长</option>
									</select>
								</div>
								<span id="error_type" style="color: red" class="col-sm-2"></span>
							  </div>
							  
							  <div class="form-group">
							    <label for="delimiter" class="col-sm-1 control-label">分隔符</label>
							    <div class="col-sm-3">
							      <input type="text" id="delimiter" name="delimiter" class="form-control"
							       placeholder="输入分隔符" value="${metadataSchemaDTO.delimiter }">
							    </div>
							    <span id="error_delimiter" style="color: red" class="col-sm-2"></span>
							    <label for="isNested" class="col-sm-1 control-label">NestSchema</label>
							    <div class="col-sm-3">
							      <input id="isNested_yes" name="isNested" type="radio" checked="checked" <c:if test="${metadataSchemaDTO.isNested == 'Y' }">checked</c:if> value="Y"/>是 
							      <input id="isNested_not" name="isNested" type="radio" <c:if test="${metadataSchemaDTO.isNested == 'N' }">checked</c:if> value="N"/>否
							    </div>
							     <span id="error_isNested" style="color: red" class="col-sm-2"></span>
							  </div>							  
							  <div class="form-group">
							    <label for="idLength" class="col-sm-1 control-label">SchemaId的长度</label>
							    <div class="col-sm-3">
							      <input type="text" id="idLength" name="idLength" class="form-control"
							       placeholder="输入schemaId长度"  value="${metadataSchemaDTO.idLength }">
							    </div>
							    <span id="error_idLength" style="color: red" class="col-sm-2"></span>
							    <label for="description" class="col-sm-1 control-label">Schema描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control"
							       placeholder="输入描述" value="${metadataSchemaDTO.description }">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="isInner" class="col-sm-1 control-label">内部公用</label>
							    <div class="col-sm-3">
							      <input id="isInner_yes" name="isInner" type="radio" checked="checked" <c:if test="${metadataSchemaDTO.isInner == 'Y' }">checked</c:if> value="Y"/>是 
							      <input id="isInner_not" name="isInner" type="radio" <c:if test="${metadataSchemaDTO.isInner == 'N' }">checked</c:if> value="N"/>否
							    </div>
							    <span id="error_isInner" style="color: red" class="col-sm-2"></span>
							    <label for="departmentId" class="col-sm-1 control-label">所属机构</label>
							    <div class="col-sm-3">							    
							    	<select id="departmentId_select" name="departmentId" class="form-control">
							    		<option value="">- 所属机构 -</option>
							    		<c:forEach items="${platformDepartmentDtoList}" var="dep">
							    		<option value="${dep.id}" <c:if test="${dep.id == metadataSchemaDTO.departmentId }">selected</c:if>>${dep.name}</option>
							    		</c:forEach>
							    	</select>
							  	</div>
							    <span id="error_departmentId" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="isUsed" class="col-sm-1 control-label">是否可用</label>
							    <div class="col-sm-3">
							      <input id="isUsed_yes" name="isUsed" type="radio" <c:if test="${metadataSchemaDTO.isUsed == 'Y' }">checked</c:if> value="Y"/>是 
							      <input id="isUsed_not" name="isUsed" type="radio" <c:if test="${metadataSchemaDTO.isUsed == 'N' }">checked</c:if> value="N"/>否
							    </div>
							    <span id="error_isUsed" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group" id="mainSchema" style="display:none">
							 	<label for="main_showspace" class="col-sm-1 control-label">选择父Schema</label>
							    <div id="main_showspace" class="col-sm-3">
							    	<c:forEach items="${metadataSchemaDTO.metadataSchemaDTOList}" var="mainSchema">
							    		<input type="checkbox"  value="${mainSchema.id}" name="mainSchemaIdList" <c:if test="${mainSchema.schemaFlag==1 }">checked</c:if>/>${mainSchema.name}
							    	</c:forEach>
							    </div>
							    <span id="error_mainSchemaIdList" style="color: red" class="col-sm-2"></span>
							    </div>
							</fieldset>
							 <input type="hidden" name="id" value="${metadataSchemaDTO.id}">
							 <input type="hidden" id="oldName" name="oldName" value="${metadataSchemaDTO.oldName}">
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
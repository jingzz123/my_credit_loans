<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">
$().ready(function() {
	loadSelect();
	$("#variableSituationInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/variablesituation/checkName",
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
			type: {
				required: true
			},
			attributeName: {
				required: true,
				maxlength:[20]
			},
			initValue:{
				required:true,
				maxlength:[20]
			},
			baseIdList :{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入外部变量名称",
				maxlength:"外部变量名称长度不超过50个字符",
				isContainsSpecialChar:"外部变量名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在"
			},
			description:{
				required:"请输入外部变量描述",
				maxlength:"外部变量描述长度不超过200个字符"
			},
			type: {
				required: "请选择类型"
			},
			attributeName: {
				required: "请输入外部属性名",
				maxlength:"外部属性 名长度不超过20个字符"
			},
			initValue: {
				required: "请输入初始值",
				maxlength:"外部变量初始值长度不超过20个字符"
			},
			baseIdList :{
				required:"请选择所属包"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var variableSituationInfoForm = $('#variableSituationInfoForm').serializeObject();
			var baseIdArr = new Array();
			$("#base_showspace").find("input:checkbox:checked").each(function(i){
				baseIdArr.push($(this).val());			  
			});
			
			variableSituationInfoForm.baseIdList = baseIdArr;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/variablesituation/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(variableSituationInfoForm),
			    success: function () {
			    	alert("修改用户成功！");
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variablesituation/showList";
			    },
			    error: function () {
			        alert("edit regulation variablesituation error");
			    }	
			});
		}
	});

});

function loadSelect() {
	var type = "${baseVariableSituationDTO.type }";
	$("#type_select").find("option[value=" + type +"]").attr("selected", true);
}
function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variablesituation/showList";
}
</script>

<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">外部变量管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					外部变量管理 <small> <i class="icon-double-angle-right"></i>
						添加外部变量
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="variableSituationInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">外部变量名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入外部变量名称" 
							      	value="${baseVariableSituationDTO.name }">
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">外部变量描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入外部变量描述"
							      value="${baseVariableSituationDTO.description }">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="type" class="col-sm-1 control-label">类型</label>
								<div class="col-sm-3">
									<select class="form-control" id="type_select" name="type" >
									 	<option value="">- 类型 -</option>
							    		<option value="1">TEXT</option>
										<option value="2">DATE</option>
										<option value="3">INT</option>
										<option value="4">FLOAT</option>
										<option value="5">LONG</option>
										<option value="6">DOUBLE</option>
									</select>
								</div>
								<span id="error_type" style="color: red" class="col-sm-2"></span>
							  </div>
							  
							</fieldset>
							<fieldset>
							 <legend style="font-size: 16px; color: gray;">外部变量信息</legend>
							<div class="form-group">
								<label for="attributeName" class="col-sm-1 control-label">外部属性名</label>
							    <div class="col-sm-3">
							      <input type="text" id="attributeName" name="attributeName" class="form-control" placeholder="输入外部属性名"
							      value="${baseVariableSituationDTO.attributeName }">
							    </div>
							    <span id="error_attributeName" style="color: red" class="col-sm-2"></span>
							  <label for="initValue" class="col-sm-1 control-label">初始值</label>
							    <div class="col-sm-3">
							      <input type="text" id="initValue" name="initValue" class="form-control" placeholder="输入初始值"
							      value="${baseVariableSituationDTO.initValue }">
							    </div>
							    <span id="error_initValue" style="color: red" class="col-sm-2"></span>
							  </div> 
							  <div class="form-group">
							    <label for="base_showspace" class="col-sm-1 control-label">选择包</label>
							    <div id="base_showspace" class="col-sm-3">
							    	<c:forEach items="${baseVariableSituationDTO.baseElementDTOList}" var="base">
							    		<input type="checkbox"  value="${base.id}" name="baseIdList" <c:if test="${base.variableSituationFlag==1 }">checked</c:if> />${base.name}
							    	</c:forEach>
							    </div>
							    <span id="error_baseIdList" style="color: red" class="col-sm-2"></span>
							  </div> 
							</fieldset>
							<input type="hidden" id="oldName" name="oldName" value="${baseVariableSituationDTO.oldName}">
							<input type="hidden" name="id" value="${baseVariableSituationDTO.id }">
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
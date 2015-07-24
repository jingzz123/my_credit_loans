<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">

$().ready(function() {
	$("#actionVariableInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/actionvariable/checkActionVariable",
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
			variableId:{
				required:true	
			},
			content:{
				required:true,
				maxlength:[200]
			},
			baseIdList :{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入动作变量名称",
				maxlength:"动作变量名称长度不超过50个字符",
				isContainsSpecialChar:"动作变量名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在！"
			},
			description:{
				required:"请输入动作变量描述",
				maxlength:"动作变量描述长度不超过200个字符"
			},
			variableId:{
				required:"请选择简单变量"	
			},
			content:{
				required:"请输入简单变量的值",
				maxlength:"简单变量的值长度不超过200个字符"
			},
			baseIdList :{
				required:"请选择所属包"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var actionVariableInfoForm = $('#actionVariableInfoForm').serializeObject();
			var baseIdArr = new Array();
			$("#base_showspace").find("input:checkbox:checked").each(function(i){
				baseIdArr.push($(this).val());			  
			});
			
			actionVariableInfoForm.baseIdList = baseIdArr;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/actionvariable/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(actionVariableInfoForm),
			    success: function (actionVariableId) {
			    	if(actionVariableId>0){
				        alert("添加actionVariable成功！");
			    	}else{
			    		alert("添加actionVariable失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/actionvariable/showList";
			    },
			    error: function () {
			        alert("save regulation actionvariable error");
			    }	
			});
		}
	});
});


function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/actionvariable/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">动作变量管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					动作变量管理 <small> <i class="icon-double-angle-right"></i>
						添加动作变量
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="actionVariableInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">动作变量</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入动作变量名称" >
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">动作变量描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入动作变量描述">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							   <div class="form-group">
							  	<label for="variableId" class="col-sm-1 control-label">简单变量名称</label>
								<div class="col-sm-3">
									<select class="form-control" id="variableId_select" name="variableId" >
									 	<option value="">- 简单变量名称 -</option>
							    		<c:forEach items="${baseVariableSimpleDTOList}" var="simple" varStatus="status">
							    		<option value="${simple.id}">${simple.name}</option>
							    		</c:forEach>
									</select>
								</div>
								<span id="error_variableId" style="color: red" class="col-sm-2"></span>
								<label for="content" class="col-sm-1 control-label">简单变量的值</label>
							    <div class="col-sm-3">
							      <input type="text" id="content" name="content" class="form-control" placeholder="输入简单变量的值">
							    </div>
							    <span id="error_content" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							    <label for="base_showspace" class="col-sm-1 control-label">选择包</label>
							    <div id="base_showspace" class="col-sm-3">
							    	<c:forEach items="${baseElementDTOList}" var="base">
							    		<input type="checkbox"  value="${base.id}" name="baseIdList"/>${base.name}
							    	</c:forEach>
							    </div>
							    <span id="error_baseIdList" style="color: red" class="col-sm-2"></span>
							  </div> 
							</fieldset>
							 <input type="hidden" id="oldName" name="oldName" value="${baseActionVariableDTO.oldName}">
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
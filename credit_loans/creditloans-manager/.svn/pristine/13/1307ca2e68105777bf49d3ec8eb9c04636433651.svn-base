<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">

$().ready(function() {
	$("#variableExpressionInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/variableexpression/checkName",
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
			content: {
				required: true,
				maxlength:[200]
			},
			baseIdList :{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入表达式变量名称",
				maxlength:"表达式变量名称长度不超过50个字符",
				isContainsSpecialChar:"表达式变量名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在"
			},
			description:{
				required:"请输入表达式变量描述",
				maxlength:"表达式变量描述长度不超过200个字符"
			},
			type: {
				required: "请选择类型"
			},
			content: {
				required: "请输入表达式属性名",
				maxlength:"表达式长度不超过200个字符"
			},
			baseIdList :{
				required:"请选择所属包"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var variableExpressionInfoForm = $('#variableExpressionInfoForm').serializeObject();
			var baseIdArr = new Array();
			$("#base_showspace").find("input:checkbox:checked").each(function(i){
				baseIdArr.push($(this).val());			  
			});
			
			variableExpressionInfoForm.baseIdList = baseIdArr;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/variableexpression/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(variableExpressionInfoForm),
			    success: function (variableExpressionId) {
			    	if(variableExpressionId>0){
				        alert("添加variableExpression成功！");
			    	}else{
			    		alert("添加variableExpression失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variableexpression/showList";
			    },
			    error: function () {
			        alert("save regulation variableexpression error");
			    }	
			});
		}
	});
});


function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variableexpression/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">表达式变量管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					表达式变量管理 <small> <i class="icon-double-angle-right"></i>
						添加表达式变量
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="variableExpressionInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">表达式变量名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入表达式变量名称" >
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">表达式变量描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入表达式量描述">
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
							   <label for="content" class="col-sm-1 control-label">表达式</label>
							    <div class="col-sm-3">
							      <input type="text" id="content" name="content" class="form-control" placeholder="输入表达式">
							    </div>
							    <span id="error_content" style="color: red" class="col-sm-2"></span>
								</div>
								 <div class="form-group">
							    <label for="base1_showspace" class="col-sm-1 control-label">选择包</label>
							    <div id="base_showspace" class="col-sm-3">
							    	<c:forEach items="${baseElementDTOList}" var="base">
							    		<input type="checkbox"  value="${base.id}" name="baseIdList"/>${base.name}
							    	</c:forEach>
							    </div>
							    <span id="error_baseIdList" style="color: red" class="col-sm-2"></span>
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
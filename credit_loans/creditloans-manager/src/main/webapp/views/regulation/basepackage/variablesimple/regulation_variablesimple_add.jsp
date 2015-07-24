<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">

$().ready(function() {
	$("#variableSimpleInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/variablesimple/checkName",
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
			initValue:{
				required:true
			},
			baseIdList :{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入简单变量名称",
				maxlength:"简单变量名称长度不超过50个字符",
				isContainsSpecialChar:"元素名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在"
			},
			description:{
				required:"请输入简单变量描述",
				maxlength:"简单变量描述长度不超过200个字符"
			},
			type: {
				required: "请选择类型",
			},
			initValue:{
				required:"请输入初始值"
			},
			baseIdList :{
				required:"请选择所属包"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var variableSimpleInfoForm = $('#variableSimpleInfoForm').serializeObject();
			var baseIdArr = new Array();
			$("#base_showspace").find("input:checkbox:checked").each(function(i){
				baseIdArr.push($(this).val());			  
			});
			
			variableSimpleInfoForm.baseIdList = baseIdArr;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/variablesimple/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(variableSimpleInfoForm),
			    success: function (variableSimpleId) {
			    	if(variableSimpleId>0){
				        alert("添加variableSimple成功！");
			    	}else{
			    		alert("添加variableSimple失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variablesimple/showList";
			    },
			    error: function () {
			        alert("save regulation variableSimple error");
			    }	
			});
		}
	});
});


function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variablesimple/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">简单变量管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					简单变量管理 <small> <i class="icon-double-angle-right"></i>
						添加简单变量
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="variableSimpleInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">简单变量名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入简单变量名称" >
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">简单变量描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入简单变量描述">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							  	<label for="type" class="col-sm-1 control-label">类型</label>
								<div class="col-sm-3">
									<select id="type_select" name="type" class="form-control">
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
								<label for="initValue" class="col-sm-1 control-label">初始值</label>
							    <div class="col-sm-3">
							      <input type="text" id="initValue" name="initValue" class="form-control" placeholder="输入初始值">
							    </div>
							    <span id="error_initValue" style="color: red" class="col-sm-2"></span>
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
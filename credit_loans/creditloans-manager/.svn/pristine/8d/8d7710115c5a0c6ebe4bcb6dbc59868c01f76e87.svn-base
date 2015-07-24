<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">
$().ready(function() {
	$("#actionNameInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/actionname/checkActionName",
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
			baseIdList :{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入动作名称",
				maxlength:"动作名称长度不超过50个字符",
				isContainsSpecialChar:"动作名称只能由中文、英文、数字、“_”组成",
				remote:"该名称已存在！"
			},
			description:{
				required:"请输入动作描述",
				maxlength:"动作描述长度不超过200个字符"
			},
			baseIdList :{
				required:"请选择所属包"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var actionNameInfoForm = $('#actionNameInfoForm').serializeObject();
			var baseIdArr = new Array();
			$("#base_showspace").find("input:checkbox:checked").each(function(i){
				baseIdArr.push($(this).val());			  
			});
			
			actionNameInfoForm.baseIdList = baseIdArr;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/actionname/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(actionNameInfoForm),
			    success: function () {
			    	alert("修改用户成功！");
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/actionname/showList";
			    },
			    error: function () {
			        alert("edit regulation actionname error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/actionname/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">动作名称管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					动作名称管理 <small> <i class="icon-double-angle-right"></i>
						修改动作名称
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="actionNameInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">动作名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入动作名称" 
							      value="${baseActionNameDTO.name }">
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">动作描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入动作描述"
							      value="${baseActionNameDTO.description }">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							    <label for="base_showspace" class="col-sm-1 control-label">选择包</label>
							    <div id="base_showspace" class="col-sm-3">
							    	<c:forEach items="${baseActionNameDTO.baseElementDTOList}" var="base">
							    		<input type="checkbox"  value="${base.id}" name="baseIdList" <c:if test="${base.actionNameFlag==1 }">checked</c:if> />${base.name}
							    	</c:forEach>
							    </div>
							    <span id="error_baseIdList" style="color: red" class="col-sm-2"></span>
							  </div> 
							</fieldset>
							  <input type="hidden" id="oldName" name="oldName" value="${baseActionNameDTO.oldName}">
							 <input type="hidden" name="id" value="${baseActionNameDTO.id}">
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
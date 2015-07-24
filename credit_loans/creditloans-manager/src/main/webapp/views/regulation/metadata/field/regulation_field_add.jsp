<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">

$().ready(function() {
	$.fn.typeahead.Constructor.prototype.blur = function() {
		   var that = this;
		      setTimeout(function () { that.hide() }, 250);
		   };

	$('#product_search').typeahead({
		source:function(query, process)
		{
			$.ajax({
			url: "${pageContext.request.contextPath}/regulation/metadata/field/checkName",
			type: 'get',
			dataType: 'JSON',
			async: true,
			data: 'query=' + query ,
			success: function(data)
				{
				var jsonobj = eval(data);
				var arr = new Array();
					for(var p in jsonobj){
						arr.push(jsonobj[p]);
					}
					
				process(arr);
				}
			});
		}
	});

	$("#fieldInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[20],
				isRightfulString:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/metadata/field/checkName",
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
				maxlength:[100]
			},
			type:{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入field名称",
				maxlength:"field名称长度不超过20个字符",
				isRightfulString:"field名称只能由英文、数字 组成",
				remote:"该名称已存在！"
			},
			description:{
				required:"请输入field描述",
				maxlength:"field描述长度不超过100个字符"
			},
			type:{
				required:"请输入field类型"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var fieldInfoForm = $('#fieldInfoForm').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/metadata/field/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(fieldInfoForm),
			    success: function (fieldId) {
			    	if(fieldId>0){
				        alert("添加field成功！");
			    	}else{
			    		alert("添加field失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/regulation/metadata/field/showList?schemaId=${schemaId}";
			    },
			    error: function () {
			        alert("save regulation field error");
			    }	
			});
		}
	});
});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/metadata/field/showList?schemaId=${schemaId}";
}
</script>
<div style="margin: 50px 50px">
<label for="product_search">Product Search: </label>
<input type="text"  id="product_search"/>
</div>
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">field管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					field管理 <small> <i class="icon-double-angle-right"></i>
						添加field
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="fieldInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">field名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入field名称" >
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							    <label for="type" class="col-sm-1 control-label">数据类型</label>
								<div class="col-sm-3">
									<select id="type_select" name="type" class="form-control">
										<option value="">- 数据类型 -</option>
										<option value="1">serial</option>
										<option value="2">integer</option>
										<option value="3">numeric</option>
										<option value="4">character varying</option>
										<option value="5">timestamp</option>
									</select>
								</div>
								<span id="error_type" style="color: red" class="col-sm-2"></span>
							  </div>
							  
							  <div class="form-group">
							    <label for="description" class="col-sm-1 control-label">field描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入描述">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							</fieldset>
							 <input type="hidden" name="schemaId" value="${schemaId }">
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
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%> 
<script type="text/javascript">

$().ready(function() {
	$("#conditionInfoForm").validate({
	    onkeyup: false,
	    rules: {
			name:{
				required:true,
				maxlength:[50],
				isContainsSpecialChar:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/condition/checkName",
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
			lhs:{
				required:true
			},
			operator:{
				required:true
			},
			rhs:{
				required:true
			},
			type:{
				required:true
			},
			sequence:{
				required:true,
				number:true,
				remote:{
					url:"${pageContext.request.contextPath}/regulation/basepackage/condition/checkSequence",
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
			lhs:{
				required:"请输入右侧表达式"
			},
			operator:{
				required:"请输入操作符"
			},
			rhs:{
				required:"请输入左侧表达式" 
			},
			type:{
				required:"请输入类型"
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
			var conditionInfoForm = $('#conditionInfoForm').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/regulation/basepackage/condition/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(conditionInfoForm),
			    success: function (conditionId) {
			    	if(conditionId>0){
				        alert("添加condition成功！");
			    	}else{
			    		alert("添加condition失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/condition/showList?rulesetId=${rulesetId}";
			    },
			    error: function () {
			        alert("save regulation condition error");
			    }	
			});
		}
	});
	$.fn.typeahead.Constructor.prototype.blur = function() {
		   var that = this;
		      setTimeout(function () { that.hide() }, 250);
	};

	$('#lhs').typeahead({
		source:function(query, process)
		{
			$.ajax({
			url: "${pageContext.request.contextPath}/regulation/basepackage/condition/getVariable",
			type: 'get',
			dataType: 'JSON',
			async: true,
			data: 'name=' + query ,
			success: function(data)
				{
				var jsonobj = eval(data);
				var arr = new Array();
					for(var p in jsonobj){
						arr.push("$"+jsonobj[p]);
					}
				process(arr);
				}
			});
		}
	});
	$('#rhs').typeahead({
		source:function(query, process)
		{
			$.ajax({
			url: "${pageContext.request.contextPath}/regulation/basepackage/condition/getVariable",
			type: 'get',
			dataType: 'JSON',
			async: true,
			data: 'name=' + query ,
			success: function(data)
				{
				var jsonobj = eval(data);
				var arr = new Array();
					for(var p in jsonobj){
						arr.push("$"+jsonobj[p]);
					}
				process(arr);
				}
			});
		}
	});
});


function goback(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/condition/showList?rulesetId=${rulesetId}";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">条件管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					条件管理 <small> <i class="icon-double-angle-right"></i>
						添加条件
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="conditionInfoForm" class="form-horizontal" >
				    	<fieldset>
							  <div class="form-group">
							    <label for="name" class="col-sm-1 control-label">条件名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入条件名称" >
							    </div>
							    <span id="error_name" style="color: red" class="col-sm-2"></span>
							   <label for="description" class="col-sm-1 control-label">条件描述</label>
							    <div class="col-sm-3">
							      <input type="text" id="description" name="description" class="form-control" placeholder="输入条件描述">
							    </div>
							    <span id="error_description" style="color: red" class="col-sm-2"></span>
							  </div>
							  <div class="form-group">
							    <label for="lhs" class="col-sm-1 control-label">右侧表达式</label>
								<div class="col-sm-3">
							      <input type="text" id="lhs" name="lhs" class="form-control" placeholder="输入右侧表达式" >
							    </div>
								<span id="error_lhs" style="color: red" class="col-sm-2"></span>
								<label for="rhs" class="col-sm-1 control-label">左侧表达式</label>
								<div class="col-sm-3">
							      <input type="text" id="rhs" name="rhs" class="form-control" placeholder="输入左侧表达式" >
							    </div>
								<span id="error_rhs" style="color: red" class="col-sm-2"></span>
							  </div>
							  
							  <div class="form-group">
							  	<label for="operator" class="col-sm-1 control-label">操作符</label>
								<div class="col-sm-3">
									<select id="operator_select" name="operator" class="form-control">
										<option value="">- 操作符 -</option>
										<option value="1">=</option>
										<option value="2">!=</option>
										<option value="3">>=</option>
										<option value="4">></option>
										<option value="5">&lt;=</option>
										<option value="6">&lt;</option>
										<option value="7">Match</option>
									</select>
								</div>
								<span id="error_operator" style="color: red" class="col-sm-2"></span>
							  	<label for="type" class="col-sm-1 control-label">两边数据类型</label>
								<div class="col-sm-3">
									<select id="type_select" name="type" class="form-control">
										<option value="">- 两边数据类型-</option>
										<option value="1">Number</option>
										<option value="2">Text</option>
										<option value="3">Date</option>
									</select>
								</div>
								<span id="error_type" style="color: red" class="col-sm-2"></span>
							  </div >
							  <div class="form-group">
								<label for="sequence" class="col-sm-1 control-label">次序号</label>
							    <div class="col-sm-3">
							      <input type="text" id="sequence" name="sequence" class="form-control" placeholder="输入次序号">
							    </div>
							    <span id="error_sequence" style="color: red" class="col-sm-2"></span>
							</div>
							</fieldset>
							  <input type="hidden" name="rulesetId" value="${rulesetId }">
							  <input type="hidden" name="oldName" value="">
							  <input type="hidden" name="oldSequence" value="">
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
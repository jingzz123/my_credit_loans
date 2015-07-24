<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ include  file="./../../common/header.jsp"%>
<!-- 二级菜单 begin -->
<div class="sidebar" id="sidebar">
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<div class="icon-text">
				<i class="icon-group"></i> <span> 企业管理 </span>
			</div>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->
	<ul class="nav nav-list">
		<li><a href="${pageContext.request.contextPath}/enterprise/user/showList"> <i class="icon-user"></i> <span class="menu-text"> 用户管理 </span> </a> </li>				
		<li><a href="${pageContext.request.contextPath}/enterprise/role/showList"> <i class="icon-star"></i> <span class="menu-text"> 角色管理 </span> </a> </li>
		<li class="active"><a href="${pageContext.request.contextPath}/platform/dept/platformDepartList"> <i class="icon-th"></i> <span class="menu-text"> 部门信息管理 </span> </a> </li>
	</ul>
	<!-- /.nav-list -->
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>
</div>
<!-- 二级菜单 end -->  
<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
	    <ul class="breadcrumb">
		    <li><i class="icon-home home-icon"></i><a href="#">首页</a></li>
			<li class="active">部门信息</li>
			<li class="active">部门信息添加</li>
		</ul>
	</div>
	<div class="page-content">
	   <div class="page-header">
		    <h1>
				部门信息
				<small> 
				    <i class="icon-double-angle-right"></i>添加部门信息
				</small>
			</h1>
		</div><!-- /.page-header -->
		<div class="row">
		    <div class="col-xs-12">
			    <div class="col-md-12">
				    <form id="platformDept" class="form-horizontal" >
				       <div class="form-group">
						    <label for="name" class="col-sm-3 control-label">上级部门名称：</label>
							<div class="col-sm-4">
								<input id="deptName" name="deptName" type="text" class="form-control" onclick="showModal()" placeholder="..上级部门名称" readonly="readonly" value="${department.parentName}"/>
								<input type="hidden" id="fid" name="fid" value="${department.fid}"/>
								<input type="hidden" id="id" name="strId" value="${department.id}"/>
							</div>
						    <span id="error_deptName" style="color: red"></span>
						</div>
				        <div class="form-group">
						    <label for="name" class="col-sm-3 control-label">部门名称：</label>
							<div class="col-sm-4">
						    	<input type="text" id="name" name="name" class="form-control" placeholder="..部门名称" value="${department.name}" />
							</div>
						    <span id="error_name" style="color: red"></span>
						</div>
					    <div class="form-group">
						    <label for="name" class="col-sm-3 control-label">部门编号：</label>
							<div class="col-sm-4">
						    	<input type="text" id="code" name="code" class="form-control" placeholder="..部门编号" value="${department.code}" />
							</div>
						    <span id="error_code" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">部门描述：</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="description" placeholder="..部门描述" value="${department.description}"/>
							</div>
						    <span id="error_description" style="color: red"></span>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-5">
								<input type="submit" class="btn btn-primary" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="#" class="btn btn-success " onclick="goback()">返回</a>
							</div>
						</div>
					</form>
				</div>
		    </div>
		</div> <!-- class:row -->
	</div>
</div>
<div class="modal fade" id="selectsDeptModal">
    <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">×</span></button>
	        <h4 class="modal-title">部门信息</h4>
	      </div>
	      <div class="modal-body" style="height:300px;width: 200px;">
				<ul id="deptTree" class="ztree" ></ul>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-default" onclick="departmentInfo()">确定</button>
	      	<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal fade end -->   

<script type="text/javascript">
	$(function(){
		$("#platformDept").validate({
		    onkeyup: false,
			rules: {
				deptName:{
					required:true
				},
				name:{
					required:true,
					rangelength:[2,20],
					isContainsSpecialChar:true,
				    remote:{
						url:"${pageContext.request.contextPath}/platform/dept/checkDepartmentName",
						type: "post",
					    dataType: "json",
					    data: {
					    	name: function() {
					            return $("#name").val();
					        },
					        fid:function(){
					        	return $("#fid").val();
					        },
					        id:function(){
					        	return $("#id").val();
					        }
					    }
					}
				} ,
				code:{
						required:true,
						digits:true,
					    remote:{
							url:"${pageContext.request.contextPath}/platform/dept/checkDepartmentCode",
							type: "post",
						    dataType: "json",
						    data: {
						    	code: function() {
						            return $("#code").val();
						        },
						        id:function(){
						        	return $("#id").val();
						        }
						    }
						}
					},
				description:{
						rangelength:[2,120],
						isContainsSpecialChar:true
					} 
			},
			messages:{
				deptName:{
					required:"上级部门名称不能为空"
				},
				name:{
					required:"请输入部门名称",
					rangelength:"部门名称长度为2~20个字符",
					isContainsSpecialChar:"部门名称只能由中文、英文",
				    remote:"同一级节点名称不能重复"
				} ,
				code:{
					required:"部门编号不能为空",
					digits:"部门编号只能是数字",
				    remote:"部门编号不能重复"
				},
				description:{
					rangelength:"部门描述长度为2~120个字符",
					isContainsSpecialChar:"部门描述只能是数字、英文和中文"
				} 
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent().siblings("span"));
			},
			submitHandler:function(){
				$.ajax({
					 type:"POST",
					 url:"${pageContext.request.contextPath}/platform/dept/saveOrUpdateDepartment",
					 data:$("#platformDept").serialize(),
					 success:function(msg){
						 if(msg){
							 alert("保存部门信息成功");
							 goback();
						 }else{
							 alert("保存部门信息失败");
						 }
					 },
					 error: function () {
			        	alert("保存部门信息失败");
			    	}	
				});
			}
		});
	});
	
	//返回
	function goback(){
		window.location.href = "${pageContext.request.contextPath}/platform/dept/platformDepartList"
	}
	
	function showModal(){
		initDepartmentTree();
		$("#selectsDeptModal").modal("show"); 
	}
	
	function departmentInfo(){
		var zTree = $.fn.zTree.getZTreeObj("deptTree");
		var nodes = zTree.getSelectedNodes();
		if(nodes != null && "" != nodes){
			for(var index = 0;index < nodes.length;index++){
				$("#deptName").empty();
				$("#fid").empty();
				$("#deptName").attr("value",nodes[index].name);
				$("#fid").attr("value",nodes[index].id);
				$("#selectsDeptModal").modal("hide");
			}
		}else{
			alert("选择的部门信息不能为空");
		}
	}
	
	//初始化组织机构树
	function initDepartmentTree(){
		//动态加载菜单树
		var setting = {
						check: {
						    enable: false
						},
						data: {
							simpleData: {
							    enable: true
							}
						},
						callback: {
						    onClick: function(event, treeId, treeNode){
						    	
						    }
						}
					};
		$.ajax({
			url:"${pageContext.request.contextPath}/platform/dept/departmentTree",
			type:"POST",
			success:function(data){
				var treeNodes = eval(data);
				$.fn.zTree.init($("#deptTree"), setting, treeNodes);
			}
		});
	}
</script>
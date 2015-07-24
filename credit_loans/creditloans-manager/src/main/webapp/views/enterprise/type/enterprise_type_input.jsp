<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include  file="./../../common/header.jsp"%>
<!-- 二级菜单 begin -->
		<div class="sidebar" id="sidebar">
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<div class="icon-text"> <i class="icon-group"></i> <span> 企业管理 </span></div>
				</div>
			</div>
			<!-- #sidebar-shortcuts -->
			<ul class="nav nav-list">
				<li> <a href="${pageContext.request.contextPath}/enterprise/user/showList"> <i class="icon-user"></i> <span class="menu-text"> 用户管理 </span> </a> </li>				
				<li> <a href="${pageContext.request.contextPath}/enterprise/role/showList"> <i class="icon-star"></i> <span class="menu-text"> 角色管理 </span> </a> </li>
				<li> <a href="${pageContext.request.contextPath}/enterprise/info/enterpriseInfoList"> <i class="icon-th"></i> <span class="menu-text"> 企业信息管理 </span> </a> </li>
				<li class="active"> <a href="${pageContext.request.contextPath}/enterprise/type/typeList"> <i class="icon-th"></i> <span class="menu-text"> 企业类型管理 </span> </a> </li>
			</ul>
			<!-- /.nav-list -->
			<div class="sidebar-collapse" id="sidebar-collapse"> <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i> </div>
		</div>
		<!-- 二级菜单 end -->  
<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
	    <ul class="breadcrumb">
		    <li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li class="active">企业类型</li>
			<li class="active">企业类型添加</li>
		</ul>
	</div>
	<div class="page-content">
	   <div class="page-header">
		    <h1>
				企业类型<small> <i class="icon-double-angle-right"></i>
						添加企业类型
				</small>
			</h1>
		</div><!-- /.page-header -->
		<div class="row">
		    <div class="col-xs-12">
			    <div class="col-md-12">
				    <form id="enterpriseType" class="form-horizontal" >
				        <div class="form-group">
						    <label for="name" class="col-sm-4 control-label">企业类型：</label>
							<div class="col-sm-3">
						    	<input type="text" id="name" name="name" class="form-control" placeholder="..请输入企业类型" value="${enterpriseType.name}" />
						    	<input type="hidden" name="idString" value="${enterpriseType.id}"/>
							</div>
						    <span id="error_name" style="color: red"></span>
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
<script type="text/javascript">
	$(function(){
		$("#enterpriseType").validate({
		    onkeyup: false,
			rules: {
				name:{
					required:true,
					rangelength:[2,20],
					isContainsSpecialChar:true,
				    remote:{
						url:"${pageContext.request.contextPath}/enterprise/type/checkEnterpriseName",
						type: "post",
					    dataType: "json",
					    data: {
					    	email: function() {
					            return $("#name").val();
					        }
					    }
					}
				}
			},
			messages:{
				name:{
					required:"请输入企业类型",
					rangelength:"企业类型长度为2~20个字符",
					isContainsSpecialChar:"用户名称只能由中文、英文",
				    remote:"该企业类型已存在！"
				}
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent().siblings("span"));
			},
			submitHandler:function(){
				$.ajax({
					 type:"POST",
					 url:"${pageContext.request.contextPath}/enterprise/type/saveOrUpdateEnterpriseType",
					 data:$("#enterpriseType").serialize(),
					 success:function(msg){
						 if(msg){
							 alert("保存企业类型信息成功");
							 goback();
						 }else{
							 alert("保存企业类型信息失败");
						 }
					 },
					 error: function () {
			        	alert("保存企业信息失败");
			    	}	
				});
			}
		});
	});
	
	//返回
	function goback(){
		window.location.href = "${pageContext.request.contextPath}/enterprise/type/typeList"
	}
</script>
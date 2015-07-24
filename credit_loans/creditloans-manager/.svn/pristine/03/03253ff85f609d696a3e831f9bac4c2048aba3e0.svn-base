<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../common/header.jsp"%> 
<script type="text/javascript">

$().ready(function() {
	$("#userInfoForm").validate({
	    onkeyup: false,
		rules: {
			name:{
				required:true,
				rangelength:[2,20],
				isContainsSpecialChar:true
			},
			email:{
			    required: true,
			    email: true,
			    remote:{
					url:"${pageContext.request.contextPath}/enterprise/user/checkEmail",
					type: "post",
				    dataType: "json",
				    data: {
				    	email: function() {
				            return $("#email").val();
				        }
				    }
				}
			},
			password:{
				required: true,
				rangelength:[6,16],
				isContainsSpecialChar:true
			},
			tel:{
				isPhone: true
			},
			mobile:{
				required: true,
				isMobile: true
			},
			fax:{
				isPhone: true
			},
			enterpriseId:{
                min:1
			},
			//depId:{
              //  min:1
			//},
			roleIdList:{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入用户名称",
				rangelength:"用户名称长度为2~20个字符",
				isContainsSpecialChar:"用户名称只能由中文、英文、数字、“_”组成"
			},
			email:{
			    required: "请输入登录账号",
			    email: "请输入正确格式的电子邮件地址",
			    remote:"登录账号已存在请重新输入"
			},
			password:{
				required: "请输入登录密码",
				rangelength:"登录密码长度为6~16个字符",
				isContainsSpecialChar:"登录密码只能由英文、数字、“_”组成"
			},
			tel:{
				isPhone: "请输入正确的座机号码"
			},
			mobile:{
				required: "请输入手机号码",
				isMobile: "请输入正确的手机号码"
			},
			fax:{
				isPhone: "请输入正确的传真号码"
			},
			enterpriseId:{
                min:"请选择企业"
			},
			//depId:{
                //min:"请选择部门"
			//},
			roleIdList:{
				required:"请选择角色"
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.parent().siblings("span"));
		},
		submitHandler:function(){
			var userInfoJson = $('#userInfoForm').serializeObject();
			var roleIdArr = new Array();
			$("#role_showspace").find("input:checkbox:checked").each(function(i){
				roleIdArr.push($(this).val());			  
			});
			
			userInfoJson.roleIdList = roleIdArr;
			
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/enterprise/user/doAdd",
			    contentType: "application/json",
			    data: JSON.stringify(userInfoJson),
			    success: function (enterpriseUserId) {
			    	if(enterpriseUserId>0){
				        alert("添加用户成功！");
			    	}else{
			    		alert("添加用户失败！");
			    		
			    	}
			    	window.location.href = "${pageContext.request.contextPath}/enterprise/user/showList";
			    },
			    error: function () {
			        alert("save enterprise user error");
			    }	
			});
		}
	});

});

function loadDepAndRole(){
	//getDepartment();
	getRole();
}

/* function getDepartment(){
	var enterpriseId = $("#enterprise_select option:selected").val();
	if(enterpriseId!="0"){
		jQuery.ajax({
	        type: 'POST',
	        url: "${pageContext.request.contextPath}/enterprise/user/getDepList?enterpriseId="+enterpriseId,
	        success: function (data) {
	        	var htmlStr = "<option value='0'>- 所属部门 -</option>";
	        	var dataObj;
	        	for(var i = 0;i<data.length;i++){
	        		dataObj = data[i];
	        		htmlStr = htmlStr + "<option value='"+dataObj["id"]+"'>"+dataObj["name"]+"</option>"
	        	}
	        	$("#department_select").empty();
	        	$("#department_select").append(htmlStr);
	        	$("#department_select").prop("disabled", false);
	        	
	        }
	    });
	}else{
		$("#department_select").empty();
		$("#department_select").append("<option value='0'>- 所属部门 -</option>");
		$("#department_select").prop("disabled", true);
	}
} */

function getRole(){
	var enterpriseId = $("#enterprise_select option:selected").val();
	if(enterpriseId!="0"){
		jQuery.ajax({
	        type: 'POST',
	        url: "${pageContext.request.contextPath}/enterprise/user/getRoleList?enterpriseId="+enterpriseId,
	        success: function (data) {
	        	var htmlStr = "";
	        	var dataObj;
	        	for(var i = 0;i<data.length;i++){
	        		dataObj = data[i];
	        		htmlStr = htmlStr + "<input type='checkbox' value='"+dataObj["id"]+"' name='roleIdList'/>" +dataObj["name"]
	        	}
	        	$("#role_showspace").empty();
	        	$("#role_showspace").html(htmlStr);	        	
	        }
	    });
	}else{
		$("#role_showspace").empty();
		$("#role_showspace").html("<input type='checkbox'  value='' name='roleIdList' disabled='disabled'/>选择企业后加载...");
	}
}

function goback(){
	window.location.href = "${pageContext.request.contextPath}/enterprise/user/showList";
}
</script>
	<!-- 二级菜单 begin -->
		<div class="sidebar" id="sidebar">
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<div class="icon-text"> <i class="icon-group"></i> <span> 企业管理 </span></div>
				</div>
			</div>
			<!-- #sidebar-shortcuts -->
			<ul class="nav nav-list">
				<li class="active"> <a href="${pageContext.request.contextPath}/enterprise/user/showList"> <i class="icon-user"></i> <span class="menu-text"> 用户管理 </span> </a> </li>				
				<li> <a href="${pageContext.request.contextPath}/enterprise/role/showList"> <i class="icon-star"></i> <span class="menu-text"> 角色管理 </span> </a> </li>
				<li> <a href="${pageContext.request.contextPath}/enterprise/info/enterpriseInfoList"> <i class="icon-th"></i> <span class="menu-text"> 企业信息管理 </span> </a> </li>
				<li> <a href="${pageContext.request.contextPath}/enterprise/type/typeList"> <i class="icon-th"></i> <span class="menu-text"> 企业类型管理 </span> </a> </li>
			</ul>
			<!-- /.nav-list -->
			<div class="sidebar-collapse" id="sidebar-collapse"> <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i> </div>
		</div>
		<!-- 二级菜单 end -->
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">企业管理</a></li>
				<li class="active">用户管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					用户管理 <small> <i class="icon-double-angle-right"></i>
						添加用户
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="userInfoForm" class="form-horizontal" >
							  <div class="form-group">
							    <label for="name" class="col-sm-4 control-label">用户名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" placeholder="输入用户名称" >
							    </div>
							    <span id="error_name" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="email" class="col-sm-4 control-label">登录帐号</label>
							    <div class="col-sm-3">
							      <input type="text" id="email" name="email" class="form-control" placeholder="输入登录(email)帐号">
							    </div>
							    <span id="error_email" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="password" class="col-sm-4 control-label">登录密码</label>
							    <div class="col-sm-3">
							      <input type="password" id="password" name="password" class="form-control" placeholder="输入登录密码">
							    </div>
							    <span id="error_password" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="tel" class="col-sm-4 control-label">电话号码</label>
							    <div class="col-sm-3">
							      <input type="text" id="tel" name="tel" class="form-control" placeholder="输入电话号码">
							    </div>
							    <span id="error_tel" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="tel" class="col-sm-4 control-label">手机号码</label>
							    <div class="col-sm-3">
							      <input type="text" id="mobile" name="mobile" class="form-control" placeholder="输入手机号码">
							    </div>
							    <span id="error_mobile" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="tel" class="col-sm-4 control-label">传真号码</label>
							    <div class="col-sm-3">
							      <input type="text" id="fax" name="fax" class="form-control" placeholder="输入传真号码">
							    </div>
							    <span id="error_fax" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="isinvalid_alive" class="col-sm-4 control-label">用户状态</label>
							    <div class="col-sm-3">
							      <input id="isinvalid_alive" name="isinvalid" type="radio" checked="checked" value="0"/> 有效 
							      <input id="isinvalid_forbidden" name="isinvalid" type="radio" value="1"/> 无效
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="depId" class="col-sm-4 control-label">所属企业</label>
							    <div class="col-sm-3">							    
							    	<select id="enterprise_select" name="enterpriseId">
							    		<option value="0">- 所属企业 -</option>
							    		<c:forEach items="${enterpriseDtoList}" var="enterprise">
							    		<option value="${enterprise.id}">${enterprise.name}</option>
							    		</c:forEach>
							    	</select>
							    </div>
							    <span id="error_enterprise" style="color: red"></span>
							  </div>
							  <!-- <div class="form-group">
							    <label for="depId" class="col-sm-4 control-label">所属部门</label>
							    <div class="col-sm-3">							    
							    	<select id="department_select" name="depId" disabled="disabled">
							    		<option>- 所属部门 -</option>
							    	</select>
							    </div>
							    <span id="error_department" style="color: red"></span>
							  </div> -->
							  <!-- 角色选择 begin -->
							  <div class="form-group">
							    <label for="role_showspace" class="col-sm-4 control-label">选择角色</label>
							    <div id="role_showspace" class="col-sm-3">
							    	<c:forEach items="${enterpriseRoleDtoList}" var="role">
							    		<input type="checkbox"  value="${role.id}" name="roleIdList"/>${role.name}
							    	</c:forEach>
							    	<!-- 
									<input type="checkbox"  value="" name="roleIdList" disabled="disabled"/>选择企业后加载... -->
							    </div>
							    <span id="error_role" style="color: red"></span>
							  </div> <!-- 角色选择  end-->
							  
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
<%@ include  file="./../../common/footer.jsp"%>
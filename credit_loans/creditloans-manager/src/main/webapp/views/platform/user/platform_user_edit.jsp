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
			tel:{
				isTel: true
			},
			phone:{
				isMobile:true
			},
			qq:{
				isQq:true
			},
			/* depId:{
                min:1
			}, */
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
			tel:{
				isTel: "请输入正确的座机或手机号码"
			},
			phone:{
				isMobile:"请输入正确的手机号码"
			},
			qq:{
				isQq:"请输入正确的QQ号码"
			},
			/* depId:{
                min:"请选择部门"
			}, */
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
			userInfoJson.password = $("#password").val();
			userInfoJson.email = $("#email").val();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/platform/user/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(userInfoJson),
			    success: function () {
			    	alert("修改用户成功！");
			    	window.location.href = "${pageContext.request.contextPath}/platform/user/showList";
			    },
			    error: function () {
			        alert("edit platform user error");
			    }	
			});
		}
	});

});

function resetPwd(pwd){
	var password = pwd;
	if (!confirm("是否要重置密码?")) {
		return;
	}
	$("#password").val(password);
}


function goback(){
	window.location.href = "${pageContext.request.contextPath}/platform/user/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">平台管理</a></li>
				<li class="active">用户管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					用户管理<small> <i class="icon-double-angle-right"></i>
						修改用户
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
							      <input type="text" id="name" name="name" class="form-control" value="${platformUserDto.name }" placeholder="输入用户名称" >
							    </div>
							    <span id="error_name" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="email" class="col-sm-4 control-label">登录帐号</label>
							    <div class="col-sm-3">
							      <input type="text" id="email" name="email" class="form-control" value="${platformUserDto.email }" placeholder="输入登录(email)帐号" readonly="readonly">
							    </div>
							    <span id="error_email" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="password" class="col-sm-4 control-label">登录密码</label>
							    <div class="col-sm-3">
							      <input type="password" id="password" name="password" class="form-control" value="${platformUserDto.password }" placeholder="输入登录密码" readonly="readonly">
							    </div>
							    <span id="error_password" style="color: red"></span>
							    <input type="button" class="btn btn-primary btn-sm" value="重置密码" onclick="resetPwd(${resetPwd})" /> <font color="green">* 密码重置后为${resetPwd}</font>
							  </div>
							  <div class="form-group">
							    <label for="tel" class="col-sm-4 control-label">电话号码</label>
							    <div class="col-sm-3">
							      <input type="text" id="tel" name="tel" class="form-control" value="${platformUserDto.tel }" placeholder="输入电话号码">
							    </div>
							    <span id="error_tel" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="phone" class="col-sm-4 control-label">手机号码</label>
							    <div class="col-sm-3">
							      <input type="text" id="phone" name="phone" class="form-control" value="${platformUserDto.phone }" placeholder="输入手机号码">
							    </div>
							    <span id="error_phone" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="qq" class="col-sm-4 control-label">QQ号码</label>
							    <div class="col-sm-3">
							      <input type="text" id="qq" name="qq" class="form-control" value="${platformUserDto.qq }" placeholder="输入QQ号码">
							    </div>
							    <span id="error_qq" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="status_alive" class="col-sm-4 control-label">用户状态</label>
							    <div class="col-sm-3">
							    <c:if test="${platformUserDto.status == 0}">
								    <input id="status_alive" name="status" type="radio" checked="checked" value="0"/> 可用 
								    <input id="status_forbidden" name="status" type="radio" value="1"/> 禁用
							    </c:if>
							    <c:if test="${platformUserDto.status == 1}">
							    	<input id="status_alive" name="status" type="radio" value="0"/> 可用
								    <input id="status_forbidden" name="status" type="radio" checked="checked" value="1"/> 禁用
							    </c:if>  
							    </div>
							  </div>
							  <!-- 
							  <div class="form-group">
							    <label for="depId" class="col-sm-4 control-label">所属部门</label>
							    <div class="col-sm-3">							    
							    	<select id="department_select" name="depId">
							    		<option value="0">- 所属部门 -</option>
							    		<c:forEach items="${platformDepartmentDtoList}" var="department">
								    		<c:if test="${platformUserDto.depId == department.id}">
								    			<option value="${department.id}" selected="selected">${department.name}</option>
								    		</c:if>
								    		<c:if test="${platformUserDto.depId != department.id}">
								    			<option value="${department.id}">${department.name}</option>
								    		</c:if>
							    		</c:forEach>
							    	</select>
							    </div>
							    <span id="error_department" style="color: red"></span>
							  </div>  -->
							  <!-- 角色选择 begin -->
							  <div class="form-group">
							    <label for="role_showspace" class="col-sm-4 control-label">选择角色</label>
							    <div id="role_showspace" class="col-sm-3">
									<c:forEach items="${platformUserDto.platformRoleDtoList }" var="role" varStatus="status">
							    		<c:if test="${role.ownerFlag == 1 }">
							    			<input type="checkbox" value="${role.id }" name="roleIdList" checked/>${role.name }
							    		</c:if>
							    		<c:if test="${role.ownerFlag == 0 }">
							    			<input type="checkbox" value="${role.id }" name="roleIdList" />${role.name }
							    		</c:if>
							        </c:forEach>
							    </div>
							    <span id="error_role" style="color: red"></span>
							  </div> <!-- 角色选择  end-->
							  <input type="hidden" name="id" value="${platformUserDto.id }">
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
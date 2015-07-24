<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../common/header.jsp"%>
<script type="text/javascript">
//menu树设置
var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
$().ready(function() {
	getAllMenuTree();
	$("#roleInfoForm").validate({
	    onkeyup: false,
		rules: {
			name:{
				required:true,
				rangelength:[2,20],
				isContainsSpecialChar:true
			}//,
			//enterpriseId:{
            //    min:1
			//}
		},
		messages:{
			name:{
				required:"请输入角色名称",
				rangelength:"角色名称长度为2~20个字符",
				isContainsSpecialChar:"角色名称只能由中文、英文、数字、“_”组成"
			}//,
			//enterpriseId:{
            //    min:"请选择企业"
			//}
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.parent().siblings("span"));
		},
		submitHandler:function(){
			var roleInfoJson = $('#roleInfoForm').serializeObject();
			// 处理选取的菜单数据
			var menuIdArr = new Array();
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var checkedNodes = zTree.getCheckedNodes(true);
			var menuname = $("#menuname").val();
			if(checkedNodes.length>0 && menuname!=""){
				for(var i=0, l=checkedNodes.length; i<l; i++){
					menuIdArr.push(checkedNodes[i].id);
				}
				roleInfoJson.menuIdList = menuIdArr;
				jQuery.ajax({
					type: "post",
				    url: "${pageContext.request.contextPath}/enterprise/role/doEdit",
				    contentType: "application/json",
				    data: JSON.stringify(roleInfoJson),
				    success: function (roleId) {
				    	alert("修改角色成功!");
				    	window.location.href = "${pageContext.request.contextPath}/enterprise/role/showList";
				    },
				    error: function (data) {
				    	var err_msg = data.responseText;
			        	if(err_msg.indexOf("账户异常，请重新登录")) {
				        	alert("账户异常，请重新登录");
				        	window.location.href="${pageContext.request.contextPath}/login";
			        	} else {
					        alert("save role error");
			        	}
				    }	
				});
			}else{
				$('#error_menu').empty();
				var menuError = "请选择菜单权限";
				$('#error_menu').append(menuError);
			}
		}
	});

});

//动态加载菜单树
function getAllMenuTree(){
	//动态加载菜单树
	jQuery.ajax({
        type: 'POST',
		async: false,
        url: "${pageContext.request.contextPath}/enterprise/menu/getAllMenuTree",
        success: function (data) {
        	var zNodes = eval(data);
        	var menuIdList = eval("${enterpriseRoleDto.menuIdList}");
        	var menuList = "";
        	if(menuIdList.length>0){
	        	for(var i=0;i<menuIdList.length;i++){
	        		for(var j=0;j<zNodes.length;j++){
	        			if(zNodes[j].id==menuIdList[i]){
	        				zNodes[j].checked=true;
	        				menuList = menuList+zNodes[j].name+" | ";
	        			}
	        		}
	        	}
	        	$("#menuname").val(menuList.substring(0,menuList.length-2));
        	}else{
        		$("#menuname").val("");
        	}
        	
        	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }
    });
}

function doSelectMenu(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var checkedNodes = zTree.getCheckedNodes(true);
	if(checkedNodes.length>0){
		var menuList = "";
		for(var i=0;i<checkedNodes.length;i++){
			menuList = menuList+checkedNodes[i].name+" | ";
		}
		$("#menuname").val(menuList.substring(0,menuList.length-2));
	}else{
		$("#menuname").val("");
	}
}

function goback(){
	window.location.href = "${pageContext.request.contextPath}/enterprise/role/showList";
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
				<li> <a href="${pageContext.request.contextPath}/enterprise/user/showList"> <i class="icon-user"></i> <span class="menu-text"> 用户管理 </span> </a> </li>				
				<li class="active"> <a href="${pageContext.request.contextPath}/enterprise/role/showList"> <i class="icon-star"></i> <span class="menu-text"> 角色管理 </span> </a> </li>
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
				<li class="active">角色管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					角色管理 <small> <i class="icon-double-angle-right"></i>
						修改角色
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
			    	<div class="col-md-12">
				    	<form id="roleInfoForm" class="form-horizontal" >
							  <div class="form-group">
							    <label for="name" class="col-sm-4 control-label">角色名称</label>
							    <div class="col-sm-3">
							      <input type="text" id="name" name="name" class="form-control" value="${enterpriseRoleDto.name }"  placeholder="输入角色名称" >
							    </div>
							    <span id="error_name" style="color: red"></span>
							  </div>
							  <!-- 
							  <div class="form-group">
							    <label for="depId" class="col-sm-4 control-label">所属企业</label>
							    <div class="col-sm-3">							    
							    	<select id="enterprise_select" name="enterpriseId">
							    		<option value="0">- 所属企业 -</option>
							    		<c:forEach items="${enterpriseDtoList}" var="enterprise">
								    		<c:if test="${enterpriseRoleDto.enterpriseId == enterprise.id}">
								    			<option value="${enterprise.id}" selected="selected">${enterprise.name}</option>
								    		</c:if>
								    		<c:if test="${enterpriseRoleDto.enterpriseId != enterprise.id}">
								    			<option value="${enterprise.id}">${enterprise.name}</option>
								    		</c:if>
							    		</c:forEach>
							    	</select>
							    </div>
							    <span id="error_enterprise" style="color: red"></span>
							  </div> -->
							  <div class="form-group">
							  	<label for="name" class="col-sm-4 control-label">菜单权限</label>
							    <div class="col-sm-3" >
					            	<textarea id="menuname" class="form-control" readonly="readonly" style="resize:none;" rows="5" cols="20"></textarea>  
					            </div>
					            <a class="btn btn-primary " data-toggle="modal" href="#selectsMenuModal">选择菜单</a>
					            <span id="error_menu" style="color: red"></span>
							  </div>
							  <input type="hidden" name="id" value="${enterpriseRoleDto.id }">
							  
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
	<!-- /.modal fade begin -->
	<div class="modal fade" id="selectsMenuModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">×</span></button>
	        <h4 class="modal-title">选择菜单权限</h4>
	      </div>
	      <div class="modal-body">
	        	<!-- 菜单树 -->
				<ul id="treeDemo" class="ztree" ></ul>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-default" data-dismiss="modal" onclick="doSelectMenu();">选取</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
    </div><!-- /.modal fade end -->  
<%@ include  file="./../../common/footer.jsp"%>
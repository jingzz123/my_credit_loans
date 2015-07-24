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
			},
			description:{
				rangelength:[2,100]
			}
		},
		messages:{
			name:{
				required:"请输入角色名称",
				rangelength:"角色名称长度为2~20个字符",
				isContainsSpecialChar:"角色名称只能由中文、英文、数字、“_”组成"
			},
			description:{
				rangelength:"角色描述长度为2~100个字符"
			}
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
				    url: "${pageContext.request.contextPath}/platform/role/doEdit",
				    contentType: "application/json",
				    data: JSON.stringify(roleInfoJson),
				    success: function (roleId) {
				    	alert("修改角色成功!");
				    	window.location.href = "${pageContext.request.contextPath}/platform/role/showList";
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
        url: "${pageContext.request.contextPath}/platform/menu/getAllMenuTree",
        success: function (data) {
        	var zNodes = eval(data);
        	var menuIdList = eval("${platformRoleDto.menuIdList}");
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
	window.location.href = "${pageContext.request.contextPath}/platform/role/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">平台管理</a></li>
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
							      <input type="text" id="name" name="name" class="form-control" value="${platformRoleDto.name }"  placeholder="输入角色名称" >
							    </div>
							    <span id="error_name" style="color: red"></span>
							  </div>
							  <div class="form-group">
							    <label for="name" class="col-sm-4 control-label">角色描述</label>
							    <div class="col-sm-3">
							      <input type="text" name="description" id="description" class="form-control" value="${platformRoleDto.description }"   placeholder="输入角色描述">
							    </div>
							    <span id="error_description" style="color: red"></span>
							  </div>
							  <div class="form-group">
							  	<label for="name" class="col-sm-4 control-label">菜单权限</label>
							    <div class="col-sm-3" >
					            	<textarea id="menuname" class="form-control" readonly="readonly" style="resize:none;" rows="5" cols="20"></textarea>  
					            </div>
					            <a class="btn btn-primary " data-toggle="modal" href="#selectsMenuModal">选择菜单</a>
					            <span id="error_menu" style="color: red"></span>
							  </div>
							  <input type="hidden" name="id" value="${platformRoleDto.id }">
							  
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
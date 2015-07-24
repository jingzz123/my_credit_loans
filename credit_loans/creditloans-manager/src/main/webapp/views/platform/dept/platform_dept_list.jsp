<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<li> <a href="#"> <i class="icon-star"></i> <span class="menu-text"> 角色管理 </span> </a> </li>
				<li class="active"> <a href="${pageContext.request.contextPath}/platform/dept/platformDepartList"> <i class="icon-th"></i> <span class="menu-text"> 部门信息管理 </span> </a> </li>
			</ul>
			<!-- /.nav-list -->
			<div class="sidebar-collapse" id="sidebar-collapse"> <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i> </div>
		</div>
		<!-- 二级菜单 end -->
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">部门管理</a></li>
				<li class="active">部门信息</li>
			</ul>
		</div>
		<div class="page-content">
			<div class="page-header">
				<h1>
					部门信息<small> <i class="icon-double-angle-right"></i>
						设置部门信息
					</small>
				</h1>
			</div><!-- /.page-header -->
			<div class="row">
			    <!-- 组织结构树  -->
				<div class="col-xs-3">
					<ul id="departmentTree" class="ztree"></ul>
				</div>
				<div class="col-xs-9">
					<div class="table-responsive table-responsive-a">
						<div class="table-top-button">
							<button class="btn btn-success" onclick="addPlatformDeptInfo()">
								<i class="icon-plus-sign"></i> 添加
							</button>
						</div>
						<div id="sample-table-2_wrapper" class="dataTables_wrapper">
							<div class="row ">
								<form  class="form-inline" id="queryForm" action="${pageContext.request.contextPath}/platform/dept/platformDepartList" method="post">
									<input type="hidden" id ="currentPage"  name="currentPage" value="1"/>
									<div class="form-group">
										<input type="text" id="departName" name="departName" value="${departName}"  placeholder="..部门名称">
										<input type="hidden" id="deptId" name="deptId"/>
									</div>
									<div class="form-group">
										<button style="height: 30px; width: 50px;color:#FFF;border:none; background-color:#42aaec;" id="subFrom" onclick="query(1);">
											<i class="icon-search"></i>
										</button>
									</div>
								</form>
							</div>
							<table id="enterprise_user_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>部门名称</th>
										<th>上级部门名称</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${department.datas }" var="dept" varStatus="status">
									<tr>	
							          <td>${((department.currentPage*department.pageSize)-department.pageSize)+status.index + 1 }</td>
							          <td>${dept.name }</td>
							          <td>${dept.parentName}</td>
							          <td><fmt:formatDate value="${dept.createTime}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>	
							          <td>
							          	<div class="action-buttons">
											<a class="green" href="#" onclick="updateEnterpriseDept('${dept.id}')"> <i class="icon-pencil bigger-130" ><font style="color: black;font-size: 12px;">&nbsp;编辑</font></i></a>
											<a class="red" href="#" onclick="deleteEnterpriseDept('${dept.id}')"> <i class="icon-trash bigger-130"><font style="color: black;font-size: 12px;">&nbsp;删除</font></i></a>
										</div>
									  </td>
							        </tr>
						        </c:forEach>
								</tbody>
								<tfoot>
								
								</tfoot>
							</table>
							<!-- 分页  -->
						    <div style="text-align: right;margin-top: -20px;">
						    	<ul id="page" class="pagination"></ul>
						    </div>
					   </div> 
					</div>
				</div>
				 
			</div> <!-- class:row -->
			
		</div><!-- /.page-content -->
		
	</div><!-- /.main-content --> 
<%@ include  file="./../../common/footer.jsp"%>
<script type="text/javascript">
$(function(){
	initDepartmentTree();
	var options = {
		totalPages:${department.totalPage},
		startPage:${department.currentPage},
		visiblePages: 5,
		first: '&#124;&lt;',
		prev: '&lsaquo;',
		next: '&rsaquo;',
		last: '&gt;&#124;',
        onPageClick: function (event, page) {
        	
        }
	}
	$('#page').twbsPagination(options);
});


//添加企业信息
function addPlatformDeptInfo(){
	window.location.href = "${pageContext.request.contextPath}/platform/dept/departmentInput";
}

//删除企业信息
function deleteEnterpriseDept(id){
	if(window.confirm("你确定要删除该条记录")){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/platform/dept/deleteDepartmentName",
			data:{"id":id},
			success:function(isSucc){
				if(isSucc == 0){
					alert("删除成功");
					window.location.reload(true);
				}else if(isSucc == 1){
					alert("删除失败");
				}else{
					alert("已关联用户信息无法删除");
				}
			}
		});	
	}
}

//编辑企业信息
function updateEnterpriseDept(id){
	window.location.href = "${pageContext.request.contextPath}/platform/dept/updateDepartmentInfo?id="+id;
}

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
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
					    	$("#deptId").attr("value",treeNode.id);
					    	$("#subFrom").click();
					    }
					}
				};
	$.ajax({
		url:"${pageContext.request.contextPath}/platform/dept/departmentTree",
		type:"POST",
		success:function(data){
			var treeNodes = eval(data);
			$.fn.zTree.init($("#departmentTree"), setting, treeNodes);
		}
	});
}

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}
</script>
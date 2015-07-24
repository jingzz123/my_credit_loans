<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../common/header.jsp"%> 

<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}

function add(){
	window.location.href = "${pageContext.request.contextPath}/platform/role/add";
}

function edit(roleId){
	window.location.href = "${pageContext.request.contextPath}/platform/role/edit?roleId="+roleId;
}

function del(roleId){
	if (!confirm("是否删除该角色?")) {
		return;
	}
	// 请求
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/platform/role/doDelete",
        data: "roleId=" + roleId,
        success: function () {
        	alert("删除成功!");
        	window.location.href = "${pageContext.request.contextPath}/platform/role/showList"
        },
        error: function (data) {
        	alert("删除角色错误，该角色下存在用户!");
        }
    });
}

//jquery.twbsPagination.js 分页功能
$(function(){
	var options = {
		totalPages:${platformRolePage.totalPage},
		startPage:${platformRolePage.currentPage},
		visiblePages: 5,
		first: '&#124;&lt;',
		prev: '&lsaquo;',
		next: '&rsaquo;',
		last: '&gt;&#124;',
        onPageClick: function (event, page) {
        	query(page);
        }
        
	}
	$('#page').twbsPagination(options);
	
})


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
					角色管理<small> <i class="icon-double-angle-right"></i>
						设置所有角色信息
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive table-responsive-a">
						<div class="table-top-button">
							<button class="btn btn-success" onclick="add();">
								<i class="icon-plus-sign"></i> 添加
							</button>
							<!-- <button class="btn btn-danger">
								<i class="icon-trash"></i> 删除
							</button> -->
						</div>
						<div id="sample-table-2_wrapper" class="dataTables_wrapper">
							<div class="row">
								<form id="queryForm" action="${pageContext.request.contextPath}/platform/role/showList" method="post">
									<input type="hidden" id ="currentPage"  name="currentPage" value="1"/>
									<div>
										<span >	
											<input type="text" name="name" value="${queryDto.name}" placeholder="输入角色名称" >
										</span>
										<span>
										<button style="height: 30px; width: 50px;color:#FFF;border:none; background-color:#42aaec;" onclick="query(1);">
											<i class="icon-search"></i>
										</button>
										</span>
									</div>
								</form>
							</div>
							<table id="platform_role_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>名称</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${platformRolePage.datas }" var="role" varStatus="status">
									<tr>
							          <td>${((platformRolePage.currentPage*platformRolePage.pageSize)-platformRolePage.pageSize)+status.index + 1 }</td>
							          <td>${role.name }</td>
							          <td>
							          	<div class="action-buttons">
											<a class="green" href="#" onclick="edit(${role.id})"> <i class="icon-pencil bigger-130"></i></a>
											<a class="red" href="#" onclick="del(${role.id})"> <i class="icon-trash bigger-130"></i></a>
										</div>
									  </td>
							        </tr>
						        </c:forEach>
								</tbody>
							</table>
							<!-- 分页      -->
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
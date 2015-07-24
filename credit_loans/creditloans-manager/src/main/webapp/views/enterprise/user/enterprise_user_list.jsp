<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../common/header.jsp"%>

<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}

function add(){
	window.location.href = "${pageContext.request.contextPath}/enterprise/user/add";
}

function edit(userId){
	window.location.href = "${pageContext.request.contextPath}/enterprise/user/edit?userId="+userId;
}

function del(userId){
	if (!confirm("是否删除该用户?")) {
		return;
	}
	
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/enterprise/user/doDelete",
        data: "userId="+userId,
        success: function () {
        	alert("删除成功!");
        	window.location.href="${pageContext.request.contextPath}/enterprise/user/showList";
        },
        error: function (data) {
        	alert("删除用户错误!");
        	/* var err_msg = data.responseText;
        	if(err_msg.indexOf("账户异常，请重新登录")) {
	        	alert("账户异常，请重新登录");
	        	window.location.href="${pageContext.request.contextPath}/login";
        	} else {
	        	alert("删除用户错误!");
        	} */
        }
    });
}

//jquery.twbsPagination.js 分页功能
$(function(){
	var options = {
		totalPages:${enterpriseUserPage.totalPage},
		startPage:${enterpriseUserPage.currentPage},
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
					用户管理<small> <i class="icon-double-angle-right"></i>
						设置所有企业用户的个人信息
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
								<form id="queryForm" action="${pageContext.request.contextPath}/enterprise/user/showList" method="post">
									<input type="hidden" id ="currentPage"  name="currentPage" value="1"/>
									<div>
										<span >	
											<input type="text" name="name" value="${queryDto.name}" placeholder="输入用户名称" >
										</span>
										<span>	
											<input type="text" name="email" value="${queryDto.email}"  placeholder="输入用户账号">
										</span>
										<span>
										<button style="height: 30px; width: 50px;color:#FFF;border:none; background-color:#42aaec;" onclick="query(1);">
											<i class="icon-search"></i>
										</button>
										</span>
									</div>
								</form>
							</div>
							<table id="enterprise_user_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>名称</th>
										<th>登录账号</th>
										<th>企业</th>
										<th>手机</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${enterpriseUserPage.datas }" var="user" varStatus="status">
									<tr>
							          <td>${((enterpriseUserPage.currentPage*enterpriseUserPage.pageSize)-enterpriseUserPage.pageSize)+status.index + 1 }</td>
							          <td>${user.name }</td>
							          <td>${user.email }</td>
							          <td>${user.enterpriseName }</td>
							          <td>${user.mobile }</td>
							          <td>
							          <c:if test="${user.isinvalid==0}">
							          	<span class="label label-sm label-success"><i class="icon-ok"></i></span>
							          </c:if>
							          <c:if test="${user.isinvalid==1}">
							          	<span class="label label-sm label-success"><i class="icon-remove"></i></span>
							          </c:if>
							          </td>
							          <td>
							          	<div class="action-buttons">
											<a class="green" href="#" onclick="edit(${user.id})"> <i class="icon-pencil bigger-130"></i></a>
											<a class="red" href="#" onclick="del(${user.id})"> <i class="icon-trash bigger-130"></i></a>
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
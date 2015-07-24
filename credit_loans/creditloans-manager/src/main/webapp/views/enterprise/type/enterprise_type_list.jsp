<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include  file="./../../common/header.jsp"%>  
<script>
$(function(){
	var options = {
		totalPages:${enterpriseTypePage.totalPage},
		startPage:${enterpriseTypePage.currentPage},
		visiblePages: 5,
		first: '&#124;&lt;',
		prev: '&lsaquo;',
		next: '&rsaquo;',
		last: '&gt;&#124;',
        onPageClick: function (event, page) {
        	
        }
	}
	$('#page').twbsPagination(options);
})

//添加企业类型信息
function addEnterpriseType(){
	window.location.href = "${pageContext.request.contextPath}/enterprise/type/enterpriseTypeInput";
}

//删除企业类型信息
function deleteEnterpriseType(id){
	if(window.confirm("你确定要删除该条记录")){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/enterprise/type/deleteEnterpriseType",
			data:{"id":id},
			success:function(tipType){
				if(tipType == 0){
					alert("删除成功");
					window.location.reload(true);
				}else if(tipType == 1){
					alert("删除失败");
				}else{
					alert("已关联企业信息无法删除");
				}
			}
		});	
	}
}

//编辑企业类型信息
function updateEnterpriseType(id){
	window.location.href = "${pageContext.request.contextPath}/enterprise/type/updateEnterpriseType?id="+id;
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
				<li><a href="#">企业管理</a></li>
				<li class="active">企业类型</li>
			</ul>
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					企业类型<small> <i class="icon-double-angle-right"></i>
						设置企业类型信息
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive table-responsive-a">
						<div class="table-top-button">
							<button class="btn btn-success" onclick="addEnterpriseType()">
								<i class="icon-plus-sign"></i> 添加
							</button>
						</div>
						<table id="enterprise_user_table"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>公司类型</th>
									<th>创建时间</th>
									<th>更新时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${enterpriseTypePage.datas }" var="enterpriseType" varStatus="status">
								<tr>	
						          <td>${((enterpriseTypePage.currentPage*enterpriseTypePage.pageSize)-enterpriseTypePage.pageSize)+status.index + 1 }</td>
						          <td>${enterpriseType.name }</td>
						          <td><fmt:formatDate value="${enterpriseType.createTime}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
						          <td><fmt:formatDate value="${enterpriseType.updateTime}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
						          <td>
						          	<div class="action-buttons">
										<a class="green" href="#" onclick="updateEnterpriseType('${enterpriseType.id}')"> <i class="icon-pencil bigger-130" ><font style="color: black;font-size: 12px;">&nbsp;编辑</font></i></a>
										<a class="red" href="#" onclick="deleteEnterpriseType('${enterpriseType.id}')"> <i class="icon-trash bigger-130"><font style="color: black;font-size: 12px;">&nbsp;删除</font></i></a>
									</div>
								  </td>
						        </tr>
					        </c:forEach>
							</tbody>
						</table>
						<!-- 分页  -->
					    <div style="text-align: right;margin-top: -20px;">
					    	<ul id="page" class="pagination"></ul>
					    </div>
					</div>
				</div>
				 
			</div> <!-- class:row -->
			
		</div><!-- /.page-content -->
		
	</div><!-- /.main-content --> 
<%@ include  file="./../../common/footer.jsp"%>
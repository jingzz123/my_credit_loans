<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">P2P贷款业务</a></li>
			<li class="active">贷款业务信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				贷款业务信息<small> <i class="icon-double-angle-right"></i>
					设置投资人信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="table-responsive table-responsive-a">
					<div class="table-top-button">
						<button class="btn btn-success" onclick="add(${queryDto.dkjbxxId});">
							<i class="icon-plus-sign"></i> 添加
						</button>
						<button class="btn btn-danger" onclick="goBack()">
							<i class="icon-reply"></i> 返回
						</button> 
					</div>
					<div id="sample-table-2_wrapper" class="dataTables_wrapper">
						<div class="row">
							<form id="queryForm"
								action="${pageContext.request.contextPath}/p2p/tzrxx/showList"
								method="post">
								<input type="hidden" id="currentPage" name="currentPage"
									value="1" />
								<input type="hidden" id="dkjbxxId" name="dkjbxxId"
									value="${queryDto.dkjbxxId}" />
								<div>
									<span> <input type="text" name="tzrxm"
										value="${queryDto.tzrxm}" placeholder="输入投资人姓名">
									</span> <span> <input type="text" name="tzrzjhm"
										value="${queryDto.tzrzjhm}" placeholder="输入投资人证件号码">
									</span> <span>
										<button
											style="height: 30px; width: 50px; color: #FFF; border: none; background-color: #42aaec;"
											onclick="query(1);">
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
									<th>投资人姓名</th>
									<th>投资人证件号码</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${p2pTzrxxPageModel.datas }" var="tzrxx"
									varStatus="status">
									<tr>
										<td>${((p2pTzrxxPageModel.currentPage*p2pTzrxxPageModel.pageSize)-p2pTzrxxPageModel.pageSize)+status.index
											+ 1 }</td>
										<td>${tzrxx.tzrxm }</td>
										<td>${tzrxx.tzrzjhm }</td>
										<td><c:if test="${tzrxx.status==1}">
												<span class="label label-sm label-success"><i
													class="icon-ok"></i></span>
											</c:if> <c:if test="${tzrxx.status==2}">
												<span class="label label-sm label-success"><i
													class="icon-remove"></i></span>
											</c:if></td>
										<td>
											<div class="action-buttons">
												<a class="green" href="#" onclick="edit(${tzrxx.id})">
													<i class="icon-pencil bigger-130"></i>
												</a> <a class="red" href="#" onclick="del(${tzrxx.id},${tzrxx.dkjbxxId})"> <i
													class="icon-trash bigger-130"></i></a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 分页      -->
						<div style="text-align: right; margin-top: -20px;">
							<ul id="page" class="pagination"></ul>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- class:row -->

	</div>
	<!-- /.page-content -->

</div>
<!-- /.main-content -->
<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}


function add(dkjbxxId){
	window.location.href = "${pageContext.request.contextPath}/p2p/tzrxx/add?dkjbxxId="+dkjbxxId;
}

function edit(p2pTzrxxId){
	window.location.href = "${pageContext.request.contextPath}/p2p/tzrxx/edit?p2pTzrxxId="+p2pTzrxxId;
}

function del(p2pTzrxxId, dkjbxxId){
	if (!confirm("是否删除该信息?")) {
		return;
	}
	
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/p2p/tzrxx/doDelete",
        data: "p2pTzrxxId="+p2pTzrxxId,
        success: function () {
        	alert("删除成功!");
        	window.location.href="${pageContext.request.contextPath}/p2p/tzrxx/showList?dkjbxxId="+dkjbxxId;
        },
        error: function (data) {
        	alert("该无法删除!");
        }
    });
}

//jquery.twbsPagination.js 分页功能
$(function(){
	var options = {
		totalPages:${p2pTzrxxPageModel.totalPage},
		startPage:${p2pTzrxxPageModel.currentPage},
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

//返回贷款基本信息管理
function goBack(){
	window.location.href = "${pageContext.request.contextPath}/p2p/dkjbxx/showList";
}

</script>
<%@ include file="./../../common/footer.jsp"%>
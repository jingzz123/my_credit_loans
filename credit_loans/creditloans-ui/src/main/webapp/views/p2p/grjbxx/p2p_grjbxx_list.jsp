<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">P2P贷款业务</a></li>
			<li class="active">个人基本信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				个人基本信息<small> <i class="icon-double-angle-right"></i>
					设置所有个人基本信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

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
							<form id="queryForm"
								action="${pageContext.request.contextPath}/p2p/grjbxx/showList"
								method="post">
								<input type="hidden" id="currentPage" name="currentPage"
									value="1" />
								<div>
									<span> <input type="text" name="xm"
										value="${queryDto.xm}" placeholder="输入姓名">
									</span> <span> <input type="text" name="zjhm"
										value="${queryDto.zjhm}" placeholder="输入证件号码">
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
									<th>姓名</th>
									<th>证件号码</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${p2pGrjbxxPageModel.datas }" var="grjbxx"
									varStatus="status">
									<tr>
										<td>${((p2pGrjbxxPageModel.currentPage*p2pGrjbxxPageModel.pageSize)-p2pGrjbxxPageModel.pageSize)+status.index
											+ 1 }</td>
										<td>${grjbxx.xm }</td>
										<td>${grjbxx.zjhm }</td>
										<td><c:if test="${grjbxx.status==1}">
												<span class="label label-sm label-success"><i
													class="icon-ok"></i></span>
											</c:if> <c:if test="${grjbxx.status==2}">
												<span class="label label-sm label-success"><i
													class="icon-remove"></i></span>
											</c:if></td>
										<td>
											<div class="action-buttons">
												<a class="green" href="#" onclick="edit(${grjbxx.id})">
													<i class="icon-pencil bigger-130"></i>
												</a> <a class="red" href="#" onclick="del(${grjbxx.id})"> <i
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


function add(){
	window.location.href = "${pageContext.request.contextPath}/p2p/grjbxx/add";
}

function edit(p2pGrjbxxId){
	window.location.href = "${pageContext.request.contextPath}/p2p/grjbxx/edit?p2pGrjbxxId="+p2pGrjbxxId;
}

function del(p2pGrjbxxId){
	if (!confirm("是否删除该信息?")) {
		return;
	}
	
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/p2p/grjbxx/doDelete",
        data: "p2pGrjbxxId="+p2pGrjbxxId,
        success: function () {
        	alert("删除成功!");
        	window.location.href="${pageContext.request.contextPath}/p2p/grjbxx/showList";
        },
        error: function (data) {
        	alert("该无法删除!");
        }
    });
}

//jquery.twbsPagination.js 分页功能
$(function(){
	var options = {
		totalPages:${p2pGrjbxxPageModel.totalPage>0?p2pGrjbxxPageModel.totalPage:1},
		startPage:${p2pGrjbxxPageModel.currentPage},
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

<%@ include file="./../../common/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}


function add(){
	window.location.href = "${pageContext.request.contextPath}/db/dbjbxx/add";
}

function edit(dbjbxxId){
	window.location.href = "${pageContext.request.contextPath}/db/dbjbxx/edit?dbjbxxId="+dbjbxxId;
}

function del(dbjbxxId){
	if (!confirm("是否删除该信息?")) {
		return;
	}
	
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/db/dbjbxx/doDelete",
        data: "dbjbxxId="+dbjbxxId,
        success: function () {
        	alert("删除成功!");
        	window.location.href="${pageContext.request.contextPath}/db/dbjbxx/showList";
        },
        error: function (data) {
        	alert("该无法删除!");
        }
    });
}

//jquery.twbsPagination.js 分页功能
$(function(){
	var options = {
		totalPages:${dbjbxxPageModel.totalPage},
		startPage:${dbjbxxPageModel.currentPage},
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
			<li><a href="#">担保业务</a></li>
			<li class="active">担保业务信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				担保业务信息<small> <i class="icon-double-angle-right"></i>
					设置所有担保业务信息
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
								action="${pageContext.request.contextPath}/db/dbjbxx/showList"
								method="post">
								<input type="hidden" id="currentPage" name="currentPage"
									value="1" />
								<div>
									<span> <input type="text" name="dbhthm"
										value="${queryDto.dbhthm}" placeholder="输入担保合同号">
									</span> <span> <input type="text" name="bdbrmc"
										value="${queryDto.bdbrmc}" placeholder="输入被担保人名称">
									</span> <span> <input type="text" name="bdbrzjhm"
										value="${queryDto.bdbrzjhm}" placeholder="输入被担保人证件号码">
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
									<th>担保业务编号</th>
									<th>担保合同号</th>
									<th>被担保人名称</th>
									<th>被担保人证件号码</th>
									<th>被担保人信息</th>
									<th>债权人及主合同信息</th>
									<th>反担保人信息</th>
									<th>代偿明细信息</th>
									<th>追偿明细信息</th>
									<th>保费缴纳明细信息</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dbjbxxPageModel.datas }" var="dbjbxx"
									varStatus="status">
									<tr>
										<td>${((dbjbxxPageModel.currentPage*dbjbxxPageModel.pageSize)-dbjbxxPageModel.pageSize)+status.index
											+ 1 }</td>
										<td>${dbjbxx.dbhthm }</td>	
										<td>${dbjbxx.bdbrmc }</td>
										<td>${dbjbxx.bdbrzjhm }</td>
										<td><a class="blue" href="${pageContext.request.contextPath}/db/bdbrxx/showList?dbhtxxId=${dbjbxx.id}">被担保人信息</a></td>
										<td><a class="blue" href="${pageContext.request.contextPath}/db/zqrjzhtxx/showList?dbhtxxId=${dbjbxx.id}">债权人及主合同信息</a></td>
										<td><a class="blue" href="${pageContext.request.contextPath}/db/fdbrxx/showList?dbhtxxId=${dbjbxx.id}">反担保人信息</a></td>
										<td><a class="blue" href="${pageContext.request.contextPath}/db/dcmxxx/showList?dbhtxxId=${dbjbxx.id}">代偿明细信息</a></td>
										<td><a class="blue" href="${pageContext.request.contextPath}/db/zcmxxx/showList?dbhtxxId=${dbjbxx.id}">追偿明细信息</a></td>
										<td><a class="blue" href="${pageContext.request.contextPath}/db/bfjnmxxx/showList?dbhtxxId=${dbjbxx.id}">保费缴纳明细信息</a></td>
										<td><c:if test="${dbjbxx.status==0}">
												<span class="label label-sm label-success"><i
													class="icon-ok"></i></span>
											</c:if> <c:if test="${dbjbxx.status==1}">
												<span class="label label-sm label-success"><i
													class="icon-remove"></i></span>
											</c:if></td>
										<td>
											<div class="action-buttons">
												<a class="green" href="#" onclick="edit(${dbjbxx.id})">
													<i class="icon-pencil bigger-130"></i>
												</a> <a class="red" href="#" onclick="del(${dbjbxx.id})"> <i
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
<%@ include file="./../../common/footer.jsp"%>
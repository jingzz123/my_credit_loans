<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}


function add(dbhtxxId){
	window.location.href = "${pageContext.request.contextPath}/db/bfjnmxxx/add?dbhtxxId="+dbhtxxId;
}

function edit(bfjnmxxxId){
	window.location.href = "${pageContext.request.contextPath}/db/bfjnmxxx/edit?bfjnmxxxId="+bfjnmxxxId;
}

function del(bfjnmxxxId){
	if (!confirm("是否删除该信息?")) {
		return;
	}
	
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/db/bfjnmxxx/doDelete",
        data: "bfjnmxxxId="+bfjnmxxxId,
        success: function () {
        	alert("删除成功!");
        	window.location.href="${pageContext.request.contextPath}/db/bfjnmxxx/showList?dbhtxxId=${queryDto.dbhtxxId}";
        },
        error: function (data) {
        	alert("该无法删除!");
        }
    });
}

//jquery.twbsPagination.js 分页功能
$(function(){
	var options = {
		totalPages:${dbBfjnmxxxPageModel.totalPage},
		startPage:${dbBfjnmxxxPageModel.currentPage},
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

function goBack(){
	window.location.href = "${pageContext.request.contextPath}/db/dbjbxx/showList";
}
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
					设置所有保费缴纳明细信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="table-responsive table-responsive-a">
					<div class="table-top-button">
						<button class="btn btn-success" onclick="add(${queryDto.dbhtxxId});">
							<i class="icon-plus-sign"></i> 添加
						</button>
						<button class="btn btn-danger" onclick="goBack()">
							<i class="icon-reply"></i> 返回
						</button> 
					</div>
					<div id="sample-table-2_wrapper" class="dataTables_wrapper">
						<div class="row">
							<form id="queryForm"
								action="${pageContext.request.contextPath}/db/bfjnmxxx/showList"
								method="post">
								<input type="hidden" id="currentPage" name="currentPage"
									value="1" />
									<input type="hidden" id="dbhtxxId" name="dbhtxxId"
									value="${queryDto.dbhtxxId}" />
								<div>
									 <span> <input type="text" name="yjrq"
										value="${queryDto.yjrq}" placeholder="输入应缴日期">
									</span> <span> <input type="text" name="yjje"
										value="${queryDto.yjje}" placeholder="输入应缴金额">
									</span> <span> <input type="text" name="sjrq"
										value="${queryDto.sjrq}" placeholder="输入实缴日期">
									</span> <span> <input type="text" name="qjje"
										value="${queryDto.qjje}" placeholder="输入欠缴金额">
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
									<th>应缴日期</th>
									<th>应缴金额</th>
									<th>实缴金额</th>
									<th>欠缴金额</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dbBfjnmxxxPageModel.datas }" var="bfjnmxxx"
									varStatus="status">
									<tr>
										<td>${((dbBfjnmxxxPageModel.currentPage*dbBfjnmxxxPageModel.pageSize)-dbBfjnmxxxPageModel.pageSize)+status.index
											+ 1 }</td>
										<td>${bfjnmxxx.yjrq }</td>
										<td>${bfjnmxxx.yjje}</td>	
										<td>${bfjnmxxx.sjrq}</td>	
										<td>${bfjnmxxx.qjje}</td>	
										<td><c:if test="${bfjnmxxx.status==0}">
												<span class="label label-sm label-success"><i
													class="icon-ok"></i></span>
											</c:if> <c:if test="${bfjnmxxx.status==1}">
												<span class="label label-sm label-success"><i
													class="icon-remove"></i></span>
											</c:if></td>
										<td>
											<div class="action-buttons">
												<a class="green" href="#" onclick="edit(${bfjnmxxx.id})">
													<i class="icon-pencil bigger-130"></i>
												</a> <a class="red" href="#" onclick="del(${bfjnmxxx.id})"> <i
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
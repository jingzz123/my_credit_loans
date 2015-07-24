<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%>

<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}

function add(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variabletransaction/add";
}

function edit(variableTransactionId){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variabletransaction/edit?variableTransactionId="+variableTransactionId;
}

function del(variableTransactionId){
	if (!confirm("是否删除该业务变量?")) {
		return;
	}
	
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/regulation/basepackage/variabletransaction/doDelete",
        data: "variableTransactionId="+variableTransactionId,
        success: function () {
        	alert("删除成功!");
        	window.location.href="${pageContext.request.contextPath}/regulation/basepackage/variabletransaction/showList";
        },
        error: function (data) {
        	alert("删除业务变量错误!");
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
		totalPages:${baseVariableTransactionPage.totalPage}>0?${baseVariableTransactionPage.totalPage}:1,
		startPage:${baseVariableTransactionPage.currentPage}>0?${baseVariableTransactionPage.currentPage}:1,
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
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/variablesimple/showAllList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">业务变量管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					业务变量管理<small> <i class="icon-double-angle-right"></i>
						设置所有业务变量的信息
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
							<button class="btn btn-danger" onclick="goBack()">
							<i class="icon-reply"></i> 返回
						</div>
						<div id="sample-table-2_wrapper" class="dataTables_wrapper">
							<div class="row">
								<form id="queryForm" action="${pageContext.request.contextPath}/regulation/basepackage/variabletransaction/showList" method="post">
									<input type="hidden" id ="currentPage"  name="currentPage" value="1"/>
									<div>
										<span >	
											<input type="text" name="name" value="${queryDto.name}" placeholder="输入简单变量名称" >
										</span>
										<span>	
											<input type="text" name="description" value="${queryDto.description}"  placeholder="输入简单变量描述">
										</span>
										<span>
										<button style="height: 30px; width: 50px;color:#FFF;border:none; background-color:#42aaec;" onclick="query(1);">
											<i class="icon-search"></i>
										</button>
										</span>
									</div>
								</form>
							</div>
							<table id="platform_user_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>名称</th>
										<th>描述</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${baseVariableTransactionPage.datas }" var="transaction" varStatus="status">
									<tr>
							          <td>${((baseVariableTransactionPage.currentPage*baseVariableTransactionPage.pageSize)-baseVariableTransactionPage.pageSize)+status.index + 1 }</td>
							          <td>${transaction.name }</td>
							          <td>${transaction.description }</td>
							          <td>
							          	<div class="action-buttons">
											<a class="green" href="#" onclick="edit(${transaction.id})"> <i class="icon-pencil bigger-130"></i></a>
											<a class="red" href="#" onclick="del(${transaction.id})"> <i class="icon-trash bigger-130"></i></a>
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
<%@ include  file="./../../../common/footer.jsp"%>
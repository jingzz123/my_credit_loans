<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%>

<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}

function add(rulesetId){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/condition/add?rulesetId=" + rulesetId;
}

function edit(conditionId){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/condition/edit?conditionId="+conditionId;
}

function del(conditionId,rulesetId){
	if (!confirm("是否删除该condition?")) {
		return;
	}
	
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/regulation/basepackage/condition/doDelete",
        data: "conditionId="+conditionId,
        success: function () {
        	alert("删除成功!");
        	window.location.href="${pageContext.request.contextPath}/regulation/basepackage/condition/showList?rulesetId="+rulesetId;
        },
        error: function (data) {
        	alert(data.responseText);
        	alert("删除condition错误!");
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
		totalPages:${rulesetConditionPage.totalPage}>0?${rulesetConditionPage.totalPage}:1,
		startPage:${rulesetConditionPage.currentPage}>0?${rulesetConditionPage.currentPage}:1,
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
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/showList";
}
</script>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">条件管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					条件管理<small> <i class="icon-double-angle-right"></i>
						设置所有条件的信息
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive table-responsive-a">
						<div class="table-top-button">
							<button class="btn btn-success" onclick="add(${queryDto.rulesetId});">
								<i class="icon-plus-sign"></i> 添加
							</button>
							<!-- <button class="btn btn-danger">
								<i class="icon-trash"></i> 删除
							</button> -->
							<button class="btn btn-danger" onclick="goBack()">
							<i class="icon-reply"></i> 返回
							</button>
						</div>
						<div id="sample-table-2_wrapper" class="dataTables_wrapper">
							<div class="row">
								<form id="queryForm" action="${pageContext.request.contextPath}/regulation/basepackage/condition/showList" method="post">
									<input type="hidden" id ="currentPage"  name="currentPage" value="1"/>
									<input type="hidden" id="rulesetId" name="rulesetId"	value="${queryDto.rulesetId}" />
									<div>
										<span >	
											<input type="text" name="name" value="${queryDto.name}" placeholder="输入条件名称" >
										</span>
										<span>	
											<input type="text" name="description" value="${queryDto.description}"  placeholder="输入条件描述">
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
								<c:forEach items="${rulesetConditionPage.datas }" var="condition" varStatus="status">
									<tr>
							          <td>${((rulesetConditionPage.currentPage*rulesetConditionPage.pageSize)-rulesetConditionPage.pageSize)+status.index + 1 }</td>
							          <td>${condition.name }</td>
							          <td>${condition.description }</td>
							          <td>
							          	<div class="action-buttons">
											<a class="green" href="#" onclick="edit(${condition.id})"> <i class="icon-pencil bigger-130"></i></a>
											<a class="red" href="#" onclick="del(${condition.id},${condition.rulesetId })"> <i class="icon-trash bigger-130"></i></a>
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
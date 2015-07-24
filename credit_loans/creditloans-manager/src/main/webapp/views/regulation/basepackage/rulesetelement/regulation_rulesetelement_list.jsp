<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%>

<script type="text/javascript">

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}

function add(){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/add";
}

function edit(rulesetElementId){
	window.location.href = "${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/edit?rulesetElementId="+rulesetElementId;
}

function del(rulesetElementId){
	if (!confirm("是否删除该rulesetElement?")) {
		return;
	}
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/doDelete",
        data: "rulesetElementId="+rulesetElementId,
        success: function (flag) {
        	if(flag==0){
		        alert("删除rulesetElement成功！");
	    	}else{
	    		alert("存在关联不能删除rulesetElement");
	    	}
        	window.location.href="${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/showList";
        },
        error: function (data) {
        	alert("删除rulesetElement错误!");
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
		totalPages:${rulesetElementPage.totalPage>0?rulesetElementPage.totalPage:1},
		startPage:${rulesetElementPage.currentPage>0?rulesetElementPage.currentPage:1},
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
				<li><a href="#">规则管理</a></li>
				<li class="active">规则集管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					规则集管理<small> <i class="icon-double-angle-right"></i>
						设置所有规则集的信息
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
								<form id="queryForm" action="${pageContext.request.contextPath}/regulation/basepackage/rulesetelement/showList" method="post">
									<input type="hidden" id ="currentPage"  name="currentPage" value="1"/>
									<div>
										<span >	
											<input type="text" name="name" value="${queryDto.name}" placeholder="输入rulesetelement名称" >
										</span>
										<span>	
											<input type="text" name="description" value="${queryDto.description}"  placeholder="输入rulesetelement描述">
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
										<th>条件管理</th>
										<th>规定管理</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${rulesetElementPage.datas }" var="element" varStatus="status">
									<tr>
							          <td>${((rulesetElementPage.currentPage*rulesetElementPage.pageSize)-rulesetElementPage.pageSize)+status.index + 1 }</td>
							          <td>${element.name }</td>
							          <td>${element.description }</td>
							           <td><a class="blue" href="${pageContext.request.contextPath}/regulation/basepackage/condition/showList?rulesetId=${element.id }">条件信息</a></td>
							           <td><a class="blue" href="${pageContext.request.contextPath}/regulation/basepackage/rule/showList?rulesetId=${element.id }">规定信息</a></td>
							          <td>
							          <c:if test="${element.isUsed=='Y'}">
							          	<span class="label label-sm label-success"><i class="icon-ok"></i></span>
							          </c:if>
							          <c:if test="${element.isUsed=='N'}">
							          	<span class="label label-sm label-success"><i class="icon-remove"></i></span>
							          </c:if>
							          </td>
							          <td>
							          	<div class="action-buttons">
											<a class="green" href="#" onclick="edit(${element.id})"> <i class="icon-pencil bigger-130"></i></a>
											<a class="red" href="#" onclick="del(${element.id})"> <i class="icon-trash bigger-130"></i></a>
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
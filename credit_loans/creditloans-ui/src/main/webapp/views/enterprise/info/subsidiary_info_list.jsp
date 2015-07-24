<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include  file="./../../common/header.jsp"%>  

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">权限管理</a></li>
				<li class="active">企业信息</li>
			</ul>
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					企业信息<small> <i class="icon-double-angle-right"></i>
						设置企业信息
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive table-responsive-a">
						<div class="table-top-button">
							<button class="btn btn-success" onclick="addEnterpriseInfo()">
								<i class="icon-plus-sign"></i> 添加
							</button>
						</div>
						<div id="sample-table-2_wrapper" class="dataTables_wrapper">
							<div class="row ">
								<form  class="form-inline" id="queryForm" action="${pageContext.request.contextPath}/subsidiary/info/enterpriseInfoList" method="post">
									<input type="hidden" id ="currentPage"  name="currentPage" value="1"/>
									<div class="form-group">
									    <select class="form-control" id="typeId" name="typeId" >
										    <option value="0" selected="selected">- 请选择企业类型 -</option>
										    <c:forEach items="${enterpriseTypes }" var="enterpriseType"  varStatus="status">
											    <c:if test="${enterpriseType.id != queryDto.typeId}">
												    <option value="${enterpriseType.id}">${enterpriseType.name } </option>
												</c:if>	
												<c:if test="${enterpriseType.id == queryDto.typeId}">
												    <option value="${enterpriseType.id}" selected="selected">${enterpriseType.name } </option>
												</c:if>
										    </c:forEach>
										</select>
									</div>
									<div class="form-group">
										<input type="text" name="name" value="${queryDto.name}"  placeholder="..请输入企业名称">
									</div>
									<div class="form-group">
										<button style="height: 30px; width: 50px;color:#FFF;border:none; background-color:#42aaec;" onclick="query(1);">
											<i class="icon-search"></i>
										</button>
									</div>
								</form>
							</div>
							<table id="enterprise_user_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>公司名称</th>
										<th>公司电话</th>
										<th>公司地址</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${enterprisePages.datas }" var="enterpriseInfo" varStatus="status">
										<tr>	
								          <td>${((enterprisePages.currentPage*enterprisePages.pageSize)-enterprisePages.pageSize)+status.index + 1 }</td>
								          <td>${enterpriseInfo.name }</td>
								          <td>${enterpriseInfo.tel }</td>
								          <td>${enterpriseInfo.address }</td>	
								          <td>
								          <c:if test="${enterpriseInfo.isinvalid==0}">
								          	<span class="label label-sm label-success"><i class="icon-ok"></i></span>
								          </c:if>
								          <c:if test="${enterpriseInfo.isinvalid==1}">
								          	<span class="label label-sm label-success"><i class="icon-remove"></i></span>
								          </c:if>
								          </td>					          
								          <td>
								          	<div class="action-buttons">
												<a class="green" href="#" onclick="updateEnterpriseType('${enterpriseInfo.id}')" title="修改"> <i class="icon-pencil bigger-130" ></i></a>
												<a class="red" href="#" onclick="deleteEnterpriseInfo('${enterpriseInfo.id}')" title="禁用"> <i class="icon-remove-circle bigger-130"></i></a>
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
				</div>
				 
			</div> <!-- class:row -->
			
		</div><!-- /.page-content -->
		
	</div><!-- /.main-content --> 
<script type="text/javascript">
$(function(){
	var options = {
		totalPages:${enterprisePages.totalPage!=0?enterprisePages.totalPage:1},
		startPage:${enterprisePages.currentPage},
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
});

//添加企业信息
function addEnterpriseInfo(){
	window.location.href = "${pageContext.request.contextPath}/subsidiary/info/enterpriseInfoInput";
}

//禁用企业信息
function deleteEnterpriseInfo(id){
	if(window.confirm("你确定要禁用该企业?")){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/subsidiary/info/deleteEnterpriseInfo",
			data:{"strId":id},
			success:function(isSucc){
				if(isSucc == 0){
					alert("禁用成功");
					window.location.reload(true);
				}else{
					alert("禁用失败");
				}
			}
		});	
	}
}

//编辑企业信息
function updateEnterpriseType(id){
	window.location.href = "${pageContext.request.contextPath}/subsidiary/info/updateEnterpriseInfo?id="+id;
}

function query(page){
	$("#currentPage").val(page);
	$('#queryForm').submit();
}
</script>	
<%@ include  file="./../../common/footer.jsp"%>
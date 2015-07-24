<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./../../../common/header.jsp"%>


	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">规则管理</a></li>
				<li class="active">变量管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					变量管理<small> <i class="icon-double-angle-right"></i>
						设置所有变量的信息
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive table-responsive-a">
						<div class="table-top-button">
						</div>
						<div id="sample-table-2_wrapper" class="dataTables_wrapper">
							<table id="platform_user_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>简单变量管理</th>
										<th>业务变量管理</th>
										<th>外部变量管理</th>
										<th>表达式变量管理</th>
									</tr>
								</thead>
								<tbody>
									<tr>
							          <td><a class="blue" href="${pageContext.request.contextPath}/regulation/basepackage/variablesimple/showList">简单变量信息</a></td>
							          <td><a class="blue" href="${pageContext.request.contextPath}/regulation/basepackage/variabletransaction/showList">业务变量信息</a></td>
							          <td><a class="blue" href="${pageContext.request.contextPath}/regulation/basepackage/variablesituation/showList">外部变量信息</a></td>
							          <td><a class="blue" href="${pageContext.request.contextPath}/regulation/basepackage/variableexpression/showList">表达式变量信息</a></td>
							        </tr>
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
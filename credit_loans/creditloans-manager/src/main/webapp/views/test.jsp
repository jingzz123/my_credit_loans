<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include  file="./common/header.jsp"%>

	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
				<li><a href="#">权限管理</a></li>
				<li class="active">管理员</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		
		<div class="page-content">
			<div class="page-header">
				<h1>
					管理员 <small> <i class="icon-double-angle-right"></i>
						设置所有管理员的个人信息
					</small>
				</h1>
			</div><!-- /.page-header -->
			
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive table-responsive-a">
						<div class="table-top-button">
							<button class="btn btn-success">
								<i class="icon-plus-sign"></i> 添加
							</button>
							<button class="btn btn-danger">
								<i class="icon-trash"></i> 删除
							</button>
						</div>
						<table id="sample-table-2"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>分组</th>
									<th>真实姓名</th>
									<th>电话</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>zhangguoli</td>
									<td>中级管理员</td>
									<td>MS.li</td>
									<td>13311136523</td>
									<td><span class="label label-sm label-success"><i
											class="icon-ok"></i></span></td>
									<td><div class="action-buttons">
											<a class="green" href="#"> <i
												class="icon-pencil bigger-130"></i>
											</a> <a class="red" href="#"> <i class="icon-trash bigger-130"></i>
											</a>
										</div></td>
								</tr>
								<tr>
									<td>2</td>
									<td>zhangguoli</td>
									<td>中级管理员</td>
									<td>MS.li</td>
									<td>13311136523</td>
									<td><span class="label label-sm label-success"><i
											class="icon-ok"></i></span></td>
									<td><div class="action-buttons">
											<a class="green" href="#"> <i
												class="icon-pencil bigger-130"></i>
											</a> <a class="red" href="#"> <i class="icon-trash bigger-130"></i>
											</a>
										</div></td>
								</tr>
								<tr>
									<td>3</td>
									<td>zhangguoli</td>
									<td>中级管理员</td>
									<td>MS.li</td>
									<td>13311136523</td>
									<td><span class="label label-sm label-success"><i
											class="icon-ok"></i></span></td>
									<td><div class="action-buttons">
											<a class="green" href="#"> <i
												class="icon-pencil bigger-130"></i>
											</a> <a class="red" href="#"> <i class="icon-trash bigger-130"></i>
											</a>
										</div></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div> <!-- class:row -->
			
		</div><!-- /.page-content -->
		
	</div><!-- /.main-content --> 
	
	
<%@ include  file="./common/footer.jsp"%>
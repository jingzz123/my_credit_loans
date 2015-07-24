<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>玖行云电动汽车云平台</title>
<meta name="description" content=" " />
<meta name="keyword" content=" " />
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/abc.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/abc.css" />
<script src="${pageContext.request.contextPath}/static/js/abc-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.bootstrap.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/abc-elements.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/abc.min.js"></script> 

<script type="text/javascript">
	
</script>
</head>

<body>
<div class="navbar navbar-default" id="navbar">
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left"> <a href="" class="navbar-brand"> <small>玖行云电动汽车云平台 </small> </a><!-- /.brand --> 
		</div>
		<!-- /.navbar-header -->
		<div class="navbar-header pull-left" role="navigation">
			<ul class="nav abc-nav abc-nav-next">
				<li class="light-blue active"> <a href="#"> <i class="icon-group"></i><span>权限管理</span></a></li>
				<li class="light-blue"> <a href="#"> <i class="icon-bar-chart"></i><span>基础数据</span></a></li>
				<li class="light-blue"> <a href="#"> <i class="icon-exchange"></i><span>调度管理</span></a></li>
				<li class="light-blue"> <a href="#"> <i class="icon-eye-open"></i><span>实时监控</span></a></li>
				<li class="light-blue"> <a href="#"> <i class=" icon-sitemap"></i><span>数据挖掘</span></a></li>
				<li class="light-blue"> <a href="#"> <i class="icon-cogs"></i><span>系统设置</span></a></li>
			</ul>
			<!-- /.abc-nav-next --> 
		</div>
		<!-- /.navbar-header -->
		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav abc-nav">
				<li class="light-blue"> <a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img class="nav-user-photo" src="${pageContext.request.contextPath}/static/images/img/user.jpg" alt="Jason's Photo" /> <span class="user-info"> <small>欢迎你，</small> 李涛涛 </span> <i class="icon-caret-down"></i> </a>
					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li> <a href="#"> <i class="icon-cog"></i> 账户设置 </a> </li>
						<li> <a href="#"> <i class="icon-user"></i> 个人信息 </a> </li>
						<li class="divider"></li>
						<li> <a href="#"> <i class="icon-off"></i> 退出登陆 </a> </li>
					</ul>
				</li>
			</ul>
			<!-- /.abc-nav --> 
		</div>
		<!-- /.navbar-header --> 
	</div>
	<!-- /.container --> 
</div>
<div class="main-container" id="main-container">
	<div class="main-container-inner">
		<div class="sidebar" id="sidebar">
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<div class="icon-text"> <i class="icon-group"></i> <span> 权限管理 </span></div>
				</div>
			</div>
			<!-- #sidebar-shortcuts -->
			<ul class="nav nav-list">
				<li> <a href="index.html"> <i class="icon-wrench"></i> <span class="menu-text"> 权限设置 </span> </a> </li>
				<li class="active"> <a href="typography.html"> <i class="icon-user"></i> <span class="menu-text"> 管理员 </span> </a> </li>
				<li> <a href="#" class="dropdown-toggle"> <i class="icon-th"></i> <span class="menu-text"> 权限分组 </span> <b class="arrow icon-angle-down"></b> </a>
					<ul class="submenu">
						<li> <a href="index.html"> <i class="icon-double-angle-right"></i> 权限分组1 </a> </li>
						<li> <a href="index.html"> <i class="icon-double-angle-right"></i> 权限分组2 </a> </li>
						<li> <a href="index.html"> <i class="icon-double-angle-right"></i> 权限分组3 </a> </li>
						<li> <a href="index.html"> <i class="icon-double-angle-right"></i> 权限分组4 </a> </li>
						<li> <a href="index.html"> <i class="icon-double-angle-right"></i> 权限分组5 </a> </li>
					</ul>
				</li>
				<li> <a href="#" class="dropdown-toggle"> <i class="icon-screenshot"></i> <span class="menu-text"> 控制器 </span> <b class="arrow icon-angle-down"></b> </a>
					<ul class="submenu">
						<li> <a href="index.html"> <i class="icon-double-angle-right"></i> 控制器1 </a> </li>
						<li> <a href="index.html"> <i class="icon-double-angle-right"></i> 控制器2 </a> </li>
					</ul>
				</li>
			</ul>
			<!-- /.nav-list -->
			<div class="sidebar-collapse" id="sidebar-collapse"> <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i> </div>
		</div>
		
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li> <i class="icon-home home-icon"></i> <a href="#">首页</a> </li>
					<li> <a href="#">权限管理</a> </li>
					<li class="active">管理员</li>
				</ul>
				<!-- .breadcrumb --> 
			</div>
			<div class="page-content">
				<div class="page-header">
					<h1> 管理员 <small> <i class="icon-double-angle-right"></i> 设置所有管理员的个人信息 </small> </h1>
				</div>
				<!-- /.page-header -->
				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive table-responsive-a">
							<div class="table-top-button">
								<button class="btn btn-success"><i class="icon-plus-sign"></i> 添加 </button>
								<button class="btn btn-danger"><i class="icon-trash"></i> 删除 </button>
							</div>
							<table id="sample-table-2" class="table table-striped table-bordered table-hover">
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
										<td><span class="label label-sm label-success"><i class="icon-ok"></i></span></td>
										<td><div class="action-buttons"> <a class="green" href="#"> <i class="icon-pencil bigger-130"></i> </a> <a class="red" href="#"> <i class="icon-trash bigger-130"></i> </a> </div></td>
									</tr>
									<tr>
										<td>1</td>
										<td>zhangguoli</td>
										<td>中级管理员</td>
										<td>MS.li</td>
										<td>13311136523</td>
										<td><span class="label label-sm label-success"><i class="icon-ok"></i></span></td>
										<td><div class="action-buttons"> <a class="green" href="#"> <i class="icon-pencil bigger-130"></i> </a> <a class="red" href="#"> <i class="icon-trash bigger-130"></i> </a> </div></td>
									</tr>
									<tr>
										<td>1</td>
										<td>zhangguoli</td>
										<td>中级管理员</td>
										<td>MS.li</td>
										<td>13311136523</td>
										<td><span class="label label-sm label-success"><i class="icon-ok"></i></span></td>
										<td><div class="action-buttons"> <a class="green" href="#"> <i class="icon-pencil bigger-130"></i> </a> <a class="red" href="#"> <i class="icon-trash bigger-130"></i> </a> </div></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- /.page-content --> 
		</div>
		<!-- /.main-content --> 
		
	</div>
	<!-- /.main-container-inner --> 
<!-- /.main-container --> 

</body>
</html>

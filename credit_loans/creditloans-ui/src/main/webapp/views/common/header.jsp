<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>征信报送云平台</title>
<meta name="description" content=" " />
<meta name="keyword" content=" " />
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/abc.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/abc.css" />
<link href="${pageContext.request.contextPath}/static/zTree/css/zTreeStyle.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/static/js/abc-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.bootstrap.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/abc-elements.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/abc.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.twbsPagination.js"></script>  
<script src="${pageContext.request.contextPath}/static/js/jquery.serializeObject.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/static/js/additional-methods.js"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js"></script>
<script src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.core-3.5.js"></script>
<script src="${pageContext.request.contextPath}/static/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/upload/ajaxfileupload.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/static/js/custom.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	
	var activeId = $.cookie("activeId");
	var activeSecMenuId = $.cookie("activeSecMenuId");
	if(activeId==null||activeId==""){
		activeId = "fmenu_${enterpriseuser.ownerEnterpriseMenuDtoList[0].id}";
	}
	$("#"+activeId).css("backgroundColor","#0077bb");
	
	loadSecMeun(activeId,activeSecMenuId);
});
	
	function loadSecMeun(activeId,activeSecMenuId){
		var fid = activeId.substring(activeId.indexOf("_")+1);
		var str = "";
		<c:forEach items="${enterpriseuser.ownerEnterpriseMenuDtoList}" var="secMenu">
			if("${secMenu.id}"==fid){
				$("#fmenu_icon").addClass("${secMenu.icon}");
				$("#fmenu_name").text("${secMenu.name}");
			}
			if("${secMenu.fid}"==fid){
				str += "<li id='secmenu_${secMenu.id}' onclick='activeSecMenu(this.id)'> <a href='${pageContext.request.contextPath}${secMenu.url}' id='url_${secMenu.id}'> <i class='${secMenu.icon }'></i> <span class='menu-text'> ${secMenu.name } </span> </a> </li>";
			}
		</c:forEach>
		$("#secMenu_show").html(str);
		if(activeSecMenuId==null||activeSecMenuId==""){
			var url = document.location.href;
			$.each($("#secMenu_show li a"), function(i,val){
				if(url==val.href){
					var urlId = val.id;
					$("#"+urlId).parent().addClass("active");
					$.cookie("activeSecMenuId", $("#"+urlId).parent().attr("id"), { expires: 1 , path:"/creditloans-ui/" });
				}
			});
		}else{
			$("#"+activeSecMenuId).addClass("active");
		}
	}
	
	function activeMenu(obj){
		$.cookie("activeId", obj.id, { expires: 1 , path:"/creditloans-ui/" });
		$.cookie("activeSecMenuId", '', { expires: -1 , path:"/creditloans-ui/" });
	}
	
	function activeSecMenu(id){
		$.cookie("activeSecMenuId", id, { expires: 1 , path:"/creditloans-ui/" });
	}
</script>

<body>
<div class="navbar navbar-default" id="navbar">
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left"> <a href="" class="navbar-brand"> <small>征信报送云平台 </small> </a><!-- /.brand --> 
		</div>
		<!-- /.navbar-header -->
		<div class="navbar-header pull-left" role="navigation">
			<ul class="nav abc-nav abc-nav-next">
				<c:forEach items="${enterpriseuser.ownerEnterpriseMenuDtoList}" var="fmenu">
					<c:if test="${fmenu.fid==0}">
						<li class="light-blue" style="width: 130px"> <a href="${pageContext.request.contextPath}${fmenu.url}" id="fmenu_${fmenu.id}" onclick="activeMenu(this)"> <i class="${fmenu.icon }"></i><span>${fmenu.name }</span></a></li>
					</c:if>
				</c:forEach>
			</ul>
			<!-- /.abc-nav-next --> 
		</div>
		<!-- /.navbar-header -->
		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav abc-nav">
				<li class="light-blue"> <a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img class="nav-user-photo" src="${pageContext.request.contextPath}/static/images/img/user.jpg" alt="Jason's Photo" /> <span class="user-info"> <small>欢迎你，</small> ${enterpriseuser.name }</span> <i class="icon-caret-down"></i> </a>
					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li> <a href="#"> <i class="icon-cog"></i> 账户设置 </a> </li>
						<li> <a href="#"> <i class="icon-user"></i> 个人信息 </a> </li>
						<li class="divider"></li>
						<li> <a href="${pageContext.request.contextPath}/logout"> <i class="icon-off"></i> 退出登陆 </a> </li>
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
		<!-- 二级菜单 begin -->
		<div class="sidebar" id="sidebar">
			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<div class="icon-text"> <i id="fmenu_icon"></i> <span id="fmenu_name"></span></div>
				</div>
			</div>
			<!-- #sidebar-shortcuts -->
			<ul class="nav nav-list" id="secMenu_show">
				<!-- 动态加载二级菜单 -->				
			</ul>
			<!-- /.nav-list -->
			<div class="sidebar-collapse" id="sidebar-collapse"> <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i> </div>
		</div>
		<!-- 二级菜单 end -->
		
		
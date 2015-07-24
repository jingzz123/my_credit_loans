<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
<%@ include  file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
	    <ul class="breadcrumb">
		    <li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li class="active">企业管理</li>
			<li class="active">企业账户管理</li>
		</ul>
	</div>
	<div class="page-content">
	   <div class="page-header">
		    <h1>
				企业账户管理
				<small> 
				    <i class="icon-double-angle-right"></i>添加企业账户
				</small>
			</h1>
		</div><!-- /.page-header -->
		<div class="row">
		    <div class="col-xs-12">
			    <div class="col-md-12">
				    <form id="enterpriseInfo" class="form-horizontal" >
				        <div class="form-group">
						    <label for="name" class="col-sm-3 control-label">企业名称：</label>
							<div class="col-sm-4">
						    	<input type="text" id="name" name="name" class="form-control" placeholder="..企业名称"/>
							</div>
						    <span id="error_name" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">企业类型：</label>
							<div class="col-sm-4">
							<select class="form-control" id="typeId" name="typeId" >
								 <option>- 请选择企业类型 -</option>
								 <c:forEach items="${enterpriseTypes }" var="enterpriseType"  varStatus="status">
								 	 <option value="${enterpriseType.id}">${enterpriseType.name } </option>
								 </c:forEach>
							</select>
							</div>
						    <span id="error_typeId" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="email" class="col-sm-3 control-label">企业帐号：</label>
						    <div class="col-sm-4">
						    	<input type="text" id="userEmail" name="userEmail" class="form-control" placeholder="输入企业(email)帐号">
						    </div>
						    <span id="error_userEmail" style="color: red"></span>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">企业密码：</label>
							<div class="col-sm-4">
								<input type="password" id="userPassword" name="userPassword" class="form-control" placeholder="输入企业密码">
							</div>
							<span id="error_userPassword" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">企业电话：</label>
							<div class="col-sm-4">
						    	<input type="text" id="tel" name="tel" class="form-control" placeholder="..企业电话"/>
							</div>
						    <span id="error_tel" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">传真：</label>
							<div class="col-sm-4">
						    	<input type="text" id="fax" name="fax" class="form-control" placeholder="..企业传真"/>
							</div>
						    <span id="error_fax" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">邮政编码：</label>
							<div class="col-sm-4">
						    	<input type="text" id="postcode" name="postcode" class="form-control" placeholder="..邮政编码"/>
							</div>
						    <span id="error_postcode" style="color: red"></span>
						</div>
					    <div class="form-group">
						    <label for="name" class="col-sm-3 control-label">电子邮箱：</label>
							<div class="col-sm-4">
						    	<input type="text" id="email" name="email" class="form-control" placeholder="..XXX@sina.com"/>
							</div>
						    <span id="error_email" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">企业地址：</label>
							<div class="col-sm-4">
						    	<input type="text" id="address" name="address" class="form-control" placeholder="..企业地址"/>
							</div>
						    <span id="error_address" style="color: red"></span>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">网址：</label>
							<div class="col-sm-4">
						    	<input type="text" id="website" name="website" class="form-control" placeholder="..http://www.baidu.com"/>
							</div>
						    <span id="error_website" style="color: red"></span>
						</div>
						<div class="form-group">
							<label for="isinvalid_alive" class="col-sm-3 control-label">企业状态</label>
							<div class="col-sm-4">
							    <input id="isinvalid_alive" name="isinvalid" type="radio" checked="checked" value="0"/> 有效 
							    <input id="isinvalid_forbidden" name="isinvalid" type="radio" value="1"/> 无效
							</div>
						</div>
						<div class="form-group">
						    <label for="name" class="col-sm-3 control-label">备注信息：</label>
							<div class="col-sm-4">
								<textarea id="note"  name="note" class="form-control" placeholder="..备注信息" ></textarea>
							</div>
						    <span id="error_note" style="color: red"></span>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">企业权限：</label>
							<div class="col-sm-4" >
					            <textarea id="menuname" class="form-control" readonly="readonly" style="resize:none;" rows="5" cols="20"></textarea>  
					        </div>
					        <a class="btn btn-primary " data-toggle="modal" href="#selectsMenuModal">选择菜单</a>
					        <span id="error_menu" style="color: red"></span>
						</div>
						
						<input type="hidden" id="oldName" name="oldName"/>
						<div class="form-group">
							<div class="col-sm-offset-4">
								<input type="submit" class="btn btn-primary" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-success" value="返回" onclick="goback()"/>
							</div>
						</div>
					</form>
				</div>
		    </div>
		</div> <!-- class:row -->
	</div>
</div>
	<div class="modal fade" id="selectsMenuModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">×</span></button>
	        <h4 class="modal-title">选择菜单权限</h4>
	      </div>
	      <div class="modal-body">
	        	<!-- 菜单树 -->
				<ul id="treeDemo" class="ztree" ></ul>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-default" data-dismiss="modal" onclick="doSelectMenu();">选取</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
    </div><!-- /.modal fade end -->
<script type="text/javascript">
	//menu树设置
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	$().ready(function() {
		getAllMenuTree();
		$("#enterpriseInfo").validate({
		    onkeyup: false,
			rules: {
				name:{
					required:true,
					rangelength:[2,20],
					isContainsSpecialChar:true,
				    remote:{
						url:"${pageContext.request.contextPath}/enterprise/info/checkEnterpriseName",
						type: "post",
					    dataType: "json",
					    data: {
					    	name: function() {
					            return $("#name").val();
					        },
					        oldName:function(){
					        	return $("#oldName").val();
					        }
					    },
					}
				},
				typeId:{
					min:1
				},
				userEmail:{
					required:true,
					required: true,
				    email: true,
				    remote:{
						url:"${pageContext.request.contextPath}/enterprise/user/checkEmail",
						type: "post",
					    dataType: "json",
					    data: {
					    	email: function() {
					            return $("#userEmail").val();
					        }
					    }
					}
				},
				userPassword:{
					required: true,
					rangelength:[6,16],
					isContainsSpecialChar:true
				},
				tel:{
					isPhone: true
				},
				fax:{
					isPhone: true
				},
				postcode:{
					isZipCode:true
				},
				website:{
					rangelength:[0,120]
				},
				email:{
					email:true
				},
				note:{
					rangelength:[0,120],
				}
			},
			messages:{
				name:{
					required:"请输入企业名称",
					rangelength:"企业名称长度为2~20个字符",
					isContainsSpecialChar:"企业名称只能由中文、英文",
				    remote:"该企业名称已存在！"
				},
				typeId:{
					min:"企业类型不能为空"
				},
				userEmail:{
				    required: "请输入登录账号",
				    email: "请输入正确格式的电子邮件地址",
				    remote:"登录账号已存在请重新输入"
				},
				userPassword:{
					required: "请输入登录密码",
					rangelength:"登录密码长度为6~16个字符",
					isContainsSpecialChar:"登录密码只能由英文、数字、“_”组成"
				},
				tel:{
					isPhone: "请输入正确的座机号码"
				},
				fax:{
					isPhone: "请输入正确的传真号码"
				},
	            postcode:{
	            	isZipCode:"请输入正确的邮政编码"
				},
				website:{
					rangelength:"最大可输入120字符"
				},
				email:{
					email:"请输入正确的电子邮箱"
				},
				note:{
					rangelength:"最多可输入125个汉字"
				}
			},
			errorPlacement: function(error, element) {
				error.appendTo(element.parent().siblings("span"));
			},
			submitHandler:function(){
				var enterpriseInfoJson = $("#enterpriseInfo").serializeObject();
				delete enterpriseInfoJson.oldName;
				// 处理选取的菜单数据
				var menuIdArr = new Array();
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var checkedNodes = zTree.getCheckedNodes(true);
				var menuname = $("#menuname").val();
				if(checkedNodes.length>0 && menuname!=""){
					for(var i=0, l=checkedNodes.length; i<l; i++){
						menuIdArr.push(checkedNodes[i].id);
					}
					enterpriseInfoJson.menuIdList = menuIdArr;
					jQuery.ajax({
						type: "post",
						url:"${pageContext.request.contextPath}/enterprise/info/saveEnterprise",
						contentType: "application/json",
						data: JSON.stringify(enterpriseInfoJson),
						success:function(msg){
							if(msg){
								alert("保存企业信息成功");
								goback();
							}else{
								alert("保存企业信息失败");
							}
						},
						error: function () {
							alert("保存企业信息失败");
						}
					});
				}else{
					$('#error_menu').empty();
					var menuError = "请选择菜单权限";
					$('#error_menu').append(menuError);
				}
			}
		});
	});
	
	//动态加载菜单树
	function getAllMenuTree(){
		//动态加载菜单树
		jQuery.ajax({
	        type: 'POST',
			async: false,
	        url: "${pageContext.request.contextPath}/enterprise/menu/getAllMenuTree",
	        success: function (data) {
	        	var zNodes = eval(data);
	        	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	        }
	    });
	}
	
	function doSelectMenu(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var checkedNodes = zTree.getCheckedNodes(true);
		if(checkedNodes.length>0){
			var menuList = "";
			for(var i=0;i<checkedNodes.length;i++){
				menuList = menuList+checkedNodes[i].name+" | ";
			}
			$("#menuname").val(menuList.substring(0,menuList.length-2));
		}else{
			$("#menuname").val("");
		}
	}
	//返回
	function goback(){
		window.location.href = "${pageContext.request.contextPath}/enterprise/info/enterpriseInfoList"
	}
</script>
<%@ include  file="./../../common/footer.jsp"%>
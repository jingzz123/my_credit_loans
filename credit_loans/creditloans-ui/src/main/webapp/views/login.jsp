<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<title>征信报送云平台登录页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/abc.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/abc.css" rel="stylesheet"  />

<script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
//此处阻止提交表单  
function stop(e) {
	if ( e && e.preventDefault ){ 
		e.preventDefault();
　　   } else { 
	    // ie下执行 
		window.event.returnValue = false;
　　  }
}

// 登录
function login(event) {
	var emailAddress = $("#emailAddress").val();
	var password = $("#password").val();
	if(!validate_from(emailAddress, password)) {
		stop(event);
	}else{
		if($("#rmbPassword").is(":checked") == true){
			var userName = $("#emailAddress").val();
	        var passWord = $("#password").val();
	        $.cookie("rmbPassword", "true", { expires: 7 });
	        $.cookie("userName", emailAddress, { expires: 7 });
	        $.cookie("passWord", password, { expires: 7 });
		}
	    else {
	        $.cookie("rmbPassword", "false", { expires: -1 });
	        $.cookie("userName", '', { expires: -1 });
	        $.cookie("passWord", '', { expires: -1 });
	    }
		jQuery.ajax({
			type: "post",
		    url: "${pageContext.request.contextPath}/doLogin",
		    data: {email:emailAddress,password:password},
		    success: function (loginResultJson) {
		    	var result = eval(loginResultJson);
		    	if(result.loginResult == 0){
		    		$.cookie("activeId", '', { expires: -1 , path:"/creditloans-ui/" });
		    		$.cookie("activeSecMenuId", '', { expires: -1 , path:"/creditloans-ui/" });
		    		window.location.href = "${pageContext.request.contextPath}"+result.url;
		    	}
		    	if(result.loginResult == 1){
		    		$("#err_msg").html("用户名不存在！");
		    	}
		    	if(result.loginResult == 2){
		    		$("#err_msg").html("密码错误！");
		    	}
		    	if(result.loginResult == 3){
		    		$("#err_msg").html("该用户已禁用，无法登录！");
		    	}
		    	if(result.loginResult == 4 || result.loginResult == 5){
		    		$("#err_msg").html("用户所属企业已禁用，无法登录！");
		    	}
		    	if(result.loginResult == -1){
		    		$("#err_msg").html("登录失败");
		    	}
		    },
		    error: function () {
		    	$("#err_msg").html("login enterprise system error");
		    }	
		});
	}
	
}
	
$(document).ready(function(){
	//初始化页面时验证是否记住了密码
	if ($.cookie("rmbPassword") == "true") {
        $("#rmbPassword").attr("checked", true);
        $("#emailAddress").val($.cookie("userName"));
        $("#password").val($.cookie("passWord"));
    }
});

// 校验
function validate_from (emailAddress, password) {
	var err_msg_val = $("#err_msg");
	var reg = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
	if(emailAddress == null || "" == emailAddress) {
		err_msg_val.html("用户名不能为空");
		return false;
	}
	if(password == null || "" == password) {
		err_msg_val.html("密码不能为空");
		return false;
	}
	if(!reg.test(emailAddress)) {
		err_msg_val.html("请输入正确的邮箱地址");
		return false;
	}
	return true;
}
</script>
<body>
<div class="header layout">
<!-- <div class="logo"></div> -->  
  <div class="navsab">
    <ul>
      <li><a href="#">征信云首页</a><span></span></li>
      <li><a href="#">手机版下载</a><span></span></li>
      <li><a href="#">帮助</a></li>
    </ul>
  </div>
</div>
<!--/.header end-->
<div class="content layout">
  <div class="row">
    <div class="sildbar">&nbsp;</div>
    <!--/.sildbar end-->
    <div class="login">
      <div class="login-main">
        <div class="login-title">用户登录</div>
        <form class="form-main" id="user_info_form">
          <fieldset>
          	<div style="height: 20px;"><font id="err_msg" color="#FF3030">${errorMsg }</font></div>
            <label class="block clearfix">
              <input class="form-control" id="emailAddress" name="emailAddress" value="" placeholder="用户名" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"  onafterpaste="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/>
            </label>
            <label class="block clearfix">
              <input type="password" class="form-control" id="password" name="password" value="" placeholder="密码" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"  onafterpaste="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/>
            </label>
            <div class="clearfix">
              <div class="pull-left">
                <input class="form-control" type="" id="" placeholder="验证字符"/><!--李柯侬-->
              </div>
              <label class="inline"><a href=""><img class="imgyanzheng" src="${pageContext.request.contextPath}/static/images/captcha2.png"></a></label><!--李柯侬-->
            </div>
            <div class="clearfix">
              <label class="inline checkbox-dist">
                <input type="checkbox" class="ace" id="rmbPassword" name="chkRememberPassword"/>
                <span class="lbl pull-right">&nbsp;记住密码</span> </label>
            </div>
            <div class="clearfix">
              <input type="button" class="btn-block btn-color" onclick="login(event);" value="登录" />
            </div>
          </fieldset>
        </form>
      </div>
    </div>
    <!--/.login end--> 
  </div>
  <!--/.row end--> 
</div>
<!--/.content end-->
<div class="footer layout">
  <p>Copyright © 商旗网. All Rights Reseved. 沪ICP备12029598号 京公网安备11010102001291号</p>
</div>
<!--/.footer end--> 


</body>
</html>

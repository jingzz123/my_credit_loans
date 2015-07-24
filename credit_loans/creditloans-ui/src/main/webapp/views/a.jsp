<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>登录-北京移动</title>

<link rel="stylesheet" type="text/css" href="/ac/cmsso/images/skin_new.css" />

<script type="text/javascript" src="/ac/script/jquery.min.js"></script>
<script type="text/javascript" src="/ac/script/jquery.timers-1.1.2.js"></script>
<script type="text/javascript" src="/ac/script/jquery.cookie.js"></script>
<script type="text/javascript" src="/ac/script/message.js"></script>
<script type="text/javascript" src="/ac/script/sdc_new.js"></script>
<script type="text/javascript" src="/ac/script/notice.js"></script>
<script type="text/javascript" src="/ac/script/popup_layer.js"></script>   
<!--切换省代码-->
<script type="text/javascript">
function ShowDivCity() {
    document.getElementById("DivCity").style.display= "block";
}

function HiddenDivCity() {
    document.getElementById("DivCity").style.display = "none";
}

function ShowDivCity2() {
    document.getElementById("DivCity2").style.display= "block";
}

function HiddenDivCity2() {
    document.getElementById("DivCity2").style.display = "none";
}

function ShowDivkjbl() {
    document.getElementById("DivCity6").style.display= "block";
}

function HiddenDivkjbl() {
    document.getElementById("DivCity6").style.display = "none";
}
</script>

<script type="text/javascript">
//----- js 全局变量定义
var $valRnum = true;		//是否js 进行附加码校验 ,true 校验 ，false 不校验
var $valRnum = true;				//是否js 进行附加码校验 ,true 校验 ，false 不校验
var $bmcc_check = 'NO';				//是否为北京移动的验证标识
var $isBmccCheck = false;			//是否进行北京移动手机号校验
var $last_check_num = '';			//最后一次校验是否为北京移动用户的手机号
</script>
<!-- 初始化 登录方式 账号类型 start -->
<script type="text/javascript">
	var $loginModeInit = 1 ;	//默认网站密码登录
	var $loginMethodInit = 1;	//默认 账号类型为 手机号
	
	
</script>
<!-- 初始化 登录方式 账号类型 end-->

<script>
//**************************************
$(function(){
	//********初始设置************************
	
	//******* Cookie 记录手机号 和 用户名 初始化 
	var $initName = $.cookie("c_name");  //获取cookie 中记录的用户名
	var $nowLoginName = $.cookie("login_name");
	if($nowLoginName!=null && $nowLoginName!='null' && $nowLoginName!=''){
		$initName = $nowLoginName;
	}
	var $initMobile = $.cookie("c_mobile"); //获取cookie 中记录的手机号
	var $nowMobile = $.cookie("login_mobile");
	if($nowMobile !=null && $nowMobile !='null' && $nowMobile!=''){
		$initMobile = $nowMobile;
	}
	if($initMobile !='' && $initMobile != 'null' && $initMobile !=null ){
		//初始化
		if(checkMobileNo1($initMobile)){
			$("#loginName").val($initMobile);
			//判断当前展示的手机号是否需要附加码校验（是否显示验证码）
			isValidateRnum($initMobile);
		}
	} else if ($initName !='' && $initName != 'null' && $initName !=null ){
		$("#loginName").val($initName);
		$loginMethodInit = 0 ; //获取到cookie  设置用户名登录
	}
	
	//*********初始化 选择框********************* 
	$("#loginID").val($loginMethodInit);
	$("#net"+$loginModeInit).attr("checked","checked");
	switch($loginModeInit){
		case 1:webModeShow($("#loginID").val());break;
		case 2:smsModeShow();break;
		case 3:cusModeShow();break;
	}
	
	//*********初始化附加码*******************
	$("#NumImage").attr("src","/ac/ValidateNum?smartID="+Math.ceil(Math.random()*10000000000));
	
	//******* 事件处理**************************
	//选择 网站密码
	$("#net1").click(function(){
		messageHide();			//隐藏所有错误信息
		clearBorder();			//清除输入框红边框样式
		webModeShow($("#loginID").val());
	});
	
	//选择 随机短信
	$("#net2").click(function(){
		messageHide();			//隐藏所有错误信息
		clearBorder();			//清除输入框红边框样式
		smsModeShow();
	});	
	
	//选择 客服密码登录
	$("#net3").click(function(){
		messageHide();			//隐藏所有错误信息
		clearBorder();			//清除输入框红边框样式
		cusModeShow();
	});
	
	//选择 手机号 或 用户名
	$("#loginID").change(function(){
		var $loginMethod = $(this).val();
		messageHide();			//隐藏所有错误信息
		clearBorder();			//清除输入框红边框样式
		if($loginMethod==0){   //用户名登录
			if($("#loginName").val()=="" ||$("#loginName").val()=="请输入手机号"){
				$("#loginName").val("请输入用户名");	
			}
			$("#rememberName").show();
			$("#rememberPhone").hide();
			$("#logPwd_3").show();
		}else{				  //手机号登录
			if($("#loginName").val()=="" ||$("#loginName").val()=="请输入用户名" || !checkMobileNo($("#loginName").val())){
				$("#loginName").val("请输入手机号");	
			}
			$("#rememberName").hide();
			$("#rememberPhone").show();
			$("#logPwd_3").hide();
		}
	});
	
	//显示 附加码域
	$("#rnum").focus(function(){
		var $x = '';
		if($x!='' && $x=='x'){
			$("#rnum").css("width","49px");
		}
		$('#ValidateNum').show();
		$(this).css("color","#000000");
		checkPassword($("#password").val(),false);
	});
	
	//更换 附加码
	$("#NumImage").click(function(){
		$("#NumImage").attr("src","/ac/ValidateNum?smartID="+Math.ceil(Math.random()*10000000000));
	});
	
	//表单提交
	$("#loginBut").click(function(){
		login();
	});
	//添加 插码 20120924 by weimw
	$("#loginBut").click(function(){
		if (typeof(_tag)!= 'undefined'){_tag.dcsMultiTrack('WT.event','Logon_EVENT');}
	});
	//回车表单提交
	$("#frmLogin").keydown(function(event){
		 if (event.keyCode == 13){		//回车
	        login();
	    }
	});
	
	//发送随机短信
	$('#numLink').bind("click",function(){  
		var $phone = $("#loginName").val(); 
		if (checkMBPhone($phone)){
			$.ajax({
				  url:'/ac/tempPwdSend',
				  type:"POST",
				  data:"mobile="+$phone,
				  //contentType:"application/json; charset=utf-8",
				  dataType:"json",
				  success: function($data){
				     var $k1 = '';
				     var $v1 = '';
				     $.each($data, function(k, v) {
			           $k1 = k;
			           $v1 = v;
			         });
			         var $m = getMessage($k1);
			         if($m==''){$m = $v1;}	//如果js 中定义错误提示信息，则返回js中信息，否则返回服务端定义的信息！
			         if($k1=='PP_1_2014'){	//短信发送成功
			         	showMessage($m,"smsNum");
			         	showMS(0);
			         }else{					//短信发送失败
			         	  //在一分钟限制内
				         if($k1=='PP_1_2012'){
				         	var $sx = 0;
				         	if($v1!=''){
				         		$sx = parseInt($v1);
				         	}
				         	showMessage("对不起，短信暂时不能发送！","loginName");
				         	showMS($sx);
				         }else{
				         	showMessage($m,"loginName");
				         }
			         }
			       
				  },
				  error: function (msg) {
				  	 //alert("ERROR:"+msg.name);
				  	 showMessage(msg.name,"loginName");
		      	  }
			 });
		 } 
	});
	
	//判断手机号是否需要验证码校验
	//用户名失去焦点
	/**
	$("#loginName").blur(function(){
		$valRnum = true;
		var $phone = $(this).val();
		var $loginMode = getLoginMode(); 			//当前登录方式
		var $loginMethod = $("#loginID").val(); 	//账号类型
		if($loginMode == 1 && $loginMethod == 1 ){	//网站密码 并且手机号登录
			//手机号格式校验
			if(checkMobileNo($phone)){
				isValidateRnum($phone);
			}else{
				$(this).val("");
				$(this).focus();
				showMessage("温馨提示：请正确输入11位中国移动手机号码!","loginName");
			}
		}
	});
	**/
	
	//密码框获得焦点、对用户名或手机号进行校验*******************************
	$("#password").focus(function(){
		$valRnum = true;
		var $phone = $("#loginName").val();
		var $loginMode = getLoginMode(); 			//当前登录方式
		var $loginMethod = $("#loginID").val(); 	//账号类型
		if($loginMode == 1 && $loginMethod == 1 ){	//网站密码 ，手机号登录
			//手机号格式校验
			if(checkMobileNo($phone)){
				isValidateRnum($phone);
				if($phone!=$last_check_num){				//当前输入的手机号未进行归属地校验
					//判断手机号是否为北京移动手机号（true 进行异步校验）
					bmccMobile($phone,true);
				}
			}else{
				$("#loginName").val("");
				$("#loginName").focus();
				showMessage("温馨提示：请正确输入11位中国移动手机号码!","loginName");
			}
		}
		if($loginMode == 1 && $loginMethod == 0 ){	//网站密码 ，用户名登录
			//$phone 用户名
			if($phone=='' || $phone=='请输入用户名'){
				$("#loginName").val("");
				$("#loginName").focus();
				showMessage("温馨提示：用户名不能为空！","loginName");
			}
		}
		$(this).css("color","#000000");
	});
	
	
	//验证用户手机号是否为北京移动手机号，并将用户品牌信息存储在会话范围内
	$("#loginName").keyup(function(){
		var $mobile = $(this).val();
		//登录方式
		var $loginMode = getLoginMode();	
		if($loginMode==1){										//网站密码登陆
			var $loginMethod = $("#loginID").val();
			if($loginMethod==1){								//手机号用户名
				if(!isNum($mobile)){							//手机号非数字类型
					showMessage("温馨提示：请正确输入11位中国移动手机号码!","loginName");
				}else if(checkMobileNo1($mobile)){				//手机号格式正确
					isValidateRnum($mobile);					//判断该手机号是否需要验证码校验
				}
			}
		}else{													//随机短信 或 服务密码 登录
			if(!isNum($mobile)){
				showMessage("温馨提示：请正确输入11位中国移动手机号码!","loginName");
			}
		}
		if($mobile.length==11){
			if($mobile!=$last_check_num){				//当前输入的手机号未进行归属地校验
				//判断手机号是否为北京移动手机号（true 进行异步校验）
				bmccMobile($mobile,true);
			}
		}
	});
	$("#loginName").focus(function(){
		var $mobile = $(this).val();
		//登录方式
		var $loginMode = getLoginMode();						//网站登录方式
		if($loginMode==1){										//网站密码登陆
			var $loginMethod = $("#loginID").val();
			if($loginMethod==1){								//手机号用户名 (只对：（ 网站密码登录 && 手机号） 的用户进行附加码校验 )
				if(checkMobileNo1($mobile)){
					//判断当前展示的手机号是否需要附加码校验（是否显示验证码）
					isValidateRnum($mobile);
					if($mobile!=$last_check_num){				//当前输入的手机号未进行归属地校验
						//判断手机号是否为北京移动手机号（true 进行异步校验）
						bmccMobile($mobile,true);
					}
				}
			}
		}else{													//随机短信 或 服务密码 登录
			if($mobile!=$last_check_num){						//当前输入的手机号未进行归属地校验
				//判断手机号是否为北京移动手机号（true 进行异步校验）
				bmccMobile($mobile,true);
			}
		}
		$(this).css("color","#000000");
	});
	$("#smsNum").focus(function(){
		var $phone = $("#loginName").val();
		if(checkMobileNo($phone)){
			if($phone!=$last_check_num){				//当前输入的手机号未进行归属地校验
				//判断手机号是否为北京移动手机号（true 进行异步校验）
				bmccMobile($phone,true);
			}
		}else{
			$("#loginName").val("");
			$("#loginName").focus();
			showMessage("温馨提示：请正确输入11位中国移动手机号码!","loginName");
		}
		$(this).css("color","#000000");
	});
});

//*********************************************************************************************************


//验证是否为北京移动手机号
function goCheckBmcc($mobile){
	if(!$isBmccCheck || $mobile!=$last_check_num ){							//未做校验 或 两次 校验的手机号不同
		return bmccMobile($mobile,false);		//校验(同步方式)
	}else{										//已经做校验
		if($bmcc_check=='OK'){
			checkSuc("loginName");
			return true;
		}else{
			showMessage("如手机号码非北京号码,请切换至号码归属地。","loginName");
			return false;
		}
	}
}
function checkBmcc($mobile){
	if($bmcc_check=='OK'){
		checkSuc("loginName");
		return true;
	}else{
		showMessage("如手机号码非北京号码,请切换至号码归属地。","loginName");
		return false;
	}
}
//验证是否为北京移动手机号***************************************
//$async :是否异步 ， true：异步 ；    false:同步
function bmccMobile($mobile,$async){
	$mobile = $.trim($mobile);				//手机号
	$last_check_num = $mobile;
	if(checkMobileNo($mobile)){				//手机号格式正确
		//Ajax 校验手机号是否为北京移动手机号码
		$.ajax({
			  url:'/ac/BmccMobile',
			  async:$async,				//是否异步操作
			  type:"POST",
			  data:"mobile="+$mobile,
			  //contentType:"application/json; charset=utf-8",
			  dataType:"json",
			  success: function($data){
			     var $k1 = '';
			     var $v1 = '';
			     $.each($data, function(k, v) {
		           $k1 = k;
		           $v1 = v;
		         });
		         var $m = getMessage($k1);
		         if($m==''){$m = $v1;}	//如果js 中定义错误提示信息，则返回js中信息，否则返回服务端定义的信息！
		         if($k1=='BMCC_MOB'&& $v1=='OK'){	//手机号校验成功
		         	$bmcc_check = 'OK';
		         }else{					//短信发送失败
		         	$bmcc_check = 'NO';
			        showMessage($m,"loginName");
			        $("#loginName").focus();
		         }
		         $isBmccCheck = true;
			  },
			  error: function (msg) {
			  	 //alert("ERROR:"+msg.name);
			  	 showMessage(msg.name,"loginName");
	      	  }
		 });
		 if(!$async){			//如果是同步操作，则需要设置返回值！
		 	if($bmcc_check=='NO'){
		 		return false;
		 	}else{
		 		return true;
		 	}
		 }
	}
		
}
//******************************************************************
//判断该手机号是否需要验证码校验
function isValidateRnum($phone){
	$.ajax({
		  url:'/ac/IsShowValidateRnum',
		  type:"POST",
		  data:{phone:$phone},
		  //contentType:"application/json; charset=utf-8",
		  //dataType:"json",
		  success: function(State){
			   State  = State.replace(/(^\s*)|(\s*$)/g, '');
			   if(State=="show"){
					$("#logPwd_3").show();
			   }else if(State=="NotShow"){
					$("#logPwd_3").hide();
					$valRnum = false;	//提交表单不进行验证码校验
			   }
		  },
		  error: function (msg) {
		  	//showMessage(msg);
		  	$("#logPwd_3").show();
      	  }
	});	
}

//记住 手机号 或 用户名
function setRememberCookie(){
	if($("#loginCheckBox").attr("checked")){			//记住 手机号 或 用户名 设置cookie
		var $loginMode = getLoginMode(); 				//当前登录方式
		var $loginMethod = $("#loginID").val(); 		//账号类型
		var $loginName = $("#loginName").val(); 		//账号
		if($loginMode==1){								//网站密码登录
			if($loginMethod==1){ 						//账号类型 手机号 
				$.cookie("c_mobile",$loginName,{ expires: 30 ,path:'/'});
				$.cookie('c_name', '', { expires: -1,path:'/' }); 		// delete cookie
			}else{ 										//账号类型 用户名
				$.cookie("c_name",$loginName,{ expires: 30 ,path:'/'}); // delete cookie
				$.cookie('c_mobile', '', { expires: -1 ,path:'/'}); 
			}
		}else{											//随机短信 或 客服密码登录
			$.cookie("c_mobile",$loginName,{ expires: 30 ,path:'/'});
			$.cookie('c_name', '', { expires: -1,path:'/' }); 			// delete cookie
		}
	}else{												//删除 cookie
		$.cookie('c_mobile', '', { expires: -1,path:'/' }); 			// delete cookie
		$.cookie('c_name', '', { expires: -1,path:'/' }); 				// delete cookie
	}
}

//存储当前操作用户信息
function setLoginCookie(){
	var $loginMode = getLoginMode(); 									//当前登录方式
	var $loginMethod = $("#loginID").val(); 							//账号类型
	var $loginName = $("#loginName").val(); 							//账号
	if($loginMode==1){													//网站密码登录
		if($loginMethod==1){ 											//账号类型 手机号 
			$.cookie("login_mobile",$loginName,{path:'/'});
			$.cookie('login_name', '', { expires: -1,path:'/' }); 		// delete cookie
		}else{ 															//账号类型 用户名
			$.cookie("login_name",$loginName,{path:'/'}); 
			$.cookie('login_mobile', '', { expires: -1 ,path:'/'});  	// delete cookie
		}
	}else{																//随机短信 或 客服密码登录
		$.cookie("login_mobile",$loginName,{path:'/'});
		$.cookie('login_name', '', { expires: -1,path:'/' }); // delete cookie
	}
}

//发送短信倒计时
function showMS($xt){
	$("#numLink").hide();
	$('#info1').html("一分钟可获取一次短信密码"); 
	$('#info1').show();
	var $t = 60;
	var $tx = 61;
	if($xt!=0){
		$t = $t - $xt;
		$tx = $t + 1;
	}
	$('#numLink').everyTime('1s', function() {
		if($t==0){
			$("#numLink").show();
			$('#info1').html("一分钟可获取一次短信密码");
		}else{
			$('#info1').html($t+"秒后可再次获取");
		}
		$t--;
	},$tx);
}

//隐藏所有错误信息
function messageHide(){
	$('#errorinfologinName').hide();
	$('#errorinfosmsNum').hide();
	$('#errorinfopassword').hide();
	$('#errorinfornum').hide();
}

//校验成功
//清除指定控件的错误提示信息
function checkSuc($obj){
	$('#errorinfo'+$obj).hide();
	$('#'+$obj).css("border-color","#ccc");
}

//清除所有输入框样式
function clearBorder(){
	$('#loginName').css("border-color","#ccc");
	$('#smsNum').css("border-color","#ccc");
	$('#password').css("border-color","#ccc");
	$('#rnum').css("border-color","#ccc");
}
//登录框信息展示*******************************************
//$errorCode :loginName 用户名或手机号
//           :smsNum   随机短信
//           :password 密码
//           :rnum     附加码
//
function showMessage($message,$errorCode){
	messageHide();
	clearBorder();
	$("#"+$errorCode).css("border-color","red");
	$("#errorinfo"+$errorCode).show();
	$("#errorinfo"+$errorCode).html('<img src="/ac/cmsso/images/youhui-76.jpg" />'+$message);
}
//网站密码登录 展示
function webModeShow($loginMethod){
	$("#loginID").val($loginMethod);;
	$("#sjhm").show();  
	$("#yhm").hide();
	$("#logPwd_1").show();  
	$("#logPwd_2").hide();
	$("#webpassLabel").show(); //显示 网站密码 label
	$("#cuspass").hide(); //隐藏 服务密码 label 
	//$("#logPwd_3").show(); 
	$('#info1').hide();
	if($("#loginName").val()==""||$("#loginName").val()=="请输入手机号" || $("#loginName").val()=="请输入用户名"){
		$("#loginName").val($loginMethod==1?"请输入手机号":"请输入用户名");	
	}
	$("#password").val("");  //密码重置
	$("#forgetPassword").show();
	if($loginMethod==1){
		if(!checkMobileNo1($("#loginName").val())){
			$("#loginName").val("请输入手机号");	
		}
		$("#rememberPhone").show();	//记住手机号码
		$("#rememberName").hide();
	}else{
		$("#rememberPhone").hide();
		$("#rememberName").show();	//记住用户名
		$("#logPwd_3").show();
	}
}

//随机短信登录 展示
function smsModeShow(){
	$("#sjhm").hide();
	$("#yhm").show();
	$("#logPwd_1").hide();
	$("#logPwd_2").show();
	$("#logPwd_3").hide();
	$("#info1").show();
	$("#info1").html("一分钟可获取一次短信密码");
	sendSMSNoticeHide('info1','<span style="color:red">温馨提示:系统在9月17日0时至5时进行维护，暂时不能使用随机短信登录!</span>','numLink');
	if($("#loginName").val()==""||$("#loginName").val()=='请输入用户名' || !checkMobileNo1($("#loginName").val())){
		$("#loginName").val("请输入手机号");	
	}
	$("#rememberName").hide();
	$("#rememberPhone").show();
}
//服务密码登录
function cusModeShow(){
	$("#sjhm").hide();
	$("#logPwd_1").show();//显示 输入密码域
	$("#logPwd_2").hide();//隐藏 随机码域
	$("#yhm").show();     //显示 手机号码 label
	$("#webpassLabel").hide(); //隐藏 网站密码 label
	$("#cuspass").show(); //显示 服务密码 label
	//$("#logPwd_3").show();//显示 验证码域
	$('#info1').hide();
	if($("#loginName").val()=="" ||$("#loginName").val()=="请输入用户名" || !checkMobileNo1($("#loginName").val())){
		$("#loginName").val("请输入手机号");	
	}
	$("#password").val("");  //密码重置
	$("#forgetPassword").hide();	//忘记密码
	$("#rememberName").hide();
	$("#rememberPhone").show();
}

//获取登录方式
//loginMode:1 网站密码登录
//loginMode:2 随机短信登录
//loginMode:3 服务密码登录
function getLoginMode(){
	 return $('input:radio[name=loginMode]:checked').val();
}

//校验手机号格式 
//校验成功，清除手机号相关提示信息
function checkMobileNo(mobileNo){
  if((/^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[2-8]|147|178)[0-9]{8}$/.test(mobileNo))){
  	 checkSuc("loginName");
     return  true;
  }else{
     return  false;
  }
}
//校验手机号格式 
function checkMobileNo1(mobileNo){
  if((/^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[2-8]|147|178)[0-9]{8}$/.test(mobileNo))){
     return  true;
  }else{
     return  false;
  }
}
//校验数值类型,该数值类型必须小于等于11位
function isNum($num){
	 var num11 = /^\d{0,11}$/; //数字;
	 if(num11.exec($num)){
	 	checkSuc("loginName");					//清除样式信息
	 	return true;
	 }else{
	 	showMessage("温馨提示：请输入11位中国移动手机号码!","loginName");
	  	return false;
	 }
}
//校验手机号格式是否正确 并显示提示信息
function checkMBPhone(phone){
    var GSMPhNo = /^(13[4-9])|(147)|(159)|(158)|(150)|(151)|(152)|(157)|(178)|(182)|(183)|(184)|(187)|(188)/; //以134(5、6、7、8、9)或159,158,151,150开头;
    var num11 = /^\d{11}$/; //11位数字;
    if( "" != phone ){
      if(num11.exec(phone)){
        if(GSMPhNo.exec(phone)){
          checkSuc("loginName");
          return true;
        }else{
          //alert("对不起，请您正确输入中国移动GSM手机号码(以134-139、159、158、157、152、151、150 、182 或188开头)!");
          showMessage("温馨提示：请正确输入11位中国移动手机号码!","loginName");
          return false;
        }
      }else{
        //alert("请正确输入11位手机号码(数字)!");
        showMessage("温馨提示：请正确输入11位中国移动手机号码!","loginName");
        return false;
      }
    }else{
      showMessage("温馨提示：请输入11位中国移动手机号码!","loginName");
      return false;
    }
}

//校验用户名
function checkUsername($username){
	if($username == ''||$username=='请输入用户名'){
		showMessage("温馨提示：请输入用户名!","loginName");
		return false;
	}else{
		checkSuc("loginName");
		return true;
	}
}
//校验 密码 和 服务密码
function checkPassword($password ,$isCus){
	if($password==''&& !$isCus){
		showMessage("温馨提示：请输入密码!","password");
		return false;
	}
	if($isCus){					//服务密码
	 	var num6 = /^\d{6}$/; 	//6位数字;
		if(!num6.exec($password)){
			showMessage("温馨提示：请输入6位服务密码（数字）","password");
			return false;
		}
	}
	checkSuc("password");
	return true;
}
//校验 随机短信
function checkSmsNum($smsNum){
	var num6 = /^\d{6}$/; 	//6位数字;
	if($smsNum=='' || $smsNum=='随机码'){
		showMessage("温馨提示：请输入随机短信密码!","smsNum");
		return false;
	}else{
		if(!num6.exec($smsNum)){
			showMessage("温馨提示：请输入正确的随机短信密码!","smsNum");
			return false;
		}
	}
	checkSuc("smsNum");
	return true;
}

//校验 附加码（验证码）
function checkRnum($rnum){
	if($valRnum == false){		//如果初始化为不校验附加码
		checkSuc("rnum");
		return true;	//不校验
	}
	var $RNUMEX = /^[A-Za-z0-9]+$/;
	if($rnum!=''){
		if($RNUMEX.exec($rnum)){
			//附加码异步校验
			if(validateRnum($rnum)){
				//异步校验成功
				checkSuc("rnum");
				return true;
			}else{
				//异步校验失败
				return false;
			}
		}else{
			showMessage("验证码格式不正确！","rnum");
			return false;
		}
	}else{
		showMessage("验证码不能为空！","rnum");
		return false;
	}
}

//校验Ip 登录限制
function validateIp(){
	//Ajax 校验当前IP登录限制
	var $checkIp = false;
	$.ajax({
		  url:'/ac/ValidateIp',
		  async:false,
		  type:"POST",
		  data:"ceshi=false",
		  //contentType:"application/json; charset=utf-8",
		  dataType:"json",
		  success: function($data){
		     var $k1 = '';
		     var $v1 = '';
		     $.each($data, function(k, v) {
	           $k1 = k;
	           $v1 = v;
	         });
	         if($k1=='val-ip'&& $v1=='ok'){	//IP 校验成功
	         	$checkIp = true;
	         }else{					
	         	$checkIp = false;
	         }
		  },
		  error: function (msg) {
		  	 //alert("ERROR:"+msg.name);
      	  }
	 });
	 return $checkIp;
}

//附加码异步校验
function validateRnum($rnum){
	//Ajax 校验当前IP登录限制
	var $checkRnum = false;
	var $loginMode = getLoginMode(); 			//当前登录方式
	var $loginMethod = $("#loginID").val(); 	//账号类型
	var $loginName = $("#loginName").val(); 	//账号
	var $phone = '';
	if($loginMode==1){							//网站密码登录
		if($loginMethod==1){ 					//账号类型 手机号 
			$phone = $loginName;
		}else{									//账号类型是用户名
			if(checkMobileNo1($loginName)){		//手机号作为用户名
				$phone = $loginName;
			}
		}
	}
	if($loginMode==2){ 							//随机短信密码 登录
		$phone = $loginName;
	}
	var $service = $("#service").val();
	$.ajax({
		  url:'/ac/ValidateRnum',
		  async:false,			//是否使用异步校验
		  type:"POST",
		  data:{rnum:$rnum,user:$loginName,phone:$phone,service:$service,loginMethod:$loginMethod,loginMode:$loginMode},
		  //contentType:"application/json; charset=utf-8",
		  dataType:"json",
		  success: function($data){
		     var $k1 = '';
		     var $v1 = '';
		     $.each($data, function(k, v) {
	           $k1 = k;
	           $v1 = v;
	         });
	         var $m = getMessage($k1);
			 if($m==''){$m = $v1;}	//如果js 中定义错误提示信息，则返回js中信息，否则返回服务端定义的信息！
	         if($k1=='rnum-check'&& $v1=='ok'){	//IP 校验成功
	         	$checkRnum = true;
	         }else{					
	         	$checkRnum = false;
	         	showMessage($m,"rnum");
	         	$("#NumImage").attr("src","/ac/ValidateNum?smartID="+Math.ceil(Math.random()*10000000000));	//更换附加码
	         	$("#rnum").val("");	//清空附加码
	         }
		  },
		  error: function (msg) {
		  	 //alert("ERROR:"+msg.name);
      	  }
	 });
	 return $checkRnum;
}

//登录**************************************
function login(){
	var $loginMode = getLoginMode(); 			//当前登录方式
	var $loginMethod = $("#loginID").val(); 	//账号类型
	var $loginName = $("#loginName").val(); 	//账号
	var $password = $("#password").val();		//密码
	var $rnum = $("#rnum").val();				//验证码
	$("#user").val($loginName);
	$("#phone").val($loginName);		
	//********************
	setLoginCookie();							//存储当前操作用户
	setRememberCookie();						//设置 手机号 用户名 记住 Cookie		
	//IP限制判定
	if(!validateIp()){
		return ;
	}
	//**************
	if($loginMode==1){							//网站密码登录
		if($loginMethod==1){ 					//账号类型 手机号 
			if(!checkMBPhone($loginName)){		//手机号格式不正确
				return;
			}
			//获取是否是北京移动手机号的信息
			if(!goCheckBmcc($loginName)){
				return;
			}
		}else{ 									//账号类型 用户名
			if(!checkUsername($loginName)){		//用户名格式错误
				return;
			}
			$("#phone").val("");
		}
		if(!checkPassword($password,false)){	//校验密码格式
			return;
		}
		if(!checkRnum($rnum)){					//校验附加码
			return;
		}
	}
	//****************
	if($loginMode==2){ 							//随机短信密码 登录
		if(!checkMBPhone($loginName)){			//手机号格式不正确
			return;
		}
		//获取是否是北京移动手机号的信息
		if(!goCheckBmcc($loginName)){
			return;
		}
		var $smsNum = $("#smsNum").val();
		if(!checkSmsNum($smsNum)){				//随机短信密码 格式校验
			return;
		}
	}
	//*************
	if($loginMode==3){ 							//服务密码登录
		if(!checkMBPhone($loginName)){			//手机号格式不正确
			return;
		}
		if(!goCheckBmcc($loginName)){
			return;
		}
		if(!checkPassword($password,true)){		//校验服务密码格式
			return;
		}
		if(!checkRnum($rnum)){					//校验附加码
			return;
		}
	}
 	$("#frmLogin").submit();					//校验通过 ，提交表单
}
//登录******************************************

</script>
</head>
<body style="background-color:transparent;">
<div class="border-left fl"></div>
<div class="border-middle fl">
	<!--切换省  layout-->
   <div onMouseOut="HiddenDivCity();" onMouseOver="ShowDivCity();" id="DivCity" style=""> 
     <ul>
   		<li><a href="https://bj.ac.10086.cn/login" target="_parent">北京</a></li>
		<li><a href="https://gd.ac.10086.cn/login" target="_parent">广东</a></li>
		<li><a href="https://sh.ac.10086.cn/login" target="_parent">上海</a></li>
		<li><a href="https://tj.ac.10086.cn/login" target="_parent">天津</a></li>
		<li><a href="https://cq.ac.10086.cn/login" target="_parent">重庆</a></li>
		<li><a href="https://ln.ac.10086.cn/login" target="_parent">辽宁</a></li>
		<li><a href="https://js.ac.10086.cn/login" target="_parent">江苏</a></li>
		<li><a href="https://he.ac.10086.cn/login" target="_parent">河北</a></li>
		<li><a href="https://sc.ac.10086.cn/login" target="_parent">四川</a></li>
		<li><a href="https://sn.ac.10086.cn/login" target="_parent">陕西</a></li>
		<li><a href="https://sx.ac.10086.cn/login" target="_parent">山西</a></li>
		<li><a href="https://ha.ac.10086.cn/login" target="_parent">河南</a></li>
		<li><a href="https://jl.ac.10086.cn/login" target="_parent">吉林</a></li>
		<li><a href="https://hl.ac.10086.cn/login" target="_parent">黑龙江</a></li>
		<li><a href="https://nm.ac.10086.cn/login" target="_parent">内蒙古</a></li>
		<li><a href="https://sd.ac.10086.cn/login" target="_parent">山东</a></li>
		<li><a href="https://ah.ac.10086.cn/login" target="_parent">安徽</a></li>
		<li><a href="https://hn.ac.10086.cn/login" target="_parent">湖南</a></li>
		<li><a href="https://gx.ac.10086.cn/login" target="_parent">广西</a></li>
		<li><a href="https://jx.ac.10086.cn/login" target="_parent">江西</a></li>
		<li><a href="https://gz.ac.10086.cn/login" target="_parent">贵州</a></li>
		<li><a href="https://yn.ac.10086.cn/login" target="_parent">云南</a></li>
		<li><a href="https://xz.ac.10086.cn/login" target="_parent">西藏</a></li>
		<li><a href="https://gs.ac.10086.cn/login" target="_parent">甘肃</a></li>
		<li><a href="https://nx.ac.10086.cn/login" target="_parent">宁夏</a></li>
		<li><a href="https://zj.ac.10086.cn/login" target="_parent">浙江</a></li>
		<li><a href="https://fj.ac.10086.cn/login" target="_parent">福建</a></li>
		<li><a href="https://hi.ac.10086.cn/login" target="_parent">海南</a></li>
		<li><a href="https://hb.ac.10086.cn/login" target="_parent">湖北</a></li>
		<li><a href="https://qh.ac.10086.cn/login" target="_parent">青海</a></li>
		<li><a href="https://xj.ac.10086.cn/login" target="_parent">新疆</a></li>
     </ul>
   </div>
   <!--切换省  end-->
	<h2 class="user_login">
个人客户	</h2>
	<div class="login_content">
	<!-- form  -->
	<form name="frmLogin" id="frmLogin" action="https://bj.ac.10086.cn/ac/CmSsoLogin" method="post" class="form1">
	<input type="hidden" name="user" id="user"/>
	<input type="hidden" name="phone" id="phone" />
	<input type="hidden" name="backurl" value="" />
	<input type="hidden" name="continue" value="http://www.bj.10086.cn" />
	<input type="hidden" name="style" value="" />
	<input type="hidden" name="service" value="" />
	<input type="hidden" name="box" value="" />
	<input type="hidden" name="target" value="" />
	<!--登录方式 -->
	<div class="logtype" style="margin-top:5px;">
		<p class="ras fl" style="_margin-top:6px;">
			<span><input id="net1" type="radio" name="loginMode" class="radios" value="1" checked="checked"/>&nbsp;网站密码</span>
		</p>
		<p class="ras fl" id="sdlms" style="margin-left:25px;_margin-left:14px;*margin-left:12px; _margin-top:6px;">
			<span><input id="net2" type="radio" name="loginMode" class="radios" value="2" />&nbsp;随机密码</span>
		</p>
		<p class="ras fl" id="cusms" style="margin-left:10px;display:none;">
			<span><input id="net3" type="radio" name="loginMode" class="radios" value="3" />&nbsp;服务密码</span>
		</p>
	</div>
			
	<!-- 手机号 用户名 -->
	<div class="logtype">
		<span class="sjhm fl" id="sjhm">
			<div class="select_border">
				<div class="container">
					<select name="loginMethod" class="login_select" id="loginID" >
						<option selected="selected" value="1" id="se1" style="clear: both;">
							手机号码
						</option>
						<option value="0" id="se2" >
							&nbsp;用户名
						</option>
					</select>
				</div>
			</div>
		</span>
		<span class="label fl" id="yhm">手机号码：</span>

		<p class="fl" style="overflow: hidden;">
			<input id="loginName" name="loginName" type="text" class="text_one" value="请输入手机号" onfocus='if(this.value=="请输入手机号"|| this.value=="请输入用户名"){this.value=""};' />
     
            </p><span class="loginForm_link">
       		<!--切换省  触发layout-->
            <a onMouseOut="HiddenDivCity();" onMouseOver="ShowDivCity();"><font size="-1" style="margin-left:-20px">北京</font></a>
            <!--切换省  触发end-->
       </span>
	</div>
    <div id="errorinfologinName" class="prompt_mistake redtip" style="display:none" ><img src="/ac/cmsso/images/youhui-76.jpg" /></div>
			
	<!-- 网站密码 、服务密码-->
	<div class="logtype" id="logPwd_1">
		<span class="label2 fl" id="webpassLabel">网站密码：</span>
		<span class="label2 fl" id="cuspass" style="display: none;">服务密码：</span>
		<p class="fl">
		<input id="password" name="password" type="password" onblur="" class="text_two" />
		</p>
    <span style="float:left;margin-left:20px;">
			<a href="https://bj.ac.10086.cn/ac/html/resetpassword.html" id="forgetPassword" target="_blank" style="color: #0479CF; text-decoration: underline;" >忘记密码？</a>
		</span>
	</div>
    <div id="errorinfopassword" class="prompt_mistake redtip" style="display:none" ><img src="/ac/cmsso/images/youhui-76.jpg" /></div>
			
	<!-- 短信随机码 -->
	<div class="logtype" id="logPwd_2"  style="position: relative;display:none;">		
		<span class="label2 fl">随机密码：</span>
		<p class="fl">
			<input id="smsNum" name="smsNum" value="随机码" onfocus="if(this.value=='随机码') this.value='';"  type="text" class="text_two" maxlength="6" />
		</p>
		<span style="float:left;margin-left:20px;">
			<a href="#" title="获取随机密码" id="numLink" style="color: #0479CF; text-decoration: underline;">获取随机密码</a>
		</span>
	</div>
    <div id="errorinfosmsNum" class="prompt_mistake redtip" style="display:none"><img src="/ac/cmsso/images/youhui-76.jpg" /></div>
	
	<!-- 验证码 -->	
	<div class="logtype" id="logPwd_3" style="position: relative;display:none;">
		<span class="label3 fl">验证码：</span>
		<p class="fl"  style="margin-top:-5px;*margin-top:-1px;">
			<input name="rnum" id="rnum" type="text" class="text_one r_num" value="" />
		</p>
		<p id="ValidateNum" style="display: none;">
			<img class="yam_pic" id="NumImage" />
			<span class="tiptext">点击图片更换验证码</span>
		</p>
	</div>
    <div id="errorinfornum" class="prompt_mistake redtip" style="display:none"><img src="/ac/cmsso/images/youhui-76.jpg" /></div>
	
	<!-- 存储手机号 -->
	<div class="logtype" id="remberTele"
		style="clear: both; _margin-bottom: 5px; line-height: 25px; margin-top: 0px; display: block; color: #999;">
		<input type="checkbox" class="loginCheckBox" id="loginCheckBox" name="ckCookie" checked="checked" style="margin-left:-55px;_margin-left:-35px;" />
		<label for="loginCheckBox" id="rememberPhone">
			记住手机号码
		</label>
		<label for="loginCheckBox" id="rememberName" style="display:none">
			记住用户名
		</label>
	</div>

	<!-- 提示信息 -->
	<div id="info1" style="line-height: 20px; margin-top:-10px; margin-left:5px; display: none; color: #999; text-align: center;"></div>
	
	<!-- 提交按钮 -->
	<div class="logtype" style="text-align: center; clear: both;">
		<input name="login" id="loginBut" type="button" class="submit" value="" />
	</div>
	<p class="reg_text">
		<a href="https://bj.ac.10086.cn/ac/html/register.html" style="color: #0479CF; text-decoration: underline;" class="word3" target="_blank">注册</a>北京移动网站，24小时贴心服务尽情享受
	</p>
	
	</form><!-- form end -->
	</div><!-- login_content end  -->
</div><!-- border-middle end  -->

<div class="border-right fl"></div>

<div id="loginAgain" class="blk" style="background-color:#fff;clear:both;display:none;border:1px solid #ccc;position:relative;left:0px;margin-left:-145px;width:400px;margin-top:-150px;height:150px;">

	  <div style="font-size:18px;">
	  	<div style="font-size:15px;background:url(/ac/images/img1.jpg) repeat-x;color:#666666;height:30px;font-weight:bold;">
	  		<div style="margin-top:0px;margin-left:10px;padding-top:5px;">登录提示</div>
	  	</div>
	  	<div style="text-align:center;font-size:18px;margin-top:10px;height:20px;">
	  		
	  	</div>
	  	<div style="font-size:12px;height:40px;margin-top:-15px;text-align:;padding:5px;font-family:宋体,arial;line-height:24px;">
	  		&nbsp;&nbsp;&nbsp;&nbsp;尊敬的用户，您刚刚登录过并且仍未退出，如果希望继续本次登录，上次登录将被退出。
	  	</div>
	  </div>
	  <div style="position:relative;margin-top:16px;text-align:center;">
	  	<form action="https://bj.ac.10086.cn/ac/loginAgain" method="post">
	  		<input type="hidden" value="" name="backurl"/>
			<input type="hidden" value="http://www.bj.10086.cn" name="continue"/>
			<input type="hidden" value="" name="style"/>
			<input type="hidden" value="null" name="loginMethod"/>
			<input type="hidden" value="null" name="loginMode"/>
			<input type="hidden" value="" name="service" id="service"/>
			<input type="hidden" value="" name="box"/>
			<input type="hidden" value="" name="target"/>
			<input type="hidden" value="null" name="hostId"/>
			<input type="submit" name="submit" style="width:125px;height:26px;background:url(/ac/images/go.jpg) no-repeat;border:0px;margin-left:5px;" value=""/>
			<input type="button" name="buttom" id="close6" style="width:125px;height:26px;background:url(/ac/images/back.jpg) no-repeat;border:0px;" value=""/>
	  	</form>	
	  </div>

</div>
		

<script>
$(function(){
	var $fcode = '';
	if($fcode =='PP_1_0807'){
		$t = new PopupLayer({trigger:"#loginBut",popupBlk:"#loginAgain",closeBtn:"#close6",useOverlay:true,eventType:"mouseover"});
		$("#loginBut").mouseover();
		$("#loginBut").unbind("mouseover");	
	};
	$("#close6").bind("click",function(){
		$.ajax({
		  url:'/ac/CmSsoAjaxLogin',
		  async:false,
		  type:"POST",
		  data:{token:""},
		  //contentType:"application/json; charset=utf-8",
		  dataType:"json",
		  success: function($data){
		  },
		  error: function (msg) {
      	  }
	 	});
	});
});
</script>
</body>
</html>
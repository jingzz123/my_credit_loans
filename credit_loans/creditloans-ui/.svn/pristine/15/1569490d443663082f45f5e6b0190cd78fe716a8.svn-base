<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<title>玖行云电动汽车云平台首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/abc.min.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/static/css/abc.css" rel="stylesheet"  />
</head>
<body>
<div class="header layout">
  <div class="logo"></div>
  <div class="navsab">
    <ul>
      <li><a href="#">玖行云首页</a><span></span></li>
      <li><a href="#">手机版下载</a><span></span></li>
      <li><a href="#">帮助</a></li>
    </ul>
  </div>
</div>
<!--/.header end-->
<div class="banne">
  <div class="row">
  <div class="banner" id="banner" >
	<a href="#" class="d1 d3"></a>
	<a href="#" class="d1 d4"></a>
	<a href="#" class="d1 d5"></a>
	<div class="d2" id="banner_id">
		<ul>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
</div>
  </div>
  <!--/.row end--> 
</div>
<!--/.banner end-->
<div class="container marketing">
  <div class="row">
    <div class="col-lg-3"> 
      <i class=" icon-cogs"></i>
      <h2>系统管理</h2>
      <p>充电运营商管理，汽车运营商<br>
        管理，权限分配，密码设置。</p>
      <p><a href="${pageContext.request.contextPath}/views/login.jsp" class="push-title">操作进入</a></p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-3"> 
      <i class=" icon-bolt"></i>
      <h2>充电运营商</h2>
      <p>充电桩管理，位置标注，远程<br>
        维护，状态查看，故障报告。</p>
      <p><a href="${pageContext.request.contextPath}/views/login.jsp" class="push-title">操作进入</a></p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-3"> 
      <i class="  icon-truck"></i>
      <h2>汽车运营商</h2>
        车辆管理，司机管理，线路管理，<br/>
		车辆调度，充电站监控</p>
      <p><a href="${pageContext.request.contextPath}/views/login.jsp" class="push-title">操作进入</a></p>
    </div>
	<div class="col-lg-3"> 
      <i class=" icon-sitemap"></i>
      <h2>大数据系统</h2>
        开发、共享的数据接口，调度<br/>
		决策、运营决策、政府决策依据。</p>
      <p><a href="${pageContext.request.contextPath}/views/login.jsp" class="push-title">操作进入</a></p>
    </div>
    <!-- /.col-lg-4 --> 
  </div>
  <!-- /.row --> 
  <!--/.row end--> 
</div>
<!--/.content end-->
<div class="foot">
  <div class="footer layout">
    <p>Copyright © 商旗网. All Rights Reseved. 沪ICP备12029598号 京公网安备11010102001291号</p>
  </div>
  <!--/.footer end--> 
</div>
<!--/.foot end--> 
<script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script> 
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script type="text/javascript">
function banner(){	
	var bn_id = 0;
	var bn_id2= 1;
	var speed33=5000;
	var qhjg = 1;
    var MyMar33;
	$("#banner .d1").hide();
	$("#banner .d1").eq(0).fadeIn("slow");
	if($("#banner .d1").length>1)
	{
		$("#banner_id li").eq(0).addClass("nuw");
		function Marquee33(){
			bn_id2 = bn_id+1;
			if(bn_id2>$("#banner .d1").length-1)
			{
				bn_id2 = 0;
			}
			$("#banner .d1").eq(bn_id).css("z-index","2");
			$("#banner .d1").eq(bn_id2).css("z-index","1");
			$("#banner .d1").eq(bn_id2).show();
			$("#banner .d1").eq(bn_id).fadeOut("slow");
			$("#banner_id li").removeClass("nuw");
			$("#banner_id li").eq(bn_id2).addClass("nuw");
			bn_id=bn_id2;
		};
	
		MyMar33=setInterval(Marquee33,speed33);
		
		$("#banner_id li").click(function(){
			var bn_id3 = $("#banner_id li").index(this);
			if(bn_id3!=bn_id&&qhjg==1)
			{
				qhjg = 0;
				$("#banner .d1").eq(bn_id).css("z-index","2");
				$("#banner .d1").eq(bn_id3).css("z-index","1");
				$("#banner .d1").eq(bn_id3).show();
				$("#banner .d1").eq(bn_id).fadeOut("slow",function(){qhjg = 1;});
				$("#banner_id li").removeClass("nuw");
				$("#banner_id li").eq(bn_id3).addClass("nuw");
				bn_id=bn_id3;
			}
		})
		$("#banner_id").hover(
			function(){
				clearInterval(MyMar33);
			}
			,
			function(){
				MyMar33=setInterval(Marquee33,speed33);
			}
		)	
	}
	else
	{
		$("#banner_id").hide();
	}
}
</script>
<script type="text/javascript">banner()</script>
</body>
</html>

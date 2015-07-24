<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
	td,th {
		white-space: nowrap;
	}
</style>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {

});

//过滤上传文件类型
function checkFile() {
	var array = new Array('xls', 'xlsx');  //可以上传的文件类型
	//var array = new Array('zip');  //可以上传的文件类型
	var file = $("#fileField").val();
	if (file == '') {
		alert_message("请选择要上传的文件!");
		return false;
	} else {
		var fileContentType = file.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用：）
		var isExists = false;
		for (var i in array) {
			if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
				isExists = true;
				return true;
			}
		}
		if (isExists == false) {
			//alert("文件格式不正确，您只能选择Excel 2003/1997格式数据文件上传！");
			alert_message("文件格式不正确，您只能选择Excel 2003/1997格式数据文件上传！");
			//alert_message("文件格式不正确！");
			return false;
		}
		return false;
	}
	//经过ajax检测文件大小
}

function fileUpload(){
	if(!checkFile()){
		return;
	}
	var urlString = "${pageContext.request.contextPath}/black/uploadCheck";
	// 弹出进度框
	//$("#validation-form").submit();
	$.ajaxFileUpload({
		url:urlString,            //需要链接到服务器地址
		secureuri:false,
		fileElementId:'fileField',//文件选择框的id属性，文件框的名称最好设定为file1，而不是file
		contentType: 'xml', //服务器返回的格式，可以是xml,如果是json
		success: function (data, status){//相当于java中try语句块的用法
			var response = $(data).find('response');
			var result = $(response).find('tips').text();
			if(result != null && "" != result) {
				alert(result);
			} else {
				alert("success!");
				//window.location.href = "${pageContext.request.contextPath}/excelUpload/showNoPassInfo"
			}
		},
		error : function(data, status, e) { //服务器响应失败时的处理函数 
			alert("上传失败!");
		}
	});
}

// 确认信息onchange事件
function selectConfirmedType() {
	jQuery.ajax({
		type: "post",
	    url: "${pageContext.request.contextPath}/black/showConfirmedDetailsMap",
	    data: "val=" + $("#confirmedType_select").val(),
	    success: function (data) {
	    	if(data != null && data.length != 0) {
	    		var keyList = data.keyList;
	    		var valList = data.valList;
	    		$("#confirmedDetails_select").empty();
	    		for(var i = 0; i < keyList.length; i ++) {
	    			var optionObj;
	    			if(i == 0) {
	    				optionObj = "<option value='" + keyList[i] + "' selected='selected'>" + valList[i] + "</option>"
	    			} else {
	    				optionObj = "<option value='" + keyList[i] + "'>" + valList[i] + "</option>"
	    			}
	    			$("#confirmedDetails_select").append(optionObj);
	    		}
	    	}
	    },
	    error: function () {
	        alert("save dbxx error");
	    }	
	});
}

//下载错误数据
function downloadError(){
	var error = $("#error_count").val();
	if(error == 0){
		alert("不存在错误数据！");
		return;
	}		
	var urlString = "${pageContext.request.contextPath}/excelUpload/download";
	window.location.href = urlString;
}

//插入数据
function insert(){
	var correct = $("#correct_count").val();
	var db_correct = $("#correct_db_count").val();
	if(correct == 0){
		alert_message("不存在正确数据！");
		return;
	}
	// 弹出进度框
	showAlertModal();
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/excelUpload/insert",
        dataType:'json',
        success: function (data) {
        	var result = data.result;
        	if(result=='success'){
        		alert_message("正确数据加载成功！");
        		$("#correct_count").val(0);
        	}else{
        		alert_message("正确数据加载失败！");
        	}
        	hideAlertModal(); // 关闭进度框
        },
        error: function (data) {
        	hideAlertModal(); // 关闭进度框
        	//alert_message("error");
        	var err_msg = data.responseText;
        	if(err_msg.indexOf("登录超时，请重新登录") != -1) {
	        	alert("登录超时，请重新登录");
	        	window.location.href="${pageContext.request.contextPath}/login";
        	} else {
	        	alert_message("正确数据加载失败！");
        	}
        }
	});
}
</script>
<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">黑名单信息</a></li>
			<li class="active">批量上传</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				黑名单信息<small> <i class="icon-double-angle-right"></i>
					批量上传
				</small>
			</h1>
		</div>
		<div class="well">
			<p>共上传了<strong>${infoDTO.errorCount + infoDTO.correctCount}</strong>条记录，耗时<strong>${infoDTO.totalTime }</strong></p>
			<p>验证通过<strong>${infoDTO.correctCount }</strong>条，另有<strong>${infoDTO.errorCount }</strong>条存在错误！</p>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<button type="button" class="btn btn-primary" onclick="downloadError();">下载错误数据</button>
              		<button type="button" class="btn btn-primary" onclick="insert();">加载正确数据</button>
				</div>
				<div class="col-md-12" style="overflow-x: auto;">
				${infoDTO.errorInformation }
				</div>
			</div>
		</div>
		<!-- class:row -->

	</div>
	<!-- /.page-content -->

</div>
<!-- /.main-content -->
<%@ include file="./../../common/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
				//alert("success!");
				window.location.href = "${pageContext.request.contextPath}/black/showNoPassInfo"
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
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="singleAddBlack" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<input type="file" name="fileField" class="file" id="fileField" />
							</div>
						</fieldset>
						<div class="form-group">
							<div class="col-sm-offset-5">
								<input type="button" class="btn btn-primary" onclick="fileUpload()" value="上传"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-success" value="重置"/>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
		<!-- class:row -->

	</div>
	<!-- /.page-content -->

</div>
<!-- /.main-content -->
<script type="text/javascript">
    $('.form_date').datetimepicker({
    	format: "yyyymmdd",
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
</script>
<%@ include file="./../../common/footer.jsp"%>
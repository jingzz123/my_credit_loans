<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
});

//验证参数是否为空
function validateFrom() {
	var name = $("#name").val();
	var idNumber = $("#idNumber").val();
	var	mobile = $("#mobile").val();
	var	tell = $("#tell").val();
	var	address = $("#address").val();
	var	workName = $("#workName").val();
	
	if ((name == null || name == "") 
			&& (idNumber == null || idNumber == "") 
			&& (mobile == null || mobile == "") 
			&& (tell == null || tell == "") 
			&& (address == null || address == "")
			&& (workName == null || workName == "")) {
		
		alert("请至少输入一个查询条件");
		return false;
	}
	
	if (name.length > 250) {
		alert("姓名长度过长");
		return false;
	}
	if (address.length > 250) {
		alert("地址长度过长");
		return false;
	}
	if (workName.length > 250) {
		alert("单位名称长度过长");
		return false;
	}
	
	if (mobile != "" && mobile != null) {
		if (!/^1[3|4|5|8][0-9]\d{8}$/.test(mobile)) { 
			alert("非法的手机号码");
			return false;
		}
	}
	
	if (tell != "" && tell != null) {
		if (!/^(\d{3,4}-?)?\d{7,9}$/g.test(tell)) {
			alert_message("非法的固定电话号码，正确例如(区号+号码)：01012345678");
			return false;
		}
	}
	
	if (idNumber != "" && idNumber != null) {
		var reg = new RegExp("^[a-zA-Z0-9]*$"); 
		if (!reg.test(idNumber)) {
			alert("非法的证件号码");
			return false;
		}
	}
	return true;
}

// 查询比对
function queryCompare() {
	if(!validateFrom()) {
		return;
	}
	// 处理表单数据
	var conditionJson = $('#queryBlack').serializeObject();
	// 请求
	jQuery.ajax({
        type: 'POST',
        url: "${pageContext.request.contextPath}/black/query",
        contentType: 'application/json',
        data: JSON.stringify(conditionJson),
        success: function (compareResults) {
        	if(compareResults == null) {
        		alert("查询结果为空");
        		return;
        	}
        	var queryCompareResultItemList = compareResults.queryCompareResultItemList;
        	if(queryCompareResultItemList.length == 0) {
        		alert("查询结果为空");
        		return;
        	}
        	// 显示列表title和图例
        	$("#result_title").show();
        	$("#result_space").empty();
        	$("#symbol").show();
        		
        		var appendHtml = 
        			"<table class=\"table table-striped table-bordered\" id=\"queryCompareTable\">" +
				      "<thead>" +
				        "<tr>" +
				          "<th></th><th colspan=\"2\">10天内</th><th colspan=\"2\">30天内</th><th colspan=\"2\">90天内</th><th colspan=\"2\">365天内</th><th colspan=\"2\">所有</th>" +
				        "</tr>" +
				      "</thead>" +
				      "<tbody>" +
				        "<tr>" +
				          "<td>字段</td><td>匹配数</td><td>涉及银行</td><td>匹配数</td><td>涉及银行</td><td>匹配数</td><td>涉及银行</td><td>匹配数</td><td>涉及银行</td><td>匹配数</td><td>涉及银行</td>" +
				        "</tr>";
			        
		        for(var j = 0; j < queryCompareResultItemList.length; j++) {
		        	var compareResultItem = queryCompareResultItemList[j];
		        	var matchItems = compareResultItem.matchItemList;
		        	
		        	if (matchItems.length > 0) {
		        		appendHtml = appendHtml + 
		        			"<tr class=\"table-color\" onclick=\"showHeadTd(this)\">" +
					          "<td><font color=\"#9D9D9D\">" + compareResultItem.field + "</font></td>";
						for (var k = 0; k < matchItems.length; k++) {
							var matchItem = matchItems[k];
							
					        var matchCountHtml = "";
					        if (matchItem.matchCount == 0) {
					        	matchCountHtml = "<td style=\"background-color: #FFFFFF\"><font color=\"#9D9D9D\">" + matchItem.matchCount + "</font></td>";
					        } else if (matchItem.matchCount == 1) {
								matchCountHtml = "<td style=\"background-color: #FFFF37\" onclick=\"showTh(this,"+i+")\"><font color=\"#9D9D9D\" onclick=\"showDetail('" + matchItem.itemIdList + "', '" + compareResultItem.blackType + "')\" style=\"cursor:pointer\">" + matchItem.matchCount + "</font></td>";
							} else if (matchItem.matchCount == 2) {
								matchCountHtml = "<td style=\"background-color: #FFD1A4\" onclick=\"showTh(this,"+i+")\"><font color=\"#9D9D9D\" onclick=\"showDetail('" + matchItem.itemIdList + "', '" + compareResultItem.blackType + "')\" style=\"cursor:pointer\">" + matchItem.matchCount + "</font></td>";
							} else if (matchItem.matchCount == 3) {
								matchCountHtml = "<td style=\"background-color: #FF8000\" onclick=\"showTh(this,"+i+")\"><font color=\"#9D9D9D\" onclick=\"showDetail('" + matchItem.itemIdList + "', '" + compareResultItem.blackType + "')\" style=\"cursor:pointer\">" + matchItem.matchCount + "</font></td>";
							} else if (matchItem.matchCount == 4) {
								matchCountHtml = "<td style=\"background-color: #BB3D00\" onclick=\"showTh(this,"+i+")\"><font color=\"#9D9D9D\" onclick=\"showDetail('" + matchItem.itemIdList + "', '" + compareResultItem.blackType + "')\" style=\"cursor:pointer\">" + matchItem.matchCount + "</font></td>";
							} else if (matchItem.matchCount >= 5) {
								matchCountHtml = "<td style=\"background-color: red\" onclick=\"showTh(this,"+i+")\"><font color=\"#9D9D9D\" onclick=\"showDetail('" + matchItem.itemIdList + "', '" + compareResultItem.blackType + "')\" style=\"cursor:pointer\">" + matchItem.matchCount + "</font></td>";
							}
							
					        var referBankCountHtml = "";
					        if (matchItem.matchCount == 0) {
					        	referBankCountHtml = "<td style=\"background-color: #FFFFFF\"><font color=\"#9D9D9D\">" + matchItem.referBankCount + "</font></td>";
					        } else if (matchItem.referBankCount == 1) {
					        	referBankCountHtml = "<td style=\"background-color: #FFFF37\"><font color=\"#9D9D9D\">" + matchItem.referBankCount + "</font></td>";
							} else if (matchItem.referBankCount == 2) {
								referBankCountHtml = "<td style=\"background-color: #FFD1A4\"><font color=\"#9D9D9D\">" + matchItem.referBankCount + "</font></td>";
							} else if (matchItem.referBankCount == 3) {
								referBankCountHtml = "<td style=\"background-color: #FF8000\"><font color=\"#9D9D9D\">" + matchItem.referBankCount + "</font></td>";
							} else if (matchItem.referBankCount == 4) {
								referBankCountHtml = "<td style=\"background-color: #BB3D00\"><font color=\"#9D9D9D\">" + matchItem.referBankCount + "</font></td>";
							} else if (matchItem.referBankCount >= 5) {
								referBankCountHtml = "<td style=\"background-color: red\"><font color=\"#9D9D9D\">" + matchItem.referBankCount + "</font></td>";
							}
					        appendHtml = appendHtml + matchCountHtml + referBankCountHtml;
						}											          
						appendHtml = appendHtml + "</tr>";
		        	}
		        }
		        
		        appendHtml = appendHtml + "</tbody>" + "</table>";
		        // 挂上
		        $("#result_space").append(appendHtml);
        	
        },
        error: function (data) {
	        var err_msg = data.responseText;
        	if(err_msg.indexOf("登录超时，请重新登录") != -1) {
	        	alert("登录超时，请重新登录");
	        	window.location.href="${pageContext.request.contextPath}/login";
        	} else {
		        alert_message("比对出错!");
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
			<li class="active">查询比对</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				黑名单信息<small> <i class="icon-double-angle-right"></i>
					查询比对
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="queryBlack" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="name" class="col-sm-1 control-label">姓名</label>
								<div class="col-sm-3">
									<input type="text" id="name" name="name" class="form-control"
										placeholder="输入人姓名">
								</div>
								<span id="error_name" style="color: red" class="col-sm-2"></span>								
								<label for="idNumber" class="col-sm-1 control-label">身份证号码</label>
								<div class="col-sm-3">
									<input type="text" id="idNumber" name="idNumber" class="form-control"
										placeholder="输入身份证号码">
								</div>
								<span id="error_idNumber" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="mobile" class="col-sm-1 control-label">手机</label>
								<div class="col-sm-3">
									<input type="text" id="mobile" name="mobile" class="form-control"
										placeholder="输入手机">
								</div>
								<span id="error_mobile" style="color: red" class="col-sm-2"></span>								
								<label for="tell" class="col-sm-1 control-label">固定电话</label>
								<div class="col-sm-3">
									<input type="text" id="tell" name="tell" class="form-control"
										placeholder="输入固定电话号码">
								</div>
								<span id="error_tell" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="address" class="col-sm-1 control-label">地址</label>
								<div class="col-sm-3">
									<input type="text" id="address" name="address" class="form-control"
										placeholder="输入地址">
								</div>
								<span id="error_address" style="color: red" class="col-sm-2"></span>								
								<label for="workName" class="col-sm-1 control-label">单位名称</label>
								<div class="col-sm-3">
									<input type="text" id="workName" name="workName" class="form-control"
										placeholder="输入单位名称">
								</div>
								<span id="error_workName" style="color: red" class="col-sm-2"></span>
							</div>
							<fieldset>
								<legend></legend>
								<div class="form-group">
									<label for="" class="col-sm-5 control-label">选择机构范围：</label>
								    <div class="col-sm-3">							    
								    	<input name="depCondition" type="radio" checked="checked" value="1"/> 所有
								      	&nbsp;&nbsp;&nbsp;
								    	<input name="depCondition" type="radio" value="2"/> 本机构
								      	&nbsp;&nbsp;&nbsp;
								      	<input name="depCondition" type="radio" value="3"/> 不含本机构
								    </div>
								</div>
							</fieldset>
						</fieldset>
						<div class="form-group">
							<div class="col-sm-offset-5">
								<input type="button" class="btn btn-primary" onclick="queryCompare();" value="查询"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-success" value="联合查询"/>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
		<!-- class:row -->
		<div class="row"  id="result_title" style="display: none;">
			<div class="col-xs-12">
				<div class="col-md-12">
					<div class="page-header">
						<h5>查询比对结果</h5>
					</div>
				</div>
				<br/>
				<div class="widget-content">
		            <div id="result_space">
		            	<!-- 展示结果 -->
		            </div>
		             <div class="table-box table-box2" id="symbol" style="display: none;">
		              <table class="table table-striped table-bordered">
		                <thead>
		                  <tr>
		                    <th>图例</th>
		                    <th>&nbsp;</th>
		                    <th>&nbsp;</th>
		                    <th>&nbsp;</th>
		                    <th>&nbsp;</th>
		                    <th>&nbsp;</th>
		                  </tr>
		                </thead>
		                <tr>
		                  <td>0</td>
		                  <td>1</td>
		                  <td>2</td>
		                  <td>3</td>
		                  <td>4</td>
		                  <td>>=5</td>
		                </tr>
		                <tr class="table-color">
		                  <td style="background-color: #FFFFFF">&nbsp;</td>
		                  <td style="background-color: #FFFF37">&nbsp;</td>
		                  <td style="background-color: #FFD1A4">&nbsp;</td>
		                  <td style="background-color: #FF8000">&nbsp;</td>
		                  <td style="background-color: #BB3D00">&nbsp;</td>
		                  <td style="background-color: red">&nbsp;</td>
		                </tr>
		              </table>
		            </div>
	          </div>
			</div>
		</div>
	</div>
	<!-- /.page-content -->

</div>
<!-- /.main-content -->
<%@ include file="./../../common/footer.jsp"%>
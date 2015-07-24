<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
	$("#password").blur(function(){
		if ($.trim($("#password").val()) != "") {
			checkMobile();
		}
	});
});

//手机号码验证
function checkMobile() {
	//$("#mobile_info2").submit();
	var mobileProp = $('#mobile_info2').serializeObject();
	jQuery.ajax({
		type: 'POST',
        url: "${pageContext.request.contextPath}/mobileInformation/showMobileLoginValidate",
        contentType: "application/json",
	    data: JSON.stringify(mobileProp),
        success: function (data) {
        	if (data.length != 0) {
	        	if (data.url != null && data.url != "") {
		        	$("#imgDiv").css("display", "block")
		        	$("#validateImage").attr("src", data.url);
	        	}
	        	$("#next").removeAttr("disabled");
        	}
        },
        error: function (data) {
        }
	});
}

</script>
<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">信息查看</a></li>
			<li class="active">手机信息查看</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				信息查看<small> <i class="icon-double-angle-right"></i>
					手机信息查看
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<div class="form-group">
						手机号码：<span>${phoneInfo.mobile }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						运营商：
						<c:choose>
						    <c:when test="${phoneInfo.isp == ''}">
						      <span>未知</span>
						    </c:when>
							<c:otherwise>  
						      <span>${phoneInfo.isp }</span>
						   	</c:otherwise>
					  	</c:choose>
					  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						归属地：
						<c:choose>
						    <c:when test="${phoneInfo.province == ''}">
						      <span>未知</span>
						    </c:when>
							<c:otherwise>  
						      <span>${phoneInfo.province }</span>&nbsp;(省/市)
						   	</c:otherwise>
					  	</c:choose>
					  	<c:if test="${phoneInfo.cityName != ''}">
					  		<span>${phoneInfo.cityName }</span>&nbsp;(市/县)
					  	</c:if>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<ul id="myTab" class="nav nav-pills nav-stacked">
							   <li class="active">
							      <a href="#personInfo" data-toggle="tab">个人信息</a>
							   </li>
							   <li><a href="#history" data-toggle="tab">历史账单</a></li>
							   <li><a href="#java" data-toggle="tab">iOS</a></li>
							</ul>
						</div>
						<div class="col-sm-10 tab-content">
								<div class="tab-pane fade in active" id="personInfo">
									<table class="table table-hover">
										<tr>
											<td>姓名</td>
											<td>${personInfo.custName }</td>
										</tr>
										<tr>
											<td>性别</td>
											<td>${personInfo.custsex }</td>
										</tr>
										<tr>
											<td>身份证</td>
											<td>${personInfo.idNumber }</td>
										</tr>
										<tr>
											<td>地址</td>
											<td>${personInfo.certaddr }</td>
										</tr>
										<tr>
											<td>入网日期</td>
											<td>${personInfo.opendate }</td>
										</tr>
										<tr>
											<td>当前状态</td>
											<td>${personInfo.status }</td>
										</tr>
										<tr>
											<td>当前套餐</td>
											<td>${personInfo.packageName }</td>
										</tr>
										<tr>
											<td>付款类型</td>
											<td>${personInfo.paytype }</td>
										</tr>
										<tr>
											<td>所属品牌</td>
											<td>${personInfo.brandName }</td>
										</tr>
									</table>
								</div>
								<div class="tab-pane fade" id="history">
									<ul id="myTab1" class="nav nav-tabs">
										<c:forEach var="month" items="${historyMonth}" varStatus="status">
											<c:choose>
											    <c:when test="${status.index == 0}">
												   <li class="active">
												      <a href="#home${status.index }" data-toggle="tab">${month }</a>
												   </li>
											    </c:when>
												<c:otherwise>  
												   <li><a href="#home${status.index }" data-toggle="tab">${month }</a></li>
											   	</c:otherwise>
										  	</c:choose>
										</c:forEach>
									</ul>
									<div class="tab-content" style="height: 500px;">
										<c:forEach var="unicomHistoryDTO" items="${historySummary}" varStatus="status">
											<c:choose>
											    <c:when test="${status.index == 0}">
												   <div class="tab-pane fade in active" id="home${status.index }">
												   		<div class="form-group col-sm-12">
															<label class="col-sm-2">客户姓名：</label>
															<label class="col-sm-4">${unicomHistoryDTO.customName }</label>
															<label class="col-sm-2">本月消费：</label>
															<label class="col-sm-4">${unicomHistoryDTO.discountFee }元</label>
														</div>
												   		<div class="form-group col-sm-12">
															<label class="col-sm-2">手机号码：</label>
															<label class="col-sm-4">${unicomHistoryDTO.userNumber }</label>
															<label class="col-sm-2">本月抵扣：</label>
															<label class="col-sm-4">${unicomHistoryDTO.discountFee }元</label>
														</div>
												   		<div class="form-group col-sm-12">
															<label class="col-sm-2">实际应缴：</label>
															<label class="col-sm-4">${unicomHistoryDTO.payTotal}元</label>
															<label class="col-sm-2">计费周期：</label>
															<label class="col-sm-4">${unicomHistoryDTO.month }</label>
														</div>
														<div class="form-group col-sm-12">
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th>费用名称</th>
																		<th>金额/元</th>
																		<th>费用明细</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="unicomHistoryColumnList" items="${unicomHistoryDTO.list}" varStatus="status1">
																		<tr>
																			<td>${unicomHistoryColumnList.name}</td>
																			<td>${unicomHistoryColumnList.fee}</td>
																			<td>
																				<c:forEach var="unicomHistoryItem" items="${unicomHistoryColumnList.itemList}" varStatus="status2">
																					${unicomHistoryItem.name }：${unicomHistoryItem.fee }&nbsp;&nbsp;
																				</c:forEach>
																			</td>
																		</tr>
																	</c:forEach>
																	<tr>
																		<td>抵扣合计</td>
																		<td><strong>${unicomHistoryDTO.sumFee}</strong></td>
																		<td></td>
																	</tr>
																	<tr>
																		<td>实际应缴合计</td>
																		<td><strong>${unicomHistoryDTO.payTotal}</strong></td>
																		<td></td>
																	</tr>
																</tbody>
															</table>
														</div>
														<div class="form-group col-sm-12">
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th>本月新增积分</th>
																		<th>已兑换积分</th>
																		<th>可用积分余额</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>${unicomHistoryDTO.preMonthInterral }</td>
																		<td>${unicomHistoryDTO.usedIntegral }</td>
																		<td>${unicomHistoryDTO.useIntegral }</td>
																	</tr>
																</tbody>
															</table>
														</div>
												   </div>
											    </c:when>
												<c:otherwise>  
												   <div class="tab-pane fade" id="home${status.index }">
												   		<div class="form-group col-sm-12">
															<label class="col-sm-2">客户姓名：</label>
															<label class="col-sm-4">${unicomHistoryDTO.customName }</label>
															<label class="col-sm-2">本月消费：</label>
															<label class="col-sm-4">${unicomHistoryDTO.discountFee }元</label>
														</div>
												   		<div class="form-group col-sm-12">
															<label class="col-sm-2">手机号码：</label>
															<label class="col-sm-4">${unicomHistoryDTO.userNumber }</label>
															<label class="col-sm-2">本月抵扣：</label>
															<label class="col-sm-4">${unicomHistoryDTO.discountFee }元</label>
														</div>
												   		<div class="form-group col-sm-12">
															<label class="col-sm-2">实际应缴：</label>
															<label class="col-sm-4">${unicomHistoryDTO.payTotal}元</label>
															<label class="col-sm-2">计费周期：</label>
															<label class="col-sm-4">${unicomHistoryDTO.month }</label>
														</div>
														<div class="form-group col-sm-12">
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th>费用名称</th>
																		<th>金额/元</th>
																		<th>费用明细</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="unicomHistoryColumnList" items="${unicomHistoryDTO.list}" varStatus="status1">
																		<tr>
																			<td>${unicomHistoryColumnList.name}</td>
																			<td>${unicomHistoryColumnList.fee}</td>
																			<td>
																				<c:forEach var="unicomHistoryItem" items="${unicomHistoryColumnList.itemList}" varStatus="status2">
																					${unicomHistoryItem.name }：${unicomHistoryItem.fee }&nbsp;&nbsp;
																				</c:forEach>
																			</td>
																		</tr>
																	</c:forEach>
																	<tr>
																		<td>抵扣合计</td>
																		<td><strong>${unicomHistoryDTO.sumFee}</strong></td>
																		<td></td>
																	</tr>
																	<tr>
																		<td>实际应缴合计</td>
																		<td><strong>${unicomHistoryDTO.payTotal}</strong></td>
																		<td></td>
																	</tr>
																</tbody>
															</table>
														</div>
														<div class="form-group col-sm-12">
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th>本月新增积分</th>
																		<th>已兑换积分</th>
																		<th>可用积分余额</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>${unicomHistoryDTO.preMonthInterral }</td>
																		<td>${unicomHistoryDTO.usedIntegral }</td>
																		<td>${unicomHistoryDTO.useIntegral }</td>
																	</tr>
																</tbody>
															</table>
														</div>
												   </div>
											   	</c:otherwise>
										  	</c:choose>
										</c:forEach>
									</div>
								</div>
								<div class="tab-pane fade" id="java">java</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- class:row -->

	</div>
	<!-- /.page-content -->

</div>
<!-- /.main-content -->
<%@ include file="./../../common/footer.jsp"%>
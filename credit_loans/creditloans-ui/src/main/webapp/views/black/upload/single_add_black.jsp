<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>
<script type="text/javascript">

$().ready(function() {
	$("#singleAddBlack").validate({
	    onkeyup: false,
		rules: {
			name:{
				required:true,
				rangelength:[2,30]
			},
			idNumber:{
				required:true,
				isIdCardNo:true
			},
			mobile:{
				required:true,
				isMobile:true
			},
			homeAddress:{
				required:true
			},
			homeTell:{
				required:true,
				isPhone:true
			},
			workName:{
				required:true
			},
			workAddress:{
				required:true
			},
			workTell:{
				required:true,
				isPhone:true
			},
			appliedOn:{
				required:true,
			},
			confirmedDate:{
				required:true,
			}
		},
		messages:{
			name:{
				required:"输入人姓名",
				rangelength:"姓名长度为2~30个字符"
			},
			idNumber:{
				required:"输入身份证号码",
				isIdCardNo:"请输入正确的身份证号码"
			},
			mobile:{
				required:"请输入手机",
			},
			homeAddress:{
				required:"请输入家庭住址"
			},
			homeTell:{
				required:"请输入家庭电话"
			},
			workName:{
				required:"请输入单位名称"
			},
			workAddress:{
				required:"请输入单位地址"
			},
			workTell:{
				required:"请输入单位电话"
			},
			appliedOn:{
				required:"请选择贷款申请日期"
			},
			confirmedDate:{
				required:"请选择确认日期"
			}
		},
		errorPlacement: function(error, element) {
			$(element).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
			var errmsg = "";
			$("#singleAddBlack").find("input[name='contactTells']").each(function() {
				var tell = $(this).val();
				if("" != tell && !tel.test(tell)) {
					errmsg = "请输入正确的电话号码";
					$(this).parent().next().append("请输入正确的电话号码");
				}
			});
			if(errmsg != "") {
				return;
			}
			var singleAddBlack = $('#singleAddBlack').serializeObject();
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/black/doSingleAddBlack",
			    contentType: "application/json",
			    data: JSON.stringify(singleAddBlack),
			    success: function (data) {
			    	if(data > 0){
				        alert("添加成功！");
			    	}else{
			    		alert("添加失败！");
			    	}
			    },
			    error: function () {
			        alert("添加出错！");
			    }	
			});
		}
	});

});

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
			<li class="active">单条增加</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				黑名单信息<small> <i class="icon-double-angle-right"></i>
					单条增加黑名单信息
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
								<label for="homeAddress" class="col-sm-1 control-label">家庭住址</label>
								<div class="col-sm-3">
									<input type="text" id="homeAddress" name="homeAddress" class="form-control"
										placeholder="输入家庭住址">
								</div>
								<span id="error_homeAddress" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="homeTell" class="col-sm-1 control-label">家庭电话</label>
								<div class="col-sm-3">
									<input type="text" id="homeTell" name="homeTell" class="form-control"
										placeholder="输入家庭电话">
								</div>
								<span id="error_homeTell" style="color: red" class="col-sm-2"></span>
								<label for="workName" class="col-sm-1 control-label">单位名称</label>
								<div class="col-sm-3">
									<input type="text" id="workName" name="workName" class="form-control"
										placeholder="输入单位名称">
								</div>
								<span id="error_workName" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="workAddress" class="col-sm-1 control-label">单位地址</label>
								<div class="col-sm-3">
									<input type="text" id="workAddress" name="workAddress" class="form-control"
										placeholder="输入单位地址">
								</div>
								<span id="error_workAddress" style="color: red" class="col-sm-2"></span>
								<label for="workTell" class="col-sm-1 control-label">单位电话</label>
								<div class="col-sm-3">
									<input type="text" id="workTell" name="workTell" class="form-control"
										placeholder="输入单位电话">
								</div>
								<span id="error_workTell" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="confirmedType" class="col-sm-1 control-label">确认状态</label>
								<div class="col-sm-3">
									<select id="confirmedType_select" name="confirmedType" class="form-control" onchange="selectConfirmedType();">
										<c:forEach items="${confirmedTypeMap }" var="items" varStatus="status1">
											<c:if test="${status1.index == 0 }">
												<option value="${items.key }" selected="selected">${items.value }</option>
											</c:if>
											<c:if test="${status1.index != 0 }">
												<option value="${items.key }">${items.value }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<span id="error_confirmedType" style="color: red" class="col-sm-2"></span>
								<label for="loanType" class="col-sm-1 control-label">贷款类型</label>
								<div class="col-sm-3">
									<select id="loanType_select" name="loanType" class="form-control">
										<c:forEach items="${loanMap }" var="items" varStatus="status1">
											<c:if test="${status1.index == 0 }">
												<option value="${items.key }" selected="selected">${items.value }</option>
											</c:if>
											<c:if test="${status1.index != 0 }">
												<option value="${items.key }">${items.value }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<span id="error_loanType" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="loanType" class="col-sm-1 control-label">确认状态详细</label>
								<div class="col-sm-3">
									<select id="confirmedDetails_select" name="confirmedDetails" class="form-control">
									</select>
								</div>
								<span id="error_loanType" style="color: red" class="col-sm-2"></span>
								<label for="appliedOn" class="col-sm-1 control-label">贷款申请日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="appliedOn" name="appliedOn" type="text"
											placeholder="贷款申请日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_appliedOn" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-1 control-label">联系人1姓名</label>
								<div class="col-sm-3">
									<input type="text" name="contactNames" class="form-control"
										placeholder="输入联系人1姓名">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
								<label for="" class="col-sm-1 control-label">联系人1单位名称</label>
								<div class="col-sm-3">
									<input type="text" name="contactWorks" class="form-control"
										placeholder="输入联系人1单位名称">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-1 control-label">联系人1电话</label>
								<div class="col-sm-3">
									<input type="text" name="contactTells" class="form-control"
										placeholder="输入联系人1姓名">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
								<label for="" class="col-sm-1 control-label">与联系人1人关系</label>
								<div class="col-sm-3">
									<select name="contactRelationships" class="form-control">
										<c:forEach items="${contactRelationshipMap }" var="items" varStatus="status1">
											<c:if test="${status1.index == 0 }">
												<option value="${items.key }" selected="selected">${items.value }</option>
											</c:if>
											<c:if test="${status1.index != 0 }">
												<option value="${items.key }">${items.value }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-1 control-label">联系人2姓名</label>
								<div class="col-sm-3">
									<input type="text" name="contactNames" class="form-control"
										placeholder="输入联系人2姓名">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
								<label for="" class="col-sm-1 control-label">联系人2单位名称</label>
								<div class="col-sm-3">
									<input type="text" name="contactWorks" class="form-control"
										placeholder="输入联系人2单位名称">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-1 control-label">联系人2电话</label>
								<div class="col-sm-3">
									<input type="text" name="contactTells" class="form-control"
										placeholder="输入联系人2姓名">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
								<label for="" class="col-sm-1 control-label">与联系人2人关系</label>
								<div class="col-sm-3">
									<select name="contactRelationships" class="form-control">
										<c:forEach items="${contactRelationshipMap }" var="items" varStatus="status1">
											<c:if test="${status1.index == 0 }">
												<option value="${items.key }" selected="selected">${items.value }</option>
											</c:if>
											<c:if test="${status1.index != 0 }">
												<option value="${items.key }">${items.value }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-1 control-label">联系人3姓名</label>
								<div class="col-sm-3">
									<input type="text" name="contactNames" class="form-control"
										placeholder="输入联系人3姓名">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
								<label for="" class="col-sm-1 control-label">联系人3单位名称</label>
								<div class="col-sm-3">
									<input type="text" name="contactWorks" class="form-control"
										placeholder="输入联系人3单位名称">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="" class="col-sm-1 control-label">联系人3电话</label>
								<div class="col-sm-3">
									<input type="text" name="contactTells" class="form-control"
										placeholder="输入联系人3姓名">
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
								<label for="" class="col-sm-1 control-label">与联系人3人关系</label>
								<div class="col-sm-3">
									<select name="contactRelationships" class="form-control">
										<c:forEach items="${contactRelationshipMap }" var="items" varStatus="status1">
											<c:if test="${status1.index == 0 }">
												<option value="${items.key }" selected="selected">${items.value }</option>
											</c:if>
											<c:if test="${status1.index != 0 }">
												<option value="${items.key }">${items.value }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<span id="" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="comments" class="col-sm-1 control-label">备注</label>
								<div class="col-sm-3">
									<input type="text" name="comments" class="form-control"
										placeholder="输入备注">
								</div>
								<span id="error_comments" style="color: red" class="col-sm-2"></span>
								<label for="confirmedDate" class="col-sm-1 control-label">确认日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="confirmedDate" name="confirmedDate" type="text"
											placeholder="确认日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_confirmedDate" style="color: red" class="col-sm-2"></span>
							</div>
							<fieldset>
								<legend></legend>
								<div class="form-group">
									<label for="married" class="col-sm-5 control-label">是否已婚</label>
								    <div class="col-sm-3">							    
								    	<input id="married_yes" name="married" type="radio" checked="checked" value="1"/> 已婚 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="married_not" name="married" type="radio" value="2"/> 未婚
								    </div>
								    <span id="error_married" style="color: red"></span>
								</div>
							</fieldset>
						</fieldset>
						<div class="form-group">
							<div class="col-sm-offset-5">
								<input type="submit" class="btn btn-primary" value="保存"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" class="btn btn-success" value="重置"/>
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
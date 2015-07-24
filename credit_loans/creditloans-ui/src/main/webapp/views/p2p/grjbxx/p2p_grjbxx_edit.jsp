<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">P2P贷款业务</a></li>
			<li class="active">个人基本信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				个人基本信息<small> <i class="icon-double-angle-right"></i>
					修改个人基本信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="grjbxxInfoForm" class="form-horizontal">
						<fieldset>
							<legend style="font-size: 16px; color: gray;">基本信息</legend>
							<div class="form-group">
								<label for="xm" class="col-sm-1 control-label">姓名</label>
								<div class="col-sm-3">
									<input type="text" id="xm" name="xm" class="form-control"
										placeholder="输入姓名" value="${p2pGrjbxxDto.xm}">
								</div>
								<span id="error_xm" style="color: red" class="col-sm-2">*</span>
								<label for="zjlx" class="col-sm-1 control-label">证件类型</label>
								<div class="col-sm-3">
									<select id="zjlx_select" name="zjlx" class="form-control" onchange="removeZjhm();">
										<option value="">- 证件类型 -</option>
										<option value="0">身份证</option>
									</select>
								</div>
								<span id="error_zjlx" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="zjhm" class="col-sm-1 control-label">证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="zjhm" name="zjhm" class="form-control"
										placeholder="输入证件号码" value="${p2pGrjbxxDto.zjhm}">
								</div>
								<span id="error_zjhm" style="color: red" class="col-sm-2">*</span>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 16px; color: gray;">身份信息</legend>
							<div class="form-group">
								<label for="xb" class="col-sm-1 control-label">性别</label>
								<div class="col-sm-3">
									<select id="xb_select" name="xb" class="form-control">
										<option value="">- 选择性别 -</option>
										<option value="1">男性</option>
										<option value="2">女性</option>
										<option value="0">未知的性别</option>
										<option value="9">未说明性别</option>
									</select>
								</div>
								<span id="error_xb" style="color: red" class="col-sm-2">*</span>
								<label for="csrq" class="col-sm-1 control-label">出生日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="csrq" name="csrq" type="text"
											placeholder="出生日期" readonly="readonly" value="${p2pGrjbxxDto.csrq}"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_csrq" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="hyzk" class="col-sm-1 control-label">婚姻状况</label>
								<div class="col-sm-3">
									<select id="hyzk_select" name="hyzk" class="form-control">
										<option value="">- 婚姻状况 -</option>
										<option value="10">未婚</option>
										<option value="20">已婚</option>
										<option value="21">初婚</option>
										<option value="22">再婚</option>
										<option value="23">复婚</option>
										<option value="30">丧偶</option>
										<option value="40">离婚</option>
										<option value="90">未说明的婚姻状况</option>
									</select>
								</div>
								<span id="error_hyzk" style="color: red" class="col-sm-2">*</span>
								<label for="zgxl" class="col-sm-1 control-label">最高学历</label>
								<div class="col-sm-3">
									<select id="zgxl_select" name="zgxl" class="form-control">
										<option value="">- 最高学历 -</option>
										<option value="10">研究生</option>
										<option value="20">大学本科</option>
										<option value="30">大学专科和专科学校</option>
										<option value="40">中等专业学校或中等技术学校</option>
										<option value="50">技术学校</option>
										<option value="60">高中</option>
										<option value="70">初中</option>
										<option value="80">小学</option>
										<option value="90">文盲或半文盲</option>
										<option value="99">未知</option>
									</select>
								</div>
								<span id="error_zgxl" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="zgxw" class="col-sm-1 control-label">最高学位</label>
								<div class="col-sm-3">
									<select id="zgxw_select" name="zgxw" class="form-control">
										<option value="">- 最高学位 -</option>
										<option value="0">其他</option>
										<option value="1">名誉博士</option>
										<option value="2">博士</option>
										<option value="3">硕士</option>
										<option value="4">学士</option>
										<option value="9">未知</option>
									</select>
								</div>
								<span id="error_zgxw" style="color: red" class="col-sm-2">*</span>
								<label for="zzdh" class="col-sm-1 control-label">住宅电话</label>
								<div class="col-sm-3">
									<input type="text" id="zzdh" name="zzdh" class="form-control"
										placeholder="输入住宅电话" value="${p2pGrjbxxDto.zzdh}">
								</div>
								<span id="error_zzdh" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="sjhm" class="col-sm-1 control-label">手机号码</label>
								<div class="col-sm-3">
									<input type="text" id="sjhm" name="sjhm" class="form-control"
										placeholder="输入手机号码" value="${p2pGrjbxxDto.sjhm}">
								</div>
								<span id="error_sjhm" style="color: red" class="col-sm-2"></span>
								<label for="dwdh" class="col-sm-1 control-label">单位电话</label>
								<div class="col-sm-3">
									<input type="text" id="dwdh" name="dwdh" class="form-control"
										placeholder="输入单位电话" value="${p2pGrjbxxDto.dwdh}">
								</div>
								<span id="error_dwdh" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="dzyx" class="col-sm-1 control-label">电子邮箱</label>
								<div class="col-sm-3">
									<input type="text" id="dzyx" name="dzyx" class="form-control"
										placeholder="输入电子邮箱" value="${p2pGrjbxxDto.dzyx}">
								</div>
								<span id="error_dzyx" style="color: red" class="col-sm-2"></span>
								<label for="txdz" class="col-sm-1 control-label">通讯地址</label>
								<div class="col-sm-3">
									<input type="text" id="txdz" name="txdz" class="form-control"
										placeholder="输入通讯地址" value="${p2pGrjbxxDto.txdz}">
								</div>
								<span id="error_txdz" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="txdzyzbm" class="col-sm-1 control-label">通讯地址邮政编码</label>
								<div class="col-sm-3">
									<input type="text" id="txdzyzbm" name="txdzyzbm" class="form-control"
										placeholder="输入通讯地址邮政编码" value="${p2pGrjbxxDto.txdzyzbm}">
								</div>
								<span id="error_txdzyzbm" style="color: red" class="col-sm-2">*</span>
								<label for="hjdz" class="col-sm-1 control-label">户籍地址</label>
								<div class="col-sm-3">
									<input type="text" id="hjdz" name="hjdz" class="form-control"
										placeholder="输入户籍地址" value="${p2pGrjbxxDto.hjdz}">
								</div>
								<span id="error_hjdz" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="poxm" class="col-sm-1 control-label">配偶姓名</label>
								<div class="col-sm-3">
									<input type="text" id="poxm" name="poxm" class="form-control"
										placeholder="输入配偶姓名" value="${p2pGrjbxxDto.poxm}">
								</div>
								<span id="error_poxm" style="color: red" class="col-sm-2"></span>
								<label for="pozjlx" class="col-sm-1 control-label">配偶证件类型</label>
								<div class="col-sm-3">
									<select id="pozjlx_select" name="pozjlx" class="form-control">
										<option value="">- 配偶证件类型 -</option>
										<option value="0">身份证</option>
									</select>
								</div>
								<span id="error_pozjlx" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="pozjhm" class="col-sm-1 control-label">配偶证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="pozjhm" name="pozjhm" class="form-control"
										placeholder="输入配偶证件号码" value="${p2pGrjbxxDto.pozjhm}">
								</div>
								<span id="error_pozjhm" style="color: red" class="col-sm-2"></span>
								<label for="pogzdw" class="col-sm-1 control-label">配偶工作单位</label>
								<div class="col-sm-3">
									<input type="text" id="pogzdw" name="pogzdw" class="form-control"
										placeholder="输入配偶工作单位" value="${p2pGrjbxxDto.pogzdw}">
								</div>
								<span id="error_pogzdw" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="polxdh" class="col-sm-1 control-label">配偶联系电话</label>
								<div class="col-sm-3">
									<input type="text" id="polxdh" name="polxdh" class="form-control"
										placeholder="输入配偶联系电话" value="${p2pGrjbxxDto.polxdh}">
								</div>
								<span id="error_polxdh" style="color: red" class="col-sm-2"></span>
								<label for="lxrxm1" class="col-sm-1 control-label">第一联系人姓名</label>
								<div class="col-sm-3">
									<input type="text" id="lxrxm1" name="lxrxm1" class="form-control"
										placeholder="输入第一联系人姓名" value="${p2pGrjbxxDto.lxrxm1}">
								</div>
								<span id="error_lxrxm1" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="lxrgx1" class="col-sm-1 control-label">第一联系人关系</label>
								<div class="col-sm-3">
									<select id="lxrgx1_select" name="lxrgx1" class="form-control">
										<option value="">- 第一联系人关系 -</option>
										<option value="0">父子（女）</option>
										<option value="1">母子（女）</option>
										<option value="2">配偶</option>
										<option value="3">子女</option>
										<option value="4">其他亲属</option>
										<option value="5">同事</option>
										<option value="6">朋友</option>
										<option value="7">兄弟姐妹</option>
										<option value="8">其他</option>
									</select>
								</div>
								<span id="error_lxrgx1" style="color: red" class="col-sm-2">*</span>
								<label for="lxrdh1" class="col-sm-1 control-label">第一联系人电话</label>
								<div class="col-sm-3">
									<input type="text" id="lxrdh1" name="lxrdh1" class="form-control"
										placeholder="输入第一联系人电话" value="${p2pGrjbxxDto.lxrdh1}">
								</div>
								<span id="error_lxrdh1" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="lxrxm2" class="col-sm-1 control-label" >第二联系人姓名</label>
								<div class="col-sm-3">
									<input type="text" id="lxrxm2" name="lxrxm2" class="form-control"
										placeholder="输入第二联系人姓名" value="${p2pGrjbxxDto.lxrxm2}">
								</div>
								<span id="error_lxrxm2" style="color: red" class="col-sm-2"></span>
								<label for="lxrgx2" class="col-sm-1 control-label">第二联系人关系</label>
								<div class="col-sm-3">
									<select id="lxrgx2_select" name="lxrgx2" class="form-control">
										<option value="">- 第二联系人关系 -</option>
										<option value="0">父子（女）</option>
										<option value="1">母子（女）</option>
										<option value="2">配偶</option>
										<option value="3">子女</option>
										<option value="4">其他亲属</option>
										<option value="5">同事</option>
										<option value="6">朋友</option>
										<option value="7">兄弟姐妹</option>
										<option value="8">其他</option>
									</select>
								</div>
								<span id="error_lxrgx2" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="lxrdh2" class="col-sm-1 control-label">第二联系人电话</label>
								<div class="col-sm-3">
									<input type="text" id="lxrdh2" name="lxrdh2" class="form-control"
										placeholder="输入第二联系人电话" value="${p2pGrjbxxDto.lxrdh2}">
								</div>
								<span id="error_lxrdh2" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 16px; color: gray;">职业信息</legend>
							<div class="form-group">
								<label for="zy" class="col-sm-1 control-label">职业</label>
								<div class="col-sm-3">
									<select id="zy_select" name="zy" class="form-control">
										<option value="">- 选择职业 -</option>
										<option value="0">国家机关、党群组织、企业、事业单位负责人</option>
										<option value="1">专业技术人员</option>
										<option value="3">办事人员和有关人员</option>
										<option value="4">商业、服务业人员</option>
										<option value="5">农、林、牧、渔、水利业生产人员</option>
										<option value="6">生产、运输设备操作人员及有关人员</option>
										<option value="X">军人</option>
										<option value="Y">不便分类的其他从业人员</option>
										<option value="Z">未知</option>
									</select>
								</div>
								<span id="error_zy" style="color: red" class="col-sm-2">*</span>
								<label for="dwmc" class="col-sm-1 control-label">单位名称</label>
								<div class="col-sm-3">
									<input type="text" id="dwmc" name="dwmc" class="form-control"
										placeholder="输入单位名称" value="${p2pGrjbxxDto.dwmc}">
								</div>
								<span id="error_dwmc" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="dwsshy" class="col-sm-1 control-label">单位所属行业</label>
								<div class="col-sm-3">
									<select id="dwsshy_select" name="dwsshy" class="form-control">
										<option value="">- 单位所属行业 -</option>
										<option value="A">农、林、牧、渔业</option>
										<option value="B">采掘业</option>
										<option value="C">制造业</option>
										<option value="D">电力、燃气及水的生产和供应业</option>
										<option value="E">建筑业</option>
										<option value="F">交通运输、仓储和邮政业</option>
										<option value="G">信息传输、计算机服务和软件业</option>
										<option value="H">批发和零售业</option>
										<option value="I">住宿和餐饮业</option>
										<option value="J">金融业</option>
										<option value="K">房地产业</option>
										<option value="L">租赁和商务服务业</option>
										<option value="M">科学研究、技术服务业和地质勘察业</option>
										<option value="N">水利、环境和公共设施管理业</option>
										<option value="O">居民服务和其他服务业</option>
										<option value="P">教育</option>
										<option value="Q">卫生、社会保障和社会福利业</option>
										<option value="R">文化、体育和娱乐业</option>
										<option value="S">公共管理和社会组织</option>
										<option value="T">国际组织</option>
										<option value="Z">未知</option>
									</select>
								</div>
								<span id="error_dwsshy" style="color: red" class="col-sm-2">*</span>
								<label for="dwdz" class="col-sm-1 control-label">单位地址</label>
								<div class="col-sm-3">
									<input type="text" id="dwdz" name="dwdz" class="form-control"
										placeholder="输入单位地址" value="${p2pGrjbxxDto.dwdz}">
								</div>
								<span id="error_dwdz" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="dwdzyzbm" class="col-sm-1 control-label">单位地址邮政编码</label>
								<div class="col-sm-3">
									<input type="text" id="dwdzyzbm" name="dwdzyzbm" class="form-control"
										placeholder="输入单位地址邮政编码" value="${p2pGrjbxxDto.dwdzyzbm}">
								</div>
								<span id="error_dwdzyzbm" style="color: red" class="col-sm-2"></span>
								<label for="bdwgzqsnf" class="col-sm-1 control-label">单位工作起始年份</label>
								<div class="col-sm-3">
									<div class="input-group date work_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;"
										data-date-format="yyyy">
										<input class="form-control" id="bdwgzqsnf" name="bdwgzqsnf" type="text"
											placeholder="单位工作起始年份" readonly="readonly" value="${p2pGrjbxxDto.bdwgzqsnf}"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_bdwgzqsnf" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="zw" class="col-sm-1 control-label">职务</label>
								<div class="col-sm-3">
									<select id="zw_select" name="zw" class="form-control">
										<option value="">- 选择职务 -</option>
										<option value="1">高级领导</option>
										<option value="2">中级领导</option>
										<option value="3">一般员工</option>
										<option value="4">其他</option>
										<option value="9">未知</option>
									</select>
								</div>
								<span id="error_zw" style="color: red" class="col-sm-2"></span>
								<label for="zc" class="col-sm-1 control-label">职称</label>
								<div class="col-sm-3">
									<select id="zc_select" name="zc" class="form-control">
										<option value="">- 选择职称 -</option>
										<option value="0">无</option>
										<option value="1">高级</option>
										<option value="2">中级</option>
										<option value="3">初级</option>
										<option value="9">未知</option>
									</select>
								</div>
								<span id="error_zc" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="nsr" class="col-sm-1 control-label">年收入</label>
								<div class="col-sm-3">
									<input type="text" id="nsr" name="nsr" class="form-control"
										placeholder="输入年收入" value="${p2pGrjbxxDto.nsr}">
								</div>
								<span id="error_nsr" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 16px; color: gray;">居住地址</legend>
							<div class="form-group">
								<label for="jzdz" class="col-sm-1 control-label">居住地址</label>
								<div class="col-sm-3">
									<input type="text" id="jzdz" name="jzdz" class="form-control"
										placeholder="输入居住地址" value="${p2pGrjbxxDto.jzdz}">
								</div>
								<span id="error_jzdz" style="color: red" class="col-sm-2">*</span>
								<label for="jzdzyzbm" class="col-sm-1 control-label">居住地址邮政编码</label>
								<div class="col-sm-3">
									<input type="text" id="jzdzyzbm" name="jzdzyzbm"  class="form-control"
										placeholder="输入居住地址邮政编码" value="${p2pGrjbxxDto.jzdzyzbm}">
								</div>
								<span id="error_jzdzyzbm" style="color: red" class="col-sm-2">*</span>
							</div>
							<div class="form-group">
								<label for="jzzk" class="col-sm-1 control-label">居住状况</label>
								<div class="col-sm-3">
									<select id="jzzk_select" name="jzzk" class="form-control">
										<option value="">- 选择居住状况 -</option>
										<option value="1">自置</option>
										<option value="2">按揭</option>
										<option value="3">亲属楼宇</option>
										<option value="4">集体宿舍</option>
										<option value="5">租房</option>
										<option value="6">共有住宅</option>
										<option value="7">其他</option>
										<option value="9">未知</option>
									</select>
								</div>
								<span id="error_jzzk" style="color: red" class="col-sm-2">*</span>
							</div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">
							      	<c:if test="${p2pGrjbxxDto.status == 1}">
									    <input id="status_yes" name="status" type="radio" checked="checked" value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" value="2"/> 停止
								    </c:if>
								    <c:if test="${p2pGrjbxxDto.status == 2}">
									    <input id="status_yes" name="status" type="radio"value="1"/> 正常 
								      	&nbsp;&nbsp;&nbsp;
								      	<input id="status_not" name="status" type="radio" checked="checked" value="2"/> 停止
								    </c:if>
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						<input type="hidden" id="oldZjhm" name="oldZjhm" value="${p2pGrjbxxDto.zjhm}">
						<input type="hidden" id="orgCode" name="orgCode" value="${p2pGrjbxxDto.orgCode}">
						<input type="hidden" name="id" value="${p2pGrjbxxDto.id}">
						<div class="form-group">
							<div class="col-sm-offset-5">
								<input type="submit" class="btn btn-primary" value="保存"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn btn-success" value="返回"
									onclick="goback()"/>
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

$().ready(function() {
	loadSelect();
	$("#grjbxxInfoForm").validate({
	    onkeyup: false,
	    rules: {
			xm:{
				required:true,
				rangelength:[2,15],
				isChinese:true
			},
			zjlx:{
				required:true
			},
			zjhm:{
				required:true,
				isIdCardNo:true,
				remote:{
					url:"${pageContext.request.contextPath}/p2p/grjbxx/checkZjhm",
					type: "post",
				    dataType: "json",
				    data: {
				    	zjlx: function() {
				            return $("#zjlx_select").val();
				        },
				    	zjhm: function() {
				            return $("#zjhm").val();
				        },
				        orgCode: function() {
				            return $("#orgCode").val();
				        },
				        oldZjhm:function(){
				        	return $("#oldZjhm").val();
				        }
				    }
				}
			},
			xb:{
				required:true
			},
			csrq:{
				required:true
			},
			hyzk:{
				required:true
			},
			zgxl:{
				required:true
			},
			zgxw:{
				required:true
			},
			zzdh:{
				isPhone:true
			},
			sjhm:{
				isMobile:true
			},
			dwdh:{
				isPhone:true
			},
			dzyx:{
				email:true,
				maxlength:30
			},
			txdz:{
				required:true,
				realLength:60,
				isContainsSpecialChar:true
			},
			txdzyzbm:{
				required:true,
				isZipCode:true
			},
			hjdz:{
				realLength:60,
				isContainsSpecialChar:true
			},
			poxm:{
				rangelength:[2,15],
				isChinese:true
			},
			pozjhm:{
				isIdCardNo:true
			},
			pogzdw:{
				realLength:60,
				isContainsSpecialChar:true
			},
			polxdh:{
				isMobile:true
			},
			lxrxm1:{
				required:true,
				rangelength:[2,15],
				isChinese:true
			},
			lxrgx1:{
				required:true
			},
			lxrdh1:{
				required:true,
				isMobile:true
			},
			lxrxm2:{
				rangelength:[2,15],
				isChinese:true
			},
			lxrdh2:{
				isMobile:true
			},
			zy:{
				required:true
			},
			dwmc:{
				required:true,
				realLength:60,
				isContainsSpecialChar:true
			},
			dwsshy:{
				required:true
			},
			dwdz:{
				realLength:60,
				isContainsSpecialChar:true
			},
			dwdzyzbm:{
				isZipCode:true
			},
			zw:{
				required:true
			},
			zc:{
				required:true
			},
			nsr:{
				digits:true,
				isIntGtZero:true,
				maxlength:10
			},
			jzdz:{
				required:true,
				realLength:60,
				isContainsSpecialChar:true
			},
			jzdzyzbm:{
				required:true,
				isZipCode:true
			},
			jzzk:{
				required:true
			}			
		},
		messages:{
			xm:{
				required:"请输入姓名",
				rangelength:"姓名长度应为2~15个中文",
				isChinese:"姓名必须为中文"
			},
			zjlx:{
				required:"请选择证件类型"
			},
			zjhm:{
				required:"请输入证件号码",
				isIdCardNo:"请输入正确的身份证号码",
				remote:"该人员已存在"
			},
			xb:{
				required:"请选择性别"
			},
			csrq:{
				required:"请选择出生日期"
			},
			hyzk:{
				required:"请选择婚姻状况"
			},
			zgxl:{
				required:"请选择最够学历"
			},
			zgxw:{
				required:"请选择最够学位"
			},
			zzdh:{
				isPhone:"请输入正确的座机号码"
			},
			sjhm:{
				isMobile:"请输入正确的手机号码"
			},
			dwdh:{
				isPhone:"请输入正确的座机号码"
			},
			dzyx:{
				email:"电子邮箱格式错误",
				maxlength:"电子邮箱最大长度为30"
			},
			txdz:{
				required:"请输入通讯地址",
				realLength:"通讯地址最大长度为60",
				isContainsSpecialChar:"通讯地址不能包含特殊字符"
			},
			txdzyzbm:{
				required:"请输入通讯地址邮政编码",
				isZipCode:"请输入正确的邮政编码"
			},
			hjdz:{
				realLength:"户籍地址最大长度为60",
				isContainsSpecialChar:"户籍地址不能包含特殊字符"
			},
			poxm:{
				rangelength:"配偶姓名长度应为2~15个中文",
				isChinese:"配偶姓名必须为中文"
			},
			pozjhm:{
				isIdCardNo:"请输入正确的身份证号码"
			},
			pogzdw:{
				realLength:"配偶工作单位最大长度为60",
				isContainsSpecialChar:"配偶工作单位不能包含特殊字符"
			},
			polxdh:{
				isMobile:"请输入正确的手机号码"
			},
			lxrxm1:{
				required:"请输入第一联系人姓名",
				rangelength:"第一联系人姓名长度应为2~15个中文",
				isChinese:"第一联系人姓名必须为中文"
			},
			lxrgx1:{
				required:"请选择第一联系人关系"
			},
			lxrdh1:{
				required:"请输入第一联系人电话",
				isMobile:"请输入正确的手机号码"
			},
			lxrxm2:{
				rangelength:"第二联系人姓名长度应为2~15个中文",
				isChinese:"第二联系人姓名必须为中文"
			},
			lxrdh2:{
				isMobile:"请输入正确的手机号码"
			},
			zy:{
				required:"请选择职业"
			},
			dwmc:{
				required:"请输入单位名称",
				realLength:"单位名称最大长度为60",
				isContainsSpecialChar:"单位名称不能包含特殊字符"
			},
			dwsshy:{
				required:"请选择单位所属行业"
			},
			dwdz:{
				realLength:"单位地址最大长度为60",
				isContainsSpecialChar:"单位地址不能包含特殊字符"
			},
			dwdzyzbm:{
				isZipCode:"请输入正确的邮政编码"
			},
			zw:{
				required:"请选择职务"
			},
			zc:{
				required:"请选择职称"
			},
			nsr:{
				digits:"年收入为大于0的整数",
				isIntGtZero:"年收入为大于0的整数",
				maxlength:"年收入最大长度为10"
			},
			jzdz:{
				required:"请输入居住地址",
				realLength:"居住地址最大长度为60",
				isContainsSpecialChar:"居住地址不能包含特殊字符"
			},
			jzdzyzbm:{
				required:"请输入居住地址邮政编码",
				isZipCode:"请输入正确的邮政编码"
			},
			jzzk:{
				required:"请选择居住状况"
			}
		},
		errorPlacement: function(error, element) {
			error.appendTo($("span[id='error_" + element.attr( "name" ) + "']"));
		},
		submitHandler:function(){
			var grjbxxInfoForm = $('#grjbxxInfoForm').serializeObject();
			delete grjbxxInfoForm.oldZjhm;
			jQuery.ajax({
				type: "post",
			    url: "${pageContext.request.contextPath}/p2p/grjbxx/doEdit",
			    contentType: "application/json",
			    data: JSON.stringify(grjbxxInfoForm),
			    success: function () {
			    	alert("修改成功！");
			    	window.location.href = "${pageContext.request.contextPath}/p2p/grjbxx/showList";
			    },
			    error: function () {
			        alert("edit grjbxx error");
			    }	
			});
		}
	});

});

function goback(){
	window.location.href = "${pageContext.request.contextPath}/p2p/grjbxx/showList";
}

//临时处理，后面不用这种方式
function loadSelect(){
	var zjlx = "${p2pGrjbxxDto.zjlx}";
	var xb = "${p2pGrjbxxDto.xb}";
	var hyzk = "${p2pGrjbxxDto.hyzk}";
	var zgxl = "${p2pGrjbxxDto.zgxl}";
	var zgxw = "${p2pGrjbxxDto.zgxw}";
	var pozjlx = "${p2pGrjbxxDto.pozjlx}";
	var lxrgx1 = "${p2pGrjbxxDto.lxrgx1}";
	var lxrgx2 = "${p2pGrjbxxDto.lxrgx2}";
	var zy = "${p2pGrjbxxDto.zy}";
	var dwsshy = "${p2pGrjbxxDto.dwsshy}";
	var zw = "${p2pGrjbxxDto.zw}";
	var zc = "${p2pGrjbxxDto.zc}";
	var jzzk = "${p2pGrjbxxDto.jzzk}";
	
	$("#zjlx_select").find("option[value="+zjlx+"]").attr("selected",true);
	$("#xb_select").find("option[value="+xb+"]").attr("selected",true);
	$("#hyzk_select").find("option[value="+hyzk+"]").attr("selected",true);
	$("#zgxl_select").find("option[value="+zgxl+"]").attr("selected",true);
	$("#zgxw_select").find("option[value="+zgxw+"]").attr("selected",true);
	$("#pozjlx_select").find("option[value="+pozjlx+"]").attr("selected",true);
	$("#lxrgx1_select").find("option[value="+lxrgx1+"]").attr("selected",true);
	$("#lxrgx2_select").find("option[value="+lxrgx2+"]").attr("selected",true);
	$("#zy_select").find("option[value="+zy+"]").attr("selected",true);
	$("#dwsshy_select").find("option[value="+dwsshy+"]").attr("selected",true);
	$("#zw_select").find("option[value="+zw+"]").attr("selected",true);
	$("#zc_select").find("option[value="+zc+"]").attr("selected",true);
	$("#jzzk_select").find("option[value="+jzzk+"]").attr("selected",true);
}

function removeZjhm(){
	$("#zjhm").removeData("previousValue");
}

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
    $('.work_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 4,
		minView: 4,
		forceParse: 0
    });
</script>
<%@ include file="./../../common/footer.jsp"%>
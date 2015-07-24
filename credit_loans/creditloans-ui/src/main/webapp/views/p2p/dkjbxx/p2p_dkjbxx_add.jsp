<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./../../common/header.jsp"%>

<div class="main-content">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
			<li><a href="#">P2P贷款业务</a></li>
			<li class="active">贷款业务信息</li>
		</ul>
		<!-- .breadcrumb -->
	</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				贷款业务信息<small> <i class="icon-double-angle-right"></i>
					添加贷款基本信息
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<div class="col-md-12">
					<form id="dkjbxxInfoForm" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="dklx" class="col-sm-1 control-label">贷款类型</label>
								<div class="col-sm-3">
									<select id="dklx_select" name="dklx" class="form-control">
										<option value="">- 选择贷款类型 -</option>
										<option value="11">个人住房贷款</option>
										<option value="12">个人商用房（含商住两用）贷款</option>
										<option value="13">个人住房公积金贷款</option>
										<option value="21">个人汽车消费贷款</option>
										<option value="31">个人助学贷款</option>
										<option value="41">个人经营性贷款</option>
										<option value="51">农户贷款</option>
										<option value="91">个人消费贷款</option>
										<option value="99">其他</option>
									</select>
								</div>
								<span id="error_dklx" style="color: red" class="col-sm-2"></span>
								<label for="dkhthm" class="col-sm-1 control-label">贷款合同号码</label>
								<div class="col-sm-3">
									<input type="text" id="dkhthm" name="dkhthm"
										class="form-control" placeholder="输入贷款合同号码">
								</div>
								<span id="error_dkhthm" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="ywh" class="col-sm-1 control-label">业务号</label>
								<div class="col-sm-3">
									<input type="text" id="ywh" name="ywh" class="form-control"
										placeholder="输入业务号">
								</div>
								<span id="error_ywh" style="color: red" class="col-sm-2"></span>
								<label for="fsdd" class="col-sm-1 control-label">发生地点</label>
								<div class="col-sm-3">
									<select id="fsdd_select" name="fsdd" class="form-control">
										<option value="">- 选择发生地点 -</option>
										<option value="110000">北京市</option>
										<option value="110101">东城区</option>
										<option value="110102">西城区</option>
										<option value="110105">朝阳区</option>
										<option value="110106">丰台区</option>
										<option value="310000">上海市</option>
										<option value="310101">黄浦区</option>
										<option value="310104">徐汇区</option>
										<option value="310115">浦东新区</option>
									</select>
								</div>
								<span id="error_fsdd" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="khrq" class="col-sm-1 control-label">开户日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="khrq" name="khrq" type="text"
											placeholder="开户日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_khrq" style="color: red" class="col-sm-2"></span>
								<label for="dqrq" class="col-sm-1 control-label">到期日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="dqrq" name="dqrq" type="text"
											placeholder="到期日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_dqrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="jcbz" class="col-sm-1 control-label">币种</label>
								<div class="col-sm-3">
									<select id="jcbz_select" name="jcbz" class="form-control">
										<option value="">- 选择币种 -</option>
										<option value="CNY">人民币</option>
										<option value="HKD">港元</option>
										<option value="USD">美元</option>
										<option value="EUR">欧元</option>
										<option value="JPY">日元</option>
										<option value="MOP">澳门元</option>
										<option value="CHF">瑞士法郎</option>
										<option value="GBP">英镑</option>
										<option value="RUR">俄罗斯卢布</option>
										<option value="CAD">加拿大元</option>
									</select>
								</div>
								<span id="error_jcbz" style="color: red" class="col-sm-2"></span>
								<label for="sxed" class="col-sm-1 control-label">授信额度</label>
								<div class="col-sm-3">
									<input type="text" id="sxed" name="sxed" class="form-control"
										placeholder="输入授信额度">
								</div>
								<span id="error_sxed" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="gxsxed" class="col-sm-1 control-label">共享授信额度</label>
								<div class="col-sm-3">
									<input type="text" id="gxsxed" name="gxsxed" class="form-control"
										placeholder="输入共享授信额度">
								</div>
								<span id="error_gxsxed" style="color: red" class="col-sm-2"></span>
								<label for="zdfze" class="col-sm-1 control-label">最大负债额</label>
								<div class="col-sm-3">
									<input type="text" id="zdfze" name="zdfze" class="form-control"
										placeholder="输入最大负债额">
								</div>
								<span id="error_zdfze" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="dbfs" class="col-sm-1 control-label">担保方式</label>
								<div class="col-sm-3">
									<select id="dbfs_select" name="dbfs" class="form-control">
										<option value="">- 选择担保方式 -</option>
										<option value="1">质押（含保证金）</option>
										<option value="2">抵押</option>
										<option value="3">自然人保证</option>
										<option value="4">信用/免担保</option>
										<option value="5">组合（含自然人保证）</option>
										<option value="6">组合（不含自然人保证）</option>
										<option value="7">农户联保</option>
										<option value="9">其他</option>
									</select>
								</div>
								<span id="error_dbfs" style="color: red" class="col-sm-2"></span>
								<label for="hkpl" class="col-sm-1 control-label">还款频率</label>
								<div class="col-sm-3">
									<select id="hkpl_select" name="hkpl" class="form-control" onchange="chooseHkpl(this.value);">
										<option value="">- 选择还款频率 -</option>
										<option value="01">日</option>
										<option value="02">周</option>
										<option value="03">月</option>
										<option value="04">季</option>
										<option value="05">半年</option>
										<option value="06">年</option>
										<option value="07">一次性</option>
										<option value="08">不定期</option>
										<option value="99">其他</option>
									</select>
								</div>
								<span id="error_hkpl" style="color: red" class="col-sm-2"></span>
							</div>							
							<div class="form-group">
								<label for="hkqs" class="col-sm-1 control-label">还款期数</label>
								<div class="col-sm-3">
									<input type="text" id="hkqs" name="hkqs" class="form-control"
										placeholder="输入还款期数">
								</div>
								<span id="error_hkqs" style="color: red" class="col-sm-2"></span>
								<label for="syhkqs" class="col-sm-1 control-label">剩余还款期数</label>
								<div class="col-sm-3">
									<input type="text" id="syhkqs" name="syhkqs" class="form-control"
										placeholder="输入剩余还款月数">
								</div>
								<span id="error_syhkqs" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="xdhkqs" class="col-sm-1 control-label">协定还款期数</label>
								<div class="col-sm-3">
									<input type="text" id="xdhkqs" name="xdhkqs" class="form-control"
										placeholder="输入协定还款期数">
								</div>
								<span id="error_xdhkqs" style="color: red" class="col-sm-2"></span>
								<label for="xdqhke" class="col-sm-1 control-label">协定期还款额</label>
								<div class="col-sm-3">
									<input type="text" id="xdqhke" name="xdqhke" class="form-control"
										placeholder="输入协定期还款额">
								</div>
								<span id="error_xdqhke" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="jsyhkrq" class="col-sm-1 control-label">结算/应还款日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="jsyhkrq" name="jsyhkrq" type="text"
											placeholder="结算/应还款日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_jsyhkrq" style="color: red" class="col-sm-2"></span>
								<label for="zjycsjhkrq" class="col-sm-1 control-label">最近一次还款日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="zjycsjhkrq" name="zjycsjhkrq" type="text"
											placeholder="最近一次还款日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_zjycsjhkrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="byyhkje" class="col-sm-1 control-label">本月应还款金额</label>
								<div class="col-sm-3">
									<input type="text" id="byyhkje" name="byyhkje" class="form-control"
										placeholder="输入本月应还款金额">
								</div>
								<span id="error_byyhkje" style="color: red" class="col-sm-2"></span>
								<label for="bysjhkje" class="col-sm-1 control-label">本月实际还款金额</label>
								<div class="col-sm-3">
									<input type="text" id="bysjhkje" name="bysjhkje" class="form-control"
										placeholder="输入本月实际还款金额">
								</div>
								<span id="error_bysjhkje" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="ye" class="col-sm-1 control-label">余额</label>
								<div class="col-sm-3">
									<input type="text" id="ye" name="ye" class="form-control"
										placeholder="输入余额">
								</div>
								<span id="error_ye" style="color: red" class="col-sm-2"></span>
								<label for="dqyqqs" class="col-sm-1 control-label">当前逾期期数</label>
								<div class="col-sm-3">
									<input type="text" id="dqyqqs" name="dqyqqs" class="form-control"
										placeholder="输入当前逾期期数">
								</div>
								<span id="error_dqyqqs" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="dqyqze" class="col-sm-1 control-label">当前逾期总额</label>
								<div class="col-sm-3">
									<input type="text" id="dqyqze" name="dqyqze" class="form-control"
										placeholder="输入当前逾期总额">
								</div>
								<span id="error_dqyqze" style="color: red" class="col-sm-2"></span>
								<label for="yqwghdkbj1" class="col-sm-1 control-label">逾期31-60天未归还贷款本金</label>
								<div class="col-sm-3">
									<input type="text" id="yqwghdkbj1" name="yqwghdkbj1" class="form-control"
										placeholder="输入逾期31-60天未归还贷款本金">
								</div>
								<span id="error_yqwghdkbj1" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="yqwghdkbj2" class="col-sm-1 control-label">逾期61-90天未归还贷款本金</label>
								<div class="col-sm-3">
									<input type="text" id="yqwghdkbj2" name="yqwghdkbj2" class="form-control"
										placeholder="输入逾期61-90天未归还贷款本金">
								</div>
								<span id="error_yqwghdkbj2" style="color: red" class="col-sm-2"></span>
								<label for="yqwghdkbj3" class="col-sm-1 control-label">逾期91-180天未归还贷款本金</label>
								<div class="col-sm-3">
									<input type="text" id="yqwghdkbj3" name="yqwghdkbj3" class="form-control"
										placeholder="输入逾期91-180天未归还贷款本金">
								</div>
								<span id="error_yqwghdkbj3" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="yqwghdkbj4" class="col-sm-1 control-label">逾期180天以上未归还贷款本金</label>
								<div class="col-sm-3">
									<input type="text" id="yqwghdkbj4" name="yqwghdkbj4" class="form-control"
										placeholder="输入逾期180天以上未归还贷款本金">
								</div>
								<span id="error_yqwghdkbj4" style="color: red" class="col-sm-2"></span>
								<label for="ljyqqs" class="col-sm-1 control-label">累计逾期期数</label>
								<div class="col-sm-3">
									<input type="text" id="ljyqqs" name="ljyqqs" class="form-control"
										placeholder="输入累计逾期期数">
								</div>
								<span id="error_ljyqqs" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="zgyqqs" class="col-sm-1 control-label">最高逾期期数</label>
								<div class="col-sm-3">
									<input type="text" id="zgyqqs" name="zgyqqs" class="form-control"
										placeholder="输入最高逾期期数">
								</div>
								<span id="error_zgyqqs" style="color: red" class="col-sm-2"></span>
								<label for="wjflzt" class="col-sm-1 control-label">五级分类状态</label>
								<div class="col-sm-3">
									<select id="wjflzt_select" name="wjflzt" class="form-control">
										<option value="">- 选择五级分类 -</option>
										<option value="1">正常</option>
										<option value="2">关注</option>
										<option value="3">次级</option>
										<option value="4">可疑</option>
										<option value="5">损失</option>
										<option value="9">未知</option>
									</select>
								</div>
								<span id="error_wjflzt" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="zhzt" class="col-sm-1 control-label">账户状态</label>
								<div class="col-sm-3">
									<select id="zhzt_select" name="zhzt" class="form-control">
										<option value="">- 选择账户状态 -</option>
										<option value="1">正常</option>
										<option value="2">逾期</option>
										<option value="3">结清</option>
										<option value="4">呆账（待核销）</option>
										<option value="5">转出</option>
									</select>
								</div>
								<span id="error_zhzt" style="color: red" class="col-sm-2"></span>
								<label for="byhkzt" class="col-sm-1 control-label">本月还款状态</label>
								<div class="col-sm-3">
									<select id="byhkzt_select" name="byhkzt" class="form-control">
										<option value="">- 选择账户状态 -</option>
										<option value="*">当月不需还款</option>
										<option value="N">正常</option>
										<option value="1">逾期1-30天</option>
										<option value="2">逾期31-60天</option>
										<option value="3">逾期61-90天</option>
										<option value="4">逾期91-120天</option>
										<option value="5">逾期121-150天</option>
										<option value="6">逾期151-180天</option>
										<option value="7">逾期180天以上</option>
										<option value="D">担保人代还</option>
										<option value="Z">以资抵债</option>
										<option value="C">结清</option>
										<option value="G">结束</option>
									</select>
								</div>
								<span id="error_byhkzt" style="color: red" class="col-sm-2"></span>								
							</div>
							<div class="form-group">
								<label for="zhyyzxxts" class="col-sm-1 control-label">账户拥有者信息提示</label>
								<div class="col-sm-3">
									<select id="zhyyzxxts_select" name="zhyyzxxts" class="form-control">
										<option value="">- 账户拥有者信息提示 -</option>
										<option value="1">已开立账户</option>
										<option value="2">新账户开立</option>
									</select>
								</div>
								<span id="error_zhyyzxxts" style="color: red" class="col-sm-2"></span>
								<label for="xm" class="col-sm-1 control-label">姓名</label>
								<div class="col-sm-3">
									<input type="text" id="xm" name="xm" class="form-control"
										placeholder="输入姓名">
								</div>
								<span id="error_xm" style="color: red" class="col-sm-2"></span>								
							</div>
							<div class="form-group">								
								<label for="zjlx" class="col-sm-1 control-label">证件类型</label>
								<div class="col-sm-3">
									<select id="zjlx_select" name="zjlx" class="form-control">
										<option value="">- 选择证件类型 -</option>
										<option value="0">身份证</option>
									</select>
								</div>
								<span id="error_zjlx" style="color: red" class="col-sm-2"></span>
								<label for="zjhm" class="col-sm-1 control-label">证件号码</label>
								<div class="col-sm-3">
									<input type="text" id="zjhm" name="zjhm" class="form-control"
										placeholder="输入证件号码">
								</div>
								<span id="error_zjhm" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 16px; color: gray;">合同信息</legend>
							<div class="form-group">
								<label for="dkhtsxrq" class="col-sm-1 control-label">贷款合同生效日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="dkhtsxrq" name="dkhtsxrq" type="text"
											placeholder="贷款合同生效日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_dkhtsxrq" style="color: red" class="col-sm-2"></span>
								<label for="dkhtzzrq" class="col-sm-1 control-label">贷款合同终止日期</label>
								<div class="col-sm-3">
									<div class="input-group date form_date col-md-3 form-control"
										style="padding: 0px; margin: 0px;">
										<input class="form-control" id="dkhtzzrq" name="dkhtzzrq" type="text"
											placeholder="贷款合同终止日期" readonly="readonly"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-calendar icon-calendar"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove icon-remove"></span></span>
									</div>
								</div>
								<span id="error_dkhtzzrq" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="htbz" class="col-sm-1 control-label">币种</label>
								<div class="col-sm-3">
									<select id="htbz_select" name="htbz" class="form-control">
										<option value="">- 选择币种 -</option>
										<option value="CNY">人民币</option>
										<option value="HKD">港元</option>
										<option value="USD">美元</option>
										<option value="EUR">欧元</option>
										<option value="JPY">日元</option>
										<option value="MOP">澳门元</option>
										<option value="CHF">瑞士法郎</option>
										<option value="GBP">英镑</option>
										<option value="RUR">俄罗斯卢布</option>
										<option value="CAD">加拿大元</option>
									</select>
								</div>
								<span id="error_htbz" style="color: red" class="col-sm-2"></span>
								<label for="dkhtje" class="col-sm-1 control-label">贷款合同金额</label>
								<div class="col-sm-3">
									<input type="text" id="dkhtje" name="dkhtje" class="form-control"
										placeholder="输入贷款合同金额">
								</div>
								<span id="error_dkhtje" style="color: red" class="col-sm-2"></span>
							</div>
							<div class="form-group">
								<label for="dkhtzt" class="col-sm-1 control-label">合同有效状态</label>
								<div class="col-sm-3">
									<select id="dkhtzt_select" name="dkhtzt" class="form-control">
										<option value="">- 选择合同有效状态 -</option>
										<option value="0">有效</option>
										<option value="1">无效</option>
									</select>
								</div>
								<span id="error_dkhtzt" style="color: red" class="col-sm-2"></span>
							</div>
						</fieldset>
						<fieldset>
							<legend></legend>
							<div class="form-group">
							    <label for="status" class="col-sm-5 control-label">数据状态</label>
							    <div class="col-sm-3">							    
							    	<input id="status_yes" name="status" type="radio" checked="checked" value="1"/> 正常 
							      	&nbsp;&nbsp;&nbsp;
							      	<input id="status_not" name="status" type="radio" value="2"/> 停止
							    </div>
							    <span id="error_status" style="color: red"></span>
							  </div>
						</fieldset>
						
						<input type="hidden" id="hkys" name="hkys" value="">
						<input type="hidden" id="syhkys" name="syhkys" value="">
						<input type="hidden" id="hkzt" name="hkzt" value="">
						<input type="hidden" name="orgCode" value="${orgCode}">
						
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
	$("#dkjbxxInfoForm").validate({
	    onkeyup: false,
	    ignore : '.ignore',
		rules: {
			dklx:{
				required:true
			},
			dkhthm:{
				required:true,
				maxlength:[60]
			},
			ywh:{
				required:true,
				maxlength:[40]
			},
			fsdd:{
				required:true
			},
			khrq:{
				required:true
			},
			dqrq:{
				required:true
			},
			jcbz:{
				required:true
			},
			sxed:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			gxsxed:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			zdfze:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			dbfs:{
				required:true
			},
			hkpl:{
				required:true
			},
			hkqs:{
				required:true,
				digits:true,
				isIntGtZero:true,
				maxlength:[3],
				range:[1,800]
			},
			syhkqs:{
				required:true,
				digits:true,
				isIntGtZero:true,
				maxlength:[3],
				range:[1,800]
			},
			xdhkqs:{
				required:true,
				isInteger:true,
				maxlength:[3]
			},
			xdqhke:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			jsyhkrq:{
				required:true
			},
			zjycsjhkrq:{
				required:true
			},
			byyhkje:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			bysjhkje:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			ye:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			dqyqqs:{
				required:true,
				isInteger:true,
				maxlength:[2]
			},
			dqyqze:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			yqwghdkbj1:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			yqwghdkbj2:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			yqwghdkbj3:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			yqwghdkbj4:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			ljyqqs:{
				required:true,
				isInteger:true,
				maxlength:[3]
			},
			zgyqqs:{
				required:true,
				isInteger:true,
				maxlength:[2]
			},
			wjflzt:{
				required:true
			},
			zhzt:{
				required:true
			},
			byhkzt:{
				required:true
			},
			zhyyzxxts:{
				required:true
			},
			xm:{
				required:true,
				rangelength:[2,30]
			},
			zjlx:{
				required:true
			},
			zjhm:{
				required:true,
				isIdCardNo:true
			},
			dkhtsxrq:{
				required:true
			},
			dkhtzzrq:{
				required:true
			},
			htbz:{
				required:true
			},
			dkhtje:{
				required:true,
				isFloat:true,
				maxlength:[8]
			},
			dkhtzt:{
				required:true
			}
		},
		messages:{
			dklx:{
				required:"请输入贷款类型"
			},
			dkhthm:{
				required:"请输入贷款合同号码",
				maxlength:"贷款合同号码不能超过60位"
			},
			ywh:{
				required:"请输入业务号",
				maxlength:"业务号不能超过40位"
			},
			fsdd:{
				required:"请选择发生地点"
			},
			khrq:{
				required:"请选择开户日期"
			},
			dqrq:{
				required:"请选择到期日期"
			},
			jcbz:{
				required:"请选择币种"
			},
			sxed:{
				required:"请输入授信额度",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"授信额度不能超过8位"
			},
			gxsxed:{
				required:"请输入共享授信额度",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"共享授信额度不能超过8位"
			},
			zdfze:{
				required:"请输入最大负债额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"最大负债额不能超过8位"
			},
			dbfs:{
				required:"请选择担保方式"
			},
			hkpl:{
				required:"请选择还款频率"
			},
			hkqs:{
				required:"请输入还款期数",
				digits:"还款期数应为大于0的整数",
				isIntGtZero:"还款期数应为大于0的整数",
				maxlength:"还款期数长度不能超过3位",
				range:"还款期数应在1~800之间"
			},
			syhkqs:{
				required:"请输入剩余还款期数",
				digits:"剩余还款期数应为大于0的整数",
				isIntGtZero:"剩余还款期数应为大于0的整数",
				maxlength:"剩余还款期数长度不能超过3位",
				range:"剩余还款期数应在1~800之间"
			},
			xdhkqs:{
				required:"请输入协定还款期数",
				isInteger:"请输入整数",
				maxlength:"协定还款期数不能超过3位"
			},
			xdqhke:{
				required:"请输入协定期还款额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"协定期还款额不能超过8位"
			},
			jsyhkrq:{
				required:"请选择结算/应还款日期"
			},
			zjycsjhkrq:{
				required:"请选择最近一次实际还款日期"
			},
			byyhkje:{
				required:"请输入本月应还款金额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"本月应还款金额不能超过8位"
			},
			bysjhkje:{
				required:"请输入本月实际还款金额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"本月实际还款金额不能超过8位"
			},
			ye:{
				required:"请输入余额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"余额不能超过8位"
			},
			dqyqqs:{
				required:"请输入当前逾期期数",
				isInteger:"请输入整数",
				maxlength:"当前逾期期数不能超过2位"
			},
			dqyqze:{
				required:"请输入当前逾期总额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"当前逾期总额不能超过8位"
			},
			yqwghdkbj1:{
				required:"请输入逾期31-60天未归还贷款本金",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"逾期31-60天未归还贷款本金不能超过8位"
			},
			yqwghdkbj2:{
				required:"请输入逾期61-90天未归还贷款本金",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"逾期61-90天未归还贷款本金不能超过8位"
			},
			yqwghdkbj3:{
				required:"请输入逾期91-180天未归还贷款本金",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"逾期91-180天未归还贷款本金不能超过8位"
			},
			yqwghdkbj4:{
				required:"请输入逾期180天以上未归还贷款本金",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"逾期180天以上未归还贷款本金不能超过8位"
			},
			ljyqqs:{
				required:"请输入累计逾期期数",
				isInteger:"请输入整数",
				maxlength:"累计逾期期数不能超过3位"
			},
			zgyqqs:{
				required:"请输入最高逾期期数",
				isInteger:"请输入整数",
				maxlength:"最高逾期期数不能超过2位"
			},
			wjflzt:{
				required:"请选择五级分类状态"
			},
			zhzt:{
				required:"请选择账户状态"
			},
			byhkzt:{
				required:"请选择本月还款状态"
			},
			zhyyzxxts:{
				required:"请选择账户拥有者信息提示"
			},
			xm:{
				required:"请输入姓名",
				rangelength:"姓名长度应为2~30个字符"
			},
			zjlx:{
				required:"请选择证件类型"
			},
			zjhm:{
				required:"请输入证件号码",
				isIdCardNo:"请输入正确的身份证号码"
			},
			dkhtsxrq:{
				required:"请选择贷款合同生效日期"
			},
			dkhtzzrq:{
				required:"请选择贷款合同终止日期"
			},
			htbz:{
				required:"请选择币种"
			},
			dkhtje:{
				required:"请输入贷款合同金额",
				isFloat:"请输入合法的数字，只能包含数字、小数点",
				maxlength:"贷款合同金额不能超过8位"
			},
			dkhtzt:{
				required:"请选择合同有效状态"
			}
		},
		errorPlacement: function(error, element) {
			$( element ).closest( "form" ).find( "span[id='error_" + element.attr( "name" ) + "']" ).append( error );
		},
		submitHandler:function(){
			var dkjbxxInfoForm = $('#dkjbxxInfoForm').serializeObject();
			dkjbxxInfoForm.hkys = calculateHkys(dkjbxxInfoForm.hkpl, dkjbxxInfoForm.hkqs);
			dkjbxxInfoForm.syhkys = calculateHkys(dkjbxxInfoForm.hkpl, dkjbxxInfoForm.syhkqs);
			if(dkjbxxValidate(dkjbxxInfoForm)){
				jQuery.ajax({
					type: "post",
				    url: "${pageContext.request.contextPath}/p2p/dkjbxx/doAdd",
				    contentType: "application/json",
				    data: JSON.stringify(dkjbxxInfoForm),
				    success: function (p2pDkjbxxId) {
				    	if(p2pDkjbxxId>0){
					        alert("添加成功！");
				    	}else{
				    		alert("添加失败！");
				    		
				    	}
				    	window.location.href = "${pageContext.request.contextPath}/p2p/dkjbxx/showList";
				    },
				    error: function () {
				        alert("save dkjbxx error");
				    }	
				});
			}
			
		}
	});

});



function calculateHkys(hkpl, hkqs){
	switch(hkpl){
		case "01":			
			return Digit.round(parseInt(hkqs)/30.42, 1);
			break;
		case "02":
			return Digit.round(parseInt(hkqs)/4.33, 1);
			break;
		case "03":
			return parseInt(hkqs);
			break;
		case "04":
			return parseInt(hkqs)*3;
			break;
		case "05":
			return parseInt(hkqs)*6;
			break;
		case "06":
			return parseInt(hkqs)*12;
			break;
		case "07":
			return "O";
			break;
		case "08":
			return "U";
			break;
		case "99":
			return "X";
			break;
		default:
			return 0;
			break;
	}
}

function chooseHkpl(hkpl){
	var xdhkqs = $("#xdhkqs");
	var xdqhke = $("#xdqhke");
	switch(hkpl){
		case "07":
			xdhkqs.val("O");
			xdhkqs.attr("readonly","readonly");
			xdhkqs.addClass("ignore");
			if(xdqhke.val()=="U"){
				xdqhke.val("");
				xdqhke.removeAttr("readonly");
				xdqhke.removeClass("ignore");
			}else{
				xdqhke.removeAttr("readonly");
				xdqhke.removeClass("ignore");
			}
			break;
		case "08":
			xdhkqs.val("U");
			xdhkqs.attr("readonly","readonly");
			xdhkqs.addClass("ignore");
			xdqhke.val("U");
			xdqhke.attr("readonly","readonly");
			xdqhke.addClass("ignore");
			break;
		case "99":
			xdhkqs.val("X");
			xdhkqs.attr("readonly","readonly");
			xdhkqs.addClass("ignore");
			if(xdqhke.val()=="U"){
				xdqhke.val("");
				xdqhke.removeAttr("readonly");
				xdqhke.removeClass("ignore");
			}else{
				xdqhke.removeAttr("readonly");
				xdqhke.removeClass("ignore");
			}
			break;
		default:
			if(xdhkqs.val()=="O"||xdhkqs.val()=="U"||xdhkqs.val()=="X"){
				xdhkqs.val("");
				xdhkqs.removeAttr("readonly");
				xdhkqs.removeClass("ignore");
			}else{
				xdhkqs.removeAttr("readonly");
				xdhkqs.removeClass("ignore");
			}
			if(xdqhke.val()=="U"){
				xdqhke.val("");
				xdqhke.removeAttr("readonly");
				xdqhke.removeClass("ignore");
			}else{
				xdqhke.removeAttr("readonly");
				xdqhke.removeClass("ignore");
			}
			break;
	}
}

function dkjbxxValidate(form){
	//清空所有校验错误提示信息
	$("span[id^='error_']").empty();
	
	//开户日期<=到期日期
	if(form.khrq <= form.dqrq){
		$("#error_khrq").html("*");
	}else{
		$("#error_khrq").html("*开户日期<=到期日期");
		return false;
	}
	
	//开户日期<=结算\应还款日期
	if(form.khrq <= form.jsyhkrq){
		$("#error_khrq").html("*");
	}else{
		$("#error_khrq").html("*开户日期<=结算\应还款日期");
		return false;
	}
	
	//授信额度>=最大负债额
	if(parseInt(form.sxed) >= parseInt(form.zdfze)){
		$("#error_sxed").html("*");
	}else{
		$("#error_sxed").html("*授信额度>=最大负债额");
		return false;
	}
	
	//若“账户状态”为“2-逾期”或“4-呆账”，则“当前逾期期数”、“当前逾期总额”、“累计逾期期数”、“最高逾期期数”不能为零
	if(form.zhzt == "2" || form.zhzt == "4"){
		form.dqyqqs=="0"?$("#error_dqyqqs").html("*当前逾期期数不能为0"):$("#error_dqyqqs").html("*");
		form.dqyqze=="0"?$("#error_dqyqze").html("*当前逾期总额不能为0"):$("#error_dqyqze").html("*");
		form.ljyqqs=="0"?$("#error_ljyqqs").html("*累计逾期期数不能为0"):$("#error_ljyqqs").html("*");
		form.zgyqqs=="0"?$("#error_zgyqqs").html("*最高逾期期数不能为0"):$("#error_zgyqqs").html("*");

		if ($("#error_dqyqqs").html() != "*"
				|| $("#error_dqyqze").html() != "*"
				|| $("#error_ljyqqs").html() != "*"
				|| $("#error_zgyqqs").html() != "*") {
			return false;
		}
	} else {
		$("#error_dqyqqs").html("*");
		$("#error_dqyqze").html("*");
		$("#error_ljyqqs").html("*");
		$("#error_zgyqqs").html("*");
	}

	//若“账户状态”为“2-逾期”，则“本月还款状态”必须为1～7的数字
	if (form.zhzt == "2") {
		if ("1234567".indexOf(form.byhkzt) < 0) {
			$("#error_byhkzt").html("*请选择相应的逾期状态");
			return false;
		} else {
			$("#error_byhkzt").html("*");
		}
	}

	//若“账户状态”为“4-呆账”，则“本月还款状态”必须为1～7的数字或“G-结束”
	if (form.zhzt == "4") {
		if ("1234567G".indexOf(form.byhkzt) < 0) {
			$("#error_byhkzt").html("*请选择相应的逾期状态或结束");
			return false;
		} else {
			$("#error_byhkzt").html("*");
		}
	}

	//若“账户状态”为“3-结清”，则“余额”、“当前逾期期数”、“当前逾期总额”必须为0
	if (form.zhzt == "3") {
		form.ye != "0"?$("#error_ye").html("*余额必须为0"):$("#error_ye").html("*");
		form.dqyqqs != "0"?$("#error_dqyqqs").html("*当前逾期期数必须为0"):$("#error_dqyqqs").html("*");
		form.dqyqze != "0"?$("#error_dqyqze").html("*当前逾期总额必须为0"):$("#error_dqyqze").html("*");
		form.byhkzt != "C"?$("#error_byhkzt").html("*请选择“结清”"):$("#error_byhkzt").html("*");
		
		if ($("#error_ye").html() != "*"
			|| $("#error_dqyqqs").html() != "*"
			|| $("#error_dqyqze").html() != "*"
			|| $("#error_byhkzt").html() != "*") {
			return false;
		}
	} else {
		$("#error_ye").html("*");
		$("#error_dqyqqs").html("*");
		$("#error_dqyqze").html("*");
		$("#error_byhkzt").html("*");
	}
	
	//“还款期数”>=“剩余还款期数”
	if(form.hkqs >= form.syhkqs){
		$("#error_hkqs").html("*");
	}else{
		$("#error_hkqs").html("*还款期数>=剩余还款期数");
		return false;
	}
}

function goback() {
	window.location.href = "${pageContext.request.contextPath}/p2p/dkjbxx/showList";
}

$('.form_date').datetimepicker({
	format : "yyyymmdd",
	language : 'zh-CN',
	weekStart : 1,
	todayBtn : 1,
	autoclose : 1,
	todayHighlight : 1,
	startView : 2,
	minView : 2,
	forceParse : 0
});
</script>
<%@ include file="./../../common/footer.jsp"%>
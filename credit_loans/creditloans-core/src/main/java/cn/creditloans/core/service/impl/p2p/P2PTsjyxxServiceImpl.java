package cn.creditloans.core.service.impl.p2p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.P2PTsjyxxDao;
import cn.creditloans.core.dto.p2p.P2PTsjyxxDTO;
import cn.creditloans.core.entity.p2p.P2PTsjyxx;
import cn.creditloans.core.service.P2PTsjyxxService;
import cn.creditloans.tools.page.PageModel;

@Service("p2pTsjyxxService")
public class P2PTsjyxxServiceImpl implements P2PTsjyxxService {
	private static final Log logger = LogFactory.getLog(P2PTsjyxxServiceImpl.class);
	
	@Autowired
	P2PTsjyxxDao p2pTsjyxxDao;
	
	public PageModel<P2PTsjyxxDTO> getP2PTsjyxxPageList(
			P2PTsjyxxDTO p2pTsjyxxDto, int currentPage, int pageSize) {
		P2PTsjyxx p2pTsjyxx = new P2PTsjyxx();
		BeanUtils.copyProperties(p2pTsjyxxDto, p2pTsjyxx);
		
		PageModel<P2PTsjyxxDTO> pm = new PageModel<P2PTsjyxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = p2pTsjyxxDao.selectP2PTsjyxxPageCount(p2pTsjyxx);
		List<P2PTsjyxx> p2pTsjyxxList = p2pTsjyxxDao.selectP2PTsjyxxPageList(pm, p2pTsjyxx);
		List<P2PTsjyxxDTO> p2pTsjyxxDtoList = new ArrayList<P2PTsjyxxDTO>();
		for(P2PTsjyxx _p2pTsjyxx : p2pTsjyxxList){
			P2PTsjyxxDTO _p2pTsjyxxDto = new P2PTsjyxxDTO();
			BeanUtils.copyProperties(_p2pTsjyxx, _p2pTsjyxxDto);
			p2pTsjyxxDtoList.add(_p2pTsjyxxDto);
		}
		
		pm.setTotal(count);
		pm.setDatas(p2pTsjyxxDtoList);
		return pm;
	}

	public P2PTsjyxxDTO getP2PTsjyxxById(int id) {
		P2PTsjyxx p2pTsjyxx = p2pTsjyxxDao.selectP2PTsjyxxById(id);
		P2PTsjyxxDTO p2pTsjyxxDto = new P2PTsjyxxDTO();
		BeanUtils.copyProperties(p2pTsjyxx, p2pTsjyxxDto);
		return p2pTsjyxxDto;
	}

	public boolean p2pTsjyxxIsOnly(String zjlx, String zjhm, String fsrq, String orgCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("zjlx", zjlx);
		params.put("zjhm", zjhm);
		params.put("fsrq", fsrq);
		params.put("orgCode", orgCode);
		int count = p2pTsjyxxDao.selectP2PTsjyxxCountForUnique(params);
		if(count > 0){
			return false;
		}
		return true;
	}

	public int addP2PTsjyxx(P2PTsjyxxDTO p2pTsjyxxDto) {
		P2PTsjyxx p2pTsjyxx = new P2PTsjyxx();
		BeanUtils.copyProperties(p2pTsjyxxDto, p2pTsjyxx);
		p2pTsjyxxDao.insertP2PTsjyxx(p2pTsjyxx);
		int p2pTsjyxxId = p2pTsjyxx.getId();
		return p2pTsjyxxId;
	}

	public void editP2PTsjyxx(P2PTsjyxxDTO p2pTsjyxxDto) {
		P2PTsjyxx p2pTsjyxx = new P2PTsjyxx();
		BeanUtils.copyProperties(p2pTsjyxxDto, p2pTsjyxx);
		p2pTsjyxxDao.updateP2PTsjyxx(p2pTsjyxx);		
	}

	public void deleteP2PTsjyxxById(int id) {
		p2pTsjyxxDao.deleteP2PTsjyxxById(id);		
	}
}

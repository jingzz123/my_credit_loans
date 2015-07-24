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

import cn.creditloans.core.dao.P2PDbxxDao;
import cn.creditloans.core.dto.p2p.P2PDbxxDTO;
import cn.creditloans.core.entity.p2p.P2PDbxx;
import cn.creditloans.core.service.P2PDbxxService;
import cn.creditloans.tools.page.PageModel;

@Service("p2pDbxxService")
public class P2PDbxxServiceImpl implements P2PDbxxService{
	private static final Log logger = LogFactory.getLog(P2PDbxxServiceImpl.class);
	
	@Autowired
	P2PDbxxDao p2pDbxxDao;
	
	public PageModel<P2PDbxxDTO> getP2PDbxxPageList(
			P2PDbxxDTO p2pDbxxDto, int currentPage, int pageSize) {
		P2PDbxx p2pDbxx = new P2PDbxx();
		BeanUtils.copyProperties(p2pDbxxDto, p2pDbxx);
		
		PageModel<P2PDbxxDTO> pm = new PageModel<P2PDbxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = p2pDbxxDao.selectP2PDbxxPageCount(p2pDbxx);
		List<P2PDbxx> p2pDbxxList = p2pDbxxDao.selectP2PDbxxPageList(pm, p2pDbxx);
		List<P2PDbxxDTO> p2pDbxxDtoList = new ArrayList<P2PDbxxDTO>();
		for(P2PDbxx _p2pDbxx : p2pDbxxList){
			P2PDbxxDTO _p2pDbxxDto = new P2PDbxxDTO();
			BeanUtils.copyProperties(_p2pDbxx, _p2pDbxxDto);
			p2pDbxxDtoList.add(_p2pDbxxDto);
		}
		
		pm.setTotal(count);
		pm.setDatas(p2pDbxxDtoList);
		return pm;
	}

	public P2PDbxxDTO getP2PDbxxById(int id) {
		P2PDbxx p2pDbxx = p2pDbxxDao.selectP2PDbxxById(id);
		P2PDbxxDTO p2pDbxxDto = new P2PDbxxDTO();
		BeanUtils.copyProperties(p2pDbxx, p2pDbxxDto);
		return p2pDbxxDto;
	}

	public boolean p2pDbxxIsOnly(String dbrzjlx, String dbrzjhm,int dkjbxxId, String orgCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dbrzjlx", dbrzjlx);
		params.put("dbrzjhm", dbrzjhm);
		params.put("dkjbxxId", dkjbxxId);
		params.put("orgCode", orgCode);
		int count = p2pDbxxDao.selectP2PDbxxCountForUnique(params);
		if(count > 0){
			return false;
		}
		return true;
	}

	public int addP2PDbxx(P2PDbxxDTO p2pDbxxDto) {
		P2PDbxx p2pDbxx = new P2PDbxx();
		BeanUtils.copyProperties(p2pDbxxDto, p2pDbxx);
		p2pDbxxDao.insertP2PDbxx(p2pDbxx);
		int p2pDbxxId = p2pDbxx.getId();
		return p2pDbxxId;
	}

	public void editP2PDbxx(P2PDbxxDTO p2pDbxxDto) {
		P2PDbxx p2pDbxx = new P2PDbxx();
		BeanUtils.copyProperties(p2pDbxxDto, p2pDbxx);
		p2pDbxxDao.updateP2PDbxx(p2pDbxx);		
	}

	public void deleteP2PDbxxById(int id) {
		p2pDbxxDao.deleteP2PDbxxById(id);		
	}
}

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

import cn.creditloans.core.dao.P2PTzrxxDao;
import cn.creditloans.core.dto.p2p.P2PTzrxxDTO;
import cn.creditloans.core.entity.p2p.P2PTzrxx;
import cn.creditloans.core.service.P2PTzrxxService;
import cn.creditloans.tools.page.PageModel;

@Service("p2pTzrxxService")
public class P2PTzrxxServiceImpl implements P2PTzrxxService {
	private static final Log logger = LogFactory.getLog(P2PTzrxxServiceImpl.class);
	
	@Autowired
	P2PTzrxxDao p2pTzrxxDao;
	
	public PageModel<P2PTzrxxDTO> getP2PTzrxxPageList(
			P2PTzrxxDTO p2pTzrxxDto, int currentPage, int pageSize) {
		P2PTzrxx p2pTzrxx = new P2PTzrxx();
		BeanUtils.copyProperties(p2pTzrxxDto, p2pTzrxx);
		
		PageModel<P2PTzrxxDTO> pm = new PageModel<P2PTzrxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = p2pTzrxxDao.selectP2PTzrxxPageCount(p2pTzrxx);
		List<P2PTzrxx> p2pTzrxxList = p2pTzrxxDao.selectP2PTzrxxPageList(pm, p2pTzrxx);
		List<P2PTzrxxDTO> p2pTzrxxDtoList = new ArrayList<P2PTzrxxDTO>();
		for(P2PTzrxx _p2pTzrxx : p2pTzrxxList){
			P2PTzrxxDTO _p2pTzrxxDto = new P2PTzrxxDTO();
			BeanUtils.copyProperties(_p2pTzrxx, _p2pTzrxxDto);
			p2pTzrxxDtoList.add(_p2pTzrxxDto);
		}
		
		pm.setTotal(count);
		pm.setDatas(p2pTzrxxDtoList);
		return pm;
	}

	public P2PTzrxxDTO getP2PTzrxxById(int id) {
		P2PTzrxx p2pTzrxx = p2pTzrxxDao.selectP2PTzrxxById(id);
		P2PTzrxxDTO p2pTzrxxDto = new P2PTzrxxDTO();
		BeanUtils.copyProperties(p2pTzrxx, p2pTzrxxDto);
		return p2pTzrxxDto;
	}

	public boolean p2pTzrxxIsOnly(String tzrzjlx, String tzrzjhm, int dkjbxxId, String orgCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tzrzjlx", tzrzjlx);
		params.put("tzrzjhm", tzrzjhm);
		params.put("dkjbxxId", dkjbxxId);
		params.put("orgCode", orgCode);
		int count = p2pTzrxxDao.selectP2PTzrxxCountForUnique(params);
		if(count > 0){
			return false;
		}
		return true;
	}

	public int addP2PTzrxx(P2PTzrxxDTO p2pTzrxxDto) {
		P2PTzrxx p2pTzrxx = new P2PTzrxx();
		BeanUtils.copyProperties(p2pTzrxxDto, p2pTzrxx);
		p2pTzrxxDao.insertP2PTzrxx(p2pTzrxx);
		int p2pTzrxxId = p2pTzrxx.getId();
		return p2pTzrxxId;
	}

	public void editP2PTzrxx(P2PTzrxxDTO p2pTzrxxDto) {
		P2PTzrxx p2pTzrxx = new P2PTzrxx();
		BeanUtils.copyProperties(p2pTzrxxDto, p2pTzrxx);
		p2pTzrxxDao.updateP2PTzrxx(p2pTzrxx);		
	}

	public void deleteP2PTzrxxById(int id) {
		p2pTzrxxDao.deleteP2PTzrxxById(id);		
	}
}

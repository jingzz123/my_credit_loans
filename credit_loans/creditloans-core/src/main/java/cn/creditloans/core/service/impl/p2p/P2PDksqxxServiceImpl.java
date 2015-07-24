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

import cn.creditloans.core.dao.P2PDksqxxDao;
import cn.creditloans.core.dto.p2p.P2PDksqxxDTO;
import cn.creditloans.core.entity.p2p.P2PDksqxx;
import cn.creditloans.core.service.P2PDksqxxService;
import cn.creditloans.tools.page.PageModel;

@Service("p2pDksqxxService")
public class P2PDksqxxServiceImpl implements P2PDksqxxService {
	private static final Log logger = LogFactory.getLog(P2PDksqxxServiceImpl.class);
	
	@Autowired
	P2PDksqxxDao p2pDksqxxDao;
	
	public PageModel<P2PDksqxxDTO> getP2PDksqxxPageList(
			P2PDksqxxDTO p2pDksqxxDto, int currentPage, int pageSize) {
		P2PDksqxx p2pDksqxx = new P2PDksqxx();
		BeanUtils.copyProperties(p2pDksqxxDto, p2pDksqxx);
		
		PageModel<P2PDksqxxDTO> pm = new PageModel<P2PDksqxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = p2pDksqxxDao.selectP2PDksqxxPageCount(p2pDksqxx);
		List<P2PDksqxx> p2pDksqxxList = p2pDksqxxDao.selectP2PDksqxxPageList(pm, p2pDksqxx);
		List<P2PDksqxxDTO> p2pDksqxxDtoList = new ArrayList<P2PDksqxxDTO>();
		for(P2PDksqxx _p2pDksqxx : p2pDksqxxList){
			P2PDksqxxDTO _p2pDksqxxDto = new P2PDksqxxDTO();
			BeanUtils.copyProperties(_p2pDksqxx, _p2pDksqxxDto);
			p2pDksqxxDtoList.add(_p2pDksqxxDto);
		}
		
		pm.setTotal(count);
		pm.setDatas(p2pDksqxxDtoList);
		return pm;
	}

	public P2PDksqxxDTO getP2PDksqxxById(int id) {
		P2PDksqxx p2pDksqxx = p2pDksqxxDao.selectP2PDksqxxById(id);
		P2PDksqxxDTO p2pDksqxxDto = new P2PDksqxxDTO();
		BeanUtils.copyProperties(p2pDksqxx, p2pDksqxxDto);
		return p2pDksqxxDto;
	}

	public boolean p2pDksqxxIsOnly(String dksqh, String orgCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dksqh", dksqh);
		params.put("orgCode", orgCode);
		int count = p2pDksqxxDao.selectP2PDksqxxCountForUnique(params);
		if(count > 0){
			return false;
		}
		return true;
	}

	public int addP2PDksqxx(P2PDksqxxDTO p2pDksqxxDto) {
		P2PDksqxx p2pDksqxx = new P2PDksqxx();
		BeanUtils.copyProperties(p2pDksqxxDto, p2pDksqxx);
		p2pDksqxxDao.insertP2PDksqxx(p2pDksqxx);
		int p2pDksqxxId = p2pDksqxx.getId();
		return p2pDksqxxId;
	}

	public void editP2PDksqxx(P2PDksqxxDTO p2pDksqxxDto) {
		P2PDksqxx p2pDksqxx = new P2PDksqxx();
		BeanUtils.copyProperties(p2pDksqxxDto, p2pDksqxx);
		p2pDksqxxDao.updateP2PDksqxx(p2pDksqxx);		
	}

	public void deleteP2PDksqxxById(int id) {
		p2pDksqxxDao.deleteP2PDksqxxById(id);		
	}
}

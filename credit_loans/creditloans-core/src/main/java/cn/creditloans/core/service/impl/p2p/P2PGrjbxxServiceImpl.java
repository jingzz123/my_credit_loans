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

import cn.creditloans.core.dao.P2PGrjbxxDao;
import cn.creditloans.core.dto.p2p.P2PGrjbxxDTO;
import cn.creditloans.core.entity.p2p.P2PGrjbxx;
import cn.creditloans.core.service.P2PGrjbxxService;
import cn.creditloans.tools.page.PageModel;

@Service("p2pGrjbxxService")
public class P2PGrjbxxServiceImpl implements P2PGrjbxxService {
	private static final Log logger = LogFactory.getLog(P2PGrjbxxServiceImpl.class);
	
	@Autowired
	P2PGrjbxxDao p2pGrjbxxDao;
	
	public PageModel<P2PGrjbxxDTO> getP2PGrjbxxPageList(
			P2PGrjbxxDTO p2pGrjbxxDto, int currentPage, int pageSize) {
		P2PGrjbxx p2pGrjbxx = new P2PGrjbxx();
		BeanUtils.copyProperties(p2pGrjbxxDto, p2pGrjbxx);
		
		PageModel<P2PGrjbxxDTO> pm = new PageModel<P2PGrjbxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = p2pGrjbxxDao.selectP2PGrjbxxPageCount(p2pGrjbxx);
		List<P2PGrjbxx> p2pGrjbxxList = p2pGrjbxxDao.selectP2PGrjbxxPageList(pm, p2pGrjbxx);
		List<P2PGrjbxxDTO> p2pGrjbxxDtoList = new ArrayList<P2PGrjbxxDTO>();
		for(P2PGrjbxx _p2pGrjbxx : p2pGrjbxxList){
			P2PGrjbxxDTO _p2pGrjbxxDto = new P2PGrjbxxDTO();
			BeanUtils.copyProperties(_p2pGrjbxx, _p2pGrjbxxDto);
			p2pGrjbxxDtoList.add(_p2pGrjbxxDto);
		}
		
		pm.setTotal(count);
		pm.setDatas(p2pGrjbxxDtoList);
		return pm;
	}

	public P2PGrjbxxDTO getP2PGrjbxxById(int id) {
		P2PGrjbxx p2pGrjbxx = p2pGrjbxxDao.selectP2PGrjbxxById(id);
		P2PGrjbxxDTO p2pGrjbxxDto = new P2PGrjbxxDTO();
		BeanUtils.copyProperties(p2pGrjbxx, p2pGrjbxxDto);
		return p2pGrjbxxDto;
	}

	public boolean p2pGrjbxxIsOnly(String zjlx, String zjhm, String orgCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("zjlx", zjlx);
		params.put("zjhm", zjhm);
		params.put("orgCode", orgCode);
		int count = p2pGrjbxxDao.selectP2PGrjbxxCountForUnique(params);
		if(count > 0){
			return false;
		}
		return true;
	}

	public int addP2PGrjbxx(P2PGrjbxxDTO p2pGrjbxxDto) {
		P2PGrjbxx p2pGrjbxx = new P2PGrjbxx();
		BeanUtils.copyProperties(p2pGrjbxxDto, p2pGrjbxx);
		p2pGrjbxxDao.insertP2PGrjbxx(p2pGrjbxx);
		int p2pGrjbxxId = p2pGrjbxx.getId();
		return p2pGrjbxxId;
	}

	public void editP2PGrjbxx(P2PGrjbxxDTO p2pGrjbxxDto) {
		P2PGrjbxx p2pGrjbxx = new P2PGrjbxx();
		BeanUtils.copyProperties(p2pGrjbxxDto, p2pGrjbxx);
		p2pGrjbxxDao.updateP2PGrjbxx(p2pGrjbxx);		
	}

	public void deleteP2PGrjbxxById(int id) {
		p2pGrjbxxDao.deleteP2PGrjbxxById(id);		
	}

}

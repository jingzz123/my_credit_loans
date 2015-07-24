package cn.creditloans.core.service.impl.p2p;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.P2PDkjbxxDao;
import cn.creditloans.core.dto.p2p.P2PDkjbxxDTO;
import cn.creditloans.core.entity.p2p.P2PDkjbxx;
import cn.creditloans.core.service.P2PDkjbxxService;
import cn.creditloans.tools.page.PageModel;

@Service("p2pDkjbxxService")
public class P2PDkjbxxServiceImpl implements P2PDkjbxxService {
	private static final Log logger = LogFactory.getLog(P2PDkjbxxServiceImpl.class);
	
	@Autowired
	P2PDkjbxxDao p2pDkjbxxDao;
	
	public PageModel<P2PDkjbxxDTO> getP2PDkjbxxPageList(
			P2PDkjbxxDTO p2pDkjbxxDto, int currentPage, int pageSize) {
		P2PDkjbxx p2pDkjbxx = new P2PDkjbxx();
		BeanUtils.copyProperties(p2pDkjbxxDto, p2pDkjbxx);
		
		PageModel<P2PDkjbxxDTO> pm = new PageModel<P2PDkjbxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = p2pDkjbxxDao.selectP2PDkjbxxPageCount(p2pDkjbxx);
		List<P2PDkjbxx> p2pDkjbxxList = p2pDkjbxxDao.selectP2PDkjbxxPageList(pm, p2pDkjbxx);
		List<P2PDkjbxxDTO> p2pDkjbxxDtoList = new ArrayList<P2PDkjbxxDTO>();
		for(P2PDkjbxx _p2pDkjbxx : p2pDkjbxxList){
			P2PDkjbxxDTO _p2pDkjbxxDto = new P2PDkjbxxDTO();
			BeanUtils.copyProperties(_p2pDkjbxx, _p2pDkjbxxDto);
			p2pDkjbxxDtoList.add(_p2pDkjbxxDto);
		}
		
		pm.setTotal(count);
		pm.setDatas(p2pDkjbxxDtoList);
		return pm;
	}

	public P2PDkjbxxDTO getP2PDkjbxxById(int id) {
		P2PDkjbxx p2pDkjbxx = p2pDkjbxxDao.selectP2PDkjbxxById(id);
		P2PDkjbxxDTO p2pDkjbxxDto = new P2PDkjbxxDTO();
		BeanUtils.copyProperties(p2pDkjbxx, p2pDkjbxxDto);
		return p2pDkjbxxDto;
	}

	public boolean p2pDkjbxxIsOnly(String dkhthm, String ywh,String jsyhkrq, String orgCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ywh", ywh);
		params.put("jsyhkrq", jsyhkrq);
		params.put("orgCode", orgCode);
		int count = p2pDkjbxxDao.selectP2PDkjbxxCountForUnique(params);
		if(count > 0){
			return false;
		}
		return true;
	}

	public int addP2PDkjbxx(P2PDkjbxxDTO p2pDkjbxxDto) {
		try {
			String hkzt = getHkzt(p2pDkjbxxDto.getDkhthm(),p2pDkjbxxDto.getYwh(),p2pDkjbxxDto.getOrgCode(),p2pDkjbxxDto.getJsyhkrq());
			if(StringUtils.isNotEmpty(hkzt)){
				p2pDkjbxxDto.setHkzt(hkzt+p2pDkjbxxDto.getByhkzt());
				P2PDkjbxx p2pDkjbxx = new P2PDkjbxx();
				BeanUtils.copyProperties(p2pDkjbxxDto, p2pDkjbxx);
				p2pDkjbxxDao.insertP2PDkjbxx(p2pDkjbxx);
				int p2pDkjbxxId = p2pDkjbxx.getId();
				return p2pDkjbxxId;
			}else{
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		
	}

	public void editP2PDkjbxx(P2PDkjbxxDTO p2pDkjbxxDto) {
		try {
			String hkzt = getHkzt(p2pDkjbxxDto.getDkhthm(),p2pDkjbxxDto.getYwh(),p2pDkjbxxDto.getOrgCode(),p2pDkjbxxDto.getJsyhkrq());
			if(StringUtils.isNotEmpty(hkzt)){
				p2pDkjbxxDto.setHkzt(hkzt+p2pDkjbxxDto.getByhkzt());
				P2PDkjbxx p2pDkjbxx = new P2PDkjbxx();
				BeanUtils.copyProperties(p2pDkjbxxDto, p2pDkjbxx);
				p2pDkjbxxDao.updateP2PDkjbxx(p2pDkjbxx);
			}			
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		}		
	}

	public void deleteP2PDkjbxxById(int id) {
		p2pDkjbxxDao.deleteP2PDkjbxxById(id);		
	}
	
	public String getHkzt(String dkhthm, String ywh, String orgCode, String jsyhkrq) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dkhthm", dkhthm);
		params.put("ywh", ywh);
		params.put("orgCode", orgCode);
		Date date = DateUtils.parseDate(jsyhkrq, new String[]{"yyyyMMdd"});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String hkzt = "";
		for(int i = 1; i <= 23; i++){
			params.put("jsyhkrq", sdf.format(DateUtils.addMonths(date, -i)));
			String byhkzt = p2pDkjbxxDao.selectByhkztByUniqueId(params);
			if(StringUtils.isEmpty(byhkzt)){
				byhkzt = "/";
			}
			hkzt = byhkzt + hkzt;				
		}
		return hkzt;
	}
	
}

package cn.creditloans.core.service.impl.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.DbBdbrxxDao;
import cn.creditloans.core.dao.DbBfjnmxxxDao;
import cn.creditloans.core.dao.DbDcmxxxDao;
import cn.creditloans.core.dao.DbFdbrxxDao;
import cn.creditloans.core.dao.DbZcmxxxDao;
import cn.creditloans.core.dao.DbZqrjzhtxxDao;
import cn.creditloans.core.dao.DbjbxxDao;
import cn.creditloans.core.dto.db.DbjbxxDTO;
import cn.creditloans.core.entity.db.DbZcmxxx;
import cn.creditloans.core.entity.db.Dbjbxx;
import cn.creditloans.core.service.DbjbxxService;
import cn.creditloans.tools.page.PageModel;
@Service("DbjbxxService")
public class DbjbxxServiceImpl implements DbjbxxService {

	@Autowired
	DbjbxxDao dbjbxxDao;
	@Autowired
	DbBdbrxxDao dbBdbrxxDao;
	@Autowired
	DbBfjnmxxxDao dbBfjnmxxxDao;
	@Autowired
	DbDcmxxxDao dbDcmxxxDao;
	@Autowired
	DbFdbrxxDao dbFdbrxxDao;
	@Autowired
	DbZcmxxxDao dbZcmxxxDao;
	@Autowired
	DbZqrjzhtxxDao dbZqrjzhtxxDao;
	/**
	 * 分页查询
	 * @param dbjbxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageModel<DbjbxxDTO> getDbjbxxPageList(DbjbxxDTO dbjbxxDto,
			int currentPage, int pageSize) {
		Dbjbxx dbjbxx = new Dbjbxx();
		BeanUtils.copyProperties(dbjbxxDto, dbjbxx);
		
		PageModel<DbjbxxDTO> pm = new PageModel<DbjbxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = dbjbxxDao.selectDbjbxxPageCount(dbjbxx);
		List<Dbjbxx> dbjbxxList = dbjbxxDao.selectDbjbxxPageList(pm, dbjbxx);
		List<DbjbxxDTO> dbjbxxDtoList = new ArrayList<DbjbxxDTO>();
		for(Dbjbxx _dbjbxx : dbjbxxList){
			DbjbxxDTO _dbjbxxDto = new DbjbxxDTO();
			BeanUtils.copyProperties(_dbjbxx, _dbjbxxDto);
			dbjbxxDtoList.add(_dbjbxxDto);
		}
		
		pm.setTotal(count);
		pm.setDatas(dbjbxxDtoList);
		return pm;
	}
	
	public DbjbxxDTO getDbjbxxById(int id) {
		Dbjbxx dbjbxx = dbjbxxDao.selectDbjbxxById(id);
		DbjbxxDTO dbjbxxDto = new DbjbxxDTO();
		BeanUtils.copyProperties(dbjbxx, dbjbxxDto);
		return dbjbxxDto;
	}

	public int addDbjbxx(DbjbxxDTO dbjbxxDto) {
		Dbjbxx dbjbxx = new Dbjbxx();
		BeanUtils.copyProperties(dbjbxxDto, dbjbxx);
		dbjbxxDao.insertDbkjbxx(dbjbxx);
		int dbjbxxId = dbjbxx.getId();
		return dbjbxxId;
	}

	public void editDbjbxx(DbjbxxDTO dbjbxxDto) {
		Dbjbxx Dbjbxx = new Dbjbxx();
		BeanUtils.copyProperties(dbjbxxDto, Dbjbxx);
		dbjbxxDao.updateDbjbxx(Dbjbxx);
	}

	public int deleteDbjbxxById(int id) {
		int bdbrxxCount = dbBdbrxxDao.selectCountByDbhtxxId(id);
		int bfjnmxxxCount = dbBfjnmxxxDao.selectCountByDbhtxxId(id);
		int dcmxxxCount = dbDcmxxxDao.selectCountByDbhtxxId(id);
		int fdbrxxCount = dbFdbrxxDao.selectCountByDbhtxxId(id);
		int zcmxxxCount = dbZcmxxxDao.selectCountByDbhtxxId(id);
		int zqrjzhtxxCount = dbZqrjzhtxxDao.selectCountByDbhtxxId(id);
		if(bdbrxxCount>0|| bfjnmxxxCount>0 || dcmxxxCount>0 || fdbrxxCount>0 || zcmxxxCount>0 || zqrjzhtxxCount>0) {
			return 1;
		}
		dbjbxxDao.deleteDbjbxxById(id);
		return 0;
	}

	


}

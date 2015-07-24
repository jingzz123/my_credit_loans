package cn.creditloans.core.service.impl.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.DbZcmxxxDao;
import cn.creditloans.core.dto.db.DbZcmxxxDTO;
import cn.creditloans.core.entity.db.DbZcmxxx;
import cn.creditloans.core.service.DbZcmxxxService;
import cn.creditloans.tools.page.PageModel;

@Service("dbZcmxxxService")
public class DbZcmxxxServiceImpl implements DbZcmxxxService{
	private static final Log logger = LogFactory.getLog(DbZcmxxxServiceImpl.class);
	
	@Autowired
	DbZcmxxxDao dbZcmxxxDao;
	
	public PageModel<DbZcmxxxDTO> getDbZcmxxxPageList(
			DbZcmxxxDTO dbZcmxxxDTO, int currentPage, int pageSize) {
		DbZcmxxx dbZcmxxx = new DbZcmxxx();
		BeanUtils.copyProperties(dbZcmxxxDTO, dbZcmxxx);
		
		PageModel<DbZcmxxxDTO> pm = new PageModel<DbZcmxxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = dbZcmxxxDao.selectDbZcmxxxPageCount(dbZcmxxx);
		List<DbZcmxxx> dbZcmxxxList = dbZcmxxxDao.selectDbZcmxxxPageList(pm, dbZcmxxx);
		List<DbZcmxxxDTO> dbZcmxxxDTOList = new ArrayList<DbZcmxxxDTO>();
		for(DbZcmxxx _dbZcmxxx : dbZcmxxxList){
			DbZcmxxxDTO _dbZcmxxxDTO = new DbZcmxxxDTO();
			BeanUtils.copyProperties(_dbZcmxxx, _dbZcmxxxDTO);
			dbZcmxxxDTOList.add(_dbZcmxxxDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(dbZcmxxxDTOList);
		return pm;
	}

	public DbZcmxxxDTO getDbZcmxxxById(int id) {
		DbZcmxxx dbZcmxxx = dbZcmxxxDao.selectDbZcmxxxById(id);
		DbZcmxxxDTO dbZcmxxxDTO = new DbZcmxxxDTO();
		BeanUtils.copyProperties(dbZcmxxx, dbZcmxxxDTO);
		return dbZcmxxxDTO;
	}


	public int addDbZcmxxx(DbZcmxxxDTO dbZcmxxxDTO) {
		DbZcmxxx dbZcmxxx = new DbZcmxxx();
		BeanUtils.copyProperties(dbZcmxxxDTO, dbZcmxxx);
		dbZcmxxxDao.insertDbZcmxxx(dbZcmxxx);
		int dbZcmxxxId = dbZcmxxx.getId();
		return dbZcmxxxId;
	}

	public void editDbZcmxxx(DbZcmxxxDTO dbZcmxxxDTO) {
		DbZcmxxx dbZcmxxx = new DbZcmxxx();
		BeanUtils.copyProperties(dbZcmxxxDTO, dbZcmxxx);
		dbZcmxxxDao.updateDbZcmxxx(dbZcmxxx);		
	}

	public void deleteDbZcmxxxById(int id) {
		dbZcmxxxDao.deleteDbZcmxxxById(id);		
	}

}

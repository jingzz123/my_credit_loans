package cn.creditloans.core.service.impl.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.DbDcmxxxDao;
import cn.creditloans.core.dto.db.DbDcmxxxDTO;
import cn.creditloans.core.dto.db.DbFdbrxxDTO;
import cn.creditloans.core.entity.db.DbDcmxxx;
import cn.creditloans.core.entity.db.DbFdbrxx;
import cn.creditloans.core.service.DbDcmxxxService;
import cn.creditloans.tools.page.PageModel;

@Service("dbDcmxxxService")
public class DbDcmxxxServiceImpl implements DbDcmxxxService{
	private static final Log logger = LogFactory.getLog(DbDcmxxxServiceImpl.class);
	
	@Autowired
	DbDcmxxxDao dbDcmxxxDao;
	
	public PageModel<DbDcmxxxDTO> getDbDcmxxxPageList(
			DbDcmxxxDTO dbDcmxxxDTO, int currentPage, int pageSize) {
		DbDcmxxx dbDcmxxx = new DbDcmxxx();
		BeanUtils.copyProperties(dbDcmxxxDTO, dbDcmxxx);
		
		PageModel<DbDcmxxxDTO> pm = new PageModel<DbDcmxxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = dbDcmxxxDao.selectDbDcmxxxPageCount(dbDcmxxx);
		List<DbDcmxxx> dbDcmxxxList = dbDcmxxxDao.selectDbDcmxxxPageList(pm, dbDcmxxx);
		List<DbDcmxxxDTO> dbDcmxxxDTOList = new ArrayList<DbDcmxxxDTO>();
		for(DbDcmxxx _dbDcmxxx : dbDcmxxxList){
			DbDcmxxxDTO _dbDcmxxxDTO = new DbDcmxxxDTO();
			BeanUtils.copyProperties(_dbDcmxxx, _dbDcmxxxDTO);
			dbDcmxxxDTOList.add(_dbDcmxxxDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(dbDcmxxxDTOList);
		return pm;
	}

	public DbDcmxxxDTO getDbDcmxxxById(int id) {
		DbDcmxxx dbDcmxxx = dbDcmxxxDao.selectDbDcmxxxById(id);
		DbDcmxxxDTO dbDcmxxxDTO = new DbDcmxxxDTO();
		BeanUtils.copyProperties(dbDcmxxx, dbDcmxxxDTO);
		return dbDcmxxxDTO;
	}


	public int addDbDcmxxx(DbDcmxxxDTO dbDcmxxxDTO) {
		DbDcmxxx dbDcmxxx = new DbDcmxxx();
		BeanUtils.copyProperties(dbDcmxxxDTO, dbDcmxxx);
		dbDcmxxxDao.insertDbDcmxxx(dbDcmxxx);
		int dbDcmxxxId = dbDcmxxx.getId();
		return dbDcmxxxId;
	}

	public void editDbDcmxxx(DbDcmxxxDTO dbDcmxxxDTO) {
		DbDcmxxx dbDcmxxx = new DbDcmxxx();
		BeanUtils.copyProperties(dbDcmxxxDTO, dbDcmxxx);
		dbDcmxxxDao.updateDbDcmxxx(dbDcmxxx);		
	}

	public void deleteDbDcmxxxById(int id) {
		dbDcmxxxDao.deleteDbDcmxxxById(id);		
	}

}

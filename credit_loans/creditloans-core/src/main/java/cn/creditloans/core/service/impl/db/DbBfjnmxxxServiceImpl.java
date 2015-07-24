package cn.creditloans.core.service.impl.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.DbBfjnmxxxDao;
import cn.creditloans.core.dto.db.DbBfjnmxxxDTO;
import cn.creditloans.core.dto.db.DbZcmxxxDTO;
import cn.creditloans.core.entity.db.DbBfjnmxxx;
import cn.creditloans.core.entity.db.DbZcmxxx;
import cn.creditloans.core.service.DbBfjnmxxxService;
import cn.creditloans.tools.page.PageModel;

@Service("dbBfjnmxxxService")
public class DbBfjnmxxxServiceImpl implements DbBfjnmxxxService{
	private static final Log logger = LogFactory.getLog(DbBfjnmxxxServiceImpl.class);
	
	@Autowired
	DbBfjnmxxxDao dbBfjnmxxxDao;
	
	public PageModel<DbBfjnmxxxDTO> getDbBfjnmxxxPageList(
			DbBfjnmxxxDTO dbBfjnmxxxDTO, int currentPage, int pageSize) {
		DbBfjnmxxx dbBfjnmxxx = new DbBfjnmxxx();
		BeanUtils.copyProperties(dbBfjnmxxxDTO, dbBfjnmxxx);
		
		PageModel<DbBfjnmxxxDTO> pm = new PageModel<DbBfjnmxxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = dbBfjnmxxxDao.selectDbBfjnmxxxPageCount(dbBfjnmxxx);
		List<DbBfjnmxxx> dbBfjnmxxxList = dbBfjnmxxxDao.selectDbBfjnmxxxPageList(pm, dbBfjnmxxx);
		List<DbBfjnmxxxDTO> dbBfjnmxxxDTOList = new ArrayList<DbBfjnmxxxDTO>();
		for(DbBfjnmxxx _dbBfjnmxxx : dbBfjnmxxxList){
			DbBfjnmxxxDTO _dbBfjnmxxxDTO = new DbBfjnmxxxDTO();
			BeanUtils.copyProperties(_dbBfjnmxxx, _dbBfjnmxxxDTO);
			dbBfjnmxxxDTOList.add(_dbBfjnmxxxDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(dbBfjnmxxxDTOList);
		return pm;
	}

	public DbBfjnmxxxDTO getDbBfjnmxxxById(int id) {
		DbBfjnmxxx dbBfjnmxxx = dbBfjnmxxxDao.selectDbBfjnmxxxById(id);
		DbBfjnmxxxDTO dbBfjnmxxxDTO = new DbBfjnmxxxDTO();
		BeanUtils.copyProperties(dbBfjnmxxx, dbBfjnmxxxDTO);
		return dbBfjnmxxxDTO;
	}


	public int addDbBfjnmxxx(DbBfjnmxxxDTO dbBfjnmxxxDTO) {
		DbBfjnmxxx dbBfjnmxxx = new DbBfjnmxxx();
		BeanUtils.copyProperties(dbBfjnmxxxDTO, dbBfjnmxxx);
		dbBfjnmxxxDao.insertDbBfjnmxxx(dbBfjnmxxx);
		int dbBfjnmxxxId = dbBfjnmxxx.getId();
		return dbBfjnmxxxId;
	}

	public void editDbBfjnmxxx(DbBfjnmxxxDTO dbBfjnmxxxDTO) {
		DbBfjnmxxx dbBfjnmxxx = new DbBfjnmxxx();
		BeanUtils.copyProperties(dbBfjnmxxxDTO, dbBfjnmxxx);
		dbBfjnmxxxDao.updateDbBfjnmxxx(dbBfjnmxxx);		
	}

	public void deleteDbBfjnmxxxById(int id) {
		dbBfjnmxxxDao.deleteDbBfjnmxxxById(id);		
	}

}

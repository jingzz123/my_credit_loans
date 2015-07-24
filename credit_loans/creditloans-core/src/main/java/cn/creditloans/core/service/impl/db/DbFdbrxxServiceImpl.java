package cn.creditloans.core.service.impl.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.DbFdbrxxDao;
import cn.creditloans.core.dto.db.DbFdbrxxDTO;
import cn.creditloans.core.entity.db.DbFdbrxx;
import cn.creditloans.core.service.DbFdbrxxService;
import cn.creditloans.tools.page.PageModel;

@Service("dbFdbrxxService")
public class DbFdbrxxServiceImpl implements DbFdbrxxService{
	private static final Log logger = LogFactory.getLog(DbFdbrxxServiceImpl.class);
	
	@Autowired
	DbFdbrxxDao dbFdbrxxDao;
	
	public PageModel<DbFdbrxxDTO> getDbFdbrxxPageList(
			DbFdbrxxDTO dbFdbrxxDTO, int currentPage, int pageSize) {
		DbFdbrxx dbFdbrxx = new DbFdbrxx();
		BeanUtils.copyProperties(dbFdbrxxDTO, dbFdbrxx);
		
		PageModel<DbFdbrxxDTO> pm = new PageModel<DbFdbrxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = dbFdbrxxDao.selectDbFdbrxxPageCount(dbFdbrxx);
		List<DbFdbrxx> dbFdbrxxList = dbFdbrxxDao.selectDbFdbrxxPageList(pm, dbFdbrxx);
		List<DbFdbrxxDTO> dbFdbrxxDTOList = new ArrayList<DbFdbrxxDTO>();
		for(DbFdbrxx _dbFdbrxx : dbFdbrxxList){
			DbFdbrxxDTO _dbFdbrxxDTO = new DbFdbrxxDTO();
			BeanUtils.copyProperties(_dbFdbrxx, _dbFdbrxxDTO);
			dbFdbrxxDTOList.add(_dbFdbrxxDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(dbFdbrxxDTOList);
		return pm;
	}

	public DbFdbrxxDTO getDbFdbrxxById(int id) {
		DbFdbrxx dbFdbrxx = dbFdbrxxDao.selectDbFdbrxxById(id);
		DbFdbrxxDTO dbFdbrxxDTO = new DbFdbrxxDTO();
		BeanUtils.copyProperties(dbFdbrxx, dbFdbrxxDTO);
		return dbFdbrxxDTO;
	}


	public int addDbFdbrxx(DbFdbrxxDTO dbFdbrxxDTO) {
		DbFdbrxx dbFdbrxx = new DbFdbrxx();
		BeanUtils.copyProperties(dbFdbrxxDTO, dbFdbrxx);
		dbFdbrxxDao.insertDbFdbrxx(dbFdbrxx);
		int dbFdbrxxId = dbFdbrxx.getId();
		return dbFdbrxxId;
	}

	public void editDbFdbrxx(DbFdbrxxDTO dbFdbrxxDTO) {
		DbFdbrxx dbFdbrxx = new DbFdbrxx();
		BeanUtils.copyProperties(dbFdbrxxDTO, dbFdbrxx);
		dbFdbrxxDao.updateDbFdbrxx(dbFdbrxx);		
	}

	public void deleteDbFdbrxxById(int id) {
		dbFdbrxxDao.deleteDbFdbrxxById(id);		
	}

}

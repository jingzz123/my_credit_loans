package cn.creditloans.core.service.impl.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.DbBdbrxxDao;
import cn.creditloans.core.dto.db.DbBdbrxxDTO;
import cn.creditloans.core.entity.db.DbBdbrxx;
import cn.creditloans.core.service.DbBdbrxxService;
import cn.creditloans.tools.page.PageModel;

@Service("dbBdbrxxService")
public class DbBdbrxxServiceImpl implements DbBdbrxxService{
	private static final Log logger = LogFactory.getLog(DbBdbrxxServiceImpl.class);
	
	@Autowired
	DbBdbrxxDao dbBdbrxxDao;
	
	public PageModel<DbBdbrxxDTO> getDbBdbrxxPageList(
			DbBdbrxxDTO dbBdbrxxDTO, int currentPage, int pageSize) {
		DbBdbrxx dbBdbrxx = new DbBdbrxx();
		BeanUtils.copyProperties(dbBdbrxxDTO, dbBdbrxx);
		
		PageModel<DbBdbrxxDTO> pm = new PageModel<DbBdbrxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = dbBdbrxxDao.selectDbBdbrxxPageCount(dbBdbrxx);
		List<DbBdbrxx> dbBdbrxxList = dbBdbrxxDao.selectDbBdbrxxPageList(pm, dbBdbrxx);
		List<DbBdbrxxDTO> dbBdbrxxDTOList = new ArrayList<DbBdbrxxDTO>();
		for(DbBdbrxx _dbBdbrxx : dbBdbrxxList){
			DbBdbrxxDTO _dbBdbrxxDTO = new DbBdbrxxDTO();
			BeanUtils.copyProperties(_dbBdbrxx, _dbBdbrxxDTO);
			dbBdbrxxDTOList.add(_dbBdbrxxDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(dbBdbrxxDTOList);
		return pm;
	}

	public DbBdbrxxDTO getDbBdbrxxById(int id) {
		DbBdbrxx dbBdbrxx = dbBdbrxxDao.selectDbBdbrxxById(id);
		DbBdbrxxDTO bdbrxxDTO = new DbBdbrxxDTO();
		BeanUtils.copyProperties(dbBdbrxx, bdbrxxDTO);
		return bdbrxxDTO;
	}


	public int addDbBdbrxx(DbBdbrxxDTO dbBdbrxxDTO) {
		DbBdbrxx dbBdbrxx = new DbBdbrxx();
		BeanUtils.copyProperties(dbBdbrxxDTO, dbBdbrxx);
		dbBdbrxxDao.insertDbBdbrxx(dbBdbrxx);
		int dbBdbrxxId = dbBdbrxx.getId();
		return dbBdbrxxId;
	}

	public void editDbBdbrxx(DbBdbrxxDTO dbBdbrxxDTO) {
		DbBdbrxx dbBdbrxx = new DbBdbrxx();
		BeanUtils.copyProperties(dbBdbrxxDTO, dbBdbrxx);
		dbBdbrxxDao.updateDbBdbrxx(dbBdbrxx);		
	}

	public void deleteDbBdbrxxById(int id) {
		dbBdbrxxDao.deleteDbBdbrxxById(id);		
	}

}

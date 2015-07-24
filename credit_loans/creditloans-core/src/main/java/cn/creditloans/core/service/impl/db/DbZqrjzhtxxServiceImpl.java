package cn.creditloans.core.service.impl.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.DbZqrjzhtxxDao;
import cn.creditloans.core.dto.db.DbZqrjzhtxxDTO;
import cn.creditloans.core.entity.db.DbZqrjzhtxx;
import cn.creditloans.core.service.DbZqrjzhtxxService;
import cn.creditloans.tools.page.PageModel;

@Service("dbZqrjzhtxxService")
public class DbZqrjzhtxxServiceImpl implements DbZqrjzhtxxService{
	private static final Log logger = LogFactory.getLog(DbZqrjzhtxxServiceImpl.class);
	
	@Autowired
	DbZqrjzhtxxDao dbZqrjzhtxxDao;
	
	public PageModel<DbZqrjzhtxxDTO> getDbZqrjzhtxxPageList(
			DbZqrjzhtxxDTO dbZqrjzhtxxDTO, int currentPage, int pageSize) {
		DbZqrjzhtxx dbZqrjzhtxx = new DbZqrjzhtxx();
		BeanUtils.copyProperties(dbZqrjzhtxxDTO, dbZqrjzhtxx);
		
		PageModel<DbZqrjzhtxxDTO> pm = new PageModel<DbZqrjzhtxxDTO>();
		pm.init(currentPage, pageSize);
		
		int count = dbZqrjzhtxxDao.selectDbZqrjzhtxxPageCount(dbZqrjzhtxx);
		List<DbZqrjzhtxx> dbZqrjzhtxxList = dbZqrjzhtxxDao.selectDbZqrjzhtxxPageList(pm, dbZqrjzhtxx);
		List<DbZqrjzhtxxDTO> dbZqrjzhtxxDTOList = new ArrayList<DbZqrjzhtxxDTO>();
		for(DbZqrjzhtxx _dbZqrjzhtxx : dbZqrjzhtxxList){
			DbZqrjzhtxxDTO _dbZqrjzhtxxDTO = new DbZqrjzhtxxDTO();
			BeanUtils.copyProperties(_dbZqrjzhtxx, _dbZqrjzhtxxDTO);
			dbZqrjzhtxxDTOList.add(_dbZqrjzhtxxDTO);
		}
		
		pm.setTotal(count);
		pm.setDatas(dbZqrjzhtxxDTOList);
		return pm;
	}

	public DbZqrjzhtxxDTO getDbZqrjzhtxxById(int id) {
		DbZqrjzhtxx dbZqrjzhtxx = dbZqrjzhtxxDao.selectDbZqrjzhtxxById(id);
		DbZqrjzhtxxDTO dbzqrjzhtxxDTO = new DbZqrjzhtxxDTO();
		BeanUtils.copyProperties(dbZqrjzhtxx, dbzqrjzhtxxDTO);
		return dbzqrjzhtxxDTO;
	}


	public int addDbZqrjzhtxx(DbZqrjzhtxxDTO dbZqrjzhtxxDTO) {
		DbZqrjzhtxx dbZqrjzhtxx = new DbZqrjzhtxx();
		BeanUtils.copyProperties(dbZqrjzhtxxDTO, dbZqrjzhtxx);
		dbZqrjzhtxxDao.insertDbZqrjzhtxx(dbZqrjzhtxx);
		int dbZqrjzhtxxId = dbZqrjzhtxx.getId();
		return dbZqrjzhtxxId;
	}

	public void editDbZqrjzhtxx(DbZqrjzhtxxDTO dbZqrjzhtxxDTO) {
		DbZqrjzhtxx dbZqrjzhtxx = new DbZqrjzhtxx();
		BeanUtils.copyProperties(dbZqrjzhtxxDTO, dbZqrjzhtxx);
		dbZqrjzhtxxDao.updateDbZqrjzhtxx(dbZqrjzhtxx);		
	}

	public void deleteDbZqrjzhtxxById(int id) {
		dbZqrjzhtxxDao.deleteDbZqrjzhtxxById(id);		
	}

}

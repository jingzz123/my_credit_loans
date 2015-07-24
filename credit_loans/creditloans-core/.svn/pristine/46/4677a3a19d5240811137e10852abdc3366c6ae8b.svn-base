package cn.creditloans.core.dao.share;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import cn.creditloans.tools.page.PageModel;

/**
 * 提供一些公用的方法，后续的方法再变更
 * @author Ash
 *
 */
public interface AbstractEntityDao<T> {
	
	/**
	 * 单个插入
	 */
	public void insert(T entity);
	
	/**
	 * 单个修改
	 */
	public void update(T entity);
	
	/**
	 * 根据ID删除单个
	 */
	public void delete(int id);
	
	/**
	 * 获取单个实体信息
	 * @param id
	 * @return
	 */
	public T select(int id);

	/**
	 * 获取所有的实体
	 * @return
	 */
	public List<T> selectAllInfos();
	
	/**
	 * 分页查询
	 * @param pm
	 * @return
	 */
	public List<T> selectPageList(@Param("entity") T entity, @Param("pm") PageModel<?> pm);
	
	/**
	 * 得到所有数据的数量
	 * @return
	 */
	public int selectPageCount(T entity);
	/**
	 * 
	* 检查名字是否 唯一
	* @param 
	* @return
	 */
	public int selectNameIsExit(String name);
	
	/**
	 * 
	* 得到最大sequence
	* @param 
	* @return
	 */
	Integer getMaxSequence(Map<String, Object> param);
	
}

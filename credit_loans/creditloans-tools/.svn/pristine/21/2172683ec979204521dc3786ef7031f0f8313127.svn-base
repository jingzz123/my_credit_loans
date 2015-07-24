package cn.creditloans.tools.sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;

import cn.creditloans.tools.util.ApplicationContextUtil;

public class SequenceGenerator {
	
	private static final Log logger = LogFactory.getLog(SequenceGenerator.class);
	
	private static Object lock = new Object();
	
	private static SequenceGenerator instance = new SequenceGenerator();
	
	private SequenceGenerator() {}
	
	public static SequenceGenerator getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new SequenceGenerator();
				}
			}
		}
		
		return instance;
	}

	public int getSequence(String sequenceName) {
		int sequenceValue = 0;
		Statement statement = null;
		ResultSet rs = null;
		
		SqlSession sqlSession = null;
		try {
			SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ApplicationContextUtil.getApplicationContext().getBean("sqlSessionFactory");
			sqlSession = sqlSessionFactory.openSession();
			
			Connection connection = sqlSession.getConnection();
			
			statement = connection.createStatement();
			//enterprise_code_seq
			rs = statement.executeQuery("select nextval('"+sequenceName+"')");
			if(rs.next()){
				sequenceValue = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (BeansException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			sqlSession.close();
		}
		
		return sequenceValue;
	}
}

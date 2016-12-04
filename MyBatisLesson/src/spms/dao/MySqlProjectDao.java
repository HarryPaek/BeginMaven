/**
 * 
 */
package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.abstracts.IProjectDao;
import spms.annotation.Component;
import spms.vo.Project;

/**
 * @author HarryPaek
 *
 */
@Component("projectDao")
public class MySqlProjectDao implements IProjectDao {
    SqlSessionFactory sqlSessionFactory;
    
	/**
	 * @param sqlSessionFactory the sqlSessionFactory to set
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/* (non-Javadoc)
	 * @see spms.abstracts.IProjectDao#selectList()
	 */
	@Override
	public List<Project> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	return sqlSession.selectList("spms.dao.ProjectDao.selectList");
		}
	    finally {
	    	sqlSession.close();
		}
	}

	@Override
	public int insert(Project project) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	int count = sqlSession.insert("spms.dao.ProjectDao.insert", project);
	    	sqlSession.commit();
	    	
	    	return count;
		}
	    finally {
	    	sqlSession.close();
		}
	}

	@Override
	public Project select(int no) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	return sqlSession.selectOne("spms.dao.ProjectDao.select", no);
		}
	    finally {
	    	sqlSession.close();
		}
	}

	@Override
	public int update(Project project) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	int count = sqlSession.update("spms.dao.ProjectDao.update", project);
	    	sqlSession.commit();
	    	
	    	return count;
		}
	    finally {
	    	sqlSession.close();
		}
	}

	@Override
	public int delete(int no) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	int count = sqlSession.delete("spms.dao.ProjectDao.delete", no);
	    	sqlSession.commit();
	    	
	    	return count;
		}
	    finally {
	    	sqlSession.close();
		}
	}
}

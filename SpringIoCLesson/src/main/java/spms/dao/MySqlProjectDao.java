/**
 * 
 */
package spms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.abstracts.IProjectDao;
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
	@Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/* (non-Javadoc)
	 * @see spms.abstracts.IProjectDao#selectList()
	 */
	@Override
	public List<Project> selectList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	return sqlSession.selectList("spms.dao.ProjectDao.selectList", paramMap);
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
	    	Project original = sqlSession.selectOne("spms.dao.ProjectDao.select", project.getNo());
	    	
	    	HashMap<String, Object> paramMap = new HashMap<String, Object>();
	    	
	    	if(!project.getTitle().equals(original.getTitle()))
	    		paramMap.put("title", project.getTitle());
	    	
	    	if(!project.getContent().equals(original.getContent()))
	    		paramMap.put("content", project.getContent());
	    	
	    	if(project.getStartDate().compareTo(original.getStartDate()) != 0)
	    		paramMap.put("startDate", project.getStartDate());
	    	
	    	if(project.getEndDate().compareTo(original.getEndDate()) != 0)
	    		paramMap.put("endDate", project.getEndDate());
	    	
	    	if(project.getState() != original.getState())
	    		paramMap.put("state", project.getState());
	    	
	    	if(!project.getTags().equals(original.getTags()))
	    		paramMap.put("tags", project.getTags());
	    	
	    	int count = 0;
	    	if(paramMap.size() > 0) {
	    		paramMap.put("no", project.getNo());
	    		count = sqlSession.update("spms.dao.ProjectDao.update", paramMap);
	    		sqlSession.commit();
	    	}
	    	
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

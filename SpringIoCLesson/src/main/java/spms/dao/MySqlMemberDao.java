package spms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spms.abstracts.IMemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
@Component("memberDao")
public class MySqlMemberDao implements IMemberDao {
    SqlSessionFactory sqlSessionFactory;
    
	/**
	 * @param sqlSessionFactory the sqlSessionFactory to set
	 */
	@Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
    
    public List<Member> selectList(HashMap<String, Object> paramMap) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	return sqlSession.selectList("spms.dao.MemberDao.selectList", paramMap);
		}
	    finally {
	    	sqlSession.close();
		}
    }
    
    public int insert(Member member) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	int count = sqlSession.insert("spms.dao.MemberDao.insert", member);
	    	sqlSession.commit();
	    	
	    	return count;
		}
	    finally {
	    	sqlSession.close();
		}
    }
    
    public Member select(int no) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	return sqlSession.selectOne("spms.dao.MemberDao.select", no);
		}
	    finally {
	    	sqlSession.close();
		}
    }
    
    public int update(Member member) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	Member original = sqlSession.selectOne("spms.dao.MemberDao.select", member.getNo());
	    	
	    	HashMap<String, Object> paramMap = new HashMap<String, Object>();
	    	
	    	if(!member.getName().equals(original.getName()))
	    		paramMap.put("name", member.getName());
	    	
	    	if(!member.getEmail().equals(original.getEmail()))
	    		paramMap.put("email", member.getEmail());
	    	
	    	int count = 0;
	    	if(paramMap.size() > 0) {
	    		paramMap.put("no", member.getNo());
	    		count = sqlSession.update("spms.dao.MemberDao.update", paramMap);
	    		sqlSession.commit();
	    	}
	    	
	    	return count;
		}
	    finally {
	    	sqlSession.close();
		}
    }
    
    public int delete(int no) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	int count = sqlSession.delete("spms.dao.MemberDao.delete", no);
	    	sqlSession.commit();
	    	
	    	return count;
		}
	    finally {
	    	sqlSession.close();
		}
    }
    
    public Member exist(String email, String password) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
		
	    try {
	    	Member member = new Member().setEmail(email).setPassword(password);
	    	return sqlSession.selectOne("spms.dao.MemberDao.exist", member);
		}
	    finally {
	    	sqlSession.close();
		}
    }
}


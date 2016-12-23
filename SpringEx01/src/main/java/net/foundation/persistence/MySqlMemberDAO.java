/**
 * 
 */
package net.foundation.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.foundation.abstracts.IMemberDAO;
import net.foundation.domain.MemberVO;

/**
 * @author HarryPaek
 *
 */
@Repository
public class MySqlMemberDAO implements IMemberDAO {
	private static final Logger logger = LoggerFactory.getLogger(MySqlMemberDAO.class);

	@Inject
	private SqlSession sqlSession;
	
	/* (non-Javadoc)
	 * @see net.foundation.persistence.IMemberDAO#getTime()
	 */
	@Override
	public String getTime() {
		logger.info("getTime() ................");
		return sqlSession.selectOne(getMapper("getTime"));
	}

	/* (non-Javadoc)
	 * @see net.foundation.persistence.IMemberDAO#insertMember(net.foundation.domain.MemberVO)
	 */
	@Override
	public void insertMember(MemberVO vo) {
		logger.info("insertMember() ................");
		sqlSession.insert(getMapper("insertMember"), vo);
	}
	
	@Override
	public MemberVO readMember(String userid) throws Exception {
		logger.info("readMember() ................");
		return sqlSession.selectOne(getMapper("readMember"), userid);
	}

	@Override
	public MemberVO readWithPW(String userid, String userpw) throws Exception {
		logger.info("readWithPW() ................");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return sqlSession.selectOne(getMapper("readWithPW"), paramMap);
	}
	
	private String getMapper(String mapping)
	{
		String namesapce = "net.foundation.mapper.MemberDAO";
				
		return String.format("%s.%s", namesapce, mapping);
	}

}

/**
 * 
 */
package net.foundation.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.foundation.abstracts.IReplyDAO;
import net.foundation.domain.Criteria;
import net.foundation.domain.ReplyVO;

/**
 * @author HarryPaek
 *
 */
@Repository
public class MySqlReplyDAO implements IReplyDAO {
	private static final Logger logger = LoggerFactory.getLogger(MySqlReplyDAO.class);
	
	@Inject
	private SqlSession sqlSession;

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyDAO#list(int)
	 */
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		logger.info("list() ................");
		
		return sqlSession.selectList(getMapper("list"), bno);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyDAO#create(net.foundation.domain.ReplyVO)
	 */
	@Override
	public void create(ReplyVO vo) throws Exception {
		logger.info("create() ................");
		
		sqlSession.insert(getMapper("create"), vo);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyDAO#update(net.foundation.domain.ReplyVO)
	 */
	@Override
	public void update(ReplyVO vo) throws Exception {
		logger.info("update() ................");
		
		sqlSession.update(getMapper("update"), vo);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyDAO#delete(int)
	 */
	@Override
	public void delete(int rno) throws Exception {
		logger.info("delete() ................");
		
		sqlSession.delete(getMapper("delete"), rno);
	}
	
	@Override
	public List<ReplyVO> listPage(int bno, Criteria criteria) throws Exception {
		logger.info("listPage() ................");
		logger.info(criteria.toString());
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("criteria", criteria);
		
		return sqlSession.selectList(getMapper("listPage"), paramMap);
	}

	@Override
	public int count(int bno) throws Exception {
		logger.info("count() ................");
		
		return sqlSession.selectOne(getMapper("count"), bno);
	}
	
	private String getMapper(String mapping)
	{
		String namesapce = "net.foundation.mapper.ReplyDAO";
				
		return String.format("%s.%s", namesapce, mapping);
	}


}

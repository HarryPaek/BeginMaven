/**
 * 
 */
package net.foundation.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.foundation.abstracts.IBoardDAO;
import net.foundation.domain.BoardVO;
import net.foundation.domain.Criteria;

/**
 * @author HarryPaek
 *
 */
@Repository
public class MySqlBoardDAO implements IBoardDAO {
	private static final Logger logger = LoggerFactory.getLogger(MySqlBoardDAO.class);

	@Inject
	private SqlSession sqlSession;
	
	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardDAO#create(net.foundation.domain.BoardVO)
	 */
	@Override
	public void create(BoardVO vo) throws Exception {
		logger.info("create() ................");
		sqlSession.insert(getMapper("create"), vo);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardDAO#read(int)
	 */
	@Override
	public BoardVO read(int bno) throws Exception {
		logger.info("read() ................");
		return sqlSession.selectOne(getMapper("read"), bno);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardDAO#update(net.foundation.domain.BoardVO)
	 */
	@Override
	public void update(BoardVO vo) throws Exception {
		logger.info("update() ................");
		sqlSession.insert(getMapper("update"), vo);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardDAO#delete(int)
	 */
	@Override
	public void delete(int bno) throws Exception {
		logger.info("delete() ................");
		sqlSession.delete(getMapper("delete"), bno);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardDAO#listAll()
	 */
	@Override
	public List<BoardVO> listAll() throws Exception {
		logger.info("listAll() ................");
		return sqlSession.selectList(getMapper("listAll"));
	}
	
	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		logger.info("listPage() ................");
		
		if(page <= 0)
			page = 1;
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(getMapper("listPage"), page);
	}
	
	@Override
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception {
		logger.info("listCriteria() ................");
		
		return sqlSession.selectList(getMapper("listCriteria"), criteria);
	}
	
	@Override
	public int getTotalCount() throws Exception {
        logger.info("getTotalCount() ................");
		
		return sqlSession.selectOne(getMapper("getTotalCount"));
	}

	private String getMapper(String mapping)
	{
		String namesapce = "net.foundation.mapper.BoardDAO";
				
		return String.format("%s.%s", namesapce, mapping);
	}

}

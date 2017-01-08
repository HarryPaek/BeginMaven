/**
 * 
 */
package net.foundation.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import net.foundation.abstracts.IBoardDAO;
import net.foundation.abstracts.IBoardService;
import net.foundation.domain.BoardVO;
import net.foundation.domain.Criteria;
import net.foundation.domain.SearchCriteria;

/**
 * @author HarryPaek
 *
 */
@Service
public class MySqlBoardService implements IBoardService {
	private static final Logger logger = LoggerFactory.getLogger(MySqlBoardService.class);
	
	@Inject
	private IBoardDAO dao;

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardService#regist(net.foundation.domain.BoardVO)
	 */
	@Transactional
	@Override
	public void register(BoardVO vo) throws Exception {
		logger.info("register() ................");
		
		dao.create(vo);
		String[] files = vo.getFiles();
		
		if(files == null || files.length == 0)
			return;
		
		int bno = vo.getBno();
		for (String fileName : files) {
			dao.addAttach(bno, fileName);
		}
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardService#read(int)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(int bno) throws Exception {
		logger.info("read() ................");
		
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardService#modify(net.foundation.domain.BoardVO)
	 */
	@Transactional
	@Override
	public void modify(BoardVO vo) throws Exception {
		logger.info("modify() ................");
		logger.info(String.format("BoardVO: %s", vo));
		
		int bno = vo.getBno();

		dao.update(vo);
		dao.deleteAttach(bno);
		
		String[] files = vo.getFiles();
		
		if(files == null || files.length == 0)
			return;

		for (String fileName : files) {
			dao.addAttach(bno, fileName);
		}
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardService#remove(int)
	 */
	@Transactional
	@Override
	public void remove(int bno) throws Exception {
		logger.info("remove() ................");
		
		dao.deleteAttach(bno);
		dao.delete(bno);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IBoardService#listAll()
	 */
	@Override
	public List<BoardVO> listAll() throws Exception {
		logger.info("listAll() ................");
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception {
		logger.info("listCriteria() ................");
		
		return dao.listCriteria(criteria);
	}

	@Override
	public int getTotalCount() throws Exception {
		logger.info("getTotalCount() ................");
		
		return dao.getTotalCount();
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria criteria) throws Exception {
        logger.info("listSearchCriteria() ................");
		
		return dao.listSearch(criteria);
	}

	@Override
	public int getTotalCount(SearchCriteria criteria) throws Exception {
        logger.info("getTotalCount() with SearchCriteria ................");
		
		return dao.getTotalCountSearch(criteria);
	}

	@Override
	public List<String> getAttach(int bno) throws Exception {
        logger.info("getAttach() ................");
		
		return dao.getAttach(bno);
	}

}

/**
 * 
 */
package net.foundation.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.foundation.abstracts.IBoardDAO;
import net.foundation.domain.BoardVO;
import net.foundation.domain.Criteria;

/**
 * @author HarryPaek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private IBoardDAO dao;
	
	@Test
	public void test01Create() throws Exception {
		logger.info("test01Create() ................");
		
		BoardVO vo = new BoardVO();
		vo.setTitle("새로운 글 제목");
		vo.setContent("새로운 글을 넣습니다.");
		vo.setWriter("user00");
		
		dao.create(vo);
	}
	
	@Test
	public void test02Read() throws Exception {
		logger.info("test02Read() ................");
		
		logger.info(dao.read(3).toString());
	}
	
	@Test
	public void test03Update() throws Exception {
		logger.info("test03Update() ................");
		
		BoardVO vo = new BoardVO();
		vo.setBno(3);
		vo.setTitle("수정된 글 제목");
		vo.setContent("수정된 글을 넣습니다.");
		
		dao.update(vo);
	}
	
	@Test
	public void test04Delete() throws Exception {
		logger.info("test04Delete() ................");
		
		dao.delete(3);
	}
	
	@Test
	public void test05ListPage() throws Exception {
        logger.info("test05ListPage() ................");
        int page = 3;
        
        List<BoardVO> list = dao.listPage(page);
        
		for (BoardVO vo : list) {
			logger.info(String.format("%8d: %s", vo.getBno(), vo.getTitle()));
		}
	}
	
	@Test
	public void test06ListCriteria() throws Exception {
        logger.info("test06ListCriteria() ................");
        
        Criteria criteria = new Criteria();
        criteria.setPage(3);
        criteria.setPerPageCount(20);
        
        List<BoardVO> list = dao.listCriteria(criteria);
        
		for (BoardVO vo : list) {
			logger.info(String.format("%8d: %s", vo.getBno(), vo.getTitle()));
		}
	}
	
	@Test
	public void test07URI() throws Exception {
        logger.info("test07URI() ................");
        
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
        		.path("/board/read")
        		.queryParam("bno", 12)
        		.queryParam("perPageCount", 20)
        		.build();
        
        logger.info("/board/read?bno=12&perPageCount=20");
        logger.info(uriComponents.toString());
	}
	
	@Test
	public void test08URI2() throws Exception {
        logger.info("test08URI2() ................");
        
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
        		.path("/{module}/{page}")
        		.queryParam("bno", 12)
        		.queryParam("perPageCount", 20)
        		.build()
        		.expand("board", "read")
        		.encode();
        
        logger.info("/board/read?bno=12&perPageCount=20");
        logger.info(uriComponents.toString());
	}

}

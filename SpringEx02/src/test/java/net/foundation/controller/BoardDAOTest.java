/**
 * 
 */
package net.foundation.controller;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.foundation.abstracts.IBoardDAO;
import net.foundation.domain.BoardVO;

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
}

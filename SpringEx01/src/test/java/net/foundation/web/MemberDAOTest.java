/**
 * 
 */
package net.foundation.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.foundation.abstracts.IMemberDAO;
import net.foundation.domain.MemberVO;

/**
 * @author HarryPaek
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	@Inject
	private IMemberDAO dao;
	
	@Test
	public void testTime() throws Exception {
		logger.info("testTime ................");
		
		System.out.println(dao.getTime());
	}
	
	@Test
	public void testInsertMember() throws Exception {
		logger.info("testInsertMember ................");
		
		MemberVO vo = new MemberVO();
		vo.setUserid("user10");
		vo.setUserpw("user10");
		vo.setUsername("USER10");
		vo.setEmail("user10@example.com");
		
		dao.insertMember(vo);
	}
}

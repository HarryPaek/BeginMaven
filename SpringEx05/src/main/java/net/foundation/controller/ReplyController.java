/**
 * 
 */
package net.foundation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.foundation.abstracts.IReplyService;
import net.foundation.domain.Criteria;
import net.foundation.domain.PageMaker;
import net.foundation.domain.ReplyVO;

/**
 * @author HarryPaek
 *
 */
@RestController
@RequestMapping("/replies")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	IReplyService service;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
		logger.info("register reply .............");
		logger.info(vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.addReply(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno) {
		logger.info("list replies .............");
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		logger.info("list replies with Paging .............");
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria criteria = new Criteria();
			criteria.setPage(page);
			criteria.setPerPageCount(5);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCriteria(criteria);
			pageMaker.setTotalCount(service.getTotalCount(bno));
			
			Map<String, Object> result = new HashMap<>();
			List<ReplyVO> list = service.listReplyPage(bno, criteria);
			
			result.put("list", list);
			result.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}

	
	@RequestMapping(value="/{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo) {
		logger.info("update reply .............");
		logger.info(vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno);
			service.modifyReply(vo);
			
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") int rno) {
		logger.info("remove reply .............");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.removeReply(rno);
			
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}


}

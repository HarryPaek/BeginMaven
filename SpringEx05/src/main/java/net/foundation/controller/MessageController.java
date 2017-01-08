/**
 * 
 */
package net.foundation.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.foundation.abstracts.IMessageService;
import net.foundation.domain.MessageVO;

/**
 * @author HarryPaek
 *
 */
@RestController
@RequestMapping("/messages")
public class MessageController {
	
	@Inject
	private IMessageService service;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.addMessage(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}

}

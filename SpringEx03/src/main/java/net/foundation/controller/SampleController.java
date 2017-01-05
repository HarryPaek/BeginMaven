/**
 * 
 */
package net.foundation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.foundation.domain.SampleVO;

/**
 * @author HarryPaek
 *
 */
@RestController
@RequestMapping("/sample")
public class SampleController {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		SampleVO vo = new SampleVO();
		
		vo.setMno(135);
		vo.setFirstName("길동");
		vo.setLastName("홍");
		
		return vo;
	}
	
	@RequestMapping("/sendList")
	public List<SampleVO> sendList() {
		List<SampleVO> list = new ArrayList<>();
		
		for (int i = 100; i < 110; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("길동");
			vo.setLastName("홍");
			
			list.add(vo);
		}
		
		return list;
	}
	
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap() {
		Map<Integer, SampleVO> map = new HashMap<>();
		
		for (int i = 201; i < 223; i=i+2) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("길동");
			vo.setLastName("홍");
			
			map.put(vo.getMno(), vo);
		}
		
		return map;
	}
	
	@RequestMapping("sendErrorAuth")
	public ResponseEntity<Void> sendListAuth() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot() {
		List<SampleVO> list = new ArrayList<>();
		
		for (int i = 170; i < 180; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("길동");
			vo.setLastName("홍");
			
			list.add(vo);
		}
		
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}

}

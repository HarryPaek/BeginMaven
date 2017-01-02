/**
 * 
 */
package net.foundation.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.foundation.abstracts.IBoardService;
import net.foundation.domain.BoardVO;

/**
 * @author HarryPaek
 *
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private IBoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register(BoardVO vo, Model model) throws Exception {
		
		logger.info("register get .............");
	}

/*
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, Model model) throws Exception {
		
		logger.info("register post .............");
		logger.info(vo.toString());
		
		service.register(vo);
		model.addAttribute("result", "success");
		
		//return "/board/success";
		return "redirect:/board/listAll";
	}
*/

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO vo, RedirectAttributes reAttr) throws Exception {
		
		logger.info("register post .............");
		logger.info(vo.toString());
		
		service.register(vo);
		
		reAttr.addFlashAttribute("message", "success");
		
		//return "/board/success";
		return "redirect:/board/listAll";
	}

	@RequestMapping(value="/listAll", method = RequestMethod.GET)
	public void listALL(Model model) throws Exception {
		
		logger.info("show all list .............");
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("read a board object .............");
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(int bno, RedirectAttributes reAttr) throws Exception {
		logger.info("remove a board object .............");
		
		service.remove(bno);
		reAttr.addFlashAttribute("message", "success");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(int bno, Model model) throws Exception {
		logger.info("modify get .............");
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO vo, RedirectAttributes reAttr) throws Exception {
		logger.info("modify post .............");
		
		service.modify(vo);
        reAttr.addFlashAttribute("message", "success");
		
		return "redirect:/board/listAll";
	}

}

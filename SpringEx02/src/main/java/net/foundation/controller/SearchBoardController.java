/**
 * 
 */
package net.foundation.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.foundation.abstracts.IBoardService;
import net.foundation.domain.BoardVO;
import net.foundation.domain.SearchCriteria;
import net.foundation.domain.SearchPageMaker;

/**
 * @author HarryPaek
 *
 */
@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private IBoardService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register(BoardVO vo, @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {
		
		logger.info("SearchBoard, register get .............");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(BoardVO vo, SearchCriteria criteria, RedirectAttributes reAttr) throws Exception {
		
		logger.info("SearchBoard, register post .............");
		logger.info(vo.toString());
		
		service.register(vo);
		
		reAttr.addAttribute("perPageCount", criteria.getPerPageCount());
		reAttr.addFlashAttribute("message", "success");
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {
		logger.info("SearchBoard, readPage a board object .............");
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(int bno, @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {
		logger.info("SearchBoard, modify get .............");
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO vo, SearchCriteria criteria, RedirectAttributes reAttr) throws Exception {
		logger.info("SearchBoard, modify post .............");
		
		service.modify(vo);
		
		reAttr.addAttribute("page", criteria.getPage());
		reAttr.addAttribute("perPageCount", criteria.getPerPageCount());
		reAttr.addAttribute("searchType", criteria.getSearchType());
		reAttr.addAttribute("keyword", criteria.getKeyword());
        reAttr.addFlashAttribute("message", "success");
		
		return "redirect:/sboard/list";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(int bno, SearchCriteria criteria, RedirectAttributes reAttr) throws Exception {
		logger.info("SearchBoard, remove a board object .............");
		
		service.remove(bno);
		
		reAttr.addAttribute("page", criteria.getPage());
		reAttr.addAttribute("perPageCount", criteria.getPerPageCount());
		reAttr.addAttribute("searchType", criteria.getSearchType());
		reAttr.addAttribute("keyword", criteria.getKeyword());
		reAttr.addFlashAttribute("message", "success");
		
		return "redirect:/sboard/list";
	}


	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void listPage(SearchCriteria criteria, Model model) throws Exception {
		
		logger.info("SearchBoard, show list with Paging .............");
		logger.info(criteria.toString());
		
		model.addAttribute("list", service.listSearchCriteria(criteria));

		SearchPageMaker pageMaker = new SearchPageMaker();
		pageMaker.setSearchCriteria(criteria);
		pageMaker.setTotalCount(service.getTotalCount(criteria));
		
		model.addAttribute("pageMaker", pageMaker);
	}

}

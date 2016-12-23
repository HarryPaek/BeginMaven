/**
 * 
 */
package net.foundation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.foundation.domain.ProductVO;

/**
 * @author HarryPaek
 *
 */
@Controller
public class SampleController3 {
	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
    @RequestMapping("doD")
	public String doD(Model model) {
    	logger.info("doD called ...................");
    	
    	ProductVO product = new ProductVO("Sample Product", 10000);
    	model.addAttribute(product);
    	
    	return "productDetail";
    }
}

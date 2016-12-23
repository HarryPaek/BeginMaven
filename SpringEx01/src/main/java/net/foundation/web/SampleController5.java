/**
 * 
 */
package net.foundation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.foundation.domain.ProductVO;

/**
 * @author HarryPaek
 *
 */
@Controller
public class SampleController5 {
	private static final Logger logger = LoggerFactory.getLogger(SampleController5.class);
	
    @RequestMapping("doJSON")
	public @ResponseBody ProductVO doJSON() {
    	logger.info("doJSON called ...................");
    	
    	ProductVO product = new ProductVO("샘플 제품", 70000);
    	
    	return product;
    }
}

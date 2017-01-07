/**
 * 
 */
package net.foundation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HarryPaek
 *
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public static String getFileFormatName(String fileName) {
		logger.info("getFileFormatName() ............");
		
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
}

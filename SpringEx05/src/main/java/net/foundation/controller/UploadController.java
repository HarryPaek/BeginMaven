/**
 * 
 */
package net.foundation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.foundation.util.FileUtils;
import net.foundation.util.MimeMediaUtils;
import net.foundation.util.UploadFileUtils;

/**
 * @author HarryPaek
 *
 */
@Controller
@RequestMapping("/files")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		logger.info("uploadForm() GET ............");
	}
	
	@RequestMapping(value="/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartFile file, Model model) throws Exception {
		logger.info("uploadForm() POST ............");
		
		logger.info(String.format("Original File Name: %s", file.getOriginalFilename()));
		logger.info(String.format("File Size: %,10d", file.getSize()));
		logger.info(String.format("Content Type: %s", file.getContentType()));
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("savedName", savedName);
		
		return "/files/uploadResult";
	}
	
	@RequestMapping(value="/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {
		logger.info("uploadAjax() GET ............");
	}
	
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public @ResponseBody ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		logger.info("uploadAjax() POST ............");
		
		logger.info(String.format("Original File Name: %s", file.getOriginalFilename()));
		logger.info(String.format("File Size: %,10d", file.getSize()));
		logger.info(String.format("Content Type: %s", file.getContentType()));
		
		String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());

		logger.info(String.format("Saved File Name = [%s]", savedName));
		
		return new ResponseEntity<>(savedName, HttpStatus.CREATED);
	}
	
	@RequestMapping("/displayFile")
	public @ResponseBody ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		logger.info("displayFile() ............");
		logger.info(String.format("File Name = [%s]", fileName));

		ResponseEntity<byte[]> entity = null;

		try (InputStream ins = new FileInputStream(new File(uploadPath, fileName))) {
			String fileFormatName = FileUtils.getFileFormatName(fileName);
			MediaType mediaType = MimeMediaUtils.getMediaType(fileFormatName);
			HttpHeaders headers = new HttpHeaders();
			
			if(mediaType != null)
				headers.setContentType(mediaType);
			else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "ISO-8859-1")));
			}
			
			entity = new ResponseEntity<>(IOUtils.toByteArray(ins), headers, HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/deleteFile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteFile(String fileName) throws Exception {
		logger.info("deleteFile() POST ............");
		logger.info(String.format("Delete File Name: %s", fileName));

		ResponseEntity<String> entity = null;
		String savedFilePath = fileName.replace('/', File.separatorChar);
		String fileFormatName = FileUtils.getFileFormatName(fileName);
		MediaType mediaType = MimeMediaUtils.getMediaType(fileFormatName);
		
		logger.info(String.format("Saved File Path: %s", savedFilePath));
		
        try {
    		if(mediaType != null) {
    			String front = savedFilePath.substring(0,12);
    			String end = savedFilePath.substring(14);
    			String originalImageFilePath = String.format("%s%s", front, end);
    			
    			logger.info(String.format("Original Image File Path: %s", originalImageFilePath));
    			
    			new File(uploadPath, originalImageFilePath).delete();
    		}
    		
    		new File(uploadPath, savedFilePath).delete();
			
    		entity = new ResponseEntity<>("deleted", HttpStatus.OK);
		}
        catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
        return entity;
	}


	private String uploadFile(String originalFilename, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID();
		String savedName = String.format("%s_%s", uuid, originalFilename);
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
	}
}

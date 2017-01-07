package net.foundation.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
		logger.info("uploadFile() ............");
		
		UUID uuid = UUID.randomUUID();
		String savedFileName = String.format("%s_%s", uuid, originalFileName);
		String datePath = calcPath(uploadPath);
		String fileFormatName = FileUtils.getFileFormatName(originalFileName);
		File targetFullPath = new File(new File(uploadPath, datePath), savedFileName);
		
		logger.info(String.format("targetFullPath = [%s]", targetFullPath));
		
		FileCopyUtils.copy(fileData, targetFullPath);
		
		String uploadedFileName = null;
		
		if(MimeMediaUtils.getMediaType(fileFormatName) != null)
			uploadedFileName = makeThumbnail(uploadPath, datePath, savedFileName);
		else
			uploadedFileName = makeIcon(uploadPath, datePath, savedFileName);
		
		return uploadedFileName;
	}
	
	private static String calcPath(String uploadPath) {
		logger.info("calcPath() ............");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(String.format("%1$syyyy%1$sMM%1$sdd", File.separator));
		String datePath = simpleDateFormat.format(new Date());
		logger.info(String.format("datePath = [%s]", datePath));
		
		makeDir(uploadPath, datePath);
		
		return datePath;
	}

	private static void makeDir(String uploadPath, String datePath) {
		logger.info("makeDir() ............");

		File dirPath = new File(uploadPath, datePath);
		logger.info(String.format("dirPath = [%s]", dirPath));
		
		if(!dirPath.exists())
			dirPath.mkdirs();
	}
	
	private static String makeThumbnail(String uploadPath, String datePath, String fileName) throws Exception {
		logger.info("makeThumbnail() ............");
		
		File imageFolder = new File(uploadPath, datePath);
		String thumbnailName = String.format("s_%s", fileName);
		String fileFormatName = FileUtils.getFileFormatName(fileName);
		
		BufferedImage sourceImage = ImageIO.read(new File(imageFolder, fileName));
		BufferedImage destImage = Scalr.resize(sourceImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		File newFile = new File(imageFolder, thumbnailName);
		
		ImageIO.write(destImage, fileFormatName.toUpperCase(), newFile);
		
		return getFileUploadedPath(datePath, thumbnailName);
	}
	
	private static String makeIcon(String uploadPath, String datePath, String fileName) throws Exception {
		logger.info("makeIcon() ............");
		
		return getFileUploadedPath(datePath, fileName);
	}
	
	private static String getFileUploadedPath(String datePath, String savedFileName) throws Exception {
		logger.info("getFileUploadedPath() ............");
		logger.info(String.format("datePath = [%s]", datePath));
		logger.info(String.format("savedFileName = [%s]", savedFileName));
		
		return new File(datePath, savedFileName).getPath().replace(File.separatorChar, '/');
	}
}

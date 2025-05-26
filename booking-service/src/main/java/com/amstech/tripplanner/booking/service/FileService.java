package com.amstech.tripplanner.booking.service;

import java.io.File;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FileService {

	private final String BASE_REPO_DIRECTORY_PATH = "./storage";

	private final String FILE_PATH_FORMAT = "{0}/{1}/{2}.{3}";

	@Getter
	@Value("${app.max-file-size}")
	private long fileMaxSize;
	
	
	public FileService() {
		log.info("FileService : Object Created");
	}
	

	@PostConstruct
	public void init() {

		log.info("Init File Service");
		
		File file = new File(BASE_REPO_DIRECTORY_PATH);
		
		if (!file.exists()) {
			try {
				log.info("Base Repo Does Not Exist ... Createing New Repo");
				
				if (file.mkdir()) {
					log.info("Base Repo SuccessFull Create");
				}
				else {
					log.info("Unable to crate Base Repo");
				}
			} catch (Exception e) {
				log.error("failed To Create Base Repo due to "+ e.getMessage(),e);
			}
		}else {
			log.info("Base Repo already Exist");
		}
		log.info("Max File Size : {} ",getFileMaxSize());
	}
	
	public String saveFile(byte[] date,String directoryName,String fileExt) throws IOException {
		
		String filePath = MessageFormat.format(FILE_PATH_FORMAT,BASE_REPO_DIRECTORY_PATH,directoryName,UUID.randomUUID().toString(),fileExt);
		
		FileUtils.writeByteArrayToFile(new File(filePath), date);
		
		log.info("File Path : {} ", filePath);
		return filePath;
	}

	
}

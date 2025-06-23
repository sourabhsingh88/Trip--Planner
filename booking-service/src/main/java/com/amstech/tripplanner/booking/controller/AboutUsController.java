package com.amstech.tripplanner.booking.controller;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amstech.tripplanner.booking.modal.request.AboutUsSaveRequestModal;
import com.amstech.tripplanner.booking.modal.request.AboutUsUpdateRequestModal;
import com.amstech.tripplanner.booking.modal.response.AboutUsResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
import com.amstech.tripplanner.booking.service.FileService;
import com.amstech.tripplanner.booking.service.AboutUsService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("aboutUs")
public class AboutUsController {
	
	@Autowired
	private AboutUsService aboutUsService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FileService fileService;
	
	
	private final Logger LOGGER = LoggerFactory.getLogger(AboutUsController.class);

	@RequestMapping(method = RequestMethod.POST, value = "/multiPartSave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public RestResponse multiPartSave(@RequestParam("aboutUsRequestModalJson") String aboutUsRequestModalJson,@RequestParam("banner") MultipartFile banner) throws IOException {
		String filePath = null;
 
		try {

			AboutUsSaveRequestModal aboutUsSaveRequestModal = objectMapper.readValue(aboutUsRequestModalJson,AboutUsSaveRequestModal.class);
			LOGGER.info("Start Save About Us Detail with email : {} ", aboutUsSaveRequestModal.getEmail());
			
			if(banner != null) {
				LOGGER.info("File Name : "+ banner.getOriginalFilename() + " and Size : " + banner.getSize() );
				
				if(banner.getSize() > fileService.getFileMaxSize()) {
					throw new Exception(" File Size Can Not Greater Than : " + fileService.getFileMaxSize() + " bytes");
				}
				
				filePath = fileService.saveFile(banner.getBytes(),"aboutUs",FilenameUtils.getExtension(banner.getOriginalFilename()));
			}
			
			aboutUsSaveRequestModal.setImgUrl(filePath);
			
			AboutUsResponseModal aboutUsResponseModal = aboutUsService.save(aboutUsSaveRequestModal);
			LOGGER.info("AboutUs Response Modal Reseived");
			return RestResponse.build().withSuccess("AboutUs Detail save Successfully", aboutUsResponseModal);
		} catch (Exception e) {
			FileUtils.delete(new File(filePath));
			LOGGER.error("Failed to save AboutUs Detial due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to save AboutUs Detial due to : " + e.getMessage());
		}
	}
	@RequestMapping(method = RequestMethod.POST, value = "/multiPartUpdate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public RestResponse multiPartUpdate(@RequestParam("aboutUsRequestModalJson") String aboutUsRequestModalJson,@RequestParam("banner") MultipartFile banner) throws IOException {
		String filePath = null;
 
		try {

			AboutUsUpdateRequestModal aboutUsUpdateRequestModal = objectMapper.readValue(aboutUsRequestModalJson,AboutUsUpdateRequestModal.class);
			LOGGER.info("Start Updateing About Us Detail with email : {} ", aboutUsUpdateRequestModal.getEmail());
			
			if(banner != null) {
				LOGGER.info("File Name : "+ banner.getOriginalFilename() + " and Size : " + banner.getSize() );
				
				if(banner.getSize() > fileService.getFileMaxSize()) {
					throw new Exception(" File Size Can Not Greater Than : " + fileService.getFileMaxSize() + " bytes");
				}
				
				filePath = fileService.saveFile(banner.getBytes(),"aboutUs",FilenameUtils.getExtension(banner.getOriginalFilename()));
			}
			
			aboutUsUpdateRequestModal.setImgUrl(filePath);
			
			AboutUsResponseModal aboutUsResponseModal = aboutUsService.update(aboutUsUpdateRequestModal);
			LOGGER.info("AboutUs Response Modal Reseived");
			return RestResponse.build().withSuccess("AboutUs Detail Update Successfully", aboutUsResponseModal);
		} catch (Exception e) {
			FileUtils.delete(new File(filePath));
			LOGGER.error("Failed to save AboutUs Detial due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Update AboutUs Detial due to : " + e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/findBy", produces = "application/json")
	public RestResponse findById(@RequestParam("id") Integer id) throws IOException {
		LOGGER.info("Start Finding  About Us Detail with Id : {} ", id);
		
		try {
			AboutUsResponseModal aboutUsResponseModal = aboutUsService.findById(id);
			LOGGER.info("AboutUs Response Modal Reseived");
			return RestResponse.build().withSuccess("AboutUs Detail Find Successfully", aboutUsResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to Find AboutUs Detial due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Find AboutUs Detial due to : " + e.getMessage());
		
		}
		
	}

}

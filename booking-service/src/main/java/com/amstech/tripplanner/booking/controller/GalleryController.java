package com.amstech.tripplanner.booking.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

import com.amstech.tripplanner.booking.modal.request.GallerySaveReqestModal;
import com.amstech.tripplanner.booking.modal.request.UserSignUpRequestModel;
import com.amstech.tripplanner.booking.modal.response.GalleryResponseModal;
import com.amstech.tripplanner.booking.modal.response.LocationWithUserResponseModal;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
import com.amstech.tripplanner.booking.service.FileService;
import com.amstech.tripplanner.booking.service.GalleryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("gallery")
public class GalleryController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);
	
	@Autowired
	private GalleryService galleryService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FileService fileService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/multiPartSave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public RestResponse multiPartSave(@RequestParam("galleyRequestModalJson") String galleyRequestModalJson,@RequestParam("image") MultipartFile image) throws IOException {
		String filePath = null;
 
		try {

			GallerySaveReqestModal gallerySaveReqestModal = objectMapper.readValue(galleyRequestModalJson,GallerySaveReqestModal.class);
			LOGGER.info("Start Uploding Image on Gallery : {} ", gallerySaveReqestModal.getDescription());
			
			if(image != null) {
				LOGGER.info("File Name : "+ image.getOriginalFilename() + " and Size : " + image.getSize() );
				
				if(image.getSize() > fileService.getFileMaxSize()) {
					throw new Exception(" File Size Can Not Greater Than : " + fileService.getFileMaxSize() + " bytes");
				}
				
				filePath = fileService.saveFile(image.getBytes(),"gallery",FilenameUtils.getExtension(image.getOriginalFilename()));
			}
			
			gallerySaveReqestModal.setImage(filePath);
			
			GalleryResponseModal galleryResponseModal = galleryService.save(gallerySaveReqestModal);
			LOGGER.info("Gallery Response Modal Reseived");
			return RestResponse.build().withSuccess("Successfully Upload The Image", galleryResponseModal);
		} catch (Exception e) {
			FileUtils.delete(new File(filePath));
			LOGGER.error("Failed to Failed to uplode Image due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to uplode Image due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		LOGGER.info("Fetching All Image Of Gallery");
		try {
			List<GalleryResponseModal> galleryResponseModals = galleryService.findAll(page, size);
			long totalRecords = galleryService.coutAll();
			return RestResponse.build().withSuccess("Gallery Images Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(galleryResponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed To Find GAllery Images due to  : {} ", e.getMessage(), e);
			return RestResponse.build().withError("Failed To Find GAllery Images due to" + e.getMessage());
		}
	}
	
}

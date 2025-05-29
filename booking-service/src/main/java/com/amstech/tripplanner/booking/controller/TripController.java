package com.amstech.tripplanner.booking.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amstech.tripplanner.booking.modal.request.TripCreateRequestModal;
import com.amstech.tripplanner.booking.modal.response.LocationWithTripResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripDetailResponseModal;
import com.amstech.tripplanner.booking.modal.response.TripResponseModal;
import com.amstech.tripplanner.booking.modal.response.UserResponseModal;
import com.amstech.tripplanner.booking.response.RestResponse;
import com.amstech.tripplanner.booking.service.FileService;
import com.amstech.tripplanner.booking.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("trip")
public class TripController {

	private final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

	@Autowired
	private TripService tripService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FileService fileService;

	public TripController() {

		LOGGER.info("TripController : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = "application/json")
	public RestResponse create(
			@RequestParam("tripCreateJson") String tripCreateJson,
			@RequestParam("image") MultipartFile image,
			@RequestParam("tripBanner") List<MultipartFile> tripBanners) throws IOException {
		String filePath = null;
		List<String> tripBannerPath = new ArrayList<>();
		
		try {
			TripCreateRequestModal tripCreateRequestModal = objectMapper.readValue(tripCreateJson,TripCreateRequestModal.class);
			LOGGER.info("Creating Trip with name : " + tripCreateRequestModal.getName());
			
			if(image != null) {
				LOGGER.info("File Name : "+ image.getOriginalFilename() + " and Size : " + image.getSize() );
				if(image.getSize() > fileService.getFileMaxSize()) {
					throw new Exception(" File Size Can Not Greater Than : " + fileService.getFileMaxSize() + " bytes");
				}
				filePath = fileService.saveFile(image.getBytes(),"trip",FilenameUtils.getExtension(image.getOriginalFilename()));
			}
			
			if (tripBanners != null) {

				for (MultipartFile tripBanner : tripBanners) {
					LOGGER.info("File name: {} with file size: {} byts", tripBanner.getOriginalFilename(),tripBanner.getSize());
					if (tripBanner.getSize() > fileService.getFileMaxSize())
						throw new Exception(
								"File size can not be gretter then: " + fileService.getFileMaxSize() + "byts");

					tripBannerPath.add(fileService.saveFile(tripBanner.getBytes(), "users",FilenameUtils.getExtension(tripBanner.getOriginalFilename())));

				}
				

			}
			tripCreateRequestModal.setTripBanners(tripBannerPath);
			tripCreateRequestModal.setUrl(filePath);
			LocationWithTripResponseModal locationWithTripResponseModal = tripService.create(tripCreateRequestModal);
			return RestResponse.build().withSuccess("SuccessFully Create trip",locationWithTripResponseModal);
		} catch (Exception e) {
			FileUtils.delete(new File(filePath));
			LOGGER.error("Failed to Fetching All Trips Availables due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Create Trip due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public RestResponse findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

		LOGGER.info("Fetching All Trips Available");
		try {
			List<TripResponseModal> tripResponseModals = tripService.findAllContinue(page, size);
			long totalRecords = tripService.countAllContinue();
			return RestResponse.build().withSuccess("Trip Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(tripResponseModals);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching All Trips Availables due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Fetching All Trips Availables due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byid", produces = "application/json")
	public RestResponse findById(@RequestParam("id") Integer id) {

		LOGGER.info("Fetching Details of Trip with id : {}", id);
		try {
			TripDetailResponseModal tripResponseModal = tripService.findById(id);
			return RestResponse.build().withSuccess("Trip founds",tripResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to Fetching Details of Trip with id due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Fetching Details of Trip with id due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search", produces = "application/json")
	public RestResponse SearchByName(@RequestParam("name") String name,@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		LOGGER.info("fetching trip data by name : {}" , name);
		try {
			List<TripResponseModal> tripResponseModals = tripService.findByName(name, page, size);
			long totalRecords = tripService.countByName(name);
			return RestResponse.build().withSuccess("Trip Founds").withTotalRecords(totalRecords)
					.withPageNumber(page).withPageSize(size).withData(tripResponseModals);
		} catch (Exception e) {
			LOGGER.error("Failed to Fetching Details of Trip with id due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to Fetching Details of Trip with id due to : " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateStatus", produces = "application/json")
	public RestResponse toggleTripStatus(@RequestParam("id") Integer id) {
		LOGGER.info("Updateing trip Status by statusId : {}" , id);
		try {
			TripDetailResponseModal tripreDetailResponseModal = tripService.toggleTripStatus(id);
			return RestResponse.build().withSuccess("Successufully Update status of trip", tripreDetailResponseModal);
		} catch (Exception e) {
			LOGGER.error("Failed to update status due to : {}", e.getMessage(), e);
			return RestResponse.build().withError("Failed to update status due to : " +  e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/filterBy", produces = "application/json")
	public RestResponse filterBy(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "size", required = true) Integer size,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "duration", required = false) Integer duration,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "keyword", required = false) String keyword) {
		LOGGER.info(
				"Fetching user by fillter page: {}, size: {}, to: {}, from: {}, gender: {}, duration: {}, price: {}, keyword: {}",
				page, size, to, from, duration, price, keyword);
		try {
			List<TripResponseModal> tripResponseModals = tripService.filterBy(page, size, to, from, duration, price, keyword);
			long totalRecord = tripService.countBy(to, from, duration, price, keyword);
			return RestResponse.build().withSuccess("Trip list found successfully").withTotalRecords(totalRecord)
					.withPageNumber(page).withPageSize(size).withData(tripResponseModals);
		} catch ( Exception e) {
			LOGGER.error("Failed to find Trip list due to: {}", e.getMessage(), e);
			return RestResponse.build().withError(e.getMessage());
		}
	}
}

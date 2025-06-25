package com.amstech.tripplanner.booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.converter.entity.AboutUsModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.AboutUsEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.AboutUs;
import com.amstech.tripplanner.booking.modal.request.AboutUsSaveRequestModal;
import com.amstech.tripplanner.booking.modal.request.AboutUsUpdateRequestModal;
import com.amstech.tripplanner.booking.modal.response.AboutUsResponseModal;
import com.amstech.tripplanner.booking.repo.AboutUsRepo;

@Service
public class AboutUsService {

	@Autowired
	private AboutUsRepo aboutUsRepo;
	@Autowired
	private AboutUsModalToEntityConverter aboutUsModalToEntityConverter;
	@Autowired
	private AboutUsEntityToModalConverter abotAboutUsEntityToModalConverter;

	public AboutUsResponseModal save(AboutUsSaveRequestModal aboutUsSaveRequestModal) throws Exception {

		AboutUs userByemail = aboutUsRepo.findByemail(aboutUsSaveRequestModal.getEmail());
		if (userByemail != null) {
			throw new Exception("User Is Already Exist with is Email : " + aboutUsSaveRequestModal.getEmail());
		}
		AboutUs userByPhoneMunber = aboutUsRepo.findByPhonenumber(aboutUsSaveRequestModal.getPhoneNumber());
		if (userByPhoneMunber != null) {
			throw new Exception(
					"User Is Already Exist with is PhoneNumber : " + aboutUsSaveRequestModal.getPhoneNumber());
		}

		AboutUs aboutUs = aboutUsModalToEntityConverter.save(aboutUsSaveRequestModal);
		AboutUs saveAboutUS = aboutUsRepo.save(aboutUs);
		return abotAboutUsEntityToModalConverter.findById(saveAboutUS);
	}

	public AboutUsResponseModal update(AboutUsUpdateRequestModal aboutUsUpdateRequestModal) throws Exception {

		Optional<AboutUs> aboutUsOptional = aboutUsRepo.findById(aboutUsUpdateRequestModal.getId());
		if (aboutUsOptional == null) {
			throw new Exception("AboutUs Details not Exit with id : " + aboutUsUpdateRequestModal.getId());
		}
		

		AboutUs aboutUs = aboutUsModalToEntityConverter.update(aboutUsUpdateRequestModal);
		AboutUs updateAboutUs = aboutUsRepo.save(aboutUs);
		return abotAboutUsEntityToModalConverter.findById(updateAboutUs);
	}
	
	public AboutUsResponseModal findById(Integer id) throws Exception {
		Optional<AboutUs> aboutUsOptional = aboutUsRepo.findById(id);
		if (aboutUsOptional == null) {
			throw new Exception("AboutUs Details not Exit with id : " + id);
		}
		AboutUs aboutUs = aboutUsOptional.get();
		return abotAboutUsEntityToModalConverter.findById(aboutUs);
	}
}

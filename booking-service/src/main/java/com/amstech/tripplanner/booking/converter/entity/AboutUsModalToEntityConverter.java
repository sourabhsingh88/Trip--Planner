package com.amstech.tripplanner.booking.converter.entity;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.AboutUs;
import com.amstech.tripplanner.booking.modal.request.AboutUsSaveRequestModal;
import com.amstech.tripplanner.booking.modal.request.AboutUsUpdateRequestModal;
import com.amstech.tripplanner.booking.repo.AboutUsRepo;

@Component
public class AboutUsModalToEntityConverter {
	
	@Autowired
	private AboutUsRepo aboutUsRepo;
	
	private String extractFileNameOnly(String fullPath) {
	    return new File(fullPath).getName();
	}
	
	public AboutUs save(AboutUsSaveRequestModal aboutUsSaveRequestModal) {
		AboutUs aboutUs =  new AboutUs();
		aboutUs.setTitle(aboutUsSaveRequestModal.getTital());
		aboutUs.setEmail(aboutUsSaveRequestModal.getEmail());
		aboutUs.setDescription(aboutUsSaveRequestModal.getDescription());
		aboutUs.setMission(aboutUsSaveRequestModal.getMission());
		aboutUs.setImgUrl(extractFileNameOnly(aboutUsSaveRequestModal.getImgUrl()));
		aboutUs.setPhoneNumber(aboutUsSaveRequestModal.getPhoneNumber());
		aboutUs.setVision(aboutUsSaveRequestModal.getVision());
		return aboutUs;
	}
	
	public AboutUs update(AboutUsUpdateRequestModal aboutUsUpdateRequestModal ) {
		Optional<AboutUs> aboutUsOptional = aboutUsRepo.findById(aboutUsUpdateRequestModal.getId());
		AboutUs aboutUs = aboutUsOptional.get();
		aboutUs.setTitle(aboutUsUpdateRequestModal.getTital());
		aboutUs.setEmail(aboutUsUpdateRequestModal.getEmail());
		aboutUs.setDescription(aboutUsUpdateRequestModal.getDescription());
		aboutUs.setMission(aboutUsUpdateRequestModal.getMission());
		aboutUs.setImgUrl(extractFileNameOnly(aboutUsUpdateRequestModal.getImgUrl()));
		aboutUs.setPhoneNumber(aboutUsUpdateRequestModal.getPhoneNumber());
		aboutUs.setVision(aboutUsUpdateRequestModal.getVision());
		return aboutUs;
	}
}

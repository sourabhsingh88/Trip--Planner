package com.amstech.tripplanner.booking.converter.modal;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.AboutUs;
import com.amstech.tripplanner.booking.modal.response.AboutUsResponseModal;

@Component
public class AboutUsEntityToModalConverter {

	public AboutUsResponseModal findById(AboutUs aboutUs) {
		AboutUsResponseModal aboutUsResponseModal = new AboutUsResponseModal();
		aboutUsResponseModal.setId(aboutUs.getId());
		aboutUsResponseModal.setEmail(aboutUs.getEmail());
		aboutUsResponseModal.setDescription(aboutUs.getDescription());
		aboutUsResponseModal.setImgUrl(aboutUs.getImgUrl());
		aboutUsResponseModal.setMission(aboutUs.getMission());
		aboutUsResponseModal.setPhoneNumber(aboutUs.getPhoneNumber());
		aboutUsResponseModal.setTital(aboutUs.getTitle());
		aboutUsResponseModal.setVision(aboutUs.getVision());
		return aboutUsResponseModal;
	}
}

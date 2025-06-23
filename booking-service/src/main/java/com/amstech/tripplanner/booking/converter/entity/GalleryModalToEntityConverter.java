package com.amstech.tripplanner.booking.converter.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Gallery;
import com.amstech.tripplanner.booking.modal.request.GallerySaveReqestModal;
import com.amstech.tripplanner.booking.modal.request.GalleryUpdateReqestModal;
import com.amstech.tripplanner.booking.repo.GalleryRepo;

@Component
public class GalleryModalToEntityConverter {
	
	@Autowired
	private GalleryRepo galleryRepo;
	
	public Gallery save(GallerySaveReqestModal gallerySaveReqestModal) {
		Gallery gallery = new Gallery();
		gallery.setImageUrl(gallerySaveReqestModal.getImage());
		gallery.setDecription(gallerySaveReqestModal.getDescription());
		return gallery;
	}
	
	public Gallery update(GalleryUpdateReqestModal galleryUpdateReqestModal) throws Exception {
		Optional<Gallery> galleryOptional = galleryRepo.findById(galleryUpdateReqestModal.getId());
		if(!galleryOptional.isPresent()) {
			throw new Exception("gallery is not Present");
		}
		Gallery gallery = galleryOptional.get();
		gallery.setImageUrl(galleryUpdateReqestModal.getImage());
		gallery.setDecription(galleryUpdateReqestModal.getDescription());
		return gallery;
	}
}

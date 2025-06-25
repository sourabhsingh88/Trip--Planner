package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.Gallery;
import com.amstech.tripplanner.booking.modal.response.GalleryResponseModal;

@Component
public class GalleryEntityToModalConverter {
	
	public GalleryResponseModal findBy(Gallery gallery) {
		GalleryResponseModal galleryResponseModal =  new GalleryResponseModal();
		galleryResponseModal.setId(gallery.getId());
		galleryResponseModal.setImage(gallery.getImageUrl());
		galleryResponseModal.setDescription(gallery.getDecription());
		return galleryResponseModal;
	}
	public List<GalleryResponseModal> findAll(List<Gallery> galleries){
		List<GalleryResponseModal> galleryResponseModals = new ArrayList<>();
		for (Gallery gallery : galleries) {
			GalleryResponseModal galleryResponseModal =  new GalleryResponseModal();
			galleryResponseModal.setId(gallery.getId());
			galleryResponseModal.setImage(gallery.getImageUrl());
			galleryResponseModal.setDescription(gallery.getDecription());
			galleryResponseModals.add(galleryResponseModal);
		}
		return galleryResponseModals;
	}
}

package com.amstech.tripplanner.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.tripplanner.booking.converter.entity.GalleryModalToEntityConverter;
import com.amstech.tripplanner.booking.converter.modal.GalleryEntityToModalConverter;
import com.amstech.tripplanner.booking.entity.Gallery;
import com.amstech.tripplanner.booking.modal.request.GallerySaveReqestModal;
import com.amstech.tripplanner.booking.modal.request.GalleryUpdateReqestModal;
import com.amstech.tripplanner.booking.modal.response.GalleryResponseModal;
import com.amstech.tripplanner.booking.repo.GalleryRepo;

@Service
public class GalleryService {

	@Autowired 
	private GalleryRepo galleryRepo;
	@Autowired
	private GalleryEntityToModalConverter galleryEntityToModalConverter;
	@Autowired
	private GalleryModalToEntityConverter galleryModalToEntityConverter;
	
	public GalleryResponseModal save(GallerySaveReqestModal gallerySaveReqestModal) {
		Gallery gallery = galleryModalToEntityConverter.save(gallerySaveReqestModal);
		Gallery saveGallery = galleryRepo.save(gallery);
		return galleryEntityToModalConverter.findBy(saveGallery);
	}
	public GalleryResponseModal update(GalleryUpdateReqestModal galleryUpdateReqestModal) throws Exception {
		Gallery gallery = galleryModalToEntityConverter.update(galleryUpdateReqestModal);
		Gallery updateGallery = galleryRepo.save(gallery);
		return galleryEntityToModalConverter.findBy(updateGallery);
	}
	public List<GalleryResponseModal> findAll(Integer page ,  Integer size){
		List<Gallery> galleries = galleryRepo.findAllImage(PageRequest.of(page, size));
		return galleryEntityToModalConverter.findAll(galleries);
	}
	public long coutAll() {
		return galleryRepo.countAllImage();
	}
}

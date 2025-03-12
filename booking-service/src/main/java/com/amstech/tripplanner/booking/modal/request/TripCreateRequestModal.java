package com.amstech.tripplanner.booking.modal.request;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class TripCreateRequestModal {

	private int tripplannerId;
	private String name;
	private String to;
	private String from;
	private int duration;
	private double prrice;
	private String tourGuideName;
	private Date startDate;
	private Date endDtate;
	private String description;
	private String locationName;
	private String url;
	private int cityId;
	private List<AccommodationCreateRequestModal> accommodationCreateRequestModals;
	private List<ActivityCreateRequestModal> activityCreateRequestModals;
	private List<MealCreateRequestModal> mealCreateRequestModals;
	private List<TransportCreateRequestModal> transportCreateRequestModals;
	
}

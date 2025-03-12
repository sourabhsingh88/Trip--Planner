package com.amstech.tripplanner.booking.modal.request;

import java.util.Date;

import lombok.Data;

@Data
public class ActivityCreateRequestModal {

	private String name;
	private Date activityDate;
	private String description;
}

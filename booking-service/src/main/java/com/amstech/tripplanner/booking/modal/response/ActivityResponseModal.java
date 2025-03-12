package com.amstech.tripplanner.booking.modal.response;

import java.util.Date;

import lombok.Data;

@Data
public class ActivityResponseModal {
	private int id;
	private String name;
	private Date activityDate;
	private String description;
}

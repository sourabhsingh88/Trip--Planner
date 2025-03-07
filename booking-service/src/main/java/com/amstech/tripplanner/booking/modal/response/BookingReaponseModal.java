package com.amstech.tripplanner.booking.modal.response;



import lombok.Data;
@Data
public class BookingReaponseModal {

	private int id;
	private String tripName;
	private String tripPlannerName;
	private String tripDescription;
	private double tripPrice;
	private String statusName;
		
}

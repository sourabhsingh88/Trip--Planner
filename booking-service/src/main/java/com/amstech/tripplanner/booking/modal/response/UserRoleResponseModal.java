package com.amstech.tripplanner.booking.modal.response;

import java.util.Date;
import lombok.Data;

@Data
public class UserRoleResponseModal {

	private int id;
	private String userName;
	private String roleName;
	private Date createdAt;
	private Date updatedAt;
}

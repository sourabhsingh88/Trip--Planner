package com.amstech.tripplanner.booking.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.tripplanner.booking.entity.State;
import com.amstech.tripplanner.booking.modal.response.StateResponseModal;

@Component
public class StateEntityToModalConverter {

	public List<StateResponseModal> findAll(List<State> states){
		List<StateResponseModal> stateResponseModals = new ArrayList<>();
		for (State state : states) {
			StateResponseModal stateResponseModal = new StateResponseModal();
			stateResponseModal.setId(state.getId());
			stateResponseModal.setStateName(state.getName());
			stateResponseModal.setCountryName(state.getCountry());
			stateResponseModals.add(stateResponseModal);
		}
		return stateResponseModals;
	}
}

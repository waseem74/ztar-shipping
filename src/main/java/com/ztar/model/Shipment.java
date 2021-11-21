package com.ztar.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface Shipment {
	
	void validate();
	
	void create();
	
}

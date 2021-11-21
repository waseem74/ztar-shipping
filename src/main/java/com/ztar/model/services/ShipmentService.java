package com.ztar.model.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ztar.model.Shipment;
import com.ztar.model.ShipmentFactory;
import com.ztar.model.dto.ShipmentDTO;

/**
 * @author Waseem Zawaideh
 */
@Service
public class ShipmentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void createShipment(ShipmentDTO shipment) {

		logger.info("Inside the shipment service");		
		Shipment shpmnt = ShipmentFactory.getType(shipment);		
		logger.info("Shipment service type is: " + shpmnt.getClass());
		
		shpmnt.validate();		
		logger.info("Shipment service validation is completed");

		shpmnt.create();		
		logger.info("Shipment order was created successfully");
	}

}

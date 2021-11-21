package com.ztar.model;

import org.springframework.stereotype.Component;

import com.ztar.model.dto.CarrierDTO;
import com.ztar.model.dto.ShipmentDTO;
import com.ztar.model.utils.ShipmentException;

public interface ShipmentFactory {

	static Shipment getType(ShipmentDTO shipment) {

		String carrierId = shipment.getCarrier().getCarrierId();

		if (CarrierDTO.Carrier.FEDEX.getValue().equalsIgnoreCase(carrierId)) {
			return new FedexShipment(shipment);
		}

		if (CarrierDTO.Carrier.UPS.getValue().equalsIgnoreCase(carrierId)) {
			return new UpsShipment(shipment);
		}

		throw new ShipmentException("ztar.validation.not_valid", new Object[] { "'carrierId'" });
	}
}

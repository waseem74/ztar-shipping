package com.ztar.model;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ztar.model.dto.CarrierDTO;
import com.ztar.model.dto.ShipmentDTO;
import com.ztar.model.dto.UnitDTO;
import com.ztar.model.utils.GeneralUtils;
import com.ztar.model.utils.ShipmentException;

@Component
public class FedexShipment implements Shipment {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	private ShipmentDTO shipment;

	public FedexShipment(ShipmentDTO shipment) {
		this.shipment = shipment;
	}

	@Override
	public void validate() {
		String carrierServiceId = shipment.getCarrier().getCarrierServiceId();

		logger.info("Start Fedex service validation");
		if (StringUtils.isBlank(carrierServiceId)) {
			throw new ShipmentException("ztar.validation.mandatory", new Object[] {"'carrierServiceId'"} );
		}

		if (!CarrierDTO.CarrierService.contains(carrierServiceId)) {
			throw new ShipmentException("ztar.validation.not_valid", new Object[] {"'carrierServiceId'"} );
		}
		
		if (!UnitDTO.LengthUnit.CM.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getHeight().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'height'", "cm" });
		}

		if (!UnitDTO.LengthUnit.CM.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getWidth().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'width'", "cm" });
		}

		if (!UnitDTO.LengthUnit.CM.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getLength().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'length'", "cm" });
		}

		if (!UnitDTO.WeightUnit.GRAM.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getWeight().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'weight'", "gram" });
		}

		logger.info("Fedex service validation is passed");
	}

	@Override
	public void create() {

		// generating a random unique number for the shipment order
		String orderId = RandomStringUtils.randomAlphanumeric(6);
		shipment.setOrderId(orderId);
		shipment.setOrderDate(GeneralUtils.formatDate(ZonedDateTime.now(), "MMMM dd, yyyy H:mm z"));
		
		XmlMapper xmlMapper = new XmlMapper();
		try {
			xmlMapper.writeValue(new File("orders", "fedx_shipment_order_" + orderId + ".xml"), shipment);

		} catch (IOException e) {
			throw new ShipmentException(e.toString());
		}

	}

}

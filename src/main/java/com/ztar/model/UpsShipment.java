package com.ztar.model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

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
public class UpsShipment implements Shipment {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 

	private ShipmentDTO shipment;

	public UpsShipment(ShipmentDTO shipment) {
		this.shipment = shipment;
	}

	@Override
	public void validate() {

		String shpmntServiceId = shipment.getCarrier().getShipmentServiceId();

		logger.info("Start UPS service validation");

		if (StringUtils.isBlank(shpmntServiceId)) {
			throw new ShipmentException("ztar.validation.mandatory", new Object[] { "'shipmentServiceId'" });
		}

		if (!CarrierDTO.ShipmentService.contains(shpmntServiceId)) {
			throw new ShipmentException("ztar.validation.not_valid", new Object[] { "'shipmentServiceId'" });
		}

		if (!UnitDTO.LengthUnit.INCH.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getHeight().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'height'", "inch" });
		}

		if (!UnitDTO.LengthUnit.INCH.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getWidth().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'width'", "inch" });
		}

		if (!UnitDTO.LengthUnit.INCH.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getLength().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'length'", "inch" });
		}

		if (!UnitDTO.WeightUnit.POUND.getValue()
				.equalsIgnoreCase(shipment.getShipmentPackage().getWeight().getMeasurementUnit())) {
			throw new ShipmentException("ztar.validation.wrong_unit", new Object[] { "'weight'", "pound" });
		}

		logger.info("UPS service validation is passed");
	}

	@Override
	public void create() {

		// generating a random unique number for the shipment order
		String orderId = RandomStringUtils.randomAlphanumeric(6, 8);
		shipment.setOrderId(orderId);
		shipment.setOrderDate(GeneralUtils.formatDate(ZonedDateTime.now(), "MMMM dd, yyyy H:mm z"));

		XmlMapper xmlMapper = new XmlMapper();
		try {
			xmlMapper.writeValue(new File("orders", shipment.getCarrier().getCarrierId() + "_shipment_order_" + orderId + ".xml"), shipment);

		} catch (IOException e) {
			throw new ShipmentException(e.toString());
		}

	}

}

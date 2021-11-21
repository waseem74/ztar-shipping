package com.ztar.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipmentDTO implements Serializable {

	private static final long serialVersionUID = 2615769230986670002L;

	/** Generated order number */
	private String orderId;

	/** Generated order date */
	private String orderDate;

	/** Package info */
	@NotNull
	@Valid
	private PackageDTO shipmentPackage;

	/** Carrier info */
	@NotNull
	@Valid
	private CarrierDTO carrier;

	public PackageDTO getShipmentPackage() {
		return shipmentPackage;
	}

	public void setShipmentPackage(PackageDTO shipmentPackage) {
		this.shipmentPackage = shipmentPackage;
	}

	public CarrierDTO getCarrier() {
		return carrier;
	}

	public void setCarrier(CarrierDTO carrier) {
		this.carrier = carrier;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "ShipmentDTO [orderId=" + orderId + ", orderDate=" + orderDate + ", shipmentPackage=" + shipmentPackage
				+ ", carrier=" + carrier + "]";
	}

}

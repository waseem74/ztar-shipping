package com.ztar.model.dto;

import java.io.Serializable;
import java.util.Arrays;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ztar.model.dto.UnitDTO.WeightUnit;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarrierDTO implements Serializable {

	private static final long serialVersionUID = 5813369878717295L;

	public enum Carrier {
		FEDEX("fedex"), UPS("ups");

		private String value;

		private Carrier(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public boolean contains(String val) {

			return Arrays.asList(Carrier.values()).stream().anyMatch(unit -> unit.value.equals(val));
		}
	}

	public enum CarrierService {
		FEDEX_AIR("fedexAIR"), FEDEX_GROUND("fedexGroud");

		private String value;

		private CarrierService(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static boolean contains(String val) {

			return Arrays.asList(CarrierService.values()).stream().anyMatch(unit -> unit.value.equals(val));
		}

	}

	public enum ShipmentService {
		UPS_EXPRESS("UPSExpress"), UPS_2DAY("UPS2DAY");

		private String value;

		private ShipmentService(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static boolean contains(String val) {

			return Arrays.asList(ShipmentService.values()).stream().anyMatch(unit -> unit.value.equals(val));
		}
	}

	@NotEmpty
	private String carrierId;

	private String carrierServiceId;

	private String shipmentServiceId;

	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getCarrierServiceId() {
		return carrierServiceId;
	}

	public void setCarrierServiceId(String carrierServiceId) {
		this.carrierServiceId = carrierServiceId;
	}

	public String getShipmentServiceId() {
		return shipmentServiceId;
	}

	public void setShipmentServiceId(String shipmentServiceId) {
		this.shipmentServiceId = shipmentServiceId;
	}

	@Override
	public String toString() {
		return "CarrierDTO [carrierId=" + carrierId + ", carrierServiceId=" + carrierServiceId + ", shipmentServiceId="
				+ shipmentServiceId + "]";
	}

}

package com.ztar.model.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PackageDTO implements Serializable {

	private static final long serialVersionUID = 6579143433462021555L;

	@NotNull
	@Valid
	private UnitDTO height;

	@NotNull
	@Valid
	private UnitDTO width;

	@NotNull
	@Valid
	private UnitDTO length;

	@NotNull
	@Valid
	private UnitDTO weight;

	public UnitDTO getHeight() {
		return height;
	}

	public void setHeight(UnitDTO height) {
		this.height = height;
	}

	public UnitDTO getWidth() {
		return width;
	}

	public void setWidth(UnitDTO width) {
		this.width = width;
	}

	public UnitDTO getLength() {
		return length;
	}

	public void setLength(UnitDTO length) {
		this.length = length;
	}

	public UnitDTO getWeight() {
		return weight;
	}

	public void setWeight(UnitDTO weight) {
		this.weight = weight;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PackageDTO [height=" + height + ", width=" + width + ", length=" + length + ", weight=" + weight + "]";
	}

}

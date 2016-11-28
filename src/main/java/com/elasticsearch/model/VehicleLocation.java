package com.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "vehiclelocation", type = "vehiclelocation", useServerConfiguration = true, createIndex = true)
public class VehicleLocation {

	@Id
	private String vehicleId;
	
	@GeoPointField
	private GeoPoint geoPoint;

	public VehicleLocation() {
	}

	public VehicleLocation(String vehicleId, Double latitude,Double longitude) {
		super();
		this.vehicleId = vehicleId;
		this.geoPoint = new GeoPoint(latitude, longitude);
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public GeoPoint getGeoPoint() {
		return geoPoint;
	}

	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}

	@Override
	public String toString() {
		return "VehicleLocation [vehicleId=" + vehicleId + ", geoPoint=" + geoPoint + "]";
	}

}

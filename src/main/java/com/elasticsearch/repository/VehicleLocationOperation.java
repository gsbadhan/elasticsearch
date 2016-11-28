package com.elasticsearch.repository;

import java.util.List;

import org.elasticsearch.common.unit.DistanceUnit;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.elasticsearch.model.VehicleLocation;

public interface VehicleLocationOperation {
	boolean save(VehicleLocation vehicleLocation);

	VehicleLocation getVehicleInfo(String id);
	
	List<VehicleLocation> searchNearVehicle(GeoPoint currentGeo,Integer radius,DistanceUnit distanceUnit,int startPage);
	
}

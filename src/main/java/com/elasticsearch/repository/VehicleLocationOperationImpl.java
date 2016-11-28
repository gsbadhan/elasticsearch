package com.elasticsearch.repository;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceRangeQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Repository;

import com.elasticsearch.model.VehicleLocation;

@Repository
public class VehicleLocationOperationImpl implements VehicleLocationOperation {

	@Inject
	private VehicleLocationRepository vehicleLocationRepository;

	@Override
	public boolean save(VehicleLocation vehicleLocation) {
		VehicleLocation result = vehicleLocationRepository.save(vehicleLocation);
		if (result != null) {
			return true;
		}
		return false;
	}

	@Override
	public VehicleLocation getVehicleInfo(String id) {
		return vehicleLocationRepository.findOne(id);
	}

	@Override
	public List<VehicleLocation> searchNearVehicle(GeoPoint currentGeo, Integer radius, DistanceUnit distanceUnit,
			int startPage) {
		String maxDistance = getMaxDistanceInMeters(radius, distanceUnit);
		String minDistance = "0m";
		if (startPage < 0) {
			startPage = 0;
		}
		GeoDistanceRangeQueryBuilder builder = new GeoDistanceRangeQueryBuilder("geoPoint");
		builder.point(currentGeo.getLat(), currentGeo.getLon()).from(minDistance).to(maxDistance).optimizeBbox("memory")
				.geoDistance(GeoDistance.ARC);
		Pageable pageable = new PageRequest(0, 10);
		Iterable<VehicleLocation> iterable = vehicleLocationRepository.search(builder, pageable);
		Iterator<VehicleLocation> itr = iterable.iterator();
		while (itr.hasNext()) {
			System.out.println("search:" + itr.next());
		}
		return null;
	}

	private String getMaxDistanceInMeters(Integer radius, DistanceUnit distanceUnit) {
		String maxDist = null;
		final String unit = "m";
		switch (distanceUnit) {
		case METERS:
			maxDist = radius + unit;
			break;
		case MILES:
			maxDist = (radius * 1609.344) + unit;
		case KILOMETERS:
			maxDist = (radius * 1000) + unit;
			break;
		default:
			throw new IllegalArgumentException("Units not handled:" + distanceUnit);
		}
		return maxDist;
	}

}

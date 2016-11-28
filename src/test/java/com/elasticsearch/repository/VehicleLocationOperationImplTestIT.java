package com.elasticsearch.repository;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.common.unit.DistanceUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elasticsearch.model.VehicleLocation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:elastic-context.xml" })
public class VehicleLocationOperationImplTestIT {

	@Inject
	private VehicleLocationOperation vehicleLocationOperation;
	private List<VehicleLocation> dataList = new LinkedList<>();

	@Before
	public void setup() {
		dataList.add(new VehicleLocation("101", 12.453D, 100.78D));
		dataList.add(new VehicleLocation("102", 12.452D, 100.78D));
		dataList.add(new VehicleLocation("103", 12.451D, 100.78D));
	}

	@Test
	public void testSave() {
		try {
			for (VehicleLocation vl : dataList) {
				vehicleLocationOperation.save(vl);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetVehicleInfo() {
		VehicleLocation vehicleLocation = vehicleLocationOperation.getVehicleInfo("101");
		System.out.println(vehicleLocation);
	}

	@Test
	public void testsearch() {
		GeoPoint currentLocation = new GeoPoint(12.450D, 100.78D);
		List<VehicleLocation> vehicleLocation = vehicleLocationOperation.searchNearVehicle(currentLocation, 10,
				DistanceUnit.KILOMETERS, 0);
	}
}

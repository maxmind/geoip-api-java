package com.maxmind.geoip;

/* CityLookupTest.java */

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class CityLookupTest {
	private static final double DELTA = 1e-5;

	@Test
	public void testCityLookup() throws IOException {

		LookupService cl = new LookupService(
				"src/test/resources/GeoIP/GeoIPCity.dat",
				LookupService.GEOIP_MEMORY_CACHE);

		Location l1 = cl.getLocation("64.17.254.216");
		Location l2 = cl.getLocation("66.92.181.240");

		assertEquals("US", l2.countryCode);
		assertEquals("United States", l2.countryName);
		assertEquals("CA", l2.region);
		assertEquals("California",
				regionName.regionNameByCode(l2.countryCode, l2.region));
		assertEquals("Fremont", l2.city);
		assertEquals("94538", l2.postalCode);
		assertEquals(37.507904, l2.latitude, DELTA);
		assertEquals(-121.96, l2.longitude, DELTA);
		assertEquals(512.893498, l2.distance(l1), DELTA);
		assertEquals(l2.distance(l1), l1.distance(l2), DELTA);
		assertEquals(807, l2.metro_code);
		assertEquals(510, l2.area_code);
		assertEquals("America/Los_Angeles",
				timeZone.timeZoneByCountryAndRegion(l2.countryCode, l2.region));

		cl.close();

	}

    @Test(expected=InvalidDatabaseException.class)
	public void testCityLookupInInvalidDatabase() throws IOException {

		LookupService cl = new LookupService(
				"src/test/resources/GeoIP/GeoIPCity-Corrupt.dat",
				LookupService.GEOIP_MEMORY_CACHE);
		Location l2 = cl.getLocation("66.92.181.240");

	}
}

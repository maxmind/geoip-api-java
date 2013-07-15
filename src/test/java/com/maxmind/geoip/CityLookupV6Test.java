package com.maxmind.geoip;

/* CityLookupV6Test.java */

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class CityLookupV6Test {
	private static final double DELTA = 1e-5;

	@Test
	public void testCityLookupV6() throws IOException {

		LookupService cl = new LookupService(
				"src/test/resources/GeoIP/GeoLiteCityv6.dat",
				LookupService.GEOIP_MEMORY_CACHE);
		Location l1 = cl.getLocationV6("2a02:ff40::");
		Location l2 = cl.getLocationV6("2001:208::");

		assertEquals("SG", l2.countryCode);
		assertEquals("Singapore", l2.countryName);
		assertEquals(1.3666992, l2.latitude, DELTA);
		assertEquals(103.79999, l2.longitude, DELTA);
		assertEquals(11074.876894, l2.distance(l1), DELTA);
		assertEquals(11074.876894, l1.distance(l2), DELTA);
		assertEquals(0, l2.metro_code);
		assertEquals("Asia/Singapore",
				timeZone.timeZoneByCountryAndRegion(l2.countryCode, l2.region));

		cl.close();
	}
}

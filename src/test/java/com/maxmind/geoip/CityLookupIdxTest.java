package com.maxmind.geoip;

/* CityLookupTest.java */

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class CityLookupIdxTest {
	private static final double DELTA = 1e-5;

	@Test
	public void testCityLookupIdx() throws IOException {

		LookupService cl = new LookupService(
				"src/test/resources/GeoIP/GeoIPCity.dat",
				LookupService.GEOIP_INDEX_CACHE);

		Location l1 = cl.getLocation("222.230.137.0");

		assertEquals("JP", l1.countryCode);
		assertEquals("Japan", l1.countryName);
		assertEquals("40", l1.region);
		assertEquals("Tokyo", l1.city);
		assertEquals(35.6850, l1.latitude, DELTA);
		assertEquals(139.7510, l1.longitude, DELTA);
		assertEquals(0, l1.metro_code);
		assertEquals(0, l1.area_code);
		cl.close();
	}

}

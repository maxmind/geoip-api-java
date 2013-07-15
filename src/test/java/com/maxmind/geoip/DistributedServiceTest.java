package com.maxmind.geoip;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class DistributedServiceTest {
	private static final double DELTA = 1e-5;

	@Test
	public void testDistributedService() throws IOException {
		LookupService cl = new LookupService(
				"src/test/resources/GeoIP/GeoIPCity.dat",
				LookupService.GEOIP_MEMORY_CACHE);

		Location l = cl.getLocation("64.17.254.216");
		assertEquals("US", l.countryCode);
		assertEquals("United States", l.countryName);
		assertEquals("CA", l.region);
		assertEquals("El Segundo", l.city);
		assertEquals("90245", l.postalCode);
		assertEquals(33.916397, l.latitude, DELTA);
		assertEquals(-118.404, l.longitude, DELTA);
		cl.close();
	}

}

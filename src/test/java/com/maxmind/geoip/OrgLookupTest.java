package com.maxmind.geoip;

/* OrgLookupTest.java */

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class OrgLookupTest {
	@Test
	public void testOrgLookup() throws IOException {
		LookupService orgl = new LookupService(
				"src/test/resources/GeoIP/GeoIPOrg.dat");

		assertEquals("DSLAM WAN Allocation", orgl.getOrg("70.46.123.145"));
		orgl.close();
	}

	@Test
	public void testIspLookup() throws IOException {
		LookupService ispl = new LookupService(
				"src/test/resources/GeoIP/GeoIPISP.dat");

		assertEquals("FDN Communications", ispl.getOrg("70.46.123.145"));
		ispl.close();
	}

}

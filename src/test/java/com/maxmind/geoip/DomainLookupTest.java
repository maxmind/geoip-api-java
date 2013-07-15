package com.maxmind.geoip;

/* DomainLookupTest.java */

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class DomainLookupTest {
	@Test
	public void testDomainLookup() throws IOException {
		LookupService gid = new LookupService(
				"src/test/resources/GeoIP/GeoIPDomain.dat");
		assertEquals("shoesfin.NET", gid.getOrg("67.43.156.0"));
		gid.close();
	}
}

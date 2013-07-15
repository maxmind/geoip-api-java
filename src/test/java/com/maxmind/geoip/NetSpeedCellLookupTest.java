package com.maxmind.geoip;

/* OrgLookupTest.java */

// Need a binary sample dataset

import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class NetSpeedCellLookupTest {
	public void testNetSpeedCellLookup() throws IOException {
		LookupService ns = new LookupService(
				"src/test/resources/GeoIP/GeoIPNetSpeedCell.dat");
		assertEquals("Cable/DSL", "89.66.148.0");
		ns.close();
	}
}

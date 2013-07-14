package com.maxmind.geoip;

/* ASNumLookupV6Test.java */


import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;


public class ASNumLookupV6Test {
	@Test
	public void testASNumLookupV6() throws IOException {
	    LookupService asnl = new LookupService("GeoIP/GeoIPASNumv6.dat");
	    String ASNum = asnl.getOrgV6("2a00:1450:4013:c00::8a");
	    asnl.close();
	    assertEquals(ASNum, "AS15169 Google Inc.");
	}
}

	


package com.maxmind.geoip;

/* OrgLookupTest.java */

// Need a binary sample dataset

import com.maxmind.geoip.LookupService;

import java.io.IOException;


public class NetSpeedCellLookupTest {
   public void testNetSpeedCellLookup () throws IOException {
	    LookupService ns = new LookupService("GeoIP/GeoIPNetSpeedCell.dat");
	    System.out.println("XX: " + ns.getOrg("64.4.4.4"));
	    ns.close();
   }
}

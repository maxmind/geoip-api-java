package com.maxmind.geoip;

/* OrgLookupTest.java */

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;


public class OrgLookupTest {
   @Test
   public void testOrgLookup() throws IOException {
	    LookupService orgl = new LookupService("GeoIP/GeoIPOrg.dat");
	    LookupService ispl = new LookupService("GeoIP/GeoIPISP.dat");
	    System.out.println("Organization: " + orgl.getOrg("64.4.4.4") +
		       "\tISP: " + ispl.getOrg("64.4.4.4"));
	    orgl.close();
	    ispl.close();
   }
}

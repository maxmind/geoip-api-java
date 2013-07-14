package com.maxmind.geoip;

/* DomainLookupTest.java */

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;


public class DomainLookupTest {
	@Test
    public void testDomainLookup() throws IOException {
	    LookupService gid = new LookupService("src/test/resources/GeoIP/GeoIPDomain.dat");
	    System.out.println("Domain: " + gid.getOrg("64.4.4.4"));
	    gid.close();
	}
}

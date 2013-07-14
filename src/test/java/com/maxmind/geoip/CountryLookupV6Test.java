package com.maxmind.geoip;

/* CountryLookupV6Test.java */


import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;

public class CountryLookupV6Test {
	@Test
    public void testCountryLookupV6() throws IOException {
	    String sep = System.getProperty("file.separator");

	    // Uncomment for windows
	    // String dir = System.getProperty("user.dir"); 

	    // Uncomment for Linux
	    String dir = "GeoIP";

	    String dbfile = dir + sep + "GeoIPv6.dat"; 
	    // You should only call LookupService once, especially if you use
	    // GEOIP_MEMORY_CACHE mode, since the LookupService constructor takes up
	    // resources to load the GeoIP.dat file into memory
	    //LookupService cl = new LookupService(dbfile,LookupService.GEOIP_STANDARD);
	    LookupService cl = new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);

	    assertEquals(cl.getCountryV6("ipv6.google.com").getCode(),"US");
	    assertEquals(cl.getCountryV6("::127.0.0.1").getName(),"N/A");
	    assertEquals(cl.getCountryV6("::151.38.39.114").getName(),"Italy");
	    assertEquals(cl.getCountryV6("2001:4860:0:1001::68").getName(),"United States");

	    cl.close();
	}
	
}

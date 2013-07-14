package com.maxmind.geoip;

/* CountryLookupV6Test.java */


import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;

public class CountryLookupV6Test {
	@Test
    public void testCountryLookupV6() throws IOException {

	    String dbfile = "src/test/resources/GeoIP/GeoLiteCityv6.dat"; 

	    LookupService cl = new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);

	    //assertEquals(cl.getCountryV6("ipv6.google.com").getCode(),"US");
	    //assertEquals(cl.getCountryV6("::127.0.0.1").getName(),"N/A");
	    //assertEquals(cl.getCountryV6("::127.0.0.1").getName(),"Italy");
	    //assertEquals(cl.getCountryV6("2a02:ffc0::").getName(),"United States");

	    cl.close();
	}
	
}

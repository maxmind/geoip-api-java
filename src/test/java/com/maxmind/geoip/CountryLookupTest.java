package com.maxmind.geoip;

/* CountryLookupTest.java */

/* Only works with GeoIP Country Edition */
/* For Geoip City Edition, use CityLookupTest.java */

import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;

public class CountryLookupTest {
	@Test
    public void testCountryLookup() throws IOException {

	    String sep = System.getProperty("file.separator");

	    // Uncomment for windows
	    // String dir = System.getProperty("user.dir"); 

	    // Uncomment for Linux
	    String dir = "GeoIP";

	    String dbfile = dir + sep + "GeoIP.dat"; 
	    // You should only call LookupService once, especially if you use
	    // GEOIP_MEMORY_CACHE mode, since the LookupService constructor takes up
	    // resources to load the GeoIP.dat file into memory
	    //LookupService cl = new LookupService(dbfile,LookupService.GEOIP_STANDARD);
	    LookupService cl = new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);

	    assertEquals(cl.getCountry("64.17.254.216").getCode(),"US");
	    assertEquals(cl.getCountry("64.17.254.216").getName(),"United States");
	    assertEquals(cl.getCountry("78.26.70.208").getName(),"Italy");
	    assertEquals(cl.getCountry("83.206.36.224").getName(),"France");
	    assertEquals(cl.getCountry("85.88.2.224").getName(),"Germany");

	    cl.close();
	
	}
}


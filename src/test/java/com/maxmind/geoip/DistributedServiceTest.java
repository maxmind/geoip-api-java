package com.maxmind.geoip;


import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;

public class DistributedServiceTest {
	@Test
    public void testDistributedService() throws IOException {
		LookupService cl = new LookupService("src/test/resources/GeoIP/GeoIPCity.dat",
 					LookupService.GEOIP_MEMORY_CACHE );
        	 
        Location l = cl.getLocation("64.17.254.216");
        String results = "countryCode: " + l.countryCode +
			       " countryName: " + l.countryName +
			       " region: " + l.region +
                               " city: " + l.city +
                               " postalCode: " + l.postalCode +
                               " latitude: " + l.latitude +
                               " longitude: " + l.longitude;
	    cl.close();
	    
	    String expected = "countryCode: US countryName: United States region: CA city: El Segundo postalCode: 90245 latitude: 33.916397 longitude: -118.404";
	    
	    assertEquals(results, expected);
	    
	    }
    
    
}


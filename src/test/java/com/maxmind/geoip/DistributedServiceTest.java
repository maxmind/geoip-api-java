package com.maxmind.geoip;


import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;

public class DistributedServiceTest {
	@Test
    public void testDistributedService() throws IOException {
		LookupService cl = new LookupService("GeoIP/GeoLiteCity.dat",
 					LookupService.GEOIP_MEMORY_CACHE );
        	 
        Location l = cl.getLocation("24.24.24.24");
        String results = "countryCode: " + l.countryCode +
			       " countryName: " + l.countryName +
			       " region: " + l.region +
                               " city: " + l.city +
                               " postalCode: " + l.postalCode +
                               " latitude: " + l.latitude +
                               " longitude: " + l.longitude;
	    cl.close();
	    
	    String expected = "countryCode: US countryName: United States region: NY city: East Syracuse postalCode: 13057 latitude: 43.089203 longitude: -76.025";
	    
	    assertEquals(results, expected);
	    
	    }
    
    
}


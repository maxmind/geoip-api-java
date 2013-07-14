package com.maxmind.geoip;

/* CityLookupTest.java */

import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;


public class CityLookupTest {
	@Test
    public void testCityLookup() throws IOException {

	    LookupService cl = new LookupService("src/test/resources/GeoIP/GeoIPCity.dat",
					LookupService.GEOIP_MEMORY_CACHE );
	    
	    	System.out.println(cl.getDatabaseInfo());
	    	
	    
            Location l1 = cl.getLocation("64.17.254.216");
            Location l2 = cl.getLocation("66.92.181.240");
            
	    String output = "countryCode: " + l2.countryCode +
                               "\n countryName: " + l2.countryName +
                               "\n region: " + l2.region +
                               "\n regionName: " + regionName.regionNameByCode(l2.countryCode, l2.region) +
                               "\n city: " + l2.city +
                               "\n postalCode: " + l2.postalCode +
                               "\n latitude: " + l2.latitude +
                               "\n longitude: " + l2.longitude +
                               "\n distance: " + l2.distance(l1) +
                               "\n distance: " + l1.distance(l2) + 
 			       "\n metro code: " + l2.metro_code +
 			       "\n area code: " + l2.area_code +
                               "\n timezone: " + timeZone.timeZoneByCountryAndRegion(l2.countryCode, l2.region);

	    cl.close();
	    
	    System.out.println(output);
	    
	    String expected = "countryCode: US" +
	    			"\n countryName: United States" +
	    			"\n region: CA" + 
	    			"\n regionName: California" +
	    			"\n city: Fremont" +
	    			"\n postalCode: 94538" +
	    			"\n latitude: 37.507904" +
	    			"\n longitude: -121.96" +
	    			"\n distance: 512.8934984414043" +
	    			"\n distance: 512.8934984414043" +
	    			"\n metro code: 807" +
	    			"\n area code: 510" +
	    			"\n timezone: America/Los_Angeles";
	    
	    assertEquals(expected, output);
	    
	}
	
   
}

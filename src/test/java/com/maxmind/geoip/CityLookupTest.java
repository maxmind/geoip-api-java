package com.maxmind.geoip;

/* CityLookupTest.java */

import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;


public class CityLookupTest {
	@Test
    public void testCityLookup() throws IOException {

	    LookupService cl = new LookupService("GeoIP/GeoLiteCity.dat",
					LookupService.GEOIP_MEMORY_CACHE );
	    
            Location l1 = cl.getLocation("213.52.50.8");
            Location l2 = cl.getLocation("64.4.4.4");
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
	    
	    String expected = "countryCode: US" +
	    			"\n countryName: United States" +
	    			"\n region: WA" + 
	    			"\n regionName: Washington" +
	    			"\n city: Redmond" +
	    			"\n postalCode: 98052" +
	    			"\n latitude: 47.6801" +
	    			"\n longitude: -122.120605" +
	    			"\n distance: 7347.927119377516" +
	    			"\n distance: 7347.927119377516" +
	    			"\n metro code: 819" +
	    			"\n area code: 425" +
	    			"\n timezone: America/Los_Angeles";
	    
	    assertEquals(expected, output);
	    
	}
	
   
}

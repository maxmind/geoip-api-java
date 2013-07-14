package com.maxmind.geoip;

/* CityLookupV6Test.java */

import static org.junit.Assert.assertEquals;

import com.maxmind.geoip.LookupService;
import java.io.IOException;

import org.junit.Test;


public class CityLookupV6Test {
	
	@Test
    public void testCityLookupV6() throws IOException {
	
	    LookupService cl = new LookupService("GeoIP/GeoLiteCityv6.dat",
					LookupService.GEOIP_MEMORY_CACHE );
            Location l1 = cl.getLocationV6("::213.52.50.8");
            Location l2 = cl.getLocationV6("2a00:1450:4013:c00::8a");
	   
            String results = "countryCode: " + l2.countryCode +
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
	    String expected = "countryCode: IE" + 
	    		"\n countryName: Ireland" +
	    		"\n region: null" +
	    		"\n regionName: null" +
	    		"\n city: null" +
	    		"\n postalCode: null" +
	    		"\n latitude: 53.0" +
	    		"\n longitude: -8.0" +
	    		"\n distance: 1369.9140208883634" +
	    		"\n distance: 1369.9140208883634" +
	    		"\n metro code: 0" +
	    		"\n area code: 0" +
	    		"\n timezone: Europe/Dublin";
	    
	    		assertEquals(expected, results);
	
}
}

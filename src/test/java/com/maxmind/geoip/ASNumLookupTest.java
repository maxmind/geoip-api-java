package com.maxmind.geoip;

/* ASNumLookupTest.java */

import com.maxmind.geoip.LookupService;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.io.IOException;



public class ASNumLookupTest {
	
	@Test
    public void testASNumLookup() throws IOException
    {
    	    LookupService asnl = new LookupService("GeoIP/GeoIPASNum.dat");
    	    String ASNum = asnl.getOrg("64.4.4.4");
    	    asnl.close();
    	    assertEquals(ASNum, "AS8075 Microsoft Corp");
    }
	
	
   
}

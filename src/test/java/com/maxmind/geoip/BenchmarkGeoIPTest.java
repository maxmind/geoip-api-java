package com.maxmind.geoip;

import java.io.IOException;

import org.junit.Test;


public class BenchmarkGeoIPTest {

	
	public void testGeoipCountry(int flags,String msg,int numlookups) throws IOException {
		LookupService cl = new LookupService("src/test/resources/GeoIP/GeoIP.dat",flags);
		int i4 = 0;
		long t1 = System.currentTimeMillis();
		for (int i2 = 0;i2 < numlookups;i2++){
			cl.getCountry(ipstring[i4]);
			i4 = (i4 + 1) % ipstring.length;
		}
		long t2 = System.currentTimeMillis();
		double t3 = ((double) (t2-t1)) / 1000.0;
		System.out.println(msg);
		System.out.println(numlookups + " lookups made in " + t3 + " seconds ");
}

	/*
	 Need binary version of this 
	 
	public void testGeoipRegion(int flags,String msg,int numlookups){
		LookupService cl = new LookupService("GeoIP/GeoIPRegion.dat",flags);
		int i4 = 0;
		long t1 = System.currentTimeMillis();
		for (int i2 = 0;i2 < numlookups;i2++){
			cl.getRegion(ipstring[i4]);
			i4 = (i4 + 1) % ipstring.length;
		}
		long t2 = System.currentTimeMillis();
		double t3 = ((double) (t2-t1)) / 1000.0;
		System.out.println(msg);
		System.out.println(numlookups + " lookups made in " + t3 + " seconds ");

	} 
	 */

static void testGeoipCity(int flags,String msg,int numlookups) throws IOException{
  LookupService cl = new LookupService("src/test/resources/GeoIP/GeoIPCity.dat",flags);
  int i4 = 0;
  long t1 = System.currentTimeMillis();
  for (int i2 = 0;i2 < numlookups;i2++){
    cl.getLocation(ipstring[i4]);
    i4 = (i4 + 1) % ipstring.length;
  }
  long t2 = System.currentTimeMillis();
  double t3 = ((double) (t2-t1)) / 1000.0;
  System.out.println(msg);
  System.out.println(numlookups + " lookups made in " + t3 + " seconds ");
}

  static String ipstring[] = {"24.24.24.24","80.24.24.80","200.24.24.40","68.24.24.46"};
  
  @Test
  public void testBenchmarkGeoIP() throws IOException{
    // GEOIP_CHECK_CACHE is not yet supported, so that is why the lines below are commented out!

    testGeoipCountry(LookupService.GEOIP_STANDARD,"GeoIP Country",3000);
    //testGeoipCountry(LookupService.GEOIP_CHECK_CACHE,"GeoIP Country with GEOIP_CHECK_CACHE",1000);
    testGeoipCountry(LookupService.GEOIP_MEMORY_CACHE,"GeoIP Country with GEOIP_MEMORY_CACHE",30000);
    //testGeoipCountry(LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE,"GeoIP Country with GEOIP_MEMORY_CACHE",10000);

    //testGeoipRegion(LookupService.GEOIP_STANDARD,"GeoIP Region",3000);
    //testGeoipRegion(LookupService.GEOIP_CHECK_CACHE,"GeoIP Region with GEOIP_CHECK_CACHE",1000);
    //testGeoipRegion(LookupService.GEOIP_MEMORY_CACHE,"GeoIP Region with GEOIP_MEMORY_CACHE",30000);
    //testGeoipRegion(LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE,"GeoIP Region with GEOIP_MEMORY_CACHE and GEOIP_CHECK_CACHE",10000);

    testGeoipCity(LookupService.GEOIP_STANDARD,"GeoIP City",1500);
    //testGeoipCity(LookupService.GEOIP_INDEX_CACHE,"GeoIP City with GEOIP_INDEX_CACHE",2000);
    //testGeoipCity(LookupService.GEOIP_INDEX_CACHE | LookupService.GEOIP_CHECK_CACHE,"GeoIP City with GEOIP_INDEX_CACHE and GEOIP_CHECK_CACHE",2000);
    testGeoipCity(LookupService.GEOIP_MEMORY_CACHE,"GeoIP City with GEOIP_MEMORY_CACHE",15000);    
  }
}

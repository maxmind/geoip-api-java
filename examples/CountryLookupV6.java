/* Only works with GeoIP Country Edition */
/* For Geoip City Edition, use CityLookup.java */

import com.maxmind.geoip.*;
import java.io.IOException;

class CountryLookupV6 {
    public static void main(String[] args) {
	try {
	    String sep = System.getProperty("file.separator");

	    // Uncomment for windows
	    // String dir = System.getProperty("user.dir");

	    // Uncomment for Linux
	    String dir = "/usr/local/share/GeoIP";

	    String dbfile = dir + sep + "GeoIPv6.dat";
	    // You should only call LookupService once, especially if you use
	    // GEOIP_MEMORY_CACHE mode, since the LookupService constructor takes up
	    // resources to load the GeoIP.dat file into memory
	    //LookupService cl = new LookupService(dbfile,LookupService.GEOIP_STANDARD);
	    LookupService cl = new LookupService(dbfile,LookupService.GEOIP_MEMORY_CACHE);

	    System.out.println(cl.getCountryV6("ipv6.google.com").getCode());
	    System.out.println(cl.getCountryV6("::127.0.0.1").getName());
	    System.out.println(cl.getCountryV6("::151.38.39.114").getName());
	    System.out.println(cl.getCountryV6("::ffff:151.38.39.114").getName());
	    System.out.println(cl.getCountryV6("2001:4860:0:1001::68").getName());

	    cl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

/* CountryLookupTest.java */

/* Only works with GeoIP Country Edition */
/* For Geoip City Edition, use CityLookupTest.java */

import com.maxmind.geoip.*;
import java.io.IOException;

class CountryLookupTest {
    public static void main(String[] args) {
	try {
	    String sep = System.getProperty("file.separator");

	    // Uncomment for windows
	    // String dir = System.getProperty("user.dir"); 

	    // Uncomment for Linux
	    String dir = "/usr/local/share/GeoIP";

	    String dbfile = dir + sep + "GeoIP.dat"; 
	    LookupService cl = new LookupService(dbfile);

	    System.out.println(cl.getCountry("151.38.39.114").getCode());
	    System.out.println(cl.getCountry("151.38.39.114").getName());
	    System.out.println(cl.getCountry("12.25.205.51").getName());
	    System.out.println(cl.getCountry("64.81.104.131").getName());
	    System.out.println(cl.getCountry("200.21.225.82").getName());

	    cl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

/* CityLookupTest.java */

import com.maxmind.GeoIP.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API */
/* Usage: java CityLookupTest 64.4.4.4

class CityLookupTest {
    public static void main(String[] args) {
	try {
	    Lookup cl = new Lookup("/usr/local/share/GeoIP/GeoIPCity.dat");
            Location l1 = cl.lookupLocation("213.52.50.8");
            Location l2 = cl.lookupLocation(args[0]);
	    System.out.println("countryCode: " + l2.countryCode +
                               " countryName: " + l2.countryName +
                               " city: " + l2.city +
                               " postalCode: " + l2.postalCode +
                               " latitude: " + l2.latitude +
                               " longitude: " + l2.longitude +
                               " distance: " + l2.distance(l1) +
                               " distance: " + l1.distance(l2));

	    cl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

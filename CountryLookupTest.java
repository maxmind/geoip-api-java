/* CountryLookupTest.java */

import com.maxmind.GeoIP.*;
import java.io.IOException;

class CountryLookupTest {
    public static void main(String[] args) {
	try {
	    Lookup cl = new Lookup("/usr/local/share/GeoIP/GeoIP.dat");
	    System.out.println(cl.lookupCountryCode("151.38.39.114"));
	    System.out.println(cl.lookupCountryName("151.38.39.114"));
	    System.out.println(cl.lookupCountryName("12.25.205.51"));
	    System.out.println(cl.lookupCountryName("64.81.104.131"));
	    System.out.println(cl.lookupCountryName("200.21.225.82"));

	    cl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

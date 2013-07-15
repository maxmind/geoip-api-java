import com.maxmind.geoip.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP Organization and ISP databases */
/* This example can also be used with the GeoIP Domain and ASNum databases */
/* Usage: java ASNumLookupV6 64.4.4.4 */

class ASNumLookupV6 {
    public static void main(String[] args) {
	try {
	    LookupService asnl = new LookupService("/usr/local/share/GeoIP/GeoIPASNumv6.dat");
	    System.out.println("ASNum V6: " + asnl.getOrgV6(args[0]));
	    asnl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

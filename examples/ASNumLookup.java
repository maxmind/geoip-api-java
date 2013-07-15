import com.maxmind.geoip.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP Organization and ISP databases */
/* This example can also be used with the GeoIP Domain and ASNum databases */
/* Usage: java ASNumLookup 64.4.4.4 */

class ASNumLookup {
    public static void main(String[] args) {
	try {
	    LookupService asnl = new LookupService("/usr/local/share/GeoIP/GeoIPASNum.dat");
	    System.out.println("ASNum: " + asnl.getOrg(args[0]));
	    asnl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

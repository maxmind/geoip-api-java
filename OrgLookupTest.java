/* OrgLookupTest.java */

import com.maxmind.geoip.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP Organization and ISP databases */
/* Usage: java OrgLookupTest 64.4.4.4 */

class OrgLookupTest {
    public static void main(String[] args) {
	try {
	    LookupService orgl = new LookupService("/usr/local/share/GeoIP/GeoIPOrg.dat");
	    LookupService ispl = new LookupService("/usr/local/share/GeoIP/GeoIPISP.dat");
	    System.out.println("Organization: " + orgl.getOrg(args[0]) +
			       "\tISP: " + ispl.getOrg(args[0]));
	    orgl.close();
	    ispl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

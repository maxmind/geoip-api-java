/* OrgLookupTest.java */

import com.maxmind.GeoIP.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP City database */
/* Usage: java CityLookupTest 64.4.4.4 */

class OrgLookupTest {
    public static void main(String[] args) {
	try {
	    Lookup orgl = new Lookup("/usr/local/share/GeoIP/GeoIPOrg.dat");
	    Lookup ispl = new Lookup("/usr/local/share/GeoIP/GeoIPISP.dat");
	    System.out.println("Organization: " + orgl.lookupOrg(args[0]) +
			       "\tISP: " + ispl.lookupOrg(args[0]));
	    orgl.close();
	    ispl.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

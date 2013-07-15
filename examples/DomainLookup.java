import com.maxmind.geoip.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP Domain database */
/* Usage: java DomainLookup 64.4.4.4 */

class DomainLookup {
    public static void main(String[] args) {
	try {
	    LookupService gid = new LookupService("/usr/local/share/GeoIP/GeoIPDomain.dat");
	    System.out.println("Domain: " + gid.getOrg(args[0]));
	    gid.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

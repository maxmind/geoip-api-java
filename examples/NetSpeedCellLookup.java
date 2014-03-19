import com.maxmind.geoip.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP Organization and ISP databases */
/* This example can also be used with the GeoIP Domain, NetSpeedCell and ASNum databases */
/* Usage: java NetSpeedCellLookup 64.4.4.4 */

class NetSpeedCellLookup {
    public static void main(String[] args) {
	try {
	    LookupService ns = new LookupService("/usr/local/share/GeoIP/GeoIPNetSpeedCell.dat");
	    System.out.println("XX: " + ns.getOrg(args[0]));
	    ns.close();
	}
	catch (IOException e) {
	    System.out.println("IO Exception");
	}
    }
}

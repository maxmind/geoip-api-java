/* NetspeedLookup.java */

import com.maxmind.geoip.*;
import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP Netspeed database */
/* Usage: java NetspeedLookupTest 24.24.24.24 */

class NetspeedLookupTest {
    public static void main(String[] args) {
        try {
            LookupService cl = new LookupService("/usr/local/share/GeoIP/GeoIPNetspeed.dat");
            if (args.length > 0) {
                int speed = cl.getID(args[0]);
	        if (speed == cl.GEOIP_UNKNOWN_SPEED){
		    System.out.println("Unknown");
                } else if (speed == cl.GEOIP_DIALUP_SPEED) {
		    System.out.println("Dialup");
                } else if (speed == cl.GEOIP_CABLEDSL_SPEED) {
		    System.out.println("Cable/DSL");
                } else if (speed == cl.GEOIP_CORPORATE_SPEED) {
		    System.out.println("Corporate");
                }
            } else {
 	        System.out.println("input a ip address\n");
 	    }
	}
        catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
}

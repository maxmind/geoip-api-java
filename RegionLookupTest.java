/* RegionLookupTest.java */

import com.maxmind.geoip.*;
import java.io.IOException;

class RegionLookupTest {
    public static void main(String[] args) {
        try {
            LookupService cl = new LookupService("/usr/local/share/GeoIP/GeoIPRegion.dat");
            Region l = cl.getRegion(args[0]);
            System.out.println("country Code: " + l.countryCode + "\n");
            System.out.println("country Name: " + l.countryName + "\n");
            System.out.println("region: " + l.region + "\n");
            cl.close();
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
}

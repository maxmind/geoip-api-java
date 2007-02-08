/* RegionLookupTest.java */

/* Requires subscription to MaxMind GeoIP Region database */

import com.maxmind.geoip.*;
import java.io.IOException;

class RegionLookupTest {
    public static void main(String[] args) {
        try {
            LookupService cl = new LookupService("/usr/local/share/GeoIP/GeoIPRegion.dat");
            Region l = cl.getRegion(args[0]);
            System.out.println("Country Code: " + l.countryCode + "\n");
            System.out.println("Country Name: " + l.countryName + "\n");
            System.out.println("Region Code: " + l.region + "\n");
            System.out.println("Region Name: " + regionName.regionNameByCode(l.countryCode,l.region) + "\n");
            cl.close();
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
}

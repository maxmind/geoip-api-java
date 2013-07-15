/* Requires subscription to MaxMind GeoIP Region database */

import com.maxmind.geoip.*;
import java.io.IOException;

class RegionLookup {
    public static void main(String[] args) {
        try {
            LookupService cl = new LookupService("/usr/local/share/GeoIP/GeoIPRegion.dat");
            Region l = cl.getRegion(args[0]);
            System.out.println("Country Code: " + l.countryCode);
            System.out.println("Country Name: " + l.countryName);
            System.out.println("Region Code: " + l.region);
            System.out.println("Region Name: " + regionName.regionNameByCode(l.countryCode,l.region));
            cl.close();
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
}

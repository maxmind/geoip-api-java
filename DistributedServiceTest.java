// Usage: CLASSPATH=".:source" java DistributedServiceTest LICENSE_KEY 24.24.24.24

import com.maxmind.geoip.*;
import java.io.IOException;

class DistributedServiceTest {
    public static void main(String[] args) {
        try {
	    // /usr/local/share/GeoIP/GeoIPCity.dat is the location of a backup local copy of the database
            LookupService cl = new LookupService(0,args[0]);
            Location l = cl.getLocation(args[1]);
            System.out.println("countryCode: " + l.countryCode +
			       " countryName: " + l.countryName +
			       " region: " + l.region +
                               " city: " + l.city +
                               " postalCode: " + l.postalCode +
                               " latitude: " + l.latitude +
                               " longitude: " + l.longitude);
	    cl.close();
	    }
        catch (IOException e) {
            System.out.println("IO Exception");
        }

    }
}


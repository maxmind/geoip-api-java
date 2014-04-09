package com.maxmind.geoip;

/* OrgLookupTest.java */

// Need a binary sample dataset

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class NetSpeedCellLookupTest {
    @Test
    public void testNetSpeedCellLookup() throws IOException {
        LookupService ns = new LookupService(
                "src/test/resources/GeoIP/GeoIPNetSpeedCell.dat");
        assertEquals("Cable/DSL", ns.getOrg("89.66.148.0"));
        ns.close();
    }
}

#!/usr/bin/perl

use strict;
use warnings;
use autodie;

use Text::CSV_XS;

# Used to generate timeZone.java
# usage: ./generate_timeZone.pl > ../source/com/maxmind/geoip/timeZone.java

my $old_country;
my $old_region;
my $had_region;

# Obtain timezone.txt from http://www.maxmind.com/timezone.txt
open my $fh, '<', 'timezone.txt' or die $!;
<$fh>;    #header

my $csv = Text::CSV_XS->new( { binary => 1 } );

print "package com.maxmind.geoip;\n";
print "// generated automatically from admin/generate_timeZone.pl\n";
print "public class timeZone {\n";
print
    "    public static String timeZoneByCountryAndRegion(String country, String region) {\n";
print "        String timezone = null;\n";
print <<END;
        if (country == null) {
            return null;
        }
        if (region == null) {
            region = "";
        }
END

while ( my $row = $csv->getline($fh) ) {
    my ( $country, $region, $timezone ) = @$row;
    die "Wrong timezone\n" unless $timezone;
    if ( $country ne $old_country ) {
        if ($had_region) {
            print "            }\n";
            $had_region = "";
            $old_region = "";
        }
        if ( $old_country ne "" ) {
            print "        } else if (" . qq(") . $country . qq(")
                . ".equals(country)) {\n";
        }
        else {
            print "        if (" . qq(") . $country . qq(")
                . ".equals(country)) {\n";
        }
    }
    if ( $region ne "" ) {
        $had_region = 1;
        if ( $old_region ne "" ) {
            print "            } else if (" . qq(") . $region . qq(")
                . ".equals(region)) {\n  ";
        }
        else {
            print "            if (" . qq(") . $region . qq(")
                . ".equals(region)) {\n  ";
        }
    }
    elsif ( $old_region ne "" ) {
        print "            } else {\n  ";
    }
    print qq(                timezone = ") . $timezone . qq(") . ";\n";
    $old_country = $country;
    $old_region  = $region;
}
print "        }\n";
print "        return timezone;\n";
print "    }\n";
print "}\n";

close($fh);


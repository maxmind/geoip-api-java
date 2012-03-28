#!/usr/bin/perl
use Data::Dumper;
use strict;
use warnings;

# Used to generate timeZone.java
# usage: ./generate_timeZone.pl > ../source/com/maxmind/geoip/timeZone.java

my $old_country;
my $old_region;
my $had_region;

# Obtain timezone.txt from http://www.maxmind.com/timezone.txt
open(FILE,"timezone.txt") or die $!;
my $str = <FILE>;
my @time_zones = map { [ split /\t/, $_, 3 ] } <FILE>;
#print Dumper(\@time_zones);
#exit;
my $cnt_time_zones = @time_zones;
print <<__HEADER__;
package com.maxmind.geoip;

import java.util.HashMap;
import java.util.TimeZone;

// generated automatically from admin/generate_timeZone.pl
public class GeoIPTimeZone {
  private static final HashMap<String, TimeZone>TIME_ZONES = new HashMap<String, TimeZone>($cnt_time_zones);
  static {
__HEADER__

for ( @time_zones ) {
    my ( $c, $r, $tz ) = @$_;
    chomp $tz;
    my $k = $c . $r || '';
    print qq[  TIME_ZONES.put("$k", TimeZone.getTimeZone("$tz"));\n];
}
print qq[  };\n];

print <<__FOOTER__;
  static public TimeZone getTimeZone(String country, String region) {
    
    TimeZone tz;
    
    if ( country.length() != 2 ) {
        return null;
    }

    if ( region.length() == 2 ) {
      tz = TIME_ZONES.get(country + region);
      if ( tz != null ) {
        return tz;
      }
    }
    return TIME_ZONES.get(country) ;
  }

  static public String timeZoneByCountryAndRegion(String country,String region) {
    return getTimeZone( country, region ).getID() ;
  }
};
__FOOTER__

__END__
print "    String timezone = null;\n";
print <<END;
    if (country == null) {
      return null;
    }
    if (region == null) {
      region = "";
    }
END

while ($str = <FILE>) {
  $str =~ s!\s*$!!; 
  my ($country,$region,$timezone) = split(/\t/,$str);
  if ($country ne $old_country) {
    if ($had_region) {
      print "      }\n";
      $had_region = "";
      $old_region = "";
    }
    if ($old_country ne "") {
      print "    } else if (country.equals(" . qq(") . $country . qq(") . ") == true) {\n";
    } else {
      print "    if (country.equals(" . qq(") . $country . qq(") . ") == true) {\n";
    }
  }
  if ($region ne "") {
    $had_region = 1;
    if ($old_region ne "") {
      print "      } else if (region.equals(" . qq(") . $region . qq(") . ") == true) {\n  ";
    } else {
      print "      if (region.equals(" . qq(") . $region . qq(") . ") == true) {\n  ";
    }
  } elsif ($old_region ne "") {
    print "      } else {\n  ";
  }
  print qq(      timezone = ") . $timezone . qq(") . ";\n";
  $old_country = $country;
  $old_region = $region;
}
print "    }\n";
print "    return timezone;\n";
print "  }\n";
print "}\n";

close(FILE);


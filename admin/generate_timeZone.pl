#!/usr/bin/perl

use strict;

# Used to generate timeZone.java
# usage: ./generate_timeZone.pl > ../source/com/maxmind/geoip/timeZone.java

my $old_country;
my $old_region;
my $had_region;

# Obtain timezone.txt from http://www.maxmind.com/timezone.txt
open(FILE,"timezone.txt");
my $str = <FILE>;

print "package com.maxmind.geoip;\n";
print "// generated automatically from admin/generate_timeZone.pl\n";
print "public class timeZone {\n";
print "  static public String timeZoneByCountryAndRegion(String country,String region) {\n";
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
  my ($country,$region,$timezone) = split("\t",$str);
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


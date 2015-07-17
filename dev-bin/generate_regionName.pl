#!/usr/bin/perl

# Used to generate regionName.java
# usage: ./generate_regionName.pl > ../source/com/maxmind/geoip/regionName.java

use strict;
use warnings;

use HTTP::Tiny;
use Text::CSV_XS;

print <<'__JAVA_CODE__';
package com.maxmind.geoip;
// generated automatically from admin/generate_regionName.pl
public class regionName {
    public static String regionNameByCode(String country_code, String region_code) {
        String name = null;
        int region_code2 = -1;
        if (region_code == null) { return null; }
        if (region_code.length() == 0) { return null; }

        if (    ((region_code.charAt(0) >= 48 ) && ( region_code.charAt(0) < ( 48 + 10 )))
             && ((region_code.charAt(1) >= 48 ) && ( region_code.charAt(1) < ( 48 + 10 )))
        ){
            // only numbers, that shorten the large switch statements
            region_code2 = (region_code.charAt(0)- 48) * 10 + region_code.charAt(1) - 48;
        } else if (    (    ((region_code.charAt(0) >= 65) && (region_code.charAt(0) < (65 + 26)))
                     || ((region_code.charAt(0) >= 48) && (region_code.charAt(0) < (48 + 10))))
                && (    ((region_code.charAt(1) >= 65) && (region_code.charAt(1) < (65 + 26)))
                     || ((region_code.charAt(1) >= 48) && (region_code.charAt(1) < (48 + 10))))
        ) {

            region_code2 = (region_code.charAt(0) - 48) * (65 + 26 - 48) + region_code.charAt(1) - 48 + 100;
        }

        if (region_code2 == -1) {return null;}

__JAVA_CODE__

my $response = HTTP::Tiny->new->get(
    'http://www.maxmind.com/download/geoip/misc/region_codes.csv');

die "Failed to download CSV!\n" unless $response->{success};

my $csv = Text::CSV_XS->new( { binary => 1 } );
open my $sh, '<', \$response->{content};

my $last_country_code = q{};
while ( my $row = $csv->getline($sh) ) {
    my ( $country_code, $region_code, $name ) = @$row;

    my $region_code2;
    if ( $region_code =~ /^\d\d$/ ) {
        $region_code2
            = ( ord( substr( $region_code, 0, 1 ) ) - 48 ) * 10
            + ord( substr( $region_code, 1, 1 ) )
            - 48;

    }
    elsif ( $region_code =~ /^[A-Z0-9]{2}$/ ) {
        $region_code2
            = (
            ( ord( substr( $region_code, 0, 1 ) ) - 48 ) * ( 65 + 26 - 48 ) )
            + ord( substr( $region_code, 1, 1 ) )
            - 48 + 100;
    }
    elsif ( $region_code =~ /^[A-Z]{2}$/ ) {
        $region_code2
            = (
            ( ord( substr( $region_code, 0, 1 ) ) - 48 ) * ( 65 + 26 - 48 ) )
            + ord( substr( $region_code, 1, 1 ) )
            - 48 + 100;
    }
    else {
        die "Region code seems wrong $region_code\n";
    }
    readcode(
        $last_country_code, $country_code, $region_code, $region_code2,
        $name
    );
    $last_country_code = $country_code;
}
print "            }\n";
print "        }\n";
print "        return name;\n";
print "    }\n";
print "}\n";

sub readcode {
    my (
        $last_country_code, $country_code, $region_code, $region_code2,
        $name
    ) = @_;
    if ( $country_code ne $last_country_code ) {
        if ( $last_country_code ne q{} ) {
            print "            }\n";
        }

        if ( $last_country_code eq q{} ) {
            print '        if (country_code.equals(' . q{"}
                . $country_code . q{"}
                . ")) {\n";
        }
        else {
            print '        } else if (country_code.equals(' . qq(")
                . $country_code . qq(")
                . ")) {\n";
        }
        print "            switch (region_code2) {\n";
    }

    $name =~ s/\"//g;
    $name = q{"} . $name . q{"};
    print '                case ' . $region_code2 . ":\n";
    print "                    return $name;\n";
}


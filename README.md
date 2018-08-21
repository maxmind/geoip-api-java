# GeoIP Legacy Java API #

[![Build Status](https://travis-ci.org/maxmind/geoip-api-java.png?branch=master)](https://travis-ci.org/maxmind/geoip-api-java)  [![Known Vulnerabilities](https://snyk.io/test/github/maxmind/geoip-api-java/badge.svg)](https://snyk.io/test/github/maxmind/geoip-api-java)

## Important Note ##

This API is for the GeoIP Legacy format (dat). To read the MaxMind DB format
(mmdb) used by GeoIP2, please see
[our GeoIP2 Java API](https://github.com/maxmind/GeoIP2-java).


## Define Your Dependencies ##

We recommend installing this package with Maven. To do this, add the dependency to your pom.xml:

```xml
    <dependency>
        <groupId>com.maxmind.geoip</groupId>
        <artifactId>geoip-api</artifactId>
        <version>1.3.1</version>
    </dependency>
```

## Building Manually ##

### Installation ###
    mvn clean install

### Packaging ###
    mvn package

The jar file will be in the `target` directory.

### Testing ###
    mvn test

## IP Geolocation Usage ##

IP geolocation is inherently imprecise. Locations are often near the center of
the population. Any location provided by a GeoIP database or web service
should not be used to identify a particular address or household.

## Memory Caching and Other Options ##

The following options can be passed as the second parameter to the
`LookupService` constructor.

* `GEOIP_STANDARD` - Read database from file system. Uses the least memory.
* `GEOIP_MEMORY_CACHE` - Load database into memory. This provides faster
  performance but uses more memory
* `GEOIP_CHECK_CACHE` - Check for updated database. If database has been
  updated, reload file handle and/or memory cache.
* `GEOIP_INDEX_CACHE` - Cache only the most frequently accessed index portion
  of the database, resulting in faster lookups than GEOIP_STANDARD, but less
  memory usage than `GEOIP_MEMORY_CACHE`. This is useful for larger databases
  such as GeoIP Legacy Organization and GeoIP Legacy City. Note: for GeoIP
  Legacy Country, Region and Netspeed databases, `GEOIP_INDEX_CACHE` is
  equivalent to `GEOIP_MEMORY_CACHE`.

These options may be combined. For example:

```java
LookupService cl = new LookupService(dbfile,
    LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
```

## Thread Safety ##

As of version 1.1.4 this API is thread safe.


# GeoIP Legacy Java API #

## End of Life ##

MaxMind will be retiring the GeoIP Legacy databases at the end of May
2022. Until then, this library will only receive critical security and bug
fixes. Support for this library will end completely with the last release of
the legacy GeoIP databases.

We recommend that you upgrade to our GeoIP2 databases. You can read these
from Java using [our GeoIP2 Java API](https://github.com/maxmind/GeoIP2-java).

See [our blog post](https://blog.maxmind.com/2020/06/01/retirement-of-geoip-legacy-downloadable-databases-in-may-2022/)
for more information.

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


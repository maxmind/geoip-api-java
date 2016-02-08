Changes
=======

1.3.1 (2016-02-08)
------------------

* `getAllCountryNames()` and `getAllCountryCodes()` were added to the
  `LookupService`. These return all country names and codes known to the
  GeoIP reader. Pull request by nguillaumin. GitHub #33.

1.3.0 (2016-01-21)
------------------

* `LookupService` will now throw an `InvalidDatabaseException` if there is a
  problem with the database. This is a unchecked, runtime exception in order
  to not introduce an API change. Previously `LookupService` would swallow
  exceptions, either returning `null` or an invalid value or throwing an
  exception such as `ArrayIndexOutOfBoundsException` cause by the invalid
  state.
* When using `GEOIP_MEMORY_CACHE`, the number of allocations has been reduced,
  providing a moderate performance increase.
* Minor code clean-up and de-duplication.

1.2.15 (2015-07-17)
-------------------

* Several threading synchronization issues were fixed.
* Target version was decreased to 1.5 for increased compatibility.
* Minor code cleanup.
* Region and timezone data were updated.

1.2.14 (2014-07-23)
-------------------

* OSGi metadata was added to the jar. (Miguel Ángel Pastor Olivar)
* Regions were updated.

1.2.13 (2014-04-30)
-------------------

* Previously no checks were done to ensure that an organization name did not
  exceed the 300 character maximum. If an organization name did exceed this
  maximum, an `ArrayIndexOutOfBoundsException` was thrown.
* A potential string comparison issue was fixed in `getLocation`.
* Documentation and test fixes.

1.2.12 (2014-03-10)
-------------------

* Code and examples for the non-functional distributed lookup service were
  removed.
* A bug that could cause an `ArrayIndexOutOfBoundsException` when using
  `GEOIP_INDEX_CACHE` was fixed. On the last node, the reader would attempt
  to read beyond the end of the buffer due to the incorrect record length
  being used.

1.2.11 (2013-09-19)
-------------------

* Fix IPv6 lookups with ffff prefix. (Boris Zentner )
* File reorganization and unit tests ( Travis Pinney )

1.2.10 (2013-05-23)
-------------------

* First release to the Maven Central Repository

1.2.9 (2013-02-19)
------------------

* Update FIPS codes 20130208 ( Boris Zentner )
* Add new country South Sudan ( Boris Zentner )

1.2.8 (2013-04-13)
------------------

* Fix: Close the database file without exceptions whether it is
  open(STANDARD) or not(MEMORY_CACHE) in conjunction with the
  CHECK_CACHE option. (Lorenzo Crosby)

1.2.7 (2012-04-10)
------------------

* Update timezones ( Boris Zentner )
* Update FIPS codes 20120402 ( Boris Zentner )

1.2.6 (2012-03-28)
------------------

* Update country codes, FIPS codes and timezone ( Boris Zentner )

1.2.5 (2011-08-23)
------------------

* Fix small buffer issue when java tries to read over the EOF ( Boris Zentner )
* Add support for new database types NETSPEED_EDITION_REV1_V6,
  NETSPEED_EDITION_REV1, ASNUM_EDITION_V6, CITY_EDITION_REV1_V6,
  ISP_EDITION_V6, ORG_EDITION_V6, DOMAIN_EDITION, DOMAIN_EDITION_V6 ( Boris Zentner )
* Add new examples CityLookupTestV6 and ASNumLookupTestV6 ( Boris Zentner )

1.2.4 (2010-08-13)
------------------

* Update FIPS codes 20100810 ( Boris Zentner )
* Simplify init code in class LookupServices ( Boris Zentner )
* Big timezone update ( Boris Zentner )
* Update FIPS codes 20100530 ( Boris Zentner )
* Update FIPS codes 20100510 ( Boris Zentner )
* Add IPv6 support via getCountryV6 ( Boris Zentner )
* Add example for IPv6 lookups: CountryLookupTestV6 ( Boris Zentner )
* Fix DatabaseInfo string ( Boris Zentner )
* Add netmask and last_netmask methods ( Boris Zentner )
* Remove static keyword from objects that are reinitialized in the init()
  method. ( Tony Jacobs )
* Replace nearly all calls to file.read() with file.readFully() ( Tony Jacobs )
* Synchronized 100% of the calls to file ( Tony Jacobs )
* Use Integer.valueOf() instead of new Integer() to reduce memory consumption.
  ( Tony Jacobs )
* Use explicit imports instead of import java.io.*; and the like. ( Tony
  Jacobs )
* Fix array length in distributed service setup ( quarz12h )
* Sync with recent FIPS codes 20090723 ( Boris Zentner )

1.2.3  (2009-05-11)
-------------------

* Sync with recent FIPS codes 20090511 ( Boris Zentner )
* Fixed spelling of Kazakhstan, was Kazakstan ( Boris Zentner )
* Fix rare out of range error, when the last entry of the Org/ISP/Domain
  database is copied ( MEMORY_CACHE only ). ( Boris Zentner )
* Update timezones for Australia
* Remove "\n" from RegionLookupTest.java. println add already a newline
  ( Boris Zentner )
* Change regionName.java and generate_regionName.pl to support FIPS codes
  with letters ( Boris Zentner ).
* Sync with recent FIPS codes Jan 14th, 2009 ( Boris Zentner )
* remove dma_code from city records. use metro_code instead. ( Boris Zentner )

1.2.2 (2008-03-22)
------------------

* Added BL/Saint Barthelemy, MF/Saint Martin (ISO-3166-1 additions)
* Updated timeZone.java ( Boris Zentner )
* Add missing \n to admin/generate_timeZone.pl ( Boris Zentner )
* Fix refresh databaseInfo in combination with GEOIP_CHECK_CACHE ( Boris
  Zentner, Glenn Pedersen )
* Changed license from GPL to LGPL, to allow commercial programs to include GeoIP API

1.2.1 (2007-06-19)
------------------

* Made GeoIP ASNum database work with Java API
* Added timeZone and regionName classes to lookup timezone and region name
  from country code and region code (Frank Mather)

1.2.0 (2007-01-10)
------------------

* Added support for GEOIP_CHECK_CACHE (Frank Mather)
* Allow database to be closed in memory cache mode
* Replaced CS/Serbia and Montenegro with RS/Serbia, removed ZR/Zaire, added ME/Montenegro
* Added AX/Aland Islands, GG/Guernsey, IM/Isle of Man, JE/Jersey (ISO-3166-1 changes)
* Added support for GEOIP_INDEX_CACHE (Frank Mather)
* close filehandle after data is read into memory for MemoryCache mode
* Added BenchmarkGeoIP.java (Frank Mather)

1.1.8 (2006-08-18)
------------------

* Increased FULL_RECORD_LENGTH from 50 to 60, fixes Array out
  ArrayOutOfBounds=50 for long city names in GeoIP City databases before July
  2006
* TP/East Timor changed to TL/Timor-Leste, reflecting changes in ISO-3166
* Removed hashmap initialization for local database
* Replaced Yugoslavia with Serbia and Montenegro

1.1.7 (2004-10-06)
------------------

* Specified ISO-8859-1 encoding for City, ISP and Organization field string
  (Yan Zhang Chen)

1.1.6 (2004-09-09)
------------------

* Fixed problem with postalCode, city and region fields
  returning null char (Frank Mather)
* Added support for GeoIP Netspeed Database (Frank Mather)
* Added support for GEOIP_MEMORY_CACHE (Frank Mather)

1.1.5 (2004-06-03)
------------------

* Added support for MaxMind GeoIP Region (Frank Mather)
* Windows compatibility fix for CountryLookupTest.java (Hanan Al-Shargi)
* Country name fixes

1.1.4 (2003-10-21)
------------------

* Added support for distributed queries (Frank Mather)
* Massive code cleanup (Matt Tucker)
* Added support for DMA code and Area codes (Frank Mather)

1.1.3 (2003-06-09)
------------------

* Added support for GeoIP Organization and ISP databases
* Changed Taiwan, Province of China to Taiwan

1.1.2 (2002-12-16)
------------------
* Added support for postal codes in GeoIP City (beta)

1.1.1 (2002-10-31)
------------------

* Added support for GeoIP Full Edition, renamed CountryLookup class
  to Lookup

1.1.0 (2002-10-28)
------------------

* API Change!!! Return null instead of '--' if country can not be found
* rewrote seekCountry to use loop instead of recursion for faster performance
* Fixed error when looking Anonymous Proxies

1.0.4 (2002-09-11)
------------------

* Added close method to clean up filehandles (Jeroen Nijenhuis)

0.3.0 (2002-09-04)
------------------

* Added support for satellite providers

0.2.1 (2002-06-03)
------------------

* Added support for anonymous proxies

0.1.2 (2002-04-14)
------------------

* Added public to CountryLookup class (Noel Bergman), should
now be accessible outside of GeoIP package.

0.1.1 (2002-04-09)
------------------

* Initial Release

#!/usr/bin/env sh
mkdir -p GeoIP
curl http://download.maxmind.com/download/geoip/database/asnum/GeoIPASNum.dat.gz -o GeoIP/GeoIPASNum.dat.gz
gunzip GeoIP/GeoIPASNum.dat.gz
curl http://download.maxmind.com/download/geoip/database/asnum/GeoIPASNumv6.dat.gz -o GeoIP/GeoIPASNumv6.dat.gz
gunzip GeoIP/GeoIPASNumv6.dat.gz
curl http://geolite.maxmind.com/download/geoip/database/GeoLiteCityv6-beta/GeoLiteCityv6.dat.gz -o GeoIP/GeoLiteCityv6.dat.gz 
gunzip GeoIP/GeoLiteCityv6.dat.gz
curl http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz -o GeoIP/GeoLiteCity.dat.gz
gunzip GeoIP/GeoLiteCity.dat.gz
curl http://geolite.maxmind.com/download/geoip/database/GeoIPv6.dat.gz -o GeoIP/GeoIPv6.dat.gz
gunzip GeoIP/GeoIPv6.dat.gz
curl http://geolite.maxmind.com/download/geoip/database/GeoLiteCountry/GeoIP.dat.gz -o GeoIP/GeoIP.dat.gz
gunzip GeoIP/GeoIP.dat.gz
# this sample dataset needs to be converted to a binary dataset
# curl https://www.maxmind.com/GeoIPDomainSample.csv -o GeoIP/GeoIPDomainSample.csv
# this sample dataset needs to be converted to a binary dataset
# curl https://www.maxmind.com/sample/GeoIP_177_Sample_NetspeedWithCellular.csv -o GeoIP/GeoIP_177_Sample_NetspeedWithCellular.csv
# this sample dataset needs to be converted to a binary dataset
# curl https://www.maxmind.com/GeoIPOrgSample.csv -o GeoIP/GeoIPOrgSample.csv
# this sample dataset needs to be converted to a binary dataset
# curl https://www.maxmind.com/regionSample.csv -o GeoIP/regionSample.csv




#mvn compile
#mvn exec:java -Dexec.mainClass="com.berico.clavin.resolver.impl.lucene.GeonamesIndexBuilder" -Dexec.args="./IndexDirectory allCountries.txt"
#mvn package


/* Lookup.java
 *
 * Copyright (C) 2002 MaxMind.com.  All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.maxmind.GeoIP;

import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.lang.Exception;
import java.net.UnknownHostException;
import com.maxmind.GeoIP.*;

public class Lookup {
    RandomAccessFile file = null;
    byte databaseType = COUNTRY_EDITION;
    int databaseSegments[];

    private final static int RECORD_LENGTH = 3;
    private final static int COUNTRY_BEGIN = 16776960;
    private final static int STATE_BEGIN   = 16700000;
    private final static int STRUCTURE_INFO_MAX_SIZE = 20;

    private final static int COUNTRY_EDITION = 106;
    private final static int REGION_EDITION  = 112;
    private final static int CITY_EDITION    = 111;

    private final static int FULL_RECORD_LENGTH = 50;

    private final static String[] countryCode = { "--","AP","EU","AD","AE","AF","AG","AI","AL","AM","AN","AO","AQ","AR","AS","AT","AU","AW","AZ","BA","BB","BD","BE","BF","BG","BH","BI","BJ","BM","BN","BO","BR","BS","BT","BV","BW","BY","BZ","CA","CC","CD","CF","CG","CH","CI","CK","CL","CM","CN","CO","CR","CU","CV","CX","CY","CZ","DE","DJ","DK","DM","DO","DZ","EC","EE","EG","EH","ER","ES","ET","FI","FJ","FK","FM","FO","FR","FX","GA","GB","GD","GE","GF","GH","GI","GL","GM","GN","GP","GQ","GR","GS","GT","GU","GW","GY","HK","HM","HN","HR","HT","HU","ID","IE","IL","IN","IO","IQ","IR","IS","IT","JM","JO","JP","KE","KG","KH","KI","KM","KN","KP","KR","KW","KY","KZ","LA","LB","LC","LI","LK","LR","LS","LT","LU","LV","LY","MA","MC","MD","MG","MH","MK","ML","MM","MN","MO","MP","MQ","MR","MS","MT","MU","MV","MW","MX","MY","MZ","NA","NC","NE","NF","NG","NI","NL","NO","NP","NR","NU","NZ","OM","PA","PE","PF","PG","PH","PK","PL","PM","PN","PR","PS","PT","PW","PY","QA","RE","RO","RU","RW","SA","SB","SC","SD","SE","SG","SH","SI","SJ","SK","SL","SM","SN","SO","SR","ST","SV","SY","SZ","TC","TD","TF","TG","TH","TJ","TK","TM","TN","TO","TP","TR","TT","TV","TW","TZ","UA","UG","UM","US","UY","UZ","VA","VC","VE","VG","VI","VN","VU","WF","WS","YE","YT","YU","ZA","ZM","ZR","ZW","A1","A2","O1"};

    private final static String[] countryName = {"N/A","Asia/Pacific Region","Europe","Andorra","United Arab Emirates","Afghanistan","Antigua and Barbuda","Anguilla","Albania","Armenia","Netherlands Antilles","Angola","Antarctica","Argentina","American Samoa","Austria","Australia","Aruba","Azerbaijan","Bosnia and Herzegovina","Barbados","Bangladesh","Belgium","Burkina Faso","Bulgaria","Bahrain","Burundi","Benin","Bermuda","Brunei Darussalam","Bolivia","Brazil","Bahamas","Bhutan","Bouvet Island","Botswana","Belarus","Belize","Canada","Cocos (Keeling) Islands","Congo, The Democratic Republic of the","Central African Republic","Congo","Switzerland","Cote D'Ivoire","Cook Islands","Chile","Cameroon","China","Colombia","Costa Rica","Cuba","Cape Verde","Christmas Island","Cyprus","Czech Republic","Germany","Djibouti","Denmark","Dominica","Dominican Republic","Algeria","Ecuador","Estonia","Egypt","Western Sahara","Eritrea","Spain","Ethiopia","Finland","Fiji","Falkland Islands (Malvinas)","Micronesia, Federated States of","Faroe Islands","France","France, Metropolitan","Gabon","United Kingdom","Grenada","Georgia","French Guiana","Ghana","Gibraltar","Greenland","Gambia","Guinea","Guadeloupe","Equatorial Guinea","Greece","South Georgia and the South Sandwich Islands","Guatemala","Guam","Guinea-Bissau","Guyana","Hong Kong","Heard Island and McDonald Islands","Honduras","Croatia","Haiti","Hungary","Indonesia","Ireland","Israel","India","British Indian Ocean Territory","Iraq","Iran, Islamic Republic of","Iceland","Italy","Jamaica","Jordan","Japan","Kenya","Kyrgyzstan","Cambodia","Kiribati","Comoros","Saint Kitts and Nevis","Korea, Democratic People's Republic of","Korea, Republic of","Kuwait","Cayman Islands","Kazakstan","Lao People's Democratic Republic","Lebanon","Saint Lucia","Liechtenstein","Sri Lanka","Liberia","Lesotho","Lithuania","Luxembourg","Latvia","Libyan Arab Jamahiriya","Morocco","Monaco","Moldova, Republic of","Madagascar","Marshall Islands","Macedonia, the Former Yugoslav Republic of","Mali","Myanmar","Mongolia","Macau","Northern Mariana Islands","Martinique","Mauritania","Montserrat","Malta","Mauritius","Maldives","Malawi","Mexico","Malaysia","Mozambique","Namibia","New Caledonia","Niger","Norfolk Island","Nigeria","Nicaragua","Netherlands","Norway","Nepal","Nauru","Niue","New Zealand","Oman","Panama","Peru","French Polynesia","Papua New Guinea","Philippines","Pakistan","Poland","Saint Pierre and Miquelon","Pitcairn","Puerto Rico","Palestinian Territory, Occupied","Portugal","Palau","Paraguay","Qatar","Reunion","Romania","Russian Federation","Rwanda","Saudi Arabia","Solomon Islands","Seychelles","Sudan","Sweden","Singapore","Saint Helena","Slovenia","Svalbard and Jan Mayen","Slovakia","Sierra Leone","San Marino","Senegal","Somalia","Suriname","Sao Tome and Principe","El Salvador","Syrian Arab Republic","Swaziland","Turks and Caicos Islands","Chad","French Southern Territories","Togo","Thailand","Tajikistan","Tokelau","Turkmenistan","Tunisia","Tonga","East Timor","Turkey","Trinidad and Tobago","Tuvalu","Taiwan, Province of China","Tanzania, United Republic of","Ukraine","Uganda","United States Minor Outlying Islands","United States","Uruguay","Uzbekistan","Holy See (Vatican City State)","Saint Vincent and the Grenadines","Venezuela","Virgin Islands, British","Virgin Islands, U.S.","Vietnam","Vanuatu","Wallis and Futuna","Samoa","Yemen","Mayotte","Yugoslavia","South Africa","Zambia","Zaire","Zimbabwe","Anonymous Proxy","Satellite Provider","Other"};

    public static int unsignedByteToInt(byte b) {
        return (int) b & 0xFF;
    }

    private void setupSegments() {
        int i, j;
        byte [] delim = new byte[3];
        byte [] buf = new byte[RECORD_LENGTH];

        try {
            file.seek(file.length() - 3);
            for (i = 0; i < STRUCTURE_INFO_MAX_SIZE; i++) {
                file.read(delim);
                if (delim[0] == -1 && delim[1] == -1 && delim[2] == -1) {
                    databaseType = file.readByte();
                    if (databaseType == REGION_EDITION) {
                        databaseSegments = new int[1];
                        databaseSegments[0] = STATE_BEGIN;
                    } else if (databaseType == CITY_EDITION) {
                        databaseSegments = new int[1];
                        databaseSegments[0] = 0;
                        file.read(buf);
                        for (j = 0; j < RECORD_LENGTH; j++) {
                            databaseSegments[0] += (unsignedByteToInt(buf[j]) << (j * 8));
                        }
                    }
                    break;
                } else {
                    file.seek(file.getFilePointer() - 4);
                }
            }
            if (databaseType == COUNTRY_EDITION) {
                databaseSegments = new int[1];
                databaseSegments[0] = COUNTRY_BEGIN;
            }
        }
        catch (IOException e) {
	    System.out.println("IO Exception while seting up segments");
	}
    }

    public Lookup(String databaseFile) {
	try {
	    this.file = new RandomAccessFile(databaseFile, "r");
	}
	catch (FileNotFoundException e) {
	    System.out.println("File " + databaseFile + " not found");
	}
	catch (IOException e) {
	    System.out.println("IO Exception while opening " + databaseFile);
	}
        setupSegments();
    }

    public void close() throws IOException
        {
	    this.file.close();
	}

    public String lookupCountryCode(String str) {
	InetAddress addr;
	try {
	    addr = InetAddress.getByName(str);
	}
	catch (UnknownHostException e) {
	    return null;
	}
	return lookupCountryCode(addr);
    }

    private long addrToNum(InetAddress addr) {
	long ipnum = 0;
	byte[] b = addr.getAddress();
	for (int i = 0; i < 4; ++i) {
	    long y = b[i];
	    if (y < 0) {
		y+= 256;
	    }
	    ipnum += y << ((3-i)*8);
	}
	return ipnum;
    }

    public String lookupCountryCode(InetAddress addr) {
        int ret;
        ret = seekCountry(addrToNum(addr)) - COUNTRY_BEGIN;
        if (ret == 0) {
            return null;
        } else {
            return countryCode[ret];
        }
    }

    public String lookupCountryName(String str) {
	InetAddress addr;
	try {
	    addr = InetAddress.getByName(str);
	}
	catch (UnknownHostException e) {
	    return null;
	}
	return lookupCountryName(addr);
    }

    public String lookupCountryName(InetAddress addr) {
        int ret;
        ret = seekCountry(addrToNum(addr)) - COUNTRY_BEGIN;
        if (ret == 0) {
            return null;
        } else {
            return countryName[ret];
        }
    }

    // for GeoIP City only
    public Location lookupLocation(String str) {
	InetAddress addr;
	try {
	    addr = InetAddress.getByName(str);
	}
	catch (UnknownHostException e) {
	    return null;
	}
	return lookupLocation(addr);
    }

    // for GeoIP City only
    public Location lookupLocation(InetAddress addr) {
        return getRecord(addrToNum(addr));
    }

    private Location getRecord(long ipnum) {
        int record_pointer;
        byte record_buf[] = new byte[FULL_RECORD_LENGTH];
        int record_buf_offset = 0;
        Location record = new Location();
        int str_length = 0;
        int j, seek_country;
        double latitude = 0, longitude = 0;

        try {
            seek_country = seekCountry(ipnum);
            if (seek_country == databaseSegments[0]) {
                return null;
            }

            record_pointer = seek_country + (2 * RECORD_LENGTH - 1) * databaseSegments[0];

            file.seek(record_pointer);
            file.read(record_buf);

            // get country
            record.countryCode = countryCode[unsignedByteToInt(record_buf[0])];
            record.countryName = countryName[unsignedByteToInt(record_buf[0])];
            record_buf_offset++;

            // get region
            while (record_buf[record_buf_offset + str_length] != '\0')
                str_length++;
            if (str_length > 0) {
                record.region = new String(record_buf, record_buf_offset, str_length+1);
            }
            record_buf_offset += str_length + 1;
            str_length = 0;

            // get city
            while (record_buf[record_buf_offset + str_length] != '\0')
                str_length++;
            if (str_length > 0) {
                record.city = new String(record_buf, record_buf_offset, str_length+1);
            }
            record_buf_offset += (str_length + 1);
            str_length = 0;

            // get postal code
            while (record_buf[record_buf_offset + str_length] != '\0')
                str_length++;
            if (str_length > 0) {
                record.postalCode = new String(record_buf, record_buf_offset, str_length+1);
            }
            record_buf_offset += (str_length + 1);

            // get latitude
            for (j = 0; j < 3; j++)
                latitude += (unsignedByteToInt(record_buf[record_buf_offset + j]) << (j * 8));
            record.latitude = (float) latitude/10000 - 180;
            record_buf_offset += 3;

            // get longitude
            for (j = 0; j < 3; j++)
                longitude += (unsignedByteToInt(record_buf[record_buf_offset + j]) << (j * 8));
            record.longitude = (float) longitude/10000 - 180;
        }
        catch (IOException e) {
	    System.out.println("IO Exception while seting up segments");
	}
        return record;
    }

    private int seekCountry(long ipnum) {
	byte [] buf = new byte[6];
	int [] x = new int[2];
        int offset = 0;
        for (int depth = 31; depth >= 0; depth--) {
            try {
                file.seek(6 * offset);
                file.read(buf);
            }
            catch (IOException e) {
                System.out.println("IO Exception");
            }
            for (int i = 0; i<2; i++) {
                x[i] = 0;
                for (int j = 0; j<3; j++) {
                    int y = buf[i*3+j];
                    if (y < 0) {
                        y+= 256;
                    }
                    x[i] += (y << (j * 8));
                }
            }

            if ((ipnum & (1 << depth)) > 0) {
                if (x[1] >= databaseSegments[0]) {
                    return x[1];
                }
                offset = x[1];
            } else {
                if (x[0] >= databaseSegments[0]) {
                    return x[0];
                }
                offset = x[0];
	    }
	}

        /* shouldn't reach here */
        System.out.println("Error seeking country while seeking " + ipnum);
	//    throw new Exception("Error reached depth 0");
        return 0;
    }
}

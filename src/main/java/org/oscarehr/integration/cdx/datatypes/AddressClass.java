/**
 * Copyright (c) 2013-2015. Department of Computer Science, University of Victoria. All Rights Reserved.
 * This software is published under the GPL GNU General Public License.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * This software was written for the
 * Department of Computer Science
 * LeadLab
 * University of Victoria
 * Victoria, Canada
 */
package org.oscarehr.integration.cdx.datatypes;

import oscar.util.StringUtils;

public class AddressClass {
	String addressUse;
	String streetAddress1;
	String streetAddress2;
	String city;
	String province;
	String postalCode;

	public AddressClass(String sAddressUse, String sStreetAddr1, String sStreetAddr2, String sCity, String sProv, String sPCode) {
		if (sAddressUse == "Home" || sAddressUse == "Work") {
			addressUse = sAddressUse;
		} else {
			addressUse = "Home";
		}
		this.streetAddress1 = sStreetAddr1;
		this.streetAddress2 = sStreetAddr2;
		this.city = sCity;
		this.province = sProv;
		this.postalCode = sPCode;

	}

	public String getAddressUse() {
		return addressUse;
	}

	public void setAddressUse(String addressUse) {
		this.addressUse = addressUse;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String GetAddressUseCode() {
		if (!StringUtils.isNullOrEmpty(addressUse) && addressUse.equals("Work")) {
			return "W";
		}

		return "H";
	}
}

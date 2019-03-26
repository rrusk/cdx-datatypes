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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.oscarehr.util.MiscUtils;

import oscar.util.StringUtils;

public class PatientClass {
	String PHN; // Whatever other IDs the patient has, we need the PHN for indexing
	List<IDClass> patientIds;
	NameClass name;
	AddressClass address;
	DateTime dateOfBirth;
	String sex;
	String telecom;

	private static final Logger logger = MiscUtils.getLogger();

	public PatientClass() {
		PHN = "";
		patientIds = new ArrayList<IDClass>();
		// dateOfBirth = DateTime.Parse("1900-01-01")
		name = new NameClass();
		address = new AddressClass("", "", "", "", "", "");
		sex = "Unknown";
		telecom = "";
		dateOfBirth = new DateTime("1900-01-01");
	}

	public Boolean FourPointMatching(PatientClass otherPt) {
		// If the PHNs don't match, fail.
		this.GetPHNFromIDs();
		otherPt.GetPHNFromIDs();

		if (!this.PHN.equals(otherPt.PHN)) {
			return false;
		}

		// Conformance requirement: match patient sex, last name, date of birth.

		if (((StringUtils.isNullOrEmpty(sex) && StringUtils.isNullOrEmpty(otherPt.sex)) ||
				(!StringUtils.isNullOrEmpty(sex) && !StringUtils.isNullOrEmpty(otherPt.sex) && sex.equals(otherPt.sex))) &&
				((StringUtils.isNullOrEmpty(name.familyName) && StringUtils.isNullOrEmpty(otherPt.name.familyName)) ||
						(!StringUtils.isNullOrEmpty(name.familyName) && !StringUtils.isNullOrEmpty(otherPt.name.familyName) &&
								name.familyName.toUpperCase().equals(otherPt.name.familyName.toUpperCase())))
		        && (dateOfBirth.equals(otherPt.dateOfBirth))) {
			return true;
		}

		return false;
	}

	// Set the PHN to the first ID with a "Default" category
	public void GetPHNFromIDs() {
		PHN = "";
		if (patientIds != null && patientIds.size() > 0) {
			PatientIDList idlist = new PatientIDList();
			for (IDClass id : patientIds) {
				if (!StringUtils.isNullOrEmpty(id.root) && !StringUtils.isNullOrEmpty(id.extension) &&
						idlist.GetCategoryFromCode(id.root).equals("Default")) {
					PHN = id.extension;
					return;
				}
			}
		}
	}

	// Look for IHA Meditech Account Number.
	// If you can't find the Account Number, return the last ID with an "IHA" category
	public String GetIHAIDFromIDs() {
		if (patientIds != null && patientIds.size() > 0) {
			String bestId = "";
			PatientIDList idlist = new PatientIDList();
			for (IDClass id : patientIds) {
				if (!StringUtils.isNullOrEmpty(id.root) && !StringUtils.isNullOrEmpty(id.extension) &&
						idlist.GetCategoryFromCode(id.root).equals("IHA")) {
					if (idlist.GetNameFromCode(id.root).contains("Account")) {
						return id.extension;
					}
					bestId = id.extension;
				}
			}
			if (!StringUtils.isNullOrEmpty(bestId)) {
				return bestId;
			}
		}
		return "";
	}

	// Return the last ID with the given category
	public String FindIdWithCategory(String idCategory) {
		if (patientIds != null && patientIds.size() > 0) {
			PatientIDList idlist = new PatientIDList();
			for (IDClass id : patientIds) {
				if (!StringUtils.isNullOrEmpty(id.root) && !StringUtils.isNullOrEmpty(id.extension) &&
						idlist.GetCategoryFromCode(id.root).equals(idCategory)) {
					return id.extension;
				}
			}
		}
		return "";
	}

	@Override
	// Equality is entirely based on at least one ID matching.
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		PatientClass other = (PatientClass) obj;
		if (patientIds == null) {
			if (other.patientIds != null) return false;
		}
		Boolean idMatch = false;
		for (IDClass id : this.patientIds) {
			for (IDClass otherid : other.patientIds) {
				if ((!StringUtils.isNullOrEmpty(id.root) && !StringUtils.isNullOrEmpty(id.extension)) &&
						(!StringUtils.isNullOrEmpty(otherid.root) && !StringUtils.isNullOrEmpty(otherid.extension)) &&
						(id.root.equals(otherid.root) && id.extension.equals(otherid.extension))) {
					idMatch = true;
					logger.info("Matching Patients ID:" + id.root + " " + id.extension);
				}
			}
		}

		return idMatch;
	}

	// TODO: Fix hashCode.  This hashCode() DOES NOT match Equality!!!
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patientIds == null) ? 0 : patientIds.hashCode());
		return result;
	}

	public String getPHN() {
		return PHN;
	}

	public void setPHN(String pHN) {
		PHN = pHN;
	}

	public List<IDClass> getPatientIds() {
		return patientIds;
	}

	public void setPatientIds(List<IDClass> patientIds) {
		this.patientIds = patientIds;
	}

	public NameClass getName() {
		return name;
	}

	public void setName(NameClass name) {
		this.name = name;
	}

	public AddressClass getAddress() {
		return address;
	}

	public void setAddress(AddressClass address) {
		this.address = address;
	}

	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelecom() {
		return telecom;
	}

	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}
}

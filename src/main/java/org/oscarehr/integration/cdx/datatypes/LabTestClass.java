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

public class LabTestClass {
	IDClass id;
	IDClass code;
	String resultStatus;
	DateTime resultTime; // OBX.14
	String value;
	String unit;
	String valueType;
	IDClass interpretationCode;
	String referenceRange;
	// String referenceRangeLow;
	// String referenceRangeHigh;
	// ProviderClass performer;
	LocationClass performingLocation;
	String comment;

	public LabTestClass() {
		id = new IDClass("", "", "");
		code = new IDClass("", "", "");
		resultStatus = "";
		resultTime = new DateTime("1900-01-01");
		value = "";
		unit = "";
		valueType = "";
		referenceRange = "";
		interpretationCode = new IDClass("", "", "");
		// performer = new ProviderClass();
		performingLocation = new LocationClass();
		comment = "";
	}

	public IDClass getId() {
		return id;
	}

	public void setId(IDClass id) {
		this.id = id;
	}

	public IDClass getCode() {
		return code;
	}

	public void setCode(IDClass code) {
		this.code = code;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public DateTime getResultTime() {
		return resultTime;
	}

	public void setResultTime(DateTime resultTime) {
		this.resultTime = resultTime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public IDClass getInterpretationCode() {
		return interpretationCode;
	}

	public void setInterpretationCode(IDClass interpretationCode) {
		this.interpretationCode = interpretationCode;
	}

	public String getReferenceRange() {
		return referenceRange;
	}

	public void setReferenceRange(String referenceRange) {
		this.referenceRange = referenceRange;
	}

	public LocationClass getPerformingLocation() {
		return performingLocation;
	}

	public void setPerformingLocation(LocationClass performingLocation) {
		this.performingLocation = performingLocation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

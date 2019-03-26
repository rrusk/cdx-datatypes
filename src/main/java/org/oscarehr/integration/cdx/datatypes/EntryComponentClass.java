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

public class EntryComponentClass {
	// EntryType type;
	IDClass templateId;
	IDClass code;
	String statusCode;
	String value;
	String valueUnit;
	String valueType;
	//       IDClass interpretationCode;
	//       ProviderClass performer;
	//       String referenceRange;
	//       String referenceRangeHigh;
	//       String referenceRangeLow;
	public IDClass getTemplateId() {
		return templateId;
	}
	public void setTemplateId(IDClass templateId) {
		this.templateId = templateId;
	}
	public IDClass getCode() {
		return code;
	}
	public void setCode(IDClass code) {
		this.code = code;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValueUnit() {
		return valueUnit;
	}
	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
}

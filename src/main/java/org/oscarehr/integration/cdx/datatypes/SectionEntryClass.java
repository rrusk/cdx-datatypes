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

import java.util.List;

public class SectionEntryClass {
	// EntryType type;
	IDClass entryTemplate;
	IDClass id;
	IDClass code;
	String statusCode;
	DateTime effectiveTime;
	// List<SectionEntryClass> entries;
	List<EntryComponentClass> components;
	public IDClass getEntryTemplate() {
		return entryTemplate;
	}
	public void setEntryTemplate(IDClass entryTemplate) {
		this.entryTemplate = entryTemplate;
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
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public DateTime getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(DateTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public List<EntryComponentClass> getComponents() {
		return components;
	}
	public void setComponents(List<EntryComponentClass> components) {
		this.components = components;
	}
}

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

public class RelatedDocumentClass {
	DocumentRelation type;
	IDClass id;
	// public IDClass code;

	public DocumentRelation getType() {
		return type;
	}

	public void setType(DocumentRelation type) {
		this.type = type;
	}

	public IDClass getId() {
		return id;
	}

	public void setId(IDClass id) {
		this.id = id;
	}

	public RelatedDocumentClass() {
		type = DocumentRelation.replace;
		id = new IDClass("", "", "");
		// code = new IDClass("","","");
	}

	public String GetDocumentRelationCode() {
		String relationCode = "";
		switch (this.type) {
		case append:
			relationCode = "APND";
			break;
		case replace:
			relationCode = "RPLC";
			break;
		case transform:
			relationCode = "XFRM";
			break;
		}
		return relationCode;
	}

	public String GetDocumentRelationName() {
		String relationName = "";
		switch (this.type) {
		case append:
			relationName = "Append";
			break;
		case replace:
			relationName = "Replace";
			break;
		case transform:
			relationName = "Transform";
			break;
		}
		return relationName;
	}

	public void SetDocumentRelationFromCode(String relationCode) {

		if ("APND".equals(relationCode)) {
			this.type = DocumentRelation.append;
		}
		if ("RPLC".equals(relationCode)) {
			this.type = DocumentRelation.replace;
		}
		if ("XFRM".equals(relationCode)) {
			this.type = DocumentRelation.transform;
		}
	}
}

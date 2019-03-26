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

public class IDClass {
	String root;
	String extension;
	String idType; // Used for IDs
	String displayName; // Used for codes

	public IDClass(String sRoot, String sExtension, String sIdType) {
		this.root = sRoot;
		this.extension = sExtension;
		this.idType = sIdType;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.trim().hashCode());
		result = prime * result + ((root == null) ? 0 : root.trim().hashCode());
		return result;
	}

	// The perfect case: both root and extension are non-null and the same.
	// Also acceptable: both roots are blank, extension is the same,
	// Minimally acceptable: roots and extensions are all blank.
	// the idType and displayName fields are irrelevant for equality purposes.
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		IDClass other = (IDClass) obj;
		if (extension == null) {
			if (other.extension != null) return false;
		} else if (!extension.trim().equals(other.extension.trim())) return false;
		if (root == null) {
			if (other.root != null) return false;
		} else if (!root.trim().equals(other.root.trim())) return false;
		return true;
	}
}

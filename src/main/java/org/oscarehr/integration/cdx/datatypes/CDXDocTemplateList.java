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

public class CDXDocTemplateList extends CDXIDList {
	public CDXDocTemplateList() {
		Items = new ArrayList<CDXID>();
		Items.add(new CDXID("2.16.840.1.113883.3.51.60.2.7", "Admit Notification", "Facility Admissions"));
		Items.add(new CDXID("2.16.840.1.113883.3.51.60.2.1", "Lab Results Report", "Lab Report"));
		Items.add(new CDXID("2.16.840.1.113883.3.51.60.2.2", "Anatomic Pathology Report", "Lab Report"));
		Items.add(new CDXID("2.16.840.1.113883.3.51.60.2.3", "Procedure Note", "Clinical Report"));
		Items.add(new CDXID("2.16.840.1.113883.3.51.60.2.4", "Discharge Summary", "Clinical Report"));
		Items.add(new CDXID("2.16.840.1.113883.3.51.60.2.5", "Diagnostic Imaging Report", "Medical Imaging"));
		Items.add(new CDXID("2.16.840.1.113883.3.51.60.2.6", "Discharge Notification", "Facility Admissions"));
		Items.add(new CDXID("2.16.840.1.113883.3.1818.10.1.4", "e2e Unstructured Document", "Bidirectional Exchange"));
		Items.add(new CDXID("2.16.840.1.113883.3.1818.10.1.5", "e2e Referral (Unstructured)", "Bidirectional Exchange"));
		Items.add(new CDXID("2.16.840.1.113883.3.1818.10.1.2", "e2e Generic Episodic Document", "Bidirectional Exchange"));
		Items.add(new CDXID("2.16.840.1.113883.10.20.2", "History and Physical Note", "Clinical Report"));
		Items.add(new CDXID("2.16.840.1.113883.10.20.4", "Consultation Note", "Clinical Report"));
		Items.add(new CDXID("2.16.840.1.113883.10.20.7", "Operative Note", "Clinical Report"));
		Items.add(new CDXID("2.16.840.1.113883.10.20.21", "Progress Note", "Clinical Report"));
		Items.add(new CDXID("2.16.840.1.113883.10.20.19", "Unstructured Report", "Clinical Report"));

		// These templates are not currently listed on the CDX template list.
		// Items.add(new CDXID("2.16.840.1.113883.3.1818.10.1.6","e2e Referral (Structured)","Bidirectional Exchange"));
		// Items.add(new CDXID("2.16.840.1.113883.3.1818.10.1.3","e2e Patient Chart Transfer","Bidirectional Exchange"));

	}
}

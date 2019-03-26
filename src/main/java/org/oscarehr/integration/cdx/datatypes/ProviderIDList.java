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

public class ProviderIDList extends CDXIDList {
	public ProviderIDList() {
		Items = new ArrayList<CDXID>();
		Items.add(new CDXID("2.16.840.1.113883.3.40.2.11", "BC Ministry Practitioner ID", "Default"));
		Items.add(new CDXID("2.16.840.1.113883.3.40.2.20", "Registered Nurse Practitioner ID", "Default"));
		Items.add(new CDXID("2.16.840.1.113883.3.277.1.61", "IHA Meditech ID", "IHA"));
		Items.add(new CDXID("2.16.840.1.113883.3.523.1.76", "NHA Cerner ID", "NHA"));
		Items.add(new CDXID("2.16.840.1.113883.3.523.1.77", "NHA Crescendo ID", "NHA"));
		Items.add(new CDXID("2.16.840.1.113883.3.277.100.30.1", "Provider Group ID", "NHA"));
		Items.add(new CDXID("2.16.840.1.113883.3.277.100.61", "CDX Recipient ID", "CDX"));
		Items.add(new CDXID("2.16.840.1.113883.3.851.1.61", "VIHA Cerner Provider ID", "VIHA"));
	}
}

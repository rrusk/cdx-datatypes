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

// An extremely simple IHA/NHA representation of a CDX location.
public class LocationClass {

	public String locationName;

	public String cdxID;
	public List<IDClass> ids;
	public List<ProviderClass> providers;
	public List<IDClass> templates;
	public AddressClass address;
	public String telecom;

	private static final Logger logger = MiscUtils.getLogger();

	public LocationClass() {
		locationName = "";
		cdxID = "";
		telecom = "";
		ids = new ArrayList<IDClass>();
		providers = new ArrayList<ProviderClass>();
		templates = new ArrayList<IDClass>();
		address = new AddressClass("", "", "", "", "", "");
	}

	public boolean IsUnstructuredBDECapable() {
		CDXDocTemplateList BDEList = new CDXDocTemplateList();

		if (templates != null && templates.size() > 0) {
			// Check every BDE template. If the location supports them all, return TRUE.
			for (CDXID tpl : BDEList.Items) {
				if (tpl.Category.equals("Bidirectional Exchange")) {
					boolean foundIt = false;
					for (IDClass locTpl : templates) {
						if (locTpl.extension == tpl.Code) {
							foundIt = true;
							break;
						}
					}
					if (!foundIt) {
						logger.info("Template Not Found: " + tpl.Name + "\n" + locationName);
						return false;
					}

				}
			}
			return true;
		} else {
			logger.info("No Templates in " + locationName);
		}
		return false;
	}
}

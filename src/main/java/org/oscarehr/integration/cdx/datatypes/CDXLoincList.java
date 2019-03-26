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

public class CDXLoincList extends CDXIDList {
	public CDXLoincList()
	{
		Items = new ArrayList<CDXID>();
		Items.add(new CDXID("11488-4","Consult note","Consults"));
		Items.add(new CDXID("11502-2","Laboratory report","Lab Results"));
		Items.add(new CDXID("11504-8","Surgical operation note","Procedures"));
		Items.add(new CDXID("11506-3","Progress note","Clinical Report"));
		Items.add(new CDXID("11510-5","Psychology Progress note","Clinical Report"));
		Items.add(new CDXID("11522-0","Cardiac Echo Study","Cardiology"));
		Items.add(new CDXID("11523-8","EEG study","Cardiology"));
		Items.add(new CDXID("11524-6","EKG study","Cardiology"));
		Items.add(new CDXID("11526-1","Pathology study","Lab Results"));
		Items.add(new CDXID("18717-9","Blood bank studies (set)","Lab Results"));
		Items.add(new CDXID("18725-2","Microbiology studies (set)","Lab Results"));
		Items.add(new CDXID("18736-9","Physician Initial evaluation note","Clinical Report"));
		Items.add(new CDXID("18745-0","Cardiac catheterization study","Cardiology"));
		Items.add(new CDXID("18748-4","Diagnostic imaging study","Medical Imaging"));
		Items.add(new CDXID("18749-2","Electromyogram study","Cardiology"));
		Items.add(new CDXID("18752-6","Exercise stress test study","Cardiology"));
		Items.add(new CDXID("18754-2","Holter monitor study","Cardiology"));
		Items.add(new CDXID("18761-7","Transfer summary note","Facility Admissions"));
		Items.add(new CDXID("18842-5","Discharge summary","Clinical Report"));
		Items.add(new CDXID("19805-1","Endoscopy procedure Study observation Narrative","Procedures"));
		Items.add(new CDXID("24858-1","Pain Management Note","Clinical Report"));
		Items.add(new CDXID("27445-6","Cardiac rehabilitation treatment plan Initial evaluation note (narrative)","Cardiology"));
		Items.add(new CDXID("28569-2","Physician consulting Progress note","Consults"));
		Items.add(new CDXID("28570-0","Procedure note","Procedures"));
		Items.add(new CDXID("28627-8","Psychiatry Progress note","Clinical Report"));
		Items.add(new CDXID("28633-6","Polysomnography (sleep) study","Clinical Report"));
		Items.add(new CDXID("34099-2","Cardiology Consult note","Consults"));
		Items.add(new CDXID("34104-0","Hospital Consult note","Consults"));
		Items.add(new CDXID("34108-1","Outpatient Note","Clinical Report"));
		Items.add(new CDXID("34111-5","Emergency department Note","Clinical Report"));
		Items.add(new CDXID("34117-2","History and physical note","Consults"));
		Items.add(new CDXID("34129-7","Patient's home Progress note","Clinical Report"));
		Items.add(new CDXID("34131-3","Outpatient Progress note","Clinical Report"));
		Items.add(new CDXID("34137-0","Outpatient Surgical operation note","Procedures"));
		Items.add(new CDXID("34748-4","Telephone encounter Note","Clinical Report"));
		Items.add(new CDXID("34760-9","Diabetology Consult note","Consults"));
		Items.add(new CDXID("34785-6","Mental health Consult note","Consults"));
		Items.add(new CDXID("34788-0","Psychiatry Consult note","Consults"));
		Items.add(new CDXID("34791-4","Psychology Consult note","Consults"));
		Items.add(new CDXID("34795-5","Nephrology Consult note","Consults"));
		Items.add(new CDXID("34805-2","Oncology Consult note","Consults"));
		Items.add(new CDXID("34806-0","Oncology Note","Clinical Report"));
		Items.add(new CDXID("34815-1","Orthopaedic surgery Note","Clinical Report"));
		Items.add(new CDXID("34830-0","Pulmonary Note","Clinical Report"));
		Items.add(new CDXID("34838-3","Respiratory therapy Note","Clinical Report"));
		Items.add(new CDXID("34904-3","Mental health Progress note","Clinical Report"));
		Items.add(new CDXID("42148-7","US Heart","Clinical Report"));
		Items.add(new CDXID("51845-6","Outpatient Consult note","Consults"));
		Items.add(new CDXID("51846-4","Emergency department Consult note","Consults"));
		Items.add(new CDXID("51849-8","Admission history and physical note","Consults"));
		Items.add(new CDXID("51852-2","Letter","Clinical Report"));
		Items.add(new CDXID("57057-2","Labor and delivery summary note","Procedures"));
		Items.add(new CDXID("57133-1","Referral note","Orders"));
		Items.add(new CDXID("59259-2","Psychiatry Discharge summary","Clinical Report"));
		Items.add(new CDXID("67862-3","Preoperative evaluation and management note","Clinical Report"));
		Items.add(new CDXID("68556-0","Neurology Diagnostic study note","Clinical Report"));
		Items.add(new CDXID("68608-9","Summary note","Consults"));
		Items.add(new CDXID("68636-0","Audiology Note","Clinical Report"));
		Items.add(new CDXID("74209-8","Injury event summary Document","Consults"));
		Items.add(new CDXID("79429-7","Hospital Admission notification note","Facility Admissions"));
		Items.add(new CDXID("79430-5","Hospital Discharge notification note","Facility Admissions"));
		Items.add(new CDXID("80421-1","Public Health Mandatory Reporting Form","Clinical Report"));
	}
	
	public List<String> GetCategoryList()
	{
		List<String> catList = new ArrayList<String>();
		for (CDXID loinc: Items)
		{
			if(!catList.contains(loinc.Category))
			{
				catList.add(loinc.Category);
			}
		}
		catList.sort(null);
		
		return catList;
		
	}
}

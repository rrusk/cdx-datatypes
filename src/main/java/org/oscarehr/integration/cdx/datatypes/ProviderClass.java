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

import oscar.util.StringUtils;

// An extremely simple IHA/NHA representation of a CDX provider.
// Not very flexible, conveys only the minimum information necessary.
public class ProviderClass {
	NameClass name;

	List<IDClass> ids;

	String mspID;
	String IHAID;
	String NHAID;

	String GUID;

	String providerType;
	// public String participantType;
	ParticipantType participantType;

	LocationClass providerLocation;

	public ProviderClass() {
		participantType = ParticipantType.provider;
		name = new NameClass();
		name.fullName = "";
		name.prefix = "";
		name.givenName = "";
		name.familyName = "";
		name.suffix = "";

		ids = new ArrayList<IDClass>();
		mspID = "";
		IHAID = "";
		NHAID = "";

		GUID = new Guid().toString();

		providerLocation = new LocationClass();
	}

	public String GetProviderKey() {
		if (!StringUtils.isNullOrEmpty(mspID)) {
			return mspID;
		}

		if (!StringUtils.isNullOrEmpty(IHAID)) {
			return IHAID;
		}

		if (!StringUtils.isNullOrEmpty(NHAID)) {
			return NHAID;
		}

		return GUID;
	}

	public void PopulateIds() {
		if (ids != null && ids.size() > 0) {
			PopulateMSP();
			PopulateIHAID();
			PopulateNHAID();
		}
	}

	private void PopulateMSP() {
		ProviderIDList idlist = new ProviderIDList();
		for (IDClass pid : ids) {
			if (idlist.GetCategoryFromCode(pid.root).equals("Default")) {
				mspID = pid.extension;
				return;
			}
		}
	}

	private void PopulateIHAID() {
		ProviderIDList idlist = new ProviderIDList();
		for (IDClass pid : ids) {
			if (idlist.GetCategoryFromCode(pid.root).equals("IHA")) {
				IHAID = pid.extension;
				return;
			}
		}
	}

	private void PopulateNHAID() {
		ProviderIDList idlist = new ProviderIDList();
		for (IDClass pid : ids) {
			if (idlist.GetCategoryFromCode(pid.root).equals("NHA")) {
				NHAID = pid.extension;
				return;
			}
		}
	}

	public String GetParticipantTypeDescription() {

		String description;

		switch (participantType) {
		case admitter:
			description = "Admitter";
			break;

		case attender:
			description = "Attender";
			break;

		case consultant:
			description = "Consultant";
			break;

		case discharger:
			description = "Discharger";
			break;

		case referrer:
			description = "Referrer";
			break;

		case family:
			description = "Primary Care";
			break;

		case performer:
			description = "Performer";
			break;

		case provider:
			description = "Provider";
			break;
		default:
			description = "unknown";
			break;
		}
		return description;
	}

	public void SetParticipantTypeFromDescription(String participantDescription) {

		ParticipantType ptype;

		if ("Admitter".equals(participantDescription)) {
			ptype = ParticipantType.admitter;
		} else if ("Attender".equals(participantDescription)) {
			ptype = ParticipantType.attender;
		} else if ("Consultant".equals(participantDescription)) {
			ptype = ParticipantType.consultant;
		} else if ("Discharger".equals(participantDescription)) {
			ptype = ParticipantType.discharger;
		} else if ("Primary Care".equals(participantDescription)) {
			ptype = ParticipantType.family;
		} else if ("Performer".equals(participantDescription)) {
			ptype = ParticipantType.performer;
		} else if ("Referrer".equals(participantDescription)) {
			ptype = ParticipantType.referrer;
		} else if ("Provider".equals(participantDescription)) {
			ptype = ParticipantType.provider;
		} else {
			ptype = ParticipantType.unknown;
		}
		participantType = ptype;
	}

	public String GetParticipantTypeCode() {

		String code = "UNK";

		switch (participantType) {
		case admitter:
			code = "ADM";
			break;

		case attender:
			code = "ATND";
			break;

		case consultant:
			code = "CON";
			break;

		case discharger:
			code = "DIS";
			break;

		case performer:
			code = "PPRF";
			break;

		case referrer:
			code = "REF";
			break;

		case family:
			code = "PCP";
			break;
		}

		return code;
	}

	public void SetParticipantTypeFromCode(String participantCode) {

		ParticipantType ptype;

		if ("ADM".equals(participantCode)) {
			ptype = ParticipantType.admitter;
		} else if ("ATND".equals(participantCode)) {
			ptype = ParticipantType.attender;
		} else if ("CON".equals(participantCode)) {
			ptype = ParticipantType.consultant;
		} else if ("DIS".equals(participantCode)) {
			ptype = ParticipantType.discharger;
		} else if ("PCP".equals(participantCode)) {
			ptype = ParticipantType.family;
		} else if ("PPRF".equals(participantCode)) {
			ptype = ParticipantType.performer;
		} else if ("REF".equals(participantCode)) {
			ptype = ParticipantType.referrer;
		} else {
			ptype = ParticipantType.unknown;
		}
		participantType = ptype;
	}

	public void SetPrimaryRecipient() {
		providerType = "Primary";
	}

	// TODO: This hashCode isn't consistent with equals.  Fix!!!
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		return result;
	}

	// Equality is entirely based on at least one ID matching.
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ProviderClass other = (ProviderClass) obj;
		if (ids == null && other.ids != null) return false;
		boolean idMatch = false;
		for (IDClass id : this.ids) {
			for (IDClass otherid : other.ids) {
				if (id.root.equals(otherid.root) && id.extension.equals(otherid.extension)) {
					idMatch = true;
				}
			}
		}
		return idMatch;
	}

	public NameClass getName() {
		return name;
	}

	public void setName(NameClass name) {
		this.name = name;
	}

	public List<IDClass> getIds() {
		return ids;
	}

	public void setIds(List<IDClass> ids) {
		this.ids = ids;
	}

	public String getMspID() {
		return mspID;
	}

	public void setMspID(String mspID) {
		this.mspID = mspID;
	}

	public String getIHAID() {
		return IHAID;
	}

	public void setIHAID(String iHAID) {
		IHAID = iHAID;
	}

	public String getNHAID() {
		return NHAID;
	}

	public void setNHAID(String nHAID) {
		NHAID = nHAID;
	}

	public String getGUID() {
		return GUID;
	}

	public void setGUID(String gUID) {
		GUID = gUID;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public ParticipantType getParticipantType() {
		return participantType;
	}

	public void setParticipantType(ParticipantType participantType) {
		this.participantType = participantType;
	}

	public LocationClass getProviderLocation() {
		return providerLocation;
	}

	public void setProviderLocation(LocationClass providerLocation) {
		this.providerLocation = providerLocation;
	}

}

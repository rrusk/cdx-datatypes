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

public class CDAHeaderClass {
	String documentID;
	String originalDocumentId;
	DateTime documentDate;
	IDClass code;
	IDClass templateID;
	String title;
	String status;
	PatientClass patient;
	ProviderClass author;
	IDClass authoringSoftware;
	LocationClass custodian;
	List<ProviderClass> recipients;
	ProviderClass familyProvider;
	ProviderClass orderingProvider;
	IDClass orderId;
	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

	public String getOriginalDocumentId() {
		return originalDocumentId;
	}

	public void setOriginalDocumentId(String originalDocumentId) {
		this.originalDocumentId = originalDocumentId;
	}

	public DateTime getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(DateTime documentDate) {
		this.documentDate = documentDate;
	}

	public IDClass getCode() {
		return code;
	}

	public void setCode(IDClass code) {
		this.code = code;
	}

	public IDClass getTemplateID() {
		return templateID;
	}

	public void setTemplateID(IDClass templateID) {
		this.templateID = templateID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PatientClass getPatient() {
		return patient;
	}

	public void setPatient(PatientClass patient) {
		this.patient = patient;
	}

	public ProviderClass getAuthor() {
		return author;
	}

	public void setAuthor(ProviderClass author) {
		this.author = author;
	}

	public IDClass getAuthoringSoftware() {
		return authoringSoftware;
	}

	public void setAuthoringSoftware(IDClass authoringSoftware) {
		this.authoringSoftware = authoringSoftware;
	}

	public LocationClass getCustodian() {
		return custodian;
	}

	public void setCustodian(LocationClass custodian) {
		this.custodian = custodian;
	}

	public List<ProviderClass> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<ProviderClass> recipients) {
		this.recipients = recipients;
	}

	public ProviderClass getFamilyProvider() {
		return familyProvider;
	}

	public void setFamilyProvider(ProviderClass familyProvider) {
		this.familyProvider = familyProvider;
	}

	public ProviderClass getOrderingProvider() {
		return orderingProvider;
	}

	public void setOrderingProvider(ProviderClass orderingProvider) {
		this.orderingProvider = orderingProvider;
	}

	public IDClass getOrderId() {
		return orderId;
	}

	public void setOrderId(IDClass orderId) {
		this.orderId = orderId;
	}

	public ProviderClass getPerformer() {
		return performer;
	}

	public void setPerformer(ProviderClass performer) {
		this.performer = performer;
	}

	public IDClass getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(IDClass serviceCode) {
		this.serviceCode = serviceCode;
	}

	public DateTime getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(DateTime serviceDate) {
		this.serviceDate = serviceDate;
	}

	public DateTime getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(DateTime encounterDate) {
		this.encounterDate = encounterDate;
	}

	public DateTime getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(DateTime dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public List<RelatedDocumentClass> getRelatedDocuments() {
		return relatedDocuments;
	}

	public void setRelatedDocuments(List<RelatedDocumentClass> relatedDocuments) {
		this.relatedDocuments = relatedDocuments;
	}

	public IDClass getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(IDClass encounterId) {
		this.encounterId = encounterId;
	}

	public List<ProviderClass> getEncounterParticipants() {
		return encounterParticipants;
	}

	public void setEncounterParticipants(List<ProviderClass> encounterParticipants) {
		this.encounterParticipants = encounterParticipants;
	}

	public IDClass getEncounterLocationId() {
		return encounterLocationId;
	}

	public void setEncounterLocationId(IDClass encounterLocationId) {
		this.encounterLocationId = encounterLocationId;
	}

	public IDClass getEncounterLocationCode() {
		return encounterLocationCode;
	}

	public void setEncounterLocationCode(IDClass encounterLocationCode) {
		this.encounterLocationCode = encounterLocationCode;
	}

	public String getEncounterLocationName() {
		return encounterLocationName;
	}

	public void setEncounterLocationName(String encounterLocationName) {
		this.encounterLocationName = encounterLocationName;
	}

	ProviderClass performer;
	IDClass serviceCode;
	DateTime serviceDate;
	DateTime encounterDate;
	DateTime dischargeDate;
	List<RelatedDocumentClass> relatedDocuments;
	IDClass encounterId;
	List<ProviderClass> encounterParticipants;
	IDClass encounterLocationId;
	IDClass encounterLocationCode;
	String encounterLocationName;

	public CDAHeaderClass() {
		// this.code = new IDClass();
	}

	public String GetPrimaryRecipientName() {

		return GetPrimaryRecipient().name.fullName;
	}

	public ProviderClass GetPrimaryRecipient() {
		if (this.recipients != null && this.recipients.size() > 0) {
			for (ProviderClass recip : this.recipients) {
				if (recip.providerType == "Primary") {
					return recip;
				}
			}
		}

		// Return an empty provider if nothing was found.
		ProviderClass rp = new ProviderClass();
		rp.providerType = "Primary";
		rp.name = new NameClass();
		rp.name.fullName = "";
		return rp;

	}

}

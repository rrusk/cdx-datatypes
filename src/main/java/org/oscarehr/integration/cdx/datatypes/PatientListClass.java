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

//A class that holds a patient, an associated clinic provider, and a list ID
public class PatientListClass {
	IDClass patientID;
	PatientClass patient;
	ProviderClass ClinicProvider;
	protected Guid id;

	public PatientListClass() {
		this.patientID = new IDClass("", "", "");
		this.patient = new PatientClass();

		id = new Guid();
	}

	public PatientListClass(XmlDocument cdaXml) {
		this.patient = new PatientClass();
		this.GetPatientFromXmlDocument(cdaXml);

	}

	public void GetPatientFromXmlDocument(XmlDocument cdaXml) {
        {
        	//TODO - implement method
			/*            var nsMgr = new XmlNamespaceManager(xmlCda.NameTable);
			// The basic CDA Namespace: "urn:hl7-org:v3"
			nsMgr.AddNamespace("ns", "urn:hl7-org:v3");
			
			XmlNode xRecordTarget = xmlCda.SelectSingleNode("/ns:ClinicalDocument/ns:recordTarget", nsMgr);
			if (xRecordTarget != null)
			{       
			        this.patient.LoadPatientFromXml(xRecordTarget, ref nsMgr);
			}  */                      
    }

		
	}

	public IDClass getPatientID() {
		return patientID;
	}

	public void setPatientID(IDClass patientID) {
		this.patientID = patientID;
	}

	public PatientClass getPatient() {
		return patient;
	}

	public void setPatient(PatientClass patient) {
		this.patient = patient;
	}

	public ProviderClass getClinicProvider() {
		return ClinicProvider;
	}

	public void setClinicProvider(ProviderClass clinicProvider) {
		ClinicProvider = clinicProvider;
	}

	public Guid getId() {
		return id;
	}

	public void setId(Guid id) {
		this.id = id;
	}

	//	public void AssignClinicProvider(List<ProviderClass> clinicProviders)
	//	{
	//		
	//	}
}

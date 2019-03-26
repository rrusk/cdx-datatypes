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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import oscar.util.StringUtils;
import org.apache.log4j.Logger;
import org.oscarehr.util.MiscUtils;

// Used to store the downloaded CDA documents and associated attachments
public class DocumentListClass {

	IDClass documentID;
	IDClass messageID;
	IDClass code;
	IDClass patientID;
	IDClass authorID;
	DateTime documentDate;
	ProviderClass ClinicProvider;
	CDAClass CDA;
	// XmlDocument xmlCDA;
	String filename;
	// List<String> attachmentFilenames;
	List<AttachmentClass> attachments;

	List<DocumentDeliveryStatusClass> documentStatus;

	// If this is a versioned document, this should be a chain of related documents
	boolean isOldVersion;
	DocumentListClass previousVersion;

	// If this is a referral or consult, this is where the complimentary document goes
	DocumentListClass associatedDocument;

	private static final Logger logger = MiscUtils.getLogger();

	public DocumentListClass(XmlDocument cda) {

		this.CDA = new CDAClass(cda);

		// Is this a valid CDA? Load it up then
		if (this.CDA.CDAHeader.code != null) {
			documentID = new IDClass("2.16.840.1.113883.3.277.100.3", this.CDA.CDAHeader.documentID, "CDX Clinical Document ID");
			messageID = new IDClass("2.16.840.1.113883.3.277.100.1", "", "CDX Message ID"); // We'll only have this at download
			code = new IDClass("2.16.840.1.113883.6.1", this.CDA.CDAHeader.code.extension, "BC CDA Template");
			patientID = new IDClass("", "", "");
			authorID = new IDClass("", "", "");
			// CDAHeader = new CDAHeaderClass();
			filename = "";
			// attachmentFilenames = new List<String>();
			attachments = new ArrayList<AttachmentClass>();

			// When instantiated, this document is set to version 1.
			isOldVersion = false;
			previousVersion = null;
		}

	}

	public void AssignClinicProvider(List<ProviderClass> clinicProviders) {
		if (this.CDA.CDAHeader.recipients.size() > 0) {
			List<ProviderClass> recipients = this.CDA.CDAHeader.recipients;

			for (ProviderClass clinicProvider : clinicProviders) {
				ProviderClass tempProvider = new ProviderClass();

				for (ProviderClass x : recipients) {
					if (x.name.fullName.equals(clinicProvider.name.fullName)) {
						tempProvider = x;
						break;
					}
				}

				if (tempProvider != null && tempProvider.name != null) {
					this.ClinicProvider = tempProvider;
					return;
				} else if (!StringUtils.isNullOrEmpty(clinicProvider.mspID)) {
					// I can't get Find to work here, so this will have to suffice.
					for (ProviderClass recipient : recipients) {
						if (recipient.mspID.equals(clinicProvider.mspID)) {
							this.ClinicProvider = clinicProvider;
							return;
						}
					}
				}
			}
		}
	}

	public void GetAttachments() {
		File f = null;
		if (StringUtils.isNullOrEmpty(filename)) {
			f = new File(filename);
			if (!f.exists() || f.isDirectory()) {
				return;
			}
		}
		String filePath = f.getParent();
		String docFileName = f.getName();
		docFileName = docFileName.replaceFirst("[.][^.]+$", "");

		String attachmentSearch = docFileName + "_att??.*";
		logger.info("Attachment Search: " + attachmentSearch);

		String[] attachmentFilenames = GetFiles(filePath, attachmentSearch);
		attachments = new ArrayList<AttachmentClass>();
		for (String attachmentName : attachmentFilenames) {
			AttachmentClass attachment = new AttachmentClass();
			attachment.filename = attachmentName;
			attachment.SetSHA1Hash();
			attachments.add(attachment);
		}
		SetBodyAttachment();
		return;
	}

	private static String[] GetFiles(final String path, final String searchPattern) {
		final Pattern re = Pattern.compile(searchPattern.replace("*", ".*").replace("?", ".?"));
		return new File(path).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isFile() && re.matcher(name).matches();
			}
		});
	}

	public void SetBodyAttachment() {
		// Look for the attachment referenced in the body, get the SHA-1 hash

		if (attachments == null || attachments.size() < 1) {
			//System.Windows.Forms.MessageBox.Show("No attachments","Body Attachment");
			return;
		}

		AttachmentClass bodyAttachment = null; // new AttachmentClass();
		String bodyFilename = "";
		String bodyHash = "z"; // Will never match a hex String

		boolean attachmentBody = false;

		// System.Windows.Forms.MessageBox.Show(attachments.Count+" attachment(s)","Body Attachment");

		if (this.CDA.CDABody != null && this.CDA.CDABody.bodyType == BodyType.attachment) {
			if (this.CDA.CDABody.body != null) {
				bodyAttachment = (AttachmentClass) this.CDA.CDABody.body;
				bodyFilename = bodyAttachment.filename;
				bodyHash = bodyAttachment.SHA1HashHexString();
				// System.Windows.Forms.MessageBox.Show(bodyHash,"Body Attachment Hash");

			}
			attachmentBody = true;
		}

		if (!attachmentBody) {
			// System.Windows.Forms.MessageBox.Show("Body is not attachment type","Body Attachment");
			return;
		}

		if (StringUtils.isNullOrEmpty(bodyHash)) {
			//System.Windows.Forms.MessageBox.Show("No hash: using the first attachment."
			//                               +"\n"+attachments[0].filename,"Body Attachment");

			attachments.get(0).SHA1HashHexString();
			this.CDA.CDABody.body = attachments.get(0);
			return;
		}

		for (AttachmentClass att : attachments) {
			//            System.Windows.Forms.MessageBox.Show("Hash Comparison: attachment, body\n"
			//			                                +att.SHA1HashHexString()+"\n"
			//			                                +bodyHash,"Body Attachment");
			if (bodyHash.equals(att.SHA1HashHexString())) {
				bodyAttachment = att;
				// System.Windows.Forms.MessageBox.Show("HASH MATCH!"
				//                               +"\n"+bodyAttachment.filename,"Body Attachment");
			}
		}

		if (bodyAttachment == null) {
			// System.Windows.Forms.MessageBox.Show("NO HASH MATCH","Body Attachment");
			return;
		}

		this.CDA.CDABody.body = bodyAttachment;

	}

	public void SetPropertiesFromCDA() {
		this.documentID.extension = this.CDA.CDAHeader.documentID;
		this.code = this.CDA.CDAHeader.code;
		this.documentDate = this.CDA.CDAHeader.documentDate;
		this.CDA.CDAHeader.patient.GetPHNFromIDs();
		this.patientID = new IDClass("2.16.840.1.113883.4.50", this.CDA.CDAHeader.patient.PHN, "BC Patient Health Number");
		if (CDA.CDAHeader.author != null && !StringUtils.isNullOrEmpty(CDA.CDAHeader.author.mspID)) {
			this.authorID = new IDClass("2.16.840.1.113883.3.40.2.11", this.CDA.CDAHeader.author.mspID, "Provider MSP");
		}
	}

	// Determine if this document and the given document are versions of the same document.

	// <param name="otherDocument">A document to compare to this document</param>
	// <returns>-1 if this is a previous version of otherDocument, 1 if this is the same version, 2 if a later version, 0 if unrelated.</returns>
	public int IsVersionOf(DocumentListClass otherDocument) {
		// If either of these documents are nullish, don't bother
		if (this.CDA == null || otherDocument.CDA == null || StringUtils.isNullOrEmpty(this.CDA.CDAHeader.documentID) || StringUtils.isNullOrEmpty(otherDocument.CDA.CDAHeader.documentID)) {
			return 0;
		}

		// Patients must match
		if (!this.CDA.CDAHeader.patient.equals(otherDocument.CDA.CDAHeader.patient)) {
			return 0;
		}

		// If ID and Date are the same, this is the same document!
		if (this.CDA.CDAHeader.documentID.equals(otherDocument.CDA.CDAHeader.documentID) && this.CDA.CDAHeader.documentDate.equals(otherDocument.CDA.CDAHeader.documentDate)) {
			return 1;
		}

		// This document's replacement doc ID matches other's doc ID: This is a later version of the other document.
		if (this.CDA.CDAHeader.relatedDocuments != null && this.CDA.CDAHeader.relatedDocuments.size() > 0) {
			for (RelatedDocumentClass relDoc : this.CDA.CDAHeader.relatedDocuments) {
				// If this is a replacement AND the document ID is a CDX document ID AND the CDX document ID matches the other doc
				if (relDoc.type == DocumentRelation.replace && relDoc.id.root.equals("2.16.840.1.113883.3.277.100.3") && relDoc.id.extension.equals(otherDocument.CDA.CDAHeader.documentID)) {
					return 2;
				}
			}
		}

		// Other document's replacement doc ID matches this doc ID: This is a previous version of the other document.
		if (otherDocument.CDA.CDAHeader.relatedDocuments != null && otherDocument.CDA.CDAHeader.relatedDocuments.size() > 0) {
			for (RelatedDocumentClass relDoc : otherDocument.CDA.CDAHeader.relatedDocuments) {
				// If the other doc is a replacement AND the document ID is a CDX document ID AND the CDX document ID matches this doc
				if (relDoc.type == DocumentRelation.replace && relDoc.id.root.equals("2.16.840.1.113883.3.277.100.3") && relDoc.id.extension.equals(this.CDA.CDAHeader.documentID)) {
					return -1;
				}
			}
		}

		// ORDER ID IS THE SAME: Same source document, different versions
		if ((this.CDA.CDAHeader.orderId != null) && (this.CDA.CDAHeader.orderId.equals(otherDocument.CDA.CDAHeader.orderId))) {

			// Discrete Lab Results: Order ID is the same, but Specimen ID is different
			if (CDA.CDABody.bodyType == BodyType.discreteLabReport && otherDocument.CDA.CDABody.bodyType == BodyType.discreteLabReport) {
				discreteLabReportBody thisBody = (discreteLabReportBody) CDA.CDABody.body;
				discreteLabReportBody otherBody = (discreteLabReportBody) otherDocument.CDA.CDABody.body;
				if (!thisBody.SpecimenID.equals(otherBody.SpecimenID)) {
					return 0;
				}
			}

			// Date is later, so this is a later version
			if (this.CDA.CDAHeader.documentDate.getDate().after(otherDocument.CDA.CDAHeader.documentDate.getDate())) {
				return 2;
			}
			// Date is earlier, so this is an earlier version
			else if (this.CDA.CDAHeader.documentDate.getDate().before(otherDocument.CDA.CDAHeader.documentDate.getDate())) {
				return -1;
			} else {
				return 1;
			}

		}

		return 0; // Default: unrelated documents
	}

	@Override
	public boolean equals(Object obj) {
		DocumentListClass other = (DocumentListClass) obj;
		if (other == null) {
			return false;
		}

		// If the CDA Document ID and patient PHN match, this is the same document.
		// Maybe in the future the attachments could be compared.
		boolean isEqual = false;
		if (this.CDA.CDAHeader.documentID.equals(other.CDA.CDAHeader.documentID) &&
				this.CDA.CDAHeader.patient.PHN.equals(other.CDA.CDAHeader.patient.PHN)) {
			isEqual = true;

		}
		return isEqual;
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		{
			if (documentID != null) hashCode += 1000000007 * documentID.hashCode();
			if (messageID != null) hashCode += 1000000009 * messageID.hashCode();
			if (code != null) hashCode += 1000000021 * code.hashCode();
			if (patientID != null) hashCode += 1000000033 * patientID.hashCode();
			if (authorID != null) hashCode += 1000000087 * authorID.hashCode();
			hashCode += 1000000093 * documentDate.hashCode();
			if (CDA != null) hashCode += 1000000097 * CDA.hashCode();
			if (filename != null) hashCode += 1000000103 * filename.hashCode();
		}
		return hashCode;
	}
}

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.oscarehr.util.MiscUtils;

import oscar.util.StringUtils;

public class AttachmentClass {
	String filename;
	String encodedText;
	String encodingType;
	String mimetype;
	byte[] hash;
	byte[] oldHash;
	String hashAlgorithm;

	private static final Logger logger = MiscUtils.getLogger();

	public AttachmentClass() {
		filename = "";
		encodedText = "";
		encodingType = "base64";
		hashAlgorithm = "SHA1";
		mimetype = "application/pdf";
		hash = null;
	}

	public boolean SetSHA1Hash() {
		boolean itWorked = false;

		if (!StringUtils.isNullOrEmpty(filename) && hashAlgorithm.equals("SHA1")) {
			FileInputStream fis = null;
			try {
				MessageDigest sha1 = MessageDigest.getInstance("SHA1");
				fis = new FileInputStream(filename);

				byte[] data = new byte[1024];
				int read = 0;
				while ((read = fis.read(data)) != -1) {
					sha1.update(data, 0, read);
				}
				hash = sha1.digest();
				itWorked = true;
			} catch (NoSuchAlgorithmException e) {
				logger.info(e.getMessage());
			} catch (IOException e) {
				logger.info(e.getMessage());
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						logger.info(e.getMessage());
					}
				}
			}
		}
		return itWorked;
	}

	public boolean SetMimeType() {
		boolean itWorked = false;
		String mime = "";
		if (!StringUtils.isNullOrEmpty(filename)) {
			mime = URLConnection.guessContentTypeFromName(filename);
			if (!StringUtils.isNullOrEmpty(mime)) {
				mimetype = mime;
				itWorked = true;
			}
		}
		return itWorked;
	}

	public String SHA1HashHexString() {
		if (!StringUtils.isNullOrEmpty(filename)) {
			SetSHA1Hash();
		}

		return binary2hexString(hash);
	}

	public String SHA1HashBase64String() {
		if (!StringUtils.isNullOrEmpty(filename)) {
			SetSHA1Hash();
		}
		return Base64.encodeBase64String(hash);
	}

	private String binary2hexString(byte[] binhash) {
		String hexHash = "";

		if (binhash != null && binhash.length > 0) {
			Formatter formatter = new Formatter();
			for (byte b : binhash) {
				formatter.format("%02x", b);
			}
			hexHash = formatter.toString();
			formatter.close();
		}

		return hexHash;
	}

	public boolean SetSHA1HashFromHex(String hexHash) {
		boolean itWorked = false;
		byte[] bytes = hexStringToByteArray(hexHash);

		if (bytes.length > 0) {
			hash = bytes;
			itWorked = true;
		}
		return itWorked;
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public void SetSHA1HashFromBase64(String b64hash) {
		hash = Base64.decodeBase64(b64hash);
	}

	public boolean Base64Encode() {
		boolean itWorked = false;
		if (!StringUtils.isNullOrEmpty(filename) && encodingType.equals("base64")) {
			File f = new File(filename);
			if (f.exists() && !f.isDirectory()) {
				try {
					InputStream is = new FileInputStream(f);
					if (f.length() > Integer.MAX_VALUE) {
						logger.error("File " + filename + " is too large!");
						return false;
					}
					byte[] fileBytes = new byte[(int) f.length()];

					int offset = 0;
					int numRead = 0;
					while (offset < fileBytes.length && (numRead = is.read(fileBytes, offset, fileBytes.length - offset)) >= 0) {
						offset += numRead;
					}

					if (offset < fileBytes.length) {
						logger.error("Could not completely read file " + filename);
						return false;
					}

					is.close();
					encodedText = Base64.encodeBase64String(fileBytes);
					itWorked = true;
				} catch (FileNotFoundException e) {
					logger.info(e.getMessage());
					return false;
				} catch (IOException e) {
					logger.info(e.getMessage());
				}
			}
		}

		return itWorked;
	}

	public boolean DecodeToFile() {
		boolean itWorked = false;
		String ErrorMessage = "";

		if (encodingType.equals("B64")) {
			encodingType = "base64";
		}

		if (!StringUtils.isNullOrEmpty(filename) && encodingType.equals("base64") && !StringUtils.isNullOrEmpty(encodedText)) {
			FileOutputStream output;
			try {
				output = new FileOutputStream(new File(filename));
			} catch (FileNotFoundException e) {
				logger.info(e.getMessage());
				return false;
			}
			byte[] data = Base64.decodeBase64(encodedText);
			try {
				IOUtils.write(data, output);
				output.close();
			} catch (IOException e) {
				logger.info(e.getMessage());
				return false;
			}
			oldHash = hash;
			SetSHA1Hash();
			if (oldHash != null) {
				String hexOldHash = binary2hexString(oldHash);
				String hexNewHash = binary2hexString(hash);
				if (hexOldHash.equals(hexNewHash)) {
					itWorked = true;
				} else {
					ErrorMessage = "Hash problem! Old and new hashes don't line up!\n" + "Old Hash: " + hexOldHash + "\n" + "New Hash: " + hexNewHash;
				}
			} else {
				itWorked = true;
			}
		} else {
			if (StringUtils.isNullOrEmpty(filename)) {
				ErrorMessage = "Filename is EMPTY! ";
			}
			if (!encodingType.equals("base64")) {
				ErrorMessage += "Encoding is NOT base64! (" + encodingType + ") ";
			}
			if (StringUtils.isNullOrEmpty(encodedText)) {
				ErrorMessage += "No encoded attachment found!";
			}
		}

		if (!itWorked) {
			logger.error("Attachment Saving Error");
		}

		return itWorked;
	}

}

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

import oscar.util.StringUtils;

// A simple IHA/NHA representation of a BC CDA.
// Conveys the minimum information necessary.
// XML extraction methods are in a separate CDAXML file.
public class CDAClass {
	CDAHeaderClass CDAHeader;
	CDABodyClass CDABody;
	XmlDocument xml;

	public CDAClass()
	{
		this.xml = new XmlDocument();
		this.CDAHeader = new CDAHeaderClass();
		this.CDABody = new CDABodyClass(BodyType.unstructured);
	}
	
	public CDAClass(String cda)
	{
		this.xml = new XmlDocument();
		this.xml.LoadXml(cda);
        LoadFromXml();
	}

    public CDAClass(XmlDocument cda)
    {
       this.xml = cda;
        LoadFromXml();
    }

    private void LoadFromXml()
    {
        this.CDAHeader = GetHeaderFromXml(xml); // new CDAHeaderClass();
        if (this.CDAHeader.code != null && !StringUtils.isNullOrEmpty(this.CDAHeader.code.extension))
        {
            this.CDABody = GetBodyFromXml(xml); // new CDABodyClass("");			
        }
    }
    
    private CDABodyClass GetBodyFromXml(XmlDocument xml2) {
		// TODO Implement method contained in CDAXML.cs
		return null;
	}

	private CDAHeaderClass GetHeaderFromXml(XmlDocument xml2) {
    	//TODO Implement method contained in CDAXML.cs
		return null;
	}

	public boolean SaveToXml()
    {
    	boolean success = false;
    	
    	success = this.ReplaceHeaderXml();
    	if(success && (this.CDABody.bodyType == BodyType.unstructured || this.CDABody.bodyType == BodyType.attachment))
    	{
    		success = this.CreateUnstructuredBodyXml();
    	}
    	
    	return success;
    }

	private boolean CreateUnstructuredBodyXml() {
		// TODO Implement method contained in CDAXML.cs
		return false;
	}

	private boolean ReplaceHeaderXml() {
		// TODO Implement method contained in CDAXML.cs
		return false;
	}
}

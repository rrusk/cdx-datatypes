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

public class discreteLabReportBody {
    IDClass SpecimenID;
    DateTime SpecimenCollectionTime;
    DateTime SpecimenReceivedTime;
    String resultStatus;
    List<BatteryClass> batteries;

    // Formatted text representation
    public String title;
    public String text;
    public String instructionalText;

    public discreteLabReportBody()
    {
        SpecimenID = new IDClass("", "", "");
        SpecimenCollectionTime = new DateTime("1900-01-01");
        SpecimenReceivedTime = new DateTime("1900-01-01");
        resultStatus = "";
        title = "";
        text = "";
        instructionalText = "";
        batteries = new ArrayList<BatteryClass>();
    }

	public IDClass getSpecimenID() {
		return SpecimenID;
	}

	public void setSpecimenID(IDClass specimenID) {
		SpecimenID = specimenID;
	}

	public DateTime getSpecimenCollectionTime() {
		return SpecimenCollectionTime;
	}

	public void setSpecimenCollectionTime(DateTime specimenCollectionTime) {
		SpecimenCollectionTime = specimenCollectionTime;
	}

	public DateTime getSpecimenReceivedTime() {
		return SpecimenReceivedTime;
	}

	public void setSpecimenReceivedTime(DateTime specimenReceivedTime) {
		SpecimenReceivedTime = specimenReceivedTime;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<BatteryClass> getBatteries() {
		return batteries;
	}

	public void setBatteries(List<BatteryClass> batteries) {
		this.batteries = batteries;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getInstructionalText() {
		return instructionalText;
	}

	public void setInstructionalText(String instructionalText) {
		this.instructionalText = instructionalText;
	}
}

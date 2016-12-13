package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class AcquisitionSummary extends Section {
	
	@FindBy(css=".mld-experiment-validation>.row")
	private Elements<AcquisitionSummaryLine> rows;
	
	public boolean hasPassed(String paramName) {
		return getRowByParam(paramName).hasPassed();			
	}

	public boolean hasNotPassed(String paramName) {
		return getRowByParam(paramName).hasNotPassed();			
	}

	public AcquisitionSummaryLine getRowByParam(String paramName) {
		for (AcquisitionSummaryLine row : rows) {
			if (row.getParamName().equals(paramName)) {return row;}
		}
		return null;
	}

	public void openMessage(String paramName) {
		getRowByParam(paramName).clickOnIcon();
	}

	public void openParameter(String paramName) {
		getRowByParam(paramName).clickOnParameter();
	}

}

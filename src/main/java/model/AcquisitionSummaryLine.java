package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class AcquisitionSummaryLine extends Section {

	@FindBy(xpath="./*[@class='row-inner']/div[1]/label")
	private Clickable paramName;
	
	@FindBy(xpath="./*[@class='row-inner']/div[2]")
	private Text paramValues;
	
	@FindBy(xpath="./div[@class='sign-container']/i[1]")
	private Clickable icon;
	
	public String getParamName() {
		return paramName.getWebElement().getText();
	}
	
	public String getParamValues() {
		return paramValues.getText();		
	}

	public boolean hasPassed() {
		return icon.getAttribute("class").contains(" valid");
	}

	public boolean hasNotPassed() {
		return icon.getAttribute("class").contains(" invalid");
	}

	public void clickOnIcon() {
		icon.click();
	}

	public void clickOnParameter() {
		paramName.click();
	}
	
}

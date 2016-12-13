package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import enums.PlateAnalysisViews;

public class BreadcrumbDropdown extends Section {

	@FindBy(css="button")
	private Clickable expander;
	
	@FindBy(css="span")
	private Text chosenOption;

	@FindBy(xpath="./div/div/a")
	private Elements<Clickable> options;

	
	public void expand() {
		expander.click();		
	}

	public void select(PlateAnalysisViews view) {
		if (chosenOption.getText().equals(view.toString())) {
			chosenOption.clickCenter();
		} else {
			expand();
			options.get(view.toString()).click();
		}
	}

	public void select(String analysis) {
		if (!chosenOption.getText().equals(analysis)) {
			expand();
			options.get(analysis).click();
		}		
	}
	
	public String getChosenOption() {
		return chosenOption.getText();
	}

	
}

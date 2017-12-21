package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class AnalysisTile extends Section {

	@FindBy(css=".v2_list-item__header")
	public Clickable header;

	@FindBy(css=".v2_list-item__title")
	public Text name;

	@FindBy(css=".v2_list-item__btn")
	private Button launchButton;

	@FindBy(css=".btn-group button")
	private Clickable menu;

	
	public AnalysisTile expand() {
		header.click();
		return this;
	}

	public void launch() {
		launchButton.click();
	}

	public AnalysisTile openMenu() {
		menu.click();
		return this;
	}

	
	public String getAnalysisName() {
		return name.getText();
	}
	
}

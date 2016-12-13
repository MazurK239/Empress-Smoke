package model;


import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import pages.ExperimentTemplatesPage;
import sites.EmpressSite;

public class ProtocolTile extends Section {
	
	@FindBy(css=".dashboard-item__button_run")
	private Button runButton;

	@FindBy(css=".dashboard-item__button_delete")
	private Button deleteButton;
	
	@FindBy(css=".dashboard-item-vis__link-part")
	private Clickable clickablePart;
	
	@FindBy(css=".dashboard-item__heading")
	private Text header;
	
	public String getTileName() {
		return header.getText();
	}
	
	public void open() {
		clickablePart.click();
	}

	public void run() {
		runButton.click();
	}
	
	public ExperimentTemplatesPage delete() {
		deleteButton.click();
		return EmpressSite.experimentTemplatesPage;
	}
	
}

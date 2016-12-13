package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class MonitoringExperimentItem extends Section {
	
	@FindBy(xpath=".")
	private Table experiment = new Table();
	
	public String getExperimentName() {
		return experiment.cell(1, 1).getText();
	}

}

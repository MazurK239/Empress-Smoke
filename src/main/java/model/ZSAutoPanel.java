package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class ZSAutoPanel extends Section {
	
	@FindBy(css=".container-fluid:nth-of-type(1)")
	private Clickable percentSection;
	
	@FindBy(css=".container-fluid:nth-of-type(1) > div:first-child input:nth-of-type(1)")
	private TextField percentage;

	@FindBy(css=".container-fluid:nth-of-type(2)")
	private Clickable sitesSection;

	@FindBy(css=".container-fluid:nth-of-type(2) > div:first-child input:nth-of-type(1)")
	private TextField xSites;
	
	@FindBy(css=".container-fluid:nth-of-type(2) > div:nth-child(2) input:nth-of-type(1)")
	private TextField ySites;


	public void setSites(int horSites, int vertSites) {
		sitesSection.click();
		xSites.newInput(String.valueOf(horSites));
		ySites.newInput(String.valueOf(vertSites));
	}

}

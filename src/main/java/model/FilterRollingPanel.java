package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class FilterRollingPanel extends Section {

	@FindBy(css=".mld-dashboard-filters_reset-button")
	public Button resetButton;
	
	@FindBy(css=".mld-dashboard-filters_inner-block:nth-last-of-type(2) .multi-trigger")
	private FilterSwitcher imagesFilter;

	@FindBy(css=".mld-dashboard-filters_inner-block:last-of-type .multi-trigger")
	private FilterSwitcher analysisFilter;

	public void hasImages() {
		imagesFilter.choose(1);		
	}

	public void hasAnalysis() {
		analysisFilter.choose(1);
	}
	
}

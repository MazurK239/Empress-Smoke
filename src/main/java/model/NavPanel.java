package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class NavPanel extends Section {

	@FindBy(css="mld-accordion-panel:nth-of-type(3) div button:nth-of-type(1)")	
	protected Button cellHeatmapButton;
	
	public void goToCellHeatmap() {
		cellHeatmapButton.click();
	}

}

package sections.visualization;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

import model.MldToggler;

public class WellLevelSection extends AnalysisSection {

	@FindBy(css=".rolling-panel button:nth-child(2)")	
	protected Button cellHeatmapButton;

	@FindBy(xpath="//button[@title='Show Selected Only']")
	protected MldToggler selectedOnlyToggler;
	
	public void goToCellHeatmap() {
		goToButton.click();
		cellHeatmapButton.click();
	}
	
}

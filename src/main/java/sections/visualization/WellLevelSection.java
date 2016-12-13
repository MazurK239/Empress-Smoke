package sections.visualization;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

import model.MldToggler;

public class WellLevelSection extends AnalysisSection {

	@FindBy(xpath="//mld-rolling-panel//button[@title='Cell Level Heatmap']")	
	protected Button cellHeatmapButton;

	@FindBy(xpath="//button[@title='Selected Only']")
	protected MldToggler selectedOnlyToggler;
	
	public void goToCellHeatmap() {
		goToButton.click();
		cellHeatmapButton.click();
	}
	
}

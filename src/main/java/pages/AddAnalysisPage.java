package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.web.matcher.junit.Assert;

import sections.acquisition.AnalysisSettingsTab;
import sections.acquisition.RunTab;
import sections.acquisition.WellSelectionTab;
import sections.acquisition.AnZoneSelectionTab;
import sections.experiment.TimePointsTab;

public class AddAnalysisPage extends InternalPage {

	@FindBy(css="mld-analysis-button-panel > div:nth-child(1) > button")
	private Clickable timePointsIcon;
	
	@FindBy(css="mld-analysis-t-grid-panel")
	public TimePointsTab timePointsTab;

	@FindBy(css="mld-analysis-button-panel > div:nth-child(2) > button")
	private Clickable analysisSettingsIcon;

	@FindBy(css="mld-experiment-analysis-panel")
	public AnalysisSettingsTab analysisSettingsTab;
	
	@FindBy(css="mld-analysis-button-panel > div:nth-child(3) > button")
	private Clickable zoneSelectionIcon;
	
	@FindBy(css="mld-experiment-wellarea-analysis-panel")
	public AnZoneSelectionTab zoneSelectionTab;

	@FindBy(css="mld-analysis-button-panel > div:nth-child(4) > button")
	private Clickable wellSelectionIcon;

	@FindBy(css="mld-experiment-analysis-well-selection-panel")
	private WellSelectionTab wellSelectionTab;

	@FindBy(css="mld-analysis-button-panel > div:nth-child(5) > button")
	private Clickable runIcon;
	
	@FindBy(css="mld-experiment-run-panel")
	public RunTab runTab;
	
	
	@Override
	public void checkOpened() {
		super.checkOpened();
		Assert.isTrue(() -> timePointsTab.tGrid.isDisplayed());
	}

	public TimePointsTab openTimePointsTab() {
		timePointsIcon.click();
		return timePointsTab;
	}

	public AnalysisSettingsTab openAnalysisSettingsTab() {
		analysisSettingsIcon.click();
		return analysisSettingsTab;
	}


	public AnZoneSelectionTab openZoneSelectionTab() {
		zoneSelectionIcon.click();
		return zoneSelectionTab;
	}


	public WellSelectionTab openWellSelectionTab() {
		wellSelectionIcon.click();
		return wellSelectionTab;
	}


	public RunTab openRunTab() {
		runIcon.click();
		return runTab;
	}

	public void openExpEditPage() {
		breadcrumbsMenu.get(breadcrumbsMenu.size() - 3).click();
		confirmation.ok();
	}

}

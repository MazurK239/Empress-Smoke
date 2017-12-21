package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.Tabs;
import com.epam.web.matcher.testng.Assert;

import enums.MonitoringTabs;
import model.MldCheckbox;
import model.MonitoringExperimentItem;

public class MonitoringPage extends InternalPage {
	
	Timer timer = new Timer();

	@FindBy(xpath="//ul/li[contains(@class, 'general-controls-item')]")
	public Tabs<MonitoringTabs> tabs;
	
	@FindBy(xpath="//div[@class='tab-content']/div[3]//mld-monitoring-table//tbody[@class='ng-scope']")
	private Elements<MonitoringExperimentItem> succeededExperimentsList;

	@FindBy(xpath="//div[@class='tab-content']/div[2]//mld-monitoring-table//tbody[@class='ng-scope']")
	private Elements<MonitoringExperimentItem> failedExperimentsList;

	@FindBy(xpath="//div[@class='tab-content']/div[1]//mld-monitoring-table//tbody[@class='ng-scope']")
	private Elements<MonitoringExperimentItem> inProgressExperimentsList;
	
	@FindBy(css=".tab-pane.active .messages")
	private MonitoringExperimentItem nothingToShowText;

	@FindBy(css="button.monitoring-delete")
	private Button deleteButton;

	@FindBy(css=".tab-pane.active th input")
	private MldCheckbox deleteAllCheckbox;

	public void waitForExperiment(String experimentName) {
		Assert.assertTrue(() -> inProgressExperimentsList.get(0).getExperimentName().equals(experimentName));
		timer.setTimeout(300 * 1000);
		timer.wait(() -> this.noDataAvailable());
	}

	public boolean hasSucceeded(String experimentName) {
		return succeededExperimentsList.get(0).getExperimentName().equals(experimentName);
	}
	
	public boolean noDataAvailable() {
		return nothingToShowText.isDisplayed() && nothingToShowText.getExperimentName().equals("No data available");
	}

	public void deleteAllJobs() {
		deleteAllCheckbox.checkIt();
		deleteButton.click();
		confirmation.ok();
	}

}

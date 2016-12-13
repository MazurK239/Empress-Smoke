package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.Tabs;
import com.epam.web.matcher.base.BaseMatcher;
import com.epam.web.matcher.testng.Assert;

import enums.MonitoringTabs;
import model.MonitoringExperimentItem;

public class MonitoringPage extends InternalPage {

	@FindBy(xpath="//ul/li[contains(@class, 'general-controls-item')]")
	public Tabs<MonitoringTabs> tabs;
	
	@FindBy(xpath="//div[@class='tab-content']/div[3]//mld-monitoring-table//tbody[@class='ng-scope']")
	private Elements<MonitoringExperimentItem> succeededExperimentsList;

	@FindBy(xpath="//div[@class='tab-content']/div[2]//mld-monitoring-table//tbody[@class='ng-scope']")
	private Elements<MonitoringExperimentItem> failedExperimentsList;

	@FindBy(xpath="//div[@class='tab-content']/div[1]//mld-monitoring-table//tbody[@class='ng-scope']")
	private Elements<MonitoringExperimentItem> inProgressExperimentsList;
	
	@FindBy(xpath="//div[@class='tab-content']/div[1]//mld-monitoring-table//tbody[@class='messages']")
	private MonitoringExperimentItem nothingToShowText;

	public void waitForExperiment(String experimentName) {
		Assert.assertTrue(() -> inProgressExperimentsList.get(0).getExperimentName().equals(experimentName));
		BaseMatcher.setDefaultTimeout(300 * 1000); 
		Assert.assertTrue(() -> this.nothingToShow());
		BaseMatcher.setDefaultTimeout(10 * 1000);
		tabs.select(MonitoringTabs.SUCCEEDED);
		Assert.assertTrue(() -> succeededExperimentsList.get(0).getExperimentName().equals(experimentName));
	}
	
	public boolean nothingToShow() {
		return nothingToShowText.isDisplayed() && nothingToShowText.getExperimentName().equals("No data available");
	}

}

package pages;

import static com.epam.commons.LinqUtils.first;

import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;

import model.ExperimentTile;
import static sites.EmpressSite.editEntityPage;

public class DashboardPage extends InternalPage {

//	@FindBy(xpath="//ul/li[contains(@class, 'general-controls-item')]")
//	private Elements<Button> tabs;
	
	@FindBy(xpath="//*[@class='tab-content']/div//mld-plate-item")
	private Elements<ExperimentTile> experiments;

	@FindBy(xpath="//*[@class='tab-content']//mld-plate-item[1]")
	private ExperimentTile simpleExperimentTile;

	public ExperimentTile findExperiment(String experimentName) {
		return first(experiments, el -> el.getTileName().equals(experimentName));
	}

//	public DashboardPage selectTab(VisDashboardTabs tab) {
//		tabs.get(tab).click();
//		return this;
//	}

	@Override
	public void checkOpened() {
		super.checkOpened();
		Timer.waitCondition(() -> simpleExperimentTile.isDisplayed());
	}

	public void deleteExperiment(String experimentName) {
		try{
			this.findExperiment(experimentName).open();
			editEntityPage.deleteExperiment().confirm();
		} catch (NullPointerException e) {
			System.out.println("The experiment " + experimentName + " culdn't be deleted, because it doesn't exist");
		}
		
	}
}

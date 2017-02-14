package uitestsPO;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import enums.ExpDashboardTabs;

import static sites.EmpressSite.*;

public class MiscAcquisitionTests extends InitTest {

	private static final String TEMPLATE_NAME = "New Plate Acquisition";

	@BeforeSuite
	public void preconditions() {
		loginPage.isOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
	}

	@Test(groups={"general"})
	public void redirectBack() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL).findTemplate(TEMPLATE_NAME).open();
		experimentPage.goToDashboard();
		experimentTemplatesPage.back();
		landingPage.checkOpened();
	}

	@Test(groups={"general"})
	public void redirectInvalidURL() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL).findTemplate(TEMPLATE_NAME).open();
		String url = experimentPage.url;
		url += "239";
		experimentPage.getDriver().get(url);
		landingPage.checkOpened();
	}

	
	
}

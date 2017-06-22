package uitestsPO;

import static sites.EmpressSite.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import enums.ExpDashboardTabs;

public class LoginTestsLogout extends InitTest {

	@BeforeMethod
	public void initTest() {		
		loginPage.isOpened();
	}
	
	@Test
	public void manualLogoutAcq() {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate(TEMPLATE_NAME).open();
		experimentPage.logout();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
	}
	
	@Test
	public void manualLogoutVis() {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataVisualizationPage();
		dashboardPage.findFirstGoodExperiment().view();
		viewAnalysisPage.openEditEntity();
		experimentPage.logout();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
	}
	
	@Test
	public void timeoutLogoutSameUser() throws Exception {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openUserPrefs();
		userSettingsPage.openMiscTab().setSessionTimeout(5);
		userSettingsPage.goBack();
		landingPage.openDataVisualizationPage();
		dashboardPage.findFirstGoodExperiment().open();
		Thread.sleep(360*1000);
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		editEntityPage.checkOpened();
	}

	@Test
	public void timeoutLogoutDifferentUser() throws Exception {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openUserPrefs();
		userSettingsPage.openMiscTab().setSessionTimeout(5);
		userSettingsPage.goBack();
		landingPage.openDataVisualizationPage();
		dashboardPage.findFirstGoodExperiment().view();
		Thread.sleep(360*1000);
		loginPage.checkOpened();
		loginPage.loginAs("mld", MLDPASSWORD);
		landingPage.checkOpened();
	}
	
	@Test
	public void browserNavigation() {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.logout();
		loginPage.back();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		landingPage.back();
		loginPage.checkOpened();
		loginPage.forward();
		landingPage.checkOpened();
	}
	
	
	
}

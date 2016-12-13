package uitestsPO;

import static sites.EmpressSite.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import enums.ExpDashboardTabs;
import enums.VisDashboardTabs;

public class LoginTestsLogout extends InitTest {

	@BeforeMethod
	public void initTest() {		
		loginPage.isOpened();
	}
	
	@Test
	public void manualLogoutAcq() {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.TEMPLATES);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		experimentPage.logout();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
	}
	
	@Test
	public void manualLogoutVis() {
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment("Real Analysis").view();
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
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment("Real Analysis").view();
		Thread.sleep(360*1000);
		viewAnalysisPage.openEditEntity();
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
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment("Real Analysis").view();
		Thread.sleep(360*1000);
		viewAnalysisPage.openEditEntity();
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

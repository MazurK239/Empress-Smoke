package uitestsPO;

import static sites.EmpressSite.*;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.web.matcher.testng.Assert;
import com.epam.web.matcher.verify.Verify;

import static com.epam.web.matcher.testng.Assert.assertTrue;

import enums.ExpDashboardTabs;

public class PermissionsTests extends InitTest {
	
	private static final String ANALYSIS_NAME = "Cell Count";
	private static final String EXPERIMENT_NAME = "Test Permissions Experiment";
	private static final String PROTOCOL_NAME = "Protocol For Testing Lock";
	private static List<String> AVAILABLE_STAINS;

	@BeforeClass
	public void initTest() {
		loginPage.open();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		if (landingPage.openDataVisualizationPage().findExperiment(EXPERIMENT_NAME) == null) {
			dashboardPage.goHome().openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL).findTemplate("New Plate Acquisition").open();
			AVAILABLE_STAINS = experimentPage.openAcquisitionTab().getAvailableStains();
			experimentPage.runFromScratch(DEVICE_NAME, "96 Well Plate", Arrays.asList("Off", "Off", AVAILABLE_STAINS.get(0), AVAILABLE_STAINS.get(1), "Off"),
					                      "4x", 0.4f, 0.4f, false, ANALYSIS_NAME, 0.5f, 0.5f, Arrays.asList("E7", "E8"), EXPERIMENT_NAME, "");
			monitoringPage.checkOpened();
			monitoringPage.waitForExperiment(EXPERIMENT_NAME);
		}
	}

	@BeforeMethod
	public void startTest() {
		landingPage.open();
		landingPage.checkOpened();
	}
	
	@Test(groups={"userPrefs"})
	public void permissionsTest() {
		landingPage.openUserPrefs();
		userSettingsPage.openPermissionsTab().protocolSharingPanel.lockLock().addUsers(Arrays.asList("aaa"));
		userSettingsPage.openPermissionsTab().experimentSharingPanel.lockLock().addUsers(Arrays.asList("bbb"));
		userSettingsPage.goHome().openDataAcquisitionPage();
		experimentTemplatesPage.selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		verify.isTrue(() -> experimentPage.openSaveTab().expandSharingPanel().mainCheckbox.wetherChecked());
		verify.isTrue(() -> experimentPage.saveTab.expandSharingPanel().isUserInList("aaa"));
		verify.isTrue(() -> experimentPage.openRunTab().expandSharingPanel().mainCheckbox.wetherChecked());
		verify.isTrue(() -> experimentPage.runTab.expandSharingPanel().isUserInList("bbb"));
		experimentPage.openUserPrefs();
		userSettingsPage.openPermissionsTab().protocolSharingPanel.removeAllUsers().unlockLock();
		userSettingsPage.openPermissionsTab().experimentSharingPanel.removeAllUsers().unlockLock();
		Assert.isEmpty(Verify.getFails());
	}
	
	@Test(groups={"userPrefs"})
	public void permissionsCurrentUser() {
		landingPage.openUserPrefs();
		userSettingsPage.openPermissionsTab().protocolSharingPanel.lockLock().addUsers(Arrays.asList(""));
		userSettingsPage.openPermissionsTab().experimentSharingPanel.lockLock().addUsers(Arrays.asList(""));
		userSettingsPage.goHome().openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		verify.isTrue(() -> experimentPage.openSaveTab().expandSharingPanel().mainCheckbox.wetherChecked());
		verify.isTrue(() -> experimentPage.saveTab.expandSharingPanel().isUserInList(USERNAME));
		verify.isTrue(() -> experimentPage.openRunTab().expandSharingPanel().mainCheckbox.wetherChecked());
		verify.isTrue(() -> experimentPage.runTab.expandSharingPanel().isUserInList(USERNAME));
		experimentPage.openUserPrefs();
		userSettingsPage.openPermissionsTab().protocolSharingPanel.unlockLock();
		userSettingsPage.openPermissionsTab().experimentSharingPanel.unlockLock();
		Assert.isEmpty(Verify.getFails());
	}
	
	@Test(groups={"entity"})
	public void shareExistingHide() {
		landingPage.openDataVisualizationPage();
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.checkOpened();
		verify.isTrue(() -> !editEntityPage.openExperimentProperties().expandSharingPanel().mainCheckbox.wetherChecked());
		editEntityPage.experimentProperties.sharingPanel.mainCheckbox.checkIt();
		if (editEntityPage.experimentProperties.sharingPanel.isUserInList("mld")) {
			editEntityPage.experimentProperties.sharingPanel.removeUser("mld");
		}
		verify.isTrue(() -> editEntityPage.experimentProperties.sharingPanel.mainCheckbox.wetherChecked());
		verify.isTrue(() -> editEntityPage.experimentProperties.sharingPanel.isInputAvailable());
		editEntityPage.logout();
		loginPage.checkOpened();
		loginPage.loginAs("mld", MLDPASSWORD);
		landingPage.openDataVisualizationPage();
		verify.isTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME) == null);
		dashboardPage.logout();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataVisualizationPage();
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.openExperimentProperties().expandSharingPanel().removeAllUsers().mainCheckbox.uncheckIt();
		Assert.isEmpty(Verify.getFails());
	}

	@Test(groups={"entity"})
	public void shareExistingAllow() {
		landingPage.openDataVisualizationPage();
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.checkOpened();
		verify.isTrue(() -> !editEntityPage.openExperimentProperties().expandSharingPanel().mainCheckbox.wetherChecked());
		editEntityPage.experimentProperties.sharingPanel.mainCheckbox.checkIt();
		verify.isTrue(() -> editEntityPage.experimentProperties.sharingPanel.mainCheckbox.wetherChecked());
		verify.isTrue(() -> editEntityPage.experimentProperties.sharingPanel.isInputAvailable());
		editEntityPage.experimentProperties.sharingPanel.addUser("mld");
		editEntityPage.logout();
		loginPage.checkOpened();
		loginPage.loginAs("mld", MLDPASSWORD);
		landingPage.openDataVisualizationPage();
		verify.isTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME) != null);
		dashboardPage.logout();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataVisualizationPage();
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.openExperimentProperties().expandSharingPanel().removeAllUsers().mainCheckbox.uncheckIt();		
		Assert.isEmpty(Verify.getFails());
	}
	
	@Test(groups={"dashboard"})
	public void sharedIconTest() {
		landingPage.openDataVisualizationPage();
		assertTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME).isSharedIconPresent());
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.openExperimentProperties().expandSharingPanel().mainCheckbox.checkIt();
		editEntityPage.goHome().openDataVisualizationPage();
		assertTrue(() -> !dashboardPage.findExperiment(EXPERIMENT_NAME).isSharedIconPresent());
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.openExperimentProperties().expandSharingPanel().mainCheckbox.uncheckIt();
		editEntityPage.goHome().openDataVisualizationPage();
		assertTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME).isSharedIconPresent());
	}
	
	@Test(groups={"dashboard", "experimentCreation"})
	public void runPrivate() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		experimentPage.openDeviceTab().setDevice(DEVICE_NAME);
		String stain = experimentPage.openAcquisitionTab().getAvailableStains().get(0);
		experimentPage.openAcquisitionTab().setLabware("96 Well Plate").
		                                    setStains(Arrays.asList("Off", "Off", "Off", stain, "Off")).setObjective("4x");
		experimentPage.openAcqZonesTab().addMinZone(0.5f, 0.5f);
		experimentPage.openAnalysisSettingsTab().doAnalysis(false);
		experimentPage.openAnZonesTab().addMinZone(0.5f, 0.5f);
		experimentPage.openWellSelectionTab().selectWells(Arrays.asList("E7"));
		experimentPage.openRunTab().expandSharingPanel().mainCheckbox.checkIt();
		experimentPage.runTab.setExperimentName("Permissions Icon Test Private").run();
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment("Permissions Icon Test Private");
		monitoringPage.goHome().openDataVisualizationPage();
		assertTrue(() -> !dashboardPage.findExperiment("Permissions Icon Test Private").isSharedIconPresent());
	}

	@Test(groups={"dashboard", "experimentCreation"})
	public void runShared() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		String stain = experimentPage.openAcquisitionTab().getAvailableStains().get(0);
		experimentPage.runFromScratch(DEVICE_NAME, "96 Well Plate", Arrays.asList("Off", "Off", "Off", stain, "Off"),
				                      "4x", 0.5f, 0.5f, false, ANALYSIS_NAME,  0.5f, 0.5f, Arrays.asList("E7"), "Permissions Icon Test Shared", "");
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment("Permissions Icon Test Shared");
		monitoringPage.goHome().openDataVisualizationPage();
		assertTrue(() -> dashboardPage.findExperiment("Permissions Icon Test Shared").isSharedIconPresent());
	}
	
	@Test
	public void lockProtocol() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL).findTemplate("New Plate Acquisition").open();
		String stain = experimentPage.openAcquisitionTab().getAvailableStains().get(0);
		experimentPage.createProtocol(DEVICE_NAME, "96 Well Plate", Arrays.asList("Off", "Off", "Off", stain, "Off"),
				                      "4x", true, "Cell Count", PROTOCOL_NAME, "");
		experimentPage.goToDashboard();
		experimentTemplatesPage.selectTab(ExpDashboardTabs.PROTOCOLS).findProtocol(PROTOCOL_NAME).open();
		experimentPage.openSaveTab().expandSharingPanel().mainCheckbox.checkIt();
		experimentPage.saveTab.saveProtocol();
		experimentPage.logout();
		loginPage.loginAs("mld", MLDPASSWORD);
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS).findProtocol(PROTOCOL_NAME).open();
		verify.isTrue(() -> !experimentPage.acquisitionIcon.getWebElement().isEnabled());
		verify.isTrue(() -> !experimentPage.analysisSettingsIcon.getWebElement().isEnabled());
		verify.isTrue(() -> !experimentPage.saveIcon.getWebElement().isEnabled());
		experimentPage.logout();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		Assert.isEmpty(Verify.getFails());
	}
	
	@Test(dependsOnMethods={"lockProtocol"}, alwaysRun=true)
	public void unlockProtocol() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS).findProtocol(PROTOCOL_NAME).open();
		experimentPage.openSaveTab().expandSharingPanel().mainCheckbox.uncheckIt();
		experimentPage.saveTab.saveProtocol();
		experimentPage.logout();
		loginPage.loginAs("mld", MLDPASSWORD);
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS).findProtocol(PROTOCOL_NAME).open();
		verify.isTrue(() -> experimentPage.acquisitionIcon.getWebElement().isEnabled());
		verify.isTrue(() -> experimentPage.analysisSettingsIcon.getWebElement().isEnabled());
		verify.isTrue(() -> experimentPage.saveIcon.getWebElement().isEnabled());
		experimentPage.logout();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		Assert.isEmpty(Verify.getFails());
	}
	
	@AfterSuite
	public void cleanUp() {
		landingPage.open();
		landingPage.openDataVisualizationPage();
		dashboardPage.deleteExperiment("Permissions Icon Test Shared");
		dashboardPage.deleteExperiment("Permissions Icon Test Private");
//		dashboardPage.deleteExperiment(EXPERIMENT_NAME);
		dashboardPage.goHome();
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS).findProtocol(PROTOCOL_NAME).delete().confirm();
	}
}

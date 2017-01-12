package uitestsPO;

import static sites.EmpressSite.*;

import java.util.Arrays;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.web.matcher.testng.Assert;
import com.epam.web.matcher.verify.Verify;

import static com.epam.web.matcher.testng.Assert.assertTrue;

import enums.ExpDashboardTabs;
import enums.VisDashboardTabs;

public class PermissionsTests extends InitTest {
	
	private static final String ANALYSIS_NAME = "Cell Count";
	private static final String EXPERIMENT_NAME = "Test Permissions Experiment";
	private static final String PROTOCOL_NAME = "Protocol For Testing Lock";

	@BeforeClass
	public void initTest() {
		loginPage.isOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		if (landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS).findExperiment(EXPERIMENT_NAME) == null) {
			dashboardPage.goHome().openDataAcquisitionPage().selectTab(ExpDashboardTabs.TEMPLATES).findTemplate("New Plate Acquisition").open();
			experimentPage.runFromScratch(DEVICE_NAME, "96 Well Plate", Arrays.asList("Off", "DAPI", "TRITC", "FITC", "Off"),
					                      "4x", 0.4f, 0.4f, false, ANALYSIS_NAME, 0.5f, 0.5f, Arrays.asList("E7", "E8"), EXPERIMENT_NAME, "");
			monitoringPage.checkOpened();
			monitoringPage.waitForExperiment(EXPERIMENT_NAME);
		}
	}

	@BeforeMethod
	public void startTest() {
		landingPage.isOpened();
	}
	
	@Test(groups={"userPrefs"})
	public void permissionsTest() {
		landingPage.openUserPrefs();
		userSettingsPage.openPermissionsTab().protocolSharingPanel.lockLock().addUsers(Arrays.asList("aaa"));
		userSettingsPage.openPermissionsTab().experimentSharingPanel.lockLock().addUsers(Arrays.asList("bbb"));
		userSettingsPage.goHome().openDataAcquisitionPage();
		experimentTemplatesPage.selectTab(ExpDashboardTabs.TEMPLATES);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		verify.isTrue(() -> experimentPage.openSaveTab().expandSharingPanel().getLockState().equals("Locked"));
		verify.isTrue(() -> experimentPage.saveTab.expandSharingPanel().isUserInList("aaa"));
		verify.isTrue(() -> experimentPage.openRunTab().expandSharingPanel().getLockState().equals("Locked"));
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
		userSettingsPage.goHome().openDataAcquisitionPage().selectTab(ExpDashboardTabs.TEMPLATES);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		verify.isTrue(() -> experimentPage.openSaveTab().expandSharingPanel().getLockState().equals("Locked"));
		verify.isTrue(() -> experimentPage.saveTab.expandSharingPanel().isUserInList("user1"));
		verify.isTrue(() -> experimentPage.openRunTab().expandSharingPanel().getLockState().equals("Locked"));
		verify.isTrue(() -> experimentPage.runTab.expandSharingPanel().isUserInList("user1"));
		experimentPage.openUserPrefs();
		userSettingsPage.openPermissionsTab().protocolSharingPanel.unlockLock();
		userSettingsPage.openPermissionsTab().experimentSharingPanel.unlockLock();
		Assert.isEmpty(Verify.getFails());
	}
	
	@Test(groups={"entity"})
	public void shareExistingHide() {
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.checkOpened();
		verify.isTrue(() -> editEntityPage.expandSharingPanel().getLockState().equals("Unlocked"));
		editEntityPage.expandSharingPanel().lockLock();
		if (editEntityPage.expandSharingPanel().isUserInList("mld")) {
			editEntityPage.expandSharingPanel().removeUser("mld");
		}
		verify.isTrue(() -> editEntityPage.expandSharingPanel().getLockState().equals("Locked"));
		verify.isTrue(() -> editEntityPage.expandSharingPanel().isInputAvailable());
		editEntityPage.logout();
		loginPage.checkOpened();
		loginPage.loginAs("mld", MLDPASSWORD);
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		verify.isTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME) == null);
		dashboardPage.logout();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.expandSharingPanel().removeAllUsers().unlockLock();
		Assert.isEmpty(Verify.getFails());
	}

	@Test(groups={"entity"})
	public void shareExistingAllow() {
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.checkOpened();
		verify.isTrue(() -> editEntityPage.expandSharingPanel().getLockState().equals("Unlocked"));
		editEntityPage.expandSharingPanel().lockLock();
		verify.isTrue(() -> editEntityPage.expandSharingPanel().getLockState().equals("Locked"));
		verify.isTrue(() -> editEntityPage.expandSharingPanel().isInputAvailable());
		editEntityPage.expandSharingPanel().addUser("mld");
		editEntityPage.logout();
		loginPage.checkOpened();
		loginPage.loginAs("mld", MLDPASSWORD);
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		verify.isTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME) != null);
		dashboardPage.logout();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.expandSharingPanel().removeAllUsers().unlockLock();		
		Assert.isEmpty(Verify.getFails());
	}
	
	@Test(groups={"dashboard"})
	public void sharedIconTest() {
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		assertTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME).isSharedIconPresent());
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.expandSharingPanel().lockLock();
		editEntityPage.goToDashboard();
		assertTrue(() -> !dashboardPage.findExperiment(EXPERIMENT_NAME).isSharedIconPresent());
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.expandSharingPanel().unlockLock();
		editEntityPage.goToDashboard();
		assertTrue(() -> dashboardPage.findExperiment(EXPERIMENT_NAME).isSharedIconPresent());
	}
	
	@Test(groups={"dashboard", "experimentCreation"})
	public void runPrivate() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.TEMPLATES);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		experimentPage.openDeviceTab().setDevice(DEVICE_NAME);
		experimentPage.openAcquisitionTab().setLabware("96 Well Plate").
		                                    setStains(Arrays.asList("Off", "Off", "Off", "DAPI", "Off")).setObjective("4x");
		experimentPage.openAcqZonesTab().addMinZone(0.5f, 0.5f);
		experimentPage.openAnalysisSettingsTab().doAnalysis(false);
		experimentPage.openAnZonesTab().addMinZone(0.5f, 0.5f);
		experimentPage.openWellSelectionTab().selectWells(Arrays.asList("E7"));
		experimentPage.openRunTab().expandSharingPanel().lockLock();
		experimentPage.runTab.setExperimentName("Permissions Icon Test Private").run();
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment("Permissions Icon Test Private");
		monitoringPage.goHome().openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		assertTrue(() -> !dashboardPage.findExperiment("Permissions Icon Test Private").isSharedIconPresent());
	}

	@Test(groups={"dashboard", "experimentCreation"})
	public void runShared() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.TEMPLATES);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		experimentPage.runFromScratch(DEVICE_NAME, "96 Well Plate", Arrays.asList("Off", "Off", "Off", "DAPI", "Off"),
				                      "4x", 0.5f, 0.5f, false, ANALYSIS_NAME,  0.5f, 0.5f, Arrays.asList("E7"), "Permissions Icon Test Shared", "");
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment("Permissions Icon Test Shared");
		monitoringPage.goHome().openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		assertTrue(() -> dashboardPage.findExperiment("Permissions Icon Test Shared").isSharedIconPresent());
	}
	
	@Test
	public void lockProtocol() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.TEMPLATES).findTemplate("New Plate Acquisition").open();
		experimentPage.createProtocol(DEVICE_NAME, "96 Well Plate", Arrays.asList("Off", "DAPI", "TRITC", "FITC", "Off"),
				                      "4x", true, "Cell Count", PROTOCOL_NAME, "");
		experimentPage.goToDashboard();
		experimentTemplatesPage.selectTab(ExpDashboardTabs.PROTOCOLS).findProtocol(PROTOCOL_NAME).open();
		experimentPage.openSaveTab().expandSharingPanel().lockLock();
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
		experimentPage.openSaveTab().expandSharingPanel().unlockLock();
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
		landingPage.isOpened();	
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.deleteExperiment("Permissions Icon Test Shared");
		dashboardPage.deleteExperiment("Permissions Icon Test Private");
//		dashboardPage.deleteExperiment(EXPERIMENT_NAME);
		dashboardPage.goHome();
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS).findProtocol(PROTOCOL_NAME).delete().confirm();
	}
}

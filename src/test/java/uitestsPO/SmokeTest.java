package uitestsPO;

import java.util.Arrays;
import java.util.List;

import static com.epam.web.matcher.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.epam.web.matcher.testng.Assert;
import com.epam.web.matcher.verify.Verify;

import static sites.EmpressSite.*;
import enums.ExpDashboardTabs;
import enums.MonitoringTabs;
import sections.acuisition.AnalysisSettingsTab;
import enums.PlateAnalysisViews;

public class SmokeTest extends InitTest {

	private static final List<String> STAINS = Arrays.asList("Cy5", "Off", "TRITC", "FITC", "Capture first");
	private static final String ANALYSIS_NAME = "Live Cells";
	private static final int[] LABWARE_GEOMETRY = {12,8};
	private static final String LABWARE_NAME = Integer.toString(LABWARE_GEOMETRY[0] * LABWARE_GEOMETRY[1]) + " Well Plate";
	private static final List<String> WELLS = Arrays.asList("E7", "E8", "E9", "E10");
	private static final String PROTOCOL_NAME = "Test Protocol";
	private static final String EXPERIMENT_NAME = "Auto Test Run 1487063992204"; //"Auto Test Run " + System.currentTimeMillis();
	
	
	@BeforeSuite
	public void login() {
		loginPage.open();
		loginPage.checkOpened();
		loginPage.loginAsReturn(USERNAME, PASSWORD);
		landingPage.checkOpened();
	}

	@BeforeMethod
	public void openLanding() {
		landingPage.open();
	}

	@Test
	public void createProtocol() {
		landingPage.openDataAcquisitionPage();
		experimentTemplatesPage.selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		experimentPage.checkOpened();
		experimentPage.createProtocol(DEVICE_NAME, LABWARE_NAME, STAINS, 
				                      "4x", true, ANALYSIS_NAME, PROTOCOL_NAME, "For automation needs");
		assertTrue(experimentPage.isSuccessfulSave());
		experimentPage.goHome().openDataAcquisitionPage();
		experimentTemplatesPage.selectTab(ExpDashboardTabs.PROTOCOLS);
		assertTrue(experimentTemplatesPage.findProtocol(PROTOCOL_NAME) != null);
	}
	
	@Test (dependsOnMethods={"createProtocol"})
	public void verifyProtocol() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS);
		experimentTemplatesPage.findProtocol(PROTOCOL_NAME).open();
		verify.isTrue(() -> experimentPage.selectDeviceTab.getSelectedDeviceName().equals(DEVICE_NAME));
		verify.isTrue(() -> experimentPage.openAcquisitionTab().getSelectedLabwareName().equals(LABWARE_NAME));
		verify.isTrue(() -> experimentPage.openAnalysisSettingsTab().getSelectedAnalysisName().equals(ANALYSIS_NAME));
		experimentPage.analysisSettingsTab.singleMode().openAlgorithmInputPanel().selectTab("Nuclei").setProperty("Min Width", 3);
		experimentPage.analysisSettingsTab.runTestAnalysis();
		Assert.isTrue(() -> !experimentPage.analysisSettingsTab.expandSumMeasurements().values.get(0).getValue().equals(""));
		Assert.isEmpty(Verify.getFails());
	}
	
	@Test(dependsOnMethods={"runProtocol"}, alwaysRun=true)
	public void deleteProtocol() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS);
		experimentTemplatesPage.findProtocol(PROTOCOL_NAME).delete().confirm();
		assertTrue(() -> experimentTemplatesPage.absenceOfProtocol(PROTOCOL_NAME));
	}
	
	@Test(dependsOnMethods={"verifyProtocol"})
	public void runProtocol() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.PROTOCOLS);
		experimentTemplatesPage.findProtocol(PROTOCOL_NAME).run();
		experimentPage.checkOpened();
		experimentPage.runExperiment(0.5f, 0.5f, 0.5f, 0.5f, WELLS, EXPERIMENT_NAME, "For automation needs");
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment(EXPERIMENT_NAME);
		monitoringPage.tabs.select(MonitoringTabs.SUCCEEDED);
		Assert.assertTrue(() -> monitoringPage.hasSucceeded(EXPERIMENT_NAME));
	}
	
	@Test //(dependsOnMethods={"runProtocol"})
	public void verifyExperiment() {
		landingPage.openDataVisualizationPage();
		dashboardPage.findExperiment(EXPERIMENT_NAME).choose("Acquired images").view();
		assertTrue(() -> viewAnalysisPage.thumbView.isMainControlDisplayed());
		viewAnalysisPage.navigateToWellView(PlateAnalysisViews.IMAGES);
		assertTrue(() -> viewAnalysisPage.deepZoom.checkWells(WELLS));
		viewAnalysisPage.switchTo(EXPERIMENT_NAME);
		assertTrue(() -> viewAnalysisPage.deepZoom.areZonesDisplayed(1));
		viewAnalysisPage.navigateToWellView(PlateAnalysisViews.HEATMAP);
		assertTrue(() -> viewAnalysisPage.heatmap.isMainControlDisplayed());
		viewAnalysisPage.heatmap.selectWells(WELLS, LABWARE_GEOMETRY);
		viewAnalysisPage.leftNavPanel.goToCellHeatmap();
		assertTrue(() -> viewAnalysisPage.cellHeatmap.isMainControlDisplayed());
	}
	
	@Test(dependsOnMethods={"verifyExperiment"}, alwaysRun=true)
	public void deleteExperiment() {
		landingPage.openDataVisualizationPage();
		dashboardPage.findExperiment(EXPERIMENT_NAME).open();
		editEntityPage.deleteExperiment().confirm();
		dashboardPage.checkOpened();
	}
	
}



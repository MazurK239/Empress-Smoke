package uitestsPO;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.web.matcher.testng.Assert;

import enums.ExpDashboardTabs;
import enums.ExperimentEditTabs;
import enums.MonitoringTabs;

import static sites.EmpressSite.*;

import java.util.Arrays;

public class AddAnalysisRegression extends InitTest {

	private static final String ANALYSIS_TEMPLATE = "Cell Count";
	private static final String EXPERIMENT_NAME = "Testing Analyses";
	private static final String ANALYSIS_NAME = "Auto Test Analysis " + System.currentTimeMillis();
	private static final String BIG_ANALYSIS_NAME = ANALYSIS_TEMPLATE;
	private static final String PROTOCOL_NAME = "New Plate Acquisition";

	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		loginPage.open();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		try {
			landingPage.openDataVisualizationPage().findExperiment(EXPERIMENT_NAME).open();			
		} catch (NullPointerException e) {
			landingPage.open();
			landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL)
						                         .findTemplate(PROTOCOL_NAME).open();
			experimentPage.openDeviceTab().setDevice(DEVICE_NAME);
			experimentPage.openAcquisitionTab().setLabware("24 Well Costar #3526")
											   .setStains(Arrays.asList("FITC", "DAPI", "Cy5", "TRITC", "Capture last"))
											   .setObjective("4x");
			experimentPage.openAcqZonesTab().addAutoCenteredZone(1, 1);
			experimentPage.openAnalysisSettingsTab().doAnalysis(false);
			experimentPage.openWellSelectionTab().selectWells(Arrays.asList("C4", "C3", "B4", "B3"));
			experimentPage.openWorkflowTab().enableWorkflow();
			experimentPage.openRunTab().setRunName(EXPERIMENT_NAME).run();
			monitoringPage.checkOpened();
			monitoringPage.waitForExperiment(EXPERIMENT_NAME);
			monitoringPage.tabs.select(MonitoringTabs.SUCCEEDED);
			Assert.assertTrue(() -> monitoringPage.hasSucceeded(EXPERIMENT_NAME));
			
//			landingPage.open();
//			landingPage.openDataVisualizationPage().findExperiment(EXPERIMENT_NAME).open();
//			experimentEditPage.tabs.select(ExperimentEditTabs.ACQUISITIONS);
//			experimentEditPage.acquisitionsSection.findProtocol(PROTOCOL_NAME).expand().launch();
//			experimentPage.checkOpened();
//			experimentPage.openRunTab().run();
//			monitoringPage.checkOpened();
//			monitoringPage.waitForExperiment("Adding to " + EXPERIMENT_NAME);
//			monitoringPage.tabs.select(MonitoringTabs.SUCCEEDED);
//			Assert.assertTrue(() -> monitoringPage.hasSucceeded("Adding to " + EXPERIMENT_NAME));
			
			landingPage.open();
			landingPage.openDataVisualizationPage().findExperiment(EXPERIMENT_NAME).open();
			experimentEditPage.checkOpened();
			experimentEditPage.tabs.select(ExperimentEditTabs.ACQUISITIONS);
			experimentEditPage.duplicateProtocol(PROTOCOL_NAME);
			experimentPage.checkOpened();
			experimentPage.openAcquisitionTab().setStains(Arrays.asList("DAPI", "Texas Red", "Off", "Off", "Capture first"));
			experimentPage.acquisitionTab.setObjective("4x");
			experimentPage.openAcqZonesTab().addAutoCenteredZone(2, 1);
			experimentPage.openAnalysisSettingsTab().doAnalysis(true).chooseTemplate(ANALYSIS_TEMPLATE);
			experimentPage.openAnZonesTab().addZoneButton.click();
			experimentPage.openRunTab().setRunName(PROTOCOL_NAME + " 2").run();
			monitoringPage.checkOpened();
			monitoringPage.waitForExperiment("Adding to " + EXPERIMENT_NAME);
			monitoringPage.tabs.select(MonitoringTabs.SUCCEEDED);
			Assert.assertTrue(() -> monitoringPage.hasSucceeded("Adding to " + EXPERIMENT_NAME));
		}
		landingPage.open();
	}
	
	@BeforeTest(alwaysRun = true)
	public void openExperiment() {
		landingPage.checkOpened();
		landingPage.openDataVisualizationPage().findExperiment(EXPERIMENT_NAME).open();
		experimentEditPage.checkOpened();
	}
	
	@AfterTest(alwaysRun = true)
	public void exitToLanding() {
		landingPage.open();
		if (!addAnalysisPage.confirmation.isDisplayed()) {
			return;
		}
		addAnalysisPage.confirmation.ok();
	}
	
	@Test(groups={"basic"})
	public void addSimpleAnalysis() {
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openAnalysisSettingsTab().chooseTemplate(ANALYSIS_TEMPLATE);
		addAnalysisPage.analysisSettingsTab.runTestAnalysis();
		Assert.isTrue(() -> !experimentPage.analysisSettingsTab.expandSumMeasurements().values.get(0).getValue().equals(""));
		addAnalysisPage.openZoneSelectionTab().useMaxCoverage();
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab().setRunName(ANALYSIS_NAME);
		Assert.assertTrue(() -> addAnalysisPage.runTab.validationSummary.allPassed());
		addAnalysisPage.runTab.run();
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment("Adding to " + EXPERIMENT_NAME);
		monitoringPage.tabs.select(MonitoringTabs.SUCCEEDED);
		Assert.assertTrue(() -> monitoringPage.hasSucceeded("Adding to " + EXPERIMENT_NAME));
		monitoringPage.deleteAllJobs();
		Assert.assertTrue(() -> monitoringPage.noDataAvailable());
	}
	
	@Test(dependsOnMethods={"addSimpleAnalysis"}, groups={"basic"})
	public void launchAnalysis() {
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.findAnalysis(ANALYSIS_NAME).expand().launch();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int [] {2});
		addAnalysisPage.openZoneSelectionTab();
		Assert.isTrue(() -> addAnalysisPage.zoneSelectionTab.getAcquiredArea().equals(addAnalysisPage.zoneSelectionTab.getAnalyzedArea()));
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.assertTrue(() -> addAnalysisPage.runTab.validationSummary.allPassed());
	    addAnalysisPage.runTab.run();
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment("Adding to " + EXPERIMENT_NAME);
		monitoringPage.tabs.select(MonitoringTabs.SUCCEEDED);
		Assert.assertTrue(() -> monitoringPage.hasSucceeded("Adding to " + EXPERIMENT_NAME));
	}
	
	@Test(dependsOnMethods={"addSimpleAnalysis"}, groups={"basic"})
	public void duplicateAnalysis() {
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.duplicateAnalysis(ANALYSIS_NAME);
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectAll();
		addAnalysisPage.openAnalysisSettingsTab();
		Assert.isTrue(() -> addAnalysisPage.analysisSettingsTab.getSelectedAnalysisName().equals(ANALYSIS_TEMPLATE));
		addAnalysisPage.openRunTab();
		addAnalysisPage.runTab.setRunName(ANALYSIS_NAME + " duplicated");
		Assert.assertTrue(() -> addAnalysisPage.runTab.validationSummary.allPassed());
	    addAnalysisPage.runTab.run();
		monitoringPage.checkOpened();
		monitoringPage.waitForExperiment("Adding to " + EXPERIMENT_NAME);
		monitoringPage.tabs.select(MonitoringTabs.SUCCEEDED);
		Assert.assertTrue(() -> monitoringPage.hasSucceeded("Adding to " + EXPERIMENT_NAME));		
	}
	
	@AfterMethod(groups={"basic"})
	public void returnToExpEditFromMonitoring() {
		landingPage.open();
		if (addAnalysisPage.confirmation.isDisplayed()) {
			addAnalysisPage.confirmation.ok();
		}
		landingPage.checkOpened();
		landingPage.openDataVisualizationPage().findExperiment(EXPERIMENT_NAME).open();
		experimentEditPage.checkOpened();
	}
	
	@Test(groups={"validation"})
	public void noName() {
		/*
		 * add analysis
		 * select 1 T
		 * select template
		 * add zone
		 * select all wells
		 * open run tab
		 * verify that name error is present
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openAnalysisSettingsTab().chooseTemplate(ANALYSIS_TEMPLATE);
		addAnalysisPage.openZoneSelectionTab().useMaxCoverage();
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.isNameWarningPresent());
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(() -> addAnalysisPage.getMessage().equalsIgnoreCase("Please specify analysis name"));
	}

	@Test(groups={"validation"})
	public void noTimePoints() {
		/*
		 * add analysis
		 * deselect all T's
		 * select template
		 * add zone
		 * select all wells
		 * open run tab
		 * verify that T error is present
		 * type name
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.clearAll();
		addAnalysisPage.openAnalysisSettingsTab().chooseTemplate(ANALYSIS_TEMPLATE);
		addAnalysisPage.openZoneSelectionTab().useMaxCoverage();
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.validationSummary.getRowByParam("Time Points").hasNotPassed());
		addAnalysisPage.runTab.validationSummary.openMessage("Time Points");
		String errorMessage = addAnalysisPage.getMessage();
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(() -> addAnalysisPage.getMessage().contains(errorMessage));
	}

	@Test(groups={"validation"})
	public void noZones() {
		/*
		 * add analysis
		 * select 1 T
		 * select template
		 * remove all zones
		 * select all wells
		 * open run tab
		 * verify that zone error is present
		 * type name
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openAnalysisSettingsTab().chooseTemplate(ANALYSIS_TEMPLATE);
		addAnalysisPage.openZoneSelectionTab().clearAll();
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.validationSummary.getRowByParam("Analysis zones").hasNotPassed());
		addAnalysisPage.runTab.validationSummary.openMessage("Analysis zones");
		String errorMessage = addAnalysisPage.getMessage();
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(() -> addAnalysisPage.getMessage().contains(errorMessage));
	}
	
	@Test(groups={"validation"})
	public void noWells() {
		/*
		 * add analysis
		 * select 1 T
		 * select template
		 * add zone
		 * remove all wells
		 * open run tab
		 * verify that T error is present
		 * type name
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openAnalysisSettingsTab().chooseTemplate(ANALYSIS_TEMPLATE);
		addAnalysisPage.openZoneSelectionTab().useMaxCoverage();;
		addAnalysisPage.openWellSelectionTab().clearAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.validationSummary.getRowByParam("Time Points").hasNotPassed());
		addAnalysisPage.runTab.validationSummary.openMessage("Time Points");
		String errorMessage = addAnalysisPage.getMessage();
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(() -> addAnalysisPage.getMessage().contains(errorMessage));

	}

	@Test(groups={"validation"})
	public void noTemplate() {
		/*
		 * add analysis
		 * select 1 T
		 * add zone
		 * select all wells
		 * open run tab
		 * verify that selected measurements error is present
		 * type name
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openZoneSelectionTab().useMaxCoverage();
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.validationSummary.getRowByParam("Selected Measurements").hasNotPassed());
		addAnalysisPage.runTab.validationSummary.openMessage("Selected Measurements");
		String errorMessage = addAnalysisPage.getMessage();
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(() -> addAnalysisPage.getMessage().contains(errorMessage));

	}

	@Test(groups={"validation"})
	public void noMeasurements() {
		/*
		 * add analysis
		 * select 1 T
		 * select Template
		 * deselect all measurements
		 * add zone
		 * select all wells
		 * open run tab
		 * verify that selected measurements error is present
		 * type name
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openAnalysisSettingsTab().chooseTemplate(ANALYSIS_TEMPLATE);
		addAnalysisPage.analysisSettingsTab.expandSumMeasurements().deselectAll();
		addAnalysisPage.analysisSettingsTab.expandCellMeasurements().deselectAll();
		addAnalysisPage.openZoneSelectionTab().useMaxCoverage();
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.validationSummary.getRowByParam("Selected Measurements").hasNotPassed());
		addAnalysisPage.runTab.validationSummary.openMessage("Selected Measurements");
		String errorMessage = addAnalysisPage.getMessage();
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(() -> addAnalysisPage.getMessage().contains(errorMessage));
	}

	@Test(groups={"validation"})
	public void badChannel() {
		/* 
		 * launch first analysis
		 * select T2
		 * select all wells
		 * open run tab
		 * verify that T error is present
		 * type name
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openAnalysisSettingsTab().chooseTemplate(ANALYSIS_TEMPLATE);
		addAnalysisPage.analysisSettingsTab.openAlgorithmInputPanel().setTargetChannel("Texas Red");
		addAnalysisPage.openZoneSelectionTab().useMaxCoverage();
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.validationSummary.getRowByParam("Time points").hasNotPassed());
		addAnalysisPage.runTab.validationSummary.openMessage("Time points");
		String errorMessage = addAnalysisPage.getMessage();
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(() -> addAnalysisPage.getMessage().contains(errorMessage));
	}

	@Test(groups={"validation"})
	public void bigZone() {
		/* 
		 * launch second analysis
		 * select T2
		 * verify that big zone alert is present on the ZS page
		 * select all wells
		 * open run tab
		 * verify that T error is present
		 * type name
		 * run
		 * verify that error is present
		 */
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.findAnalysis(BIG_ANALYSIS_NAME).expand().launch();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openWellSelectionTab().selectAll();
		addAnalysisPage.openRunTab();
		Assert.isTrue(() -> addAnalysisPage.runTab.validationSummary.getRowByParam("time points").hasNotPassed());
		addAnalysisPage.runTab.validationSummary.openMessage("time points");
		String errorMessage = addAnalysisPage.getMessage();
		addAnalysisPage.runTab.run();
		Assert.isTrue(() -> addAnalysisPage.hasMessage());
		Assert.isTrue(addAnalysisPage.getMessage().contains(errorMessage));		
	}

	@BeforeMethod(groups={"toggler"})
	public void togglerPrecondition() {
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1});
		addAnalysisPage.openZoneSelectionTab();
	}
	
	@Test(groups={"toggler"})
	public void togglerDefaultState() {
		Assert.isTrue(() -> addAnalysisPage.zoneSelectionTab.analyzeAllToggler.whetherChecked());
	}
	
	@Test(groups={"toggler"})
	public void togglerOffAutoMode() {
		addAnalysisPage.zoneSelectionTab.openAutoPanel();
		Assert.isTrue(() -> !addAnalysisPage.zoneSelectionTab.analyzeAllToggler.whetherChecked());
	}

	@Test(groups={"toggler"})
	public void togglerOffManualMode() {
		addAnalysisPage.zoneSelectionTab.switchToManualMode();
		Assert.isTrue(() -> !addAnalysisPage.zoneSelectionTab.analyzeAllToggler.whetherChecked());
	}

	@Test(groups={"toggler"})
	public void togglerOnAfterManual() {
		addAnalysisPage.zoneSelectionTab.switchToManualMode();
		addAnalysisPage.zoneSelectionTab.clearAll();
		Assert.isTrue(() -> addAnalysisPage.zoneSelectionTab.getAnalyzedArea().equals("0 m"));
		addAnalysisPage.zoneSelectionTab.useMaxCoverage();
		Assert.isTrue(() -> addAnalysisPage.zoneSelectionTab.getAcquiredArea().equals(addAnalysisPage.zoneSelectionTab.getAnalyzedArea()));
	}

	@Test(groups={"toggler"})
	public void togglerOnAfterAuto() {
		addAnalysisPage.zoneSelectionTab.openAutoPanel().setUnits("mcm").resizeAutoZone(100000);
		addAnalysisPage.zoneSelectionTab.useMaxCoverage();
		Assert.isTrue(() -> addAnalysisPage.zoneSelectionTab.getAcquiredArea().equals(addAnalysisPage.zoneSelectionTab.getAnalyzedArea()));
	}
	
	@AfterMethod(groups={"toggler", "validation"})
	public void openExpEdit() {
		addAnalysisPage.openExpEditPage();
		experimentEditPage.checkOpened();
	}

	@Test(groups={"slider", "change_timepoints"})
	public void sliderMaxValueChange() {
		experimentEditPage.tabs.select(ExperimentEditTabs.ANALYSES);
		experimentEditPage.analysesSection.addAnalysis();
		addAnalysisPage.checkOpened();
		addAnalysisPage.timePointsTab.selectTimePoints(new int[] {1,2});
		addAnalysisPage.openZoneSelectionTab();
		String value1 = addAnalysisPage.zoneSelectionTab.openAutoPanel().getMaxSliderValue();
		addAnalysisPage.openTimePointsTab().selectTimePoints(new int[] {3,4});
		addAnalysisPage.openZoneSelectionTab();
		String value2 = addAnalysisPage.zoneSelectionTab.openAutoPanel().getMaxSliderValue();
		Assert.isTrue(!value1.equals(value2));
		addAnalysisPage.openTimePointsTab().selectAll();
		addAnalysisPage.openZoneSelectionTab();
		String value3 = addAnalysisPage.zoneSelectionTab.openAutoPanel().getMaxSliderValue();
		Assert.isTrue(value3.equals(value1));
	}
}

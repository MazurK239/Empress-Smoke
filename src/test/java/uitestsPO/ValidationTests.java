package uitestsPO;

import static com.epam.web.matcher.testng.Assert.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import enums.ExpDashboardTabs;

import static sites.EmpressSite.*;

import java.util.Arrays;

public class ValidationTests extends InitTest {

	@BeforeSuite
	public void loginAndNavigate() {
		loginPage.open();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
		initStainLists();
		landingPage.openDataAcquisitionPage();
	}
	
	@BeforeMethod
	public void openTemplate() {
		experimentTemplatesPage.selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate(TEMPLATE_NAME).open();
		experimentPage.openDeviceTab().setDevice(DEVICE_NAME);		
	}
	
	@AfterMethod
	public void closeTemplate() {
		experimentPage.goToDashboard();
	}
	
	
	@Test
	public void stainsValidation() {
		experimentPage.openAcquisitionTab().setStains(Arrays.asList(UNAVAILABLE_STAINS.get(0), AVAILABLE_STAINS.get(0), "Off", "Off", "Off"));
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasNotPassed("Device"));
		assertTrue(() -> experimentPage.runTab.validationSummary.hasNotPassed("Stains"));
		experimentPage.openRunTab().validationSummary.openMessage("Stains");
		assertTrue(experimentPage.getMessage().contains("is not active."));
		experimentPage.openAcquisitionTab().setStains(Arrays.asList(AVAILABLE_STAINS.get(0), "Off", "Off", "Off", "Off"));
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasPassed("Device"));
		assertTrue(() -> experimentPage.runTab.validationSummary.hasPassed("Stains"));		
	}
	
	@Test
	public void zonesValidation() {
		experimentPage.openAcqZonesTab().clearAll();
		experimentPage.openAnZonesTab().clearAll();
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasNotPassed("Acquisition region"));
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasNotPassed("Analysis regions"));
		experimentPage.openRunTab().validationSummary.openMessage("Acquisition region");
		assertTrue(experimentPage.getMessage().contains("No acquisition regions selected."));
		experimentPage.openRunTab().validationSummary.openMessage("Analysis regions");
		assertTrue(experimentPage.getMessage().contains("No analysis regions selected."));
		experimentPage.openAcqZonesTab().addMinZone(0.5f, 0.5f);
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasPassed("Acquisition region"));
		experimentPage.openAnZonesTab().addMinZone(0.5f, 0.5f);
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasPassed("Analysis regions"));
	}
	
	@Test
	public void analysisOffValidation() {
		experimentPage.openAnZonesTab().clearAll();
		experimentPage.openAnalysisSettingsTab().expandCellMeasurements().deselectAll();
		experimentPage.openAnalysisSettingsTab().expandSumMeasurements().deselectAll();
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasNotPassed("Analysis regions"));
		assertTrue(() -> experimentPage.runTab.validationSummary.hasNotPassed("Selected measurements"));
		assertTrue(() -> experimentPage.runTab.validationSummary.getRowByParam("Analysis services") != null);
		experimentPage.openAcquisitionTab().setStains(Arrays.asList("Off", AVAILABLE_STAINS.get(0), "Off", "Off", "Off"));
		experimentPage.openWellSelectionTab().selectWells(Arrays.asList("A1"));
		experimentPage.openRunTab().setRunName("aaa").run();
		assertTrue(() -> experimentPage.hasMessage());
		experimentPage.openAnalysisSettingsTab().doAnalysis(false);
		assertTrue(() -> experimentPage.openRunTab().validationSummary.getRowByParam("Analysis regions") == null);
		assertTrue(() -> experimentPage.runTab.validationSummary.getRowByParam("Analysis services") == null);
		assertTrue(() -> experimentPage.runTab.validationSummary.getRowByParam("Selected measurements") == null);
		experimentPage.openAnalysisSettingsTab().doAnalysis(true);
	}
	
	@Test
	public void wellsValidation() {
		experimentPage.openWellSelectionTab().clearAll();
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasNotPassed("Selected Wells"));
		experimentPage.runTab.run();
		assertTrue(() -> experimentPage.hasMessage());
		experimentPage.runTab.validationSummary.openMessage("Selected Wells");
		assertTrue(experimentPage.getMessage().contains("No wells selected."));
		experimentPage.openWellSelectionTab().selectWells(Arrays.asList("A1"));
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasPassed("Selected Wells"));		
	}
	
	@Test
	public void measurementsValidation() {
		experimentPage.openAnalysisSettingsTab().expandSumMeasurements().deselectAll();
		experimentPage.openAnalysisSettingsTab().expandCellMeasurements().deselectAll();
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasNotPassed("Selected measurements"));
		experimentPage.runTab.run();
		assertTrue(() -> experimentPage.hasMessage());
		experimentPage.openRunTab().validationSummary.openMessage("Selected measurements");
		assertTrue(experimentPage.getMessage().contains("At least one measurement needs to be selected."));
		experimentPage.openAnalysisSettingsTab().expandCellMeasurements().selectSegmentation();
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasPassed("Selected measurements"));		
	}
	
	@Test
	public void locationAndStorageValidation() {
		experimentPage.openAcquisitionTab().setLabware("6 Well Costar #3516")
		                                   .setObjective("40x")
		                                   .setStains(Arrays.asList(AVAILABLE_STAINS.get(0), AVAILABLE_STAINS.get(1), AVAILABLE_STAINS.get(2),
		                                		   					AVAILABLE_STAINS.get(3), TL_OPTIONS.get(1)));
		experimentPage.openAcqZonesTab().addMaxZone();
		experimentPage.openWellSelectionTab().selectAll();
		assertTrue(() -> experimentPage.openRunTab().validationSummary.hasNotPassed("Device Temp Storage"));
		assertTrue(() -> experimentPage.runTab.validationSummary.hasNotPassed("Data Storage"));
		assertTrue(() -> experimentPage.runTab.validationSummary.hasNotPassed("Device"));
	}
	
}

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
		loginPage.isOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDataAcquisitionPage();
	}
	
	@BeforeMethod
	public void openTemplate() {
		experimentTemplatesPage.selectTab(ExpDashboardTabs.ADD_PROTOCOL);
		experimentTemplatesPage.findTemplate("New Plate Acquisition").open();
		experimentPage.openDeviceTab().setDevice(DEVICE_NAME);		
	}
	
	@AfterMethod
	public void closeTemplate() {
		experimentPage.goToDashboard();
	}
	
	
	@Test
	public void stainsValidation() {
		String unavailableStain = experimentPage.openAcquisitionTab().getUnavailableStains().get(0);
		experimentPage.openAcquisitionTab().setStains(Arrays.asList(unavailableStain, "DAPI", "Off", "Off", "Off"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Instrument"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Stains"));
		experimentPage.openRunTab().getValidationSummary().openMessage("Stains");
		assertTrue(experimentPage.getMessage().contains("is not active."));
		experimentPage.openAcquisitionTab().setStains(Arrays.asList("FITC", "DAPI", "Off", "Off", "Off"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasPassed("Instrument"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasPassed("Stains"));		
	}
	
	@Test
	public void zonesValidation() {
		experimentPage.openAcqZonesTab().clearAll();
		experimentPage.openAnZonesTab().clearAll();
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Acquisition zones"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Analysis zones"));
		experimentPage.openRunTab().getValidationSummary().openMessage("Acquisition zones");
		assertTrue(experimentPage.getMessage().contains("No acquisition zones selected."));
		experimentPage.openRunTab().getValidationSummary().openMessage("Analysis zones");
		assertTrue(experimentPage.getMessage().contains("No analysis zones selected."));
		experimentPage.openAcqZonesTab().addMinZone(0.5f, 0.5f);
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasPassed("Acquisition zones"));
		experimentPage.openAnZonesTab().addMinZone(0.5f, 0.5f);
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasPassed("Analysis zones"));
	}
	
	@Test
	public void analysisOffValidation() {
		experimentPage.openAnZonesTab().clearAll();
		experimentPage.openAnalysisSettingsTab().expandCellMeasurements().deselectAll();
		experimentPage.openAnalysisSettingsTab().expandSumMeasurements().deselectAll();
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Analysis zones"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Selected measurements"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().getRowByParam("Analysis services") != null);
		experimentPage.openAcquisitionTab().setStains(Arrays.asList("Off", "DAPI", "Off", "Off", "Off"));
		experimentPage.openWellSelectionTab().selectWells(Arrays.asList("A1"));
		experimentPage.openRunTab().setExperimentName("aaa");
		assertTrue(() -> !experimentPage.openRunTab().canRun());
		experimentPage.openAnalysisSettingsTab().doAnalysis(false);
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().getRowByParam("Analysis zones") == null);
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().getRowByParam("Analysis services") == null);
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().getRowByParam("Selected measurements") == null);
		assertTrue(() -> experimentPage.openRunTab().canRun());		
		experimentPage.openAnalysisSettingsTab().doAnalysis(true);
	}
	
	@Test
	public void wellsValidation() {
		experimentPage.openWellSelectionTab().clearAll();
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Selected wells"));
		assertTrue(() -> !experimentPage.openRunTab().canRun());
		experimentPage.openRunTab().getValidationSummary().openMessage("Selected wells");
		assertTrue(experimentPage.getMessage().contains("No wells selected."));
		experimentPage.openWellSelectionTab().selectWells(Arrays.asList("A1"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasPassed("Selected wells"));		
	}
	
	@Test
	public void measurementsValidation() {
		experimentPage.openAnalysisSettingsTab().expandSumMeasurements().deselectAll();
		experimentPage.openAnalysisSettingsTab().expandCellMeasurements().deselectAll();
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Selected measurements"));
		assertTrue(() -> !experimentPage.openRunTab().canRun());
		experimentPage.openRunTab().getValidationSummary().openMessage("Selected measurements");
		assertTrue(experimentPage.getMessage().contains("At least one measurement needs to be selected."));
		experimentPage.openAnalysisSettingsTab().expandCellMeasurements().selectSegmentation();
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasPassed("Selected measurements"));		
	}
	
	@Test
	public void locationAndStorageValidation() {
		experimentPage.openAcquisitionTab().setLabware("6 Well Costar #3516")
		                                   .setObjective("40x")
		                                   .setStains(Arrays.asList("TRITC/Cy3", "DAPI", "TRITC", "FITC", "First"));
		experimentPage.openAcqZonesTab().addMaxZone();
		experimentPage.openWellSelectionTab().selectAll();
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Storage"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Location"));
		assertTrue(() -> experimentPage.openRunTab().getValidationSummary().hasNotPassed("Instrument"));
	}
	
}

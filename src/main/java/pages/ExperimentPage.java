package pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

import sections.acquisition.*;
import sites.EmpressSite;

import java.util.List;

import org.openqa.selenium.support.FindBy;

public class ExperimentPage extends InternalPage {
	
	@FindBy(css=".general-button-panel_left div:nth-child(2) button")
	public Button selectDeviceIcon;
	
	@FindBy(css="mld-experiment-device-panel")
	public DeviceTab selectDeviceTab;

	@FindBy(css=".general-button-panel_left div:nth-child(3) button")
	public Button acquisitionIcon;

	@FindBy(css="mld-experiment-acquisition-panel")
	public AcquisitionTab acquisitionTab;

	@FindBy(css=".general-button-panel_left div:nth-child(4) button")
	public Button acquisitionZonesIcon;

	@FindBy(css="mld-experiment-wellarea-acquisition-panel")
	public AcqZoneSelectionTab acquisitionZonesTab;

	@FindBy(css=".general-button-panel_left div:nth-child(5) button")
	public Button analysisSettingsIcon;

	@FindBy(css="mld-experiment-analysis-panel")
	public AnalysisSettingsTab analysisSettingsTab;

	@FindBy(css=".general-button-panel_left div:nth-child(7) button")
	public Button analysisZonesIcon;

	@FindBy(css="mld-experiment-wellarea-analysis-panel")
	public AnZoneSelectionTab analysisZonesTab;

	@FindBy(css=".general-button-panel_left div:nth-child(8) button")
	public Button wellSelectionIcon;

	@FindBy(css="mld-experiment-well-selection-panel")
	public WellSelectionTab wellSelectionTab;

	@FindBy(css=".general-button-panel_left div:nth-last-child(3) button")
	public Button workflowIcon;
	
	@FindBy(css="mld-experiment-workflow-panel")
	public WorkflowTab workflowTab;
	
	@FindBy(css=".general-button-panel_left div:nth-last-child(2) button")
	public Button saveIcon;

	@FindBy(css="mld-experiment-save-panel")
	public SaveTab saveTab;

	@FindBy(css=".general-button-panel_left div:last-child button")
	public Button runIcon;

	@FindBy(css="mld-experiment-run-panel")
	public RunTab runTab;

	public DeviceTab openDeviceTab() {
		selectDeviceIcon.click();
		return selectDeviceTab;
	}
	
	public AcquisitionTab openAcquisitionTab() {
		acquisitionIcon.click();
		return acquisitionTab;
	}
	
	public AcqZoneSelectionTab openAcqZonesTab() {
		acquisitionZonesIcon.click();
		return acquisitionZonesTab;
	}
	
	public AnalysisSettingsTab openAnalysisSettingsTab() {
		analysisSettingsIcon.click();
		return analysisSettingsTab;
	}
	
	public AnZoneSelectionTab openAnZonesTab() {
		analysisZonesIcon.click();
		return analysisZonesTab;
	}
	
	public WellSelectionTab openWellSelectionTab() {
		wellSelectionIcon.click();
		return wellSelectionTab;
	}
	
	public WorkflowTab openWorkflowTab() {
		workflowIcon.click();
		return workflowTab;
	}
	
	public SaveTab openSaveTab() {
		saveIcon.click();
		return saveTab;
	}
	public RunTab openRunTab() {
		runIcon.click();
		return runTab;
	}
	
	public void createProtocol(String device, String labware, List<String> stains, String objective,
			boolean doAnalysis, String analysisName, String name, String description) {
		openDeviceTab().setDevice(device);
		openAcquisitionTab().setLabware(labware)
		                    .setStains(stains)
		                    .setObjective(objective);
		openAnalysisSettingsTab().doAnalysis(doAnalysis).chooseTemplate(analysisName);
		openSaveTab().setProtocolName(name, description).saveProtocol();
	}

	public void runExperiment(float xAcq, float yAcq, float xAn, float yAn, List<String> wells, String experimentName,
			String description) {
		openAcqZonesTab().addMinZone(xAcq, yAcq);
		openAnZonesTab().addMinZone(xAn, yAn);
		openWellSelectionTab().selectWells(wells);
		openRunTab().setRunName(experimentName, description).run();
	}

	public void runFromScratch(String device, String labware, List<String> stains, String objective,
			float xAcq, float yAcq, boolean doAnalysis, String analysisName, float xAn, float yAn, List<String> wells,
			boolean enabeWorkflow, int[] workflowParameters, String experimentName, String description) {
		openDeviceTab().setDevice(device);
		openAcquisitionTab().setLabware(labware)
		                    .setStains(stains)
		                    .setObjective(objective);
		openAnalysisSettingsTab().doAnalysis(doAnalysis);
		if (doAnalysis) {analysisSettingsTab.chooseTemplate(analysisName);}
		if (enabeWorkflow) {openWorkflowTab().enableWorkflow().setParameters(workflowParameters);}
		runExperiment(xAcq, yAcq, xAn, yAn, wells, experimentName, description);
	}

	public boolean isSuccessfulSave() {
		return alert.getText().contains("Protocol has been saved") && alert.isDisplayed();
	}
	
	@Override
	public LandingPage goHome() {
		breadcrumbsMenu.get(breadcrumbsMenu.size() - 1).click();
		confirmation.ok();
		EmpressSite.landingPage.checkOpened();
		return EmpressSite.landingPage;
	}

	@Override
	public void goToDashboard() {
		breadcrumbsMenu.get(breadcrumbsMenu.size() - 2).click();
		confirmation.ok();
		EmpressSite.experimentTemplatesPage.checkOpened();
	}


}

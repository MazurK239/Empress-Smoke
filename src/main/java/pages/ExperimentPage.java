package pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;

import model.MldPopup;
import sections.acquisition.*;
import sites.EmpressSite;

import java.util.List;

import org.openqa.selenium.support.FindBy;

public class ExperimentPage extends InternalPage {
	
	@FindBy(css=".general-button-panel-left div:nth-child(2) button")
	public Button selectDeviceIcon;
	
	@FindBy(css="mld-experiment-device-panel")
	public DeviceTab selectDeviceTab;

	@FindBy(css=".general-button-panel-left div:nth-child(3) button")
	public Button acquisitionIcon;

	@FindBy(css="mld-experiment-acquisition-panel")
	public AcquisitionTab acquisitionTab;

	@FindBy(css=".general-button-panel-left div:nth-child(4) button")
	public Button acquisitionZonesIcon;

	@FindBy(css="mld-experiment-wellarea-acquisition-panel")
	public ZoneSelectionTab acquisitionZonesTab;

	@FindBy(css=".general-button-panel-left div:nth-child(5) button")
	public Button analysisSettingsIcon;

	@FindBy(css="mld-experiment-analysis-panel")
	public AnalysisSettingsTab analysisSettingsTab;

	@FindBy(css=".general-button-panel-left div:nth-child(6) button")
	public Button analysisZonesIcon;

	@FindBy(css="mld-experiment-wellarea-analysis-panel")
	public ZoneSelectionTab analysisZonesTab;

	@FindBy(css=".general-button-panel-left div:nth-child(7) button")
	public Button wellSelectionIcon;

	@FindBy(css="mld-experiment-well-selection-panel")
	public WellSelectionTab wellSelectionTab;

	@FindBy(css=".general-button-panel-left div:nth-child(9) button")
	public Button saveIcon;

	@FindBy(css="mld-experiment-save-panel")
	public SaveTab saveTab;

	@FindBy(css=".general-button-panel-left div:nth-child(10) button")
	public Button runIcon;

	@FindBy(css="mld-experiment-run-panel")
	public RunTab runTab;

	@FindBy(css=".global-message")
	private Text message;

	@FindBy(css="div.modal-dialog")
	private MldPopup confirmation;
	
	public DeviceTab openDeviceTab() {
		selectDeviceIcon.click();
		return selectDeviceTab;
	}
	
	public AcquisitionTab openAcquisitionTab() {
		acquisitionIcon.click();
		return acquisitionTab;
	}
	
	public ZoneSelectionTab openAcqZonesTab() {
		acquisitionZonesIcon.click();
		return acquisitionZonesTab;
	}
	
	public AnalysisSettingsTab openAnalysisSettingsTab() {
		analysisSettingsIcon.click();
		return analysisSettingsTab;
	}
	
	public ZoneSelectionTab openAnZonesTab() {
		analysisZonesIcon.click();
		return analysisZonesTab;
	}
	
	public WellSelectionTab openWellSelectionTab() {
		wellSelectionIcon.click();
		return wellSelectionTab;
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
		openSaveTab().setProtocolName(name, description)
		             .saveProtocol();
	}

	public void runExperiment(float xAcq, float yAcq, float xAn, float yAn, List<String> wells, String experimentName,
			String description) {
		openAcqZonesTab().addMinZone(xAcq, yAcq);
		openAnZonesTab().addMinZone(xAn, yAn);
		openWellSelectionTab().selectWells(wells);
		openRunTab().setExperimentName(experimentName, description)
		            .run();
	}

	public void runFromScratch(String device, String labware, List<String> stains, String objective,
			float xAcq, float yAcq, boolean doAnalysis, String analysisName, float xAn, float yAn, List<String> wells, String experimentName,
			String description) {
		openDeviceTab().setDevice(device);
		openAcquisitionTab().setLabware(labware)
		                    .setStains(stains)
		                    .setObjective(objective);
		openAnalysisSettingsTab().doAnalysis(doAnalysis).chooseTemplate(analysisName);
		runExperiment(xAcq, yAcq, xAn, yAn, wells, experimentName, description);
	}
	
	public boolean isSuccessfulSave() {
		return message.getText().contains("Protocol has been saved") && message.isDisplayed();
	}

	public String getMessage() {
		return message.getText();
	}

	public boolean hasMessage() {
		return message.isDisplayed();
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

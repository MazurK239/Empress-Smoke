package sections.acuisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.AlgInputPanel;
import model.MeasurementsPanel;
import model.MldCheckbox;
import model.MldToggler;
import model.OSD;

public class AnalysisSettingsTab extends Section {

	@FindBy(css=".general-button-panel-right button:first-child.opening-marker")
	private MldToggler chooseTemplateToggler;
	
	@FindBy(xpath="//mld-choose-template-panel//*[@type='checkbox']")
	private MldCheckbox doAnalysisSwitcher;

	@FindBy(css=".mld-select-suggest-list .template-item__title")
	private Elements<Clickable> analysisTemplates;

	@FindBy(css=".selected .template-item__title")
	private Elements<Clickable> selectedTemplate;

	@FindBy(xpath="//button[@title='Measurements']")
	private MldToggler measurementsToggler;
	
	@FindBy(xpath="//li[@heading='Summary Measurements']")
	private MldToggler sumMeasurementsTab;

	@FindBy(css="mld-measurements-selector[measurement-type=summary]")
	private MeasurementsPanel sumMeasurementsPanel;

	@FindBy(xpath="//li[@heading='Cell Measurements']")
	private MldToggler cellMeasurementsTab;
	
	@FindBy(css="mld-measurements-selector[measurement-type=cell]")
	private MeasurementsPanel cellMeasurementsPanel;

	@FindBy(xpath="//label[@title='Single Mode']")
	private MldToggler hdrModeToggler;
	
	@FindBy(xpath="//label[@title='Comparison Mode']")
	private MldToggler compareModeToggler;
	
	@FindBy(css="canvas")
	public OSD canvas;

	@FindBy(css="mld-analysis-parameters")
	public AlgInputPanel algorithmInputPanel;

	@FindBy(css=".acq-labware-controls> button:last-of-type")
	private MldToggler algorithmInputPanelToggler;

	@FindBy(css=".general-panel-button-acq-capture:last-of-type")
	private Clickable runTestAnalysisButton;

	@FindBy(css=".acq-labware-controls .general-panel-button-acq-capture")
	private Clickable captureButton;


	public AnalysisSettingsTab doAnalysis(boolean doAnalysis) {
		chooseTemplateToggler.turnOn();
		if (doAnalysis) {
			doAnalysisSwitcher.checkIt();
		} else {
			doAnalysisSwitcher.uncheckIt();
		}
		return this;		
	}

	public MeasurementsPanel expandSumMeasurements() {
		measurementsToggler.turnOn();
		sumMeasurementsTab.click();
		return sumMeasurementsPanel;
	}

	public MeasurementsPanel expandCellMeasurements() {
		measurementsToggler.turnOn();
		cellMeasurementsTab.click();
		return cellMeasurementsPanel;
	}

	public AnalysisSettingsTab chooseTemplate(String analysisName) {
		chooseTemplateToggler.turnOn();
		analysisTemplates.get(analysisName).click();
		return this;
	}

	public String getSelectedAnalysisName() {
		chooseTemplateToggler.turnOn();
		return selectedTemplate.get(0).getWebElement().getText();
	}

	public AlgInputPanel openAlgorithmInputPanel() {
		algorithmInputPanelToggler.turnOn();
		return algorithmInputPanel;
	}

	public AnalysisSettingsTab singleMode() {
		hdrModeToggler.turnOn();
		return this;
	}

	public AnalysisSettingsTab comparisonMode() {
		compareModeToggler.turnOn();
		return this;		
	}
	
	public void capture() {
		captureButton.click();
	}

	public void runTestAnalysis() {
		runTestAnalysisButton.click();
	}
}

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

	@FindBy(xpath="//button[@title='Summary Measurements']")
	private MldToggler sumMeasurementsToggler;
	
	@FindBy(css="mld-summary-measurements-panel")
	private MeasurementsPanel sumMeasurementsPanel;

	@FindBy(xpath="//button[@title='Cell Measurements']")
	private MldToggler cellMeasurementsToggler;

	@FindBy(xpath="//label[@title='Single Mode']")
	private MldToggler hdrModeToggler;
	
	@FindBy(xpath="//label[@title='Comparison Mode']")
	private MldToggler compareModeToggler;

	@FindBy(css="mld-cell-measurements-panel")
	private MeasurementsPanel cellMeasurementsPanel;
	
	@FindBy(css="canvas")
	public OSD canvas;

	@FindBy(css="mld-analysis-parameters")
	public AlgInputPanel algorithmInputPanel;

	@FindBy(xpath="//li[@title='Algorithm input']")
	private Clickable algorithmInputPanelIcon;

	@FindBy(css=".bottom-set button:first-child")
	private Clickable runTestAnalysisButton;

	@FindBy(css=".bottom-set button:nth-child(2)")
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
		sumMeasurementsToggler.turnOn();
		return sumMeasurementsPanel;
	}

	public MeasurementsPanel expandCellMeasurements() {
		cellMeasurementsToggler.turnOn();
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
		algorithmInputPanelIcon.click();
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

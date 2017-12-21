package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.complex.Tabs;
import com.epam.web.matcher.testng.Assert;

import enums.ExperimentEditTabs;
import model.ContextMenu;
import sections.experiment.AcquisitionsSection;
import sections.experiment.AnalysesSection;

public class NewEditEntityPage extends InternalPage {

	@FindBy(css=".entity-info-item:first-of-type div span")
	private Text experimentName;
	
	@FindBy(css="button.btn-parameters")
	private Clickable parametersIcon;

	@FindBy(css=".angular-bootstrap-contextmenu")
	private ContextMenu contextMenu;

	@FindBy(css=".experiment-edit-tabs > ul > li.nav-item")
	public Tabs<ExperimentEditTabs> tabs;

	@FindBy(css="mld-experiment-edit-analyses")
	public AnalysesSection analysesSection;

	@FindBy(css="mld-experiment-edit-acquisitions")
	public AcquisitionsSection acquisitionsSection;

	@Override
	public void checkOpened() {
		super.checkOpened();
		Assert.isTrue(() -> experimentName.isDisplayed());
	}
	
	public String getExperimentName() {
		return experimentName.getWebElement().getText();
	}
	
	public void openExperimentPropertiesPanel() {		
		parametersIcon.click();
	}

	public void deleteExperiment() {
		openExperimentPropertiesPanel();
		contextMenu.options.get(0).click();
		confirmation.ok();
	}

	public void duplicateAnalysis(String analysisName) {
		analysesSection.findAnalysis(analysisName).openMenu();
		contextMenu.options.get(0).click();
	}

	public void duplicateProtocol(String protocolName) {
		acquisitionsSection.findProtocol(protocolName).openMenu();
		contextMenu.options.get(0).click();
	}

}

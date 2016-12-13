package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;

import static com.epam.commons.LinqUtils.first;

import enums.ExpDashboardTabs;
import model.MldPopup;
import model.ProtocolTile;

public class ExperimentTemplatesPage extends InternalPage {

	@FindBy(xpath="//ul/li[contains(@class, 'general-controls-item')]")
	private Elements<Button> tabs;
	
	@FindBy(xpath="//*[@class='tab-content']/div[3]//mld-experiment-template")
	private Elements<ProtocolTile> protocols;

	@FindBy(xpath="//*[@class='tab-content']/div[2]//mld-experiment-template")
	private Elements<ProtocolTile> templates;
	
	@FindBy(css=".modal-dialog")
	private MldPopup confirmation;

	@FindBy(css=".tab-pane.active mld-experiment-template:first-child")
	private ProtocolTile simpleTile;

	public ProtocolTile findTemplate(String name) {
		return first(templates, el -> el.getTileName().equals(name));
	}
	
	public ProtocolTile findProtocol(String name) {
		return first(protocols, el -> el.getTileName().equals(name));
	}
	
	public boolean absenceOfProtocol(String name) {
		for (ProtocolTile tile : protocols) {
			if (tile.getTileName().equals(name)) {
				return false;
			}
		}
		return true;
	}

	public void confirm() {
		confirmation.ok();
		Timer.waitCondition(() -> simpleTile.isDisplayed());
	}

	public ExperimentTemplatesPage selectTab(ExpDashboardTabs tab) {
		tabs.get(tab).click();
		return this;
	}
}

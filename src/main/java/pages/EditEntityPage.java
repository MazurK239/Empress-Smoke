package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;

import model.MldPopup;
import model.MldToggler;
import model.SharingPanel;

public class EditEntityPage extends InternalPage {

	@FindBy(xpath="//button[@title='Delete Experiment']")
	private Button deleteExperimentButton;
	
	@FindBy(xpath="//button[@title='Experiment Properties']")
	private Button experimentProperties;	
	
	@FindBy(css=".modal-dialog")
	private MldPopup confirmation;

	@FindBy(xpath="//button[@title='Share']")
	private MldToggler sharingToggler;

	@FindBy(css="mld-user-acceptance-panel")
	public SharingPanel sharingPanel;

	public EditEntityPage deleteExperiment() {
		experimentProperties.click();
		deleteExperimentButton.click();
		return this;
	}

	public void confirm() {
		confirmation.ok();
	}

	public SharingPanel expandSharingPanel() {
		experimentProperties.click();
		sharingToggler.turnOn();
		Timer.waitCondition(() -> this.getDriver().findElement(By.cssSelector(".pinned-panel__header .pull-right")).isDisplayed());
		return sharingPanel;
	}

}

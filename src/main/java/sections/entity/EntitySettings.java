package sections.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.MldToggler;
import model.SharingPanel;

public class EntitySettings extends Section {

	@FindBy(xpath="//button[@ng-click='vm.actions.deleteExperiment()']")
	private Button deleteExperimentButton;

	@FindBy(css="mld-user-acceptance-panel")
	public SharingPanel sharingPanel;

	@FindBy(css=".general-button-panel-right > button")
	private MldToggler sharingToggler;
	
	public void deleteExperiment() {
		deleteExperimentButton.click();
	}
	
	public SharingPanel expandSharingPanel() {
		sharingToggler.turnOn();
		Timer.waitCondition(() -> this.getDriver().findElement(By.cssSelector(".pinned-panel__header .pull-right")).isDisplayed());
		return sharingPanel;
	}

}

package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Popup;

public class MldPopup extends Popup {

	@FindBy(xpath="//button[@ng-click='ok()']")
	public Button okButton;
	
	@FindBy(xpath="//button[@ng-click='cancel()']")
	public Button cancelButton;

	@FindBy(xpath="//*[contains(@class,'inner-content')]")
	public Text content;
	
}

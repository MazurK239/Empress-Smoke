package sections.acuisition;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class CanvasTab extends Section {
	
	protected Actions action = new Actions(this.getDriver());

	@FindBy(xpath="//canvas[contains(@class, 'plate-selector')]")
	protected Clickable canvas;
	
	@FindBy(css=".general-button-panel-right > button:last-child")
	protected Clickable clearAllButton;
	
	public void clearAll() {
		clearAllButton.click();
	}
	
}

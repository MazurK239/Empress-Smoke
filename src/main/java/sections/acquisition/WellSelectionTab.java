package sections.acquisition;

import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class WellSelectionTab extends Section {
	
	private Actions action = new Actions(this.getDriver()); 
	
	@FindBy(xpath="//canvas[contains(@class, 'plate-selector')]")
	protected Clickable canvas;
	
	@FindBy(css=".general-button-panel_right > button:first-of-type")
	private Button selectAllButton;

	@FindBy(css=".general-button-panel_right > button:last-of-type")
	private Button clearAllButton;

	public WellSelectionTab selectWells(List<String> wells) {
		for (String well : wells) {
			char letter = well.substring(0, 1).charAt(0);
			int yOffset = 50 + 40 * ((int)letter - (int)("A".charAt(0)));
			int xOffset = 50 + 40 * (Integer.parseInt(well.substring(1)) - 1);
			action.moveToElement(canvas.getWebElement(), xOffset, yOffset).click().build().perform();	
		}
		return this;
	}

	public void selectAll() {
		selectAllButton.click();		
	}

	public void clearAll() {
		clearAllButton.click();
	}
	
}

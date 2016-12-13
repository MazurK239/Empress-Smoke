package sections.acuisition;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

public class WellSelectionTab extends CanvasTab {

	@FindBy(xpath="//button[@title='Select all']")
	private Button selectAllButton;

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
}

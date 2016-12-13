package sections.preferences;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class MiscTab extends Section {
	
	private Actions action = new Actions(this.getDriver());

	@FindBy(xpath="./*/*/*/*/*[@class='noUi-base']")
	private Clickable timeoutSlider;
	
	public void setSessionTimeout(int minutes) {
		int sliderSectionLength = timeoutSlider.getWebElement().getSize().getWidth()/9;
		int xOffset;
		switch (minutes) {
		case 5:
			xOffset = 1;
			break;
		case 10:
			xOffset = sliderSectionLength;
			break;
		case 15:
			xOffset = 2 * sliderSectionLength;
			break;
		case 20:
			xOffset = 3 * sliderSectionLength;
			break;
		case 30:
			xOffset = 4 * sliderSectionLength;
			break;
		case 60:
			xOffset = 5 * sliderSectionLength;
			break;
		case 180:
			xOffset = 6 * sliderSectionLength;
			break;
		case 360:
			xOffset = 7 * sliderSectionLength;
			break;
		case 720:
			xOffset = 8 * sliderSectionLength;
			break;
		case 1440:
			xOffset = 9 * sliderSectionLength;
			break;
		default:
			System.out.println("Nothing was selected, incorrect timeout value!");
			return;
		}
		action.moveToElement(timeoutSlider.getWebElement(), xOffset, 5).click().build().perform();
	}
}

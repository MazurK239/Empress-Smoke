package sections.acquisition;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;

public class ZoneSelectionTab extends CanvasTab {

	int CANVAS_SIZE;// = 749;
	final int MIN_ZONE_SIZE = 40;
	final int MARGIN = 25;	

	@FindBy(css=".general-button-panel-right > button:first-child")
	protected Clickable addZoneButton;
	
	public ZoneSelectionTab addMinZone(float xPosition, float yPosition) {
		CANVAS_SIZE = canvas.getWebElement().getSize().getHeight();
		clearAllButton.click();
		addZoneButton.click();
		action.moveToElement(canvas.getWebElement(), CANVAS_SIZE/2, CANVAS_SIZE/2).clickAndHold()
		      .moveToElement(canvas.getWebElement(), Math.round(xPosition * CANVAS_SIZE), Math.round(yPosition * CANVAS_SIZE)).release()
		      .build().perform();
		return this;
	}

	public ZoneSelectionTab addMaxZone() {
		CANVAS_SIZE = canvas.getWebElement().getSize().getHeight();
		System.out.println(CANVAS_SIZE);
		clearAllButton.click();
		addZoneButton.click();
		action.moveToElement(canvas.getWebElement(), CANVAS_SIZE/2, CANVAS_SIZE/2).clickAndHold()
		      .moveToElement(canvas.getWebElement(), MARGIN + MIN_ZONE_SIZE/2, MARGIN + MIN_ZONE_SIZE/2).release()
		      .moveToElement(canvas.getWebElement(), MARGIN + MIN_ZONE_SIZE, MARGIN + MIN_ZONE_SIZE).clickAndHold()
		      .moveToElement(canvas.getWebElement(), CANVAS_SIZE, CANVAS_SIZE).release()
		      .build().perform();
		return this;
	}

}

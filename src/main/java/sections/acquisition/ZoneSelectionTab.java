package sections.acquisition;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.MldToggler;

public class ZoneSelectionTab extends Section {

	int CANVAS_SIZE;// = 749;
	final int MIN_ZONE_SIZE = 40;
	final int MARGIN = 25;	
	
	private Actions action = new Actions(this.getDriver()); 
	
	@FindBy(css=".general-button-panel_right > button:first-of-type")
	public MldToggler autoToggler;
	
	@FindBy(xpath="//canvas[contains(@class, 'plate-selector')]")
	protected Clickable canvas;
	
	
	public ZoneSelectionTab moveToPosition(float xPosition, float yPosition) {
		CANVAS_SIZE = canvas.getWebElement().getSize().getHeight();
		action.moveToElement(canvas.getWebElement(), CANVAS_SIZE/2, CANVAS_SIZE/2).clickAndHold()
		      .moveToElement(canvas.getWebElement(), Math.round(xPosition * CANVAS_SIZE), Math.round(yPosition * CANVAS_SIZE)).release()
		      .build().perform();
		return this;
	}

	public ZoneSelectionTab resizeToMaxZone() {
		CANVAS_SIZE = canvas.getWebElement().getSize().getHeight();
		System.out.println(CANVAS_SIZE);
		action.moveToElement(canvas.getWebElement(), CANVAS_SIZE/2, CANVAS_SIZE/2).clickAndHold()
		      .moveToElement(canvas.getWebElement(), MARGIN + MIN_ZONE_SIZE/2, MARGIN + MIN_ZONE_SIZE/2).release()
		      .moveToElement(canvas.getWebElement(), MARGIN + MIN_ZONE_SIZE, MARGIN + MIN_ZONE_SIZE).clickAndHold()
		      .moveToElement(canvas.getWebElement(), CANVAS_SIZE, CANVAS_SIZE).release()
		      .build().perform();
		return this;
	}
	

}

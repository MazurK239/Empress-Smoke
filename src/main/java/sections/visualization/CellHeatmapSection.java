package sections.visualization;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;


public class CellHeatmapSection extends CellLevelSection {

	private Actions action = new Actions(this.getDriver());
	
	@FindBy(css=".table-canvas-wrapper__canvas")
	protected Clickable canvas;
	
	public boolean isMainControlDisplayed() {
		return canvas.isDisplayed();
	}

	public void selectAreaOnHeatmap(String square1, String square2, int[] heatmapGeometry) {
		selectionModeToggler.turnOn();
		int squareSize = (canvas.getWebElement().getSize().getWidth() - 130) / heatmapGeometry[0];
		int xInit = 100 - squareSize/2 + squareSize * Integer.parseInt(square1.substring(0, 1));
		int yInit = 10 - squareSize/2 + squareSize * Integer.parseInt(square1.substring(1));
		int xEnd = 100 - squareSize/2 + squareSize * Integer.parseInt(square2.substring(0, 1));
		int yEnd = 10 - squareSize/2 + squareSize * Integer.parseInt(square2.substring(1));
		action.moveToElement(canvas.getWebElement(), xInit, yInit).clickAndHold().moveToElement(canvas.getWebElement(), xEnd, yEnd).release().build().perform();
	}

}

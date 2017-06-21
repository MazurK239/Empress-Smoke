package sections.visualization;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class CellHeatmapSection extends CellLevelSection {

	private Actions action = new Actions(this.getDriver());
	

	public void selectAreaOnHeatmap(float xInit, float xEnd, float yInit, float yEnd) {
		int canvasWidth = Integer.parseInt(canvas.getWebElement().getAttribute("width"));
		int canvasHeight = Integer.parseInt(canvas.getWebElement().getAttribute("height"));
		action.moveToElement(canvas.getWebElement(), (int)xInit*canvasWidth, (int)yInit*canvasHeight).clickAndHold()
		      .moveToElement(canvas.getWebElement(), (int)xEnd*canvasWidth, (int)yEnd*canvasHeight).release().build().perform();
	}

}

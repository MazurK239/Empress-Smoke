package sections.visualization;

import static sites.EmpressSite.screen;

import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;

public class PlateSection extends WellLevelSection {

	private Actions action = new Actions(this.getDriver());
		
	public PlateSection selectWells(List<String> wells, int[] labwareGeometry) {
		selectionModeToggler.turnOn();
		int wellSize = (canvas.getWebElement().getSize().getWidth() - 41) / labwareGeometry[0];
		for (String well : wells) {
			char letter = well.substring(0, 1).charAt(0);
			int yOffset = 41 + wellSize  * ((int)letter - (int)("A".charAt(0)));
			int xOffset = 41 + wellSize * (Integer.parseInt(well.substring(1)) - 1);
			action.moveToElement(canvas.getWebElement(), xOffset, yOffset).click().build().perform();	
		}
		return this;
	}
	
	@Override
	public boolean isMainControlDisplayed() {
		try {
			System.out.println("waiting");
			screen.wait("/skl_img/heatmap_ready_light.png");
			System.out.println("Hooray!");
		} catch (FindFailed e) {
			try {
				screen.wait("/skl_img/heatmap_ready_dark.png");
			} catch (FindFailed e1) {
				return false;
			}
		}
		return super.isMainControlDisplayed();
	}
	
}

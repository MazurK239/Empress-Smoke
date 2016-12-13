package sections.visualization;


import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;

public class CellDeepZoomSection extends CellLevelSection {

	@FindBy(css=".displayregioncontainer")
	private Clickable minimap;

	public boolean isMinimapDisplayed() {
		return minimap.isDisplayed();
	}

}

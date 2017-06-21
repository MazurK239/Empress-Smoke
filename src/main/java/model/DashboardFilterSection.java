package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class DashboardFilterSection extends Section {

	@FindBy(xpath="./../../../../../mld-pinned-panel/div[contains(@class,'pinned-panel')]")
	private FilterRollingPanel filterPanel;
	
	@FindBy(css=".dashboard-vis__sidebar-toggler")
	private Button filterPanelToggler;
	
	@FindBy(css=".multi-trigger:nth-of-type(2)")
	private FilterSwitcher recentFavFilter;

	public FilterRollingPanel expandFilterPanel() {
		if (!filterPanel.resetButton.isDisplayed()) {			
			filterPanelToggler.click();
		}
		return filterPanel;
	}

	public void recent() {
		recentFavFilter.choose(1);
	}
	
	

}

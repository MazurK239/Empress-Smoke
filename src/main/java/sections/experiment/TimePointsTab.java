package sections.experiment;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.TimePointCard;

public class TimePointsTab extends Section {

	@FindBy(css="div.t-grid__item")
	public Elements<TimePointCard> tGrid;
	
	@FindBy(css="div.t-grid__corner > div > span")
	public Clickable selectAllDot;

	public void selectAll() {
		selectAllDot.click();
		if (!tGrid.get(0).getAttribute("class").contains("selected")) {			
			selectAllDot.click();
		}
	}

	public void clearAll() {
		selectAllDot.click();
		if (tGrid.get(0).getAttribute("class").contains("selected")) {			
			selectAllDot.click();
		}
	} 

	public void selectTimePoints(int[] indices) {
		clearAll();
		for (int idx : indices) {
			for (TimePointCard timePoint : tGrid) {
				if (timePoint.getIndex() == idx) {
					timePoint.click();
					if (!timePoint.getAttribute("class").contains("selected")) {
						timePoint.click();					
					}
					break;
				}
			}
		}
	}

	
}

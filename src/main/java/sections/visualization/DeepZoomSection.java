package sections.visualization;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import static com.epam.commons.LinqUtils.first;

public class DeepZoomSection extends Section {

	@FindBy(css=".analysis-zone")
	private Elements<Element> zones;
	
	@FindBy(css=".thumbs>li")
	private Elements<Clickable> thumbs; 

	public boolean areZonesDisplayed(int number) {
		if (zones.size() != number) {return false;}
		for (Element zone : zones) {
			if(!zone.isDisplayed()) {return false;}
		}
		return true;
	}

	public boolean checkWells(List<String> wells) {
		for (String wellName : wells) {
			if (first(thumbs, el -> el.getWebElement().getText().equals(wellName)) == null) {
				return false;
			}
		}
		return true;
	}

}

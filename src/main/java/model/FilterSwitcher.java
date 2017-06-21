package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class FilterSwitcher extends Section {

	@FindBy(css="label")
	private Elements<Clickable> buttons;

	public void choose(int i) {
		buttons.get(i).click();
	}

}

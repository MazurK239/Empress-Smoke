package model;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class ZSSliderPanel extends Section {

	@FindBy(css="select")
	public Dropdown units = new Dropdown(By.xpath("."), By.cssSelector("option"));

	@FindBy(css="input:first-of-type")
	private TextField zoneSizeInput;

	@FindBy(css=".noUi-value:last-of-type")
	private Text maxValue;

	public ZSSliderPanel setUnits(String unit) {
		switch (unit) {
		case "mm":
			units.select(1);
			break;
		case "mcm":
			units.select(2);
			break;

		default:
			System.out.println("=== MESSAGE === There are no such units! Choose either mm or mcm");
			break;
		}
		return this;
	}

	public void resizeAutoZone(int size) {
		zoneSizeInput.newInput(String.valueOf(size));
	}

	public String getMaxSliderValue() {
		return maxValue.getText();
	}

	public String getInputValue() {
		return zoneSizeInput.getText();
	}
}

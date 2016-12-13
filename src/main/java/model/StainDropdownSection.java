package model;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class StainDropdownSection extends Section {

	@FindBy(xpath="./select")
	private Dropdown dropdown = new Dropdown(By.xpath("."), By.cssSelector("option"));
	
	@FindBy(xpath="./select/optgroup[@label='Not Available']/option")
	private Elements<Text> unavailableStains;
	
	public void expand() {
		dropdown.expand();
	}
	
	public void selectStain(String stain) {
		if ("Off".equals(stain)) {
			dropdown.select("Off");
		} else {
			dropdown.select(stain);
		}
	}

	public List<String> getUnavailableStains() {
		List<String> stains = new ArrayList<String>();
		for (Text uStain : unavailableStains) {
			stains.add(uStain.getText());
		}
		return stains;
	}
	
	
}

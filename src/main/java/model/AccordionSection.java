package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class AccordionSection extends Section {
	
	@FindBy(css=".accordion-panel__trigger")
	public Button panelToggler;

	@FindBy(css=".accordion-panel__body > button")
	public Elements<Button> menu;

	public void expand() {
		if (panelToggler.getWebElement().getAttribute("class").contains("collapsed")) {
			panelToggler.click();
		}
	}

	public void collapse() {
		if (!panelToggler.getWebElement().getAttribute("class").contains("collapsed")) {
			panelToggler.click();
		}
	}

}

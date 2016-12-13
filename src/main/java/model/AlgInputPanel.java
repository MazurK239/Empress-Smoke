package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.complex.Tabs;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class AlgInputPanel extends Section {

	@FindBy(css="ul>li")
	private Tabs tabSwitcher;

	@FindBy(css=".tab-content  > .tab-pane.active")
	private AlgInputTab tabContent;
	
	public AlgInputPanel selectTab(String tab) {
		tabSwitcher.select(tab);
		return this;
	}

	public void setProperty(String field, Integer value) {
		switch (field) {
		case "Intensity":			
			tabContent.intensity.newInput(value.toString());
			break;
		case "Max Width":			
			tabContent.maxWidth.newInput(value.toString());
			break;
		case "Min Width":			
			tabContent.minWidth.newInput(value.toString());
			break;

		default:
			break;
		}
	}

}

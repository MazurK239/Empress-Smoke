package model;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Tabs;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class AlgInputPanel extends Section {

	@FindBy(css="ul>li")
	private Tabs tabSwitcher;

	@FindBy(css=".tab-content  > .tab-pane.active")
	private AlgInputTab tabContent;

	@FindBy(css="select")
	private Dropdown channelDropdown = new Dropdown(By.xpath("."), By.cssSelector("option"));
	
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

	public AlgInputPanel setTargetChannel(String channel) {
		channelDropdown.select(channel);
		return this;
	}

}

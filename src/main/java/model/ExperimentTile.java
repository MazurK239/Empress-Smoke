package model;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class ExperimentTile extends Section {

	@FindBy(css=".dashboard-item-vis__button_action")
	private Button viewButton;
	
	@FindBy(css=".dashboard-item-vis__link-part")
	private Clickable clickablePart;
	
	@FindBy(css=".dashboard-item-vis__name")
	private Text header;

	@FindBy(css="select")
	private Dropdown dropdown = new Dropdown<>(By.xpath("."), By.cssSelector("option"));
	
	@FindBy(css=".dashboard-item-vis-icon_private")
	private Element sharingIcon;

	@FindBy(css=".dashboard-item-vis-icons>div")
	private Elements<Element> icons;
	
	public String getTileName() {
		return header.getText();
	}
	
	public void open() {
		clickablePart.click();
	}

	public ExperimentTile choose(String analysis) {
		dropdown.select(analysis);
		return this;
	}

	public void view() {
		viewButton.click();
	}
	
	public boolean isSharedIconPresent() {
		return icons.get(icons.size() - 1).getAttribute("class").contains("dashboard-item-vis-icon_private") ?  true : false;
	}
	
}

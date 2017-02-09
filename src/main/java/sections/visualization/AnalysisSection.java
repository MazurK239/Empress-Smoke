package sections.visualization;


import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.MldToggler;

public class AnalysisSection extends Section {
	
	@FindBy(xpath="//button[@title='Selection Mode']")
	protected MldToggler selectionModeToggler;

	@FindBy(xpath="//button[@title='Selection Mode']")
	protected Button deselectAllButton;
		
	@FindBy(css="canvas:first-child")
	protected Clickable canvas;

	public boolean isMainControlDisplayed() {
		return canvas.isDisplayed();
	}

	
}

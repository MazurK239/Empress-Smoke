package sections.entity;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.EntityNavigation;

public class EntityAnalyses extends Section {

	@FindBy(css=".link-group")
	public EntityNavigation navigation;

}

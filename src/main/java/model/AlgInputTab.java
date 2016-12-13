package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class AlgInputTab extends Section {

	@FindBy(xpath="//mld-analysis-parameters-item[1]//input")
	public TextField intensity;
	
	@FindBy(xpath="//mld-analysis-parameters-item[2]//input")
	public TextField maxWidth;

	@FindBy(xpath="//mld-analysis-parameters-item[3]//input")
	public TextField minWidth;
	
}

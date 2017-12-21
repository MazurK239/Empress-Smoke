package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;

public class TimePointCard extends Clickable {

	@FindBy(css=".t-grid-card__name")
	public Text name;

	@FindBy(css=".t-grid-card__date")
	public Text dateCreated;
	
	@FindBy(css=".t-grid-card__icon-analysis")
	public Element hasAnalysisIcon;
	
	@FindBy(css=".t-grid-card__icon-images")
	public Element hasImagesIcon;
	
	@FindBy(css=".t-grid-card__wells-data")
	public Element number;

	public int getIndex() {
		if (name.getText().substring(0,1).equals("A")) {
			return Integer.parseInt(name.getText().substring(2));
		} else {			
			return Integer.parseInt(name.getText().substring(1));
		}
	}
	
}

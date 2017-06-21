package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import enums.AnalysisViews;

public class EntityNavigation extends Section {

	@FindBy(css="li:first-of-type")
	private Clickable thumbIcon;
	
	@FindBy(css="li:nth-of-type(2)")
	private Clickable dataIcon;

	@FindBy(css="li:nth-of-type(3)")
	private Clickable heatmapIcon;

	@FindBy(css="li:nth-of-type(4)")
	private Clickable scatterIcon;

	@FindBy(css="li:nth-of-type(5)")
	private Clickable stackedIcon;

	@FindBy(css="li:nth-of-type(6)")
	private Clickable tableIcon;

	@FindBy(css="li:nth-of-type(7)")
	private Clickable imagesIcon;

	public void open(AnalysisViews view) {
		switch (view) {
		case THUMBS:
			thumbIcon.click();
			break;
		case DATA:
			dataIcon.click();
			break;
		case HEATMAP:
			heatmapIcon.click();
			break;
		case SCATTER:
			scatterIcon.click();
			break;
		case STACKED:
			stackedIcon.click();
			break;
		case TABLE:
			tableIcon.click();
			break;
		case IMAGES:
			imagesIcon.click();
			break;
		default:
			break;
		}
		
	}
	
	

}

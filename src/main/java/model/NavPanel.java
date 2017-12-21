package model;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import enums.AnalysisViews;

public class NavPanel extends Section {
		
	@FindBy(css="mld-accordion-panel:nth-of-type(1)")	
	public AccordionSection plateSection;

	@FindBy(css="mld-accordion-panel:nth-of-type(2)")	
	public AccordionSection summarySection;
	
	@FindBy(css="mld-accordion-panel:nth-of-type(3)")	
	public AccordionSection cellSection;

	public void open(AnalysisViews view) {
		plateSection.expand();
		summarySection.expand();
		cellSection.expand();
		switch (view) {
		case T_VIEW:
			plateSection.menu.get(0).click();
			break;
		case T_GRAPH:
			plateSection.menu.get(1).click();
			break;			
		case THUMBS:
			plateSection.menu.get(2).click();
			break;
		case DATA:
			plateSection.menu.get(3).click();
			break;
		case HEATMAP:
			plateSection.menu.get(4).click();
			break;
		case IMAGES:
			plateSection.menu.get(plateSection.menu.size() - 1).click();
			break;
		case SCATTER:
			summarySection.menu.get(0).click();
			break;
		case STACKED:
			summarySection.menu.get(1).click();
			break;
		case TABLE:
			summarySection.menu.get(summarySection.menu.size()-1).click();
			break;
		case CELL_HEATMAP:
			cellSection.menu.get(0).click();
			break;
		case CELL_STACKED:
			cellSection.menu.get(1).click();
			break;
		case CELL_SCATTER:
			cellSection.menu.get(2).click();
			break;
		case CELL_TABLE:
			cellSection.menu.get(3).click();
			break;
		case CELL_IMAGES:
			cellSection.menu.get(4).click();
			break;
		default:
			break;
		}
		
		
	}
	
}

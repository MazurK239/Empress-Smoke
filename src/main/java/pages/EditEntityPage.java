package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

import model.MldPopup;
import sections.entity.EntityAcquisition;
import sections.entity.EntityAnalyses;
import sections.entity.EntityAnnotation;
import sections.entity.EntitySettings;

public class EditEntityPage extends InternalPage {
	
	@FindBy(css="mld-entity-buttons-panel > button:first-of-type")
	private Button analysesIcon;	

	@FindBy(css="mld-entity-buttons-panel > button:nth-of-type(2)")
	private Button acquisitionPropertiesIcon;	
	
	@FindBy(css="mld-entity-buttons-panel > button:nth-of-type(3)")
	private Button annotationsIcon;	
	
	@FindBy(css="mld-entity-buttons-panel > button:last-of-type")
	private Button experimentPropertiesIcon;	
		
	
	@FindBy(css=".modal-dialog")
	private MldPopup confirmation;
	
	@FindBy(css="mld-entity-analysis-panel")
	public EntityAnalyses analyses;

	@FindBy(css="mld-entity-acquisition-panel")
	public EntityAcquisition acquisitionProperties;
	
	@FindBy(css="mld-entity-annotation-panel")
	public EntityAnnotation annotations;
	
	@FindBy(css="mld-entity-settings-panel")
	public EntitySettings experimentProperties;

	public EntityAnalyses openAnalyses() {		
		analysesIcon.click();
		return analyses;
	}

	public EntityAcquisition openAcquisitionProperties() {		
		acquisitionPropertiesIcon.click();
		return acquisitionProperties;
	}
	public EntityAnnotation openAnnotations() {		
		annotationsIcon.click();
		return annotations;
	}
	public EntitySettings openExperimentProperties() {		
		experimentPropertiesIcon.click();
		return experimentProperties;
	}

	public void confirm() {
		confirmation.ok();
	}


}

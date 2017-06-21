package model;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class MeasurementsPanel extends Section {

	@FindBy(css=".measurements-buttons > button:first-of-type")
	private Button selDeselAll;
	
	@FindBy(xpath="//input[@type='checkbox']")
	private Elements<MldCheckbox> measurements;
	
	@FindBy(css=".measurements-container .measurement-value")
	public Elements<Text> values;

	public void deselectAll() {
		selDeselAll.click();
		if (measurements.get(1).isChecked()) {
			selDeselAll.click();			
		}
		if (measurements.get(measurements.size() - 1).isChecked()) {
			measurements.get(measurements.size() - 1).uncheckIt();
		}
	}

	public void selectAll() {
		selDeselAll.click();
		if (!measurements.get(1).isChecked()) {
			selDeselAll.click();			
		}
		if (!measurements.get(measurements.size() - 1).isChecked()) {
			measurements.get(measurements.size() - 1).checkIt();
		}
	}

	public void selectSegmentation() {
		if (measurements.get(measurements.size() - 1).get(By.xpath("./../../../../div[1]")).getText().equals("Cell Segmentation")) {
			measurements.get(measurements.size() - 1).checkIt();
		}
	}

	public void deselectSegmentation() {
		if (measurements.get(measurements.size() - 1).get(By.xpath("./../../../../div[1]")).getText().equals("Cell Segmentation")) {
			measurements.get(measurements.size() - 1).uncheckIt();
		}
	}


}

package model;

import org.openqa.selenium.By;

import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;

public class MldCheckbox extends CheckBox {

	public boolean whetherChecked() {
		this.avatar.localElementSearchCriteria = el -> el != null;
		return this.isChecked();
	}
	
	public void checkIt() {
		this.avatar.localElementSearchCriteria = el -> el != null;
		if (!this.isChecked()) {
			this.get(By.xpath("./../*[2]")).click();
		}
	}
	
	public void uncheckIt() {
		this.avatar.localElementSearchCriteria = el -> el != null;
		if (this.isChecked()) {
			this.get(By.xpath("./../*[2]")).click();
		}
	}
}

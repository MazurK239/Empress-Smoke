package model;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

public class MldToggler extends Button {


	public void turnOn() {
		boolean active = this.getAttribute("class").contains("active");
		if (!active ) {this.click();}
	}
	
	public void turnOff() {
		boolean active = this.getAttribute("class").contains("active");
		if (active ) {this.click();}		
	}
	
}

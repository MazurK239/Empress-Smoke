package model;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

public class MldLock extends Button {
	
	public String getState() {
		return this.getAttribute("title");
	}

	public void lock() {
		boolean locked = this.getAttribute("title").equals("Locked");
		if (!locked ) {this.click();}
	}
	
	public void unlock() {
		boolean locked = this.getAttribute("title").equals("Locked");
		if (locked ) {this.click();}		
	}
	
}

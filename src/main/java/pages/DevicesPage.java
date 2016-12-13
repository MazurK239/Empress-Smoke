package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;

public class DevicesPage extends InternalPage {

	@FindBy(xpath="//button[@title='Services']")
	private Clickable servicesTab;
	
	@FindBy(xpath="//mld-select-service//li//span[contains(@class, 'item-name')]")
	private Elements<Clickable> services;

	public void setAnService(String serviceName) {
		servicesTab.click();
		
		if (serviceName.equals("first")) {
			services.get(0).click();
		}
		
		try {
		    services.get(serviceName).click();
		} catch (NullPointerException e) {
			
		}
	}

}

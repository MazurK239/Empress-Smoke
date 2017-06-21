package sections.acquisition;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class DeviceTab extends Section {

	@FindBy(xpath="//ul/li/span[contains(@class, 'item-name')]")
	private Elements<Clickable> devices;

	@FindBy(css="li.selected-item > .item-name")
	public Element selectedDevice;
	
	public String getSelectedDeviceName() {
		return selectedDevice.getWebElement().getText();
	}
	
	public DeviceTab setDevice(String device) {
		for (Clickable item : devices) {
			if (item.getWebElement().getText().contains(device)) {
				item.click();
			}
		}
		return this;
	}

	public Boolean isDeviceOnline(String deviceName) {
		return devices.get(deviceName).get(By.xpath("./../span[2]/*[contains(@class, 'fill-sucess')]")).isDisplayed();
	}

	public Boolean isDeviceOffline(String deviceName) {
		return devices.get(deviceName).get(By.xpath("./../span[2]/*[contains(@class, 'fill-error')]")).isDisplayed();
	}
	
}

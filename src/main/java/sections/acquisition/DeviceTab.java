package sections.acquisition;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class DeviceTab extends Section {

	@FindBy(css="li.list-item .device-header-title")
	private Elements<Clickable> devices;

	@FindBy(css="li.selected-item .device-header-title")
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
		return devices.get(deviceName).get(By.xpath("./../div//*[text()='Online']")).isDisplayed();
	}

	public Boolean isDeviceOffline(String deviceName) {
		return devices.get(deviceName).get(By.xpath("./../div//*[text()='Offline']")).isDisplayed();
	}
	
}

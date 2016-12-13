package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;

import sites.EmpressSite;

public class LandingPage extends InternalPage {

	@FindBy(xpath="//span[contains(text(),'Acquire')]/..")
	private Button dataAcquisition;
	
	@FindBy(css="a.home-page-controls-item:nth-child(2)")
	private Button dataVisualization;

	@FindBy(xpath="//span[contains(text(),'Devices')]/..")
	private Button devices;
	
	public ExperimentTemplatesPage openDataAcquisitionPage () {
		dataAcquisition.click();
		EmpressSite.experimentTemplatesPage.checkOpened();
		return EmpressSite.experimentTemplatesPage;
	}
	
	public DashboardPage openDataVisualizationPage () {
		dataVisualization.click();
		EmpressSite.dashboardPage.checkOpened();
		return EmpressSite.dashboardPage;
	}

	public DevicesPage openDevicesPage() {
		devices.click();
		EmpressSite.devicesPage.checkOpened();
		return EmpressSite.devicesPage;
	}
	
}

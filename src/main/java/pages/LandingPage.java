package pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.web.matcher.testng.Assert;

import sites.EmpressSite;

public class LandingPage extends InternalPage {

	@FindBy(css="a.home-page-controls-item:nth-child(1)")
	private Button dataAcquisition;
	
	@FindBy(css="a.home-page-controls-item:nth-child(2)")
	private Button dataVisualization;

	@FindBy(css="a.home-page-controls-item:nth-child(5)")
	private Button devices;

	@FindBy(css=".welcome-message")
	private Text welcomeMessage;
	
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
	
	@Override
	public void checkOpened() {
		super.checkOpened();
		Assert.isTrue(() -> welcomeMessage.isDisplayed()); 
	}
	
}

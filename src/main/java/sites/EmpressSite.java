package sites;

import org.sikuli.script.Screen;

import com.epam.jdi.uitests.web.selenium.elements.composite.CheckPageTypes;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

import pages.DashboardPage;
import pages.DevicesPage;
import pages.EditEntityPage;
import pages.ExperimentPage;
import pages.ExperimentTemplatesPage;
import pages.LandingPage;
import pages.LoginPage;
import pages.MonitoringPage;
import pages.UserSettingsPage;
import pages.ViewAnalysisPage;


@JSite(domain = "http://mld-idv-dev1.cloudapp.net/index.html#") //empress.ggasoftware.com:8080/index.html#
public class EmpressSite extends WebSite {

	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/login", title="Login")
	public static LoginPage loginPage;
	
	@JPage(url="/", title="Home")
	public static LandingPage landingPage;
	
	@JPage(url="/processing/templates", title="Acquisition")
	public static ExperimentTemplatesPage experimentTemplatesPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/processing/experiment/", title="Protocol")
	public static ExperimentPage experimentPage;
	
	@JPage(url="/visualization/dashboard", title="Experiments")
	public static DashboardPage dashboardPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/visualization/entityedit/")
	public static EditEntityPage editEntityPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/visualization/experiment/", title="View Analysis")
	public static ViewAnalysisPage viewAnalysisPage;
	
	@JPage(url="/monitoring", title="Monitor")
	public static MonitoringPage monitoringPage;
	
	@JPage(url="/config", title="Configuration Settings")
	public static UserSettingsPage userSettingsPage;

	@JPage(url="/devices", title="Devices")
	public static DevicesPage devicesPage;
	
	public static Screen screen = new Screen();
}

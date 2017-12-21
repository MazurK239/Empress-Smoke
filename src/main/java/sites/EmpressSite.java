package sites;

import com.epam.jdi.uitests.web.selenium.elements.composite.CheckPageTypes;

//import org.sikuli.script.Screen;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

import pages.*;

@JSite(domain = "http://eprupetw6500.petersburg.epam.com:8080/index.html#")
public class EmpressSite extends WebSite {

	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/login", title="Login")
	public static LoginPage loginPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/", title="Home")
	public static LandingPage landingPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/processing/templates", title="Acquisition")
	public static ExperimentTemplatesPage experimentTemplatesPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/processing/experiment/", title="Acquisition")
	public static ExperimentPage experimentPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/visualization/dashboard", title="Experiments")
	public static DashboardPage dashboardPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/visualization/entityedit/")
	public static EditEntityPage editEntityPage;

	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/visualization/experiment-edit/", title="Experiment Edit")
	public static NewEditEntityPage experimentEditPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/processing/analysis/", title="Add Analysis")
	public static AddAnalysisPage addAnalysisPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/visualization/experiment/", title="View Analysis")
	public static ViewAnalysisPage viewAnalysisPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/monitoring", title="Monitor")
	public static MonitoringPage monitoringPage;
	
	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/config", title="Configuration Settings")
	public static UserSettingsPage userSettingsPage;

	@JPage(urlCheckType=CheckPageTypes.CONTAIN, url="/devices", title="Devices")
	public static DevicesPage devicesPage;
	
//	public static Screen screen = new Screen();
}

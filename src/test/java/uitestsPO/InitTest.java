package uitestsPO;

import static com.epam.commons.PropertyReader.fillAction;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static sites.EmpressSite.*;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;

import enums.ExpDashboardTabs;
import sites.EmpressSite;

public class InitTest extends TestNGBase {
	
	protected static String DEVICE_NAME;
	protected static String USERNAME;
	protected static String PASSWORD;
	protected static String MLDPASSWORD;
	
	protected static List<String> AVAILABLE_STAINS;
	protected static List<String> UNAVAILABLE_STAINS;
	protected static List<String> TL_OPTIONS;
	
	protected static WebDriver driver;
	
	protected final static String TEMPLATE_NAME = "New Plate Acquisition";
	
	protected static Verify verify = new Verify();

	@BeforeSuite(alwaysRun = true)
	public static void init() {
		WebSite.init(EmpressSite.class);
		Verify.getFails();
		driver = loginPage.getDriver();
		driver.manage().window().setSize(new Dimension(1700, 980));;
        logger.info("Run Tests");
        fillAction(p -> DEVICE_NAME = p, "device");
        fillAction(p -> USERNAME = p, "username");
        fillAction(p -> PASSWORD = p, "password");
        fillAction(p -> MLDPASSWORD = p, "mldpassword");
	}
	
	public static void initStainLists() {
		landingPage.openDataAcquisitionPage().selectTab(ExpDashboardTabs.ADD_PROTOCOL).findTemplate(TEMPLATE_NAME).open();
		AVAILABLE_STAINS = experimentPage.openAcquisitionTab().getAvailableStains();
		UNAVAILABLE_STAINS = experimentPage.acquisitionTab.getUnavailableStains();
		TL_OPTIONS = experimentPage.acquisitionTab.getTLOptions();
		experimentPage.goHome();
	}
	
	@AfterMethod(alwaysRun = true)
    public void tearDown() {
        Verify.getFails();
    }

	@AfterSuite(alwaysRun = true)
	public void finish() {		
		driver.quit();
	}
	
}

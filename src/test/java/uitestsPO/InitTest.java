package uitestsPO;

import static com.epam.commons.PropertyReader.fillAction;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static sites.EmpressSite.devicesPage;
import static sites.EmpressSite.landingPage;
import static sites.EmpressSite.loginPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;

import sites.EmpressSite;

public class InitTest extends TestNGBase {
	
	protected static String DEVICE_NAME;
	protected static String USERNAME;
	protected static String PASSWORD;
	protected static String MLDPASSWORD;
	
	protected static Verify verify = new Verify();

	@BeforeSuite(alwaysRun = true)
	public static void init() {
		WebSite.init(EmpressSite.class);
		Verify.getFails();
        logger.info("Run Tests");
        fillAction(p -> DEVICE_NAME = p, "device");
        fillAction(p -> USERNAME = p, "username");
        fillAction(p -> PASSWORD = p, "password");
        fillAction(p -> MLDPASSWORD = p, "mldpassword");
	}
	
	@AfterMethod
    public void tearDown() {
        Verify.getFails();
    }
	
	public void loginAndSetService() {
		loginPage.isOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.openDevicesPage().setAnService("first");
		devicesPage.goHome();
	}
	
}

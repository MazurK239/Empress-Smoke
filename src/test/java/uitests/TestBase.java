package uitests;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.base.Function;

import ru.stqa.selenium.factory.WebDriverFactory;
import org.openqa.selenium.Platform;

import util.PropertyLoader;

public abstract class TestBase {

	protected static WebDriver driver;
	protected static String baseUrl;
	protected static WebDriverWait wait;
	protected static Actions action;
	protected static String preferredDevice;

	
	@BeforeClass
	public void setUpDriver() {
		baseUrl = PropertyLoader.loadProperty("site.url");
		preferredDevice = PropertyLoader.loadProperty("sila.name");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(PropertyLoader.loadProperty("browser.name"));
		capabilities.setVersion(PropertyLoader.loadProperty("browser.version"));
		String platform = PropertyLoader.loadProperty("browser.platform");
		if (!(null == platform || "".equals(platform))) {
			capabilities.setPlatform(Platform.valueOf(PropertyLoader.loadProperty("browser.platform")));
		}

		driver = WebDriverFactory.getDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	protected boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
	
	protected void login() {
		WebElement name = driver.findElement(By.name("user"));
		name.sendKeys("user1");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("p@ssw0rd");
		WebElement loginButton = driver.findElement(By.cssSelector("button"));
		loginButton.click();
	}
	
	protected void navigate(String page) {
		driver.findElement(By.xpath("//button/*[contains(@class,'hamburger')]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'pinned-panel_left_opened')]")));
		WebElement link = driver.findElement(By.xpath("//li/*[text()='" + page + "']/.."));
		wait.until(ExpectedConditions.elementToBeClickable(link));
		action.moveToElement(link, 290, 23).click().build().perform();
	}
	
	protected void selectWells(List<String> wells) {
		for (String well : wells) {
			char letter = well.substring(0, 1).charAt(0);
			int yOffset = 50 + 40 * ((int)letter - (int)("A".charAt(0)));
			int xOffset = 50 + 40 * (Integer.parseInt(well.substring(1)) - 1);
			WebElement canvas = driver.findElement(By.xpath("//canvas[contains(@class, 'plate-selector')]"));
			Actions action = new Actions(driver);
			action.moveToElement(canvas , xOffset, yOffset).click().build().perform();	
		}
	}
	
	protected void waitForPageToLoad(String header) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'navbar-item')][contains(text(),'" + header + "')]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='pinned-panel pinned-panel_left']")));
	}
	
	protected WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(300, TimeUnit.SECONDS)
	            .pollingEvery(5, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);
	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            WebElement element = driver.findElement(locator);
	        	return element.isDisplayed()? element : null;
	        }
	    });
	    return  foo;
	};
	
	protected void changeStainsToActive() {
		List<WebElement> badSelects = driver.findElements(By.xpath("//select[contains(@class, 'shaded stroked')]"));
		int i = 1;
		for (WebElement select : badSelects) {
			select.click();
			List<WebElement> availableStains = select.findElements(By.xpath("./optgroup[@label='Available']/option"));
			availableStains.get(availableStains.size() - i++).click();
		}
	}

	
}

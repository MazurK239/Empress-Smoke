package uitests;

import static org.testng.AssertJUnit.*;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SmokeTest extends TestBase {

	private String expName =  "Auto test run" + System.currentTimeMillis();
//	private String expName =  "Auto test run1470823879512"; 

	@BeforeClass
	public void init() {
		wait = new WebDriverWait(driver, 20, 500);
		driver.get(baseUrl);
	}
	
	@Test
	public void loginAndNavigate() {
		login();
		driver.findElement(By.xpath("//span[contains(text(),'Data Acquisition')]/..")).click();
//		waitForPageToLoad("Experiment Templates");
		driver.findElement(By.xpath("//li[@heading='Templates']")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='tab-content']/div[2]//mld-experiment-template"));
		for (WebElement element : elements) {
			String name = element.findElement(By.cssSelector(".dashboard-item__heading")).getText();
			System.out.println(name);
		}
	}
	
	@Test(dependsOnMethods={"loginAndNavigate"})
	public void createProtocol() {
		List<WebElement> templates = driver.findElements(By.xpath("//*[@class='dashboard-item__heading'][text()='New Plate Acquisition']"));
		templates.get(templates.size() - 1).click();
		waitForPageToLoad("Experiment");
		driver.findElement(By.xpath("//button[@title='Select Device']")).click();
		driver.findElement(By.xpath("//li/span[text()='" + preferredDevice + "']/..")).click();
		driver.findElement(By.xpath("//button[@title='Acquisition']")).click();
		driver.findElement(By.xpath("//button[@title='Labware']")).click();
		WebElement plate = driver.findElement(By.xpath("//*[@title='96 Well Plate']/../.."));
		wait.until(ExpectedConditions.visibilityOf(plate));
		action.moveToElement(plate, 5, 5).click().build().perform();
		driver.findElement(By.xpath("//button[@title='Stains']")).click();
		changeStainsToActive();
		WebElement checkbox = driver.findElement(By.id("is-tl"));
		WebElement label = driver.findElement(By.xpath("//*[contains(text(),'Transmitted light')]"));
		wait.until(ExpectedConditions.visibilityOf(label));
		if (checkbox.isSelected())
		{
		     label.click();
		}
		driver.findElement(By.xpath("//button[@title='Objectives']")).click();
		WebElement objective = driver.findElement(By.xpath("//*[@class='ng-binding'][contains(text(),'4x')]"));
		wait.until(ExpectedConditions.visibilityOf(objective));
		objective.click();
		driver.findElement(By.xpath("//button[contains(@class, 'left')][@title='Save']")).click();
		WebElement nameField = driver.findElement(By.xpath("//input[contains(@class,'name-input')]"));
		WebElement description = driver.findElement(By.xpath("//textarea[contains(@class,'description-input')]"));
		nameField.clear();
		nameField.sendKeys("Test Protocol");
		description.clear();
		description.sendKeys("For automation needs");
		driver.findElement(By.xpath("//div[@class='exp-save-panel-buttons']//button[@title='Save']")).click();
		assertTrue(isElementPresent(By.xpath("//*[contains(text(),'Protocol has been saved')]")));
		navigate("Experiment Templates");
		waitForPageToLoad("Experiment Templates");
		WebElement protocolsTab = driver.findElement(By.xpath("//li[@heading='Protocols']"));
		action.moveToElement(protocolsTab, 1, 1).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='tab-content']/div[3]//mld-experiment-template")));
		assertTrue(driver.findElement(By.xpath("//*[@class='dashboard-item__heading'][text()='Test Protocol']")).isDisplayed());
		navigate("Home");
		waitForPageToLoad("Landing");
	}
	

	@Test(dependsOnMethods = {"createProtocol"})
	public void runProtocol () {
		driver.findElement(By.xpath("//span[contains(text(),'Data Acquisition')]/..")).click();
		waitForPageToLoad("Experiment Templates");
		WebElement protocolsTab = driver.findElement(By.xpath("//li[@heading='Protocols']"));	
		action.moveToElement(protocolsTab, 1, 1).click().build().perform();
		List<WebElement> protocols = driver.findElements(By.xpath("//*[@class='dashboard-item__heading'][text()='Test Protocol']/../../.."));
		protocols.get(protocols.size() - 1).findElement(By.cssSelector("button[title='Run']")).click();
		waitForPageToLoad("Experiment");
		driver.findElement(By.xpath("//button[@title='Well selection']")).click();
		selectWells(Arrays.asList("E7","E8"));
		driver.findElement(By.xpath("//button[@title='Zone Selection for Analysis']")).click();
		driver.findElement(By.xpath("//button[@title='Custom']")).click();	
		driver.findElement(By.xpath("//button[@title='Select Device']")).click();
		driver.findElement(By.xpath("//button[@title='Zone Selection for Analysis']")).click();
		WebElement slider = driver.findElement(By.xpath("//*[text()='Include Zone']/..//*[contains(@class,'noUi-base')]"));
		action.moveToElement(slider, 0, 5).click().build().perform();
		driver.findElement(By.xpath("//button[@title='Zone Selection for Acquisition']")).click();
		slider = driver.findElement(By.xpath("//*[text()='Include Zone']/..//*[contains(@class,'noUi-base')]"));
		action.moveToElement(slider, 0, 5).click().build().perform();
		driver.findElement(By.xpath("//button[@title='Run Settings']")).click();
		driver.findElement(By.xpath("//input[contains(@class, 'name-input')]")).sendKeys(expName);
		driver.findElement(By.xpath("//textarea[contains(@class, 'description-input')]")).sendKeys("For automation needs");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Run Experiment']")));
		driver.findElement(By.xpath("//button[@title='Run Experiment']")).click();
		waitForPageToLoad("Monitoring");
		driver.findElement(By.xpath("//li[@heading='Succeeded']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='tab-content']/div[3]//*[text()='Finish Time']")));
		fluentWait(By.xpath("//mld-monitoring-table//*[text()='" + expName + "']"));
		navigate("Home");
		waitForPageToLoad("Landing");
	}
	
	@Test(dependsOnMethods={"runProtocol"})
	public void verifyExperiment() {
		driver.findElement(By.xpath("//span[contains(text(),'Data Visualization')]/..")).click();
		waitForPageToLoad("Experiments Dashboard");
		driver.findElement(By.xpath("//li[@heading='experiments']")).click();
		List<WebElement> experiments = driver.findElements(By.xpath("//*[@class='dashboard-item-vis__name'][text()='" + expName + "']/../../.."));
		WebElement dropDown = experiments.get(experiments.size() - 1).findElement(By.cssSelector("select"));
		dropDown.click();
		assertTrue(dropDown.findElement(By.xpath("./option[text()='Acquired images']")).isDisplayed());
		assertTrue(dropDown.findElement(By.xpath("./option[text()='NeuriteOutgrowth']")).isDisplayed());
		dropDown.findElement(By.xpath("./option[text()='Acquired images']")).click();
		experiments.get(experiments.size() - 1).findElement(By.cssSelector("button[title='View']")).click();
		waitForPageToLoad("View Analysis");
		assertTrue(driver.findElement(By.xpath("//mld-acquired-images-tab//*[@class='table-canvas-wrapper']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Go to...']")).click();
		driver.findElement(By.xpath("//button[@title='Images']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mld-image-gallery//div[@class='image-gallery']")));
		assertTrue(isElementPresent(By.xpath("//ul[@class='thumbs']//*[text()='E7']")));
		assertTrue(isElementPresent(By.xpath("//ul[@class='thumbs']//*[text()='E8']")));
		driver.findElement(By.xpath("//button[contains(@class, 'navbar-experiment-info__menu-toggler')]")).click();
		driver.findElement(By.xpath("//*[contains(@class, 'dropdown-menu')]/li/*[text()='NeuriteOutgrowth']")).click();
		assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'deepzoom-wrapper')]//*[@class='analysis-zone']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Zones']")).click();
		driver.findElement(By.xpath("//button[@title='Heatmap']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mld-heatmap-tab//*[@class='table-canvas-wrapper']")));
		driver.findElement(By.xpath("//button[@title='Go to...']")).click();
		driver.findElement(By.xpath("//*[contains(@class,'rolling-panel')]/button[@title='Cell level Heatmap']")).click();
		assertTrue(driver.findElement(By.xpath("//mld-cell-level-heatmap-tab//*[@class='table-canvas-wrapper']")).isDisplayed());
		navigate("Home");
		waitForPageToLoad("Landing");
	}
	
	@Test(dependsOnMethods = {"runProtocol"})
	public void cleanUpProtocol() {
		driver.findElement(By.xpath("//span[contains(text(),'Data Acquisition')]/..")).click();
		waitForPageToLoad("Experiment Templates");
		WebElement protocolsTab = driver.findElement(By.xpath("//li[@heading='Protocols']"));	
		action.moveToElement(protocolsTab, 1, 1).click().build().perform();
		List<WebElement> protocolsBefore = driver.findElements(By.xpath("//*[@class='tab-content']/div[3]//mld-experiment-template"));
		List<WebElement> testProtocols = driver.findElements(By.xpath("//*[@class='dashboard-item__heading'][text()='Test Protocol']/../../.."));
		testProtocols.get(testProtocols.size() - 1).findElement(By.cssSelector("button[title='Delete']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
		assertTrue(driver.findElement(By.xpath("//*[text()='Do you want to delete \"Test Protocol\"?']")).isDisplayed());
		driver.findElement(By.xpath("//*[@class='modal-dialog']//button[text()='Ok']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));
		List<WebElement> protocolsAfter = driver.findElements(By.xpath("//*[@class='tab-content']/div[3]//mld-experiment-template"));
		assertTrue(protocolsAfter.size() == protocolsBefore.size() - 1);
		navigate("Home");
		waitForPageToLoad("Landing");
	}

	@Test(dependsOnMethods={"verifyExperiment"})
	public void cleanUpExperiment() {
		driver.findElement(By.xpath("//span[contains(text(),'Data Visualization')]/..")).click();
		waitForPageToLoad("Experiments Dashboard");
		driver.findElement(By.xpath("//li[@heading='experiments']")).click();	
		List<WebElement> experiments = driver.findElements(By.xpath("//*[@class='dashboard-item-vis__name'][text()='" + expName + "']"));
		experiments.get(experiments.size() - 1).click();
		driver.findElement(By.xpath("//button[@title='Delete Experiment']")).click();
		assertTrue(driver.findElement(By.xpath("//*[text()='Do you want to delete \"" + expName + "\"?']")).isDisplayed());
		driver.findElement(By.xpath("//*[@class='modal-dialog']//button[text()='Ok']")).click();
		waitForPageToLoad("Experiments Dashboard");
		navigate("Home");
		waitForPageToLoad("Landing");
	}
	
}

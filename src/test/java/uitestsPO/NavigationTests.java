package uitestsPO;

import static com.epam.web.matcher.testng.Assert.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.epam.commons.Timer;

import enums.AnalysisViews;

import static sites.EmpressSite.*;

import java.util.Arrays;

public class NavigationTests extends InitTest {
	
	@BeforeSuite
	public void login() {
		loginPage.isOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
	}

	
	@Test
	public void cellLevelDropDown() {
		landingPage.openDataVisualizationPage();
		dashboardPage.findExperiment("Some experiment").choose("Cell Count").view();
		viewAnalysisPage.thumbView.selectWells(Arrays.asList("C4"), new int[]{6,4});
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_HEATMAP);;
		assertTrue(() -> viewAnalysisPage.cellHeatmap.isMainControlDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_STACKED);
		assertTrue(() -> viewAnalysisPage.cellStacked.isMainControlDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_HEATMAP);
		Timer.waitCondition(() -> viewAnalysisPage.cellHeatmap.isMainControlDisplayed());
		viewAnalysisPage.cellHeatmap.selectAreaOnHeatmap("56", "85", new int[]{20,16});
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_IMAGES);
		assertTrue(() -> viewAnalysisPage.cellDeepZoom.isMinimapDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_TABLE);
		assertTrue(() -> viewAnalysisPage.cellTable.isMainControlDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.THUMBS);
		assertTrue(() -> viewAnalysisPage.thumbView.isMainControlDisplayed());
		viewAnalysisPage.goHome();
	}
	
}

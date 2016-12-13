package uitestsPO;

import static com.epam.web.matcher.testng.Assert.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.epam.commons.Timer;

import enums.PlateAnalysisViews;
import enums.VisDashboardTabs;

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
		landingPage.openDataVisualizationPage().selectTab(VisDashboardTabs.EXPERIMENTS);
		dashboardPage.findExperiment("Some experiment").choose("Cell Count").view();
		viewAnalysisPage.thumbView.selectWells(Arrays.asList("C4"), new int[]{6,4}).goToCellHeatmap();
		assertTrue(() -> viewAnalysisPage.cellHeatmap.isMainControlDisplayed());
		viewAnalysisPage.navigateToCellView(PlateAnalysisViews.CELL_STACKED);
		assertTrue(() -> viewAnalysisPage.cellStacked.isMainControlDisplayed());
		viewAnalysisPage.navigateToCellView(PlateAnalysisViews.CELL_HEATMAP);
		Timer.waitCondition(() -> viewAnalysisPage.cellHeatmap.isMainControlDisplayed());
		viewAnalysisPage.cellHeatmap.selectAreaOnHeatmap("56", "85", new int[]{20,16});
		viewAnalysisPage.navigateToCellView(PlateAnalysisViews.CELL_IMAGES);
		assertTrue(() -> viewAnalysisPage.cellDeepZoom.isMinimapDisplayed());
		viewAnalysisPage.navigateToCellView(PlateAnalysisViews.CELL_TABLE);
		assertTrue(() -> viewAnalysisPage.cellTable.isMainControlDisplayed());
		viewAnalysisPage.navigateToWellView(PlateAnalysisViews.THUMBS);
		assertTrue(() -> viewAnalysisPage.thumbView.isMainControlDisplayed());
		viewAnalysisPage.goHome();
	}
	
}

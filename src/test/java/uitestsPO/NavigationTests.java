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
		loginPage.open();
		loginPage.checkOpened();
		loginPage.loginAs(USERNAME, PASSWORD);
		landingPage.checkOpened();
	}
	
	@Test
	public void cellLevel() {
		landingPage.openDataVisualizationPage();
		dashboardPage.findFirstGoodExperiment().open();
		int[] geometry = editEntityPage.openAcquisitionProperties().getGeometry(); 
 		editEntityPage.openAnalyses().navigation.open(AnalysisViews.THUMBS);
		viewAnalysisPage.thumbView.selectWells(Arrays.asList("F12"), geometry);
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_HEATMAP);;
		assertTrue(() -> viewAnalysisPage.cellHeatmap.isMainControlDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_STACKED);
		assertTrue(() -> viewAnalysisPage.cellStacked.isMainControlDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_HEATMAP);
		Timer.waitCondition(() -> viewAnalysisPage.cellHeatmap.isMainControlDisplayed());
		viewAnalysisPage.cellHeatmap.selectAreaOnHeatmap(0.1f, 0.5f, 0.5f, 0.6f);
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_IMAGES);
		assertTrue(() -> viewAnalysisPage.cellDeepZoom.isMinimapDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.CELL_TABLE);
		assertTrue(() -> viewAnalysisPage.cellTable.isMainControlDisplayed());
		viewAnalysisPage.navigateTo(AnalysisViews.THUMBS);
		assertTrue(() -> viewAnalysisPage.thumbView.isMainControlDisplayed());
		viewAnalysisPage.goHome();
	}
	
}

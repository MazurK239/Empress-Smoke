package pages;

import org.openqa.selenium.support.FindBy;

import enums.AnalysisViews;
import model.BreadcrumbDropdown;
import model.NavPanel;
import sections.visualization.CellDeepZoomSection;
import sections.visualization.CellHeatmapSection;
import sections.visualization.CellScatterSection;
import sections.visualization.CellStackedBarSection;
import sections.visualization.CellTableSection;
import sections.visualization.DeepZoomSection;
import sections.visualization.HeatmapSection;
import sections.visualization.ThumbViewSection;

public class ViewAnalysisPage extends InternalPage {
	
	@FindBy(xpath="//mld-breadcrumb-menu//li[last()-3]//mld-breadcrumb-menu-dropdown-item")
	private BreadcrumbDropdown analysisDropdown;

	@FindBy(css="mld-acquired-images-tab")
	public ThumbViewSection thumbView;

	@FindBy(css="mld-deep-zoom-tab")
	public DeepZoomSection deepZoom;

	@FindBy(css="mld-heatmap-tab")
	public HeatmapSection heatmap;

	@FindBy(css="mld-cell-level-heatmap-tab")
	public CellHeatmapSection cellHeatmap;

	@FindBy(css="mld-cell-level-stacked-bar-tab")
	public CellStackedBarSection cellStacked;

	@FindBy(css="mld-cell-level-deep-zoom-tab")
	public CellDeepZoomSection cellDeepZoom;

	@FindBy(css="mld-cell-level-table-tab")
	public CellTableSection cellTable;

	@FindBy(css="mld-cell-level-scatter-tab")
	public CellScatterSection cellScatter;

	@FindBy(css="mld-left-navigation-panel")
	public NavPanel leftNavPanel;

	
	public void switchTo(String analysis) {
		analysisDropdown.select(analysis);	
	}

	public void openEditEntity() {
		this.breadcrumbsMenu.get(this.breadcrumbsMenu.size() - 3).click();
	}

	public void navigateTo(AnalysisViews view) {
		leftNavPanel.open(view);
	}


}

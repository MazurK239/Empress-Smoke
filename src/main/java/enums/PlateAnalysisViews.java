package enums;

public enum PlateAnalysisViews {
	HEATMAP("Well Heatmap"), 
	DATA("Well Plate Data View"), 
	THUMBS("Well Thumbnail View"),
	SCATTER("Well Scatter Plot"),
	STACKED("Well Stacked Bar"),
	TABLE("Well Table"),
	IMAGES("Well Images"),
	CELL_HEATMAP("Cell Level Heatmap"),
	CELL_STACKED("Cell Level Stacked Bar"),
//	CELL_SCATTER("Cell Level Scatter Plot"),
	CELL_TABLE("Cell Level Table"),
	CELL_IMAGES("Cell Level Images");
	
	public String value;
	
	PlateAnalysisViews(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}

package sections.visualization;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;

public class CellTableSection extends CellLevelSection {

	@FindBy(css=".table-tab")
	private Table table;

	@Override
	public boolean isMainControlDisplayed() {
		return table.isDisplayed();
	}
	
}

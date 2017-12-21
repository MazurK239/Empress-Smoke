package sections.experiment;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.AnalysisTile;

public class AnalysesSection extends Section {

	@FindBy(css="div > a")
	private Button addAnalysisButton;

	@FindBy(css=".mld-select-list li")
	private Elements<AnalysisTile> analyses;

	public void addAnalysis() {
		addAnalysisButton.click();
	}

	public AnalysisTile findAnalysis(String analysisName) {
		int i = 1;
		for (AnalysisTile analysis : analyses) {
			System.out.println(i++);
			if (analysis.getAnalysisName().equals(analysisName)) {
				return analysis;
			}
		}
		System.out.println("=== MESSAGE === Analysis with name " + analysisName + "doesn't exist!");
		return null;
	}

}

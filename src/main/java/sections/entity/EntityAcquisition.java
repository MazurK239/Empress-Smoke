package sections.entity;

import java.util.Arrays;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

public class EntityAcquisition extends Section {

	@FindBy(css=".vis-acquisition-settings-bordered-block:nth-child(2) > div:nth-child(2) > div:nth-child(2)")
	private Text geometryField;

	public int[] getGeometry() {
		int[] geometry = Arrays.asList(geometryField.getText().split(" x ")).stream().mapToInt(Integer::parseInt).toArray();
		return geometry;
	}

}

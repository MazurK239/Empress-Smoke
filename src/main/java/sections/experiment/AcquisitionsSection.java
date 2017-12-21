package sections.experiment;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import model.ExpProtocolTile;

public class AcquisitionsSection extends Section {

	@FindBy(css="div > a")
	private Button addProtocolButton;

	@FindBy(css=".mld-select-list li")
	private Elements<ExpProtocolTile> protocols;

	public void addProtocol() {
		addProtocolButton.click();
	}

	public ExpProtocolTile findProtocol(String protocolName) {
		int i = 1;
		for (ExpProtocolTile protocol : protocols) {
			System.out.println(i++);
			if (protocol.getProtocolName().equals(protocolName)) {
				return protocol;
			}
		}
		System.out.println("=== MESSAGE === Analysis with name " + protocolName + "doesn't exist!");
		return null;
	}

}

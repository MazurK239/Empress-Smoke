package model;

import java.util.List;

import org.sikuli.script.FindFailed;

import static sites.EmpressSite.screen;

import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;

public class OSD extends Clickable {
	
	public boolean areChannelsEnabled(List<String> stains) {
		for (String stain : stains) {
			if (screen.exists("skl_img/" + stain + "_enabled.png") == null) return false;
		}
		return true;
	}

	public boolean areChannelsDisabled(List<String> stains) {
		for (String stain : stains) {
			if (screen.exists("skl_img/" + stain + "_disabled.png") == null) return false;
		}
		return true;
	}

	public void deselectChannels(List<String> channels) {
		for (String channel : channels) {
			try {
				screen.click("skl_img/" + channel + "_enabled.png");
			} catch (FindFailed e) {
				e.printStackTrace();
			}
		}
	}

}

package enums;

public enum ExpDashboardTabs {
	ADD_PROTOCOL("ADD PROTOCOL"), 
	PROTOCOLS("PROTOCOLS");
	
	public String value;
	
	ExpDashboardTabs(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
//	PROTOCOLS, ADD_PROTOCOL
}

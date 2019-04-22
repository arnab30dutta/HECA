
package heca;

import java.sql.Time;

public class Appliance{
	  String applianceCategory; //e.g Electricity
	  ApplianceType applianceType; //e.g StrictAppliance like Refrigerator
      String applianceName; //e.g WashingMachine
      int usageTime;
      Time scheduledTime;
      
	public Appliance(String applianceCategory, ApplianceType applianceType, String applianceName, int usageTime,Time scheduledTime) {
		this.applianceCategory = applianceCategory;
		this.applianceType = applianceType;
		this.applianceName = applianceName;
		this.usageTime = usageTime;
		this.scheduledTime = scheduledTime;
	}
      
      
}

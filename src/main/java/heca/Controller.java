package heca;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface Controller {
	public String createUser(String userName);
	public boolean addAppliance(String userId,String applianceCategory);
	public boolean addAppliance(String userId, String applianceCategory,List<Attribute> attribs);
	public void addAttribute(String userId,String applianceCategory,String attributeName);
	public void updateConsumption(String userId,String applianceCategory,String attributeName,int updatedValue);
	public List<Attribute> getMAXExpenses(String userId);
	public List<Attribute> getMINExpenses(String userId);
	public Map<String,List<Attribute>> getAllConsumptionDetails(String userId);
	public List<Attribute> getSpecificConsumptionDetails(String userId,String applianceCategory);
	public Score getScore();
	public Badge getBadge();
	public List<Attribute> getSuggestedOptimizedGoal(String userId,String applianceCategory,int target);
	public boolean modifyGoal(String userId,String applianceCategory,String attributeName,int targetValue);
	public boolean scheduleFlexibleAppliance(String userId,String applianceCategory, Appliance applianceName,Time schedule);
	
}

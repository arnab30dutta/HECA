package heca;
	

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



public class DesignHECA implements Controller {
	
	private UserDB userDB = new UserDB();

	@Override
	public String createUser(String userName) {
    	String userId = userName+ new java.util.Random();
    	HomeUser u = new HomeUser(userId);
    	userDB.addUser(userId,u);
		return userId ;
	}
	
    public HomeUser getUser(String userId) {
		return userDB.getUser(userId);
	}


    /*----------------------- addAppliance ----------------------------------------*/
		@Override
		public boolean addAppliance(String userId, String applianceCategory) {
	    	if(applianceCategory == null || applianceCategory.isEmpty()) return false;
	    	else getUser(userId).actualExpenseTracker.setApplianceDetails(applianceCategory,new ArrayList<>()); //telescoping
	    	return true;
			
		}
		@Override
		public boolean addAppliance(String userId, String applianceCategory,final List<Attribute> attribs){ 	// not to be leaked to Client 
	    	
			HomeUser user  = getUser(userId);
	    	
			if(applianceCategory == null || applianceCategory.isEmpty()) return false;
	    	else 
	    		if(!user.actualExpenseTracker.appliances.containsKey(applianceCategory)){
	    			user.actualExpenseTracker.setApplianceDetails(applianceCategory,attribs);  //defensive copy		    			
	    		}else{
	    			List<Attribute> prev = user.actualExpenseTracker.appliances.getOrDefault(applianceCategory, new ArrayList<>());
	    			prev.addAll(attribs); //handling override left for brevity
	    			user.actualExpenseTracker.setApplianceDetails(applianceCategory,prev);
	    		}
	    	return true;
	    }

	
	/*----------------------- addAttribute ----------------------------------------*/
	@Override
	public void addAttribute(String userId, String applianceCategory, String attributeName) {
		List<Attribute> attribs = new ArrayList<>();
		Attribute attribute = new Attribute(attributeName);
		attribs.add(attribute);
	   
		addAppliance(userId, applianceCategory,attribs);
		
	}

	/*----------------------- updateConsumption ----------------------------------------*/
	@Override
	public void updateConsumption(String userId, String applianceCategory, String attributeName, int tillNowConsumed) {
		//left intentionally for brevity		
	}
	
	/*----------------------- getMAXExpenses ----------------------------------------*/
	@Override
	public List<Attribute> getMAXExpenses(String userId) {
		HomeUser user  = getUser(userId);		
		return user.actualExpenseTracker.get_TopK_MAXConsumptionAppliance(10);
	}

	/*----------------------- getMINExpenses ----------------------------------------*/
	@Override
	public List<Attribute> getMINExpenses(String userId) {
		HomeUser user  = getUser(userId);		
		return user.actualExpenseTracker.get_TopK_MINConsumptionAppliance(10);
	}
	/*----------------------- getAllConsumptionDetails ----------------------------------------*/
	@Override
	public Map<String, List<Attribute>> getAllConsumptionDetails(String userId) {
		HomeUser user  = getUser(userId);
		return user.actualExpenseTracker.getALLApplianceDetails();
	}

	
	/*----------------------- getSpecificConsumptionDetails ----------------------------------------*/
	@Override
	public List<Attribute> getSpecificConsumptionDetails(String userId, String applianceCategory) {
		HomeUser user  = getUser(userId);
		//checks omitted for brevity
    	return user.actualExpenseTracker.getApplianceDetails(applianceCategory);
    	
	}

	/*----------------------- modifyGoal - lets user to newly calibrate his target expenses ---*/
	@Override
	public boolean modifyGoal(String userId, String applianceCategory, String attributeName, int newlimit) {
		HomeUser user  = getUser(userId);
		List<Attribute> attribs = user.targetExpenseTracker.getApplianceDetails(applianceCategory);
		Attribute temp =null;
		for(int i=0; i<attribs.size();i++){			 
			if(attributeName.equals(attribs.get(i).attibuteName)){
				temp =attribs.remove(i);
				temp.limit = newlimit;
				attribs.add(temp);
				user.targetExpenseTracker.setApplianceDetails(applianceCategory,attribs);
				return true;
			}
		}
		return false;
	}
	    
	/*---------------------- getSuggestedOptimizedGoals ------------------------------
	 * This method Suggests optimized path(Top 5 MIN Expense) for Goal if calibration set by User predicted to meet target 
	 *  */
	@Override
	public List<Attribute> getSuggestedOptimizedGoal(String userId,String applianceCategory,int target) {
		HomeUser user  = getUser(userId);
		List<Attribute>  attribs = user.actualExpenseTracker.getApplianceDetails(applianceCategory);
		/* e.g 
			 "PowerSupply",340,880
			 "WindPower",120,1
			 "SolarEnergy",10,2 
	
		int[] w ={880,1,2};
		int[] c ={340,120,10};
		
		if( this.canProduce(c,w,user.monthlyBudget -user.tillNowExpense) > 0){ //optimiseed combination
			return this.getMINExpenses(userId);
		}
		else 
			return new ArrayList<>();	
		*/
		return this.getMINExpenses(userId);
	}
	public int canProduce(int[] c,int[] w, int W){
		return min_cost(c.length,W,c,w);
	}
	
	// Dynamic programming to compute Minimum Cost Path for fixed Weight 
	public int min_cost(int N, int W,int[] c, int[] w){
		// min_cost(i, W) = min(min_cost(i+1, W), min_cost(i, W - w[i]) + c[i])
		
		int[][] dp = new int[N][W];
		int i=0;
		
			//base cases
		       if(dp[i][0] ==  0 ) return 1;       // We already reached our goal
			   if(W < 0 || i > N) dp[i][W]  = Integer.MIN_VALUE;  // if (W < 0 or i > N) then we can't get to W

			dp[i][ W] = Math.min(min_cost(i+1, W,c,w), min_cost(i, W - w[i],c,w) + c[i]); 
			
		if(dp[N][ W] <= W)
			return dp[N][ W];
		else 
			return 0;//impossible --need to re calibrate
    }
	

	@Override
	public boolean scheduleFlexibleAppliance(String userId,String applianceCategory, Appliance applianceName,Time schedule) {
		// TODO omitted for brevity
		return false;
	}

	@Override
	public Score getScore() {
		// TODO omitted for brevity
		return null;
	}

	@Override
	public Badge getBadge() {
		// TODO omitted for brevity
		return null;
	}

			/*------------Driver Program -----------------------------------------------------------*/ 
		    public static void main(String args[] ) throws Exception {
		    	
		    		DesignHECA d = new DesignHECA();
		    		String userId = d.createUser("Chandra");
		    	
		     /** Create Attribute */
		    		List<Attribute> attributeList1 = new ArrayList<>();
		    			attributeList1.add( new Attribute.Builder("CarOil").perUnitWeight(80.0).consumed(20).limit(60).build());
		    			attributeList1.add( new Attribute.Builder("CookingOil").perUnitWeight(60.0).consumed(4).limit(12).build());
		    			attributeList1.add( new Attribute.Builder("CandleOil").perUnitWeight(12.0).consumed(2).limit(10).build());
		    		List<Attribute> attributeList2 = new ArrayList<>();
		    		    attributeList2.add( new Attribute.Builder("CrudeOil").perUnitWeight(12.0).consumed(10).limit(80).build());
		    			
		    		List<Attribute> attributeList3 = new ArrayList<>();
		    			attributeList3.add( new Attribute("PowerSupply",340.0,880,60));
		    			attributeList3.add( new Attribute("WindPower",120.0,1,2));
		    		List<Attribute> attributeList4 = new ArrayList<>();
		    			attributeList4.add( new Attribute("SolarEnergy",0.0,0,2));
		    			
		     /** Create ApplapplianceCategorydd Attributes */
		    			d.addAppliance(userId,"Fuel",attributeList1); 
		    			d.addAppliance(userId,"Fuel",attributeList2); 
		    			d.addAppliance(userId,"Electricity",attributeList3);
		    			d.addAppliance(userId,"Electricity",attributeList4); 
		    			
		     /** Optimize Electric Consumption */
		    	
		    			/*------------show Electric consumption ----------------------------------------------------------------*/ 
				    							d.getAllConsumptionDetails(userId).forEach((k,v)-> System.out.println(k+" : "+v));
				    		
				    	/*------------show Suggested Paths ----------------------------------------------------------------------*/
				    							System.out.println(d.getSpecificConsumptionDetails(userId,"Electricity")); 
				    							System.out.println(d.getSuggestedOptimizedGoal(userId,"Electricity",9000)); //DP based on Graph 
				    							
				    	/*------------user can opt to re-calibrating his target ---------------------------------------------------*/	
				    							d.modifyGoal(userId,"Electricity","PowerSupply", 600);
				    							
				    	/*------------user can opt to schedule Washing Machine ---------------------------------------------------*/
				    							//d.scheduleFlexibleAppliance(userId,"Electricity", "WashingMachine", new Time(11,30,20));
				    							
		       
		    }
		    
		   
		    
    /* *********************************************
       <TODO> : Implement following Business Methods 
       =============================================
		 --remmoveAppliance() 
		 --getTargetGoal()
		 --getProjectSavings()
		 --getBadgesAndIncentives()
		 --calculateDeviation()
		 --getSuggestedOptimizedGoals("Electric") DP based on Graph
	*/	
	/*----Constructor-------------*/
		    public DesignHECA(){ init(); } 
		    
		
	/*----Utility & Loaders-------*/
		    public void init() {   	}	//can be populated from File system	    

			
}

enum ApplianceType{
    STRICT, FLEXIBLE;    
}
enum Goal{
    EXCELLENT,GOAL_ACHIEVED,EXCEEDED;    
}
enum Badge{
    
		
		// Badge with Incentive
			COPPER(100),SILVER(300),GOLD(800);
		
			private int intValue;
			private String abbreviation;
		
			private Badge(final int intValue) {
			    this.intValue = intValue;
			}
			private Badge(String value) {
		        this.abbreviation = value;
		    }
		//lookup a Java enum from its ordinals
			private static Badge[] values = Badge.values();
			public static Badge getByID(int i) {				
				    return (values[i - 1] != null)? values[i - 1] : Badge.values()[i];
			}
	
}


enum Score{
    CONSUMES_LOW, CONSUMES_MEDIUM, CONSUMES_HIGH, CONSUMES_PEAK;    
}







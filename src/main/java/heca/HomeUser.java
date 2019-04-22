package heca;

public class HomeUser{
	
	String userId;
	
	EnergyTracker targetExpenseTracker  = new EnergyTracker();
	EnergyTracker actualExpenseTracker  = new EnergyTracker();
	
	int targetExpenseGoal, monthlyBudget, tillNowExpense;
	
	public HomeUser(String uID){
		userId = uID;
	}
	
	/* <TODO> calculate based on :
							getSavings() 
							getGoalAchieved() 
	*/
	public Badge showBadgesAndIncentives(){		
		return Badge.SILVER;
	}
	/* <TODO> judge based on 
		 		           total consumption cost of all Appliances -> Attribute -> consumed*perUnitWeight
	*/
	public Score getScore(){		
		return Score.CONSUMES_MEDIUM;
	}
	
	
	private int getGoalAchieved(){ 
		return targetExpenseGoal;
	}
	private int getSavings(){
		return monthlyBudget - tillNowExpense;
	}
	
	
	
	//setters

	public void setTargetExpenseGoal(int targetExpenseGoal) {
		this.targetExpenseGoal = targetExpenseGoal;
	}

	public void setMonthlyBudget(int monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public void setTillNowExpense(int tillNowExpense) {
		this.tillNowExpense = tillNowExpense;
	}
	
}


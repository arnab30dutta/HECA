package heca;

import java.util.HashMap;

public class UserDB{
	HashMap<String,HomeUser> usserMap = new HashMap<>();
	
	public void addUser(String userId,HomeUser u){
		usserMap.put(userId, u);
	}
	
	public HomeUser getUser(String userId){
		return usserMap.get(userId);
	}
}

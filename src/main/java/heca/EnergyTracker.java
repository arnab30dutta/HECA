package heca;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class EnergyTracker{	
	
	ConcurrentHashMap<String,List<Attribute>> appliances = new ConcurrentHashMap<>();   //AdjacencyMatrix	
	Queue<Attribute> maxExpenseHeap = new PriorityQueue<>(20, new CostComparator());
	Queue<Attribute> minExpenseHeap = new PriorityQueue<>(20, new CostComparator().reversed());
	
	public List<Attribute> getApplianceDetails(String aplianceName){
		if(appliances.containsKey(aplianceName))return appliances.get(aplianceName);
		else return new ArrayList<>(); 
	}
	public void setApplianceDetails(String aplianceName,List<Attribute> attribs){
		List<Attribute> renewedAttribs = appliances.get(aplianceName);
			if(renewedAttribs==null || renewedAttribs.isEmpty())
				renewedAttribs = attribs;
			else if(appliances.containsKey(aplianceName)){
				renewedAttribs.addAll(attribs);
			}
			appliances.put(aplianceName,renewedAttribs);
			renewedAttribs.forEach((attrib )-> this.maxExpenseHeap.offer(attrib));
			renewedAttribs.forEach((attrib )-> this.minExpenseHeap.offer(attrib));
			
	}

	public ConcurrentHashMap<String,List<Attribute>> getALLApplianceDetails() {		
		return appliances;
	}
	
	public List<Attribute> get_TopK_MINConsumptionAppliance(int K){
		ArrayList<Attribute> top5minConsumption = new ArrayList<>(K);
		Attribute temp =null;
		for( int i =0; i<K && K< minExpenseHeap.size() && i < minExpenseHeap.size(); ){
			temp = minExpenseHeap.poll();
			top5minConsumption.add(temp);
			minExpenseHeap.offer(temp);
			i++;
		}		
		return top5minConsumption;
	}
	
	public List<Attribute> get_TopK_MAXConsumptionAppliance(int K){
		ArrayList<Attribute> top5minConsumption = new ArrayList<>(K);
		Attribute temp =null;
		for( int i =0; i<K && K< maxExpenseHeap.size() && i < maxExpenseHeap.size(); ){
			temp = maxExpenseHeap.poll();
			top5minConsumption.add(temp);
			maxExpenseHeap.offer(temp);
		}		
		return top5minConsumption;
	}
}

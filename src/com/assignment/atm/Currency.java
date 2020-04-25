package com.assignment.atm;

import java.util.HashMap;
import java.util.Map;

public class Currency
{
    private int curNumber;
    private int curCounts;
    Map<Integer,Integer> currDetails = new HashMap<Integer,Integer>();
    
    public int getCurNumber() {
		return curNumber;
	}

	public void setCurNumber(int curNumber) {
		this.curNumber = curNumber;
	}

	public int getCurCounts() {
		return curCounts;
	}

	public void setCurCounts(int curCounts) {
		this.curCounts = curCounts;
	}

	public Map<Integer, Integer> getCurrDetails() {
		return currDetails;
	}

	public void setCurrDetails(Map<Integer, Integer> currDetails) {
		this.currDetails = currDetails;
	}
	
	
	
}
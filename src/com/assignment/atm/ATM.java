package com.assignment.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


class ATM {

    /** The Constant Currency Denominations. */
    protected static final int[] dolCurrency = { 20, 10, 5, 1 };

    /** The Number of Currencies of each type*/
    protected static int[] currNumbers = {5,10,10,10};

    /** The count. */
    protected  int[] count = { 0, 0, 0, 0};

    /** The total corpus. */
    public int totalAmount = 0;

    /** The amount. */
    protected int amount=0;
    
    public static int value = 1;
    
    public static int depositCounter = 1;
    
    private boolean depositNotes = false;

    
    public ATM(){
    }


    /**
     * To Calculate total fund.
     */
    public void calcTotalCorpus(){       
        for(int i = 0; i < dolCurrency.length; i++){
            totalAmount=totalAmount+dolCurrency[i]*currNumbers[i];
        }
        System.out.println("Total Existing Fund Value : " +totalAmount +"\n");
    }

    /**
     * To execute the withdraw cash.
     * @throws IOException 
     * @throws NumberFormatException 
     */
	public synchronized void withdrawCash() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter withdrawn amount");
		int amount = Integer.parseInt(br.readLine());
		if (amount < 0 || amount == 0 || amount >= totalAmount) {
			System.out.println("Output: Incorrect or insufficient funds" + "\n");
		} else {
			System.out.println("Withdraw " + value + " : " + amount);
			for (int i = 0; i < dolCurrency.length; i++) {
				if (dolCurrency[i] <= amount) {
					// If the amount is less than the currDenom[i] then that particular denomination
					// cannot be dispensed
					int noteCount = amount / dolCurrency[i];
					if (currNumbers[i] > 0) {// To check whether the ATM Vault is left with the currency denomination
												// under iteration
						// If the Note Count is greater than the number of notes in ATM vault for that
						// particular denomination then utilize all of them
						count[i] = noteCount >= currNumbers[i] ? currNumbers[i] : noteCount;
						currNumbers[i] = noteCount >= currNumbers[i] ? 0 : currNumbers[i] - noteCount;
						// Deduct the total fund left in the ATM Vault with the cash being dispensed in
						// this iteration
						totalAmount = totalAmount - (count[i] * dolCurrency[i]);
						// Calculate the amount that need to be addressed in the next iterations
						amount = amount - (count[i] * dolCurrency[i]);
					}
				}
			}
			value++;
			displayDispanseNotes();
			displayBalanceNotes();
		}
	}

  
	/**
	 * To execute the operations of deposit.
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public synchronized void depositCash() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Currency curr = new Currency();
		System.out.println("Please enter Demonination number for Deposit (like 20,10,5,1)");
		curr.setCurNumber(Integer.parseInt(br.readLine()));
		System.out.println("Please enter no of notes count of denomination for Deposit" );
		curr.setCurCounts(Integer.parseInt(br.readLine()));
		map.put(curr.getCurNumber(), curr.getCurCounts());
		curr.setCurrDetails(map);
		map.forEach((key, value) -> {
			validationDepositCash(key, value);
		});

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			for (int i = 0; i < dolCurrency.length; i++) {
				if (entry.getKey() == dolCurrency[i]) {
					currNumbers[i] = currNumbers[i] + entry.getValue();
				}
			}
		}
		depositCounter++;
		if(depositNotes) displayBalanceNotes();
		
	}


	/**
	 * @param key
	 * @param value
	 */
	private void validationDepositCash(Integer key, Integer value) {
		if (key == 0 || value == 0) {
			depositNotes = false;
			System.out.println("Deposit amount cannot be zero" +"\n");
		} else if (key < 0 || value < 0) {
			depositNotes = false;
			System.out.println("Incorrect deposit amount" +"\n");
		} else {
			depositNotes = true;
			System.out.println("Deposit " + depositCounter + " : " + key + "s : " + value);
		}
	}
    
    /**
     * Display Dispensed amount
     */
    private void displayDispanseNotes(){
   			System.out.println("Dispensed:  " + dolCurrency[0] + "s" + " = " + count[0] + "," + dolCurrency[1] + "s"
						+ " = " + count[1] + "," + dolCurrency[2] + "s" + " = " + count[2] + "," + dolCurrency[3] + "s"
						+ " = " + count[3]);
  }

    /**
     * Display Balance amount.
     */
	private void displayBalanceNotes() {
		System.out.println("Balance: " + dolCurrency[0] + "s" + " = " + currNumbers[0] + "," + dolCurrency[1] + "s"
				+ " = " + currNumbers[1] + "," + dolCurrency[2] + "s" + " = " + currNumbers[2] + "," + dolCurrency[3]
				+ "s" + " = " + currNumbers[3] + " Total" + "="
				+ ((dolCurrency[0] * currNumbers[0]) + (dolCurrency[1] * currNumbers[1])
						+ (dolCurrency[2] * currNumbers[2]) + (dolCurrency[3] * currNumbers[3])) +"\n");
	}
	
	
}

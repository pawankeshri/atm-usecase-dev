package com.assignment.atm;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ATMExecutor extends Thread implements Runnable {

public ATMExecutor() {
    
}

@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				ATM atm = new ATM();
				atm.calcTotalCorpus();
				atm.withdrawCash();
				try {
					Thread.sleep(200);
				} catch (InterruptedException ex) {
					Logger.getLogger(ATMExecutor.class.getName()).log(Level.SEVERE, null, ex);
				}
				if(i == 0 || i ==1) {
					atm.depositCash();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

public static void main(String[] args) {

	ATMExecutor ts1 = new ATMExecutor();
    ts1.start();
}

}
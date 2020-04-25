package com.assignment.atm.dp;

import java.util.Scanner;

public class ATMDispenseChain {

	private DispenseChain c1;

	public ATMDispenseChain() {
		
		this.c1 = new Dollar20Dispenser();
		DispenseChain c2 = new Dollar10Dispenser();
		DispenseChain c3 = new Dollar5Dispenser();
		DispenseChain c4 = new Dollar1Dispenser();

		// set the chain of responsibility
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		c3.setNextChain(c4);
	}

	public static void main(String[] args) {
		ATMDispenseChain atmDispenser = new ATMDispenseChain();
		while (true) {
			int amount = 0;
			System.out.println("Enter amount to dispense");
			Scanner input = new Scanner(System.in);
			amount = input.nextInt();
			atmDispenser.c1.dispense(new Currency(amount));
		}

	}

}
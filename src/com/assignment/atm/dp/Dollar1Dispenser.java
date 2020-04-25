package com.assignment.atm.dp;
public class Dollar1Dispenser implements DispenseChain {

	private DispenseChain chain;
	
	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain=nextChain;
	}

	@Override
	public void dispense(Currency cur) {
		if(cur.getAmount() >= 1){
			int num = cur.getAmount()/1;
			int remainder = cur.getAmount() % 1;
			System.out.println("Dispensing "+num+" 1$ note");
			if(remainder !=0) this.chain.dispense(new Currency(remainder));
		}else{
			this.chain.dispense(cur);
		}
	}

}
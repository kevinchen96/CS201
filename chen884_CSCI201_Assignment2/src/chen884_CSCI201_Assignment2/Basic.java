package chen884_CSCI201_Assignment2;

public class Basic extends SavingsAccount{

	public Basic(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected double getBalanceAfterNumYears(int numYears) {
		// TODO Auto-generated method stub
		return super.getBalance() *(Math.pow(1.001, numYears));
	}

	@Override
	public String getAccountType() {
		// TODO Auto-generated method stub
		return "Basic Savings";
	}

}

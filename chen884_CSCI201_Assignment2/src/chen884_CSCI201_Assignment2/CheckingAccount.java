package chen884_CSCI201_Assignment2;

public class CheckingAccount extends BaseAccount {

	public CheckingAccount(double balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double getBalanceAfterNumYears(int numYears) {
		// TODO Auto-generated method stub
		return super.getBalance();
	}

	@Override
	public String getAccountType() {
		// TODO Auto-generated method stub
		return "Checking";
	}

}

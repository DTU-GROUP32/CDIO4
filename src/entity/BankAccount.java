package entity;

public class BankAccount {

	private int balance;

	/**
	 * Default constructor.
	 */
	public BankAccount(){
		this(30000);
	}

	/**
	 * Constructor for the BankAccount class which takes an integer to set balance.
	 * @param balance Integer amount to instantiate object with
	 */
	public BankAccount(int balance){
		if (balance < 1)
			this.balance = 30000;
		else
			this.balance = balance;
	}
	
	/**
	 * Withdraws money from the BankAccount.
	 * @param amount to withdraw.
	 * @return amount actually withdrawn.
	 */
	public int withdraw(int amount){
		if (this.balance >= amount)
		{
			this.balance -= amount;
			return amount;
		}
		else
		{
			int remainder = this.balance;
			this.balance = 0;
			return remainder;
		}
	}
	
	/**
	 * Deposits money to the BankAccount.
	 * @param amount to deposit.
	 */
	public void deposit(int amount){
		this.balance += amount;
		if (amount < 0)
			this.balance = 0;
	}

	/**
	 * Get the current balance of the BankAccount.
	 * @return the current balance.
	 */
	public int getBalance()
	{
		return this.balance;
	}
}

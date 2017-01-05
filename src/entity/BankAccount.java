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
	 * Withdraws money from the BankAccount if player has the required balance
	 * @param amount to withdraw
	 * @return boolean - whether the withdrawal has been completed or not
	 */
	public boolean withdraw(int amount){
		if (this.balance >= amount)
		{
			this.balance -= amount;
			return true;
		}
		return false;
	}
	
	/**
	 * Deposits money to the BankAccount.
	 * @param amount to deposit.
	 */
	public void deposit(int amount){
		this.balance += amount;
	}
	
	/**
	 * Transfers amount to receiver from the player if player has the required balance
	 * @param receiver to receive the amount
	 * @param amount to transfer
	 * @return boolean - whether the transfer has been completed or not
	 */
	public boolean transfer(Player receiver, int amount){
		if(this.balance - amount > 0)
		{
			this.withdraw(amount);
			receiver.getBankAccount().deposit(amount);
			return true;
		}
		return false;
	}

	/**
	 * Returns the current balance of the BankAccount.
	 * @return balance - the current balance.
	 */
	public int getBalance() {
		return this.balance;
	}
	
	/**
	 * Changes the balance to new balance
	 * @param balance - the new balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
}

package sef.module8.activity;


/**
 * Thsi class represents a simple representation of an account encapsulating a
 * name
 * 
 * @author John Doe
 *
 */
public class Account {

	private String name;

	/**
	 * Creates an Account object with the specified name. If the accout name given
	 * violates the minimum requirements, then an AccountException is thrown
	 * 
	 * @param accountName
	 * @throws AccountException
	 */
	
	private static String ALPHA_STRING_REGEX = ".*[A-Za-z]*";
	private static String NUMERICAL_REGEX = ".*[0-9]*";
	
	public Account(String accountName) throws AccountException {

		if ( accountName.length()<= 4) {
			throw new AccountException(AccountException.NAME_TOO_SHORT, accountName);
		}

		if (!(accountName.matches(Account.ALPHA_STRING_REGEX)
				&& accountName.matches(Account.NUMERICAL_REGEX))) {
			throw new AccountException(AccountException.NAME_TOO_SIMPLE, accountName);
		}

		this.name = accountName;

	}

	/**
	 * Returns the account name
	 * 
	 * @return the account name
	 */
	public String getName() {
		return this.name;
	}

}

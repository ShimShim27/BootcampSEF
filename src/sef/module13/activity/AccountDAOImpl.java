package sef.module13.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
	private static String TABLE_NAME = "account";
	private Connection conn;

	public AccountDAOImpl(Connection conn) {
		this.conn = conn;

	}

	public List<Account> findAccount(String firstName, String lastName) throws AccountDAOException {

		try {
			List<Account> accounts = new ArrayList<>();

			String query = String.format("SELECT * FROM %s WHERE first_name=? AND last_name=?", TABLE_NAME);
			ResultSet resultSet = fetchData(query, firstName, lastName);
			while (resultSet.next()) {
				Account account = createAccountInstance(resultSet);
				accounts.add(account);
			}

			return accounts;
		} catch (Exception e) {
			throw new AccountDAOException(AccountDAOException.ERROR_FIND_NAME, e);
		}

	}

	public Account findAccount(int id) throws AccountDAOException {

		try {
			Account account = null;
			String query = String.format("SELECT * FROM %s WHERE id=?", TABLE_NAME);
			ResultSet resultSet = fetchData(query, String.valueOf(id));
			while (resultSet.next()) {
				account = createAccountInstance(resultSet);
			}
			return account;

		} catch (Exception e) {
			throw new AccountDAOException(AccountDAOException.ERROR_FIND_ID, e);
		}

	}

	public boolean insertAccount(String firstName, String lastName, String email) throws AccountDAOException {

		try {
			boolean isSuccessful = false;
			String query = String.format("INSERT INTO %s (first_name, last_name, e_mail) VALUES(?, ?, ?)", TABLE_NAME);

			isSuccessful = executeQuery(query, firstName, lastName, email);
			return isSuccessful;
		} catch (Exception e) {
			throw new AccountDAOException(AccountDAOException.ERROR_INSERT_ACCOUNT, e);
		}

	}

	private PreparedStatement prepareTheStatement(String query, String... args) throws SQLException {
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		for (int i = 1; i <= args.length; i++) {
			preparedStatement.setString(i, args[i - 1]);
		}
		return preparedStatement;
	}

	private ResultSet fetchData(String query, String... args) throws SQLException {
		return prepareTheStatement(query, args).executeQuery();
	}

	private boolean executeQuery(String query, String... args) throws SQLException {
		return prepareTheStatement(query, args).execute();
	}

	private Account createAccountInstance(ResultSet resultSet) throws SQLException {
		return new AccountImpl(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
				resultSet.getString(4));
	}

}

package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Riembursements;
//import model.users;

public class RiembDAO {
	private final String url = "jdbc:postgresql://database-2.c23jiwlalyxf.us-east-2.rds.amazonaws.com:5432/aws";
	private final String user = "postgres";
	private final String password = "pizza1234";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO reimbursement" + "  (amount, description, type) VALUES "
			+ " (?, ?, ?);";


	private static final String SELECT_USER_BY_ID = "select id,amount,description,type from reimbursement where id =?";
	private static final String SELECT_ALL_USERS = "select * from reimbursement where reimb_status = 0";
	private static final String DELETE_USERS_SQL = "delete from reimbursement where id = ?;";
	private static final String UPDATE_USERS_SQL = "update reimbursement set amount = ?,description= ?, type =?::type,date=? where id = ?;";

	private static final String SELECT_ALL_APPROVED = "select * from reimbursement where reimb_status = 1;";
	private static final String SELECT_ALL_DENIED = "select * from reimbursement where reimb_status = 2;";
	private static final String SELECT_ALL = "select * from reimbursement;";
	
	private static final String APPROVE_USERS_SQL = "update reimbursement set reimb_status=1 where id = ?  ;";
	private static final String DENY_USERS_SQL = "update reimbursement set reimb_status=2 where id = ?  ;";

	
	private static final String LOGIN = "select * from ers_user where username = ? and password = ? ";
	public RiembDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	/*
	  public boolean validate(users loginBean) throws ClassNotFoundException {
	        boolean status = false;

	        		System.out.println("Working");
	        		// try-with-resource statement will auto close the connection.
	        		try (Connection connection = getConnection();
	        				PreparedStatement preparedStatement = connection.prepareStatement(LOGIN)) {
	        			preparedStatement.setString(1, loginBean.getUsername());
	        			preparedStatement.setString(2, loginBean.getPassword());
	        			System.out.println(preparedStatement);
	        			 ResultSet rs = preparedStatement.executeQuery();
	        	            status = rs.next();
	        		} catch (SQLException e) {
	        			printSQLException(e);
	        		}
					return status;
	    }
*/
	public void insertUser(Riembursements riembursements) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setInt(1, riembursements.getAmount());
			preparedStatement.setString(2, riembursements.getDescription());
			preparedStatement.setString(3, riembursements.getType());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Riembursements selectUser(int id) {
		Riembursements riembursements = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int amount = rs.getInt("amount");
				String description = rs.getString("description");
				String type = rs.getString("type");
				riembursements = new Riembursements(id, amount, description, type, null);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return riembursements;
	}
	
	public List<Riembursements> selectAll() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Riembursements> riembursements = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int amount = rs.getInt("amount");
				String description = rs.getString("description");
				String type = rs.getString("type");
				riembursements.add(new Riembursements(id, amount, description, type, null));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return riembursements;
	}
	

	public List<Riembursements> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Riembursements> riembursements = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int amount = rs.getInt("amount");
				String description = rs.getString("description");
				String type = rs.getString("type");
				Date time = rs.getDate("time");
				riembursements.add(new Riembursements(id, amount, description, type,time));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return riembursements;
	}
	
	public List<Riembursements> selectApproved() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Riembursements> riembursements = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APPROVED);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int amount = rs.getInt("amount");
				String description = rs.getString("description");
				String type = rs.getString("type");
				Date time = rs.getDate("time");
				riembursements.add(new Riembursements(id, amount, description, type, time));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return riembursements;
	}
	
	public List<Riembursements> selectDenied() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Riembursements> riembursements = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DENIED);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int amount = rs.getInt("amount");
				String description = rs.getString("description");
				String type = rs.getString("type");
				Date time = rs.getDate("time");
				riembursements.add(new Riembursements(id, amount, description, type, time));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return riembursements;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean ApproveUser(int id) throws SQLException {
		boolean reimAprov;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(APPROVE_USERS_SQL);) {
			statement.setInt(1, id);
			reimAprov = statement.executeUpdate() > 0;
		}
		return reimAprov;
	}

	public boolean DenyUser(int id) throws SQLException {
		boolean reimAprov;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DENY_USERS_SQL);) {
			statement.setInt(1, id);
			reimAprov = statement.executeUpdate() > 0;
		}
		return reimAprov;
	}
	
	public boolean updateUser(Riembursements riembursements) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setInt(1, riembursements.getAmount());
			statement.setString(2, riembursements.getDescription());
			statement.setString(3, riembursements.getType());
			statement.setInt(4, riembursements.getId());
			statement.setInt(5, riembursements.getStatus());
			statement.setDate(6,riembursements.getTime());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
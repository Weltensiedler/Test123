package backend.mysql;

/**
 * Imports all entries from the java.sql library. 
 * Improve on this by only importing needed classes. 
 * E.g.:
 * import java.sql.Connection
 * import java.sql.Statement
 * ...
 */
import java.sql.*;

public class Main {
	static Connection con = null;
	static Statement stt = null;
	static PreparedStatement prep;
	static ResultSet res;

	static String url = "jdbc:mysql://localhost:3306/";
	static String user = "root";
	static String password = "";

	static String usruuid = null;
	static String usrname = null;
	float balance = 0;

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			stt = con.createStatement();

			// Create and select a database for use.
			switchToDB();

			// Create Table 'Money' if not exists
			createTable();

			// Create user on first join
			createNewUser();

			// Return DB values
			printToConsole();

			System.out.println("");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void createNewUser() throws SQLException {
		/**
		 * onFirstJoin Add entries to the example table
		 */
		stt.execute(
				"INSERT INTO money (USRNAME, USRUUID) VALUES('CursedCookiie', '9e552922-cb34-4fee-af3b-d41f7bb95470')");

	}

	public static void createTable() throws SQLException {
		/**
		 * Create an example table
		 */
		// stt.execute("DROP TABLE IF NOT EXISTS money");
		stt.execute("CREATE TABLE IF NOT EXISTS `money` ( `USRUUID` VARCHAR(36) NOT NULL COMMENT 'UUID' ,"
				+ " `USRNAME` VARCHAR(16) NOT NULL COMMENT 'USERNAME' , `CHGDAT` TIMESTAMP on update"
				+ " CURRENT_TIMESTAMP NOT NULL COMMENT 'DATE OF LAST CHANGE' , `BALANCE` FLOAT NOT NULL"
				+ " DEFAULT '50' COMMENT 'USERS MONEY' , PRIMARY KEY (`USRUUID`) ) ENGINE = InnoDB;");
	}

	public static void getByUUID() throws SQLException {
		res = stt.executeQuery("SELECT * FROM money WHERE usruuid = '9e552922-cb34-4fee-af3b-d41f7bb95470'");
	}

	public static void closeAll() throws SQLException {
		if (res != null) {
			res.close();
		}
		if (stt != null) {
			stt.close();
		}
		if (prep != null) {
			prep.close();
		}
		if (con != null) {
			con.close();
		}
	}

	public static void switchToDB() throws SQLException {
		stt.execute("CREATE DATABASE IF NOT EXISTS weltensiedler");
		stt.execute("USE weltensiedler");
	}

	public static void prepStatement() throws SQLException {
		/**
		 * Same as the last query, but using a prepared statement. Prepared
		 * statements should be used when building query strings from user input
		 * as they protect against SQL injections
		 */
		prep = con.prepareStatement("SELECT * FROM people WHERE lname = ?");
		prep.setString(1, "Bloggs");

		res = prep.executeQuery();
		while (res.next()) {
			System.out.println(res.getString("fname") + " " + res.getString("lname"));
		}
	}

	public static void printToConsole() throws SQLException {
		/**
		 * Query people entries with the lname 'Bloggs'
		 */
		res = stt.executeQuery("SELECT * FROM money WHERE usrname = 'CursedCookiie'");

		/**
		 * Iterate over the result set from the above query
		 */
		while (res.next()) {
			System.out.println(res.getString("usruuid") + " " + res.getString("usrname"));
		}
	}
}

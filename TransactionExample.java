import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionExample {

	private static final String SQL_INSERT = "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";

	private static final String SQL_UPDATE = "UPDATE EMPLOYEE SET SALARY=? WHERE NAME=?";

	private static final String SQL_TABLE_CREATE = "CREATE TABLE EMPLOYEE" + "(" + " ID serial,"
			+ " NAME varchar(100) NOT NULL," + " SALARY numeric(15, 2) NOT NULL,"
			+ " CREATED_DATE timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP," + " PRIMARY KEY (ID)" + ")";

	private static final String SQL_TABLE_DROP = "DROP TABLE EMPLOYEE";

	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Test", "postgres",
					"9850575150");
			System.out.print(connection);
			
			Statement statement = connection.createStatement();
			
			PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);
			PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
			statement.execute(SQL_TABLE_CREATE);
			
			
			
			connection.setAutoCommit(false);
			
			
			psInsert.setString(1, "ShreyashW");
			psInsert.setBigDecimal(2,new BigDecimal(430000));
			psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			psInsert.execute();
			
			psInsert.setString(1, "Kungfu");
			psInsert.setBigDecimal(2,new BigDecimal(20));
			psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			psInsert.execute();
			//statement.execute(SQL_TABLE_DROP);
			
			connection.commit();
			connection.setAutoCommit(true);
			
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

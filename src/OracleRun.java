import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class OracleRun {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String db_ip, db_port, db_sid; 
		String db_user;
		String db_password;
		final String inputFile;
		
		System.err.print("DB IP: ");
		db_ip = in.readLine();
		
		System.err.print("DB port (e.g. 1521): ");
		db_port = in.readLine();
		
		System.err.print("DB SID: ");
		db_sid = in.readLine();
		
		System.err.print("DB User: ");
		db_user = in.readLine();
		
		System.err.print("DB Password: ");
		db_password = in.readLine();

		System.err.print("Input SQL file: ");
		inputFile = in.readLine();
		
		// "jdbc:oracle:thin:@IP:PORT:SID";
		String db_url = String.format("jdbc:oracle:thin:@%s:%s:%s", db_ip, db_port, db_sid);
		System.err.println("DB URL: " + db_url);

		try {
			Connection conn;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(db_url, db_user, db_password);
			
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			int count = 0;
			while (true) {
				count++;
				Statement stmt;
				ResultSet rs;
				
				// String query = "select * from foo";
				String query = br.readLine();
				if (query == null) break;
				
				System.err.println(count + " " + query);
				
				stmt = conn.createStatement();

				rs = stmt.executeQuery(query);

				while (rs.next()) {
					int numCol = rs.getMetaData().getColumnCount();
					for (int i = 1; i <= numCol; i++) {
						if (i > 1)
							System.out.print("\t");
						System.out.print(rs.getString(i));
					}
					System.out.println();
				}
				stmt.close();
				rs.close();				
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}

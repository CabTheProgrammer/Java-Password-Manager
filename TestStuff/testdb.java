// import java.sql.Connection;
// import java.sql.DatabaseMetaData;
// import java.sql.DriverManager;
// import java.sql.SQLException;
import java.sql.*;

//Taken from the tutorial 
/* public class testdb{
    public static void createNewDatabase(String fileName){
        String url = "jdbc:sqlite:C:/sqlite/" + fileName;

        try{
            Connection conn = DriverManager.getConnection(url);
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("THe driver name is: "+ meta.getDriverName());
                System.out.println("Database has been created!");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String args[])
    {
        createNewDatabase("Test.db");
    }
} */

//This creates an empty database file
// public class testdb{
//     public static void main(String args[]){
//         Connection c = null;

//         try{
//             Class.forName("org.sqlite.JDBC");
//             c = DriverManager.getConnection("jdbc:sqlite:test.db");
//         }catch(Exception e){
//             System.err.println(e.getClass().getName() + ":" + e.getMessage() );
//             System.exit(0);
//         }
//         System.out.println("The database has somehow been created!");
//     }
// }


//Creates the database with a table called user with the columns userid, username and password
// public class testdb{
//     public static void main(String args[]){
//         Connection c = null;
//         Statement stmt = null;

//         try{
//             Class.forName("org.sqlite.JDBC");
//             c = DriverManager.getConnection("jdbc:sqlite:testdb");
//             System.out.println("The database has somehow been opened");

//             stmt = c.createStatement();
//             String sql = "CREATE TABLE USER "+ "(USERID INT NOT NULL, "+"USERNAME TEXT NOT NULL,"+ "PASSWORD TEXT NOT NULL)";
//             stmt.executeUpdate(sql);
//             stmt.close();
//             c.close();
            
//         } catch (Exception e) {
//             System.err.println(e.getClass().getName()+":"+e.getMessage());
//             System.exit(0);
//         }
//         System.out.println("Database created with tables somehow?");
//     }
// }

//This section is how to insert records into the database
public class testdb{
    public static void main(String args[]){
        Connection c = null;
        Statement stmt = null;
        String DATABASE_NAME = "test.db";

        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
            c.setAutoCommit(false);
            System.out.println("Database opened up somehow");

            stmt = c.createStatement();//Stage 1 of running a SQL statement

            String sql = "CREATE TABLE IF NOT EXISTS USERS"+ "(USERID INT NOT NULL, "+"USERNAME TEXT NOT NULL,"+ "PASSWORD TEXT NOT NULL)";     
            stmt.executeUpdate(sql);  //Stage 2 of running a SQL statement
            
            System.out.println("table created");

            sql = "INSERT INTO USERS"+"(USERID,USERNAME,PASSWORD)"+"VALUES (1, 'BACONATOR', '24xSDDw');";
            stmt.executeUpdate(sql);

            stmt.close(); //Third and final stage, very important
            c.commit();
            c.close();        
        }catch(Exception e){
            System.err.print(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("Records have been added to file somehow");
    }

}
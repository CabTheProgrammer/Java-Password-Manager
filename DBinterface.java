// java -classpath ".;sqlite-jdbc-3.32.3.2.jar" DBinterface.java RUN THIS CODE IN CMD TO EXECUTE

import java.sql.*;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

import java.io.File;

public class DBinterface {
    static String dbName = "Accounts.db";
    static String[] columnNames = {"ProfileName","MasterPassword","UserName","Password","AccountID"};
    
    
    static void init()//This function will initialize a blank database if none is detected.
	{
        System.out.println("Be Strong in the Lord");

        Connection c = null;
        Statement stmt = null;
        // String sqlstmt = "CREATE TABLE USERPROFILES [IF NOT EXISTS] ("+columnNames[0]+" char(128),"+columnNames[1]+"char(128));";
        String sqlstmt = "CREATE TABLE USERPROFILES(ROWID INTEGER PRIMARY KEY AUTOINCREMENT,"+columnNames[0]+" char(128),"+columnNames[1]+" char(128) UNIQUE);"+
                         "CREATE TABLE ACCOUNTS("+columnNames[2]+" char(128), "+columnNames[3]+" char(128), "+columnNames[4]+" integer,"+
                         "FOREIGN KEY (AccountID) REFERENCES USERPROFILES (ROWID) ON DELETE CASCADE);";
                         
        
        

        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName);
            stmt = c.createStatement();
            stmt.executeUpdate(sqlstmt);
            stmt.close();
            c.close();
            
            System.out.println("Database has been initialized!");

        }catch(Exception e){
				System.err.println(e.getClass().getName() + ":" + e.getMessage());
				System.exit(0);
	
            }
        
        
    }
    
     static boolean dbCheck(String dbName){//checks if a database exists or not; returns true if it exists
      File Q = new File(dbName);
      return Q.exists();     
    }

    static void addUserProfile(String ProfileName, String MasterPassword ){ //Takes arguments and add them to the database
        //Apparently, you need to have sting literals delimited by  '' in order for it to be parsed by sqlite.
        ProfileName = ("'"+ProfileName+"'");
        MasterPassword = ("'"+MasterPassword+"'"); //That is what I did here in order for it to work

        Connection c = null;
        Statement stmt = null;
        String sqlstmt = "INSERT INTO USERPROFILES ("+columnNames[0]+","+columnNames[1]+") VALUES("+ProfileName+","+MasterPassword+");";

        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName);
            stmt = c.createStatement();

            stmt.execute(sqlstmt);
            stmt.close();
            c.close();

            System.out.println("Database has been updated!");
        }catch(Exception e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
    }

    public static void addAccount(String MasterPassword, String UserName, String Password) //This function adds an account from 
    {
        MasterPassword = ("'"+MasterPassword+"'");
        UserName = ("'"+UserName+"'");
        Password = ("'"+Password+"'");

        String sqlstmt = "SELECT ROWID FROM USERPROFILES ROWID WHERE MASTERPASSWORD = "+MasterPassword+";";
        //Remember that the accounts table uses the rowid of the userprofile as the foreign key

        Connection c = null;
        Statement stmt = null;
        

        try{
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:"+dbName);
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sqlstmt);
        System.out.println(MasterPassword +" has a rowdid of :"+ rs.getInt("rowid"));

        //Now will use the rowid to associate the useraccount and password with the masterpassword
        sqlstmt = "INSERT INTO "; 

        stmt.close();
        c.close();

        }catch(Exception e){
            System.err.println(e.getClass().getName() + ":"+e.getMessage());
            System.exit(0);
        }
    }
   public static void main(String[] args){
       //Important step to check if the database file exists or not!
        if(dbCheck(dbName)==false)
        init();

		addUserProfile("Joe", "Bidenfor2021");		   
        addAccount("Bidenfor2021","Bill","Password");

       
   }
}


 
import java.util.Scanner; // This is needed to read input from the user
import db.*;
//import javax.lang.model.util.ElementScanner6;

public class Main{
	
	public static void helpdisp(){ // This helper function will display all the options available to the user.
		System.out.println("Usage: "+ APP_NAME + "  command...\n");
		System.out.println(APP_NAME + " '<del masterpassword>' in order to delete all records associated with that master password\n");
		System.out.println(APP_NAME + " '<add username password> site masterpassword' in order to create a new record\n");
		System.out.println(APP_NAME + " '<disp masterpassword>' to display all account data saved under a particular account name\n");
		System.out.println(APP_NAME + " '<ren oldmasterpassword newmasterpassword>' in order to change passwords\n");
	}

	public static boolean confirm(){ // This helper function displays a confirmation window
	
	Scanner Obj = new Scanner(System.in); //An object is created to take input from the user
			String Response = Obj.nextLine();

			if(Response.startsWith("Y")==true || Response.startsWith("y"))
					return true;
				else
				  System.exit(0);
				  return false;
	}

	//GLOBAL VARIABLES
	static String APP_NAME = "KeyKeep";
	static String[] ARGS = {"<add>","<username>","<password>"};
    
	public static void main(String[] args){
		db.DBinterface DB = new db.DBinterface();
		//Initializes DB if needed
		DB.init();
		
		//THIS IS THE HELP SECTION
		if(args.length == 0 )
			System.exit(1);
		// Quits program if no input is given

		if(args[0].compareToIgnoreCase("help") == 0){ // calls the help display 
			System.out.println(APP_NAME + " is a command line account database intended to help you in easily keeping track of your accounts");
			System.out.println("Here is a list of all the associated commands as well as a description of what they do:");
			helpdisp();
			System.exit(1);
		}
		else if(args.length != 3 && args[1].compareToIgnoreCase("del")==0){ // For erroneus input
			System.err.println("usage: "+ APP_NAME+" "+ ARGS[0]+" "+ARGS[1]+" "+ARGS[2] +" to create a new user account");
			System.out.println("Please enter "+ "\""+ APP_NAME + " help" + "\"" + "for help :)");
			System.exit(1);
		}

		switch(args[0]){ // This is where the different commands are processed
			case "del":
				System.out.println("Delete called");	
				System.out.println("Will delete all files associated with the password " + "\""+args[1]+"\"");
				System.out.println("Confirm [Y/N]?");
				if(confirm()==true)
				{
					//TODO: do sql statements here
					System.out.println("Confirmed");
					System.exit(0);
				}

			break;
		
			case "add":
				System.out.println("Addition called");
				System.out.println("The account with user name: " + args[1]+ " and password: "+ args[2]);
				System.out.println("will be added to the account with the master password: "+ args[3]);
				//TODO: do sql stuff here
			break;

			case "disp":
				System.out.println("Display called");
				System.out.println("All passwords associated with SINGLE master account will be displayed here");	
				//TODO: do sql stuff here
			break;

			case "ren":
			System.out.println("ren called");	
			System.out.println("The old password "+ args[1]+ "will be changed to : "+ args[2]);	
			if(confirm()==true)
			{
				//TODO: do sql stuff here
			}


			break;

			default:
			System.out.println("I thought this wouldnt show");
			break;

		}
	System.out.println("Welcome to "+ APP_NAME);
	
	// I need to now send to a class or something
	System.out.println("Here is the created account that will be added to file");
	System.out.println("Username: "+ args[0]);
	System.out.println("Password: "+"\""  + args[1]+"\"");
	System.out.println("MasterPassword: "+"\"" +args[2]+"\"");
	//TODO: CHECK IF THE DATABASE FILE EXISTS AND IF IT DOESN'T THEN CREATE IT AND INSIDE IT, CREATE A TABLE FOR THE USERS, WITH THE SITE, USERNAME AND PASSWORD
	
	
	//TODO: Do a check if the encrpted database file exists for given password. If it doesn't give notice that a new file will be created
	
	//TODO: check if the master password is the correct password before creating account 		object
	Account newAccount = new Account(args[0],args[1]);	
	//TODO: Decrypt a key file
	//TODO: Add this object to a file
	//TODO: Encrypt said file

	}

	
}

//Class for a single account
class Account{
	private String UserName;
	private String PassWord;

	public Account(String UserName, String PassWord){
	this.UserName = UserName;
	this.PassWord = PassWord;	
	}

//getters
	public String getUserName(){
	return this.UserName;
	}	

	public String getPassWord(){
	return this.PassWord;
	}	
	
}


package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class User implements Serializable{
	
    private static final long serialVersionUID = 123L;
 
    protected static final String COMPANY_FILE = "CompaniesObjectFile.ser";
	protected static final String CUSTOMER_FILE = "CustomersObjectFile.ser";
		
	private FileManager file;
	
	private String UserName;
	private String password;
	private int UserID;
	
	private ArrayList<Customer> customer;
	private ArrayList<Company> company;

	
	public void setUserName(String userName) {
		UserName = userName;
	}

	public void setUserNumber(String userPass) {
		password = userPass;
	}

	public void setUserID(int Parity) {
		 int min = 10000;
	        int max = 99990+Parity; 

	        Random random = new Random();
	        int randomNumber = random.nextInt(max - min + Parity) + min;

	        if (randomNumber % 10 != Parity) {
	            randomNumber += (Parity - (randomNumber % 10));
	        }
	        
	        UserID = randomNumber;
	}
	
	public void Manual_ID_Setter(int UserID) {
		this.UserID = UserID;
	}
	
	public String getUserName() {
		return UserName;
	}


	public String getUserNumber() {
		return password;
	}

	public int getUserID() {
		return UserID;
	}
	
	public void setCustomer() {
		customer = new ArrayList<>();
		String FName;
		FName = "CustomersObjectFile.ser";
		file = new FileManager(FName);

		//////////////////////////////////////////////////////////////////
		file.getFromFile(Customer.class, customer);
		//////////////////////////////////////////////////////////////////
	}

	public ArrayList<Customer> getCustomer() {
		return customer;
	}

	public void setCompany() {
		company = new ArrayList<>();
		String FName;
		FName = "CompaniesObjectFile.ser";
		file = new FileManager(FName);

		//////////////////////////////////////////////////////////////////
		file.getFromFile(Company.class, company);
		//////////////////////////////////////////////////////////////////
	}

	public ArrayList<Company> getCompany() {
		return company;
	}

	public String toString() {
		return getUserName()+" "+getUserNumber()+" "+getUserID();
		}
	



	
}
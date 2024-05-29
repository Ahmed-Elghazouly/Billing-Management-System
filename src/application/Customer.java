package application;

import java.util.ArrayList;
import java.util.Random;


public class Customer extends User {
	
    private static final long serialVersionUID = 123L;

	//public enum Gender {Male, Female}
	private static final FileManager file = new FileManager(CUSTOMER_FILE);
	
    private ArrayList<Company> CustomerCorp;


	private String FirstName;
	private String LastName;
	private String Gender;
	private double customerBalance;
	
	private String Birth_Date;
	
	
	public Customer() {};
	
	public Customer(String Fname, String Lname, String UserName,String password,String Birth_Date,String Gender) {
		this.setFName(Fname);
		this.setLName(Lname);
		this.setGender(Gender);
		this.setBirth_Date(Birth_Date);
		this.setUserName(UserName);
		this.setUserNumber(password);
		this.setUserID(1);
		this.setCustomerBalance();
		setCustomer();
		getCustomer().add(this);
		
		file.InsertInFile(Customer.class, getCustomer());
	}
	
	
	public void setCustomerBalance() {
		double max=1000000;
		double min=1000;
		Random rand=new Random();
		this.customerBalance = rand.nextDouble(max - min) + min;
	}
	
	public void setNewCustomerBalance(double newCusBalance) {
		customerBalance=newCusBalance;
	}
	

	public double getCustomerBalance() {
		return customerBalance;
	}

	// view all bills
	public ArrayList<Bill> listCustomerBills() { 
		
		FileManager file = new FileManager("Bill.ser");
		ArrayList<Bill> list = new ArrayList<>();

		ArrayList<Bill> BillList = new ArrayList<>();
		
		// new array list of bills with past and new bills
		file.getFromFile(Bill.class, BillList);

		for (Bill bill : BillList) {
			if (getUserName()==bill.getCustomerUserName()) {
				list.add(bill);
			}
		}
		return list;
	}

	// search for bills (any field)
	public void searchCustomerBill (String companyUserName,int billID) {
        ArrayList<Bill> custBillList = new ArrayList<>();
        FileManager file = new FileManager("Bill.ser");
       file.getFromFile(Bill.class, custBillList);
        for (int i = 0; i < custBillList.size(); i++) {
            if (getUserName().equals(custBillList.get(i).getCustomerUserName())
                    && billID == custBillList.get(i).getBillID()
                    && companyUserName.equals(custBillList.get(i).getCompanyUserName())) {
                System.out.println(custBillList.get(i).getCompanyUserName()+": "
                		+custBillList.get(i).billToString());
                break;
            }
        }
    }

	// pay bills
	public void payBills(String companyUserName,int billID) {
		  ArrayList<Bill> custBillList = new ArrayList<>();
	      FileManager file = new FileManager("Bill.ser");
	      file.getFromFile(Bill.class, custBillList);
	      
		for (Bill custBill : custBillList) {
			if (billID == custBill.getBillID()&&companyUserName.equals(custBill.getCompanyUserName())
								&&getUserName().equals(custBill.getCustomerUserName())) {
				if (custBill.getBillStatus() == "paid") { // chek if bill has been paid or not
					System.out.println("Bill " + custBill.getBillID() + " is already paid.");
					return;
				}

				double charge = custBill.getBillCharge();
				if (getCustomerBalance() < charge) {
					System.out.println("not enough balance to pay!");
					return;

				}
				double newCusBalance = getCustomerBalance() - charge; // deduct bill charge from customer balance
				setNewCustomerBalance(newCusBalance);
				String newStatus = custBill.getBillStatus();
				newStatus = "paid";
				custBill.setBillStatus(newStatus); // set bill status to paid
				System.out.println("Bill: " +custBill.getCompanyUserName()+": "+ custBill.billToString() + " was paid successfully.");
				System.out.println("Current Balance: " + newCusBalance);
				file.InsertInFile(Bill.class, custBillList);

				// file.InsertInFile(Bill.class, custBillList); //insert new array list into
				// file
				return;
			}
		}
		System.out.println("the bill was not found!");
		return;
	}

	public String getFName() {
		return FirstName;
	}

	public String getLName() {
		return LastName;
	}
	public void setFName(String Fname) {
		FirstName=Fname;
		
	}
	
	public void setLName(String Lname) {
		LastName=Lname;
		
	}

	public String getBirth_Date() {
		return Birth_Date;
	}

	public void setBirth_Date(String birth_Date) {
		Birth_Date = birth_Date;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public ArrayList<Company> getCustomerCorp() {
		return CustomerCorp;
	}

	public void setCustomerCorp() {
		FileManager file = new FileManager("Bill.ser");
		FileManager file2 = new FileManager(CUSTOMER_FILE);
		ArrayList<Bill> BillList = new ArrayList<>();
		ArrayList<Company> companies = new ArrayList<>();
		
		CustomerCorp = new ArrayList<>();

		// new array list of bills with past and new bills
		file.getFromFile(Bill.class, BillList);
		file2.getFromFile(Company.class, companies);
		
		if(BillList.isEmpty()) {
			return;
		}
		
		for(Bill bill: BillList) {
			if(getUserName()==bill.getCompanyUserName()) {
				for(Company cust : companies) {
					if(cust.getUserName()==bill.getCompanyUserName() && (!CustomerCorp.contains(cust))){
						CustomerCorp.add(cust);
					}
				}
			}
		}	
	}

}

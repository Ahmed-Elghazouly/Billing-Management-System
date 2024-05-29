package application;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Company extends User {
	
    private static final long serialVersionUID = 123L;
    
	private static final FileManager file = new FileManager(COMPANY_FILE);
    
    private String field; 
    
    private ArrayList<Customer> CorpCustomers;

	// add bill
    public Company() {}
    
    public Company(String UserName ,String Password ,String field) {
    	this.setField(field);
    	this.setUserName(UserName);
    	this.setUserNumber(Password);
		this.setUserID(2);
		
		setCompany();
		getCompany().add(this);
		
		file.InsertInFile(Company.class,getCompany());

    	
    }
public void addBill(String Username, double billCharge, String creationDate, String dueDate) {
    FileManager file = new FileManager("Bill.ser");

    ArrayList<Bill> compBillList = new ArrayList<>();

    file.getFromFile(Bill.class, compBillList);

    String[] sdateParts = creationDate.split("/");
    int sm = Integer.parseInt(sdateParts[0]);
    int sd = Integer.parseInt(sdateParts[1]);
    int sy = Integer.parseInt(sdateParts[2]);

    String[] edateParts = dueDate.split("/");
    int em = Integer.parseInt(edateParts[0]);
    int ed = Integer.parseInt(edateParts[1]);
    int ey = Integer.parseInt(edateParts[2]);

    Bill compBill = new Bill();
    compBill.setBillID();
    compBill.setBillCharge(billCharge);
    compBill.setBillCreationDate(sm, sd, sy);
    compBill.setBillDueDate(em, ed, ey);
    
    // Set the customerUserName for the bill
    compBill.setCustomerUserName(Username);
    
    compBill.setCompanyUserName(getUserName());

    compBillList.add(compBill);
    file.InsertInFile(Bill.class, compBillList);
}

	// edit bill
	public void editBill(String customerUserName, int billID) {
		FileManager file = new FileManager("Bill.ser");

		ArrayList<Bill> compBillList = new ArrayList<>();
		// new array list of bills with past and new bills
		file.getFromFile(Bill.class, compBillList);

		Scanner in = new Scanner(System.in);
		Scanner number = new Scanner(System.in);
		Scanner dNumber = new Scanner(System.in);

		if (compBillList != null) {
			int i;
			for (i = 0; i < compBillList.size(); i++) {
				if (compBillList.get(i).getBillID() == billID
						&& compBillList.get(i).getCompanyUserName().equals(getUserName())
						&& compBillList.get(i).getCustomerUserName().equals(customerUserName)) {
					break;
				}

			}
			System.out.println("\tEnter field you want to edit: (1)customer username, (2)due date, (3)charge\t ");
			int field=0;
			field = number.nextInt();
			switch (field) {
			case 3:
				System.out.println("Enter new value of bill charge: ");
				double newCharge = dNumber.nextDouble();
				compBillList.get(i).setBillCharge(newCharge);
				file.InsertInFile(Bill.class, compBillList);
				break;
			case 2:
				System.out.println("Enter new value of bill due date: ");
				String start = in.next();
				String[] partsI = start.split("/");
				int sm, sd, sy;
				sm = Integer.parseInt(partsI[0]);
				sd = Integer.parseInt(partsI[1]);
				sy = Integer.parseInt(partsI[2]);
				compBillList.get(i).setBillDueDate(sm, sd, sy);
				file.InsertInFile(Bill.class, compBillList);
				break;
			case 1:
				System.out.println("Enter new customer's name: ");
				String custUser = in.nextLine();
				compBillList.get(i).setCustomerUserName(custUser);
				file.InsertInFile(Bill.class, compBillList);


			default:
				System.out.println("Invalid choice");
				in.close();
				number.close();
				dNumber.close();

			}
		}

	}

	// list bills
	public ArrayList<Bill> listCompanyBills() {
		FileManager file = new FileManager("Bill.ser");
		ArrayList<Bill> list = new ArrayList<>();

		ArrayList<Bill> BillList = new ArrayList<>();
		// new array list of bills with past and new bills
		file.getFromFile(Bill.class, BillList);

		for (Bill bill : BillList) {
			if (getUserName()==(bill.getCompanyUserName())) {
				list.add(bill);
			}
		}
		return list;
	}
	
	public void setCorpCustomers(){
		FileManager file = new FileManager("Bill.ser");
		FileManager file2 = new FileManager(CUSTOMER_FILE);
		ArrayList<Bill> BillList = new ArrayList<>();
		ArrayList<Customer> customers = new ArrayList<>();
		
		CorpCustomers = new ArrayList<>();

		// new array list of bills with past and new bills
		file.getFromFile(Bill.class, BillList);
		file2.getFromFile(Customer.class, customers);
		
		if(BillList.isEmpty()) {
			return;
		}
		
		for(Bill bill: BillList) {
			if(getUserName()==bill.getCompanyUserName()) {
				for(Customer cust : customers) {
					if(cust.getUserName()==bill.getCustomerUserName() && (!CorpCustomers.contains(cust))){
						CorpCustomers.add(cust);
					}
				}
			}
		}	
	}
	
	public ArrayList<Customer> getCorpCustomers(){
		return CorpCustomers;
	}

	// search for bill
	public void searchCompanyBill(String customerUserName, int billID) {
		boolean f = false;
		FileManager file = new FileManager("Bill.ser");
		ArrayList<Bill> BillList = new ArrayList<>();
		file.getFromFile(Bill.class, BillList);
		for (int i = 0; i < BillList.size(); i++) {
			if (getUserName().equals(BillList.get(i).getCompanyUserName()) && billID == BillList.get(i).getBillID()
					&& customerUserName.equals(BillList.get(i).getCustomerUserName())) {
				System.out.println(BillList.get(i).getCustomerUserName() + ": " + BillList.get(i).billToString());
				f = true;
				break;
			}
		}
		if (f == false) {
			System.out.println("the desired bill doesn't exsist!");
		}
	}

	// remove bills
	public void removeBill(/* String customerUserName, */int billID) {
		FileManager file = new FileManager("Bill.ser");

		ArrayList<Bill> BillList = new ArrayList<>();
		file.getFromFile(Bill.class, BillList);

		for (int i = 0; i < BillList.size(); i++) {
			if (getUserName().equals(BillList.get(i).getCompanyUserName()) && billID == BillList.get(i).getBillID()) {
				BillList.remove(i);
			}
		}
		file.InsertInFile(Bill.class, BillList);
	}

	// no. of bills made & paid over specific time //to be edited
	public void numOfBills(String startTimePeriod, String endTimePeriod) {
		ArrayList<Bill> billList = new ArrayList<>();
		// Example dates in the format "YYYY-MM-DD"

		// Parse the strings into LocalDate objects
		LocalDate startDate = LocalDate.parse(startTimePeriod, DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(endTimePeriod, DateTimeFormatter.ISO_DATE);

		// Calculate the duration between the dates
		Duration duration = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay());

		if (billList.isEmpty()) { // check if bills are avaiable in list
			System.out.println("No bills available for calculation.");
			return;
		}

		int totalNumOfBillsMade = 0, totalNumOfBillsPaid = 0;
		if (!billList.isEmpty()) {
			for (int i = 0; i < billList.size(); i++) {
				if (startTimePeriod == billList.get(i).getBillCreationDate()
						&& endTimePeriod == billList.get(i).getBillDueDate()) {
					totalNumOfBillsMade++;
				}
				if (billList.get(i).getBillStatus() == "paid") {
					totalNumOfBillsPaid++;
				}
			}
		}

		else {
			System.out.println("No bills avaiable for current time period.");
		}

		System.out.println("Total number of bills made from " + startTimePeriod + " to " + endTimePeriod + " is: "
				+ totalNumOfBillsMade);
		System.out.println("Total number of bills paid from " + startTimePeriod + " to " + endTimePeriod + " is: "
				+ totalNumOfBillsPaid);

	}

	public int getNoOfBills() {
		FileManager file = new FileManager("Bill.ser");
		ArrayList<Bill> BillList = new ArrayList<>();
		file.getFromFile(Bill.class, BillList);
		
		int count=0;
		
		for (int i = 0; i < BillList.size(); i++) {
			if (getUserName().equals(BillList.get(i).getCompanyUserName())) {
				count++;
			}
		}
		return count;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}

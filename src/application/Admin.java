
package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User {

	private static final long serialVersionUID = 123L;

	private FileManager file;

	private static final Admin admin = new Admin();

	private static final void addAdmin() {
		admin.setUserName("Alice");
		admin.Manual_ID_Setter(12340);
		admin.setUserNumber("Alice007");
	}

	public void AddCustomer(String fname, String lname, String UserName, String UserPass, String BirthDate, String Gender) {

		file = new FileManager(CUSTOMER_FILE);

		Customer customer = new Customer();
		customer.setFName(fname);
		customer.setLName(lname);
		customer.setUserName(UserName);
		customer.setUserNumber(UserPass);
		customer.setUserID(1);
		customer.setBirth_Date(BirthDate);
		customer.setGender(Gender);
		customer.setCustomerBalance();

		setCustomer();
		getCustomer().add(customer);

		file.InsertInFile(Customer.class, getCustomer());
	}

	public void AddCompany(String userName, String userPass, String field) {

		file = new FileManager(COMPANY_FILE);

		Company corp = new Company();
		corp.setUserName(userName);
		corp.setUserNumber(userPass);
		corp.setUserID(2);
		corp.setField(field);

		setCompany();
		getCompany().add(corp);

		file.InsertInFile(Company.class, getCompany());

	}

	public void edit(String userName, int userID) {
		ArrayList<User> user = null;
		String FName = null;
		String entity = null;

		String username;
		int userChoice = 0;

		Scanner number = new Scanner(System.in);
		Scanner name = new Scanner(System.in);

		FileManager file = null;
		if (userID == 1) {
			setCustomer();
			FName = "CustomersObjectFile.ser";
			entity = "customer";
			file = new FileManager(FName);
			user = new ArrayList<>(getCustomer());
			System.out.println("enter 1 to edit the name || enter 2 to edit the number");
			try {
				userChoice = number.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("please enter a number from 1 or 2");
				number.next(); // Consume the invalid input to avoid an infinite loop
				userChoice = number.nextInt();
			}
			// System.out.println("enter the name of the" + entity);
			// username = name.nextLine();
			int i;
			if (userChoice == 1) {
				for (i = 0; i < getCustomer().size() - 1; i++) {
					if (userName.equals(getCustomer().get(i).getUserName())) {
						break;
					}
				}
				System.out.println("enter the new " + entity + "s name");
				username = name.nextLine();
				getCustomer().get(i).setUserName(username);
			} else if (userChoice == 2) {
				for (i = 0; i < user.size() - 1; i++) {
					if (userName.equals(getCustomer().get(i).getUserName())) {
						break;
					}
				}
				String x = null;
				System.out.println("enter the new " + entity + "s number");
				try {
					x = name.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("error! please enter a number");
					x = name.nextLine();
				}
				getCustomer().get(i).setUserNumber(x);
			}

			file.InsertInFile(Customer.class, getCustomer());

		} else if (userID == 2) {
			setCompany();
			FName = "CompaniesObjectFile.ser";
			entity = "company";
			file = new FileManager(FName);
			user = new ArrayList<>(getCompany());
			System.out.println("enter 1 to edit the name || enter 2 to edit the number");
			try {
				userChoice = number.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("please enter a number from 1 or 2");
				number.next(); // Consume the invalid input to avoid an infinite loop
				userChoice = number.nextInt();
			}
			// System.out.println("enter the name of the" + entity);
			// username = name.nextLine();
			int i;
			if (userChoice == 1) {
				for (i = 0; i < getCompany().size() - 1; i++) {
					if (userName.equals(getCompany().get(i).getUserName())) {
						break;
					}
				}
				System.out.println("enter the new " + entity + "s name");
				username = name.nextLine();
				getCompany().get(i).setUserName(username);
			} else if (userChoice == 2) {
				for (i = 0; i < user.size(); i++) {
					if (userName.equals(user.get(i).getUserName())) {
						break;
					}
				}
				String x = null;
				System.out.println("enter the new " + entity + "s number");
				try {
					x = name.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("error! please enter a number");
					x = name.nextLine();
				}
				getCompany().get(i).setUserNumber(x);
			}

			file.InsertInFile(Company.class, getCompany());
		}
	}

	public void remove(String userName, int userID) {
		setCustomer();
		file = new FileManager(CUSTOMER_FILE);

		for (Customer cust : getCustomer()) {

			if (userName.equals(cust.getUserName())) {
				getCustomer().remove(cust);
				file.InsertInFile(Customer.class, getCustomer());
				break;
			}
		}

		setCompany();
		file = new FileManager(COMPANY_FILE);

		for (Company corp : getCompany()) {

			if (userName.equals(corp.getUserName())) {
				getCompany().remove(corp);
				file.InsertInFile(Company.class, getCompany());
				break;
			}
		}
	}



	public User search(String userName, String password) {
		// SEARCH IS WORKING COMPLETLY FINE
		/**
		 * MIGHT BE CHANGING THE @Return TYPE TO @USER OBJECT FOR EASY STRING
		 * MANUPLATION
		 **/
		setCustomer();
		setCompany();

		addAdmin();
		if (userName.equals(admin.getUserName()) && password.equals(admin.getUserNumber())) {
			return admin;
		}

		for (Customer cust : getCustomer()) {
			if (userName.equals(cust.getUserName())
					&& (password.equals(cust.getUserNumber()))) {
				return cust;
			}
		}

		for (Company corp : getCompany()) {
			if (userName.equals(corp.getUserName())
					&& (password.equals(corp.getUserNumber()))) {
				return corp;
			}
		}

		return null;
	}

	private String maximumCompany(int key) {
		setCompany();
		FileManager file = new FileManager("Bill.ser");
		ArrayList<Bill> BillList = new ArrayList<>();
		file.getFromFile(Bill.class, BillList);
		Company company = null;
		int max = 0;
		if (key == 1) {
			for (Company comp : getCompany()) {
				if (max < comp.getNoOfBills()) {
					max = comp.getNoOfBills();
					company = comp;
				}
			}
			return company.toString();
		}
		return null;
	}

	private User maximumCustomer(ArrayList<Customer> bill) {

		return null;
	}

	public void CustBillInfo() {
		System.out.println(maximumCompany(1));

	}

	public void companyBillInfo() {
	}

	public boolean Main_Info_Checker(String MainInfo) {
		setCustomer();
		setCompany();

		for (Customer customer : getCustomer()) {
			if (MainInfo.equals(customer.getUserNumber())) {
				return true;
			}
		}

		for (Company company : getCompany()) {
			if (MainInfo.equals(company.getUserNumber())) {
				return true;
			}
		}

		for (Customer customer : getCustomer()) {
			if (MainInfo.equals(customer.getUserName())) {
				return true;
			}
		}

		for (Company company : getCompany()) {
			if (MainInfo.equals(company.getUserName())) {
				return true;
			}
		}

		return false;
	}
}
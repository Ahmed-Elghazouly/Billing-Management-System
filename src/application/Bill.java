package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class Bill implements Serializable { 
    private static final long serialVersionUID = 123L;

 //   private String billType;      //tax, water, utility, etc.
    private int billID;
    private double billCharge;
   // private int billCreationDateCalc;
   // private int billDueDateCalc;
   // private int month;
   // private int day;
   // private int year;
    private String customerUserName;
    private String companyUserName;
    private String billCreationDate;
    private String billDueDate;
    private String billStatus = "unpaid";


   /* public void setBillType (String billType) {
        this.billType = billType;
    }*/
    public void getBillCompanies() {
    	
    }
    public void setBillID () {
    	int Parity = 3;
    	 int min = 10000;
	        int max = 99990+Parity; 

	        Random random = new Random();
	        int randomNumber = random.nextInt(max - min + Parity) + min;

	        if (randomNumber % 10 != Parity) {
	            randomNumber += (Parity - (randomNumber % 10));
	        }
	        this.billID = randomNumber;
	 }

    public void setBillCharge (double billCharge) {
        this.billCharge = billCharge;
    }

    public void setBillCreationDate (int m,int d,int y) {
    	//month=m;
    	//day=d;
    	//year=y;
    	billCreationDate=m+"/"+d+"/"+y;
       // billCreationDateCalc = m+d+y;
    }
    
    public void setBillDueDate (int m,int d,int y) {
    	//month=m;
    	//day=d;
    	//year=y;
    	//billDueDateCalc = m+d+y;
    	billDueDate = m+"/"+d+"/"+y; 
    }
    
    public void setBillStatus (String billStatus) {
        this.billStatus = billStatus;
    }

   /* public String getBillType () {
        return billType;
    }*/

    public int getBillID () {
        return billID;
    }

    public double getBillCharge () {
        return billCharge;
    }

    public String getBillCreationDate () {
        return billCreationDate;
    }
    
    public String getBillDueDate() {
    	return billDueDate;
    }
    
	/*
	 * public int getBillCreationDateCalc() { return billCreationDateCalc; }
	 * 
	 * public int getBillDueDateCalc() { return billDueDateCalc; }
	 */
    public String getBillStatus()
    {
        return billStatus;
    }

    public String billToString() {
        return /*getBillType() + "/ " +*/ getBillID() + "/ " + getBillCharge() + "/ " + getBillCreationDate() + "/ " + getBillDueDate() + "/ " + getBillStatus();
    }

   
//  prints details about bill with all details.
   

    

//  Average/Total Revenue over a specific period of time and their details.
	/*
	 * public void avg_total_rev(int sm,int sd,int sy,int em,int ed,int
	 * ey,ArrayList<Bill> billList) { //ArrayList<Bill> billList = new
	 * ArrayList<>(); int sSum=sm+sd+sy; String Starts=sm+"/"+sd+"/"+sy; int
	 * eSum=em+ed+ey; String Ends=em+"/"+ed+"/"+ey;
	 * 
	 * if (billList.isEmpty()) { //check if bills are avaiable in list
	 * System.out.println("No bills available for calculation."); return; }
	 * 
	 * double totalRevenue = 0, averageRevenue = 0; for (Bill bill : billList) { if
	 * (sSum <= getBillCreationDateCalc() && eSum >= getBillDueDateCalc()) { //find
	 * the total revenue of all bills totalRevenue += bill.getBillCharge(); }
	 * 
	 * } averageRevenue = totalRevenue / billList.size(); //find the average of all
	 * bills System.out.println("Revenue Details from " + Starts + " to " + Ends);
	 * System.out.println("Total Revenue: $" + totalRevenue);
	 * System.out.println("Average Revenue: $" + averageRevenue);
	 * 
	 * 
	 * }
	 */




//search for bills (any field)
public ArrayList<Bill> searchBill (String field, String value,ArrayList<Bill> billList ) {

      
        ArrayList<Bill> results = new ArrayList<>();        //returns arraylist of found bill 

        for (Bill bill : billList) {
            switch (field) {
                /*case "type":
                    if (bill.getBillType().equals(value)) {
                        results.add(bill);
                        System.out.println(field + " found");
                    }
                    break;*/
                case "id":
                    if (String.valueOf(bill.getBillID()).equals(value)) {
                        results.add(bill);
                    }
                    break;
                case "charge":
                    if (String.valueOf(bill.getBillCharge()).equals(value)) {
                        results.add(bill);
                    }
                    break;
                case "creation date":
                    if (bill.getBillCreationDate().equals(value)) {
                        results.add(bill);
                    }
                    break;
                case "due date":
                    if (bill.getBillDueDate().equals(value)) {
                        results.add(bill);
                    }
                    break;
                case "status":
                    if (bill.getBillStatus().equals(value)) {
                        results.add(bill);
                    }
                    break;
                default:
                    System.out.println("Invalid field name");
                    break;
            }
        }
        return results;
    }

public String getCustomerUserName() {
	return customerUserName;
}

public void setCustomerUserName(String customerUserName) {
	this.customerUserName = customerUserName;
}

public String getCompanyUserName() {
	return companyUserName;
}

public void setCompanyUserName(String companyUserName) {
	this.companyUserName = companyUserName;
}





/*public void printCompDataIntoFile(ArrayList<Bill> compBillList) {
    try {
        FileWriter fw = new FileWriter("bill.txt", true);
        LineNumberReader reader = new LineNumberReader(new FileReader("bill.txt"));
        while (reader.readLine() != null);
            for (Bill b : compBillList) {
                 fw.write((reader.getLineNumber() + 1) + ". " + b.billToString() + "\n");
                fw.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
}*/

}


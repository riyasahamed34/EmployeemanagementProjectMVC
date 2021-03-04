package com.ideas2it.employeeManagementProject.view;

import com.ideas2it.employeeManagementProject.controller.EmployeeController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * EmployeeView Class for User Interface
 *
 * @author Riyas Ahamed
 * @version  2.0  02-03-2021
 */
public class EmployeeView {
    EmployeeController employeeController = new EmployeeController();
    Scanner scanner = new Scanner(System.in);
    
    /**
     * Selecting menuOption for operations
     */
    public void selectOption() {
	int menuOption = 0;
        int employeeId = 1;
	String menuDisplay = " 1.Create a NewEmployee \n 2.update the existing Employee " +
		"\n 3.show employee details \n 4.Delete the employee data" +
		"\n 5.showALL \n 6.Exit \n";
		
	while (6 != menuOption) {
    	    System.out.println("\n Enter the Options \n" + menuDisplay);
            menuOption = scanner.nextInt();
	    switch(menuOption) {
   		 case 1:		
	             addNewEmployeeDetails(employeeId);
	             System.out.println("\n your employee Id is " + employeeId);
    		     employeeId++;
     		     break;	
		 case 2:
   		     System.out.println("\n Enter the employee Id  to update the details" );
   		     employeeId = scanner.nextInt();
   		     updateEmployeeDetails(employeeId) ;
                     break;
		 case 3:
		     System.out.println("\n Enter the Employee Id");
		     employeeId = scanner.nextInt();
		     showEmployeeDetails(employeeId);
 		     break;
		 case 4:
                     System.out.println("\n Enter the Employee Id");
                     employeeId = scanner.nextInt();
                     deleteEmployeeDetails(employeeId);
                     break;
		 case 5:
                     showAll();
                     break;
                 case 6:
                     System.out.println("Thank you");
                     break;
                 default:
                     System.out.println("Enter a valid option");
		     break;
	    }
        }
    }			

    /**
     * Adding New Employee and Details
     */	
    private void addNewEmployeeDetails(int employeeId) {
	System.out.println("Enter firstName");
        String firstName = scanner.next();
        System.out.println("Enter lastName");
        String lastName = scanner.next();
        System.out.println("Enter Salary");
        int salary = scanner.nextInt();
        System.out.println("Enter Mobile Number");
        long mobileNumber = validateMobileNumber();
        System.out.println("Enter Date of Birth in the format ('dd/MM/yyyy')");  
        Date dateOfBirth = dateValidate();       
        employeeController.addNewEmployeeDetails( employeeId, firstName, lastName, salary, mobileNumber, dateOfBirth);
        System.out.println("\n EmployeeDetails added successfully");    
    }

    /**
     * updating Existing Employee and Details
     */
    private void updateEmployeeDetails(int employeeId) {

	if(employeeController.employeeIdPresent(employeeId)) {
	    System.out.println("Enter firstName");
            String firstName = scanner.next();
            System.out.println("Enter lastName");
            String lastName = scanner.next();
            System.out.println("Enter Salary");
            int salary = scanner.nextInt();
            System.out.println("Enter Mobile Number");
            long mobileNumber = validateMobileNumber();
            System.out.println(" Enter Date of Birth in the format ('dd/MM/yyyy')"); 
            Date dateOfBirth = dateValidate(); 
            employeeController.updateEmployeeDetails( employeeId, firstName, lastName, salary,
                    mobileNumber, dateOfBirth);
            System.out.println("\n EmployeeDetails Updated successfully");
        } else {
	    System.out.println("EmployeeId not present");
	}
    }

    /**
     * showing particular Employee Details
     */
    private void showEmployeeDetails(int employeeId) {
	System.out.println(employeeController.getEmployeeDetails(employeeId));
        if(null == employeeController.getEmployeeDetails(employeeId)) {
	    System.out.println("No record Found");
        } 
    }

    /**
     * Deleting particular Employee Details
     */
    private void deleteEmployeeDetails(int employeeId) {
	employeeController.deleteEmployee(employeeId);
    }

    /**
     * Showing All Employee Details
     */
    private void showAll() {
        List<String> employeeList = employeeController.getAll();
        for(String employeeId : employeeList) {
            System.out.println(employeeList);
        }
    }

    /**
     * Mobile Number Validation
     */
    private long  validateMobileNumber() {
	long mobileNumber = scanner.nextLong();
	mobileNumber = employeeController.validateMobileNumber(mobileNumber);
        if(0 == mobileNumber) {
            System.out.println("Invalid Mobile Number, Enter again");
 	    validateMobileNumber();	
        }
        return mobileNumber;		
    }

    /**
     * Date Validation
     */
    private Date  dateValidate() {
	String date = scanner.next();
	
	if (null == employeeController.dateValidate(date)) {
            return dateValidate();
	} else {
	    return employeeController.dateValidate(date);
	} 	
    }
}

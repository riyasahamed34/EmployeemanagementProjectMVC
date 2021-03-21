package com.ideas2it.employeeManagementProject.view;

import com.ideas2it.employeeManagementProject.controller.EmployeeController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * EmployeeView Class for User Interface
 *
 * @author RiyasAhamed
 * @version  2.0  02-03-2021
 */
public class EmployeeView {
    EmployeeController employeeController = new EmployeeController();
    Scanner scanner = new Scanner(System.in);

    /**
     * Selecting menuOption for operations 
     */
    public void selectOption()  {
        int menuOption = 0;
        int employeeId = checkEmployeeId();
        employeeId = employeeId + 1;
        String menuDisplay = " 1.Create a NewEmployee \n 2.update the existing Employee " +
                "\n 3.show employee details \n 4.Delete the employee data" +
                "\n 5.showALL \n 6.show deleted Data  \n 7.Exit \n";

        while (7 != menuOption) {
            System.out.println("\n Enter the Options \n" + menuDisplay);
            menuOption = scanner.nextInt();
            switch(menuOption) {
                case 1:	
                    addNewEmployeeDetails(employeeId);
                    System.out.println("\n your employee Id is " + employeeId);
                    employeeId++;
                    break;	
                case 2:
                    updateEmployeeDetails() ;
                    break;
                case 3:
                    showEmployeeDetails();
                    break;
                case 4:
                    deleteEmployeeDetails();
                    break;
                case 5:
                    showAll();
                    break;
                case 6:
                    showDeletedData();
                    break;
                case 7:
                    System.out.println("Thank you");
                    break;
                default:
                    System.out.println("Enter a valid option");
                    break;
            }
        }
    }			

    /**
     * Checking EmployeeId from DataBase
     */	
    private int checkEmployeeId() {
        return employeeController.checkEmployeeId();
    }

    /**
     * Adding New Employee and Details
     * 
     * @param employeeId
     */	
    private void addNewEmployeeDetails(int employeeId)  {
        System.out.println("Enter firstName");
        String firstName = scanner.next();
        System.out.println("Enter lastName");
        String lastName = scanner.next();
        System.out.println("Enter Salary");
        double salary = scanner.nextDouble();
        long mobileNumber = validateMobileNumber();  
        Date dateOfBirth = dateValidate(); 
        System.out.println("Enter primary address details");
        List<String[]> employeeAddressList = new ArrayList<>();
        employeeAddressList = addNewEmployeeAddress(employeeAddressList, employeeId);
        
        if(0 != employeeController.addNewEmployeeDetails( employeeId, firstName, lastName, salary,
                mobileNumber, dateOfBirth, employeeAddressList)) {
            System.out.println("Employee details added successfully");
        }
    }

    /**
     * Adding New Employee's Address
     * 
     * @param emloyeeAddressList
     * @param employeeId
     * 
     * @return employeeAddressList
     */
    private List<String[]> addNewEmployeeAddress(List<String[]> employeeAddressList, int employeeId) {
        System.out.println(" Enter door number");
        String doorNumber = scanner.next();
        scanner.skip(Pattern.compile("[\n\r]{2}"));
        System.out.println("Enter Street or road Name");
        String streetOrroadName = scanner.nextLine();
        System.out.println("Enter city");
        String city = scanner.next();
        System.out.println("Enter pincode");
        String pincode = scanner.next();
       
        scanner.skip(Pattern.compile("[\n\r]{2}"));
        System.out.println("Enter State");
        String state = scanner.nextLine();
        System.out.println("Enter AddressType");
        String addressType = scanner.next();
        String[] address = {doorNumber, streetOrroadName, city, pincode, state, addressType};
        employeeAddressList.add(address);
        
        System.out.println(" Do you want to add another address for this employee"
                + " \n 1.yes,  2.No");
        int option = scanner.nextInt();
        
        switch(option) {
            case 1 :
                System.out.println("Enter Secondary address details");
                addNewEmployeeAddress(employeeAddressList, employeeId);
                break;
            default :
                break;      
        }
        return employeeAddressList;
    }

    /**
     * updating Existing Employee and Details
     */
    private void updateEmployeeDetails() {
        System.out.println("\n Enter the employee Id  to update the details" );
        int  employeeId = scanner.nextInt();
        System.out.println("Enter firstName");
        String firstName = scanner.next();
        System.out.println("Enter lastName");
        String lastName = scanner.next();
        System.out.println("Enter Salary");
        double salary = scanner.nextDouble();
        long mobileNumber = validateMobileNumber();
        Date dateOfBirth = dateValidate(); 
       if(0 != employeeController.updateEmployeeDetails( employeeId, firstName, lastName, salary,
                mobileNumber, dateOfBirth)) {
            System.out.println("\n EmployeeDetails Updated successfully");
        }
        System.out.println("Do you Want to update Address details \n "
                + "Enter 1 to update \n Enter 2 to close ");
        int option = scanner.nextInt();
        
        switch(option) {
            case 1 :
                updateEmployeeAddress(employeeId);
                break;
            default :
                break; 
        }
    }
    
    /**
     * updating Existing Employee's Address
     * 
     * @param employeeId
     */
    public void updateEmployeeAddress(int employeeId) {
        System.out.println("Enter addressId to update");
        int addressId = scanner.nextInt();
        System.out.println(" Enter door number");
        String doorNumber = scanner.next();
        scanner.skip(Pattern.compile("[\n\r]{2}"));
        System.out.println("Enter Street or road Name");
        String streetOrroadName = scanner.nextLine();
        System.out.println("Enter city");
        String city = scanner.next();
        System.out.println("Enter pincode");
        int pincode = scanner.nextInt();
        scanner.skip(Pattern.compile("[\n\r]{2}"));
        System.out.println("Enter State");
        String state = scanner.nextLine();
        System.out.println("Enter AddressType");
        String addressType = scanner.next();
        
        if(0 != employeeController.updateEmployeeAddress(addressId,employeeId, doorNumber,
                streetOrroadName, city, pincode, state, addressType)) {
            System.out.println("Address updated Successfully");
        } else {
            System.out.println("Address not updated Successfully");
        }
        
        System.out.println(" If you Want to update another Address \n Enter 1 to update "
                + " \n Enter 2 to finish");
        int option = scanner.nextInt();

        switch(option) {
            case 1 :
                updateEmployeeAddress(employeeId);
                break;
            default :
                break; 
        } 
    }

    /**
     * showing particular Employee Details
     */
    private void showEmployeeDetails() {
        System.out.println("\n Enter the Employee Id");
        int employeeId = scanner.nextInt();
        System.out.println(employeeController.getEmployeeDetails(employeeId));  
    }

    /**
     * Deleting particular Employee Details
     */
    private void deleteEmployeeDetails() {
        System.out.println("\n Enter the Employee Id");
        int employeeId = scanner.nextInt();
        
        if (0 == employeeController.deleteEmployee(employeeId)) {
            System.out.println("Employee details not deleted, \n kindly check the employeeId again ");
        } else {
            System.out.println("Employee details deleted Successfully");
        }
    }

    /**
     * Showing All Employee Details
     */
    private void showAll() {

        if(employeeController.getAll().isEmpty()) {
            System.out.println("No records Found");
        } else {
            System.out.println(employeeController.getAll());
        }
    }

    /**
     * Mobile Number Validation
     * 
     *  @return mobileNumber
     */
    private long  validateMobileNumber() {
        System.out.println("Enter Mobile Number");
        long mobileNumber = employeeController.validateMobileNumber(scanner.nextLong());
        
        if(0 == mobileNumber) {
            System.out.println("Invalid Mobile Number....");
            return validateMobileNumber();	
        } 
        return mobileNumber;	
    }

    /**
     * Date Validation
     * 
     * @return date
     */
    private Date  dateValidate() {
        System.out.println("Enter Date of Birth in the format ('dd/MM/yyyy')");
        String date = scanner.next();

        if (null == employeeController.dateValidate(date)) {
            System.out.println("Invalid Date format...");
            return dateValidate();
        } else {
            return employeeController.dateValidate(date);
        } 	
    }
    
    /**
     * Shows All delete data
     */
    private void showDeletedData() {
        System.out.println(employeeController.getDeletedData());
        
        if(employeeController.getDeletedData().isEmpty()) {
            System.out.println("No Deleted Data Found");
        }  else {
            System.out.println(" Enter 1 to restore \n Enter 2 to finish");
            int option = scanner.nextInt();

            switch(option) {
            case 1 :
                System.out.println("Enter the employeeId to restore");
                int employeeId = scanner.nextInt();
                employeeController.restoreDeletedData(employeeId);
            default :
                break;
            } 
        }
    }
}

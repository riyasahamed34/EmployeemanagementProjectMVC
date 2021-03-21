package com.ideas2it.employeeManagementProject.controller;

import com.ideas2it.employeeManagementProject.service.EmployeeService;
import com.ideas2it.employeeManagementProject.service.impl.EmployeeServiceImpl;

import java.sql.Date;
import java.util.List;

/**
 * EmployeeController Class for Controlling EmployeeService and Employee
 *
 * @author RiyasAhamed
 * @version  2.0  02-03-2021
 */
public class EmployeeController {
    EmployeeService employeeService = new EmployeeServiceImpl();
    
    /**
     * Creating New Employee and Details 
     * 
     * @param employeeId
	 * @param firstName
	 * @param lastName
	 * @param salary
	 * @param mobileNumber
	 * @param dateOfBirth
	 * @param employeeAddressList
     */
    public int addNewEmployeeDetails(int employeeId, String firstName, String lastName,
            double salary, long mobileNumber,
	    Date dateOfBirth, List<String[]> employeeAddressList)  {
	    return employeeService.addNewEmployeeDetails(employeeId, firstName,
	            lastName, salary, mobileNumber, dateOfBirth, employeeAddressList);
    }

    /**
     * Updating Existing Employee and Details
     *  
     * @param employeeId
	 * @param firstName
	 * @param lastName
	 * @param salary
	 * @param mobileNumber
	 * @param dateOfBirth
     */
    public int updateEmployeeDetails(int employeeId, String firstName, String lastName,
            double salary, long mobileNumber, Date dateOfBirth ) {
	    return employeeService.updateEmployeeDetails(employeeId, firstName, lastName,
	        salary, mobileNumber, dateOfBirth);
    }
    
    /**
     * Updating Existing Employee's Address
     *  
     * @param addressId
     * @param employeeId
     * @param doorNumber
     * @param streetOrroadName
     * @param city
     * @param pincode
     * @param state
     * @param addressType
     */
    public int updateEmployeeAddress(int addressId,int employeeId, String doorNumber, String streetOrroadName,
            String city, int pincode, String state, String addressType) {
        return employeeService.updateEmployeeAddress(addressId, employeeId, doorNumber, streetOrroadName,
                city, pincode, state, addressType);
    }

    /**
     * getting particular Employee Details
     * 
     * @return employeeList
     */	
    public List<String> getEmployeeDetails(int employeeId) {
	    return employeeService.getEmployeeDetails(employeeId); 
    }
    
    /**
     * Deleting particular Employee Details 
     */	
    public int deleteEmployee(int employeeId) {
    	return employeeService.deleteEmployee(employeeId);	 
    }

    /**
     * getting All Employee Details
     * 
     * @return employeeList
     */	
    public List<String> getAll() {
	    return employeeService.getAll();
    }
	
    /**
     * Mobile Number Validation
     * 
     * @return mobileNumber
     */
    public long validateMobileNumber(long mobileNumber) {
    	return employeeService.validateMobileNumber(mobileNumber);
    }

    /**
     * Date Validation
     * 
     * @return date
     */
    public Date dateValidate(String date) {
        return employeeService.dateValidate(date);
    }

    /**
     * Checking EmployeeId
     */
    public int checkEmployeeId() {	
	    return employeeService.checkEmployeeId();
	}

    /**
     * Showing All deleted Data 
     */
    public List<String> getDeletedData() {
        return employeeService.getDeletedData();
    }

    /**
     * Restoring particular employee details
     * 
     * @param employeeId
     */
    public int restoreDeletedData(int employeeId) {
        return employeeService.restoreDeletedData(employeeId); 
    }   
}

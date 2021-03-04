package com.ideas2it.employeeManagementProject.controller;

import java.util.Date;
import java.util.List;

import com.ideas2it.employeeManagementProject.service.EmployeeService;

/**
 * EmployeeController Class for Controlling EmployeeService and Employee
 *
 * @author Riyas Ahamed
 * @version  2.0  02-03-2021
 */
public class EmployeeController {
    EmployeeService employeeService = new EmployeeService();
    
    /**
     * Creating New Employee and Details
     */
    public void addNewEmployeeDetails(int employeeId, String firstName, String lastName, int salary, long mobileNumber,
	    Date dateOfBirth) {
	employeeService.addNewEmployeeDetails(employeeId, firstName, lastName, salary, mobileNumber, dateOfBirth);
    }

    /**
     * Updating Existing Employee and Details
     */
    public void updateEmployeeDetails(int employeeId, String firstName, String lastName, int salary, long mobileNumber,
	    Date dateOfBirth ) {
	employeeService.updateEmployeeDetails(employeeId, firstName, lastName, salary, mobileNumber, dateOfBirth);
    }

    /**
     * getting particular Employee Details
     */	
    public String getEmployeeDetails(int employeeId) {
	return employeeService.getEmployeeDetails(employeeId).toString(); 
    }

    /**
     * Deleting particular Employee Details
     */	
    public void deleteEmployee(int employeeId) {
    	employeeService.deleteEmployee(employeeId);
    }

    /**
     * getting All Employee Details
     */	
    public List<String> getAll() {
	return employeeService.getAll();
    }
	
    /**
     * Checking the EmployeeId is present or not
     */
    public boolean employeeIdPresent(int employeeId) {

    	if (employeeService.employeeIdPresent(employeeId)) {
            return true;
        } else {
    	    return false;
    	}
    }

    public long validateMobileNumber(long mobileNumber) {
    	employeeService.validateMobileNumber(mobileNumber);
    	return mobileNumber; 	
    }
}

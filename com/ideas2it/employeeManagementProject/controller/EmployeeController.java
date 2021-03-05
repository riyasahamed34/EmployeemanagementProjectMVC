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
     *
     * @param employeeId
     * @param firstName
     * @param lastName
     * @param salary
     * @param mobileNumber
     * @param date
     */
    public void addNewEmployeeDetails(int employeeId, String firstName, String lastName, int salary, long mobileNumber,
	    Date dateOfBirth) {
	employeeService.addNewEmployeeDetails(employeeId, firstName, lastName, salary, mobileNumber, dateOfBirth);
    }

    /**
     * Updating Existing Employee and Details
     *
     * @param employeeId
     * @param firstName
     * @param lastName
     * @param salary
     * @param mobileNumber
     * @param date
     */
    public void updateEmployeeDetails(int employeeId, String firstName, String lastName, int salary, long mobileNumber,
	    Date dateOfBirth ) {
	employeeService.updateEmployeeDetails(employeeId, firstName, lastName, salary, mobileNumber, dateOfBirth);
    }

    /**
     * getting particular Employee Details
     * @param employeeId
     */	
    public String getEmployeeDetails(int employeeId) {
	return employeeService.getEmployeeDetails(employeeId).toString(); 
    }

    /**
     * Deleting particular Employee 
     * @param employeeId
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
     * @param employeeId
     */
    public boolean employeeIdPresent(int employeeId) {

    	if (employeeService.employeeIdPresent(employeeId)) {
            return true;
        } else {
    	    return false;
    	}
    }

    /**
     * Mobile Number Validation
     * @param mobileNumber
     */
    public long validateMobileNumber(long mobileNumber) {
    	return employeeService.validateMobileNumber(mobileNumber); 	
    }

    /**
     * Date Validation
     * @param date
     */
    public Date dateValidate(String date) {
        return employeeService.dateValidate(date);
    }
}

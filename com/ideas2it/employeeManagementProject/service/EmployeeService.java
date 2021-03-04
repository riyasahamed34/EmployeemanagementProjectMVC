package com.ideas2it.employeeManagementProject.service;

import com.ideas2it.employeeManagementProject.model.Employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * EmployeeService Class for services
 *
 * @author Riyas Ahamed
 * @version  2.0  02-03-2021
 */
public class EmployeeService {
    Map<Integer, Employee> employeeDetails = new HashMap<Integer, Employee>();
 
    /**
     * Adding New Employee and Details to employeeDetails
     */
    public void addNewEmployeeDetails(int employeeId, String firstName, String lastName, int salary,
	    long mobileNumber, Date dateOfBirth) {
        employeeDetails.put(employeeId, new Employee(employeeId, firstName, lastName, salary,
                mobileNumber, dateOfBirth));
	}

    /**
     * getting Particular Employee and Details
     */
    public Employee getEmployeeDetails(int employeeId) {
	return employeeDetails.get(employeeId);
    }

    /**
     * Updating Existing Employee and Details
     */
    public void updateEmployeeDetails(int employeeId, String firstName, String lastName, int salary,
            long mobileNumber,Date dateOfBirth) {
	employeeDetails.put(employeeId, new Employee(employeeId, firstName, lastName, salary, mobileNumber, dateOfBirth));	
    }
    
    /**
     * Deleting Particular Employee and Details
     */
    public void deleteEmployee(int employeeId) {
	employeeDetails.remove(employeeId);
    }

    /**
     * Getting All Details in employeeList 
     */
    public List<String> getAll() {
        List<String> employeeList = new ArrayList<String>();
        for(Employee employee : employeeDetails.values()) {
            employeeList.add(employee.toString());
        }
        return employeeList;     
    }

    /**
     * Checking the the Presence of EmployeeId
     */	
    public boolean employeeIdPresent(int employeeId){

	if (employeeDetails.containsKey(employeeId)) {
	    return true;	
	} else {
	    return false;
	}
    }

    /**
     * Mobile Number Validation
     */
    public long validateMobileNumber(long mobileNumber) {
	
    	if((mobileNumber / 1000000000) != 8 && (mobileNumber / 1000000000) != 6 &&
               (mobileNumber / 1000000000)  != 7 && (mobileNumber / 1000000000) != 9) {
    	    return 0;		
    	} 
    	return mobileNumber;
    }

    /**
     * Date Validation
     */
    public Date dateValidate(String date) {
        
        try{
	    Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
	    return birthDate;
	} catch(Exception e) {
	    if(null != e) {
                return null;
	    }
	    return null;
        }
    }
}



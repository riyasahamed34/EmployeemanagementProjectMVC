package com.ideas2it.employeeManagementProject.service;

import com.ideas2it.employeeManagementProject.dao.EmployeeDao;
import com.ideas2it.employeeManagementProject.dao.impl.EmployeeDaoImpl;

import java.sql.Date;
import java.util.List;

/**
 * EmployeeService Interface for services
 *
 * @author RiyasAhamed
 * @version  2.0  02-03-2021
 */
public interface EmployeeService {

    EmployeeDao employeeDao = new EmployeeDaoImpl();

    /**
     * Adding New Employee and Details 
     * 
     * @param employeeId
     * @param firstName
     * @param lastName
     * @param salary
     * @param mobileNumber
     * @param dateOfBirth
     * @param addressList 
     */
    public int addNewEmployeeDetails(int employeeId, String firstName,
            String lastName, double salary, long mobileNumber,
            Date dateOfBirth, List<String[]> employeeAddressList)  ;

    /**
     * getting Particular Employee and Details
     * 
     * @param employeeId
     * @return employeeList
     */
    public List<String> getEmployeeDetails(int employeeId) ;

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
    public int updateEmployeeDetails(int employeeId, String firstName, 
            String lastName, double salary, long mobileNumber,Date dateOfBirth);

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
     */
    public int updateEmployeeAddress(int addressId, int employeeId, String doorNumber, 
            String streetOrroadName, String city, int pincode, String state, String addressType);

    /**
     * Deleting Particular Employee and Details
     * @param employeeId
     */
    public int deleteEmployee(int employeeId) ;

    /**
     * Getting All Details in employeeList 
     * 
     * @return employeeList
     */
    public List<String> getAll() ;

    /**
     * Mobile Number Validation
     * @param mobileNumber
     */
    public long validateMobileNumber(long mobileNumber) ;
    
    /**
     * Date Validation
     * 
     * @param date
     * @return date
     */
    public Date dateValidate(String date) ;

    /**
     * Checking EmployeeId from Database
     */
    public int checkEmployeeId();

    /**
     * Showing All deleted Data 
     * 
     * @return employeeList
     */
    public List<String> getDeletedData();

    /**
     * Restoring particular employee details
     * 
     * @param employeeId
     */
    public int restoreDeletedData(int employeeId);	
}

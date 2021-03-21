package com.ideas2it.employeeManagementProject.dao;

import com.ideas2it.employeeManagementProject.model.Employee;
import com.ideas2it.employeeManagementProject.model.EmployeeAddress;

import java.util.List;

/**
 * EmployeeDao interface for DataBase Access
 * 
 * @author Riyas
 * @version  2.0 15-03-2021
 */
public interface EmployeeDao {

    /**
     * Inserting New Employee and Details 
     * 
     * @param employee
     */
    public int insertNewEmployeeDetails(Employee employee);

    /**
     * Inserting New Employee's Address 
     * 
     * @param employee
     * @param addressList
     */
    public int insertNewEmployeeAddress(List<EmployeeAddress> addressList, int employeeId);

    /**
     * getting particular Employee and Details 
     * 
     * @param employeeId
     */
    public Employee getEmployeeDetails(int employeeId);
    
    /**
     * updating particular Employee and details 
     * 
     * @param employee
     */
    public int updateEmployeeDetails(Employee employee);

    /**
     * updating particular Employee and details 
     * 
     * @param employeeAddress
     */
    public int updateEmployeeAddress(EmployeeAddress employeeAddress);

    /**
     * Deleting particular Employee and details 
     * 
     * @param employeeId
     */
    public int deleteEmployee(int employeeId);

    /**
     * Getting All Employee and details 
     */
    public List<Employee> getAll();

    /**
     * Checking EmployeeId from Database
     */
    public int checkEmployeeId();

    /**
     * Getting Deleted EmployeeList
     */
    public List<Employee> showDeletedData();

    /**
     * Restoring particular deleted employee details
     * 
     * @param employeeId
     */
    public int restoreDeletedData(int employeeId);
}


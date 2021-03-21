package com.ideas2it.employeeManagementProject.service.impl;

import com.ideas2it.employeeManagementProject.dao.EmployeeDao;
import com.ideas2it.employeeManagementProject.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeeManagementProject.model.Employee;
import com.ideas2it.employeeManagementProject.model.EmployeeAddress;
import com.ideas2it.employeeManagementProject.service.EmployeeService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * EmployeeServiceImpl Implements for EmployeeServices
 *
 * @author RiyasAhamed
 * @version  2.0  02-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
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
     */
    @Override
    public int addNewEmployeeDetails(int employeeId, String firstName, String lastName,
            double salary, long mobileNumber, Date dateOfBirth,  List<String[]> employeeAddressList)  { 
        List<EmployeeAddress> addressList = new ArrayList<EmployeeAddress>();

        for(int index  = 0; index < employeeAddressList.size(); index++) {
            String doorNumber = employeeAddressList.get(index)[0];
            String  streetOrroadName = employeeAddressList.get(index)[1];
            String city = employeeAddressList.get(index)[2];
            int pincode = Integer.parseInt(employeeAddressList.get(index)[3]);
            String state = employeeAddressList.get(index)[4];
            String addressType = employeeAddressList.get(index)[5];
            EmployeeAddress employeeAddress = new EmployeeAddress(employeeId, doorNumber,
                    streetOrroadName,city, pincode, state, addressType);
            addressList.add(employeeAddress);
        }
        Employee employee = new Employee(employeeId,  firstName,  lastName, salary,
                mobileNumber, dateOfBirth, addressList);
        return employeeDao.insertNewEmployeeDetails(employee);
    }

    /**
     * getting Particular Employee and Details
     * @param employeeId
     */
    @Override
    public List<String> getEmployeeDetails(int employeeId)  {

        List<String> addressList = new ArrayList<>();
        List<String> employeeList = new ArrayList<>();
        Employee employee = employeeDao.getEmployeeDetails(employeeId);

        try {

            List<EmployeeAddress> employeeAddressList = employee.getAddressList();
            employeeList.add(employee.toString());
            
            for(int index = 0; index < employeeAddressList.size(); index++) {
                int addressId = employeeAddressList.get(index).getAddressId();
                String doorNumber = employeeAddressList.get(index).getDoorNumber();
                String  streetOrroadName = employeeAddressList.get(index).getStreetOrroadName();
                String city = employeeAddressList.get(index).getCity();
                int pincode = employeeAddressList.get(index).getPincode();
                String state = employeeAddressList.get(index).getState();
                String addressType = employeeAddressList.get(index).getAddressType();
                EmployeeAddress employeeAddress = new EmployeeAddress( addressId, employeeId,
                        doorNumber,streetOrroadName, city, pincode, state, addressType);
                addressList.add(employeeAddress.toString());
            }
            employeeList.addAll(addressList);
        } catch (Exception e) {
            return null;
        }
        return employeeList;      
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
    @Override
    public int updateEmployeeDetails(int employeeId, 
            String firstName, String lastName, double salary,
            long mobileNumber, Date dateOfBirth) {
        Employee employee = new Employee(employeeId,  firstName,  lastName, salary,
                mobileNumber, dateOfBirth);
        return employeeDao.updateEmployeeDetails(employee);
    }

    /**
     * Updating Existing Employee's Address
     *  
     * @param addressId
     * @param doorNumber
     * @param streetOrroadName
     * @param city
     * @param pincode
     * @param state
     */
    @Override
    public int updateEmployeeAddress(int addressId, int employeeId,
            String doorNumber, String streetOrroadName, String city,
            int pincode, String state, String addressType) {
        EmployeeAddress employeeAddress = new EmployeeAddress(addressId, employeeId, doorNumber,
                streetOrroadName, city, pincode, state, addressType);
        return employeeDao.updateEmployeeAddress(employeeAddress);
    }

    /**
     * Deleting Particular Employee and Details
     * @param employeeId
     */
    @Override
    public int deleteEmployee(int employeeId) {
        return employeeDao.deleteEmployee(employeeId);
    }

    /**
     * Getting All Details in employeeList
     * 
     *  @return employeeList
     */
    @Override
    public List<String> getAll() {
        List<Employee> employees = employeeDao.getAll();
        List<String> employeeList = new ArrayList<>();

        try {
            for(int index1 = 0; index1 < employees.size(); index1++) {
                int employeeId = employees.get(index1).getEmployeeId();
                String firstName = employees.get(index1).getFirstName();
                String lastName = employees.get(index1).getLastName();
                double salary = employees.get(index1).getSalary();
                long mobileNumber = employees.get(index1).getMobileNumber();
                Date dateOfBirth = employees.get(index1).getDateOfBirth();
                List<EmployeeAddress> employeeAddressList = employees.get(index1).getAddressList();
                List<String> addressList = new ArrayList<>();

                for(int index = 0 ; index < employeeAddressList.size(); index++) {
                    int addressId = employeeAddressList.get(index).getAddressId();
                    String doorNumber = employeeAddressList.get(index).getDoorNumber();
                    String  streetOrroadName = employeeAddressList.get(index).getStreetOrroadName();
                    String city = employeeAddressList.get(index).getCity();
                    int pincode = employeeAddressList.get(index).getPincode();
                    String state = employeeAddressList.get(index).getState();
                    String addressType = employeeAddressList.get(index).getAddressType();
                    EmployeeAddress employeeAddress = new EmployeeAddress(addressId, employeeId,
                            doorNumber, streetOrroadName, city, pincode, state, addressType);
                    addressList.add(employeeAddress.toString());
                }

                Employee employee = new Employee(employeeId, firstName, lastName, salary,
                        mobileNumber, dateOfBirth);
                employeeList.add(employee.toString());
                employeeList.addAll(addressList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return employeeList;     
    }

    /**
     * Mobile Number Validation
     * @param mobileNumber
     * 
     * @return mobileNumber
     */
    @Override
    public long validateMobileNumber(long mobileNumber) {

        if((mobileNumber / 1000000000) != 8 && (mobileNumber / 1000000000) != 6 &&
                (mobileNumber / 1000000000)  != 7 && (mobileNumber / 1000000000) != 9) {
            return 0;		
        } 
        return mobileNumber;
    }

    /**
     * Date Validation
     * @param date
     * 
     * @return date
     */
    @Override
    public Date dateValidate(String date) {
        try{
            if( Pattern.matches("([012][1-9]|3[01])-(0?[1-9]|1[012])-([12][0-9][0-9][0-9])",date )) {  
                Date birthDate = new Date( new SimpleDateFormat("dd-MM-yyyy").parse(date).getTime());
                return birthDate;
             }
            
        } catch(Exception e) {
            return null;
        }
        return null;
    }

    /**
     * Getting all deleted data
     * 
     * @return employeeList
     */
    public List<String> getDeletedData() {
        List<Employee> employees = employeeDao.showDeletedData();
        List<String> employeeList = new ArrayList<>();

        try {
            for (int index1 = 0; index1 < employees.size(); index1++) {
                int employeeId = employees.get(index1).getEmployeeId();
                String firstName = employees.get(index1).getFirstName();
                String lastName = employees.get(index1).getLastName();
                double salary = employees.get(index1).getSalary();
                long mobileNumber = employees.get(index1).getMobileNumber();
                Date dateOfBirth = employees.get(index1).getDateOfBirth();
                List<EmployeeAddress> employeeAddressList = employees.get(index1).getAddressList();
                List<String> addressList = new ArrayList<>();

                for(int index = 0 ; index < employeeAddressList.size(); index++) {
                    int addressId = employeeAddressList.get(index).getAddressId();
                    String doorNumber = employeeAddressList.get(index).getDoorNumber();
                    String  streetOrroadName = employeeAddressList.get(index).getStreetOrroadName();
                    String city = employeeAddressList.get(index).getCity();
                    int pincode = employeeAddressList.get(index).getPincode();
                    String state = employeeAddressList.get(index).getState();
                    String addressType = employeeAddressList.get(index).getAddressType();
                    EmployeeAddress employeeAddress = new EmployeeAddress(addressId, employeeId,
                            doorNumber, streetOrroadName, city, pincode, state, addressType);
                    addressList.add(employeeAddress.toString());
                }
                Employee employee = new Employee(employeeId, firstName, lastName, salary,
                        mobileNumber, dateOfBirth);
                employeeList.add(employee.toString());
                employeeList.addAll(addressList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return employeeList;
    }

    /**
     * Restores deleted Data
     * 
     * @param employeeId
     */
    public int restoreDeletedData(int employeeId) {
        return employeeDao.restoreDeletedData(employeeId);
    }

    /**
     * Checking EmployeeId from DataBase
     */
    @Override
    public int checkEmployeeId() {
        return employeeDao.checkEmployeeId();
    }
}

package com.ideas2it.employeeManagementProject.dao.impl;

import com.ideas2it.employeeManagementProject.dao.EmployeeDao;
import com.ideas2it.employeeManagementProject.model.Employee;
import com.ideas2it.employeeManagementProject.model.EmployeeAddress;
import com.ideas2it.sessionFactory.DataBaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDaoImpl implements EmployeeDao
 * 
 * @author riyas
 * @version  2.0 15-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {

    /*
     * {@inheritDoc}
     */
    @Override
    public int insertNewEmployeeDetails(Employee employee)   {
        int employeeId = employee.getEmployeeId();
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        double salary = employee.getSalary();
        long mobileNumber = employee.getMobileNumber();
        Date dateOfBirth = employee.getDateOfBirth();
        List<EmployeeAddress> addressList = employee.getAddressList();

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            int status1 = statement.executeUpdate("insert into Employee values"
                    + "('"+employeeId+"', '"+firstName+"', '"+lastName+"',"
                    + "'"+salary+"','"+mobileNumber+"','"+dateOfBirth+"', '"+0+"')");
            int status2 = insertNewEmployeeAddress(addressList, employeeId);

            if(0 != status1 &&  0 != status2 ) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public int insertNewEmployeeAddress(List<EmployeeAddress> addressList, int employeeId) {

        for (int index = 0;index < addressList.size(); index++) {
            String doorNumber = addressList.get(index).getDoorNumber();
            String  streetOrroadName = addressList.get(index).getStreetOrroadName();
            String city = addressList.get(index).getCity();
            int pincode = addressList.get(index).getPincode();
            String state = addressList.get(index).getState();
            String addressType = addressList.get(index).getAddressType();

            try {
                Connection connection = DataBaseConnection.getConnection();
                Statement statement = connection.createStatement();
                statement.executeUpdate("insert into EmployeeAddress values"
                        + "('"+0+"','"+employeeId+"', '"+doorNumber+"',"
                        + "'"+streetOrroadName+"','"+city+"','"+pincode+"',"
                        + "'"+state+"','"+addressType+"','"+0+"')");
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 1;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployeeDetails(int employeeId) {

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee,employeeAddress "
                    + "WHERE employee.employee_id = '" + employeeId + "'"
                    + " and employeeAddress.employee_id = '" + employeeId + "'"
                    + "and employee.isdeleted = 0 and employeeaddress.isdeleted = 0");
            Employee employee = null;
            List<EmployeeAddress> employeeAddressList = new ArrayList<>();
            
            while(resultSet.next()) {
                employeeId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                double salary = resultSet.getDouble(4);
                long mobileNumber = resultSet.getLong(5);
                Date dateOfBirth = resultSet.getDate(6);
                int addressId = resultSet.getInt(8);
                String doorNumber = resultSet.getString(10);
                String streetOrroadName = resultSet.getString(11);
                String city = resultSet.getString(12);
                int pincode = resultSet.getInt(13);
                String state = resultSet.getString(14);
                String addressType = resultSet.getString(15);
                System.out.println(addressId);
                EmployeeAddress employeeAddress = new EmployeeAddress(addressId, employeeId,
                        doorNumber,streetOrroadName,city, pincode, state, addressType);
                employeeAddressList.add(employeeAddress); 
                employee = new  Employee(employeeId, firstName, lastName, salary,
                        mobileNumber, dateOfBirth, employeeAddressList);
            }
            return employee;
        } catch (Exception e) {
            e.printStackTrace();		
        }
        return null;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public int updateEmployeeDetails(Employee employee) {
        int employeeId = employee.getEmployeeId();
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        double salary = employee.getSalary();
        long mobileNumber = employee.getMobileNumber();
        Date dateOfBirth = employee.getDateOfBirth();

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();

            if (0 != statement.executeUpdate("update Employee set first_Name ="
                    + " '"+firstName+"', last_Name = '"+lastName+"',"
                    + "salary = '"+salary+"', mobile_Number = '"+mobileNumber+"',"
                    + " dateOfBirth = '"+dateOfBirth+"'"
                    + " where employee_Id = '"+employeeId+"'")) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public int updateEmployeeAddress(EmployeeAddress employeeAddress) {
        int addressId = employeeAddress.getAddressId();
        int employeeId = employeeAddress.getEmployeeId();
        String doorNumber = employeeAddress.getDoorNumber();
        String streetOrroadName = employeeAddress.getStreetOrroadName();
        String city = employeeAddress.getCity();
        int pincode = employeeAddress.getPincode();
        String state = employeeAddress.getState();
        String addressType = employeeAddress.getAddressType();

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            if (0 != statement.executeUpdate("update employeeAddress set door_number = '"+doorNumber+"'"
                    + ", Street_or_RoadName = '"+streetOrroadName+"' , city = '"+city+"'"
                    + ", pincode = '"+pincode+"' , state = '"+state+"', address_type = '"+addressType+"'"
                    + " where address_id = '"+addressId+"' and employee_id = '"+employeeId+"'")) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public int deleteEmployee(int employeeId) {
        int status;

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            status = statement.executeUpdate("update employee,employeeaddress set employee.isdeleted = 1,"
                    + "employeeaddress.isdeleted = 1 where employee.employee_Id = '"+employeeId+"' and "
                    + "employeeAddress.employee_Id = '"+employeeId+"'");
            return status;
        } catch (Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("select * from  employee, employeeAddress where employee.employee_id ="
                            + "employeeAddress.employee_id and employee.isdeleted = 0 and "
                            + "employeeaddress.isdeleted = 0 ");
            int flag = 0;

            if (resultSet.next()) {

                while (flag ==0) {
                    int employeeId = resultSet.getInt(1);
                    String firstName = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    double salary = resultSet.getDouble(4);
                    long mobileNumber = resultSet.getLong(5);
                    Date dateOfBirth = resultSet.getDate(6);
                    List<EmployeeAddress> employeeAddressList = new ArrayList<>();

                    while (employeeId == resultSet.getInt(1) ) {
                        int addressId = resultSet.getInt(8);
                        String doorNumber = resultSet.getString(10);
                        String streetOrroadName = resultSet.getString(11);
                        String city = resultSet.getString(12);
                        int pincode = resultSet.getInt(13);
                        String state = resultSet.getString(14);
                        String addressType = resultSet.getString(15);
                        employeeAddressList.add(new EmployeeAddress(addressId, employeeId, doorNumber,
                                streetOrroadName,city, pincode, state, addressType)); 

                        if(!resultSet.next()) {
                            flag =1;
                            break;
                        }
                    }
                    employeeList.add(new Employee( employeeId,  firstName,  lastName,  salary,
                            mobileNumber,  dateOfBirth, employeeAddressList));
                }
            }
        } catch (Exception e ) {
            e.printStackTrace();
        } 

        return employeeList;
    }

    /*
     * {@inheritDoc}
     */
    public List<Employee> showDeletedData() {
        List<Employee> employeeList = new ArrayList<>();

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("select * from  employee, employeeAddress where employee.employee_id ="
                            + "employeeAddress.employee_id and employee.isdeleted = 1 and "
                            + "employeeaddress.isdeleted = 1 ");
            int flag = 0;

            if (resultSet.next()) {

                while (0 == flag) {
                    int employeeId = resultSet.getInt(1);
                    String firstName = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    double salary = resultSet.getDouble(4);
                    long mobileNumber = resultSet.getLong(5);
                    Date dateOfBirth = resultSet.getDate(6);
                    List<EmployeeAddress> employeeAddressList = new ArrayList<>();

                    while (resultSet.getInt(1) == employeeId) {
                        int addressId = resultSet.getInt(8);
                        System.out.println(addressId+ "addres");
                        String doorNumber = resultSet.getString(10);
                        String streetOrroadName = resultSet.getString(11);
                        String city = resultSet.getString(12);
                        int pincode = resultSet.getInt(13);
                        String state = resultSet.getString(14);
                        String addressType = resultSet.getString(15);
                        employeeAddressList.add(new EmployeeAddress(addressId, employeeId, doorNumber,
                                streetOrroadName,city, pincode, state, addressType)); 

                        if(!resultSet.next()) {
                            flag =1;
                            break;
                        }
                    }
                    employeeList.add(new Employee( employeeId,  firstName,  lastName,  salary,
                            mobileNumber,  dateOfBirth, employeeAddressList));
                }
            }

        } catch (Exception e ) {
            e.printStackTrace();
        } 
        return employeeList;
    }

    /*
     * {@inheritDoc}
     */
    public int restoreDeletedData(int employeeId) {
        int status;

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            status = statement.executeUpdate("update employee,employeeaddress set employee.isdeleted = 0,"
                    + "employeeaddress.isdeleted = 0 where employee.employee_id = '"+employeeId+"'"
                    + " and employeeAddress.employee_id = '"+employeeId+"'");
            return status;
        } catch (Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public int checkEmployeeId() {

        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("select max(employee_id) as maxLevel from employee");

            if (resultSet.next()) {
                int employeeId = resultSet.getInt("maxLevel");
                return employeeId ;
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 0;
    }
}

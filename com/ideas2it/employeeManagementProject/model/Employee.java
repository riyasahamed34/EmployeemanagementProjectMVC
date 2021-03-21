package com.ideas2it.employeeManagementProject.model;

import java.sql.Date;
import java.util.List;

public class Employee {

	private int employeeId;
	private String firstName;
	private String lastName;
	private double salary;
	private long mobileNumber;
	private Date dateOfBirth;
	private List<EmployeeAddress> addressList;
	
	public Employee(int employeeId, String firstName, String lastName, double salary, long mobileNumber,
			Date dateOfBirth,List<EmployeeAddress> addressList) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.mobileNumber = mobileNumber;
		this.dateOfBirth = dateOfBirth;
		this.addressList = addressList;
	}
	
	public Employee(int employeeId, String firstName, String lastName, double salary, long mobileNumber,
            Date dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
    }
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public List<EmployeeAddress> getAddressList() {
        return addressList;
    }
	
    public void setAddressList(List<EmployeeAddress> addressList) {
        this.addressList = addressList;
    }
	
	public String toString() {
	    return "\n EmployeeId = " + employeeId +
	            "\n FirstName =  " + firstName + 
		        "\n LastName =  " + lastName + 
		        "\n DateOfBirth =  " + dateOfBirth +
		        "\n Salary =  " + salary + 
		        "\n MobileNumber =  " + mobileNumber;
    }
}

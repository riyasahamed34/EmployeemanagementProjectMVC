package com.ideas2it.employeeManagementProject.model;

public class EmployeeAddress {
    int addressId;
    int employeeId;
	String doorNumber;
	String streetOrroadName;
	String city;
	int pincode;
	String state;
    String addressType;

	public EmployeeAddress( int employeeId, String doorNumber, String streetOrroadName,
	        String city, int pincode, String state, String addressType) {
		this.employeeId = employeeId;
		this.doorNumber = doorNumber;
		this.streetOrroadName = streetOrroadName;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.addressType = addressType;
	}
	
	public EmployeeAddress(int addressId, int employeeId, String doorNumber,
	        String streetOrroadName, String city, int pincode, String state, String addressType) {
        this.addressId = addressId;
	    this.employeeId = employeeId;
        this.doorNumber = doorNumber;
        this.streetOrroadName = streetOrroadName;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.addressType = addressType;
    }
		
	public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	public String getStreetOrroadName() {
		return streetOrroadName;
	}
	public void setStreetOrroadName(String streetOrroadName) {
		this.streetOrroadName = streetOrroadName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String toString() {
		return  "\n\n AddressId = " + addressId +
		        "\n doorNumber = " + doorNumber + 
				"\n streetOrroadName = " + streetOrroadName + 
				"\n city = " + city +
				"\n pincode = " + pincode + 
				"\n state = " + state +
				"\n addresType = " + addressType;
	}
}

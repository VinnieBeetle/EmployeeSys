package com.example.employeesys;

public class EmployeeString extends Employee{
    public String toString(){
        String result = "";
        result = getID()+ "," + getName()+ "," + getAge()+ "," + getBirth();
        return result;
    }
}

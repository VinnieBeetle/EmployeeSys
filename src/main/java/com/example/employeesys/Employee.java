package com.example.employeesys;

public class Employee {

    private String ID;
    private String clockIn;
    private String clockOut;
    private String name;
    private String age;
    private String birth;

    //constructor
    public Employee(){
        ID = "";
        name = "";
        age = "" ;
        birth = "";
        clockIn = "";
        clockOut = "";
    }
    //overloaded constructor
    public Employee(String ID, String name, String age, String birth, String cI, String cO){
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.clockIn = cI;
        this.clockOut = cO;
    }

    //setters
    public void setID (String ID){
        this.ID = ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(String age){
        this.age = age;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }
    public void setCI (String cI){
        this.clockIn = cI;
    }
    public void setCO (String cO){
        this.clockOut = cO;
    }
    //getters
    public String getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    public String getBirth(){
        return birth;
    }
    public String getCI(){
        return clockIn;
    }
    public String getCO(){
        return clockOut;
    }


}

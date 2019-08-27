package com.example.tasks_management;

public class DataEmployee {
        String      employee_id ;
        String      employee_name;
        String      employee_username;
        String      employee_password;
        String      employee_section;
        String      employee_department;
        String      employee_main_mission;

public DataEmployee(){}

    public DataEmployee(String employee_id, String employee_name, String employee_username, String employee_password, String employee_section, String employee_department, String employee_main_mission) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_username = employee_username;
        this.employee_password = employee_password;
        this.employee_section = employee_section;
        this.employee_department = employee_department;
        this.employee_main_mission = employee_main_mission;
    }


    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setEmployee_username(String employee_username) {
        this.employee_username = employee_username;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public void setEmployee_section(String employee_section) {
        this.employee_section = employee_section;
    }

    public void setEmployee_department(String employee_department) {
        this.employee_department = employee_department;
    }

    public void setEmployee_main_mission(String employee_main_mission) {
        this.employee_main_mission = employee_main_mission;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_username() {
        return employee_username;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public String getEmployee_section() {
        return employee_section;
    }

    public String getEmployee_department() {
        return employee_department;
    }

    public String getEmployee_main_mission() {
        return employee_main_mission;
    }


}

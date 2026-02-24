/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Developer {
    private String devID;
    private String name;
    private String language;
    private double salary;

    public Developer() {
    }

    public Developer(String devID, String name, String language, double salary) {
        this.devID = devID;
        this.name = name;
        this.language = language;
        this.salary = salary;
    }

    public void setDevID(String devID) {
        this.devID = devID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDevID() {
        return devID;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        System.out.printf("%-15s| %-15s| %-23s| %-15s%n",
                  "Dev Id", "Full name", "Programming languages", "Salary");
        return String.format("%-15s| %-15s| %-23s| %-15s", devID, name, language, salary);
    }
    
}

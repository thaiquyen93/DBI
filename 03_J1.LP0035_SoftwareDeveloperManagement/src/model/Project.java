/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Project {
    private String projectId;
    private String devId;
    private String projectName;
    private int durationMonth;
    private Date startDate;

    public Project() {
    }

    public Project(String projectId, String devId, String projectName, int durationMonth, Date startDate) {
        this.projectId = projectId;
        this.devId = devId;
        this.projectName = projectName;
        this.durationMonth = durationMonth;
        this.startDate = startDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(int durationMonth) {
        this.durationMonth = durationMonth;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Project{" + "projectId=" + projectId + ", devId=" + devId + ", projectName=" + projectName + ", durationMonth=" + durationMonth + ", startDate=" + startDate + '}';
    }
    
    
}

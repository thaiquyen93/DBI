/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import model.Developer;
import model.Project;
import service.DeveloperService;
import service.ProjectService;
import util.Acceptable;
import util.Inputter;
import view.ViewFormatter;

/**
 *
 * @author Admin
 */
public class ProjectController {

    private ProjectService projectService;
    private ViewFormatter viewFormatter;
    private DeveloperService developerService;

    Inputter ip = new Inputter();
    public boolean dirty = false;

    public ProjectController(ProjectService ProjectService, DeveloperService developerService) {
        this.projectService = ProjectService;
        this.developerService = developerService;
        this.viewFormatter = new ViewFormatter();
    }

    private void markDirty() {
        dirty = true;
    }

    private void clearDirty() {
        dirty = false;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void listAllProject() {
        viewFormatter.displayProjects(projectService.listAll());
    }

    public void addNewProject() {
        String proID;
        do {
            proID = ip.getStringAndCheck("Enter new project code: ", Acceptable.PROJECT_ID_VALID);
            if (projectService.findById(proID) != null) {
                System.out.println("Project code already exists!");
                proID = null;
            }
        } while (proID == null);

        String devId;
        do {
            devId = ip.getStringAndCheck("Enter new developer code: ", Acceptable.DEV_ID_VALID);
            if (developerService.findById(devId) == null) {
                System.out.println("Developer code must be existed in developers list");
            }
        } while (devId == null);

        String projectName = ip.getStringAndCheck("Enter project name: ", Acceptable.PROJECT_ID_VALID);
        int duration = ip.getInt("Enter duration: ", 1);

        Date startDate = ip.getDateFromUser("Enter start date: ", false);

        projectService.addProject(new Project(proID, devId, projectName, duration, startDate));
        markDirty();
    }

    public void listProjectsGroupedByDeveloper() {

        List<Developer> devs = developerService.listAll();

        if (devs == null || devs.isEmpty()) {
            System.out.println("No developers found.");
            return;
        }

        for (Developer dev : devs) {
            List<Project> projectList = projectService.findProjectByDevId(dev.getDevID());
            viewFormatter.displayProjectsByDeveloper(dev, projectList);
        }
    }

    public void calculateTotalExperience() {
        String devId;

        do {
            devId = ip.getStringAndCheck("Enter developer ID to calculate total experience: ", Acceptable.DEV_ID_VALID);
            if (developerService.findById(devId) == null) {
                System.out.println("Developer \n"
                        + "ID does not exist!");
                devId = null;
            }
        } while (devId == null);

        List<Project> projectList = projectService.findProjectByDevId(devId);
        int totalExp = 0;
        for (Project project : projectList) {
            totalExp += project.getDurationMonth();
        }
        System.out.println("--------------TOTAL DEVELOPER EXPERIENCE--------------");
        System.out.println(developerService.findById(devId));
        System.out.println("Total experience: " + totalExp);
    }

    public void calculateEndDate() {
        int duration = ip.getInt("Enter duration (months): ", 1);
        Date startDate = ip.getDateFromUser("Enter start date (dd/MM/yyyy): ", false);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MONTH, duration);
        Date endDate = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("------ RESULT ------");
        System.out.println("Start date : " + sdf.format(startDate));
        System.out.println("Duration   : " + duration + " months");
        System.out.println("End date   : " + sdf.format(endDate));
        System.out.println("--------------------");
    }

    public void saveProjectToFile() {
        projectService.saveToFile();
    }

}

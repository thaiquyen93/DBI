/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Comparator;
import java.util.List;
import model.Developer;
import service.DeveloperService;
import service.ProjectService;
import util.Acceptable;
import util.Inputter;
import view.ViewFormatter;

/**
     *
 * @author Admin
 */
public class DeveloperController {

    private DeveloperService developerService;
    private ProjectService projectService;
    private ViewFormatter viewFormatter;
    Inputter ip = new Inputter();

    public boolean dirty = false;

    public DeveloperController(DeveloperService developerService, ProjectService projectService) {
        this.developerService = developerService;
        this.projectService = projectService;
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

    public void listAllDeveloper() {
        viewFormatter.displayDevelopers(developerService.listAll());
    }

    public void addNewDeveloper() {
        String devId;
        do {
            devId = ip.getStringAndCheck("Enter new developer code: ", Acceptable.DEV_ID_VALID);
            if (developerService.findById(devId) != null) {
                System.out.println("Developer code already exists!");
                devId = null;
            }
        } while (devId == null);
        String name = ip.getStringAndCheck("Enter developer name: ", Acceptable.DEV_NAME_VALID);
        String lang = ip.getString("Enter programing languages(Separate by ',' if there are more than 2 languages): ");
        double salary = ip.getSalary("Enter salary: ");
        developerService.addDeveloper(new Developer(devId, name, lang, salary));
        markDirty();
    }

    public void searchById() {
        String devId = ip.getStringAndCheck("Enter developer Id to search: ", Acceptable.DEV_ID_VALID);
        Developer dev = developerService.findById(devId);
        if (dev != null) {
            System.out.println(dev);

        } else {
            System.out.println("Developer ID does not exist!");
        }
    }

    public void upadateDeveloper() {
        String devId;

        do {
            devId = ip.getStringAndCheck("Enter developer ID to update: ", Acceptable.DEV_ID_VALID);
            if (developerService.findById(devId) == null) {
                System.out.println("Developer \n"
                        + "ID does not exist!");
                devId = null;
            }
        } while (devId == null);
        System.out.println("----------CURRENT INFORMATION----------");
        Developer d = developerService.findById(devId);
        System.out.println(d);
        double newSalary = ip.getSalary("Enter new salary: ");
        if (newSalary != -1) {
            d.setSalary(newSalary);
        }
        developerService.updateDeveloper(devId, d);
        System.out.println("Developer updated successfully");
        markDirty();
        System.out.println(d);

    }

    public void removeDeveloperById() {
        String devId;
        do {
            devId = ip.getStringAndCheck("Enter developer ID to delete: ", Acceptable.DEV_ID_VALID);
            if (developerService.findById(devId) == null) {
                System.out.println("Developer \n"
                        + "ID does not exist!");
                devId = null;
            }
        } while (devId == null);
        if (projectService.findById(devId) != null) {
            System.out.println("Cannot delete: Developer is assigned to projects.");
        } else {
            developerService.deleteDeveloper(developerService.findById(devId));
            System.out.println("Delete developer successfully");
        }
        markDirty();

    }

    public void findAllDevByLang() {
        String lang = ip.getString("Enter language to search: ");
        viewFormatter.displayDevelopers(developerService.findByLang(lang));
    }

    public void sortDevelopersBySalary() {
        List<Developer> devList = developerService.listAll();

        devList.sort(Comparator.comparing(Developer::getSalary)); //.reversed() muon giam dan

        viewFormatter.displayDevelopers(devList);
    }

    public void saveDeveloperToFile() {
        developerService.getDeveloperDAO().saveToFile();
    }
}

package main;

import controller.DeveloperController;
import controller.ProjectController;
import service.DeveloperService;
import service.ProjectService;
import util.Inputter;

public class Main {

    public static void main(String[] args) {

        DeveloperService developerService = new DeveloperService();
        ProjectService projectService = new ProjectService();

        DeveloperController developerController = new DeveloperController(developerService, projectService);
        ProjectController projectController = new ProjectController(projectService, developerService);
        Inputter ip = new Inputter();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = ip.getInt("Enter your choice (1-13): ", 1, 13);

            switch (choice) {
                case 1:
                    developerController.listAllDeveloper();
                    projectController.listAllProject();
                    break;

                case 2:
                    developerController.addNewDeveloper();
                    break;

                case 3:
                    developerController.searchById();
                    break;

                case 4:
                    developerController.upadateDeveloper();
                    break;

                case 5:
                    developerController.findAllDevByLang();
                    break;

                case 6:
                    projectController.addNewProject();
                    break;

                case 7:
                    projectController.listProjectsGroupedByDeveloper();
                    break;

                case 8:
                    projectController.calculateTotalExperience();
                    break;
                case 9:
                    developerController.removeDeveloperById();
                    break;

                case 10:
                    developerController.sortDevelopersBySalary();
                    break;

                case 11:
                    developerController.saveDeveloperToFile();
                    projectController.saveProjectToFile();
                    break;

                case 12:
                    projectController.calculateEndDate();
                    break;

                case 13:
                    if (developerController.isDirty() || projectController.isDirty()) {
                        boolean confirm = ip.getYesNo("Do you want to save the changes before exiting? (Y/N): ");
                        if (confirm) {
                            developerController.saveDeveloperToFile();
                            projectController.saveProjectToFile();
                        }
                    }
                    running = false;
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== SOFTWARE DEVELOPER MANAGEMENT =====");
        System.out.println("1.  List all Developers");
        System.out.println("2.  Add a new Developer");
        System.out.println("3.  Search for a Developer by ID");
        System.out.println("4.  Update a Developer's salary by ID");
        System.out.println("5.  List all Developers by Language");
        System.out.println("6.  Add a new Project");
        System.out.println("7.  List all Projects by Developer (Grouped)");
        System.out.println("8.  Calculate Total Experience by Dev ID");
        System.out.println("9.  Remove a Developer by ID");
        System.out.println("10. Sort Developers by Salary");
        System.out.println("11. Save data to files");
        System.out.println("12. Calculate Project End Date");
        System.out.println("13. Quit program");
        System.out.println("========================================");
    }
}

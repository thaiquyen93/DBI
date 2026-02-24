/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.text.SimpleDateFormat;
import java.util.List;
import model.Developer;
import model.Project;

/**
 *
 * @author Admin
 */
public class ViewFormatter {

    // Định dạng khung cho bảng Developer 
    private static final String DEV_ROW_FORMAT = "| %-10s | %-20s | %-25s | %10s |%n";
    private static final String PROJECT_ROW_FORMAT = "| %-10s | %-10s | %-20s | %-10s | %-12s |%n";
    private static final String PROJECT_DATA_FORMAT = "| %-10s | %-10s | %-20s | %-10d | %-12s |%n";
    private static final String LINE_SEPARATOR = "------------------------------------------------------------------------------";

    public void displayDevelopers(List<Developer> developers) {

        if (developers == null || developers.isEmpty()) {
            System.out.println("There are no developers in the list.");
            return;
        }
        System.out.println("\n--- DEVELOPER LIST ---");
        System.out.println(LINE_SEPARATOR);
        System.out.printf(DEV_ROW_FORMAT, "ID", "Full Name", "Programming Languages", "Salary ($)");
        System.out.println(LINE_SEPARATOR);

        if (developers.isEmpty()) {
            System.out.println("| " + centerString(72, "Không có dữ liệu") + " |");
        } else {
            for (Developer dev : developers) {
                System.out.printf(
                        DEV_ROW_FORMAT,
                        dev.getDevID(),
                        dev.getName(),
                        dev.getLanguage(),
                        dev.getSalary()
                );
            }
        }

        System.out.println(LINE_SEPARATOR);
    }

    public void displayProjects(List<Project> projects) {

        if (projects == null || projects.isEmpty()) {
            System.out.println("There are no developers in the list.");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        System.out.println("\n--- PROJECT LIST ---");
        System.out.println(LINE_SEPARATOR);
        System.out.printf(PROJECT_ROW_FORMAT, "PROJECT ID", "DEV ID", "PROJECT NAME", "DURATIONS", "START DATE");
        System.out.println(LINE_SEPARATOR);

        if (projects.isEmpty()) {
            System.out.println("| " + centerString(72, "Không có dữ liệu") + " |");
        } else {
            for (Project pro : projects) {
                System.out.printf(
                        PROJECT_DATA_FORMAT,
                        pro.getProjectId(),
                        pro.getDevId(),
                        pro.getProjectName(),
                        pro.getDurationMonth(),
                        sdf.format(pro.getStartDate())
                );
            }
        }

        System.out.println(LINE_SEPARATOR);
    }


    public void displayMenu() {
        System.out.println("\n========== DEV CORP MANAGEMENT SYSTEM ==========");
        System.out.println("1.  List all Developers");
        System.out.println("2.  Add a new Developer");
        System.out.println("3.  Search for a Developer by ID");
        System.out.println("4.  Update a Developer's salary");
        System.out.println("5.  List all Developers by Language");
        System.out.println("6.  Add a new Project");
        System.out.println("7.  List all Projects by Developer");
        System.out.println("8.  Calculate Total Experience");
        System.out.println("9.  Remove a Developer by ID");
        System.out.println("10. Sort Developers by Salary");
        System.out.println("11. Save data to files");
        System.out.println("12. Quit program");
        System.out.print("Please choose an option (1-12): ");
    }


    public void displayProjectsByDeveloper(Developer dev, List<Project> projects) {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    System.out.println("\nDeveloper: " + dev.getDevID() + " - " + dev.getName());

    String line = "+------------+----------------------+------------+--------------+";
    String rowFormat = "| %-10s | %-20s | %10d | %-12s |%n";

    System.out.println(line);
    System.out.printf(
            "| %-10s | %-20s | %-10s | %-12s |%n",
            "PROJECT ID", "PROJECT NAME", "DURATION", "START DATE"
    );
    System.out.println(line);

    if (projects == null || projects.isEmpty()) {
        System.out.printf("| %-58s |%n", "No projects assigned.");
        System.out.println(line);
        return;
    }

    for (Project p : projects) {
        System.out.printf(
                rowFormat,
                p.getProjectId(),
                p.getProjectName(),
                p.getDurationMonth(),
                sdf.format(p.getStartDate())
        );
    }

    System.out.println(line);
}




    // Hỗ trợ căn giữa text trong bảng
    private String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}

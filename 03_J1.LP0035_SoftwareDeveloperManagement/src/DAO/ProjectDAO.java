/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import model.Project;

/**
 *
 * @author Admin
 */
public class ProjectDAO implements Persistable<Project>{
    List<Project> projects = new ArrayList<>();
    private String PATH_FILE = "projects.txt";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ProjectDAO() {
        loadFromFile();
    }
    
public void loadFromFile() {

    projects.clear(); // tránh trùng dữ liệu

    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_FILE)))) {

        String line;

        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",\\s*");

            if (parts.length != 5) {
                System.out.println("Invalid line format: " + line);
                continue;
            }

            String projectId = parts[0].trim();
            String devId = parts[1].trim();
            String projectName = parts[2].trim();

            int projectDuration;
            try {
                projectDuration = Integer.parseInt(parts[3].trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid duration: " + line);
                continue;
            }

            Date projectStartDate;
            try {
                projectStartDate = sdf.parse(parts[4].trim());
            } catch (Exception e) {
                System.out.println("Invalid date: " + line);
                continue;
            }

            projects.add(new Project(projectId, devId, projectName, projectDuration, projectStartDate));
        }

        System.out.println("Data " + PATH_FILE + " loaded successfully!");

    } catch (Exception e) {
        System.out.println("Cannot load file " + PATH_FILE);
    }
}
    
   public void saveToFile() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_FILE))) {

        for (Project project : projects) {

            String formattedDate = sdf.format(project.getStartDate());

            bw.write(String.format("%s, %s, %s, %d, %s",
                    project.getProjectId(),
                    project.getDevId(),
                    project.getProjectName(),
                    project.getDurationMonth(),
                    formattedDate));

            bw.newLine();
        }

        System.out.println("Project save successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    
    @Override
    public void add(Project obj) {
        projects.add(obj);
    }

    @Override
    public void update(String id, Project obj) {
        Project temp = findById(id);
        if (temp != null) {
            projects.remove(temp);
            projects.add(obj);
        }
    }

    @Override
    public void delete(Project obj) {
        projects.remove(obj);
    }

    @Override
    public Project findById(String id) {
        for (Project project : projects) {
            if (project.getDevId().equalsIgnoreCase(id)){
                return project;
            }
        }
        return null;
    }
  public List<Project> findProjectByDevId(String devId) {
      List<Project> projectsOfDev = new ArrayList<>();
        for (Project project : projects) {
            if (project.getDevId().trim().equalsIgnoreCase(devId.trim())){
                projectsOfDev.add(project);
            }
        }
        return projectsOfDev;
    }
    @Override
    public Collection<Project> listAll() {
        return projects;
    }

  
}

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
        FileInputStream fls = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        
        try {
            fls = new FileInputStream(PATH_FILE);
            isr = new InputStreamReader(fls);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String projectId = parts[0].trim();
                String devId = parts[1].trim();
                String projectName = parts[2].trim();
                int projectDuration = Integer.parseInt(parts[3].trim());
                String dateStart = parts[4].trim();
                Date projectStartDate = sdf.parse(dateStart);
                projects.add(new Project(projectId, devId, projectName, projectDuration, projectStartDate));
 
            }
                System.out.println("Data "+PATH_FILE+" loading successfully!");
        } catch (Exception e) {
            System.out.println("Load file " + PATH_FILE +" error!");
        }
        finally {
            try {
                if (fls != null) {
                    fls.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
    
    public void saveToFile() {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_FILE, true))) {
            for (Project project : projects) {
                bw.write(String.format("%s, %s, %s, %s",project.getProjectName(), project.getDevId(), project.getDurationMonth(), project.getStartDate()));
                bw.newLine();
            }
            System.out.println("Project save successfully!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.ProjectDAO;
import java.util.ArrayList;
import java.util.List;
import model.Project;

/**
 *
 * @author Admin
 */
public class ProjectService {
    ProjectDAO projectDAO = new ProjectDAO();
   
//    public ProjectDAO getProjectDAO() {
//        return projectDAO;
//    }

    public void addProject(Project obj) {
        projectDAO.add(obj);
    }
    public void saveToFile(){
        projectDAO.saveToFile();
    }
    public Project findById(String id) {
        return projectDAO.findById(id);
    }

    public List<Project> findProjectByDevId(String devId) {
        return projectDAO.findProjectByDevId(devId);
    }
    

    public void updateProject(String id, Project newProject) {
        projectDAO.update(id, newProject);
    }

    public void deleteProject(Project obj) {
        projectDAO.delete(obj);
    }

    public List<Project> listAll() {
        return (List<Project>) projectDAO.listAll();
    }

    public List<Project> findByName(String name) {
        List<Project> lists = new ArrayList<>();
        for (Project c : projectDAO.listAll()) {
            if (c.getDevId().toLowerCase().trim().contains(name.toLowerCase().trim())) {
                lists.add(c);
            }
        }
        return lists;
    }
    
}

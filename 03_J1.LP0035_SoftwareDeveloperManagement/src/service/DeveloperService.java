/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.DeveloperDAO;
import java.util.ArrayList;
import java.util.List;
import model.Developer;

/**
 *
 * @author Admin
 */
public class DeveloperService {
    DeveloperDAO developerDAO = new DeveloperDAO();
    
    public DeveloperDAO getDeveloperDAO() {
        return developerDAO;
    }

    public void addDeveloper(Developer obj) {
        developerDAO.add(obj);
    }

    public Developer findById(String id) {
        return developerDAO.findById(id);
    }
    
    public List<Developer> findByLang(String lang) {
        return developerDAO.findByLang(lang);
    }

    public void updateDeveloper(String id, Developer newDeveloper) {
        developerDAO.update(id, newDeveloper);
    }

    public void deleteDeveloper(Developer obj) {
        developerDAO.delete(obj);
    }

    public List<Developer> listAll() {
        return (List<Developer>) developerDAO.listAll();
    }

    public List<Developer> findByName(String name) {
        List<Developer> lists = new ArrayList<>();
        for (Developer c : developerDAO.listAll()) {
            if (c.getName().toLowerCase().trim().contains(name.toLowerCase().trim())) {
                lists.add(c);
            }
        }
        return lists;
    }
    
}

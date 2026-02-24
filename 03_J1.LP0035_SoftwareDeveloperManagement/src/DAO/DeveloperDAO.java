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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Developer;

/**
 *
 * @author Admin
 */
public class DeveloperDAO implements Persistable<Developer>{
    List<Developer> developers = new ArrayList<>();
    private String PATH_FILE = "developers.txt";

    public DeveloperDAO() {
        loadFromFile();
    }
    
public void loadFromFile() {
    try (BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(PATH_FILE)))) {

        String line;
        while ((line = br.readLine()) != null) {

            // DEV001, Nguyen Van A, [Java, C++], 5000
            int i1 = line.indexOf('[');
            int i2 = line.indexOf(']');

            if (i1 == -1 || i2 == -1) continue; // dòng lỗi, bỏ qua

            // Phần trước [
            // "DEV001, Nguyen Van A, "
            String before = line.substring(0, i1).trim();
            String[] info = before.split(",\\s*", 2);//2

            String devId = info[0];
            String devName = info[1].replace(",", "");

            // Phần trong []
            String languages = line.substring(i1 + 1, i2).trim();

            // Phần sau ]
            // ", 5000"
            String salaryPart = line.substring(i2 + 1).replace(",", "").trim();
            double salary = Double.parseDouble(salaryPart);

            developers.add(new Developer(devId, devName, languages, salary));
        }

        System.out.println("Data loading successfully!");
    } catch (Exception e) {
        System.out.println("Load file " + PATH_FILE + " error!");
    }
}

    
    public void saveToFile() {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_FILE))) {
            for (Developer dev : developers) {
                
                String devName = dev.getName();
                String devId = dev.getDevID();
                String devLang = "[" + dev.getLanguage() + "]";
                double devSalary = dev.getSalary();
                bw.write(String.format("%s, %s, %s, %.0f",devId, devName, devLang, devSalary));
                bw.newLine();
            }
            System.out.println("Developer save successfully!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void add(Developer obj) {
        developers.add(obj);
    }

    @Override
    public void update(String id, Developer obj) {
        Developer dev = findById(id);
        if (dev != null) {
            developers.remove(dev);
            developers.add(obj);
        }
    }

    @Override
    public void delete(Developer obj) {
        developers.remove(obj);
    }

    @Override
    public Developer findById(String id) {
        for (Developer developer : developers) {
            if (developer.getDevID().equalsIgnoreCase(id)) {
                return developer;
            }
        
        }
        return null;
    }
    public List<Developer> findByLang(String lang) {
        List<Developer> devList = new ArrayList<>();
        for (Developer developer : developers) {
            if (developer.getLanguage().contains(lang)) {
                devList.add(developer);
            }
        
        }
        return devList;
    }

    @Override
    public Collection<Developer> listAll() {
        return developers;
    }

  
    
}

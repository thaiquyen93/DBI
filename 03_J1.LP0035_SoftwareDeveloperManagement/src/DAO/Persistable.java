/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.Collection;

/**
 *
 * @author as
 */
public interface Persistable<T> {
    
    void add(T obj);

    void update(String id, T obj);

    void delete(T obj);

    T findById(String id);

    Collection<T> listAll();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasemanagement;

import java.util.Date;

/**
 *
 * @author Tudor Onofrei
 */
public class Team implements Comparable<Team>{
    private String name;
    private Date foundingDate;
    private Integer idCity;
    private Integer id;
    
    public Team(Integer id, String name, Date foundingDate, Integer idCity){
        this.setId(id);
        this.setName(name);
        this.setIdCity(idCity);
        this.setFoundingDate(foundingDate);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer id_city) {
        this.idCity = id_city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(Team o) {
        if(this.getId() > o.getId()){
            return 1;
        }
        else if(this.getId() < o.getId()){
            return -1;
        }
        else{
            return 0;
        }
    }
}

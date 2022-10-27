/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasemanagement;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Tudor Onofrei
 */
public interface InstanceDAO {
    public Team getTeamById(int id);
    public Team getTeamByName(String numeInstanta);
    public List<Team> getAllTeams();
    
    public void insertTeam(String nume, int idCity, Date foundingDate);
    
    public void updateTeamName(Team teamToUpdate, String name);
    public void updateTeamIdCity(Team teamInstance, int newIdCity);
    public void updateTeamFoundingDate(Team teamInstance, Date newFoundingDate);
    
    public void deleteTeamById(int teamId);
    public void deleteTeamByName(String teamName);

    public Team findById(int teamId);
    public Team findByName(String numeInstanta);
} 

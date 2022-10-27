/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import databasemanagement.Team;
import javax.inject.Named;
import databasemanagement.InstanceManipulator;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Tudor Onofrei
 */
@Named(value = "teamBean")
@ViewScoped
public class TeamBean implements Serializable {
    private List<Team> teams;
    private InstanceManipulator instanceManipulator;
    
    @PostConstruct
    public void init(){
        instanceManipulator = new InstanceManipulator();
        teams = instanceManipulator.getAllTeams();
    }
    
    public List<Team> getTeams(){
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    public void addTeam(Team team){
        teams.add(team);
    }
}

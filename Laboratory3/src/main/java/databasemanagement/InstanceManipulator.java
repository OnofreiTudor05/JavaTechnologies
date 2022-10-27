/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasemanagement;

import java.sql.Driver;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tudor Onofrei
 */
public class InstanceManipulator implements InstanceDAO{
    private Connection connectionInstance = DatabaseConnector.getConnectionInstance();
    private List<Team> teams;

    public InstanceManipulator() {
        teams = this.getAllTeams();
    }

    @Override
    public Team getTeamById(int id) {
        Team foundTeam = null;
        String query = "SELECT * FROM teams WHERE id = " + id;
        Statement state = null;
        ResultSet rs = null;
        try {
            state = connectionInstance.createStatement();
            rs = state.executeQuery(query);

            if (rs.next()) {
                foundTeam = new Team(rs.getInt("id"), rs.getString("name"), rs.getDate("founding_date"), rs.getInt("id_city"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foundTeam;
    }

    @Override
    public Team getTeamByName(String teamName) {
        Team foundTeam = null;
        String query = "SELECT * FROM teams WHERE name =" + " '" + teamName + "';";
        Statement state = null;
        ResultSet rs = null;
        try {
            state = connectionInstance.createStatement();
            rs = state.executeQuery(query);

            if (rs.next()) {
                foundTeam = new Team(rs.getInt("id"), rs.getString("nume"), rs.getDate("founding_date"), rs.getInt("id_city"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foundTeam;
    }

    @Override
    public List<Team> getAllTeams() {
        if(teams == null){
            teams = new ArrayList<>();
            
            String query = "SELECT * FROM teams;";
            Statement state = null;
            ResultSet rs = null;
            try {
                state = connectionInstance.createStatement();
                rs = state.executeQuery(query);
                System.out.print("\n\n\n I'm here! \n\n\n");
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString("name");

                    Date foundingDate = rs.getDate("founding_date");
                    Integer idCity = rs.getInt("id_city");

                    Team newTeam = new Team(id, name, foundingDate, idCity);

                    teams.add(newTeam);
                }
                Collections.sort(teams, new Comparator<Team>(){
                    public int compare(Team team1, Team team2){
                        return team1.compareTo(team2);
                    }
                });

            } catch (SQLException ex) {
                Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return teams;
    }

    @Override
    public void updateTeamName(Team teamToUpdate, String name) {
        String query = "UPDATE teams SET name =" + "'" + name + "' WHERE name = '" + teamToUpdate.getName() + "';";
        Statement state = null;
        try {
            state = connectionInstance.createStatement();
            state.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Numele instantei a fost updatat cu succes!");
        }
    }

    @Override
    public void updateTeamIdCity(Team teamToUpdate, int newCityId) {
        String query = "UPDATE teams SET id_city =" + "'" + newCityId + "' WHERE id = " + teamToUpdate.getId() + ";";
        Statement state = null;
        try {
            state = connectionInstance.createStatement();
            state.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Numele instantei a fost updatat cu succes!");
        }
    }

    @Override
    public void updateTeamFoundingDate(Team teamToUpdate, Date newFoundingDate) {
        String query = "UPDATE teams SET founding_date =" + "'" + newFoundingDate + "' WHERE id = " + teamToUpdate.getId() + ";";
        Statement state = null;
        try {
            state = connectionInstance.createStatement();
            state.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Numele instantei a fost updatat cu succes!");
        }

    }

    @Override
    public void deleteTeamById(int idTeam) {
        String query = "DELETE FROM teams WHERE id = " + idTeam + ";";
        Statement state = null;
        try {
            state = connectionInstance.createStatement();
            state.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Numele instantei a fost updatat cu succes!");
        }
    }

    @Override
    public void deleteTeamByName(String name) {
        String query = "DELETE FROM teams WHERE name = '" + name + "';";
        Statement state = null;
        try {
            state = connectionInstance.createStatement();
            state.execute(query);
          
        } catch (SQLException ex) {
            Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Numele instantei a fost updatat cu succes!");
        }
    }

    @Override
    public void insertTeam(String teamName, int idCity, Date foundingDate) {
        try {
            int maxId = 0;
            String maxIdAsString = "SELECT * FROM `teams` ORDER BY id DESC LIMIT 1";
            Statement statement1 = connectionInstance.createStatement();
            ResultSet rs = statement1.executeQuery(maxIdAsString);
            if (rs.next()) {
                maxId = rs.getInt("id");
            }
            maxId++;

            String sql = "INSERT INTO `teams`(`id`, `name`, `founding_date`, `id_city`) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connectionInstance.prepareStatement(sql);

            statement.setInt(1, maxId);
            statement.setString(2, teamName);
            statement.setString(3, foundingDate.toString());
            statement.setInt(4, idCity);

            int row = statement.executeUpdate();
            
            if (row > 0) {
                System.out.println("Instanta adaugata cu succes!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Team findById(int id) {
        for (Team instantaInLista : teams) {
            if (instantaInLista.getId() == id) {
                return instantaInLista;
            }
        }
        return null;
    }

    @Override
    public Team findByName(String name) {
        for (Team instantaInLista : teams) {
            if (instantaInLista.getName().equals(name)) {
                return instantaInLista;
            }
        }
        return null;
   }
   
   public void closeConnection(){
        if(connectionInstance != null){
            try {
                connectionInstance.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstanceManipulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

package dao2;


import java.util.*;
import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mano
 */
public class Genres {
    Genres(){};
    public void create(String idGen,String name)throws SQLException{
        Connection connection = Database.getConnection();
                    connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO genres(id_gen,name) values(?,?) ")){
                preparedStatement.setString(1,idGen);
                preparedStatement.setString(2,name);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.commit();
        }
       
    }
    public String findByName(String name) throws SQLException {
                Connection connection = Database.getConnection();
                String sql="SELECT id FROM genres WHERE name='"+name+"'";
                Statement statement=connection.prepareStatement(sql);
                ResultSet resultSet=statement.executeQuery(sql);
                if(resultSet.next()) {return resultSet.getString(1);}
                return null;
        }
 }



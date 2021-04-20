package dao2;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mano
 */
public class Directors {
        Directors(){};
    public void create(String idMovie,String name)throws SQLException{
        Connection connection = Database.getConnection();
                    connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO directors(id_movie,name) values(?,?) ")){
            
                     preparedStatement.setString(1,idMovie);
                     preparedStatement.setString(2,name);
                     preparedStatement.executeUpdate();
                     preparedStatement.close();
                     connection.commit();
        }
    }
       
    public String findByName(String name) throws SQLException {
                Connection conn = Database.getConnection();
                //here should be a select which get the name of the movie
                String sql="SELECT title from movies where id_movie in (select id_movie FROM directors WHERE name='"+name+"') ";
                Statement statement=conn.prepareStatement(sql);
                ResultSet resultSet=statement.executeQuery(sql);
                if(resultSet.next()) {return resultSet.getString(1);}
                return null;
        }
}

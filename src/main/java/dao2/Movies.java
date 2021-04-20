package dao2;


import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mano
 */
public class Movies {
    Movies(){};
    public void create(String idMovie,String title, String releaseDate, int duration, int score)throws SQLException, ParseException{
        Connection connection = Database.getConnection();
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        Date date = (Date) format.parse(releaseDate);
//        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//         String result = df.format(date);
            connection.setAutoCommit(false);

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO movies(id_movie,title,release_date,duration,score) values(?,?,?,?,?) ")){
            
                     preparedStatement.setString(1,idMovie);
                     preparedStatement.setString(2,title);
                     preparedStatement.setString(3,releaseDate);
                     preparedStatement.setInt(4,duration);
                     preparedStatement.setInt(5,score);
                     preparedStatement.executeUpdate();
                     preparedStatement.close();
                     connection.commit();
        }
    }
       
    public String findByName(String name) throws SQLException {
                Connection conn = Database.getConnection();
                String sql="SELECT id FROM movies WHERE name='"+name+"'";
                Statement statement=conn.prepareStatement(sql);
                ResultSet resultSet=statement.executeQuery(sql);
                if(resultSet.next()) {return resultSet.getString(1);}
                return null;
        }
}


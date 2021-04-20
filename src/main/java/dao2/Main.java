package dao2;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mano
 */
public class Main {
  public static void importData() throws SQLException{
//            String csv = "./resources/movies.csv";
//   Reader reader = new BufferedReader(new FileReader("./scripts/databaseScript.sql"));
        //int batchSize = 20;
        Connection connection = Database.getConnection();
        connection.setAutoCommit(false);
        try {
 
 
//            String sql = "INSERT INTO movies (course_name, student_name, timestamp, rating, comment) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader("/Users/mano/NetBeansProjects/lab8/src/main/java/resources/movies.csv"));
            String lineText = null;
 
            int id = 0;
 
            lineReader.readLine(); // skip header line
      Genres genre = new Genres();
      Actors actor = new Actors();
       Directors director = new Directors();
       Movies movie = new Movies();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                
                String[] splitValues = data[12].split("  ");
                    String idMovie = data[0];
                int lengthOfSplitValues = splitValues.length - 1;
                while(lengthOfSplitValues > 0 && id < 200){
                  actor.create(idMovie,splitValues);
                    id++;
                    lengthOfSplitValues--;
                }
            
                String title = data[1];
                String releaseDate = data[4];
                String gen = data[5];
                String duration = data[6];
                String directorName = data[9];
             //   String actorsName = data[12];
                String score = data[14];
                
          //       System.out.println(data);
   
  //int scor = int(score);
  int scor=Integer.parseInt(score);  
  int durate=Integer.parseInt(duration);  
  
    movie.create(idMovie,title,releaseDate,durate,scor);
//    
// 
   genre.create(genre.findByName(gen), gen);
//    
//  
 
//   
    director.create(director.findByName(directorName),directorName);
    Database.commit(); 
            }
 
          lineReader.close();
 
            // execute the remaining queries
//            statement.executeBatch();
 
            connection.commit();
            connection.close();
 
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (ParseException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
}
    public static void main(String args[])throws SQLException, ParseException, FileNotFoundException, IOException {
        //I used useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
        //in order to solve an error :
        //the server time zone value 'EEST' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the 'serverTimezone' configuration property) to use a more specific time zone value if you want to utilize time zone support
           String url = "jdbc:mysql://localhost:3306/databaseScript?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
           String user ="root";
           String password = "password";

   
try {
      Connection connection = DriverManager.getConnection(url,user,password );
      System.out.println("Connected to the database");
      
//  //Initialize the script runner
//      ScriptRunner sr = new ScriptRunner(connection);
//      //Creating a reader object
//      Reader reader = new BufferedReader(new FileReader("./scripts/databaseScript.sql"));
//      //Running the script
//      sr.runScript(reader);
         //      Process exec = Runtime.getRuntime().exec(  new String [] {"mysql", "-u", "root", "-pmanager", "-e", "show databases"} );

    Movies movie = new Movies();
    movie.create("1001","Movie1001","2017-05-10", 12, 234);
    
    Genres genre = new Genres();
    genre.create("121", "Comedy");
    
    Actors actor = new Actors();
               String[] actorName ={"Ion", "Marin"};
    actor.create("1001",actorName);
    
    Directors director = new Directors();
    director.create("1001","George Apetrei");
    Database.commit(); 
    importData();

} catch(SQLException e) {
  System.err.println("Cannot connect to DB: " + e);
  Database.rollback();
}
    }
}




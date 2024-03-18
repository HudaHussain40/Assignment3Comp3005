package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
public class ConnectDB {
    Connection connection=null;
    
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
       
        ConnectDB db = new ConnectDB("jdbc:postgresql://localhost:5432/Assignment3", "postgres", "1");
        try {
            if(db.connection!=null) {
                System.out.println("Connected to server");
                //System.out.println("Getting All Students");
                //db.getAllStudents();
                //System.out.println("Inserting new student");
                //db.addStudent("test", "student", "test@student.com","2023-10-12");
                //System.out.println("Getting All Students");
                //db.getAllStudents();
                //System.out.println("Updating new student");
                //db.updateStudentEmail(4,"newtest@student.com");
                //System.out.println("Getting All Students");
                //db.getAllStudents();
                //System.out.println("Deleting new student");
                //db.deleteStudent(6);
                //System.out.println("Getting All Students");
                //db.getAllStudents();

            } else {
                System.out.println("Connection failed");
                
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*
     * This function creates a ConnectDB object, and attempts to connect to a postgresql server
     * using the url, username and password provided.
     */
    public ConnectDB(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            connection = null;
        }
        
    }
    /*
     * This function uses the connection to get the table students from the database
     * and then prints out the column.
     */
    public void getAllStudents() {
        try {
            Statement stmt = connection.createStatement();
            String SQL = "SELECT * from students";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()) { 
                int id = rs.getInt("student_id");
                String name = rs.getString("first_name") + " " + rs.getString("last_name");
                String email = rs.getString("email");
                Date enrollment_date = rs.getDate("enrollment_date");
                System.out.println("Student id: "+ id + ", Name: "+name+", Email: "+ email + ", Enrollment Date: "+ enrollment_date);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*
     * This function uses the connection to connect to the Database and creates a prepeared statement
     * and update the students table from on database with the given arguments
     */
    public void addStudent(String first_name,String last_name, String email, String enrollment_date) {
        String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, java.sql.Date.valueOf(enrollment_date));
            pstmt.executeUpdate();
            System.out.println("Inserted new student");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    /*
     * This function uses the connection to connect to the Database and creates a prepeared statement
     * and update the given student's email using the students id.
     */
    public void updateStudentEmail(int student_id, String email) {
        String insertSQL = "UPDATE students SET email=? WHERE student_id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, email);
            pstmt.setInt(2, student_id);
            pstmt.executeUpdate();
            System.out.println("Updated student");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    /*
     * This function uses the connection to connect to the Database and creates a prepeared statement
     * and deletes the students table from on database with the given student ID
     */
    public void deleteStudent(int student_id) {
        String insertSQL = "DELETE FROM students WHERE student_id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();
            System.out.println("Deleted Student");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

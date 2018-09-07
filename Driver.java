import java.sql.*;

public class Driver {

    public static void main(String[] args){

        try{
        // 1. acquire the connection to the database
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tester","allhoststudent", "student123" );
            /*Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from tester1");
            while(myRs.next()){
                System.out.println("Your id is: " + myRs.getString("id")) ;
                System.out.println(myRs.getString("first_name"));
            }*/
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
}

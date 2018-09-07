import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CurrentDAO {

    private static Connection myConn;

    public CurrentDAO() throws Exception {
        //get db properties
        Properties props = new Properties();
        props.load(new FileInputStream("/home/alvin/IA/SQL/inventory.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");

        //connect to db
        myConn = DriverManager.getConnection(dburl, user, password);
        System.out.println("Connection successful to: " + dburl);
    }
    public void addPurchase(item theItem) throws Exception{
        PreparedStatement myStmt = null;
        try {
            // prepare statement
            myStmt = myConn.prepareStatement("insert into purchases"
                    + " (item_name, id, list_price, sell_price, quantity, item_type, date_added)"
                    + " values (?, ?, ?, ?, ?, ?, ?)");

            // set params
            myStmt.setString(1, theItem.getName());
            myStmt.setInt(2, theItem.getId());
            myStmt.setBigDecimal(3, theItem.getListPrice());
            myStmt.setBigDecimal(4, theItem.getSellPrice());
            myStmt.setInt(5, theItem.getQuantity());
            myStmt.setString(6, theItem.getTypeOfItem());
            myStmt.setString(7, theItem.getDateAdded());
            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }
    }
    public void addItem(item theItem) throws Exception {
        PreparedStatement myStmt = null;

        try {
            // prepare statement
            myStmt = myConn.prepareStatement("insert into current"
                    + " (item_name, id, list_price, sell_price, quantity, item_type, date_added)"
                    + " values (?, ?, ?, ?, ?, ?, ?)");

            // set params
            myStmt.setString(1, theItem.getName());
            myStmt.setInt(2, theItem.getId());
            myStmt.setBigDecimal(3, theItem.getListPrice());
            myStmt.setBigDecimal(4, theItem.getSellPrice());
            myStmt.setInt(5, theItem.getQuantity());
            myStmt.setString(6, theItem.getTypeOfItem());
            myStmt.setString(7, theItem.getDateAdded());
            // execute SQL
            myStmt.executeUpdate();

            myStmt = myConn.prepareStatement("insert into history"
                    + " (item_name, id, list_price, sell_price, quantity, item_type, date_added)"
                    + " values (?, ?, ?, ?, ?, ?, ?)");

            // set params
            myStmt.setString(1, theItem.getName());
            myStmt.setInt(2, theItem.getId());
            myStmt.setBigDecimal(3, theItem.getListPrice());
            myStmt.setBigDecimal(4, theItem.getSellPrice());
            myStmt.setInt(5, theItem.getQuantity());
            myStmt.setString(6, theItem.getTypeOfItem());
            myStmt.setString(7, theItem.getDateAdded());
            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }

    }
    public void updateItem(item theItem) throws Exception{
        PreparedStatement myStmt = null;

        try{
            myStmt = myConn.prepareStatement("update current"
                    + " set item_name = ?, list_price = ?, sell_price = ?, quantity = ?, item_type = ?, date_added = ? where id=?");
            // set params
            myStmt.setString(1, theItem.getName());
            myStmt.setBigDecimal(2, theItem.getListPrice());
            myStmt.setBigDecimal(3, theItem.getSellPrice());
            myStmt.setInt(4, theItem.getQuantity());
            myStmt.setString(5, theItem.getTypeOfItem());
            myStmt.setString(6, theItem.getDateAdded());
            myStmt.setInt(7, theItem.getId());
            // execute SQL
            myStmt.executeUpdate();
        }
        finally {
            close(myStmt);
        }
    }
    public void replaceQuantity(item theItem) throws Exception{
        PreparedStatement myStmt = null;
        try{
            myStmt = myConn.prepareStatement("update current set quantity = ? where id = ?");
            // set params
            myStmt.setInt(1, theItem.getQuantity());
            myStmt.setInt(2, theItem.getId());
            System.out.println(theItem.getQuantity());
            System.out.println(theItem.getId());
            myStmt.executeUpdate();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        finally {
            close(myStmt);
        }
    }
    private  item convertRowToItem(ResultSet Rs) throws SQLException {
        String name = Rs.getString("item_name");
        int id = Rs.getInt("id");
        BigDecimal listPrice = Rs.getBigDecimal("list_price");
        BigDecimal sellPrice = Rs.getBigDecimal("sell_price");
        int quantity = Rs.getInt("quantity");
        String typeOfItem = Rs.getString("item_type");
        String dateAdded = Rs.getString("date_added");

        item tempItem = new item(name, id, listPrice, sellPrice, quantity, typeOfItem, dateAdded);
        return tempItem;
    }
    public  List<item> getPurchasesDB() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from purchases");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getCurrentdb() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from current");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getCurrentdbAscending() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from current order by quantity");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getCurrentdbDescending() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from current order by quantity desc");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getHistoryDB() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from history");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getHistoryDBAscending() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from history order by quantity");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getHistoryDBDescending() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from purchases order by quantity desc");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getPurchasesAscending() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from purchases order by quantity");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }
    public  List<item> getPurchasesDescending() throws Exception {
        List<item> list = new ArrayList<>();
        Statement Stmt = null;
        ResultSet Rs = null;

        try {
            Stmt = myConn.createStatement();
            Rs = Stmt.executeQuery("USE INVENTORY");
            Rs = Stmt.executeQuery("select * from history order by quantity desc");
            while (Rs.next()) {
                item tempItem = convertRowToItem(Rs);
                list.add(tempItem);
            }
            return list;
        } finally {
            close(Stmt, Rs);
        }
    }

    public List<item> searchEmployeesCurrent(String lastName) throws Exception {
        List<item> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("select * from current where item_name like ?  order by item_name");
            myStmt.setString(1, lastName);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                item tempItem = convertRowToItem(myRs);
                list.add(tempItem);
            }
            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }
    public List<item> searchEmployeesHistory(String lastName) throws Exception {
        List<item> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("select * from current where item_name like ?  order by item_name");
            myStmt.setString(1, lastName);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                item tempItem = convertRowToItem(myRs);
                list.add(tempItem);
            }
            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }
    public List<item> searchEmployeesPurchases (String lastName) throws Exception {
        List<item> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("select * from current where item_name like ?  order by item_name");
            myStmt.setString(1, lastName);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                item tempItem = convertRowToItem(myRs);
                list.add(tempItem);
            }
            return list;
        }
        finally {
            close(myStmt, myRs);
        }
    }
    public List<item> filterCurrent (String itemType) throws Exception {
        List<item> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try{
            myStmt = myConn.prepareStatement("select * from current where item_type = ?");
            myStmt.setString(1, itemType);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                item tempItem = convertRowToItem(myRs);
                list.add(tempItem);
            }
            return list;
        }
        finally{
            close(myStmt, myRs);
        }
    }
    public List<item> filterHistory (String itemType) throws Exception {
        List<item> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try{
            myStmt = myConn.prepareStatement("select * from history where item_type = ?");
            myStmt.setString(1, itemType);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                item tempItem = convertRowToItem(myRs);
                list.add(tempItem);
            }
            return list;
        }
        finally{
            close(myStmt, myRs);
        }
    }
    public List<item> filterPurchases (String itemType) throws Exception {
        List<item> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try{
            myStmt = myConn.prepareStatement("select * from purchases where item_type = ?");
            myStmt.setString(1, itemType);
            myRs = myStmt.executeQuery();
            while (myRs.next()) {
                item tempItem = convertRowToItem(myRs);
                list.add(tempItem);
            }
            return list;
        }
        finally{
            close(myStmt, myRs);
        }
    }

    private static void close(Statement Stmt, ResultSet Rs) throws SQLException{
        close(null, Stmt, Rs);
    }

    private static void close(Connection myConn, Statement Stmt, ResultSet Rs) throws SQLException {
        if (Rs != null) {
            Rs.close();
        }
        if (Stmt != null) {
            Stmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }
    private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }

    public static void main(String[] args) throws Exception {
        try {
            CurrentDAO dao = new CurrentDAO();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}

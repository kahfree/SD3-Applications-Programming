package sd3.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


/**
 *
 * @author alan.ryan
 */
public class CustomerDB {
    static Connection connection;
    static QueryRunner runner;
        
    ///////////////////////////////////////////////////////////////////////////////////
    public static void doConnection() {
        try {
            //connect to DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
            //create runner
            runner = new QueryRunner();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }//end doConnection
    
    ///////////////////////////////////////////////////////////////////////////////////
    public static void closeConnection() {
        try {
            DbUtils.close(connection);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//end closeConnection
    
    ///////////////////////////////////////////////////////////////////////////////////
    public static List<Customer> getAllCustomers() throws SQLException {
        
        //my handler will convert rows in the DB to Author objects and place them in a list
        ResultSetHandler<List<Customer>> handler = new BeanListHandler(Customer.class);
        
        //execute the query and populate the list
        return runner.query(connection, "SELECT * FROM customers ORDER BY customerName ASC ", handler);
        
     //   return list;
    }//end getAllAuthors
    
        ///////////////////////////////////////////////////////////////////////////////////
    public static List<Customer> getAllCustomersInDateRange(String sql, String year) throws SQLException {
        
        //my handler will convert rows in the DB to Author objects and place them in a list
        ResultSetHandler<List<Customer>> handler = new BeanListHandler(Customer.class);
        
        //execute the query and populate the list
        List<Customer> list = runner.query(connection, sql, handler, year);
        
        return list;
    }//end getAllAuthors
    
    

     ///////////////////////////////////////////////////////////////////////////////////
    public static List<Customer> getHighestCreditLimit(int limit) throws SQLException {
        
        //my handler will convert rows in the DB to Author objects and place them in a list
        ResultSetHandler<List<Customer>> handler = new BeanListHandler(Customer.class);
        
        //execute the query and populate the list
        List<Customer> list = runner.query(connection, "SELECT * FROM customers ORDER BY creditLimit DESC LIMIT ? ", handler, limit);
        
        return list;
    }//end getAllAuthors


    ///////////////////////////////////////////////////////////////////////////////////
    public static int executeSQLStmt(String sql) throws SQLException {
        return runner.execute(connection, sql);
    }

    public static int[] batchACustomerInsert(Object[][] params) throws SQLException {
         return runner.batch(connection, "INSERT INTO customers (customerName, contactLastName,contactFirstName, datejoined) VALUES (?,?,?,?)", params);

    }
    
}

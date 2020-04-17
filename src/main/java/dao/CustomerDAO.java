package dao;

import connection.ConnectionFactory;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO {

    protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private static final String insertStatementString =
            "INSERT INTO customer (idCustomer, nameCustomer,phoneCustomer,emailCustomer,addressCustomer)" + " VALUES (?,?,?,?,?)";
    private static final String updateStatementString = "UPDATE customer SET nameCustomer=?, phoneCustomer=?, emailCustomer=?, addressCustomer=?" +
            " WHERE idCustomer=?";
    private static final String deleteStatementString = "DELETE FROM customer where idCustomer = ?";
    private final static String findStatementString = "SELECT * FROM customer wh" +
            "ere idCustomer = ?";

    /**
     * This method gets the list of customers from the db
     * it does not have any parameters
     *
     * @return customerArrayList - the array lists of all the customers
     */
    public static ArrayList<Customer> getTheCustomerList() {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        try {
            Connection dbConnection = ConnectionFactory.getConnection();
            String query = "SELECT * FROM customer";
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Customer customer;
            while (resultSet.next()) {
                customer = new Customer(resultSet.getInt("idCustomer"),
                        resultSet.getString("nameCustomer"),
                        resultSet.getString("phoneCustomer"),
                        resultSet.getString("emailCustomer"),
                        resultSet.getString("addressCustomer")
                );
                customerArrayList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerArrayList;
    }

    /**This method finds and returns if positive the customer by being given its id
     * @param customerId - the id of the customer to be found
     * @return customer Customer if positive, else null
     */
    public static Customer findCustomerById(int customerId) {
        Connection databaseConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = databaseConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, customerId);
            resultSet = findStatement.executeQuery();

            if (resultSet.next()) {
                String nameCustomer = resultSet.getString("nameCustomer");
                String phoneCustomer = resultSet.getString("phoneCustomer");
                String emailCustomer = resultSet.getString("emailCustomer");
                String addressCustomer = resultSet.getString("addressCustomer");
                return new Customer(nameCustomer, phoneCustomer, emailCustomer, addressCustomer);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:findCustomerById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(databaseConnection);
        }
        return null;
    }

    /**This method inserts a new customer
     * @param customer - the customer to be inserted
     * @return insertedId - the inserted id
     * */
    public static int insert(Customer customer) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, customer.getIdCustomer());
            insertStatement.setString(2, customer.getNameCustomer());
            insertStatement.setString(3, customer.getPhoneCustomer());
            insertStatement.setString(4, customer.getEmailCustomer());
            insertStatement.setString(5, customer.getAddressCustomer());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
                customer.setIdCustomer(insertedId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**This method deletes the customer by being given the customer's id
     * @param id - the id of the customer to be deleted
     * @return deletedId - the deleted id
     * */
    public static int delete(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int deletedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deletedId;
    }

    /**This method updates the customer
     * @param customer - the id of the customer to be updated
     * @return updatedId - the updated id
     * */
    public static int update(Customer customer) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, customer.getNameCustomer());
            updateStatement.setString(2, customer.getPhoneCustomer());
            updateStatement.setString(3, customer.getEmailCustomer());
            updateStatement.setString(4, customer.getAddressCustomer());
            updateStatement.setInt(5, customer.getIdCustomer());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }
}

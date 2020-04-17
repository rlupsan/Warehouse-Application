package dao;

import connection.ConnectionFactory;
import model.OrderItem;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderItemDAO {

    protected static final Logger LOGGER = Logger.getLogger(OrderItemDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orderitem (customerId,productId,quantity)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM orderitem where idOrderItem = ?";

    /**
     * This method finds and returns if positive the orderItem by being given its id
     *
     * @param orderItemId - the id of the order item
     * @return OrderItem orderItem or null
     */
    public static OrderItem findOrderItemById(int orderItemId) {
        Connection databaseConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = databaseConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderItemId);
            resultSet = findStatement.executeQuery();
            if (resultSet.next()) {
                Integer customerId = resultSet.getInt("customerId");
                Integer productId = resultSet.getInt("productId");
                Integer quantity = resultSet.getInt("quantity");
                return new OrderItem(customerId, productId, quantity);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderItemDAO:findOrderItemById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(databaseConnection);
        }
        return null;
    }

    /**This method inserts a new orderItem
     * @param orderItem - the order item
     * @return insertedId - the inserted id
     * */
    public static int insert(OrderItem orderItem) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, orderItem.getCustomerId());
            insertStatement.setInt(2, orderItem.getProductId());
            insertStatement.setInt(3, orderItem.getQuantity());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
                orderItem.setIdOrderItem(insertedId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderItemDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}

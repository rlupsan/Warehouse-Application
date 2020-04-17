package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (idProduct, nameProduct,categoryId,price,quantityOnStock)"
            + " VALUES (?,?,?,?,?)";
    private static final String deleteStatementString = "DELETE FROM product where idProduct = ?";
    private final static String findStatementString = "SELECT * FROM product where idProduct = ?";
    private static final String updateStatementString = "UPDATE product SET nameProduct=?, categoryId=?, price=?, quantityOnStock=? " +
            "WHERE idProduct=?";

    /**
     * This method gets the list of products from the database
     * it does not have any parameters
     *
     * @return productArrayList - the array list of all the products
     */
    public static ArrayList<Product> getTheProductsList() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        try {
            Connection dbConnection = ConnectionFactory.getConnection();
            String query = "SELECT * FROM product";
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Product product;
            while (resultSet.next()) {
                product = new Product(resultSet.getInt("idProduct"),
                        resultSet.getString("nameProduct"),
                        resultSet.getInt("categoryId"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantityOnStock")
                );
                productArrayList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productArrayList;
    }

    /**This method finds and returns if positive the product by being given its id
     * @param productId - the id of the product to be found
     * @return product Product if positive, else null
     */
    public static Product findProductById(int productId) {
        Connection databaseConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = databaseConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productId);
            resultSet = findStatement.executeQuery();
            if (resultSet.next()) {
                String nameProduct = resultSet.getString("nameProduct");
                Integer categoryId = resultSet.getInt("categoryId");
                Double price = resultSet.getDouble("price");
                Integer quantityOnStock = resultSet.getInt("quantityOnStock");
                return new Product(productId, nameProduct, categoryId, price, quantityOnStock);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findProductById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(databaseConnection);
        }
        return null;
    }

    /**This method inserts a new product
     * @param product - the product to be inserted
     * @return insertedId - the inserted id
     * */
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, product.getIdProduct());
            insertStatement.setString(2, product.getNameProduct());
            insertStatement.setInt(3, product.getCategoryId());
            insertStatement.setDouble(4, product.getPrice());
            insertStatement.setInt(5, product.getQuantityOnStock());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
                product.setIdProduct(insertedId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**This method deletes the product by being given the product's id
     * @param id - the id of the product to be deleted
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
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deletedId;
    }

    /**This method updates the product
     * @param product - the product to be updated
     * @return updatedId - the updated id
     * */
    public static int update(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, product.getNameProduct());
            updateStatement.setInt(2, product.getCategoryId());
            updateStatement.setDouble(3, product.getPrice());
            updateStatement.setInt(4, product.getQuantityOnStock());
            updateStatement.setInt(5, product.getIdProduct());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }
}

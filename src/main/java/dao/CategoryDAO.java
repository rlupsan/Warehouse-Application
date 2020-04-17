package dao;

import connection.ConnectionFactory;
import model.Category;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO {
    protected static final Logger LOGGER = Logger.getLogger(CategoryDAO.class.getName());
    private static final String insertStatementString =
            "INSERT INTO category (nameCategory,description, noDepartment)" + " VALUES (?,?,?)";
    private static final String deleteStatementString = "DELETE FROM category where idCategory = ?";
    private static final String updateStatementString = "UPDATE category SET nameCategory=?, description=?, noDepartment=? WHERE idCategory=?";
    private final static String findStatementString = "SELECT * FROM category where idCategory = ?";

    /**
     * This method finds and returns if positive the category by being given its id
     *
     * @param categoryId - the id of the category to be found
     * @return category Category if positive, else null
     */
    public static Category findCategoryById(int categoryId) {
        Connection databaseConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet resultSet = null;
        try {
            findStatement = databaseConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, categoryId);
            resultSet = findStatement.executeQuery();
            if (resultSet.next()) {
                String nameCategory = resultSet.getString("nameCategory");
                String description = resultSet.getString("description");
                Integer noDepartment = resultSet.getInt("noDepartment");
                return new Category(nameCategory, description, noDepartment);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CategoryDAO:findCategoryById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(databaseConnection);
        }
        return null;
    }

    /**This method inserts a new category
     * @param category - the category to be inserted
     * @return insertedId - the inserted id
     * */
    public static int insert(Category category) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, category.getNameCategory());
            insertStatement.setString(2, category.getDescription());
            insertStatement.setInt(3, category.getNoDepartment());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
                category.setIdCategory(insertedId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CategoryDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**This method deletes the category by being given the category's id
     * @param id - the id of the category to be deleted
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
            LOGGER.log(Level.WARNING, "CategoryDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deletedId;
    }

    /**This method updates the category
     * @param category - the category to be updated
     * @return updatedId - the updated id
     * */
    public static int update(Category category) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            // updateStatement.setInt(1, category.getIdCategory());
            updateStatement.setString(1, category.getNameCategory());
            updateStatement.setString(2, category.getDescription());
            updateStatement.setInt(3, category.getNoDepartment());
            updateStatement.setInt(4, category.getIdCategory());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CategoryDAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updatedId;
    }
}

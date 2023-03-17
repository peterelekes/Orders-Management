package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Selects all rows from a table
     * @return a list of all rows from a table
     */
    private String createFindAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `order_management`.`" + type.getSimpleName() + "`");
        return sb.toString();
    }

    /**
     * Lists all elements of the table
     * @return a list of all elements of the table
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Select statement query
     * @param field describing how to select
     * @return a select statement query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `order_management`.`" + type.getSimpleName() + "`");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * find an element by id
     * @param id the id of the element
     * @return the element
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * creates object from a result set
     * @param resultSet the result set
     * @return a list of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * get the name of columns representing the fields of the object
     * @param object the object
     * @return the name of columns
     */
    public ArrayList getColumnNames(T object) {
        ArrayList<String> columnNames = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columnNames.add(field.getName());
        }
        return columnNames;
    }

    /**
     * get the values of the object
     * @param object the object
     * @return the values of the object
     */
    public ArrayList getValues(T object) {
        ArrayList<Object> values = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                values.add(field.get(object));

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    /**
     * insert statement string
     * @param object the object
     * @return the insert statement
     */
    private String createInsertQuery(T object) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append("INTO `order_management`.`");
        sb.append(type.getSimpleName() + "` ( ");
        ArrayList<String> columnNames = getColumnNames(object);
        int size = columnNames.size();
        int i = 0;
        while (i < size - 1) {
            sb.append(columnNames.get(i) + ", ");
            i++;
        }
        sb.append(columnNames.get(i) + " ) ");
        sb.append(" VALUES ( ");
        ArrayList<Object> values = getValues(object);
        size = values.size();
        i = 0;
        while (i < size - 1) {
            sb.append("\"" + values.get(i) + "\"" + ", ");
            i++;
        }
        sb.append("\"" + values.get(i) + "\"" + " )");
        return sb.toString();
    }

    /**
     * inserts an object into the database
     * @param object the object
     */
    public void insert(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(object);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * update statement string
     * @param object the object
     * @return the update statement
     */
    private String createUpdateQuery(T object) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE `order_management`.`" + type.getSimpleName() + "` SET ");
        ArrayList<String> columnNames = getColumnNames(object);
        ArrayList<Object> values = getValues(object);
        int size = columnNames.size();
        int i = 1;
        while (i < size - 1) {
            sb.append(columnNames.get(i) + " = \"" + values.get(i) + "\", ");
            i++;
        }
        sb.append(columnNames.get(i) + " = \"" + values.get(i) + "\"");
        sb.append(" WHERE id" + " = " + values.get(0));
        return sb.toString();
    }

    /**
     * update an object in the database
     * @param object the object
     */
    public void update(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(object);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * delete query string
     * @param object the object
     * @return the delete query
     */
    private String createDeleteQuery(T object) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM `order_management`.`" + type.getSimpleName()+"`");
        ArrayList<Object> values = getValues(object);
        sb.append(" WHERE id" + " = " + values.get(0));
        return sb.toString();
    }

    /**
     * delete query
     * @param object the object
     */
    public void delete(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(object);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.mysql.cj.*;

public class DB_connector {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    DB_connector(String host, String login, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(host, login, password);

            // getting Statement object to execute query
            statement = connection.createStatement();

        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public ResultVector getData() {
        String query = "select * from store_catalog.sale";
        ResultVector resultVector = new ResultVector();
        try {
            // executing SELECT query
            resultSet = statement.executeQuery(query);
            resultVector = new ResultVector(resultSet);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { connection.close(); } catch(SQLException se) { /*can't do anything */ }
            try { statement.close(); } catch(SQLException se) { /*can't do anything */ }
            try { resultSet.close(); } catch (SQLException se) { /*can't do anything */ }
        }

        return resultVector;
    }
}

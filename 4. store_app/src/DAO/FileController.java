package DAO;

import javafx.util.Pair;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Vector;

public class FileController {
    private Vector<String> fileStores = new Vector<>(100);
    private Vector<String> fileItems = new Vector<>(100);
    private Vector<String> resFile = new Vector<>(100);
    private Vector<Pair<Integer, Pair<String, String>>> stores = new Vector<>(100);
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    FileController(String host, String login, String password) {
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
        ResultVector resultVector = this.parseFilesToDB();
        return resultVector;

    }

    private ResultVector parseFilesToDB() {
        ResultVector resultVector = new ResultVector();
        try {
            Files.lines(Paths.get("stores.csv")).forEach(fileStores::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.lines(Paths.get("items.csv")).forEach(fileItems::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : fileStores) {
            Integer storeId = new Integer(line.substring(0, line.indexOf(",")));
            String storeName = line.substring(line.indexOf(",") + 2, line.lastIndexOf(", "));
            String storeAddress = line.substring(line.lastIndexOf(", ") + 2);

            stores.add(new Pair<>(storeId, new Pair<>(storeName, storeAddress)));

        }


        for (String line : fileItems) {
            String itemName = line.substring(0, line.indexOf(","));
            String storeName = "";
            String storeAddress = "";
            line = line.substring(line.indexOf(",") + 2);
            while (line.length() != 0) {
                Integer storeId = new Integer(line.substring(0, line.indexOf(",")));
                for (Pair<Integer, Pair<String, String>> store : stores) {
                    if (store.getKey().equals(storeId)) {
                        storeName = store.getValue().getKey();
                        storeAddress = store.getValue().getValue();
                    }
                }
                line = line.substring(line.indexOf(",") + 2);
                Integer amount = new Integer(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 2);

                Double price;
                if (line.contains(", ")) {
                    price = new Double(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 2);
                } else {
                    price = new Double(line);
                    line = "";
                }

                resultVector.add(new Result(storeName, storeId, storeAddress, itemName, amount, price));

//                String query = "insert into store_catalog.sale (storeName, storeID, storeAddress, itemName, itemAmount, itemPrice) values (" +
//                        "'" + storeName + "'" + ", " + storeId + ", " + "'" + storeAddress + "'" + ", " + "'" + itemName + "'" + ", " +  amount + ", " +
//                        price + ")";
//                try {
//                    // executing SELECT query
//                    statement.executeUpdate(query);
//                } catch (SQLException sqlEx) {
//                    sqlEx.printStackTrace();
//                }
            }

        }
        return resultVector;

    }
}

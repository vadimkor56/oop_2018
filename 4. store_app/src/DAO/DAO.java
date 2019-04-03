package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DAO implements GetDataFromStorage{
    private DB_connector db;
    private FileController file;
    private boolean isDB;

    public DAO() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/config.properties");
            property.load(fis);

            String storage = property.getProperty("storage");
            if (storage.equals("db")) {
                this.isDB = true;
                String host = property.getProperty("db.host");
                String login = property.getProperty("db.login");
                String password = property.getProperty("db.password");
                this.db = new DB_connector(host, login, password);
            } else {
                String host = property.getProperty("db.host");
                String login = property.getProperty("db.login");
                String password = property.getProperty("db.password");
                this.isDB = false;
                this.file = new FileController(host, login, password);
            }
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public ResultVector getData() {
        if (isDB) {
            return db.getData();
        } else
            return file.getData();
    }


}


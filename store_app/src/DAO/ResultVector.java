package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ResultVector {
    private Vector<Result> resultVector = new Vector<>(100);

    public ResultVector(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                this.resultVector.add(new Result(resultSet.getString(2), Integer.valueOf(resultSet.getString(3)),
                        resultSet.getString(4), resultSet.getString(5), Integer.valueOf(resultSet.getString(6)),
                        Double.valueOf(resultSet.getString(7))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { resultSet.close(); } catch (SQLException se) { /*can't do anything */ }
        }
    }

    public void add(Result result) {
        this.resultVector.add(result);
    }

    public ResultVector() {
    }

    public Vector<Result> getResultVector() {
        return resultVector;
    }
}

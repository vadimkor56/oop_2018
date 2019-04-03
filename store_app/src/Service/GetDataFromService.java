package Service;

import DAO.GetDataFromStorage;

public interface GetDataFromService {
    public void setDAO(GetDataFromStorage data);
    public String getData(String s);
}
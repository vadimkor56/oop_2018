package DAO;

import java.sql.ResultSet;

public class Result {
    private String storeName;
    private Integer storeID;
    private String storeAddress;
    private String itemName;
    private Integer itemAmount;
    private Double itemPrice;

    public Result(String storeName, Integer storeID, String storeAddress, String itemName, Integer itemAmount, Double itemPrice) {
        this.storeName = storeName;
        this.storeID = storeID;
        this.storeAddress = storeAddress;
        this.itemName = itemName;
        this.itemAmount = itemAmount;
        this.itemPrice = itemPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemAmount() {
        return itemAmount;
    }

    public Double getItemPrice() {
        return itemPrice;
    }
}

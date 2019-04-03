package Service;

import DAO.GetDataFromStorage;
import java.util.Vector;

import DAO.*;

public class Service implements GetDataFromService {
    private GetDataFromStorage data;
    private Vector<Store> stores = new Vector<>(100);
    private Vector<Item> items = new Vector<>(100);
    private Catalog catalog = new Catalog();

    public void setDAO(GetDataFromStorage data) {
        this.data = data;
        ResultVector resultVector = ((DAO) this.data).getData();
        for (Result result : resultVector.getResultVector()) {
            this.stores.add(new Store(result.getStoreID(), result.getStoreName(), result.getStoreAddress()));
            Item curItem = new Item(result.getItemName(), result.getItemAmount(), result.getItemPrice());
            this.stores.lastElement().addItem(curItem);
        }

        catalog =  new Catalog(stores);
    }

    public String getData(String s) {
        if (s.contains("cheapestForOne")) {
            return catalog.findCheapestStore(new Item(s.substring(s.indexOf(" ") + 1))).getName();
        } else if (s.contains("cheapestForSome")) {
            items = new Vector<>(100);
            s = s.substring(s.indexOf(" ") + 1);
            while (!s.equals("")) {
                String itemName = s.substring(0, s.indexOf(","));
                s = s.substring(s.indexOf(",") + 2);
                String itemAmount;
                if (s.contains(", ")) {
                    itemAmount = s.substring(0, s.indexOf(","));
                    s = s.substring(s.indexOf(",") + 2);
                } else {
                    itemAmount = s;
                    s = "";
                }

                items.add(new Item(itemName, Integer.valueOf(itemAmount)));
            }
            return catalog.findCheapestStore(items).getName();
        } else if (s.contains("buy")) {
            Store store = new Store();
            return store.getName();
        } else return "";
    }
}

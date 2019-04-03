package Service;

import java.util.Vector;

public class Catalog {
    private Vector<Store> stores;

    public Catalog(Vector<Store> stores) {
        this.stores = stores;
    }

    public Catalog() {
        stores = new Vector<Store>(10);
    }

    public Vector<Store> getStores() {
        return stores;
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public Store findCheapestStore(Item item) {
        Double minPrice = Double.MAX_VALUE;
        Store bestStore = new Store();
        for (Store store : stores) {
            if (store.containsItem(item) && store.getItems().elementAt(store.indexOfItem(item)).getPrice() < minPrice) {
                minPrice = store.getItems().elementAt(store.indexOfItem(item)).getPrice();
                bestStore = store;
            }
        }
        return bestStore;
    }

    public Store findCheapestStore(Vector<Item> items) {
        Double bestPrice = Double.MAX_VALUE;
        Store bestStore = new Store();
        for (Store store : stores) {
            Boolean okStore = true;
            Double overallPrice = 0.00;
            for (Item item : items) {
                if (store.containsItem(item) && item.getAmount() <= store.getItems().elementAt(store.indexOfItem(item)).getAmount()) {
                    overallPrice += item.getAmount() * store.getItems().elementAt(store.indexOfItem(item)).getPrice();
                } else {
                    okStore = false;
                    break;
                }
            }
            if (!okStore) {
                break;
            }
            if (overallPrice < bestPrice) {
                bestPrice = overallPrice;
                bestStore = store;
            }
        }
        return bestStore;
    }
}

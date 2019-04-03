package Service;

import Service.Item;

import java.util.Vector;

public class Store {
    private Integer id;
    private String name;
    private String address;
    private Vector<Item> items;

    public Store(Integer id_, String name_, String address_) {
        this.id = id_;
        this.name = name_;
        this.address = address_;
        items = new Vector<Item>(100);
    }

    public Store(Integer id_, String name, String address, Vector<Item> items) {
        this.id = id_;
        this.name = name;
        this.address = address;
        this.items = items;
    }

    public void setItems(Vector<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Store() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void buy(Vector<Item> items_) {
        Double overallPrice = 0.0;
        for (Item item : items_) {
            if (this.containsItem(item) && item.getAmount() <= items.elementAt(this.indexOfItem(item)).getAmount()) {
                overallPrice += item.getAmount() * items.elementAt(this.indexOfItem(item)).getPrice();
                System.out.println("Добавлено: " + item.getAmount() + " " + item.getName());
                items.elementAt(this.indexOfItem(item)).setAmount(items.elementAt(this.indexOfItem(item)).getAmount() - item.getAmount());
            } else {
                System.out.println("Такого товара нет: " + item.getName());
            }

        }
        System.out.println("С вас " + overallPrice.toString() + " рублей.");
    }

    public void whatCanIbuy(Double budget) {
        System.out.println("В магазине " + this.name + " на " + budget + " рублей Вы можете купить:");
        for (Item item : items) {
            System.out.println((int)(budget / item.getPrice()) + " " + item.getName());
        }
    }


    public Boolean containsItem(Item item_) {
        for (Item item: items) {
            if (item.getName().equals(item_.getName()))
                return true;
        }
        return false;
    }

    public Integer indexOfItem(Item item_) {
        for (Item item: items) {
            if (item.getName().equals(item_.getName()))
                return items.indexOf(item);
        }
        return null;
    }
}



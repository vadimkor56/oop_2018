package Service;

public class Item {
    private String name;
    private Integer amount;
    private Double price;

    public Item(String name, Integer amount, Double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public Item(Item item, Integer amount, Double price) {
        this.name = item.getName();
        this.amount = amount;
        this.price = price;
    }

    public Item(Item item, Integer amount) {
        this.name = item.getName();
        this.amount = amount;
    }

    public Item(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }


    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

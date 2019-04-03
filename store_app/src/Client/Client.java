package Client;

import Service.GetDataFromService;

public class Client {
    private GetDataFromService data;

    public void SetServices(GetDataFromService data) {
        this.data = data;
    }

    public void View() {
        System.out.println(data.getData("cheapestForOne Rice Uvelka 500g"));
        System.out.println(data.getData("cheapestForSome Rice Uvelka 500g, 5, Chocolate Alenka, 10"));
        System.out.println(data.getData("buy in Magnit Chocolate Alenka 10"));
    }
}

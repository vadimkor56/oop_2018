import Client.Client;
import DAO.DAO;
import Service.Service;

public class Main {

    public static void main(String[] args) {
        DAO dao = new DAO();
        Service service = new Service();
        Client client = new Client();
        service.setDAO(dao);
        client.SetServices(service);
        client.View();
    }
}

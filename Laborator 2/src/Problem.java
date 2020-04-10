import java.util.ArrayList;
import java.util.List;

/**
 * Problem class
 * @author Nistor Sergiu
 *
 **/
public class Problem {
    /**
     * Problem class constructor
     *
     * @param   depots    the depots contained in the problem
     * @param   clients   the clients contained in the problem
     **/
    public Problem(List<Depot> depots, List<Client> clients) {
        this.depots = depots;
        this.clients = clients;
    }

    private List<Depot> depots;
    private List<Depot> getDepots() {
        return this.depots;
    }
    private void setDepots(List<Depot> depots) {
        this.depots = depots;
    }

    private List<Client> clients;
    public List<Client> getClients() {
        return this.clients;
    }
    private void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * addDepot method - adds a depot, if it does not exist in the problem instance
     *
     * @param   depot       the depot to add
     * @return  boolean     true if the depot was added, false otherwise
     **/
    public boolean addDepot(Depot depot) {
        if (this.depots.contains(depot))
            return false;

        this.depots.add(depot);
        return true;
    }

    /**
     * addCient method - adds a client, if it does not exist in the problem instance
     *
     * @param   client      the client to add
     * @return  boolean     true if the client was added, false otherwise
     **/
    public boolean addClient(Client client) {
        if (this.clients.contains(clients))
            return false;

        this.clients.add(client);
        return true;
    }

    /**
     * getVehicles method - returns a list of all the vehicles, from all the depots in the problem instance
     *
     * @return  List   list of all the vehicles, from all the depots in the problem instance
     **/
    public List<Vehicle> getVehicles() {
        List<Vehicle> vehiclesList = new ArrayList<Vehicle>();
        for (var depot : this.depots) {
            vehiclesList.addAll(depot.getVehicles());
        }
        return vehiclesList;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + depots +
                ", clients=" + clients +
                '}';
    }
}

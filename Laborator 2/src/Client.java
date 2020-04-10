import java.util.Objects;

/**
 * Client class - is a tour location
 * @author Nistor Sergiu
 *
 **/
public class Client extends Tour.TourLocation {
    /**
     * Client class constructor
     *
     * @param   name            the name of the client
     * @param   numberOfOrder   the client's number of order
     **/
    public Client(String name, int numberOfOrder) {
        super(name);
        this.numberOfOrder = numberOfOrder;
    }

    private int numberOfOrder;
    public int getNumberOfOrder() {
        return this.numberOfOrder;
    }
    private void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", numberOfOrder=" + numberOfOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return numberOfOrder == client.numberOfOrder &&
                Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfOrder);
    }
}
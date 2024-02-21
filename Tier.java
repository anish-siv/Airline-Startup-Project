package Project1;

public interface Tier {
    String getName();
    int getMiles();
    int getCancelledFlights();
    int getFlights();
    void addFlight(boolean isCancelled);
}

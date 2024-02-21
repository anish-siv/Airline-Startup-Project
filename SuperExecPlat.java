package Project1;

public class SuperExecPlat implements Tier {
    private final String name = "Super Executive Platinum";
    private int miles;
    private int cancelledFlights;
    private int totalFlights;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMiles() {
        return cancelledFlights * 1000;
    }

    @Override
    public int getCancelledFlights() {
        return cancelledFlights;
    }

    @Override
    public int getFlights() {
        return totalFlights;
    }

    @Override
    public void addFlight(boolean isCancelled) {    // adds a new flight
        if (isCancelled) {  // if the flight was cancelled
            //miles += 2000;  // add 1000 miles
            cancelledFlights++; // add 1 to the number of cancelled flights
        }
        totalFlights++; // add 1 to the total number of flights
    }

}

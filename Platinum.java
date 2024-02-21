package Project1;

public class Platinum implements Tier {
    private String name = "Platinum";
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
            // miles += 1000;  // add 1000 miles
            if (cancelledFlights == 0) {
                cancelledFlights = cancelledFlights + 7;
            } else {
                cancelledFlights++; // add 1 to the number of cancelled flights
            }
        }
        totalFlights++; // add 1 to the total number of flights
    }

}

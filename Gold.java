package Project1;

public class Gold implements Tier{
    private String name = "Gold";
    private int miles;
    private int cancelledFlights;
    private int totalFlights;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMiles()
    {
        return cancelledFlights * 1000;
    }

    @Override
    public int getCancelledFlights()
    {
        return cancelledFlights;
    }

    @Override
    public int getFlights()
    {
        return totalFlights;
    }

    @Override
    public void addFlight(boolean isCancelled) {    // adds a new flight
        if (isCancelled) {  // if the flight was cancelled
            //System.out.println("addFlight : isCancelled : " + isCancelled + " - ");
            //miles += 1000;  // add 1000 miles
            if(cancelledFlights == 0) { // add previous tier upper bound value
                cancelledFlights = cancelledFlights + 3;
            }else{
                cancelledFlights++; // add 1 to the number of cancelled flights
            }

        }
        //System.out.println("addFlight : " + cancelledFlights + " - ");

        totalFlights++; // add 1 to the total number of flights
    }
}
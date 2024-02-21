package Project1;

public class ExecPlatinum  implements Tier{
    private String name = "Executive Platinum";
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
            //miles += 1000;  // add 1000 miles
            if(cancelledFlights == 0) {
                cancelledFlights = cancelledFlights + 12;
            } else {
                cancelledFlights++; // add 1 to the number of cancelled flights
            }


        }
        totalFlights++; // add 1 to the total number of flights

        /*
        if (cancelledFlights >= 100) {  // if the number of cancelled flights is greater than or equal to 100
            // Upgrade to Super Executive Platinum
            SuperExecPlat sEP = new SuperExecPlat();
            sEP.addFlight(isCancelled);
        }

         */
    }
}

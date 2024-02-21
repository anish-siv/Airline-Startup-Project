package Project1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Passenger {
    private int id;
    private Tier currTier;
    private int totalMiles;
    private int totalCancelledFlights;

    private int totalReports;
    private int totalFlights;
    private boolean hasMultiplier;

    public Passenger(int id) {  // constructor
        this.id = id;   // set the passenger id
        currTier = new Basic();
    }

    public int getId() {    // returns the passenger id
        return id;
    }

    public String getTier() {
        if (currTier.getName().equals("Platinum") && hasMultiplier()) {
            return "Platinum Pro";
        }

        if (currTier.getName().equals("Executive Platinum") && hasMultiplier()) {
            return "Super Executive Platinum";
        }

        return currTier.getName();
    }

    public int getMiles() {
        if (hasMultiplier) {
            totalMiles = currTier.getMiles() * 2;
        } else {
            totalMiles = currTier.getMiles();
        }

        return totalMiles;
    }

    public int getCancelledFlights() {
        if (currTier == null) {
            totalCancelledFlights = totalCancelledFlights;
        } else {
            totalCancelledFlights = currTier.getCancelledFlights();
        }

        return totalCancelledFlights;
    }

    public int getTotalReports() {
        return totalReports;
    }

    public int getTotalFlights() {  // returns the total number of flights the passenger took, including cancelled flights
        return totalFlights;
    }

    public boolean hasMultiplier() {
        if (getTotalReports() == 0) {
            hasMultiplier = true;
        }
        return hasMultiplier;
    }

    public void addFlight(boolean isCancelled) {
        //totalFlights++; // add 1 to the total number of flights
        if (isCancelled) {  // if the flight was cancelled
            int cancelledFlights = getCancelledFlights();
            cancelledFlights++;
            if (cancelledFlights == 3) { // 100
                currTier = new Gold();
            }

            if (cancelledFlights == 7) { // 100
                currTier = new Platinum();
            }

            if (cancelledFlights == 12) { // 100
                currTier = new ExecPlatinum();
            }
        }

        //String tier = currTier.getName();// getTier();
        //System.out.println("Tier : " + tier);

        // adds a new flight
        if (currTier != null) {
            currTier.addFlight(isCancelled);    // add the flight to the current tier
            //System.out.println("Tier :: " + currTier.getName() + " - "
            //        + "totalCancelledFlights : " + currTier.getCancelledFlights());
        }

    }

    public void upgradeTier() { // upgrades the passenger to the next tier
        if (currTier instanceof Gold) { // if the passenger is in the Gold tier
            currTier = new Platinum();  // upgrade to Platinum
        } else if (currTier instanceof Platinum) {  // if the passenger is in the Platinum tier
            currTier = new PlatPro();   // upgrade to Platinum Pro
        } else if (currTier instanceof PlatPro) {   // if the passenger is in the Platinum Pro tier
            currTier = new SuperExecPlat();    // upgrade to Super Executive Platinum
        }
    }

    private static ArrayList<Passenger> passengers = new ArrayList<>();

    public static void readPassengerID() throws IOException {
        FileReader inFile = new FileReader("/Users/anishsivakumar/eclipse-workspace/Project1/src/Project1/flight-data.txt");
        //FileReader inFile = new FileReader("C:\\Users\\anish\\eclipse-workspace\\Project1\\src\\Project1\\flight-data.txt");
        Scanner readFile = new Scanner(inFile);

        StringTokenizer token = null;

        int id = 0;
        boolean isCancelled = false;
        boolean isReported = false;

        while (readFile.hasNextLine()) {
            token = new StringTokenizer(readFile.nextLine(), " ");

            id = Integer.parseInt(token.nextToken());
            String cancelStr = token.nextToken();
            isCancelled = false;
            if (cancelStr != null && cancelStr.equalsIgnoreCase("Y")) {
                isCancelled = true;
            }

            isReported = false;
            if (token.hasMoreTokens()) {
                String reportedStr = token.nextToken();

                if (reportedStr != null && reportedStr.equalsIgnoreCase("Y")) {
                    isReported = true;
                }
            }

            //System.out.println("\nID : " + id + " -  isCancelled : " + isCancelled + " -  isReported : " + isReported);
            Passenger passID = null;
            for (Passenger passenger : passengers) {
                if (passenger.getId() == id) {
                    passID = passenger;
                    /*
                    System.out.println("Input Record Id :  " + passID.getId()
                                    + " - getMiles : " + passID.getMiles()
                                    + " - getTotalFlights : " + passID.getTotalFlights()
                                    + " - getCancelledFlights : " + passID.getCancelledFlights()
                    );
                    */

                }
            }

            if (passID != null) {
                passID.addFlight(isCancelled);
                if (isReported) {
                    passID.totalReports++;
                }
            } else {
                passID = new Passenger(id);
                if (isReported) {
                    passID.totalReports++;
                }

                passID.addFlight(isCancelled);
                passengers.add(passID);
            }
        }
    }

    public static void main(String[] args) throws IOException//FileNotFoundException//InputMismatchException
    {
        readPassengerID();

        /*
        System.out.println("Main passengers Size : " + passengers.size());
        for (Passenger passID : passengers) {
            System.out.println("\n" + passID.getId());
            System.out.println("Tier :" + passID.getTier());
            System.out.println("CancelledFlights :" + passID.getCancelledFlights());
            System.out.println("Reports :" + passID.getTotalReports());
            System.out.println("hasMultiplier :" + passID.hasMultiplier());
            System.out.println("getMiles :" + passID.getMiles());
        }
        */

        System.out.print("Enter a passenger id : ");
        Scanner scanner = new Scanner(System.in);
        String passId = scanner.nextLine();
        System.out.println("Result for passsenger " + passId);
        if(passId != null) {
            boolean recordExists = false;
            for(Passenger passenger : passengers)
            {
                if(passenger.getId() == Integer.parseInt(passId)) {
                    recordExists = true;
                    System.out.println("Rewards tier : " + passenger.getTier());
                    System.out.println("Total Miles : " + passenger.getMiles());
                    System.out.println("Total cancellations : " + passenger.getCancelledFlights());
                    String multipleStr = "No";
                    if(passenger.hasMultiplier()) {
                        multipleStr = "Yes";
                    }
                    System.out.println("Did the passenger earn the mileage multiplie ? : " + multipleStr);
                    break;
                }
            }

            if(!recordExists) {
                System.out.println("No Record found !!");
            }
        }


    }
}

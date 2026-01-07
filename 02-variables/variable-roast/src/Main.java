import car.Jeep;
import car.Lucid;

public class Main {
    public static void main(String[] args) {

        // Creating an object of Jeep class
        Jeep jeep1 = new Jeep(2022, "Wrangler, Sahara", 40000);
        Jeep jeep2 = new Jeep(2023, "Grand Cherokee", 50000);
        Jeep jeep3 = new Jeep(2024, "Compass", 35000);
        Jeep jeep4 = new Jeep(2021, "Renegade", 30000);

        //now creating this objects one by one is bad what is best way?
        //why we can not run direct for loop on objects?

        Jeep[] jeeps = {jeep1, jeep2, jeep3, jeep4};
        for (Jeep jeep : jeeps) {
            jeep.displayInfo();
        }

        System.out.println();

        Lucid lucid1 = new Lucid("Lucid Air", 200, 80000);
        Lucid lucid2 = new Lucid("Lucid Gravity", 180, 90000);
        Lucid lucid3 = new Lucid("Lucid Air Touring", 210, 85000);
        Lucid lucid4 = new Lucid("Lucid Air Dream Edition", 217, 169000);

        Lucid[] lucids = {lucid1, lucid2, lucid3, lucid4};

        for (Lucid lucid : lucids) {
            System.out.println("Model: " + lucid.getModel() + ", Max Speed: " + lucid.getMaxSpeed() + " mph, Price: $" + lucid.getPrice());
        }



    }
}

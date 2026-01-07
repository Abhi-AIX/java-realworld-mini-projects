package car;

public class Jeep {

    private int modelYear;
    private String modelName;
    private int price;

    //let us take one static variable to understand static keyword
    //static variable will have single copy for all objects
    //why static? because manufacturer is same for all objects
    public static String manufacturer = "Jeep Motors";

    public Jeep(int modelYear, String modelName, int price) {
        this.modelYear = modelYear;
        this.modelName = modelName;
        this.price = price;
    }

    public int getModelYear() {
        return modelYear;
    }

    public String getModelName() {
        return modelName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    //To understand local variables we will write some method
    public void displayInfo() {
        //local variable
        String info = "Jeep Model: " + modelName + ", Year: " + modelYear + ", Price: $" + price;
        System.out.println(info);
        // 'info' variable scope is limited to this method only
        // It will be destroyed once the method execution is completed
    }

}

public class UserInput {

    private int userAge;
    private String userName;
    private int salary;


    public UserInput(String name, int age,int salary) {
        this.userName = name;
        this.userAge = age;
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public int getSalary() {
        return salary;
    }

    public int setUserAge(int age) {
        this.userAge = age;
        return userAge;
    }

    public String setUserName(String name) {
        this.userName = name;
        return userName;
    }

    public int setSalary(int salary) {
        this.salary = salary;
        return salary;
    }

    public String toString() {
        return "User Name: " + userName + ", User Age: " + userAge + ", Salary: " + salary;
    }
}

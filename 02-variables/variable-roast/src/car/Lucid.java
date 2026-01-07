package car;

public class Lucid {

    //Decalaration
    private String model;

    //why private? to achieve data hiding and encapsulation
    private int maxSpeed;

    /*
    what happens if public? anyone can access and modify it directly
    to maintain integrity we use private and provide public methods to access and modify
    this is called encapsulation

    we can also use getters and setters to access and modify private variables
    this way we can add validation logic in setters if needed
    this is a good practice in OOP

    we can also make class immutable by not providing setters
    this way the state of the object cannot be changed after creation
    this is useful in multi-threaded environments
    */
    private int price;

    //static variable example
    //why static? because manufacturer is same for all objects
    public static String manufacturer = "Lucid Motors";

    //Initialization through constructor
    public Lucid(String model, int maxSpeed, int price) {
        this.model = model;       //Initialization
        this.maxSpeed = maxSpeed; //Scope object level destroyed after object destroyed
        this.price = price;       //Stores in heap memory, each obj maintains its own copy
    }

    /*
    why it is not recommended to use initialization block?
    1. Less readable: Constructors are more explicit and easier to understand.
    2. Limited flexibility: Constructors can take parameters, allowing for more dynamic initialization.
    3. Order of execution: Initialization blocks execute before constructors, which can lead to confusion.
    4. Maintenance challenges: Using constructors makes it easier to maintain and modify code.
    5. Consistency: Constructors provide a consistent way to initialize objects.
    6. Debugging difficulties: Errors in initialization blocks can be harder to trace.
    */
    /*
    {
    //Initialization through block
        model = "Lucid Air";
        maxSpeed = 200;
        price = 80000;
    }
    */

    //Accessing through methods
    public String getModel(){
        return model;
    }

    public int getMaxSpeed(){
        return maxSpeed;
    }

    public int getPrice(){
        return price;
    }

    //Initialization through methods
    public void setModel(String model){
        this.model = model;
    }

    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    public void setPrice(int price){
        this.price = price;
    }


    //example code what if we don't use encapsulation
    /*
    public String model;
    public int maxSpeed;
    public int price;

    //Instance variables are by default initialized to default values
    1. int, float, double, long = 0
    2. boolean = false
    3. char = '\u0000' (null character)
    4. Object references = null
    5. Local variables must be initialized before use, they do not have default values.
    6. Static variables are initialized to default values similar to instance variables.
    7. Final variables must be initialized once and cannot be changed thereafter.
    8. Array elements are initialized to default values based on their data type.
    9. Class variables (static) are initialized when the class is loaded.
    10. Instance variables are initialized when an object of the class is created.
    11. Variables declared inside methods (local variables) must be explicitly initialized before use.
    12. Variables declared as parameters in methods are initialized with the values passed during method calls.
    13. Variables declared inside blocks (like loops or conditionals) must also be initialized before use.
    14. Variables declared as final must be initialized at the time of declaration or in the constructor.
    15. Static final variables (constants) must be initialized at the time of declaration.
    16. Instance variables can be initialized using instance initialization blocks or constructors.
    17. Static variables can be initialized using static initialization blocks.
    18. Variables in anonymous classes must be effectively final to be accessed from within the class.
    19. Variables declared in interfaces are implicitly public, static, and final, and must be initialized.
    20. Variables in nested classes follow the same initialization rules as regular classes.
    21. Variables in lambda expressions must be effectively final to be accessed from within the expression.
    22. Variables declared in try-with-resources statements must be initialized before use.
    23. Variables in enums can have constructors and can be initialized when the enum constants are created.
    24. Variables in generic classes follow the same initialization rules as regular classes, but with type parameters.
    25. Variables in multi-threaded environments should be properly synchronized to ensure visibility and consistency across threads.
    26. Variables declared in static nested classes follow the same initialization rules as regular classes.
    27. Variables in records (introduced in Java 14) are final and must be initialized in the record's constructor.
    28. Variables in modules (introduced in Java 9) follow the same initialization rules as regular classes.
    29. Variables in annotations are implicitly public, static, and final, and must be initialized with default values if not explicitly provided.
    30. Variables in JavaFX properties have their own initialization and binding mechanisms that differ from standard Java variables.
    31. Variables in serialization must be handled carefully to ensure proper initialization during the deserialization process.
    32. Variables in Java Native Interface (JNI) must be initialized properly to avoid issues when interacting with native code.
    33. Variables in Java Reflection API must be accessed and modified carefully to ensure proper initialization
    34. Variables in Java Memory Model must be properly initialized to ensure visibility and ordering across threads.
    35. Variables in Java Virtual Machine (JVM) have specific initialization rules that must be followed to ensure proper execution of Java programs.
    36. Variables in Java Development Kit (JDK) libraries follow the same initialization rules as regular Java classes.
    37. Variables in Java Enterprise Edition (Java EE) applications must be properly initialized to ensure correct behavior in enterprise environments.

    In SpringBoot, variables can be initialized using dependency injection, configuration properties, and application context.
    1. how to initialize variables using dependency injection in SpringBoot?
       - Using @Autowired annotation to inject dependencies into variables.
       - Using constructor injection to initialize variables through constructor parameters.
       - Using setter injection to initialize variables through setter methods.
    2. how to initialize variables using configuration properties in SpringBoot?
       - Using @Value annotation to inject values from application.properties or application.yml files.
       - Using @ConfigurationProperties annotation to bind configuration properties to variables.
    3. how to initialize variables using application context in SpringBoot?
       - Using ApplicationContext to retrieve beans and initialize variables.
       - Using @Bean annotation to define beans and initialize variables.
     */

}

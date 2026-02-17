# Module 08 Team Activity  - MVC: Controller

In this team activity, we will explore the controller component in the Model-View-Controller (MVC) design pattern. The controller is the component that manages the interaction between the model and the view. It is responsible for processing client input, updating the model, and updating the view.

## Grading
Grades for team activities will be based on attendance and notes. You must attend, and as a team you need to generate notes that we can confirm your work. You need to upload the notes as a PDF to Canvas after you write them. Avoid using AI to answer the questions; answers to the questions must be in your own words.

> [!TIP] 
> Good notes become a study guide for you and your team! Make sure they include everything you need to help better understand the weekly material. 

### ⭐ Working in Teams ⭐
When working in teams, remember do not let one person do all the work. Make sure to work together, and ask questions. It is also better if different people program, and you all take turns programming for various team assignments.

## Learning Objectives
This team activity is designed to help you understand the following concepts:
  * Define and understand the purpose of a controller and how it relates to the driver
  * Create abstract classes and interfaces to define the interaction between components
  * Explore the use of packages to break up design components

## Controller

The Controller is often the most misunderstood part of the MVC application architecture pattern. For that reason, there are multiple alternative patterns, and variations on MVC. This older article [Interactive Application Architecture Patterns](https://web.archive.org/web/20201107060523/https://lostechies.com/derekgreer/2007/08/25/interactive-application-architecture/) details some of the differences. It is more detailed than you need to know right now, but the key questions to ask when designing an application are:

* How do you want to handle client input?
* How do you display the information to the client?
* How do you want to manage the data and the "state" of the application?

With these three questions in mind, you can start to design the overarching architecture of your application. 

👉🏽  Discussion - Assuming the MVC pattern - how are those three questions answered?


It is worth noting that that just because there are three components, there are not three classes. In fact, there are often many classes that make up each component. Additionally, while the components are meant to be separate and "interchangeable" (mostly), that doesn't mean there isn't interaction between all three components. 

👉🏽  Discussion - Wait, Interchangeable? Discuss what this could mean in relation to MVC, think about how polymorphism works to help you. 

### Supervising Controller Pattern

A more strict view of the MVC pattern is the Supervising Controller. In this pattern, the view  never interacts with the model. Instead, the controller is the only component that interacts with the model. This is a more strict interpretation of the MVC pattern, and is often used in web applications. 

👉🏽  Discussion - Discuss how this can be advantageous, and any limitations that come to mind.


> [!NOTE]
> Is there a correct answer? No! Design patterns about tradeoffs and balances. You gain one thing but you may lose another. The important part is to make sure you follow SOLID design principles, and that your code is maintainable and scalable.

### Observer Pattern

Related to the interaction is the Observer Pattern. This pattern is used to allow an object to publish changes to its state. Other objects subscribe to be notified when the state changes. This is often used in GUI programming, and is a common pattern in Java. We will explore this more next module, but it is worth considering it conceptually with this question:

> If the controller drives the interaction between client, model, and view: how can the view or model notify the controller when something has changed?


## Packages
Before we practice with controllers, let's take a break from patterns to talk about packages.  The past few assignments and last week's module has a lot of files! After a while it can be hard to keep track of everything. This is where packages come in. Packages are a way to organize your code into groups. This makes it easier to find and manage your code.

While it doesn't have to be model, view, controller for your packages, it can be a common way to organize your code with the MVC pattern, along with additional packages for utilities, or other components. 

To represent a package in java, you use the `package` keyword at the top of your file. For example, if you wanted to put a class in a package called `com.mycompany.myapp`, you would put this at the top of your file:

```java
package com.mycompany.myapp;
```

However, you **ALSO** need to ensure that your file is in the correct directory structure. For the above package, you would need to put your file in a directory called `com/mycompany/myapp`. This is a common source of errors when working with packages. This directory structure is relative to the root of your project - often where your `src` directory is. Using Gradle that is `src/main/java` or `src/main/resources` for Java files and resources respectively.  When you compile your code, the compiler will look at the package statement and the directory structure to determine where to put the compiled class files, and it will also look at the directory structure to determine where to find the class files when you run your program.

👉🏽  Discussion - How can packages be useful? What would be some ways you could break up your code from past assignments or team activities. Pick one activity and discuss how you can break it up into concepts, that can then be coupled into packages. 


## Let's Practice

For this activity, we will create a simple application that uses the MVC pattern. The application will be a simple calculator that can add, subtract, multiply, and divide two numbers. The calculator will have a view that displays the two numbers and the result of the operation, and a controller that processes the user input and updates the model and view.

To make things "easier", we will use prefix notation for the operations. In prefix notation, the operator comes before the operands. For example, to add 2 and 3, you would write `+ 2 3`. The controller will parse the input and call the appropriate method on the model to perform the operation.


### :fire: Step 1: Create the Model

The model will be a simple class that performs the operations. It will have methods to add, subtract, multiply, and divide two numbers. Create a new class called `Calculator` in the `model` package.

```java
package model;

public class Calculator {

  public Number add(Number a, Number b) {
    return a.doubleValue() + b.doubleValue();
  }

  public Number subtract(Number a, Number b) {
    return a.doubleValue() - b.doubleValue();
  }

  public Number multiply(Number a, Number b) {
    return a.doubleValue() * b.doubleValue();
  }

  public Number divide(Number a, Number b) {
    return a.doubleValue() / b.doubleValue();
  }
}
```

> [!TIP]
> What is `Number`? It is an abstract class that is the superclass of all classes that represent numeric values in Java. It is used here to allow the calculator to work with different types of numbers, such as `Integer`, `Double`, and `BigDecimal`. We assumed `doubleValue()` as math operations are typically done with floating point numbers, and not whole numbers. Though an additional feature could be to allow the user to specify the type of number they want to use. Learn more about [`Number`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Number.html).

Since there are a number (pun intended) of operations, it may also make sense to create an operation `enum` that will help make it easier for the controller to determine which operation to perform. 

```java
// which package would this go in?

public enum Operation {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operation getOperation(String symbol) {
        for (Operation operation : Operation.values()) {
            if (operation.getSymbol().equals(symbol)) {
                return operation;
            }
        }
        return null;
    }
}

```

👉🏽  Discussion - where should you place Operation. It is fair to note there are good arguments for both the Model and Controller (but not the view). So as a team decide as you work through your reasoning. 

### Step 2: Create The View

At this point, we can create a *simple* view that will display the two numbers and the result of the operation. Create a class called ConsoleView, in the view package. 

```java
public class ConsoleView {
    private static final String PROMPT = "> ";
    private static final String ERROR = "Error: ";
    private static final Console console = System.console();

    public void displayWelcome() {
        console.printf("Welcome to the calculator\n");
        console.printf("Allowed operations: " + Arrays.asList(Operation.values()).stream()
                .map(Operation::getSymbol).collect(Collectors.joining(" ")) + "\n");
        console.printf("Type exit to close calculator.\n");

    }

    public void displayError(String message) {
        console.printf(ERROR + message + "\n");
    }

    public void displayResult(Number result) {
        console.printf("%s\n", result.toString());
    }

    public String getClientOperation() {
        console.printf(PROMPT);
        return console.readLine();
    }

    public void close() {
        console.printf("Goodbye!\n");
    }
}
```

👉🏽  Discussion - Discuss the provided code. What are some issues you can see (spoiler, we will add an interface in another step!). Most notably, think about the direction of communication between the view and controller at this point. 


### Step 3: Create the Controller
Now, let's create the controller which acts as input manager and interaction with the model and view. Create a class called `CalculatorController` in the `controller` package.

```java
public class CalculatorController {

    private ConsoleView view;
    private Calculator model;


    public CalculatorController(ConsoleView view, Calculator model) {
        this.view = view;
        this.model = model;
    }

    public void run() {
        view.displayWelcome();
        while (true) {
            String operation = view.getClientOperation();
            if (operation.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                // to add
            } catch (Exception e) {
                view.displayError(e.getMessage());
            }
        }
        view.close();
    }
}
```

And then you can also start the initial driver which you can place in the root/default package of your project. 

```java
public class CalculatorApp {

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        Calculator model = new Calculator();
        CalculatorController controller = new CalculatorController(view, model);
        controller.run();

    }
}
```

#### Update Controller

At this stage, if we run the application it won't do much other
than printing the welcome message, and looping until someone types
exit. Let's take time to update the controller to handle the input
from the client and perform the operation.

Since a calculator can chain operations such as `+ 1 + 2 * 3 5` which is
the equivalent of `1 + 2 + 3 * 5`, we need to parse the input and
perform the operations in the correct order. We can do this by
splitting the input into tokens and using a stack to keep track of
the numbers and operations. You don't have to worry about parenthesis or order of operations for this exercise (consider why that's the case!). 

```java

private Number isNumber(String token) {
    try {
        Number dbl = Double.parseDouble(token);
        return dbl;
    } catch (NumberFormatException e) {
        return null;
    }
}

private Number processOperation(String operation) {
  Stack<Number> numbers = new Stack<>();

  String[] tokens = operation.split("\\s+");
  for (var i = tokens.length - 1; i >= 0; i--) {
      String token = tokens[i];
      if (token.isEmpty()) {
          continue;
      }
      Number number;
      if ((number = isNumber(token)) != null) {
          numbers.push(number);
      } else {
          Operation op = Operation.getOperation(token);
          if (op == null) {
              throw new IllegalArgumentException("Invalid operation: " + token);
          }
          if (numbers.size() < 2) {
              throw new IllegalArgumentException("Not enough numbers for operation: " + token);
          }
          Number b = numbers.pop();
          Number a = numbers.pop();
          Number result;
          switch (op) {
              case ADD:
                  result = model.add(a, b);
                  break;
              case SUBTRACT:
                  result = model.subtract(a, b);
                  break;
              case MULTIPLY:
                  result = model.multiply(a, b);
                  break;
              case DIVIDE:
                  result = model.divide(a, b);
                  break;
              default:
                  throw new IllegalArgumentException("Invalid operation: " + token);
          }
          numbers.push(result);
      }
  }
  if (numbers.size() != 1) {
      throw new IllegalArgumentException("Invalid expression");
  }
  return numbers.pop();
}
```

👉🏽  Discussion  and :fire: Task - Add the above code to your controller, but before you add it, discuss what each element does. You may even want to clean up the code, as the switch statement could be its own private method (which probably would follow SOLID design better).

### Huston, we have a problem, sort of.

At this point, you should have a working calculator, but there is a problem. Everything is tightly coupled instead of using interfaces that can help make the code interchangeable. Additionally, the view relies on the control for its actions, which is not ideal as for example if we switch to a GUI view, we would have to change the controller and view both!

### Step 4: Create Interfaces

The first set will be to create an interface for the model. While this step is arguably less important, it is a good practice to follow. 

```java
package model;

public interface ICaculator {
    Number invokeOperation(Operation operation, Number a, Number b);

    Number add(Number a, Number b);

    Number subtract(Number a, Number b);

    Number multiply(Number a, Number b);

    Number divide(Number a, Number b);

    List<String> getOperationSymbols();

    Operation getOperation(String symbol);
}
```

The above interface may be a bit too detailed (specially based on where you placed Operations.java), and you may find you need either invokeOperation or add,subtract,multiply,divide. However, you should go ahead and add the interface to your program, and if need be, add another layer to the model. 

:fire: Now make sure to update your controller to use the interface instead of the concrete class. 


#### View and Controller Interface

Within the Controller, the .run() method ends up being tightly coupled with the view. This is because the loop to run the program is hosted there, and as such, if we change views, we would still need to be passing in the string "exit" to close the program. This may not at all be what we want. Instead, if we create an interface that allows the view to send messages to the controller, we can then have the controller decide what to do with those messages while the view handles all the interaction. 

To make such a change, we need to create a minimal interface for the controller. This exposes the methods that the view can call, and only those. 

```java
public interface IController {
    void processOperation(String operation);
}
```

You will want to update the permissions on processOperation.

Now, update the view to use this interface. 

```java
public interface IView {
    void start();
}

public class ConsoleView implements IView {
    private static final String PROMPT = "> ";
    private static final String ERROR = "Error: ";
    private static final Console console = System.console();
    private IController controller;

    public ConsoleView(IController controller) {
        this.controller = controller;
    }
    public void start() {
        displayWelcome();
        while (true) {
            String operation = getClientOperation();
            if (operation.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                Number result = actions.processOperation(operation);
                displayResult(result);
            } catch (Exception e) {
                displayError(e.getMessage());
            }
        }
        close();
    }

}    
```

And then in the app, 

```java
public class CalculatorApp {

    public static void main(String[] args) {

        ICalculator model = new Calculator();
        IController controller = new CalculatorController(model);
        IView view = new ConsoleView(controller);
        view.start();
    }
}
```

👉🏽  Discussion - Discuss the changes you made. What are the benefits of using interfaces in this way? What are some potential drawbacks? Also, how does this "flip" the flow of the program.


A major advantage of this approach is that the view and controller are now decoupled. This means that we can easily swap out the view for a different one without having to change the controller. For example, we could create a GUI view that displays the calculator on the screen instead of using the console. We could also create a web view that allows users to interact with the calculator through a web browser. The controller would remain the same in both cases, but the view would be different. Also, the key aspect of the view is that it is processing the input, making sure it is clean and ready for model to do the actual work. 


> [!CAUTION]
> There are no easy answers! This is design, and for every design presented, there is a good argument for why it is both the best and a bad idea. One design flaw in this approach is if the controller is doing other things in the background. It would be good to add two-way communication where the view tells the controller the exit action has happened, causing communication through all the components.  We will cover this more in the next module.
> 
> The goal of this activity is to get you thinking about how to design your code, and how to make it more maintainable and scalable.

## Overall

Design takes practice! It takes discussion, refactoring, and more discussion. The more you practice, the better you will get at it. The more you discuss, the more you will learn. 

You will find a solution in the solution folder, but remember, there are many ways to solve a problem. The key is to make sure your solution is maintainable, scalable, and understandable.


## :fire: Java Practice Problem
As part of **every** team activity, we will ask you to work on a Java Practice problem, and submit the code to the team files section (or as part of your notes). This is meant to give you practice similar to technical interviews, but also help build up your java skills. **Each team member needs to select a different problem!** But you can share/and should share answers and help each other. Remember, to learn a new language, the best thing you can do is practice! Here are some resources to find practice problems but you are not limited to them:

* [CodeHS - Java Practice](https://codehs.com/practice/java)
* [Coding Bat - Java](https://codingbat.com/java)
* [Hacker Rank - Java(Basic)](https://www.hackerrank.com/domains/java?filters%5Bskills%5D%5B%5D=Java%20%28Basic%29)

  As in previous team activities, use the repository for the team activities (as instructed by the TA). Add each of your teammates as collaborators to the repository, as well as the TA and the instructor. Each student should then clone the repository and include in their solution to each problem as `.java` files. Be sure to include in a comment your name as the author of the code (see: `@author` Javadoc tag). **Include the link to this repository in your notes file**. 
 
# Module 09 Team Activity  - MVC: View

In this activity, we will build off last week's activity (with some changes) to set up a view for our MVC application. Instead of having a console view, we will **create a GUI view using Java Swing**.

## Grading
Grades for team activities will be based on attendance and notes. You must attend, and as a team you need to generate notes that we can confirm your work. You must upload the notes as a PDF to Canvas after you build them out.


> [!TIP] 
> Good notes become a study guide for you and your team! Make sure they include everything you need to help better understand the weekly material. 

### ⭐ Working in Teams ⭐
When working in teams, remember do not let one person do all the work. Make sure to work together, and ask questions. It is also better if different people program, and you all take turns programming for various team assignments.

## Learning Objectives
This team activity is designed to help you understand the following concepts:
* Understand the Model-View-Controller (MVC) design pattern
* Implement a GUI view using Java Swing
* Work with an interface, so different types of views are interchangeable
* Explore the singleton design pattern
* Review the use of xml properties files

## Explore Provided Code

For this activity, we have provided a working program that uses a console view. The program is a simple calculator that can add, subtract, multiply, and divide two numbers. The program is divided into three parts: the model, the view, and the controller. The model is responsible for the business logic of the program, the view is responsible for displaying the data to the user, and the controller is responsible for handling user input and responding to it. 

👉🏽  Discuss the files provided in the [student](student/) package. In the last activity, we did a prefix calculator. Is it still a prefix calculator or did we update it to a normal infix calculator?


### Settings.java and a Singleton Pattern

You will see a new file called Settings.java, which uses the following method

```java
 public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
```
Where instance is a private static variable of the class. This is an example of the **singleton design pattern**. The singleton design pattern ensures that a class has only one instance and provides a global point of access to that instance. In this case, the Settings class is used to store the properties of the program, such as the title of the window. Arguably, it could be used to hold more information than what we are using, but wanted to just have it as an example.




### Running the current program

You can run the current program as is from the [CalculatorApp.java](student/CalculatorApp.java) file. This will run the program with a console view by default.

> [!CAUTION]
> This app probably has plenty of bugs/undocumented features, as we didn't put in the many layers of error checking that would be needed for a production application. Just remember, it is an example :) to get you started. 

## Replacing the view.

### Understanding Graphical User Interfaces (GUIs)

When we think about console/terminal applications, we often think that there is one path to interact with the program. However, with GUIs, there are many ways to interact with the program. For example, you can click on buttons, type in text fields, and select items from drop-down lists. That means you have to think about all the different ways a user can interact with your program and how you can handle those interactions often through "events". This is called event driven programming, which learning a GUI often teaches us. 

When thinking about GUIs, it is often good to think of them as a series of "windowed" or "framed" components - pictures within pictures! Each component can have its own layout, and you can nest components within components. For example, with a calculator, you will have the surrounding window, then a panel for the buttons, a panel for the text field, and a panel for the different operations. The main window will then determine how those different panels fit within the overall window. It can also handle interactions between the panels, just like a panel that has subcomponents/panels would do for those under it. 

👉🏽  Discuss  Take a moment to understand this 'framed' approach/view of GUIs. How does that change your thoughts about applications you use daily. Does it make more sense why certain locations seem to be related to each other, more than others?


### Update CalculatorApp.java

We first need to update the CalculatorApp.java file to use the GUI view instead of the Console view. To do that, go into the CalculatorApp.java file and update the following line:

```java
 IView view = new ConsoleView(controller);
 ```

 To use the GUIView instead of the ConsoleView, you will need to update the line to:

 ```java
IView view = new JFrameView(controller);
```

Yes, that is it for now! However, if you run the application as is, it will look like it is hanging up. That is because we aren't displaying the JFrame yet. We will do that next.

### 👉🏽 Discuss JFrameView
Take a moment to go through the incomplete JFrameView.java file. Discuss what you think the code is doing, and what you think needs to be done to complete the file? (we will step through all of what you will need to do to complete it).  

It is also worth noting, our JFrameView actually extends JFrame, which means it is a JFrame, but has the ability to add additional functionality. This is a common way to build GUIs in Java, as you can extend the components to add additional functionality. Often the additional functionality you implement has their own interfaces to help with the design, but we are keeping it simple for now.

> [!TIP]
> Often when it comes to frames and GUIs, you build them piece by piece, which involves a lot of 
> running, prototyping and getting the look you want.  It also involves a lot of research! Knowing 
> exactly all the components is difficult due to how many there are.  For this reason there exists GUI builders, 
> which can help you build the GUIs without having to remember all the components. However, they 
> also add a bunch of code that may be specific to that builder. For now, it is best to learn the 
> components by hand, so you understand what is going on. 
> 

### Enabling the JFrame

To cause the JFrame to display, you need to add the following line to the JFrameView.java file:

```java
setVisible(true);
```

As a team, discuss the best place to include this, why would the order matter?  Remember, JFrameView implements IView, which has a method that should be called by the driver.


:fire: At this point, you should be able to test it and verify it opens an empty JFrame. If our context was set up correctly in Settings.java, you should see the title populated, and it should match your operating System look and feel (though it won't match dark mode... just the default look and feel). 


### Adding Components to the JFrame - CalcDisplay.java

The first component added to the calculator is the display. The display is a text field that shows the numbers and operations that the user has entered. The display is an instance of a JLabel, but also has functionality to update the display. 

> [!WARNING]
> In a larger application, CalcDisplay (or another component) would interact with an input validator to ensure the input is valid before displaying it. The validators often exist in the controller side, but can be in the view side depending on the type of the application. Some applications like web application will have input validators at nearly every stage (view, and controller and even model) due to the nature of distributed applications needing layers of validation for security purposes.  We are keeping this simple for now, and just assuming it is valid. 

👉🏽 Discuss the components of CalcDisplay.

Uncomment the following two lines in JFrameView.java to add the CalcDisplay to the JFrame:

```java
        // calcDisplay = new CalcDisplay();
        // this.add(calcDisplay, BorderLayout.NORTH);

```

You should be able to run the application and see the CalcDisplay at the top of the JFrame.


### Adding Buttons to the JFrame - CalcNumbers.java

Let's add our next component, the numbers on the calculator, along with 'C' for clear. 

👉🏽 Discuss the components of CalcDisplay.  When are the buttons added: during the constructor or later?

It is worth noting that CalcNumbers.java extends JPanel, which is a container that can hold other components. This is a common way to group components together in a GUI. From this point of view, anything in CalcNumbers can interact with every component in CalcNumbers, but not components outside without event listeners.

Uncomment the following lines in JFrameView.java to get the numbers to display. 

```java
    // CalcNumbers calcNumbers = new CalcNumbers();
    // calcNumbers.setNumberButtonListener(this::calcNumbersListener);
```

:fire: At this point, you should be able to run the application and see the numbers. If you click on them they will display (just as one giant number). 

👉🏽 Discuss - Look through the files and try to figure out *how* the numbers are displaying. What is the sequence of events called to get them to display. 


#### Adding Clear
You will notice that the `C` button isn't showing, and doesn't do anything. We will want to add an ActionListener for the button, and then call `setClearButtonListener(ActionListener listener)` in the constructor of JFrameView.java. 

For doing this, think about your direction of interaction. The JFrameView is the main view, that is also handling the interaction between the buttons (yes, in that regard, this is a controller specific to this JFrame). Let's first add the ActionListener to the JFrameView.java file. 

```java
private void clearListener(ActionEvent e) {
    calcDisplay.clear();
}
```

Then, you can add the following line to the JFrameView constructor to add the listener to the button. 

```java
    calcNumbers.setClearButtonListener(this::clearListener);
```

Unlike the listener for the buttons, it doesn't need any information from the button itself. It just needs to know that the button was clicked. 


:fire: Make sure to test to make sure the clear button works.

### Adding Operations to the JFrame - CalcOperations.java

Now, we just need to add the calculator operations.

👉🏽 Discuss the components of CalcOperations. When are the operations added, during the constructor or later? (This is intentionally a different style than CalcNumbers).


#### Adding the Buttons
:fire: Go ahead and add the CalcOperations pane to the JFrame. You will not be attaching the
event listeners yet, but you should be able to see the operations on the JFrame.

#### Adding Event Listener for Operations

For the operations, the event listener is similar to the number. However, we will assume there are spaces on each side of the operation. This is overly simple, as it would allow multiple operations in a row to be added, but for now we are keeping it simple. 

:fire: Using the event listener for numbers as an example, go ahead and add the operations event listener and attach them. 


#### Adding the Equals Button Event Listening

Similar to the ConsoleView.java, this is where the 'magic' happens. Once this button is finally clicked, we will pass the constructed string to the controller to be evaluated, and then
return the result. 

:fire: Go ahead and add the event listener for the equals button. If you get stuck, we have one implemented in the solution. Don't forget to have a try/catch to handle an error and then display the error or "Error" if there is no message/getMessage is null.


### Giving options

By now, you hopefully have working GUI calculator. However, it is always good to give options, so we can use command line arguments to determine if we want to use the console view or the GUI view.

```java
    if (args.length > 0 && args[0].equals("console")) {
        view = new ConsoleView(controller);
    } else {
        view = new JFrameView(controller);
    }
```

Now, via the command line argument we can run the program with the console view or the Graphic User Interface! The important part to this is that if an application is designed right, the view types should be pretty interchangeable to the rest of the application. 

## Overall

Design takes practice! It takes discussion, refactoring, and more discussion. The more you practice, the better you will get at it. The more you discuss, the more you will learn. 



## :fire: Java Practice Problem
As part of **every** team activity, we will ask you to work on a Java Practice problem, and submit the code to the team files section (or as part of your notes). This is meant to give you practice similar to technical interviews, but also help build up your java skills. **Each team member needs to select a different problem!** But you can share/and should share answers and help each other. Remember, to learn a new language, the best thing you can do is practice! Here are some resources to find practice problems, but you are not limited to them:

* [CodeHS - Java Practice](https://codehs.com/practice/java)
* [Coding Bat - Java](https://codingbat.com/java)
* [Hacker Rank - Java(Basic)](https://www.hackerrank.com/domains/java?filters%5Bskills%5D%5B%5D=Java%20%28Basic%29)

As in previous team activities, use the repository for the team activities (as instructed by the TA). Add each of your teammates as collaborators to the repository, as well as the TA and the instructor. Each student should then clone the repository and include in their solution to each problem as `.java` files. Be sure to include in a comment your name as the author of the code (see: `@author` Javadoc tag). **Include the link to this repository in your notes file**. 
 
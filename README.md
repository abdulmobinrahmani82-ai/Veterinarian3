 Vet Clinic Management System
1. What is this project?
This project is a console-based Vet Clinic Management System written in Java.
It is a refactored version of Assignment 2, improved according to Week 6 requirements.
The system allows the user to:
Add pets
View pets
Add owners
View owners
The main goal of this assignment is not features, but correct use of OOP concepts.

 Project Structure 
   src/
├── Main.java
│
├── menu/
│   ├── Menu.java
│   └── MenuManager.java
│
├── model/
│   ├── Person.java
│   ├── Owner.java
│   ├── Pet.java
│   ├── Veterinarian.java
│   └── Treatable.java
│
└── exception/
    └── InvalidInputException.java
   
How I built this project?

 Step 1: Cleaning the Main Class
First, I cleaned Main.java.
Originally, the menu logic was inside Main.
I removed all menu-related code and kept Main responsible only for starting the program.
Now Main.java:
Is less than 10 lines
Creates a menu object
Calls run()
This makes the program easier to maintain and follows clean code principles.

Next, I created a Menu interface.
The purpose of this interface is to define what a menu should do, not how it works.
The interface contains:
displayMenu()
run()
This allowed me to move all menu logic out of Main and into a separate class.

next, Implementing MenuManager
After that, I created the MenuManager class.
MenuManager:
Implements the Menu interface
Contains all menu logic
Handles user input
Uses try-catch blocks to handle errors safely
This separation makes the project more modular and professional.

next,Refactoring the Parent Class into an Abstract Class
I then refactored the Person class.
I made Person an abstract class because:
A generic person should never be created
Only specific types like Owner or Veterinarian should exist
I added an abstract method:
getRole()
This forces all child classes to provide their own implementation.

after that, Updating Child Classes
After making Person abstract, I updated all child classes.
Owner extends Person
Veterinarian extends Person
Each child class:
Implements the abstract method
Has its own specific behavior
Uses setters that validate input
This demonstrates proper use of inheritance and abstraction.

then, Adding an Additional Interface
To meet the assignment requirement, I added another interface called Treatable.
This interface represents a capability, not a type.
Pet implements Treatable
This means pets can be treated
This shows that a class can both:
Extend a parent class
Implement an interface
next, Implementing Exception Handling
I removed all error messages from setters.
Instead:
All setters validate input
Invalid data causes an exception to be thrown
In the menu logic:
I used try-catch blocks
I handled NumberFormatException
I handled IllegalArgumentException
I created and used a custom exception InvalidInputException
This prevents the program from crashing and makes input handling safer.

Finally, I organized the project into packages:
menu → menu-related logic
model → core system classes
exception → custom exceptions
This structure makes the project easy to understand and maintain.

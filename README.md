# simple-bin-count-simulator
This project creates a virtual Amazon Fulfillment Center and simulates the Simple Bin Count (SBC) inventory management process.

## Project Description
This project creates a virtual Amazon Fulfillment Center and simulates the Simple Bin Count (SBC) inventory management process.

The virtual FC created by this program is modeled after legacy Amazon FCs. Legacy (aka "traditional") Amazon FCs are those without Amazon Robotics (formerly Kiva) technology. The program creates an FC with 76,384 inventory receptacles and assigns each one an unique location address and pick-path ID.

Users can log-in to the SBC process and count the number of items physically present in the bin, checking for mismatches between physical and virtual inventory amounts.

## Project Aim
The initial aim of this project was to help me learn good programming fundamentals and to simulate the SBC process.

In 2020, I left my job as an Amazon FC associate in order to pursue a few personal projects, the main one being teaching myself software development.

When thinking of ideas for good portfolio projects, I decided to simulate the SBC process, something I spent many hours doing as an Amazon ICQA (Inventory Control/Quality Assurance) associate.

On a whim, I decided to expand the project's scope by challenging myself to reverse engineer ONT2's (an Amazon legacy FC) pick-path pattern. An efficient pick-path pattern is crucial for a warehouse/FC the size of ONT2, which I estimate has about half a million inventory receptacles, each one requiring an unique and precise pick-path ID. Manual pick-path ID assignment being out of the question for obvious reasons, I was faced with creating my first set of business-critical algorithms.

## How to Use
At the program start screen where it prompts the user to enter an employee ID,

![start-screen](https://user-images.githubusercontent.com/129235347/229246989-be745e9e-b036-4356-af7c-fce3f10cce0b.jpg)

Enter:\
**10404** for employee Jose Aguilar (joseag) - has SBC process permissions\
**100** for employee Zachary Griffith (griffiz) - has SBC process permissions\
**101** for employee Justin Daniels (judanie) - has SBC process AND Problem Solve permissions\
**102** for employee Jonathan Torres (jontorre) - has SBC process, Problem Solve, AND Process Assistant permissions

-----

While in the SBC process, at the screen in which the user is prompted to "Scan location or enter 1.", additional valid inputs are:

![image](https://user-images.githubusercontent.com/129235347/229242390-ba7e8620-8892-499c-8626-87232a5eec31.png)

**p** - for (P)roblem Menu, in case the user needs to create a location-related andon\
**n** - for (n)ext bin, which allows the user to skip the current bin\
**s** - for (s)ign-out of the SBC process, takes the user back to the ICQA Process Selection menu

-----

While in the SBC process, at the screens in which the user is prompted to count/re-count items, the user can also input:

![image](https://user-images.githubusercontent.com/129235347/229245325-9d88ffde-a28c-4dcf-b388-a7f3ed4c9f84.png)

**p** - for (P)roblem Menu, which allows the user to create an inventory-related andon

## Technologies
This program uses Amazon Coretto JDK 17.0.0_35.

I chose Java because this is my first software project, and I read that Java is a good language to learn programming fundamentals.

This program reads-in inventory receptacle locations and their corresponding pick-path IDs from two text files. The values were generated using algorithms that I have decided to omit from this version of the program.

## Additional Information
***Please note that I have never seen a single line of code pertaining to Amazon FC software. Nor did I receive help/guidance from anybody formerly/currently employed by Amazon.*** I created the algorithms myself based on my own memories and experiences working as a Level 1 Fulfillment Associate at ONT2, an Amazon FC in San Bernardino, CA.

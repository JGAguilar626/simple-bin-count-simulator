import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;

public abstract class Main {

    // ont2 is hardcoded because this program doesn't have persistence
    static ONT2 ont2;
    static boolean runProgram;

    public static void main(String[] args) {

        Main.ont2 = new ONT2("ONT2");

        // send ont2 to a static method that initializes most of its fields
        ONT2Initializer.initializeONT2Fields(ont2);

        Main.programStart();
    }

    private static void programStart() {

        Main.runProgram = true;

        do {
            // user must input a value > 0
            int userInput = Main.promptEmployeeToEnterID();

            // Check to see if user input matches any actual employee ID. If so return login, if not return null.
            Employee currentUser = Main.getCurrentUser(userInput);

            if (currentUser == null) {
                System.out.println();
                System.out.println("Invalid employee ID.");
            }
            else {
                  Main.departmentSelection(currentUser);
            }
        } while (Main.runProgram);
    }

    private static int promptEmployeeToEnterID() {

        Scanner input = new Scanner(System.in);
        int userInput = 0;
        boolean continueInput = true;

        do {
            try {
                System.out.println();
                System.out.println("****************************");
                System.out.println("Amazon FC ONT2");
                System.out.println(new Date());
                System.out.println("****************************");
                System.out.println();
                System.out.print("Enter your employee ID: ");

                userInput = input.nextInt();
                input.nextLine();

                if (userInput > 0) {
                    continueInput = false;
                }
                else {
                      System.out.println();
                      System.out.println("Invalid input.");
                }
            } catch (InputMismatchException ex) {
                input.nextLine();
                System.out.println();
                System.out.println("Invalid input.");
            }
        } while (continueInput);

        return userInput;
    }

    private static Employee getCurrentUser(int userInput) {

        //TODO: Most likely going to get employee object another way, don't get reference to ont2's list here.
        Employee employee = null;
        ArrayList<Employee> employees = Main.ont2.getEmployeeArrayList();

        int i = 0;

        do {
            if (employees.get(i).getID() == userInput) {
                employee = employees.get(i);
            }
            i++;
        } while(employee == null && i < employees.size());

        return employee;
    }

    private static void departmentSelection(Employee currentUser) {

        System.out.println();
        System.out.println("Welcome, " + currentUser.getLogin() + "!");

        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        do {
            try {
                System.out.println();
                System.out.println("*********************************");
                System.out.println("Department/Process Selection Menu");
                System.out.println("*********************************");
                System.out.println();
                System.out.println("1. Outbound");
                System.out.println("2. Inbound");
                System.out.println("3. ICQA");
                System.out.println("4. Problem Solving");
                System.out.println("5. Process Assistant");
                System.out.println("6. Log Out");
                System.out.println("--------------------");
                System.out.println("0. Test Mode");
                System.out.println("555. Test Program Termination");
                System.out.println();
                System.out.print("Enter choice: ");

                int userInput = input.nextInt();
                input.nextLine();

                if (userInput == 1) {
                    System.out.println();
                    System.out.println("Sorry, this department is not available yet.");
                }
                else if (userInput == 2) {
                         System.out.println();
                         System.out.println("Sorry, this department is not available yet.");
                }
                else if (userInput == 3) {
                         ICQA.processSelection(currentUser);
                }
                else if (userInput == 4) {
                         if (currentUser.hasProblemSolvingPermissions()) {
                             ProblemSolving.problemSolvingMainMenu(currentUser);
                         }
                         else {
                               System.out.println();
                               System.out.println("Error. You don't have Problem Solving permissions.");
                         }
                }
                else if (userInput == 5) {
                         if (currentUser.hasProcessAssistantPermissions()) {
                             ProcessAssistant.departmentSelection(currentUser);
                         }
                         else {
                               System.out.println();
                               System.out.println("Error. You don't have Process Assistant permissions.");
                         }
                }
                else if (userInput == 6) {
                         continueInput = false;
                         System.out.println();
                         System.out.println("Log out successful.");
                }
                else if (userInput == 0) {
                         Test.start();
                }
                else if (userInput == 555) {
                         continueInput = false;
                         Main.runProgram = false;
                         System.out.println();
                         System.out.println("*******************");
                         System.out.println("TERMINATING PROGRAM");
                         System.out.println("*******************");
                         System.out.println();
                         System.out.println("This is a test to see whether the program terminates as it should.");
                         System.out.println("The program should run out of executable options.");
                }
                else {
                      System.out.println();
                      System.out.println("Invalid input. Try again");
                }
            }
            catch(InputMismatchException ex){
                input.nextLine();
                System.out.println();
                System.out.println("Invalid input. Try again.");
            }
        } while (continueInput) ;
    }
}
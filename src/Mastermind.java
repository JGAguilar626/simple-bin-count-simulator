import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Date;

public class Mastermind {

    static void ont2AndonOverview() {

        ArrayList<String> list = Main.ont2.getPendingAndonsP1ALocations();

        int numOfPendingAndonsP1A100s200s = 0;
        int numOfPendingAndonsP1A300s400s = 0;
        int numOfPendingAndonsP1A500s600s = 0;
        int numOfPendingAndonsP1A700s800s = 0;

        for (int i = 0; i < list.size(); i++) {
             if (list.get(i).charAt(10) == '1' || list.get(i).charAt(10) == '2')
                 numOfPendingAndonsP1A100s200s++;
             else if (list.get(i).charAt(10) == '3' || list.get(i).charAt(10) == '4')
                      numOfPendingAndonsP1A300s400s++;
             else if (list.get(i).charAt(10) == '5' || list.get(i).charAt(10) == '6')
                      numOfPendingAndonsP1A500s600s++;
            else if (list.get(i).charAt(10) == '7' || list.get(i).charAt(10) == '8')
                     numOfPendingAndonsP1A700s800s++;
        }

        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        System.out.println();
        System.out.println("*******************");
        System.out.println("ONT2 Andon Overview");
        System.out.println("*******************");
        System.out.println();
        System.out.println("P1A");
        System.out.println("---");
        System.out.println("Total Pending: " + Main.ont2.getNumOfAndonsP1A());
        System.out.println();
        System.out.println("100s & 200s: " + numOfPendingAndonsP1A100s200s);
        System.out.println("300s & 400s: " + numOfPendingAndonsP1A300s400s);
        System.out.println("500s & 600s: " + numOfPendingAndonsP1A500s600s);
        System.out.println("700s & 800s: " + numOfPendingAndonsP1A700s800s);

        do {
            System.out.println();
            System.out.print("Enter 1 to go back: ");

            try {
                 int userInput = input.nextInt();
                 input.nextLine();

                 if (userInput == 1) {
                     continueInput = false;
                } else {
                    System.out.println();
                    System.out.println("Invalid input. Try again.");
                }
            } catch (InputMismatchException ex) {
                input.nextLine();
                System.out.println();
                System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);
    }

    static void generateBinSkipsReport() {

        System.out.println();
        System.out.println("****************");
        System.out.println("Bin Skips Report");
        System.out.println("****************");
        System.out.println();
        System.out.println("Generated on: " + new Date());
        System.out.println();

        ArrayList<Employee> employees = Main.ont2.getEmployeeArrayList();
        ArrayList<String> loginsOfEmployeesWithMoreThan5Skips = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
             if (employees.get(i).getNumberOfBinsSkipped() > 5) {
                 loginsOfEmployeesWithMoreThan5Skips.add(employees.get(i).getLogin());
             }
        }

        loginsOfEmployeesWithMoreThan5Skips.trimToSize();

        if (loginsOfEmployeesWithMoreThan5Skips.size() == 0) {
            System.out.println("There are 0 employees with more than 5 skipped bins.");
        }
        else {
              System.out.println("Employees with More than 5 Bin Skips");
              System.out.println("------------------------------------");

              for (int j = 0; j < loginsOfEmployeesWithMoreThan5Skips.size(); j++) {
                   System.out.println(loginsOfEmployeesWithMoreThan5Skips.get(j));
              }
        }

        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        do {
            System.out.println();
            System.out.print("Enter 1 to go back: ");

            try {
                 int userInput = input.nextInt();
                 input.nextLine();

                 if (userInput == 1) {
                     continueInput = false;
                 }
                 else {
                       System.out.println();
                       System.out.println("Invalid input. Try again.");
                 }
            } catch (InputMismatchException ex) {
                input.nextLine();
                System.out.println();
                System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);
    }
}


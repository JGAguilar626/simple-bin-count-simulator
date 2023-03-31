import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public abstract class ProblemSolving {

    public static void problemSolvingMainMenu(Employee currentUser) {

        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        do {
            try {
                 System.out.println();
                 System.out.println("*************************");
                 System.out.println("Problem Solving Main Menu");
                 System.out.println("*************************");
                 System.out.println();
                 System.out.println("1. ONT2 Andon Overview");
                 System.out.println("2. Resolve Andons");
                 System.out.println("3. Return To Department/Process Selection Menu");
                 System.out.println();
                 System.out.print("Enter choice: ");

                 int userInput = input.nextInt();
                 input.nextLine();

                 if (userInput == 1) {
                     Mastermind.ont2AndonOverview();
                 }
                 else if (userInput == 2) {
                     System.out.println();
                     System.out.println("This feature isn't available yet.");
                     //ProblemSolving.resolveAndons(currentUser);
                }
                 else if (userInput == 3) {
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

    /*
    private static void resolveAndons(Employee currentUser) {

        System.out.println();
        System.out.println("****************");
        System.out.println("Andon Resolution");
        System.out.println("****************");
        System.out.println();
    }
    */

}

// DESIGN NOTES AND IDEAS:
//
// * The code below is code that I used in a previous version of this program. It calculates and rounds the percent
//   of receptacles (in the entire fc) with Andons.
//   int fcPercentOfBinsWithAndons = Math.round(((((float)Andon.totalFCAndons)/Main.getTotalNumberOfBinsInFC()) * 100));
//
// * This class should probably be re-designed to be an interface.


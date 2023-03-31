import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Date;

public class ProcessAssistant {

    static void departmentSelection(Employee currentUser) {

        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        do {
            System.out.println();
            System.out.println("***************************");
            System.out.println("Process Assistant Main Menu");
            System.out.println("***************************");
            System.out.println();
            System.out.println("Please select a department.");
            System.out.println();
            System.out.println("1. ICQA");
            System.out.println("2. Return To Department/Process Selection Menu");
            System.out.println();
            System.out.print("Enter choice: ");

            try {
                 int userInput = input.nextInt();
                 input.nextLine();

                 if  (userInput == 1) {
                      ProcessAssistant.icqaMetricsMenu(currentUser);
                 }
                 else if (userInput == 2) {
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

        private static void icqaMetricsMenu(Employee currentUser) {

            Scanner input = new Scanner(System.in);
            boolean continueInput = true;

            do {
                System.out.println();
                System.out.println("*****************");
                System.out.println("ICQA Metrics Menu");
                System.out.println("*****************");
                System.out.println();
                System.out.println("1. View SBC Metrics");
                System.out.println("2. View CC Metrics");
                System.out.println("3. Andon Metrics Menu");
                System.out.println("4. View Bin Skips Data");
                System.out.println("5. Go Back");
                System.out.println();
                System.out.print("Enter choice: ");

                try {
                     int userInput = input.nextInt();
                     input.nextLine();

                     if (userInput == 1) {
                         ProcessAssistant.sbcMetrics(currentUser);
                     }
                     else if (userInput == 2) {
                              ProcessAssistant.cycleCountMetrics(currentUser);
                     }
                     else if (userInput == 3) {
                              ProcessAssistant.andonMetricsMenu(currentUser);
                     }
                     else if (userInput == 4) {
                             ProcessAssistant.binSkipsData(currentUser);
                     }
                     else if (userInput == 5) {
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

        private static void sbcMetrics(Employee currentUser) {

            System.out.println();
            System.out.println("***********");
            System.out.println("SBC Metrics");
            System.out.println("***********");
            System.out.println();
            System.out.println("P1A");
            System.out.println("---");
            System.out.println("Pending: " + Main.ont2.getPendingSBCP1A_List_Size());

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

        private static void cycleCountMetrics(Employee currentUser) {

            System.out.println();
            System.out.println("**********");
            System.out.println("CC Metrics");
            System.out.println("**********");
            System.out.println();
            System.out.println("P1A");
            System.out.println("---");
            System.out.println("Pending: " + Main.ont2.getNumberOfPendingCycleCountsP1A());

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

        private static void andonMetricsMenu(Employee currentUser) {

            Scanner input = new Scanner(System.in);
            boolean continueInput = true;

            do {
                System.out.println();
                System.out.println("******************");
                System.out.println("Andon Metrics Menu");
                System.out.println("******************");
                System.out.println();
                System.out.println("1. ONT2 Andon Overview");
                System.out.println("2. Go Back");
                System.out.println();
                System.out.print("Enter choice: ");

                try {
                     int userInput = input.nextInt();
                     input.nextLine();

                    if (userInput == 1) {
                        Mastermind.ont2AndonOverview();
                    }
                    else if (userInput == 2) {
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

        private static void binSkipsData(Employee currentUser) {

            Scanner input = new Scanner(System.in);
            boolean continueInput = true;

            do {
                System.out.println();
                System.out.println("*******************");
                System.out.println("Bin Skips Data Menu");
                System.out.println("*******************");
                System.out.println();
                System.out.println("1. Generate Bin Skips Report");
                System.out.println("2. See Bin Skips by Login");
                System.out.println("3. Go Back");
                System.out.println();
                System.out.print("Enter choice: ");

                try {
                     int userInput = input.nextInt();
                     input.nextLine();

                    if (userInput == 1) {
                        Mastermind.generateBinSkipsReport();
                    }
                    else if (userInput == 2) {
                             //Mastermind.seeBinSkipsByLogin(currentUser);
                             System.out.println();
                             System.out.println("Sorry, this feature is not available yet.");
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
}
// DESIGN NOTES AND IDEAS:
//
// * Perhaps re-structure the code in the try blocks, see departmentSelection(Employee currentUser)
//   The code in the method above is not perfect either, could probably be structured better. Try having ONLY the code
//   that might throw an exception inside the try braces.
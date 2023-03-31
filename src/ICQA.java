import java.util.Scanner;
import java.util.InputMismatchException;

public class ICQA {

    static void processSelection(Employee currentUser) {

        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        do {
            try {
                 System.out.println();
                 System.out.println("**********************");
                 System.out.println("ICQA Process Selection");
                 System.out.println("**********************");
                 System.out.println();
                 System.out.println("1. SBC");
                 System.out.println("2. Return To Department/Process Selection Menu");
                 System.out.println();
                 System.out.print("Enter choice: ");

                 int userInput = input.nextInt();
                 input.nextLine();

                 // "1" is a legal input BUT I do not make continueInput = false because this program gives the
                 // employee the option to sign-out of the SBC process and return to this menu
                 if (userInput == 1) {
                     if (currentUser.isSBCTrained()) {
                         ICQA.simpleBinCount(currentUser);
                     }
                     else {
                           System.out.println();
                           System.out.println("You don't have SBC permissions.");
                     }
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
                     System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);
    }

    private static void simpleBinCount(Employee currentUser) {

        System.out.println();
        System.out.println("****************");
        System.out.println("Simple Bin Count");
        System.out.println("****************");

        while (Main.ont2.getPendingSBCP1A_List_Size() > 0) {
            // Method getPendingSBCP1ALocations() in class ONT2:
            // orders ont2's ArrayList<InventoryReceptacle> pendingSBCP1A field by pickPathID -->
            // creates an ArrayList of Strings --> fills the list w/ the locations of ont2's pendingSBCP1A receptacles

            // ont2's pendingSBCP1A list is populated at program start in class ONT2Initializer and receptacles aren't
            // added to it, only removed by completing a count or pulling an andon
            int i = 0;
            String location = Main.ont2.getPendingSBCP1ALocations().get(i);

            // A counter can skip a location at his discretion. Once skipped, the counter shouldn't be assigned the
            // skipped location again. However, the skipped location SHOULD NOT be removed from ont2's pendingSBCP1A
            // field, it should be assigned to the next available counter.
            if (currentUser.getLastCompletedLocation() != null) {
                while (Main.ont2.getInventoryReceptacle(location).getPickPathID() <=
                        currentUser.getLastCompletedReceptaclePickPathID()) {
                        i++;
                        location = Main.ont2.getPendingSBCP1ALocations().get(i);
                }
            }

            String userInput = ICQA.sbcScreen1(location);

            if (userInput.equalsIgnoreCase("p")) {
                ICQA.sbcProblemHandling(currentUser, location, 1);
            }
            else if (userInput.equalsIgnoreCase("n")) {
                     ICQA.updateFieldsAfterLocationSkip(currentUser, location);
                     System.out.println();
                     System.out.println("Location skipped.");
            }
            //"s" for (s)ign-out of the SBC process, returns the counter to the ICQA Process Selection menu
            else if (userInput.equalsIgnoreCase("s")) {
                     ICQA.updateFieldsAfterProcessExit(currentUser, location);
                     break;
            }
            else {
                  //sends counter to the screen in which they are prompted to enter the results of their first count
                  boolean continueCount = ICQA.sbcScreen2(currentUser, location);

                  if (continueCount) {
                      ICQA.sbcScreen3(currentUser, location);
                  }
            }
        }
        //TODO: ADD MESSAGE SUCH AS "NO MORE COUNTS ON THIS FLOOR" HERE
    }

    private static String sbcScreen1(String location) {

        Scanner input = new Scanner(System.in);
        String userInput;
        boolean continueInput = true;

        do {
            System.out.println();
            System.out.println("-------------------------");
            System.out.println(location);
            System.out.print("Scan location or enter 1.");
            System.out.println();
            System.out.println("-------------------------");

            userInput = input.nextLine().trim();

            //TODO: Perhaps invoke method below somewhere else. Should the purpose of this method be to ONLY
            //TODO: get userInput?
            boolean scannedIntoLocation = ICQA.compareInputToLocation(userInput, location);

            // if user is scanned into the location, or they entered other legal values
            // (p = (p)roblem menu, n = (n)ext, s = (s)ign out), return userInput to caller
            if (scannedIntoLocation || userInput.equalsIgnoreCase("p") ||
                userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("s")) {

                continueInput = false;
            }
            else {
                  System.out.println();
                  System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);

        return userInput;
    }

    private static boolean compareInputToLocation(String userInput, String location) {

        //could have simplified this code, but I wanted to check userInput for legal values without relying
        //on Exception handling
        //TODO: try and crash the program with invalid inputs at sbcScreen1

        boolean match = false;

        // if userInput matches the location that they are prompted to scan, make match = true
        // because this is a simulation, I allow the counter to enter "1" (as opposed to, say, P1A-102-A-100)
        if (userInput.equalsIgnoreCase(location))
            match = true;
        else {
              // check every index in userInput to know if EVERY index contains a char that isDigit, & if so, parse it

              // The code below eliminates negative and decimal numeric values because Character.isDigit('-') and
              // Character.isDigit('.') both return false. This is important because having a negative or decimal input
              // doesn't make sense.
              boolean everyIndexIsDigit = false;

              for (int i = 0; i < userInput.length(); i++) {
                   char var = userInput.charAt(i);

                   // if a non-digit character is found, break out of loop
                   if (!Character.isDigit(var)) {
                       break;
                   }
                   // if at the last index of userInput and still inside for loop, make everyIndexIsDigit = true
                   if (i == userInput.length() - 1) {
                       everyIndexIsDigit = true;
                   }
              }
              // if everyIndexIsDigit = true, parse userInput and check to see if its value = 1
              // (recall that the program allows the counter to enter a String such as P1A-102-A-100 OR "1")
              if (everyIndexIsDigit) {

                  //recall that the substring method creates a substring from beginning index to (ending index - 1)
                  //in other words, if the user enters: 000001 the substring will be "00000" which is not valid
                  //                if the user enters: 000010 the substring will be "00001" which is valid

                  if (userInput.length() > 5) {
                      userInput = userInput.substring(0, 5);
                  }

                  if (Integer.parseInt(userInput) == 1) {
                      match = true;
                  }
              }
        }
        // match should be true if and only if userInput matches the receptacle location or userInput is = 1
        // NOTE: 00001 is valid
        return match;
    }

    private static boolean sbcScreen2(Employee currentUser, String location) {

        Integer firstCount = null;
        Boolean continueCount = null;

        // use String location to get InventoryReceptacle and its numberOfItems
        int numberOfItems = Main.ont2.getInventoryReceptacle(location).getNumberOfItems();

        // display items as asterisks on the console (only done for simulation purposes)
        SimulationTool.displayPhysicalInventoryAsAsterisks(numberOfItems);

        boolean continueInput = true;

        do {
            // prompt user to enter number of items physically present in receptacle
            String userInput = ICQA.promptUserToEnterCount(1);

            boolean validCountInput = ICQA.checkForValidCountInputSBC(userInput);

            if (validCountInput) {
                if (userInput.equalsIgnoreCase("p")) {
                    boolean andonConfirmed = ICQA.sbcProblemHandling(currentUser, location, 2);
                    if (andonConfirmed) {
                        continueInput = false;
                        continueCount = false;
                    }
                }
                else {
                      // the statement below should only be reachable if the counter entered a valid input
                      // (controlled by the variable validCountInput) so Integer.parseInt(userInput) SHOULD NOT
                      // throw an exception below
                      continueInput = false;
                      firstCount = Integer.parseInt(userInput);
                }
            }
            else {
                  System.out.println();
                  System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);

        // Checks that firstCount != null because if the counter pulls an andon, firstCount will be null.

        if (firstCount != null) {
            if (firstCount == numberOfItems) {
                continueCount = false;
                ICQA.updateFieldsAfterCountCompleted(currentUser, location);
                System.out.println();
                System.out.println("Count completed.");
            }
            else {
                  continueCount = true;
            }
        }
        return continueCount;
    }

    private static void sbcScreen3(Employee currentUser, String location) {

        Integer secondCount = null;

        //I use String for userInput below because the counter can enter "p" for (p)roblem menu
        String userInput;

        boolean continueInput = true;

        do {
            userInput = ICQA.promptUserToEnterCount(2);

            boolean validUserInput = ICQA.checkForValidCountInputSBC(userInput);

            if (validUserInput) {
                if (userInput.equalsIgnoreCase("p")) {
                    boolean andonConfirmed = ICQA.sbcProblemHandling(currentUser, location, 3);
                    if (andonConfirmed) {
                        continueInput = false;
                    }
                }
                else {
                      continueInput = false;
                      secondCount = Integer.parseInt(userInput);
                }
            }
            else {
                System.out.println();
                System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);

        if (secondCount != null) {
            if (secondCount != Main.ont2.getInventoryReceptacle(location).getNumberOfItems()) {
                Main.ont2.getInventoryReceptacle(location).setNeedsCycleCount(true);
            }
            ICQA.updateFieldsAfterCountCompleted(currentUser, location);
            System.out.println();
            System.out.println("Count completed.");
        }
    }

    private static String promptUserToEnterCount(int attemptNumber) {

        Scanner input = new Scanner(System.in);
        String message;
        String userInput;
        boolean continueInput = true;

        message = attemptNumber == 1 ? "Count items." : "Re-count items.";

        do {
             System.out.println();
             System.out.println(message);
             System.out.print("Enter count: ");

             userInput = input.nextLine().trim();

             if (userInput.isEmpty()) {
                 System.out.println();
                 System.out.println("Invalid input. Try again.");
             }
             else {
                   continueInput = false;
             }
        } while (continueInput);

        return userInput;
    }

    private static boolean checkForValidCountInputSBC(String userInput) {

        Boolean validCountInput = null;

        if (userInput.equalsIgnoreCase("p")) {
            validCountInput = true;
        }
        // check every index for non-digits, as soon as one is found, break loop
        // if at last index and program doesn't break out of loop, String is all digits, so parseInt(userInput)
        else {
              for (int i = 0; i < userInput.length(); i++) {
                   if (!Character.isDigit(userInput.charAt(i))) {
                       validCountInput = false;
                       break;
                   }
                   // the condition: userInput.length() <= 4 prevents overflow, also, in a real life setting, no
                   // receptacle should have more than 9,999 items, so a valid count shouldn't have a length > 4
                   if (i == userInput.length() - 1) {
                       validCountInput = userInput.length() <= 4;
                   }
              }
            }
        return validCountInput;
    }

    private static boolean sbcProblemHandling(Employee currentUser, String location, int sbcProcessScreen) {

        boolean andonConfirmed = false;
        Integer userInput = null;

        if (sbcProcessScreen == 1) {
            userInput = ICQA.sbcProblemMenu1();
        }
        else if (sbcProcessScreen == 2 || sbcProcessScreen == 3) {
                 userInput = ICQA.sbcProblemMenu2();
        }

        if (userInput == 1 || userInput == 2 || userInput == 3) {
            andonConfirmed = ICQA.promptUserToConfirmAndonSBC();
            if (andonConfirmed) {
                Andon andon = ICQA.createAndon(currentUser, location, sbcProcessScreen, userInput);
                ICQA.updateFieldsAfterAndonConfirmed(currentUser, andon);
            }
        }
        return andonConfirmed;
    }

    private static int sbcProblemMenu1() {

        Scanner input = new Scanner(System.in);
        Integer userInput = null;
        boolean continueInput = true;

        do {
            try {
                System.out.println();
                System.out.println("****************");
                System.out.println("SBC Problem Menu");
                System.out.println("****************");
                System.out.println();
                System.out.println("Make a selection.");
                System.out.println();
                System.out.println("1. Bin does Not Exist");
                System.out.println("2. No Scannable Bin Label");
                System.out.println("3. Unsafe to Count");
                System.out.println("4. Go Back");
                System.out.println();
                System.out.print("Enter Choice: ");

                // token-based input, a counter can input "[][]4[]777[]" and the program will accept it as the user
                // choosing option "4"
                userInput = input.nextInt();
                input.nextLine();

                if (userInput == 1 || userInput == 2 || userInput == 3 || userInput == 4) {
                    continueInput = false;
                }
                else {
                      System.out.println();
                      System.out.println("Invalid input. Try again.");
                }
            } catch (InputMismatchException exc) {
                     input.nextLine();
                     System.out.println();
                     System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);

        return userInput;
    }

    // more than one Problem Menu because when a counter is scanned into a receptacle, andons like "Bin does Not Exist"
    // and "No Scannable Bin Label" are no longer relevant
    private static int sbcProblemMenu2() {

        Scanner input = new Scanner(System.in);
        Integer userInput = null;
        boolean continueInput = true;

        do {
            try {
                System.out.println();
                System.out.println("****************");
                System.out.println("SBC Problem Menu");
                System.out.println("****************");
                System.out.println();
                System.out.println("Make a selection.");
                System.out.println();
                System.out.println("1. Damaged Item");
                System.out.println("2. Broken Set");
                System.out.println("3. Suspected Theft");
                System.out.println("4. Go Back");
                System.out.println();
                System.out.print("Enter Choice: ");

                userInput = input.nextInt();
                input.nextLine();

                if (userInput == 1 || userInput == 2 || userInput == 3 || userInput == 4) {
                    continueInput = false;
                }
                else {
                      System.out.println();
                      System.out.println("Invalid input. Try again.");
                }
            } catch (InputMismatchException exc) {
                     input.nextLine();
                     System.out.println();
                     System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);

        return userInput;
    }

    private static boolean promptUserToConfirmAndonSBC() {

        Scanner input = new Scanner(System.in);
        Boolean andonConfirmed = null;
        boolean continueInput = true;

        do {
            System.out.println();
            System.out.println("Confirm andon?");
            System.out.print("Enter 'c' to confirm, or 'b' to go back: ");

            String userInput = input.nextLine().trim();

            if (userInput.equalsIgnoreCase("c")) {
                andonConfirmed = true;
                continueInput = false;
            }
            else if (userInput.equalsIgnoreCase("b")) {
                     andonConfirmed = false;
                     continueInput = false;
            }
            else {
                  System.out.println();
                  System.out.println("Invalid input. Try again.");
            }
        } while (continueInput);

        return andonConfirmed;
    }

    public static Andon createAndon(Employee currentUser, String location, int sbcProcessScreen, int andonType) {

        Andon andon = null;
        String currentUserLogin = currentUser.getLogin();

        if (sbcProcessScreen == 1) {
            if (andonType == 1) {
                andon = new BinDoesNotExistAndon(location, currentUserLogin);
            }
            else if (andonType == 2) {
                     andon = new NoScannableBinLabelAndon(location, currentUserLogin);
            }
            else if (andonType == 3) {
                     andon = new UnsafeToCountAndon(location, currentUserLogin);
            }
        }
        else if (sbcProcessScreen == 2) {
                 if (andonType == 1) {
                     andon = new DamageAndon(location, currentUserLogin);
                 }
                 else if (andonType == 2) {
                          andon = new BrokenSetAndon(location, currentUserLogin);
                 }
                 else if (andonType == 3) {
                          andon = new SuspectedTheftAndon(location, currentUserLogin);
                 }
        }
        // the condition below should always evaluate to true, otherwise logic error
        if (andon != null) {
            System.out.println();
            System.out.println("Andon successfully created.");
        }
        return andon;
    }

    private static void updateFieldsAfterCountCompleted(Employee currentUser, String location) {

        Main.ont2.removeReceptacleFromPendingSBCP1A(location);
        Main.ont2.getInventoryReceptacle(location).setNeedsSimpleBinCount(false);

        currentUser.increaseCompletedSBC_Counts();
        currentUser.setLastCompletedLocation(location);
    }

    private static void updateFieldsAfterAndonConfirmed(Employee currentUser, Andon andon) {

        String location = andon.getLocation();
        InventoryReceptacle receptacle = Main.ont2.getInventoryReceptacle(location);

        receptacle.addAndon(andon);
        receptacle.setHasAndon(true);
        receptacle.setNeedsSimpleBinCount(false);

        Main.ont2.removeReceptacleFromPendingSBCP1A(location);
        Main.ont2.addToPendingAndonsP1A(andon);
        Main.ont2.addToNumOfAndonsP1A();

        currentUser.increaseCompletedSBC_Counts();
        currentUser.setLastCompletedLocation(location);
        currentUser.addToListOfAndonsCreated(location);
    }

    private static void updateFieldsAfterLocationSkip(Employee currentUser, String location) {

        currentUser.addToListOfSkippedLocations(location);
        currentUser.setLastCompletedLocation(location);
    }

    private static void updateFieldsAfterProcessExit(Employee currentUser, String location) {
    }
}
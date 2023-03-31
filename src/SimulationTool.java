import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SimulationTool {

    static void createSimpleBinCounts(ArrayList<ArrayList<InventoryReceptacle>> outerList,
                                      ArrayList<InventoryReceptacle> list) {

        // add a value for every receptacle's numberOfItems field
        SimulationTool.addItemsToReceptacles(outerList);

        // traverse ont2's outerList of receptacles and tag a percentage as needing a Simple Bin Count
        // percentage value is hardcoded inside called method because a program that manually creates counts
        // with customizable percentages is beyond the scope of this program
        SimulationTool.markOuterListOfReceptaclesAsNeedingSBC(outerList);

        // invoke method that adds P1A receptacles that need SBC to list
        SimulationTool.addReceptaclesThatNeedSBCToList(outerList, list);

        list.trimToSize();

        // re-arrange the receptacles in pendingSBCP1A by their pickPathID
        SimulationTool.orderReceptaclesByPickID(list);
    }

    static void addItemsToReceptacles(ArrayList<ArrayList<InventoryReceptacle>> outerList) {

        ArrayList<InventoryReceptacle> innerList;

        for (int i = 0; i < outerList.size(); i++) {
             innerList = outerList.get(i);

            // generate a random int value (range: 0 <--> 50, inclusive both end #s)
            // to every receptacle's numberOfItems field
            for (int j = 0; j < innerList.size(); j++) {
                 int randomNumber = (int)(Math.random() * 51);
                 innerList.get(j).setNumberOfItems(randomNumber);
            }
        }
    }

    private static void markOuterListOfReceptaclesAsNeedingSBC(ArrayList<ArrayList<InventoryReceptacle>> outerList) {

        // generate a random int value (range: 1 <--> 100, inclusive both end #s)
        // if the int is <= 20 (20% chance of the random number meeting this condition), mark the
        // current bin as needing Simple Bin Count
        ArrayList<InventoryReceptacle> innerList;

        for (int i = 0; i < outerList.size(); i++) {
             innerList = outerList.get(i);

             for (int j = 0; j < innerList.size(); j++) {
                  int randomNumber = 1 + (int)(Math.random() * 100);
                  if (randomNumber <= 20)
                      innerList.get(j).setNeedsSimpleBinCount(true);
            }
        }
    }

    private static void addReceptaclesThatNeedSBCToList(ArrayList<ArrayList<InventoryReceptacle>> outerList,
                                                        ArrayList<InventoryReceptacle> list) {

        ArrayList<InventoryReceptacle> innerList;

        for (int i = 0; i < outerList.size(); i++) {
             innerList = outerList.get(i);

             for (int j = 0; j < innerList.size(); j++) {
                  if (innerList.get(j).getNeedsSimpleBinCount()) {
                      list.add(innerList.get(j));
                }
            }
        }
    }

    static void displayPhysicalInventoryAsAsterisks(int numberOfItems) {

        System.out.println();
        System.out.println("-----");
        System.out.println(numberOfItems);
        System.out.println();

        for (int i = 0; i < numberOfItems; i++) {
            if ((i + 1) % 5 == 0)
                System.out.println('*');
            else
                System.out.print('*');
        }
        if (numberOfItems % 5 != 0) {
            System.out.println();
            System.out.println("-----");
        }
        else {
            System.out.println("-----");
        }
    }

    static void orderReceptaclesByPickID(ArrayList<InventoryReceptacle> list) {
        Collections.sort(list, new BinSortByPickID());
    }

    static void showEnter1ToGoBackScreen() {

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
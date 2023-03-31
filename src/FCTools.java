import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FCTools {

    static void orderReceptaclesByPickID(ArrayList<InventoryReceptacle> list) {
        Collections.sort(list, new BinSortByPickID());
    }

    /*
    // Might change .getLocation() to printLocation(), a method that prints the string inside the Bin class.
    static void printReceptacleLocations(ArrayList<InventoryReceptacle> list) {

        System.out.println();

        for (int i = 0; i < list.size(); i++) {
             if ((i + 1) % 11 == 0)
                 System.out.println(list.get(i).getLocation());
             else
                 System.out.print(list.get(i).getLocation() + " | ");
        }
        System.out.println();
    }

    // Might change .getLocation() to printLocation(), a method that prints the string inside the Bin class.
    static void printOuterListOfReceptacleLocations(ArrayList<ArrayList<InventoryReceptacle>> outerList) {

        System.out.println();

        ArrayList<InventoryReceptacle> innerList;

        for (int i = 0; i < outerList.size(); i++) {
            innerList = outerList.get(i);

            for (int j = 0; j < innerList.size(); j++) {
                if ((j + 1) % 11 == 0)
                    System.out.println(innerList.get(j).getLocation());
                else
                    System.out.print(innerList.get(j).getLocation() + " | ");
            }
        }
        System.out.println();
    }
    */

    }


// DESIGN NOTES AND IDEAS:
//
// * perhaps make this class into an interface instead, implemented by Problem Solvers, Process Assistants,
//   Area Managers, etc.
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public final class ONT2Initializer {

    static void initializeONT2Fields(ONT2 ont2) {

        // create a local list of Employee objects which helps to keep the field encapsulated
        ArrayList<Employee> list = new ArrayList<>(100);

        // pass the local list to a method that creates 4 employees and adds them to the list
        ONT2Initializer.createEmployees(list);

        // use for loop to add all employees to ont2's ArrayList<Employee> employees
        for (int i = 0; i < list.size(); i++) {
             ont2.addEmployee(list.get(i));
        }

        // In this version of my program, I first create the list of ALL P1A receptacles
        // as opposed to creating P1A-100s, P1A-200s, etc. AND THEN a list(s) with all
        // receptacles. I do this because this version of my program DOES NOT include some
        // important algos (for intellectual property reasons).
        ArrayList<InventoryReceptacle> list10 = ONT2Initializer.createP1AReceptacles();
        ont2.setP1AReceptacles(list10);

        // Extract individual rows (100s, 200s, etc.) from list10, pass the lists' reference to
        // corresponding ont2 fields.

        // P1A 100s //
        ArrayList<InventoryReceptacle> list1 = ONT2Initializer.extractP1A100sReceptacles(list10);
        ont2.setP1A100sReceptacles(list1);

        // P1A 200s //
        ArrayList<InventoryReceptacle> list2 = ONT2Initializer.extractP1A200sReceptacles(list10);
        ont2.setP1A200sReceptacles(list2);


        // P1A 300s //
        ArrayList<InventoryReceptacle> list3 = ONT2Initializer.extractP1A300sReceptacles(list10);
        ont2.setP1A300sReceptacles(list3);

        // P1A 400s //
        ArrayList<InventoryReceptacle> list4 = ONT2Initializer.extractP1A400sReceptacles(list10);
        ont2.setP1A400sReceptacles(list4);

        // P1A 500s //
        ArrayList<InventoryReceptacle> list5 = ONT2Initializer.extractP1A500sReceptacles(list10);
        ont2.setP1A500sReceptacles(list5);

        // P1A 600s //
        ArrayList<InventoryReceptacle> list6 = ONT2Initializer.extractP1A600sReceptacles(list10);
        ont2.setP1A600sReceptacles(list6);

        // P1A 700s //
        ArrayList<InventoryReceptacle> list7 = ONT2Initializer.extractP1A700sReceptacles(list10);
        ont2.setP1A700sReceptacles(list7);

        // P1A 800s //
        ArrayList<InventoryReceptacle> list8 = ONT2Initializer.extractP1A800sReceptacles(list10);
        ont2.setP1A800sReceptacles(list8);

        // ALL P1A RECEPTACLES INDEXED BY ROW //
        ArrayList<ArrayList<InventoryReceptacle>> list9 = new ArrayList<>(8);

        // add 8 inner lists to the outer list
        list9.add(list1);
        list9.add(list2);
        list9.add(list3);
        list9.add(list4);
        list9.add(list5);
        list9.add(list6);
        list9.add(list7);
        list9.add(list8);

        // set list9 as ont2's p1AReceptaclesIndexed
        ont2.setP1AReceptaclesIndexed(list9);

        // ALL ONT2 RECEPTACLES INDEXED BY FLOOR //
        ArrayList<ArrayList<InventoryReceptacle>> list11 = new ArrayList<>(6);
        list11.add(list10);
        ont2.setListOfAllReceptacles(list11);

        // create list to hold all receptacles on floor P1A that need SBC and initialize it with a size of 38_192...
        // each floor has 76,384 receptacles, and when manually creating counts, don't mark more than 50% of total
        // floor receptacles as needing SBC, so the size limit should be 38,192
        ArrayList<InventoryReceptacle> list12 = new ArrayList<>(38_192);

        // in real life, most SBC counts are created organically by the stow and pick processes...
        // in this program, all counts are manually created
        SimulationTool.createSimpleBinCounts(list9, list12);

        // set list12 as ont2's pendingSBCP1A
        ont2.setPendingSBCP1A(list12);
    }

    private static void createEmployees(ArrayList<Employee> list) {

        Employee joseag = new Employee("Jose Aguilar", "joseag", 10404);
        Employee griffiz = new Employee("Zachary Griffith", "griffiz", 100);
        Employee judanie = new Employee("Justin Daniels", "judanie", 101);
        Employee jontorre = new Employee("Jonathan Torres", "jontorre", 102);

        joseag.setSBCPermissions(true);

        griffiz.setSBCPermissions(true);

        judanie.setSBCPermissions(true);
        judanie.setProblemSolvingPermissions(true);

        jontorre.setSBCPermissions(true);
        jontorre.setProblemSolvingPermissions(true);
        jontorre.setProcessAssistantPermissions(true);

        list.add(joseag);
        list.add(griffiz);
        list.add(judanie);
        list.add(jontorre);

        list.trimToSize();
    }

   private static ArrayList<InventoryReceptacle> createP1AReceptacles() {

        // capacity of 76_384 receptacles for floor P1A (9_548 receptacles/row)
        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(76_348);

        // I don't want to include the algos that I used to instantiate my inventory
        // receptacles' locations. Instead, I will read in the locations from a text file
        // and store them in this array.
        String[] p1AReceptaclesLocations = new String[76_384];

        // Same as above, but w/ receptacles' pickPathID.
        int[] p1AReceptaclesPickPathIds = new int[76_384];

        // read location values from text file and store them in an array
        try {
             Scanner input = new Scanner(new File("p1a-receptacles-locations.txt"));

             int i = 0;
             while (input.hasNext()) {
                    p1AReceptaclesLocations[i] = input.nextLine();
                    i++;
             }
        }
        catch(FileNotFoundException ex) {
              System.out.println("FileNotFound Exception, caused by attempt to read from" +
                                 " p1a-receptacles-locations.txt file");
        }

        // read pickPathID values from text file and store them in an array
        try {
             Scanner input = new Scanner(new File("p1a-receptacles-pickpath-ids.txt"));

             int i = 0;
             while (input.hasNext()) {
                    p1AReceptaclesPickPathIds[i] = input.nextInt();
                    i++;
             }
        }
        catch (FileNotFoundException ex) {
               System.out.println("FileNotFound Exception, caused by attempt to read from" +
                                  " p1a-receptacles-pickpath-ids.txt");
        }

        // Note that a receptacle's "id" field is different that its pickPathID field.
        // An int value for id, as well as a String value for location,
        // is needed to invoke the InventoryReceptacle constructor.
        for (int i = 0, binId = 1; i < 76_384; i++, binId++) {
             list.add(new Bin(binId, p1AReceptaclesLocations[i]));
        }

        for(int i = 0; i < list.size(); i++) {
            list.get(i).setPickPathID(p1AReceptaclesPickPathIds[i]);
        }

        return list;
    }

   private static ArrayList<InventoryReceptacle> extractP1A100sReceptacles(ArrayList<InventoryReceptacle> masterList) {

          ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
          list.addAll(masterList.subList(0, 9_548));

          return list;
    }

    private static ArrayList<InventoryReceptacle> extractP1A200sReceptacles(ArrayList<InventoryReceptacle> masterList) {

        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
        list.addAll(masterList.subList(9_548, 19_096));

        return list;
    }

    private static ArrayList<InventoryReceptacle> extractP1A300sReceptacles(ArrayList<InventoryReceptacle> masterList) {

        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
        list.addAll(masterList.subList(19_096, 28_644));

        return list;
    }

    private static ArrayList<InventoryReceptacle> extractP1A400sReceptacles(ArrayList<InventoryReceptacle> masterList) {

        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
        list.addAll(masterList.subList(28_644, 38_192));

        return list;
    }

    private static ArrayList<InventoryReceptacle> extractP1A500sReceptacles(ArrayList<InventoryReceptacle> masterList) {

        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
        list.addAll(masterList.subList(38_192, 47_740));

        return list;
    }

    private static ArrayList<InventoryReceptacle> extractP1A600sReceptacles(ArrayList<InventoryReceptacle> masterList) {

        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
        list.addAll(masterList.subList(47_740, 57_288));

        return list;
    }

    private static ArrayList<InventoryReceptacle> extractP1A700sReceptacles(ArrayList<InventoryReceptacle> masterList) {

        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
        list.addAll(masterList.subList(57_288, 66_836));

        return list;
    }

    private static ArrayList<InventoryReceptacle> extractP1A800sReceptacles(ArrayList<InventoryReceptacle> masterList) {

        ArrayList<InventoryReceptacle> list = new ArrayList<InventoryReceptacle>(9_548);
        list.addAll(masterList.subList(66_836, 76_384));

        return list;
    }
}

// list10 = list of all P1A receptacles, ordered 100s -> 200s -> 300s...

////////////////////////////////////////////////////////////////////////

// list1 = P1A-100s, extracted from list10
// list2 = P1A-200s, extracted from list10
// ...
// list8 = P1A-800s, extracted from list10

////////////////////////////////////////////////////////////////////////

// list9 = ArrayList<ArrayList<InventoryReceptacle>> containing all P1A bins indexed by row
//         for example, list9.get(0) contains all P1A-100s receptacles

// list11 = ONT2's ArrayList<ArrayList<InventoryReceptacle>>, containing all FC receptacles
//          I initialize it with a capacity of 6 (6 floors total) and add list 10 containing
//          all P1A receptacles to the list

////////////////////////////////////////////////////////////////////////

// list12 = initialize it with a capacity of 38,192 (half of 76,384 the total amount of receptacles
//          on floor P1A) because I want no more than 50% of receptacles to be tagged as needing a
//          SBC count...currently I have hardcoded about a 20% chance of an individual receptacle
//          being tagged as needing a SBC count
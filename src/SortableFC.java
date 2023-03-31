import java.util.ArrayList;
import java.util.Collections;

public abstract class SortableFC {

    private final String NAME;
    private ArrayList<Employee> employees;
    private ArrayList<ArrayList<InventoryReceptacle>> listOfAllReceptacles;

    SortableFC(String NAME) {
        this.NAME = NAME;
        this.employees = new ArrayList<>(10);
    }

    String getNAME() {
        return this.NAME;
    }

    ArrayList<Employee> getEmployeeArrayList() {
        this.employees.trimToSize();
        return this.employees;
    }

    void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    void setListOfAllReceptacles(ArrayList<ArrayList<InventoryReceptacle>> list) {
        this.listOfAllReceptacles = list;
    }

    protected static ArrayList<InventoryReceptacle> createBatchOfReceptacles
                                                    (int listCapacity, int numOfReceptaclesToCreate,
                                                     int startingBinID,
                                                     String locationPrefix, int startingAisleNumber,
                                                     char startingBinLevel, char endingBinLevel,
                                                     int startingBinNumber, int endingBinNumber) {

        ArrayList<InventoryReceptacle> list = new ArrayList<>(listCapacity);

        int aisleNumber = startingAisleNumber;
        char binLevel = startingBinLevel;
        int binNumber = startingBinNumber;
        int binID = startingBinID;

        for (int receptaclesCreated = 0; receptaclesCreated < numOfReceptaclesToCreate;
             receptaclesCreated++, binNumber++, binID++) {

            if (binNumber > endingBinNumber) {
                binNumber = startingBinNumber;
                binLevel++;
            }

            if (binLevel > endingBinLevel) {
                binLevel = startingBinLevel;
                aisleNumber++;
            }

            list.add(new Bin(binID, locationPrefix + "-" + aisleNumber + "-" + binLevel + "-" + binNumber));
        }
        return list;
    }

    InventoryReceptacle getInventoryReceptacle(String location) {

        ArrayList<InventoryReceptacle> innerList;
        InventoryReceptacle receptacle = null;

        for (int i = 0; i < this.listOfAllReceptacles.size(); i++) {

             innerList = this.listOfAllReceptacles.get(i);

             for (int j = 0; j < innerList.size(); j++) {
                  if (innerList.get(j).getLocation().equalsIgnoreCase(location)) {
                      receptacle = innerList.get(j);
                      break;
                  }
             }
        }
        return receptacle;
    }

    int getNumberOfInventoryReceptaclesInFC() {

        int number = 0;

        for (int i = 0; i < this.listOfAllReceptacles.size(); i++) {
             number += number + this.listOfAllReceptacles.get(i).size();
        }

        return number;
    }

    int getReceptaclePickPathID(String location) {

        ArrayList<InventoryReceptacle> innerList;
        InventoryReceptacle receptacle = null;

        for (int i = 0; i < this.listOfAllReceptacles.size(); i++) {
             innerList = this.listOfAllReceptacles.get(i);

             for (int j = 0; j < innerList.size(); j++) {
                  if (innerList.get(j).getLocation().equalsIgnoreCase(location)) {
                      receptacle = innerList.get(j);
                      break;
                }
            }
        }
        return receptacle.getPickPathID();
    }
}

// DESIGN NOTES AND IDEAS:
//
// *  Think about the implications of making the NAME field final, brush up on details relating to final fields.
//
// *  Think about the implications of having a getter method for the Employee ArrayList.
//
// *  Should I change formal param names from bin to receptacle? Probably not, this class is for SortableFCs.
//    Pallet type receptacles and the like can be manually created. Also, I can always add a receptacleType param.
//    Perhaps this method should return ArrayList<Bin>.
//
// *  Perhaps move orderReceptaclesByPickID() to SimulationTools or ONT2Initializer.
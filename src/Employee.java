import java.util.ArrayList;

public class Employee {

    private String name;
    private int id;
    private String login;
    private boolean sbcPermissions;
    private boolean problemSolvingPermissions;
    private boolean processAssistantPermissions;
    private String lastCompletedLocation;
    private int numberOfCompletedSBC_Counts;
    private ArrayList<String> listOfAndonsCreated = new ArrayList<>();
    private ArrayList<String> listOfSkippedLocations = new ArrayList<>();

    Employee(String name, String login, int id) {
             this.name = name;
             this.login = login;
             this.id = id;
    }

    String getName() {
           return this.name;
    }

    int getID() {
        return this.id;
    }

    String getLogin() {
           return this.login;
    }

    boolean isSBCTrained() {
            return this.sbcPermissions;
    }

    void setSBCPermissions(boolean foobar) {
         this.sbcPermissions = foobar;
    }

    boolean hasProblemSolvingPermissions() {
            return this.problemSolvingPermissions;
    }

    void setProblemSolvingPermissions(boolean foobar) {
         this.problemSolvingPermissions = foobar;
    }

    boolean hasProcessAssistantPermissions() {
            return this.processAssistantPermissions;
    }

    void setProcessAssistantPermissions(boolean foobar) {
         this.processAssistantPermissions = foobar;
    }

    String getLastCompletedLocation() {
           return this.lastCompletedLocation;
    }

    int getLastCompletedReceptaclePickPathID() {
        return Main.ont2.getReceptaclePickPathID(this.lastCompletedLocation);
    }

    void setLastCompletedLocation(String location) {
         this.lastCompletedLocation = location;
    }

    int getNumberOfCompletedSBC_Counts() {
        return this.numberOfCompletedSBC_Counts;
    }

    void increaseCompletedSBC_Counts() {
         this.numberOfCompletedSBC_Counts++;
    }

    ArrayList<String> getListOfAndonsCreated() {
              this.listOfAndonsCreated.trimToSize();
              return this.listOfAndonsCreated;
    }

    void addToListOfAndonsCreated(String location) {
         this.listOfAndonsCreated.add(location);
    }

    ArrayList<String> getListOfSkippedLocations() {
              this.listOfSkippedLocations.trimToSize();
              return this.listOfSkippedLocations;
    }

    int getNumberOfBinsSkipped() {
        return this.listOfSkippedLocations.size();
    }

    void addToListOfSkippedLocations(String location) {
         this.listOfSkippedLocations.add(location);
    }
}

// DESIGN NOTES AND IDEAS:
//
// * I have one general Employee class. If I have time to refine this project, perhaps I should make this
//   class abstract and extend it with subclasses such as FC Associate I, Process Assistant, etc. Process Assistant
//   should probably be a subclass and Problem Solver should be an interface.
//
// * Some setter methods, such as setName() (not included in this program), should only be
//   accessed by HR (this would be a separate program).
//
// * Think about better ways to implement the fields listOfAndonsCreated and listOfSkippedBins. Might not
//   have a getter method that returns the actual fields, but simply copies them or displays the relevant
//   info on the console.

import java.util.ArrayList;

public final class ONT2 extends SortableFC {

    private ArrayList<InventoryReceptacle> p1A100sReceptacles;
    private ArrayList<InventoryReceptacle> p1A200sReceptacles;
    private ArrayList<InventoryReceptacle> p1A300sReceptacles;
    private ArrayList<InventoryReceptacle> p1A400sReceptacles;
    private ArrayList<InventoryReceptacle> p1A500sReceptacles;
    private ArrayList<InventoryReceptacle> p1A600sReceptacles;
    private ArrayList<InventoryReceptacle> p1A700sReceptacles;
    private ArrayList<InventoryReceptacle> p1A800sReceptacles;
    private ArrayList<ArrayList<InventoryReceptacle>> p1AReceptaclesIndexedByRows;
    private ArrayList<InventoryReceptacle> p1AReceptacles;
    private ArrayList<InventoryReceptacle> pendingSBCP1A;
    private ArrayList<Andon> pendingAndonsP1A = new ArrayList<>();
    private int numOfAndonsP1A;


    ONT2(String NAME) {
         super(NAME);
    }

    void setP1A100sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A100sReceptacles = list;
    }

    void setP1A200sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A200sReceptacles = list;
    }

    void setP1A300sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A300sReceptacles = list;
    }

    void setP1A400sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A400sReceptacles = list;
    }

    void setP1A500sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A500sReceptacles = list;
    }

    void setP1A600sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A600sReceptacles = list;
    }

    void setP1A700sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A700sReceptacles = list;
    }

    void setP1A800sReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1A800sReceptacles = list;
    }

    ArrayList<ArrayList<InventoryReceptacle>> getP1AReceptaclesIndexedByRows() {
        return this.p1AReceptaclesIndexedByRows;
    }

    void setP1AReceptaclesIndexed(ArrayList<ArrayList<InventoryReceptacle>> list) {
        this.p1AReceptaclesIndexedByRows = list;
    }

    ArrayList<InventoryReceptacle> getP1AReceptacles() {
        return this.p1AReceptacles;
    }

    void setP1AReceptacles(ArrayList<InventoryReceptacle> list) {
        this.p1AReceptacles = list;
    }

    void setPendingSBCP1A(ArrayList<InventoryReceptacle> list) {
        this.pendingSBCP1A = list;
    }

    void addToNumOfAndonsP1A() {
        this.numOfAndonsP1A++;
    }

    int getPendingSBCP1A_List_Size(){
        return this.pendingSBCP1A.size();
    }

    ArrayList<String> getPendingSBCP1ALocations() {

        FCTools.orderReceptaclesByPickID(this.pendingSBCP1A);

        ArrayList<String> list = new ArrayList<>(80_000);

        for (int i = 0; i < this.pendingSBCP1A.size(); i++) {
             String location = this.pendingSBCP1A.get(i).getLocation();
             list.add(location);
        }

        list.trimToSize();

        return list;
    }

    void removeReceptacleFromPendingSBCP1A(String location) {
         this.pendingSBCP1A.remove(this.getInventoryReceptacle(location));
    }

    int getNumberOfPendingCycleCountsP1A() {

        int numOfCycleCountsP1A = 0;

        for (int i = 0; i < this.p1AReceptacles.size(); i++) {
            if (this.p1AReceptacles.get(i).getNeedsCycleCount()) {
                numOfCycleCountsP1A += 1;
            }
        }
        return numOfCycleCountsP1A;
    }

    void addToPendingAndonsP1A(Andon andon) {
         this.pendingAndonsP1A.add(andon);
    }

    int getNumOfAndonsP1A() {
        return this.numOfAndonsP1A;
    }

    ArrayList<String> getPendingAndonsP1ALocations() {

        ArrayList<String> list = new ArrayList<>(this.numOfAndonsP1A);

        for (int i = 0; i < this.pendingAndonsP1A.size(); i++) {
            String location = this.pendingAndonsP1A.get(i).getLocation();
            list.add(location);
        }
        return list;
    }
}

// DESIGN NOTES AND IDEAS:
//
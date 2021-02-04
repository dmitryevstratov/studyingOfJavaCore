package src.tasks.harbor;

interface ShipActions {

    void enterToHarbor();
    void goOutFromHarbor();
    void goToPier();
    void setStartCargo(Cargo cargo, int count);

}

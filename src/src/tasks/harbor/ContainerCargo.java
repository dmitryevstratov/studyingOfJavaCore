package src.tasks.harbor;

class ContainerCargo implements Cargo{

    private final int weight;

    @Override
    public int getWeightCargo() {
        return weight;
    }

    ContainerCargo(int weight) {
        this.weight = weight;
    }

}

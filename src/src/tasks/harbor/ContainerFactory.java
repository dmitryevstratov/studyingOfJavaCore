package src.tasks.harbor;

public class ContainerFactory extends CargoFactory{

    @Override
    Cargo createCargo(int weight) {
        return new ContainerCargo(weight);
    }
}

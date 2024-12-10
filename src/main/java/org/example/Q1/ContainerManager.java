package Q1;

import java.util.ArrayList;
import java.util.List;

public class ContainerManager {
    private List<IMeasurableContainer> containers = new ArrayList<>();

    public ContainerManager(List<IMeasurableContainer> container) {
        this.containers = container;
    }

    public ContainerManager() {}

    public void add(IMeasurableContainer container) {
        containers.add(container);
    }

    public double totalWeight() {
        double totalWeight = 0;
        for (IMeasurableContainer shape : containers) {
            totalWeight += shape.weight();
        }
        return totalWeight;
    }

    public double totalRectangularVolume(){
        double totalVolume = 0;
        for (IMeasurableContainer shape : containers) {
            totalVolume += shape.rectangularVolume();
        }
        return totalVolume;
    }

    public void clearAll() {
        containers.clear();
    }

    public List<IMeasurableContainer> getAllContainers() {
        return containers;
    }

}

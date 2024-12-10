package Q1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContainerManager manager = new ContainerManager();

        Box box = new Box(2, 4, 8, 6);
        Cylinder cylinder = new Cylinder(4, 7 ,6);
        Pyramid pyramid = new Pyramid(3,3,5);

        manager.add(box);
        manager.add(cylinder);
        manager.add(pyramid);

        System.out.println("Total weight: " + manager.totalWeight());
        System.out.println("Total rectangular volume: " + manager.totalRectangularVolume());

        List<IMeasurableContainer> containers = manager.getAllContainers();
        for (IMeasurableContainer container : containers) {
            if (container instanceof Box boxContainer) {
                System.out.println("\nBox:\nWeight: " + boxContainer.getWeight() + "\nWidth: "+boxContainer.getWidth()
                + "\nDepth: "+boxContainer.getDepth() + "\nLength: "+boxContainer.getLength());
            }
            if (container instanceof Cylinder cylinderContainer) {
                System.out.println("\nCylinder:\nDiameter: " + cylinderContainer.getDiameter() + "\nHeight: "
                        + cylinderContainer.getHeight() + "\nWidth: "+cylinderContainer.getWeight());
            }
            if (container instanceof Pyramid pyramidContainer) {
                System.out.println("\nPyramid:\nLength: " + pyramidContainer.getLength() + "\nSide Length: "+pyramidContainer.getSideLength()
                + "\nWeight: "+pyramidContainer.getWeight());
            }
        }
    }
}
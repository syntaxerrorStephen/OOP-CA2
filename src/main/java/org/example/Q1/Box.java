package Q1;

public class Box implements IMeasurableContainer{
    private double width;
    private double weight;
    private double depth;
    private double length;

    public Box(double width, double weight, double depth, double length) {
        this.width = width;
        this.weight = weight;
        this.depth = depth;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }
    
    public double getWeight() {
        return weight;
    }

    public double getDepth() {
        return depth;
    }

    public double getLength() {
        return length;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public double rectangularVolume() {
        return width * length * depth;
    }

}

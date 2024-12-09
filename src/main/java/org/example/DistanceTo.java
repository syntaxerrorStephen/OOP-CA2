package org.example;

public class DistanceTo implements Comparable<DistanceTo> {
    // Declaring variables
    private String target;
    private int distance;

    // Constructor
    public DistanceTo(String city, int dist) {
        target = city;
        distance = dist;
    }

    // Getters
    public String getTarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }

    // Function comparing objects by distance. If they are equal - then by city name
    @Override
    public int compareTo(DistanceTo other) {
        if (this.distance != other.distance) {
            return this.distance - other.distance;
        }

        return this.target.compareTo(other.target);
    }

    // Function to check equality of two objects (distance and target are the same)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        //if (obj == null || getClass() != obj.getClass()) return false;

        DistanceTo other = (DistanceTo) obj;
        return distance == other.distance && target.equals(other.target);
    }

    // Function to calculate the hash code of object
    @Override
    public int hashCode() {
        return 31 * Integer.hashCode(distance) + target.hashCode();
    }
}

package org.example;

// Importing libraries
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.PriorityQueue;

public class Q11 {
    // Main function
    public static void main(String[] args) {
        // Declaring starting city, and map of routes
        String startCity = "Pendleton";
        Map<String, TreeSet<DistanceTo>> cityMap = new HashMap<>();

        // Populating the map with routes
        addRoute(cityMap, "Pendleton", "Pierre", 8);
        addRoute(cityMap, "Pendleton", "Pueblo", 4);
        addRoute(cityMap, "Pierre", "Pueblo", 3);
        addRoute(cityMap, "Pierre", "Peoria", 5);
        addRoute(cityMap, "Pueblo", "Phoenix", 3);
        addRoute(cityMap, "Peoria", "Pensacola", 5);
        addRoute(cityMap, "Peoria", "Pittsburgh", 5);
        addRoute(cityMap, "Pittsburgh", "Princeton", 2);
        addRoute(cityMap, "Phoenix", "Pensacola", 10);

        // Finding the shortest paths from the starting city
        Map<String, Integer> shortestDistances = findShortestPaths(cityMap, startCity);

        // Printing the shortest paths
        System.out.println("Shortest distances from " + startCity + ":");
        for (Map.Entry<String, Integer> entry : shortestDistances.entrySet()) {
            System.out.println("To " + entry.getKey() + ": " + entry.getValue());
        }
    }

    // Function to add route to map
    private static void addRoute(Map<String, TreeSet<DistanceTo>> cityMap, String city1, String city2, int distance) {
        cityMap.putIfAbsent(city1, new TreeSet<>());
        cityMap.putIfAbsent(city2, new TreeSet<>());
        cityMap.get(city1).add(new DistanceTo(city2, distance));
        cityMap.get(city2).add(new DistanceTo(city1, distance));
    }

    // Function to find the shortest paths
    private static Map<String, Integer> findShortestPaths(Map<String, TreeSet<DistanceTo>> cityMap, String startCity) {
        // Declaring map and priorityqueue
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<DistanceTo> pq = new PriorityQueue<>();

        // Initializing distances
        for (String city : cityMap.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(startCity, 0);
        pq.add(new DistanceTo(startCity, 0));

        // Processing the cities
        while (!pq.isEmpty()) {
            // Getting data of city
            DistanceTo current = pq.poll();
            String currentCity = current.getTarget();
            int currentDistance = current.getDistance();

            // Skipping the loop if a better distance to current city has already been found earlier
            if (currentDistance > distances.get(currentCity)) continue;

            // Otherwise, checking all neighbouring cities
            for (DistanceTo neighbour : cityMap.get(currentCity)) {
                // Getting potential new distance to the neighbouring city
                int newDist = currentDistance + neighbour.getDistance();

                // Updating the map with the new shortest distance if the new distance is shorter than currently known distance to neighbour
                if (newDist < distances.get(neighbour.getTarget())) {
                    distances.put(neighbour.getTarget(), newDist);
                    pq.add(new DistanceTo(neighbour.getTarget(), newDist));
                }
            }
        }

        // Returning paths and finishing function
        return distances;
    }
}

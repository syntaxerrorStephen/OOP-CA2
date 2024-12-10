package Q5;

import java.io.File;
import java.util.*;

public class IdentifierCount {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/example/test.java";

        //create a file object to work with the file
        File file = new File(filePath);

        //check if the file exists
        if(!file.exists()) {
            System.out.println("File not found");
            return;
        }

        //create a map to store the identifiers and their count
        Map<String, Integer> identifierCountMap = new TreeMap<>();

        //create a map to store the identifiers and the lines they appear
        Map<String, List<String>> identifierLinesMap = new TreeMap<>();


        try {
            Scanner scannerFile = new Scanner(file);
            int lineNumber = 0;

            //read the file line by line
            while(scannerFile.hasNextLine()) {
                lineNumber++;
                String line = scannerFile.nextLine();

                //split the line into words using regular expression
                String[] words = line.split("[^A-Za-z0-9_]+");

                //iterate through the words
                for(String word : words) {

                    //skip empty words
                    if(word.length() == 0) {
                        continue;
                    }

                    //skip words that start with a digit
                    if(Character.isDigit(word.charAt(0))) {
                        continue;
                    }

                    //check if the word is already in the map
                    if(identifierCountMap.containsKey(word)) {
                        identifierCountMap.put(word, identifierCountMap.get(word) + 1);
                        //add the line to the list of lines
                        identifierLinesMap.get(word).add(lineNumber + ". " + line);
                    } else {
                        //if the word is not in the map, add it
                        identifierCountMap.put(word, 1);
                        List<String> lines = new ArrayList<>();
                        //add the line to the list of lines
                        lines.add(lineNumber + ". " + line);
                        identifierLinesMap.put(word, lines);
                    }
                }
            }
            scannerFile.close();

            //output the results
            for(String key : identifierCountMap.keySet()) {
                //print the identifier and its count
                System.out.println(key + " " + identifierCountMap.get(key));
                //print the lines where the identifier appears
                for(String line : identifierLinesMap.get(key)) {
                    System.out.println(line);
                }
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
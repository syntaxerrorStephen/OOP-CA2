package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Q3 {
    public static boolean validate(String filename) throws FileNotFoundException {
        // Declaring variables and file reader that will go through the file
        boolean valid = true;
        String code = "";
        ArrayList<String> openingTags = new ArrayList<>();
        Scanner fileReader = new Scanner(new File(filename));

        // If the file is empty - everything is correct with the code and there is no need to check for validation
        try {
            code = fileReader.nextLine();
        }
        catch (NoSuchElementException e) {
            return true;
        }

        // Storing every tag
        String[] tags = code.split(" ");

        // Going through array of all tags ignoring all <br> tags
        for(String tag : tags) {
            // If tag is opening tag - adding it to ArrayList with opening tags
            if(!tag.equals("<br>") && tag.charAt(1) != '/') {
                openingTags.add(tag);
            }

            // If tag is closing tag = checking if it is equal with the opening tag that needs to be closed first
            if(!tag.equals("<br>") && tag.charAt(1) == '/') {
                String closingTag = tag.substring(0, 1) + tag.substring(2);

                // If it is equal, the document continues to be valid and removing last opening tag
                if(openingTags.get(openingTags.size() - 1).equals(closingTag)) {
                    openingTags.remove(openingTags.size() - 1);
                }
                // If it is not equal, the document is invalid and there is no need to check remaining tags
                else {
                    valid = false;
                    break;
                }
            }
        }

        // Closing file reader and returning document's state
        fileReader.close();
        return valid;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};

        for(String fileName: files) {
            System.out.print(fileName +": ");
            if (validate(fileName)) {
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }
        }
    }
}

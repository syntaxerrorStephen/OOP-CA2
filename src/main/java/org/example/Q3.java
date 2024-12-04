package org.example;

// Importing libraries
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Q3 {
    public static boolean validate(String filename) throws FileNotFoundException {
        // Declaring variables
        boolean valid = true;
        String code;
        Deque<String> stack = new ArrayDeque<>();

        // If the file is empty - everything is correct with the code and there is no need to check for validation
        try {
            code = new Scanner(new File(filename)).nextLine();
        }
        catch (NoSuchElementException e) {
            return true;
        }

        // Storing every tag
        String[] tags = code.split(" ");

        // Going through array of all tags ignoring all <br> tags
        for(String tag : tags) {
            // If tag is opening tag - adding it to ArrayList with opening tags
            if(!tag.equals("<br>") && tag.charAt(1) != '/') stack.push(tag);

            // If tag is closing tag - checking if it is equal with the opening tag that needs to be closed first
            if(!tag.equals("<br>") && tag.charAt(1) == '/') {
                String closingTag = tag.charAt(0) + tag.substring(2);

                // If it is equal, the document continues to be valid and removing last opening tag
                if(stack.getFirst().equals(closingTag)) stack.pop();

                // If it is not equal, the document is invalid and there is no need to check remaining tags
                else {
                    valid = false;
                    break;
                }
            }
        }

        // Returning document's state
        return valid;
    }

    // Main method to test sample files with code
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

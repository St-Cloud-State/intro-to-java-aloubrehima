import java.io.*;

public class MyMain {
    public static void main(String[] args) {
        PersonList people = new PersonList();

        try (FileInputStream fis = new FileInputStream("persons.txt")) {
            people.store(fis);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return; 
        }

        System.out.println("All persons (Q3):");
        people.display(System.out);

        String[] testIds = {"001", "002", "500"};
        for (String id : testIds) {
            int idx = people.find(id);
            if (idx != -1) {
                System.out.println("Found person with id " + id +
                        " at index " + idx + ": " + people.get(idx));
            } else {
                System.out.println("Person with id " + id + " not found.");
            }
        }
    }
}

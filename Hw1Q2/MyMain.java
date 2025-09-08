import java.util.*;
import java.io.*;

public class MyMain {
    public static void store(InputStream in, LinkedList<Person> list) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        Person p = new Person(parts[0].trim(), parts[1].trim(), parts[2].trim());
                        list.add(p);
                    }
                }
            }
        }
    }

    public static void display(PrintStream out, LinkedList<Person> list) {
        for (Person p : list) {
            out.println(p);
        }
    }

    public static int find(String sid, LinkedList<Person> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(sid)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LinkedList<Person> people = new LinkedList<>();

        try (FileInputStream fis = new FileInputStream("persons.txt")) {
            store(fis, people);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("All persons:");
        display(System.out, people);

        String[] testIds = {"001", "002", "500"};
        for (String id : testIds) {
            int idx = find(id, people);
            if (idx != -1) {
                System.out.println("Found person with id " + id + " at index " + idx + ": " + people.get(idx));
            } else {
                System.out.println("Person with id " + id + " not found.");
            }
        }
    }
}

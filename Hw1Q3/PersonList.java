import java.io.*;
import java.util.*;

public class PersonList {
    private LinkedList<Person> list;

    public PersonList() {
        this.list = new LinkedList<>();
    }

    // Read persons from an InputStream (CSV format) and add to internal list
    public void store(InputStream in) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // skip empty lines
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Person p = new Person(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    list.add(p);
                }
            }
        }
    }

    // Display all persons to a PrintStream (e.g., System.out)
    public void display(PrintStream out) {
        for (Person p : list) {
            out.println(p);
        }
    }

    // Return index of person with matching id, or -1 if not found
    public int find(String sid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(sid)) {
                return i;
            }
        }
        return -1;
    }

    // Utility: number of persons in the list
    public int size() {
        return list.size();
    }

    // Utility: get person at specific index
    public Person get(int idx) {
        return list.get(idx);
    }
}

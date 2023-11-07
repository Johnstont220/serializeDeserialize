import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialize {
    public static void main(String[] args) {
        // Serialize objects to a CSV file
        List<nflPlayers> yourObjectList = new ArrayList<>();
        yourObjectList.add(new nflPlayers("Michael Thomas", 30, 6.3));
        yourObjectList.add(new nflPlayers("Taysom Hill", 33, 6.2));

        String csvFileName = "data.csv";

        try (FileWriter fileWriter = new FileWriter(csvFileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            // Write the CSV header
            writer.write("Name,Age,Height\n");

            // Write each object as a CSV line
            for (nflPlayers object : yourObjectList) {
                writer.write(object.getName() + "," + object.getAge() + "," + object.getHeight() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize objects from the CSV file
        List<nflPlayers> deserializedObjects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName)) ) {
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    double height = Double.parseDouble(parts[2]);
                    deserializedObjects.add(new nflPlayers(name, age, height));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the deserialized objects
        for (nflPlayers object : deserializedObjects) {
            System.out.println(object);
        }
    }

    // Define object here
    static class nflPlayers {
        private String name;
        private int age;
        private double height;

        public nflPlayers(String name, int age, double height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Age: " + age + ", Height: " + height;
        }
    }
}
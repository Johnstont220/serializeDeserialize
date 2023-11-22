import java.io.*;
import java.nio.file.*;
import java.util.Objects;

class NFLPlayer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int yearOfBirth;
    private double salary;

    // Constructors
    public NFLPlayer() {
    }

    public NFLPlayer(String name, int yearOfBirth, double salary) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.salary = salary;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Serialization method using java.nio.file
    static void serializeToCSV(String filename, NFLPlayer... players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            oos.writeObject(players);
            System.out.println("Serialization successful. Data written to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialization method using java.nio.file
    static NFLPlayer[] deserializeFromCSV(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            return (NFLPlayer[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NFLPlayer other = (NFLPlayer) obj;
        return yearOfBirth == other.yearOfBirth &&
                Double.compare(other.salary, salary) == 0 &&
                Objects.equals(name, other.name);
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfBirth, salary);
    }
}
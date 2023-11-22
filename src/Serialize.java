
public class Serialize {
    public static void main(String[] args) {
        // Create NFLPlayer objects
        NFLPlayer player1 = new NFLPlayer("Taysom Hill", 1990, 10340000);
        NFLPlayer player2 = new NFLPlayer("Alvin Kamara", 1995, 30000000);

        // Serialize to CSV
        NFLPlayer.serializeToCSV("players.csv", player1, player2);

        // Deserialize from CSV
        NFLPlayer[] deserializedPlayers = NFLPlayer.deserializeFromCSV("players.csv");

        // Check if deserialized object is equal to the original object
        System.out.println("Player1 and DeserializedPlayer are equal: " + player1.equals(deserializedPlayers[0]));
        System.out.println("Player2 and DeserializedPlayer are equal: " + player2.equals(deserializedPlayers[1]));
    }
}
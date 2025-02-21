import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private static final String[] words = {
        "java", "programming", "hangman", "computer", "algorithm", 
        "variable", "developer", "syntax", "debugging", "compiler",
        "exception", "recursion", "inheritance", "function", "interface",
        "database", "encryption", "network", "hardware", "software",
        "cybersecurity", "framework", "library", "repository", "iteration",
        "python", "javascript", "machinelearning", "artificialintelligence", "datascience",
        "cloudcomputing", "virtualization", "authentication", "authorization", "encryption",
        "blockchain", "internet", "debugger", "compiler", "processor",
        "keyboard", "monitor", "storage", "parallel", "performance"
    };
    private static String selectedWord;
    private static char[] guessedWord;
    private static final int maxAttempts = 6;
    private static int attemptsLeft = maxAttempts;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        selectWord();

        while (true) {
            System.out.println("\nWord: " + String.valueOf(guessedWord));
            System.out.println("Attempts left: " + attemptsLeft);
            drawHangman(); // Draw the Hangman as attempts decrease
            System.out.print("Enter a letter or the entire word: ");
            String guess = scanner.nextLine().toLowerCase();

            if (guess.length() == 1) {
                processLetterGuess(guess.charAt(0));
            } else if (guess.equals(selectedWord)) {
                guessedWord = selectedWord.toCharArray();
            } else {
                System.out.println("❌ Incorrect word guess!");
                attemptsLeft--;
            }

            if (String.valueOf(guessedWord).equals(selectedWord)) {
                System.out.println("🎉 Congratulations! You've guessed the word: " + selectedWord);
                break;
            } else if (attemptsLeft == 0) {
                drawHangman(); // Show the final Hangman
                System.out.println("❌ You've run out of attempts. The word was: " + selectedWord);
                break;
            }
        }
        scanner.close();
    }

    private static void selectWord() {
        Random random = new Random();
        selectedWord = words[random.nextInt(words.length)];
        guessedWord = new char[selectedWord.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
    }

    private static void processLetterGuess(char letter) {
        boolean found = false;
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == letter) {
                guessedWord[i] = letter;
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Letter not found!");
            attemptsLeft--;
        }
    }

    private static void drawHangman() {
        int stage = maxAttempts - attemptsLeft;
        String[] hangmanStages = {
            """
               +---+
               |   |
                   |
                   |
                   |
                   |
              ========
            """,
            """
               +---+
               |   |
               O   |
                   |
                   |
                   |
              ========
            """,
            """
               +---+
               |   |
               O   |
               |   |
                   |
                   |
              ========
            """,
            """
               +---+
               |   |
               O   |
              /|   |
                   |
                   |
              ========
            """,
            """
               +---+
               |   |
               O   |
              /|\\  |
                   |
                   |
              ========
            """,
            """
               +---+
               |   |
               O   |
              /|\\  |
              /    |
                   |
              ========
            """,
            """
               +---+
               |   |
               O   |
              /|\\  |
              / \\  |
                   |
              ========
            """
        };
        System.out.println(hangmanStages[stage]);
    }
}

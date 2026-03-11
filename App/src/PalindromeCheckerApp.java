import java.util.Scanner;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

/*
 * Strategy Interface
 * This defines the common method that all palindrome algorithms must implement.
 * It allows different algorithms to be used interchangeably.
 */
interface PalindromeStrategy {
    boolean checkPalindrome(String input);
}

/*
 * StackStrategy
 * This class implements the palindrome check using the Stack data structure.
 * Stack follows LIFO (Last In First Out) principle.
 */
class StackStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {

        // Normalize input: remove spaces and convert to lowercase
        String normalized = input.replaceAll("\\s+", "").toLowerCase();

        Stack<Character> stack = new Stack<>();

        // Push all characters into the stack
        for (char c : normalized.toCharArray()) {
            stack.push(c);
        }

        // Compare characters with popped elements
        for (char c : normalized.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}

/*
 * DequeStrategy
 * This class implements palindrome checking using Deque.
 * Deque allows insertion/removal from both ends.
 */
class DequeStrategy implements PalindromeStrategy {

    public boolean checkPalindrome(String input) {

        // Normalize input
        String normalized = input.replaceAll("\\s+", "").toLowerCase();

        Deque<Character> deque = new ArrayDeque<>();

        // Add characters to the deque
        for (char c : normalized.toCharArray()) {
            deque.addLast(c);
        }

        // Compare characters from front and rear
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }

        return true;
    }
}

/*
 * PalindromeChecker Service Class
 * This class uses the Strategy Pattern.
 * The algorithm used can be changed dynamically.
 */
class PalindromeChecker {

    private PalindromeStrategy strategy;

    // Constructor injection of strategy
    public PalindromeChecker(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    // Method that delegates the palindrome check to the selected strategy
    public boolean check(String input) {
        return strategy.checkPalindrome(input);
    }
}

/*
 * Main Application Class
 * Allows user to select which palindrome algorithm to use.
 */
public class UseCase12PalindromeCheckerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("Choose Algorithm:");
        System.out.println("1. Stack Strategy");
        System.out.println("2. Deque Strategy");

        int choice = scanner.nextInt();

        PalindromeStrategy strategy;

        // Select strategy dynamically
        if (choice == 1) {
            strategy = new StackStrategy();
        } else {
            strategy = new DequeStrategy();
        }

        PalindromeChecker checker = new PalindromeChecker(strategy);

        // Perform palindrome check
        if (checker.check(input)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not a Palindrome");
        }

        scanner.close();
    }
}
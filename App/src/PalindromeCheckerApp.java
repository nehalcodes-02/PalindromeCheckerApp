import java.util.Scanner;
import java.util.Stack;

public class UseCase13PalindromeCheckerApp {

    // Stack-based palindrome check
    public static boolean stackPalindrome(String input) {

        String normalized = input.replaceAll("\\s+", "").toLowerCase();
        Stack<Character> stack = new Stack<>();

        for (char c : normalized.toCharArray()) {
            stack.push(c);
        }

        for (char c : normalized.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    // Two-pointer palindrome check
    public static boolean twoPointerPalindrome(String input) {

        String normalized = input.replaceAll("\\s+", "").toLowerCase();

        int start = 0;
        int end = normalized.length() - 1;

        while (start < end) {
            if (normalized.charAt(start) != normalized.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Measure stack algorithm time
        long startStack = System.nanoTime();
        boolean stackResult = stackPalindrome(input);
        long endStack = System.nanoTime();

        // Measure two-pointer algorithm time
        long startPointer = System.nanoTime();
        boolean pointerResult = twoPointerPalindrome(input);
        long endPointer = System.nanoTime();

        System.out.println("\nStack Method Result: " + stackResult);
        System.out.println("Stack Execution Time: " + (endStack - startStack) + " ns");

        System.out.println("\nTwo Pointer Method Result: " + pointerResult);
        System.out.println("Two Pointer Execution Time: " + (endPointer - startPointer) + " ns");

        scanner.close();
    }
}
package lt.techin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Yes or no?");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase("Yes")) {
                continue;
            } else if (userAnswer.equalsIgnoreCase("No")) {
                break;
            }
        }
    }
}

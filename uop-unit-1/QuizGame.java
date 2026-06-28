import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {

        // Section 1
        Scanner scanner = new Scanner(System.in);
        int totalQuestions = 5;
        int correctAnswers = 0;

        // Section 2
        String[] questions = {
                "What is the correct syntax to print text in Java?",
                "Which data type is used to store a single character in Java?",
                "Which keyword is used to declare a constant in Java?",
                "What is the result of 10 % 3 in Java?",
                "Which of the following is a valid Java variable name?"
        };

        String[][] options = {
                { "A. print(\"Hello\");", "B. System.out.println(\"Hello\");",
                        "C. echo \"Hello\";", "D. console.log(\"Hello\");" },
                { "A. String", "B. int",
                        "C. char", "D. byte" },
                { "A. const", "B. constant",
                        "C. static", "D. final" },
                { "A. 3", "B. 1",
                        "C. 0", "D. 2" },
                { "A. 2variable", "B. variable-name",
                        "C. variableName", "D. variable name" }
        };

        char[] correctAnswerKeys = { 'B', 'C', 'D', 'B', 'C' };

        // Section 3
        System.out.println("==========================================");
        System.out.println("       Welcome to the Java Quiz Game!     ");
        System.out.println("==========================================");
        System.out.println("Answer each question by typing A, B, C, or D.");
        System.out.println();

        // Section 4
        for (int i = 0; i < totalQuestions; i++) {

            // Print the question number and question
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            for (String option : options[i]) {
                System.out.println(" " + option);
            }

            char userAnswer = ' ';
            boolean isValidInput = false;

            while (!isValidInput) {
                System.out.print("Your answer: ");
                String inputLine = scanner.nextLine().trim().toUpperCase();

                if (inputLine.length() == 1) {
                    userAnswer = inputLine.charAt(0);
                    if (userAnswer == 'A' || userAnswer == 'B' || userAnswer == 'C' || userAnswer == 'D') {
                        isValidInput = true;
                    } else {
                        System.out.println("Please enter a valid option (A, B, C, or D).");
                    }
                } else {
                    System.out.println("Please enter a valid option (A, B, C, or D).");
                }

                char correctKey = correctAnswerKeys[i];
                boolean isCorrect = false;

                switch (userAnswer) {
                    case 'A':
                        isCorrect = (correctKey == 'A');
                        break;
                    case 'B':
                        isCorrect = (correctKey == 'B');
                        break;
                    case 'C':
                        isCorrect = (correctKey == 'C');
                        break;
                    case 'D':
                        isCorrect = (correctKey == 'D');
                        break;

                    default:
                        break;
                }

                if (isCorrect) {
                    System.out.println("Correct!");
                    correctAnswers++;
                } else {
                    System.out.println("Incorrect! The correct answer is " + correctKey + ".");
                }

                System.out.println();
            }

            double scorePercentage = ((double) correctAnswers / totalQuestions) * 100;

            System.out.println("==========================================");
            System.out.println("              Quiz Complete!              ");
            System.out.println("==========================================");
            System.out.println("Questions answered : " + totalQuestions);
            System.out.println("Correct answers    : " + correctAnswers);
            System.out.println("Your final score   : " + (int) scorePercentage + "%");

            // Provide a grade remark using if-else
            if (scorePercentage == 100) {
                System.out.println("Result: Excellent! Perfect score!");
            } else if (scorePercentage >= 80) {
                System.out.println("Result: Great job! Well done.");
            } else if (scorePercentage >= 60) {
                System.out.println("Result: Good effort. Keep practicing!");
            } else {
                System.out.println("Result: Keep studying. You can do better!");
            }

            System.out.println("==========================================");

        }
        // -------------------------------------------------------
        // SECTION 6: Close the scanner to free resources
        // -------------------------------------------------------
        scanner.close();
    }
}

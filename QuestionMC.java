import java.util.List;

public class QuestionMC extends Question {
    private List<String> choices;
    private String correctAnswer;

    public QuestionMC(String questionText, int pointValue, List<String> choices, String correctAnswer) {
        super(questionText, pointValue);
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean askQuestion(java.util.Scanner scanner) {
        System.out.println("Points: " + pointValue);
        System.out.println("Question: " + questionText);

        char option = 'A';
        for (String choice : choices) {
            System.out.println(option + ") " + choice);
            option++;
        }

        String answer = scanner.nextLine().trim();

        if (answer.equalsIgnoreCase("SKIP")) {
            System.out.println("You have elected to skip that question.\n");
            skipped = true;
            return false;
        }

        if (answer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct! You get " + pointValue + " points.\n");
            return true;
        } else {
            System.out.println("Incorrect, the answer was " + correctAnswer + ". You lose " + pointValue + " points.\n");
            return false;
        }
    }
}

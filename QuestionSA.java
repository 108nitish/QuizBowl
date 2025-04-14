public class QuestionSA extends Question {
    private String correctAnswer;

    public QuestionSA(String questionText, int pointValue, String correctAnswer) {
        super(questionText, pointValue);
        this.correctAnswer = correctAnswer.toLowerCase();
    }

    @Override
    public boolean askQuestion(java.util.Scanner scanner) {
        System.out.println("Points: " + pointValue);
        System.out.println("Question: " + questionText);
        String answer = scanner.nextLine().trim();

        if (answer.equalsIgnoreCase("SKIP")) {
            System.out.println("You have elected to skip that question.\n");
            skipped =true; 
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

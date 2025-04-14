public abstract class Question {
    protected String questionText;
    protected int pointValue;

    public Question(String questionText, int pointValue) {
        this.questionText = questionText;
        this.pointValue = pointValue;
    }

    protected boolean skipped = false;

    public boolean wasSkipped() {
        return skipped;
    }

    public int getPointValue() {
        return pointValue;
    }

    public abstract boolean askQuestion(java.util.Scanner scanner);
}

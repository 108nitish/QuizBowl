public class Player {
    private String firstName;
    private String lastName;
    private int score;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = 0;
    }

    public void addPoints(int points) {
        this.score += points;
    }

    public void subtractPoints(int points) {
        this.score -= points;
    }

    public int getScore() {
        return score;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class QuizBowl {
    private Player player;
    private List<Question> questions = new ArrayList<>();

    public static void main(String[] args) {
        new QuizBowl().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your first name?");
        String firstName = scanner.nextLine().trim();

        System.out.println("What is your last name?");
        String lastName = scanner.nextLine().trim();

        player = new Player(firstName, lastName);
        String filename = "quiz_questions.json";

        try {
            readQuestionsFromFile(filename);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        int numQuestions = askHowManyQuestions(scanner);

        Collections.shuffle(questions);
        for (int i = 0; i < numQuestions; i++) {
            Question q = questions.get(i);
            boolean correct = q.askQuestion(scanner); // show question + take input
        
            if (q.wasSkipped()) {
                System.out.println("You skipped this question. No points added or removed.");
            } else if (correct) {
                player.addPoints(q.getPointValue());
            } else {
                player.subtractPoints(q.getPointValue());
            }
        }
        
        

        System.out.println(player.getFullName() + ", your game is over!");
        System.out.println("Your final score is " + player.getScore() + " points.");
    }

    private int askHowManyQuestions(Scanner scanner) {
        int num = 0;
        while (true) {
            System.out.println("How many questions would you like (out of " + questions.size() + ")?");
            String input = scanner.nextLine().trim();
            try {
                num = Integer.parseInt(input);
                if (num > 0 && num <= questions.size()) break;
                else System.out.println("Sorry, that is too many.");
            } catch (NumberFormatException e) {
                System.out.println("Sorry, that is not valid.");
            }
        }
        return num;
    }

    private void readQuestionsFromFile(String filename) throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader(filename);

        // Parse the outer object
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        JsonArray questionsArray = json.getAsJsonArray("questions");

        for (JsonElement element : questionsArray) {
            JsonObject obj = element.getAsJsonObject();

            String type = obj.get("type").getAsString();
            int points = obj.get("points").getAsInt();
            String questionText = obj.get("question").getAsString();
            String answer = obj.get("answer").getAsString();

            switch (type) {
                case "TF":
                    questions.add(new QuestionTF(questionText, points, answer));
                    break;
                case "SA":
                    questions.add(new QuestionSA(questionText, points, answer));
                    break;
                case "MC":
                    List<String> choices = new ArrayList<>();
                    JsonArray choicesArray = obj.getAsJsonArray("choices");
                    for (JsonElement choice : choicesArray) {
                        choices.add(choice.getAsString());
                    }
                    questions.add(new QuestionMC(questionText, points, choices, answer));
                    break;
                default:
                    System.out.println("Unknown question type: " + type);
            }
        }

        reader.close();
    }
}

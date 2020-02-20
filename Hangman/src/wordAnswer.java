import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class wordAnswer {

    private String word;
    private char[] wordArray;

    private Label[] charLabels;
    private GridPane letterPane = new GridPane();

    private int correctlyGuessedChars;

    public wordAnswer(String wordVal)
    {
        word = wordVal;
        correctlyGuessedChars = word.length();
        wordArray = word.toCharArray();
        charLabels = new Label[wordArray.length];

        for(int i = 0; i < wordArray.length; i++)
        {
            charLabels[i] = new Label("" + wordArray[i] + "");
            Label temp = charLabels[i];
            if(word.length() > 16)
            {
                temp.setFont(new Font("Arial", 12));
            }
            temp.setTextFill(Color.web("#FFFFFF"));
            temp.setVisible(false);
        }
    }

    public GridPane getWordGridPane()
    {
        letterPane = new GridPane();
        for(int i = 0; i < charLabels.length; i++)
        {
            StackPane tempStack = new StackPane();
            tempStack.getChildren().add(charLabels[i]);
            if(word.length() > 16)
                {tempStack.setPrefSize(25, 25); }
            else
                {tempStack.setPrefSize(30, 30); }
            tempStack.setStyle("-fx-background-color: #000000;");
            letterPane.add(tempStack, i, 0, 1, 1);
        }
        letterPane.setHgap(1);
        letterPane.setVgap(1);
        letterPane.setPadding(new Insets(0, 0, 50, 0));
        return letterPane;
    }

    public int testMove(char c)
    {
        int counter = 0;
        for(int i = 0; i < wordArray.length; i++)
        {
            if(wordArray[i] == c)
            {
                charLabels[i].setVisible(true);
                counter++;
                this.correctlyGuessedChars--;
            }
        }
        return counter;
    }

    public void revealAnswer()
    {
        for(int i = 0; i < charLabels.length; i++)
        {
            if(!(charLabels[i].isVisible()))
            {
                charLabels[i].setTextFill(Color.web("#000000"));
                charLabels[i].setVisible(true);
                StackPane tempStack = new StackPane();
                tempStack.getChildren().add(charLabels[i]);
                if(word.length() > 16)
                {tempStack.setPrefSize(25, 25); }
                else
                {tempStack.setPrefSize(30, 30); }
                tempStack.setStyle("-fx-background-color: #ff0066;");

                letterPane.add(tempStack, i, 0, 1, 1);
            }
        }
    }

    public String getWord()
    {
        return word;
    }
    public void setWord()
    {
        this.word = word;
    }

    public int getCorrectChars()
    {
        return correctlyGuessedChars;
    }

    public void setCorrectChars(int correctlyGuessedChars)
    {
        this.correctlyGuessedChars = correctlyGuessedChars;
    }
}

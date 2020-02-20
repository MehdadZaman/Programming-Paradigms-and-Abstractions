import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
public class letterClicks {
    private wordAnswer word;
    private int remGuesses;
    public HangmanDrawing drawing;
    public boolean gameOver;
    boolean needsSaving = false;
    public boolean[] clickedButtons = new boolean[26];
    public Button[] letterButtons;
    public letterClicks()
    {
        remGuesses = 10;
        word = new wordAnswer(RandomWordChooser.chooseWord("words.txt").toUpperCase());
        letterButtons = new Button[26];
        drawing = new HangmanDrawing();
        for(int i = 0; i < letterButtons.length; i++)
        {
            char letter = (char)('A' + i);
            letterButtons[i] = new Button("" + letter + "");
            Button temp = letterButtons[i];
            temp.setPrefSize(35, 35);
            temp.setStyle("-fx-background-color: #00C147;");
            temp.setOnAction(e -> {
                if(Hangman.getPlayableGameState() && !(getGameOver())) {
                    decrementMove(temp.getText(), temp);
                    Hangman.save.setDisable(false);
                    if(gameOver){Hangman.save.setDisable(true);}
                    Hangman.firstMoveMade = true;
                    needsSaving = true;
                    temp.setDisable(true);
                    Hangman.getScoreText().setText("Remaining guesses: " + getRemGuesses());
                    clickedButtons[temp.getText().charAt(0)- 'A'] = true;
                }
            }
            );
        }
    }
    public letterClicks(String s, boolean[] boolbuttons)
    {
        remGuesses = 10;
        word = new wordAnswer(s.toUpperCase());
        letterButtons = new Button[26];
        clickedButtons = boolbuttons;
        drawing = new HangmanDrawing();
        for(int i = 0; i < letterButtons.length; i++)
        {
            char letter = (char)('A' + i);
            letterButtons[i] = new Button("" + letter + "");
            Button temp = letterButtons[i];
            temp.setPrefSize(35, 35);
            temp.setStyle("-fx-background-color: #00C147;");
            temp.setOnAction(e -> {
                        if(Hangman.getPlayableGameState() && !(getGameOver())) {
                            decrementMove(temp.getText(), temp);
                            Hangman.save.setDisable(false);
                            if(gameOver){Hangman.save.setDisable(true);}
                            Hangman.firstMoveMade = true;
                            needsSaving = true;
                            temp.setDisable(true);
                            Hangman.getScoreText().setText("Remaining guesses: " + getRemGuesses());
                            clickedButtons[temp.getText().charAt(0)- 'A'] = true;
                        }
                    }
            );
        }
    }
    public void processGameFields()
    {
        for(int i = 0; i < clickedButtons.length; i++)
        {
            if(clickedButtons[i])
            {
                letterButtons[i].fire();
            }
        }
    }
    public void buttonKeyBoardPress(int val)
    {
        Button temp = letterButtons[val];
        decrementMove(temp.getText(), temp);
        temp.setDisable(true);
    }
    public GridPane getLetterGridPane()
    {
        GridPane letterPane = new GridPane();
        boolean breakLoop = false;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(i == 3 && j == 5)
                {
                    breakLoop = true;
                    break;
                }
                letterPane.add(letterButtons[(i*7)+j], j, i, 1, 1);
            }
            if(breakLoop)
            {
                break;
            }
        }
        letterPane.setHgap(1.5);
        letterPane.setVgap(1.5);
        return letterPane;
    }
    public GridPane getWordGridPane()
    {
        return word.getWordGridPane();
    }
    public void decrementMove(String s, Button b)
    {
        if(gameOver)
        {
            return;
        }
        if(b.isDisable())
        {
            return;
        }
        char val = s.charAt(0);
        int count = word.testMove(val);
        if(count == 0)
        {
            if(remGuesses > 0) {
                remGuesses--;
                drawing.addPiece(10 - remGuesses);
            }
        }
        if(remGuesses <= 0)
        {
            word.revealAnswer();
            //Hangman.scoreText.setText("Remaining guesses: " + getRemGuesses());
            Hangman.getScoreText().setText("Remaining guesses: " + getRemGuesses());
            b.setDisable(true);
            PopUp.display("Game Over", "You lost (the word was \"" + word.getWord() + "\")");
            gameOver = true;
            needsSaving = false;
            Hangman.firstMoveMade = false;
        }
        if(word.getCorrectChars() == 0)
        {
            Hangman.getScoreText().setText("Remaining guesses: " + getRemGuesses());
            //Hangman.scoreText.setText("Remaining guesses: " + getRemGuesses());
            b.setDisable(true);
            PopUp.display("Game Over", "You won.");
            gameOver = true;
            needsSaving = false;
            Hangman.firstMoveMade = false;
        }
    }
    public Group getDrawingPane()
    {
        return drawing.getDrawingPane();
    }
    public int getRemGuesses()
    {
        return remGuesses;
    }
    public void setRemGuesses(int remGuesses)
    {
        this.remGuesses = remGuesses;
    }
    public boolean getGameOver()
    {
        return gameOver;
    }
    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }
    public wordAnswer getWordAnswer()
    {
        return this.word;
    }
    public void setWordAnswer(wordAnswer wordans)
    {
        this.word = wordans;
    }
}

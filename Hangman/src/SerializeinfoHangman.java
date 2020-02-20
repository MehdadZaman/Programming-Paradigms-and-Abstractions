import java.io.Serializable;

public class SerializeinfoHangman implements Serializable {

    private String word;
    private int remGuesses;
    private boolean gameOver;

    public boolean[] clickedButtons = new boolean[26];

    public SerializeinfoHangman(String word, boolean[] clickedButtons, int remGuesses)
    {
        this.word = word;
        this.clickedButtons = clickedButtons;
        this.remGuesses = remGuesses;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public boolean[] getClickedButtons()
    {
        return this.clickedButtons;
    }

    public void setClickedButtons(boolean[] clickedButtons)
    {
        this.clickedButtons = clickedButtons;
    }

}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Hangman extends Application {
    public static letterClicks letterClass = new letterClicks();
    private static Label scoreText = new Label();
    public static Scene scene;
    VBox top;
    VBox left;
    VBox right;
    VBox bottom;
    BorderPane mainPane;
    HBox titleBox;
    public static Button save;
    public static Button load;
    public static Button anew;
    public static Button exit;
    public static Button startGame;
    public static boolean playableGameState = false;
    public static boolean firstMoveMade = false;
    public static Stage mainStage;

    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception {
            mainStage = primaryStage;
            primaryStage.setTitle("Hangman");
            primaryStage.setMaxWidth(1000);
            primaryStage.setMinWidth(1000);
            primaryStage.setMaxHeight(500);
            primaryStage.setMinHeight(500);
            mainPane = new BorderPane();
            scene = new Scene(mainPane, 1000, 500);
            top = addTop();
            mainPane.setTop(top);
            left = addLeft();
            mainPane.setLeft(left);
            right = addRight();
            mainPane.setRight(right);
            scene.addEventFilter(KeyEvent.KEY_PRESSED, k -> {
                if (k.getCode() == KeyCode.SPACE || k.getCode() == KeyCode.ENTER)
                {
                    k.consume();
                } });
                scene.setOnKeyPressed(e -> {
                    if(playableGameState && !(letterClass.getGameOver())) {
                        char inputLetter = e.getCode().toString().charAt(0);
                        int letterVal = inputLetter - 'A';
                        if (letterVal >= 0 && letterVal < 26)
                        {
                            letterClass.buttonKeyBoardPress(letterVal);
                            save.setDisable(false);
                            if(letterClass.getGameOver()){save.setDisable(true);}
                            Hangman.firstMoveMade = true;
                            letterClass.needsSaving = true;
                            letterClass.clickedButtons[letterVal] = true;
                            scoreText.setText("Remaining guesses: " + letterClass.getRemGuesses());
                        } } });
            mainPane.setBottom(addBottom());
            bottom.setVisible(false);
            left.setVisible(false);
            right.setVisible(false);
            titleBox.setVisible(false);
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    public void setScenePlay()
    {
        left.setVisible(true);
        right.setVisible(true);
        titleBox.setVisible(true);
    }
    public void setSceneStart()
    {
        bottom.setVisible(true);
    }
    public Button saveButton()
    {
        save = new Button();
        save.setPrefSize(60, 20);
        Image image = new Image("Save.png");
        save.setGraphic(new ImageView(image));
        save.setOnAction(e -> {
            try{
            if(letterClass.getGameOver()) save.setDisable(true);
            else {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Hangman Game");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Hangman files", "*.hng"));
                File selectedFile = fileChooser.showSaveDialog(mainStage);
                SerializeinfoHangman savedInfo = new SerializeinfoHangman(letterClass.getWordAnswer().getWord(),
                        letterClass.clickedButtons, letterClass.getRemGuesses());
                try {
                    FileOutputStream fos;
                    if(!(selectedFile.toString().endsWith(".hng"))) {
                        fos = new FileOutputStream(selectedFile.toString() + ".hng");
                    }
                    else
                        {
                            fos = new FileOutputStream(selectedFile.toString());
                        }
                    ObjectOutputStream fout = new ObjectOutputStream(fos);
                    fout.writeObject(savedInfo); fout.flush(); fout.close();
                    firstMoveMade = false;
                    save.setDisable(true);
                } catch (IOException ex) {} } }
        catch(Exception ex){}
        });
        save.setDisable(true); return save;

    }
    public Button loadButton()
    {
        load = new Button();
        load.setPrefSize(60, 20);
        Image image = new Image("Load.png");
        load.setGraphic(new ImageView(image));
        load.setOnAction(e -> {
            try {
                AtomicInteger ret = new AtomicInteger(-1);
                playableGameState = true;
                if (firstMoveMade && !(letterClass.gameOver))
                {
                    ret = PopUpWarningNewGame.display("Load New Game", "Do you want to save the game?");
                }
                if (ret.get() == -1)
                {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Load Hangman Game");
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("Hangman files", "*.hng"));
                    File selectedFile = fileChooser.showOpenDialog(mainStage);
                    try {
                        SerializeinfoHangman serialHangman;
                        File f = selectedFile;
                        if (f.exists())
                        {
                            FileInputStream fileinput = new FileInputStream(selectedFile);
                            ObjectInputStream inStream = new ObjectInputStream(fileinput);
                            serialHangman = (SerializeinfoHangman) inStream.readObject();
                            inStream.close(); fileinput.close();
                            this.letterClass = new letterClicks(serialHangman.getWord(), serialHangman.getClickedButtons());
                            this.letterClass.processGameFields();
                            playableGameState = true;
                            firstMoveMade = false;
                        }
                    }
                    catch (IOException ioe) {}
                    catch (Exception ex) { }
                    try
                    {
                        start(mainStage);
                    }
                    catch (Exception ex) {}
                    setScenePlay();
                    this.bottom.setVisible(true);
                    startGame.setDisable(true);
                }
            }
            catch(Exception ex){} });
        return load;
    }
    public Button anewButton()
    {
        anew = new Button();
        anew.setOnAction(e -> {
                    if(!playableGameState) {
                        this.setSceneStart();
                    }
                    else if(letterClass.gameOver)
                    {
                        letterClass = new letterClicks();
                        try
                        {
                            start(mainStage);
                        }
                        catch (Exception ex) { }
                        playableGameState = false;
                        Hangman.firstMoveMade = false;
                        anew.fire();
                        startGame.fire(); }
                    else if(firstMoveMade) {
                        AtomicInteger ret = PopUpWarningNewGame.display("Exit Game", "Do you want to save the game?");
                        if(ret.get() == -1) {
                            letterClass = new letterClicks();
                            try { start(mainStage); } catch (Exception ex) { }
                            playableGameState = false;
                            Hangman.firstMoveMade = false;
                            anew.fire(); startGame.fire(); } }
                    else { letterClass = new letterClicks();try {
                            start(mainStage);
                        } catch (Exception ex) {}
                        playableGameState = false;
                        Hangman.firstMoveMade = false;
                        anew.fire();
                        startGame.fire(); } });
        anew.setPrefSize(60, 20);
        Image image = new Image("New.png");
        anew.setGraphic(new ImageView(image));
        return anew; }
    public Button exitButton() {
        exit = new Button(); //("Exit");
        exit.setOnAction(e -> {
            if(firstMoveMade && !(letterClass.gameOver)) {
                PopUpWarningExit.display("Exit Game", "Do you want to save the game?"); }
            else {
                mainStage.close(); } });
        exit.setPrefSize(60, 20);
        Image image = new Image("Exit.png");
        exit.setGraphic(new ImageView(image));
        return exit; }
    public HBox addTopButtonHBox() {
        HBox topButtons = new HBox();
        topButtons.setPadding(new Insets(15, 12, 15, 12));
        topButtons.setStyle("-fx-background-color: #336699;");
        Button save = saveButton();
        Button load = loadButton();
        Button anew = anewButton();
        Button exit = exitButton();
        topButtons.getChildren().addAll(anew, load, save, exit);
        return topButtons; }
    public HBox addTopTitleLabel() {
        Label title = new Label("Hangman");
        title.setFont(new Font("Impact", 30));
        titleBox = new HBox();
        titleBox.setPadding(new Insets(2, 10, 2, 450));
        titleBox.setStyle("-fx-background-color: #89b5f0;");
        titleBox.getChildren().add(title);
        return titleBox; }
    public VBox addTop() {
        HBox buttonHBox = addTopButtonHBox();
        HBox titleHBox = addTopTitleLabel();
        VBox top = new VBox();
        top.getChildren().addAll(buttonHBox, titleHBox);
        return top; }
    public VBox addLeft() {
        Group drawing = letterClass.getDrawingPane();
        VBox left = new VBox();
        left.setPadding(new Insets(0, 65, 0, 165));//500
        left.setStyle("-fx-background-color: #89b5f0;");
        VBox drawingBox = new VBox();
        drawingBox.setPadding(new Insets(50, 0, 100, 0));//500
        drawingBox.getChildren().add(drawing);
        drawingBox.setStyle("-fx-background-color: #89b5f0;");
        left.getChildren().add(drawingBox);
        return left; }
    public VBox addRight() {
        VBox right = new VBox();
        right.setPadding(new Insets(0, 300, 10, 10));//500
        right.setStyle("-fx-background-color: #89b5f0;");
        scoreText.setText("Remaining guesses: " + letterClass.getRemGuesses());
        scoreText.setFont(new Font("Impact", 20));
        HBox score = new HBox();
        score.getChildren().add(scoreText);
        score.setPadding(new Insets(20, 8, 50, 100));
        GridPane letters =  letterClass.getLetterGridPane();
        GridPane word = letterClass.getWordGridPane();
        word.setAlignment(Pos.CENTER);
        word.setPadding(new Insets(0, 0, 20, 40));
        HBox displayWordMiddlePane = new HBox();
        displayWordMiddlePane.getChildren().add(word);
        displayWordMiddlePane.setAlignment(Pos.CENTER);
        StackPane.setAlignment(word,Pos.CENTER_RIGHT);
        StackPane displayMiddlePane = new StackPane();
        displayMiddlePane.getChildren().add(letters);
        displayMiddlePane.setPadding(new Insets(0, 0, 0, 100));//175
        right.getChildren().addAll(score ,displayWordMiddlePane, displayMiddlePane);
        return right; }
    public VBox addBottom() {
        startGame = new Button("Start Playing");
        startGame.setPrefSize(120, 10);
        startGame.setOnAction(e -> {
            playableGameState = true;
            this.setScenePlay();
            startGame.setDisable(true); });
        HBox bottomButtonPane = new HBox();
        bottomButtonPane.getChildren().add(startGame);
        bottom = new VBox();
        bottom.getChildren().add(bottomButtonPane);
        bottom.setPadding(new Insets(10, 80, 10, 430));
        bottom.setStyle("-fx-background-color: #D5DBDB;");
        return bottom; }
    public static Label getScoreText()
    {
        return scoreText;
    }
    public static boolean getPlayableGameState()
    {
        return playableGameState;
    }
    public static void setPlayableGameState(boolean playableGameState)
    {
        playableGameState = playableGameState;
    }
}
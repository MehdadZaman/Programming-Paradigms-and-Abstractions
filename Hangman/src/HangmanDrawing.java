import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class HangmanDrawing {
    private Group drawingPane;
    private Shape[] shapes;
    public HangmanDrawing()
    {
        drawingPane = new Group();
        drawHangMan();
        drawingPane.setRotate(180);
    }
    public void addPiece(int guess)
    {
       if(guess <= 10 && guess > 0)
       {
           shapes[guess - 1].setStroke(Color.BLACK);
       }
    }


    public void drawHangMan()
    {
        shapes = new Shape[10];
        shapes[0] = new Line(50, 0, 250, 0);
        shapes[1] = new Line(250, 0, 250, 200);
        shapes[2] = new Line(250, 200, 125, 200);
        shapes[3] = new Line(125, 200, 125, 175);
        shapes[4] = new Circle(125, 150, 25, Color.TRANSPARENT);
        shapes[5] = new Line(125, 120, 125, 50);
        shapes[6] = new Line(130, 90, 150, 112.5);
        shapes[7] = new Line(120, 90, 100, 112.5);
        shapes[8] = new Line(125, 50, 150, 25);
        shapes[9] = new Line(125, 50, 100, 25);
        for(int i = 0; i < shapes.length; i++)
        {
            shapes[i].setStroke(Color.web("0x89b5f0", 1.0));
            shapes[i].setStrokeWidth(5);
            drawingPane.getChildren().add(shapes[i]);
        }
    }
    public Group getDrawingPane()
    { return drawingPane; }
}

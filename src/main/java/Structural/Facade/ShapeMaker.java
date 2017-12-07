package Structural.Facade;

public class ShapeMaker {
    private Shape circle;
    private Shape rectanle;
    private Shape square;

    public ShapeMaker(){
        circle = new Circle();
        rectanle = new Rectangle();
        square = new Square();
    }

    public void drawShapes(){
        circle.draw();
        rectanle.draw();
        square.draw();
    }

    public void drawCircle(){
        circle.draw();
    }

    public void drawRectangle(){
        rectanle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}

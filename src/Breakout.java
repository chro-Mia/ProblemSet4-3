import processing.core.PApplet;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Breakout extends PApplet {
    private Ball b;
    private Paddle p;
    private ArrayList<Brick>bricks = new ArrayList<>();
    private int bricksPerRow = 10;
    private int rows = 3;


    public static void main(String[] args)
    {
        PApplet.main("Breakout");
    }

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        b = new Ball(this);
        p = new Paddle(this);

        for(int i = 1; i <= rows; i++){
            for(int j = 0; j < bricksPerRow; j++){
                bricks.add(new Brick(this, (this.width / bricksPerRow) * j, b.getDiam() * 2 * i, (this.width / bricksPerRow), b.getDiam() * 2));
            }
        }
    }

    public void draw() {
        background(0);
        p.move();
        b.move();

        p.checkCollision(b);

        for(int i = bricks.size() - 1; i > 0; i--){
            if(bricks.get(i).checkCollision(b)){
                bricks.remove(i);
            }
        }

        p.display();
        b.display();
        for(Brick brick : bricks){
            brick.display();
        }
    }
}
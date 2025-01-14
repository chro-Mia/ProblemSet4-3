import processing.core.PApplet;

public class Brick {
    private PApplet applet;
    private float x;
    private float y;
    private float brickLength;
    private float brickHeight;

    // x and y are the location of the top-left corner of the brick. So if
    // x = 100, y = 200, length = 30, height = 50
    // then the brick would have its top-left corner at (100, 200), and
    // its bottom-right corner at (130, 250)
    public Brick(PApplet applet, double x, double y, double length, double height) {
        this.applet = applet;
        this.x = (float)x;
        this.y = (float)y;
        brickLength = (float)length;
        brickHeight = (float)height;
    }

    public void display() {
        applet.fill(255);
        applet.rect(x, y, brickLength, brickHeight);
    }

    public boolean checkCollision(Ball b) {
        // test whether ball has hit the top
        if(b.getX() + b.getDiam()/2 >= x  &&
                b.getX() - b.getDiam()/2 <= x + brickLength  &&
                b.getY() + b.getDiam()/2 >= y &&
                b.getY() + b.getDiam()/2 <= y + b.getSpeedY() )
        {
            b.bounceVertical();
            return true;
        }

        // test whether ball has hit the bottom
        // y + brickHeight + b.getSpeedY() will be inside the brick, because
        // speedY is negative when the ball is going upwards.
        // If the ball is going downwards, this results in a condition that is never
        // true, which is just fine.
        if(b.getX() + b.getDiam()/2 >= x  &&
                b.getX() - b.getDiam()/2 <= x + brickLength  &&
                b.getY() - b.getDiam()/2 >= y + brickHeight + b.getSpeedY() &&
                b.getY() - b.getDiam()/2 <= y + brickHeight)
        {
            b.bounceVertical();
            return true;
        }

        // test whether ball has hit the left
        if(b.getY() + b.getDiam()/2 >= y  &&
                b.getY() - b.getDiam()/2 <= y + brickHeight  &&
                b.getX() + b.getDiam()/2 >= x &&
                b.getX() + b.getDiam()/2 <= x + b.getSpeedX() )
        {
            b.bounceHorizontal();
            return true;
        }

        // test whether ball has hit the right
        if(b.getY() + b.getDiam()/2 >= y  &&
                b.getY() - b.getDiam()/2 <= y + brickHeight  &&
                b.getX() - b.getDiam()/2 >= x + brickLength + b.getSpeedX()&&
                b.getX() - b.getDiam()/2 <= x + brickLength )
        {
            b.bounceHorizontal();
            return true;
        }
        return false;
    }
}
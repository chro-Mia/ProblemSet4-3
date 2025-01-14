import processing.core.PApplet;

public class Paddle {

    private PApplet applet;
    private int paddleLength;
    private int paddleHeight;
    private int x;
    private float y;
    private float speed;

    public Paddle(PApplet applet) {
        this.applet = applet;
        paddleLength = 70;
        paddleHeight = 15;
        x = applet.width / 2 - paddleLength/2;
        y = applet.height - 40;
        speed = 4;
    }

    public void display() {
        applet.rect(x, y, paddleLength, paddleHeight);
    }

    public float getX() {
        return x + paddleLength/2;
    }

    public float getY() {
        return y;
    }

    public int getPaddleLength() {
        return paddleLength;
    }

    public int getPaddleHeight() {
        return paddleHeight;
    }

    public void move() {
        if (applet.keyPressed && applet.key == applet.CODED) {
            if (applet.keyCode == applet.LEFT && x > 0) {
                x -= speed;
            } else if (applet.keyCode == applet.RIGHT && x < applet.width - paddleLength) {
                x += speed;
            }
        }
    }

    public boolean checkCollision(Ball b)
    {
        boolean hit = false;
        if(b.getX() + b.getDiam()/2 > x &&
                b.getX() - b.getDiam()/2 < x + paddleLength)
        {
            float ballBottom = b.getY() + b.getDiam()/2;
            if(b.getSpeedY() > 0 &&
                    ballBottom > y &&
                    ballBottom < y + paddleHeight)
            {
                hit = true;


                b.increaseSpeedY((float)0.2);  // to make game progressively more difficult

                // Divide paddle into 5 sections to get different
                // angle of hit for different sections.
                int hitboxWidth = paddleLength + b.getDiam();
                float relativeX = b.getX() - (x - b.getDiam() / 2);
                if (relativeX < hitboxWidth / 5) {
                    b.accelerateX(-2);
                }
                else if (relativeX < hitboxWidth * 2 / 5) {
                    b.accelerateX(-1);
                }
                else if (relativeX < hitboxWidth * 3 / 5) {
                    // don't change speedX
                }
                else if (relativeX < hitboxWidth * 4 / 5) {
                    b.accelerateX(1);
                }
                else {
                    b.accelerateX(2);
                }
                b.bounceVertical();

            }
        }
        return hit;

    }
}
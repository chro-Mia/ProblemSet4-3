import processing.core.PApplet;

public class Ball {

    private PApplet applet;
    private float x;
    private float y;
    private float speedX;
    private float speedY;
    private int diam;

    private boolean bounceVertical = false;
    private boolean bounceHorizontal = false;

    public Ball(PApplet applet) {
        this.applet = applet;
        x = applet.width * 3 / 4;
        y = applet.height / 2;
        speedX = 0.5f;
        speedY = -2.0f;
        diam = 20;
    }

    public void display() {
        applet.fill(255);
        applet.ellipse(x, y, diam, diam);
    }

    public void move() {

        if (x > applet.width - diam / 2 || x < diam / 2) {
            bounceHorizontal = true;
        }
        if (y < diam / 2) {
            bounceVertical = true;
        }
        if(bounceVertical)
        {
            speedY *= -1;
            bounceVertical = false;
        }
        if(bounceHorizontal)
        {
            speedX *= -1;
            bounceHorizontal = false;
        }

        x += speedX;
        y += speedY;
    }

    public void accelerateX(float delta)
    {
        speedX += delta;
    }

    public void increaseSpeedY(float delta)
    {
        if(speedY < 0)
        {
            speedY -= delta;
        }
        else
        {
            speedY += delta;
        }
    }

    public void bounceVertical() {
        bounceVertical = true;
    }

    public void bounceHorizontal() {
        bounceHorizontal = true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeedY() {
        return speedY;
    }
    public float getSpeedX() {
        return speedX;
    }

    public int getDiam() {
        return diam;
    }
}
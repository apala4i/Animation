package Point;

public class Point
{
    private int x = 0;
    private int y = 0;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void add(Point point)
    {
        x += point.x;
        y += point.y;
    }

    public void sub(Point point)
    {
        x -= point.x;
        y -= point.y;
    }

    public boolean equals(Point aPoint)
    {
        return ((this.x == aPoint.x) && (this.y == aPoint.y));
    }

    public int compareX(Point aPoint)
    {
        return this.x - aPoint.x;
    }

    public int compareY(Point aPoint)
    {
        return this.y - aPoint.y;
    }

    public Point clone()
    {
        return new Point(x, y);
    }

    public void rotate(Point center, double rotation)
    {
        Point diff = this.clone();
        diff.sub(center);
        this.x = (int) (diff.x * Math.cos(Math.toRadians(rotation)) - diff.y * Math.sin(Math.toRadians(rotation)) + center.x);
        this.y = (int) (diff.x * Math.sin(Math.toRadians(rotation)) + diff.y * Math.cos(Math.toRadians(rotation)) + center.y);
    }

}
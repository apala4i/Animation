package Animation;

public class Figure
{
    private String type = "none";
    private int size;
    private Point pos;
    private Point direction;

    public Figure(String type, int size, Point pos, Point direction)
    {
        this.direction = direction;
        this.type = type;
        this.size = size;
        this.pos = pos;
    }

    public String getType()
    {
        return type;
    }

    public int getSize()
    {
        return size;
    }

    public Point getPos()
    {
        return pos;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void stabPos(int width, int height)
    {
        if (pos.getX() < 0)
        {
            pos.setX(width - 1);
        }
        if (pos.getY() < 0)
        {
            pos.setY(height - 1);
        }
        pos.setX(pos.getX() % width);
        pos.setY(pos.getY() % height);
    }

    public void move()
    {
        pos.add(direction);
    }
    
}

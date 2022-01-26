package Figure;

import Point.*;

public class Figure
{
    private String type = "none";
    private int size = 0;
    private Point pos = null;
    private Point direction = null;

    public Figure(String type, int size, Point pos, Point direction)
    {
        this.direction = direction;
        this.type = type;
        this.size = size;
        this.pos = pos;
    }

    public Figure(){}

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

    public Point getDirection()
    {
        return direction; 
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void setPos(Point pos)
    {
        this.pos = pos;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setDirection(Point direction)
    {
        this.direction = direction;
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
        pos.setX(pos.getX() % (width + size));
        pos.setY(pos.getY() % (height + size));
    }

    public void move()
    {
        pos.add(direction);
    }
    
}

package Figure;

import Point.*;

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
        int a;
        // if (pos.getX() < 0)
        // {
        //     pos.setX(width - 1);
        // }
        // if (pos.getY() < 0)
        // {
        //     pos.setY(height - 1);
        // }
        if (Math.abs(pos.getX()) >= (width + size))
        {
            pos.setX(-size);   
        }
        else if (pos.getX() < -size)
        {
            pos.setX(width + size - 1);   
        }
        else
        {
            pos.setX(Integer.signum(pos.getX())*(Math.abs(pos.getX()) % (width + size)));
        }

        if (Math.abs(pos.getY()) >= (height + size))
        {
            pos.setY(-size);   
        }
        else if (pos.getY() < -size)
        {
            pos.setY(height + size - 1);   
        }
        else
        {
            pos.setY(Integer.signum(pos.getY())*(Math.abs(pos.getY()) % (height + size)));
        }
    }

    public void move()
    {
        pos.add(direction);
    }
    
}

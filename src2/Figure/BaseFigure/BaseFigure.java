package Figure.BaseFigure;

import Figure.*;
import Point.*;
// import java.util.ArrayList;

public abstract class BaseFigure extends Figure
{
    protected Point size;

    protected char filler = '*';

    public abstract boolean inFigureCheck(Point aPoint);

    public BaseFigure() {};

    protected abstract void fillFigurePixels();

    public BaseFigure(Point position, Point direction, double rotation, Point size)
    {
        super(position, direction, rotation);
        this.size = size.clone();
    }

    public Point getSize()
    {
        return size.clone();
    }

    public void setSize(Point size)
    {
        this.size = size.clone();
    }


}
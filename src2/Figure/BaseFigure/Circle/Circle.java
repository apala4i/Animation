package Figure.BaseFigure.Circle;

import Figure.BaseFigure.*;
import Point.*;
import Point.Pixel.*;

public final class Circle extends BaseFigure
{
    public Circle(Point position, int size, Point direction, double rotation)
    {
        super(position, direction, rotation, new Point(size, size));
        this.filler = 'o';
        fillFigurePixels();
    }

    public Circle(Point position, int size, Point direction, double rotation, char filler)
    {
        super(position, direction, rotation, new Point(size, size));
        this.filler = filler;
        fillFigurePixels();
    }

    @Override
    public boolean inFigureCheck(Point checkDot)
    {
        return (Math.pow((double) (checkDot.getX() - super.getPosition().getX()), 2)
                + Math.pow((double) (checkDot.getY() - super.getPosition().getY()), 2) <= Math.pow(size.getX(), 2));
    }

    @Override
    public void rotate() {}

    @Override
    protected void fillFigurePixels()
    {
        for (int y = super.getPosition().getY() - size.getX(); y <= super.getPosition().getY() + size.getX(); y++)
        {
            for (int x = super.getPosition().getX() - size.getX(); x <= super.getPosition().getX() + size.getX(); x++)
            {
                Point aPoint = new Point(x, y);
                if (inFigureCheck(aPoint))
                {
                    figurePixels.add(new Pixel(x, y, filler));
                }
            }
        }
    }
}
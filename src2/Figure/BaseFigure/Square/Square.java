package Figure.BaseFigure.Square;

import Figure.BaseFigure.*;
import Point.Point;
import Point.Pixel.*;

final public class Square extends BaseFigure
{
    public Square(Point position, Point direction, double rotation, int size)
    {
        super(position, direction, rotation, new Point(size, size));
        this.filler = '□';
        fillFigurePixels();
    }

    public Square(Point position, Point direction, double rotation, int size, char filler)
    {
        super(position, direction, rotation, new Point(size, size));
        this.filler = filler;
        fillFigurePixels();
    }

    public boolean inFigureCheck(Point aPoint)
    {
        return (double) Math.abs(aPoint.getX()) <= (double) super.getSize().getX() / 2 &&
                (double) Math.abs(aPoint.getX()) <= (double) super.getSize().getX() / 2;
    }

    protected void fillFigurePixels()
    {
        int startX = position.getX() - size.getX() / 2;
        int startY = position.getY() - size.getY() / 2;
        for (int y = startY; y < startY + size.getY(); ++y)
        {
            for (int x = startX; x < startX + size.getX(); ++x)
            {
                if (inFigureCheck(new Point(x, y)))
                {
                    figurePixels.add(new Pixel(x, y, filler));
                }
            }
        }
    }
}
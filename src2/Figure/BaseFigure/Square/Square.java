package Figure.BaseFigure.Square;

import Figure.BaseFigure.*;
import Point.Point;
import Point.Pixel.*;

final public class Square extends BaseFigure
{
    public Square(Point position, int size, Point direction, double rotation)
    {
        super(position, direction, rotation, new Point(size, size));
        this.filler = '□';
        fillFigurePixels();
    }

    public Square(Point position, int size, Point direction, double rotation, char filler)
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
                figurePixels.add(new Pixel(x, y, filler));
            }
        }
    }

    @Override
    public Square clone()
    {
        return new Square(position, size.getX(), direction, rotation, filler);
    }
}
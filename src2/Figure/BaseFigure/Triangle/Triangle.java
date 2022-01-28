package Figure.BaseFigure.Triangle;

import Figure.BaseFigure.*;
import Point.*;
import Point.Pixel.*;

public class Triangle extends BaseFigure
{
    public Triangle(Point position, Point size, Point direction, double rotation)
    {
        super(position, direction, rotation, size);
        this.filler = '△';
        fillFigurePixels();
    }

    public Triangle(Point position, Point size, Point direction, double rotation, char filler)
    {
        super(position, direction, rotation, size);
        this.filler = filler;
        fillFigurePixels();
    }

    @Override
    public boolean inFigureCheck(Point aPoint)
    {
        Point vertex1 = new Point(position.getX(), (int) (position.getY() - Math.sqrt(3) / 3 * size.getX()));
        Point vertex2 = new Point(position.getX() - size.getX() / 2,
                (int) (position.getY() + Math.sqrt(3) / 6 * size.getX()));
        Point vertex3 = new Point(position.getX() + size.getX() / 2,
                (int) (position.getY() + Math.sqrt(3) / 6 * size.getX()));

        int pr1 = (vertex1.getX() - aPoint.getX()) * (vertex2.getY() - vertex1.getY())
                - (vertex2.getX() - vertex1.getX()) * (vertex1.getY() - aPoint.getY());
        int pr2 = (vertex2.getX() - aPoint.getX()) * (vertex3.getY() - vertex2.getY())
                - (vertex3.getX() - vertex2.getX()) * (vertex2.getY() - aPoint.getY());
        int pr3 = (vertex3.getX() - aPoint.getX()) * (vertex1.getY() - vertex3.getY())
                - (vertex1.getX() - vertex3.getX()) * (vertex3.getY() - aPoint.getY());

        if ((pr1 > 0 && pr2 > 0 && pr3 > 0) || (pr1 < 0 && pr2 < 0 && pr3 < 0) || pr1 == 0 || pr2 == 0 || pr3 == 0)
            return true;
        return false;
    }

    @Override
    protected void fillFigurePixels()
    {
        for (int y = (int) (position.getY() - Math.sqrt(3) / 3 * size.getX()); y <= (int) (position.getY()
                + Math.sqrt(3) / 6 * size.getX()); y++)
        {
            for (int x = position.getX() - size.getX() / 2; x <= position.getX() + size.getX() / 2; x++)
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
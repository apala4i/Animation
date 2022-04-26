package Point;

import java.util.*;

public class PointComporator implements Comparator<Point>
{

    public int compare(Point firstPoint, Point secondPoint)
    {
        int compY = firstPoint.compareY(secondPoint);
        int compX = firstPoint.compareX(secondPoint);

        if (compY != 0)
        {
            return compY;
        }
        if (compX != 0)
        {
            return compX;
        }
        return 0;
    }
}

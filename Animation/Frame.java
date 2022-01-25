package Animation;

import java.util.ArrayList;

public class Frame
{
    private ArrayList<Point> points = new ArrayList<Point>();
    
    private int height;
    private int width;

    public Frame(Figure aFigure, int height, int width)
    {
        this.height = height;
        this.width = width;
        if (aFigure.getType().equals("Circle"))
        {
            MakeCircle(aFigure);
        }
    }

    private void MakeCircle(Figure aFigure)
    {
        for (int i = 0; i < width; ++i)
        {
            for (int j = 0; j < height; ++j)
            {
                if (inFigureCheck(aFigure, new Point(i, j)))
                {
                    points.add(new Point(i, j));
                }
            }
        }
    }

    private boolean inFigureCheck(Figure aFigure, Point checkDot)
    {
        return (Math.pow((double)(checkDot.getX() - aFigure.getPos().getX()), 2)
        + Math.pow((double)(checkDot.getY() - aFigure.getPos().getY()), 2) <= Math.pow(aFigure.getSize(), 2));
    }

    public void drawFrame()
    {
        // System.out.print("\033[H\033[2J");
        if (points.size() != 0)
        {
            int nextIndex = 0;
            Point nextPoint = points.get(nextIndex);
            for (int i = 0; i < height; ++i)
            {
                for (int j = 0; j < width / 2; ++j)
                {
                    if (new Point(i,j).equals(nextPoint))
                    {
                        System.out.print("**");
                        if (points.size() - 1 != nextIndex)
                        {
                            nextPoint = points.get(++nextIndex);
                        }
                    }
                    else
                    {
                        System.out.print("  ");
                    }
                }
                System.out.println();
        }
        }
    }
}





package Animation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Frame
{
    private ArrayList<Point> framePoints = new ArrayList<Point>();
    
    private int width;
    private int height; 
    private int squeezeX = 2;
    private int squeezeY = 1;
    
    public Frame(Figure aFigure, int width, int height)
    {
        this.height = height;
        this.width = width;
        if (aFigure.getType().equals("Circle"))
        {
            MakeCircle(aFigure);
        }
    }

    public Frame(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void addFigure(Figure aFigure)
    {
        if (aFigure.getType().equals("Circle"))
        {
            MakeCircle(aFigure);
        }
    }

    private void MakeCircle(Figure aFigure)
    {
        ArrayList<Point> figurePoints = new ArrayList<Point>();
        for (int i = 0; i < height; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                if (inFigureCheck(aFigure, new Point(j, i)))
                {
                    figurePoints.add(squeezePoint(new Point(j, i)));
                }
            }
        }
        Collections.sort(framePoints, new PointComporator());
        addFigureToFrame(figurePoints);
    }

    private Point squeezePoint(Point point)
    {
        return new Point(point.getX() * squeezeX % (width), point.getY() * squeezeY % (height));
    }


    private void addFigureToFrame(ArrayList<Point> figurePoints)
    {
        int curFrameIndex = 0;
        int curFigureIndex = 0;

        ArrayList<Point> resPoints = new ArrayList<Point>();

        while (curFrameIndex != framePoints.size() && curFigureIndex != figurePoints.size())
        {
            int cmpY = framePoints.get(curFrameIndex).compareY(figurePoints.get(curFigureIndex));
            int cmpX = framePoints.get(curFrameIndex).compareX(figurePoints.get(curFigureIndex));
            if ( cmpY != 0)
            {
                if (cmpY > 0)
                {
                    resPoints.add(figurePoints.get(curFigureIndex++));
                }
                else
                {
                    resPoints.add(framePoints.get(curFrameIndex++));
                }
            }
            else
            {
                if (cmpX > 0)
                {
                    resPoints.add(figurePoints.get(curFigureIndex++));
                }
                else if (cmpX < 0)
                {
                    resPoints.add(framePoints.get(curFrameIndex++));
                }
                else
                {
                    resPoints.add(framePoints.get(curFrameIndex++));
                    curFigureIndex++;
                }
            }
        }
        while (curFrameIndex != framePoints.size())
        {
            resPoints.add(framePoints.get(curFrameIndex++));
        }
        while (curFigureIndex != figurePoints.size())
        {
            resPoints.add(figurePoints.get(curFigureIndex++));
        }
        framePoints = resPoints;
    } 

    private boolean inFigureCheck(Figure aFigure, Point checkDot)
    {
        return (Math.pow((double)(checkDot.getX() - aFigure.getPos().getX()), 2)
        + Math.pow((double)(checkDot.getY() - aFigure.getPos().getY()), 2) <= Math.pow(aFigure.getSize(), 2));
    }

    public void drawFrame(Figure aFigure)
    {
        // System.out.print("\033[H\033[2J");
        if (framePoints.size() != 0)
        {
            int nextIndex = 0;
            Point nextPoint = framePoints.get(nextIndex);
            for (int i = 0; i < height; ++i)
            {
                for (int j = 0; j < width; ++j)
                {
                    if (new Point(j,i).equals(nextPoint))
                    {
                        System.out.print("*");
                        if (framePoints.size() - 1 != nextIndex)
                        {
                            nextPoint = framePoints.get(++nextIndex);
                        }
                    }
                    else
                    {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
        else
        {
            System.out.printf("posX - %d\nPosY - %d\n", aFigure.getPos().getX(), aFigure.getPos().getY());
        }
    }
}


